package dev.lasm.foodcraft.container;

import dev.lasm.foodcraft.init.ModBlocks;
import dev.lasm.foodcraft.init.ModMenuTypes;
import dev.lasm.foodcraft.init.ModRecipeTypes;
import dev.lasm.foodcraft.init.ModTags;
import dev.lasm.foodcraft.recipe.ChoppingRecipe;
import java.util.Optional;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.TransientCraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.ticks.ContainerSingleItem;
import org.jetbrains.annotations.NotNull;

public class ChoppingBoardMenu extends AbstractContainerMenu {
    private final ContainerLevelAccess access;

    private final CraftingContainer craftSlots = new TransientCraftingContainer(this, 3, 1);
    private final ResultContainer resultSlots = new ResultContainer();
    private final Container knifeContainer = new ContainerSingleItem() {
        private ItemStack theItem = ItemStack.EMPTY;

        @Override
        public ItemStack getTheItem() {
            return theItem;
        }

        @Override
        public void setTheItem(ItemStack item) {
            this.theItem = item;
        }

        @Override
        public void setChanged() {
            slotsChanged(knifeContainer);
        }

        @Override
        public boolean stillValid(Player player) {
            return true;
        }
    };

    private final Player player;

    public ChoppingBoardMenu(int containerId, Inventory playerInv, FriendlyByteBuf data) {
        this(containerId, playerInv, playerInv.player.level(), data.readBlockPos());
    }

    public ChoppingBoardMenu(int containerId, Inventory playerInv, Level level, BlockPos pos) {
        super(ModMenuTypes.CHOPPING_BOARD.get(), containerId);
        this.access = ContainerLevelAccess.create(level, pos);
        this.player = playerInv.player;

        addSlot(new Slot(knifeContainer, 0, 31, 26) {
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return stack.is(ModTags.KNIVES);
            }
        });

        addSlot(new Slot(craftSlots, 0, 70, 26));
        addSlot(new Slot(craftSlots, 1, 97, 26));
        addSlot(new Slot(craftSlots, 2, 124, 26));

        addSlot(new ChoppingBoardResultSlot(player, craftSlots, resultSlots, knifeContainer, 0, 97, 58));

        for (int k = 0; k < 3; k++) {
            for (int i1 = 0; i1 < 9; i1++) {
                this.addSlot(new Slot(playerInv, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
            }
        }

        for (int l = 0; l < 9; l++) {
            this.addSlot(new Slot(playerInv, l, 8 + l * 18, 142));
        }
    }

    protected static void slotChangedCraftingGrid(
        AbstractContainerMenu menu,
        Level level,
        Player player,
        CraftingContainer craftSlots,
        ResultContainer resultSlots,
        Container knifeSlots,
        @Nullable RecipeHolder<ChoppingRecipe> recipe
    ) {
        if (!level.isClientSide) {
            if (knifeSlots.getItem(0).isEmpty()) return;

            var recipeInput = new RecipeInput() {
                @Override
                public ItemStack getItem(int index) {
                    return craftSlots.getItem(index);
                }

                @Override
                public int size() {
                    return craftSlots.getContainerSize();
                }
            };
            ServerPlayer serverplayer = (ServerPlayer)player;
            ItemStack itemstack = ItemStack.EMPTY;
            Optional<RecipeHolder<ChoppingRecipe>> optional = level.getServer()
                .getRecipeManager()
                .getRecipeFor(ModRecipeTypes.CHOPPING.get(), recipeInput, level, recipe);
            if (optional.isPresent()) {
                RecipeHolder<ChoppingRecipe> recipeholder = optional.get();
                var craftingrecipe = recipeholder.value();
                if (resultSlots.setRecipeUsed(level, serverplayer, recipeholder)) {
                    ItemStack itemstack1 = craftingrecipe.assemble(recipeInput, level.registryAccess());
                    if (itemstack1.isItemEnabled(level.enabledFeatures())) {
                        itemstack = itemstack1;
                    }
                }
            }

            resultSlots.setItem(0, itemstack);
            menu.setRemoteSlot(0, itemstack);
            serverplayer.connection.send(new ClientboundContainerSetSlotPacket(menu.containerId, menu.incrementStateId(), 4, itemstack));
        }
    }

    /**
     * Callback for when the crafting matrix is changed.
     */
    @Override
    public void slotsChanged(Container inventory) {
        this.access.execute((p_344363_, p_344364_) -> slotChangedCraftingGrid(this, p_344363_, this.player, this.craftSlots, this.resultSlots, this.knifeContainer, null));
    }


    /**
     * Called when the container is closed.
     */
    @Override
    public void removed(Player player) {
        super.removed(player);
        this.access.execute((p_39371_, p_39372_) -> {
            this.clearContainer(player, this.craftSlots);
            this.clearContainer(player, this.knifeContainer);
        });
    }

    @Override
    public ItemStack quickMoveStack(Player player, int quickMovedSlotIndex) {
        if (player.level().isClientSide()) return ItemStack.EMPTY;
        // The quick moved slot stack
        ItemStack quickMovedStack = ItemStack.EMPTY;
        // The quick moved slot
        Slot quickMovedSlot = this.slots.get(quickMovedSlotIndex);

        // If the slot is in the valid range and the slot is not empty
        if (quickMovedSlot.hasItem()) {
            // Get the raw stack to move
            ItemStack rawStack = quickMovedSlot.getItem();
            // Set the slot stack to a copy of the raw stack
            quickMovedStack = rawStack.copy();

        /*
        The following quick move logic can be simplified to if in data inventory,
        try to move to player inventory/hotbar and vice versa for containers
        that cannot transform data (e.g. chests).
        */
            if (quickMovedSlotIndex > getMachineSlots() && quickMovedSlotIndex <= getMachineSlots() + 36) {
                // Try to move the inventory/hotbar slot into the data inventory input slots
                if (!this.moveItemStackTo(rawStack, 0, getMachineSlots(), false)) {
                    // If cannot move and in player inventory slot, try to move to hotbar
                    if (quickMovedSlotIndex < getMachineSlots() + 27) {
                        if (!this.moveItemStackTo(rawStack, getMachineSlots() + 27, getMachineSlots() + 36, false)) {
                            // If cannot move, no longer quick move
                            return ItemStack.EMPTY;
                        }
                    }
                    // Else try to move hotbar into player inventory slot
                    else if (!this.moveItemStackTo(rawStack, getMachineSlots(), getMachineSlots() + 27, false)) {
                        // If cannot move, no longer quick move
                        return ItemStack.EMPTY;
                    }
                }
            }
            // Else if the quick move was performed on the data inventory input slots, try to move to player inventory/hotbar
            else if (!this.moveItemStackTo(rawStack, getMachineSlots(), getMachineSlots() + 36, false)) {
                // If cannot move, no longer quick move
                return ItemStack.EMPTY;
            }

            if (rawStack.isEmpty()) {
                // If the raw stack has completely moved out of the slot, set the slot to the empty stack
                quickMovedSlot.set(ItemStack.EMPTY);
            } else {
                // Otherwise, notify the slot that that the stack count has changed
                quickMovedSlot.setChanged();
            }

        /*
        The following if statement and Slot#onTake call can be removed if the
        menu does not represent a container that can transform stacks (e.g.
        chests).
        */
            if (rawStack.getCount() == quickMovedStack.getCount()) {
                // If the raw stack was not able to be moved to another slot, no longer quick move
                return ItemStack.EMPTY;
            }
            // Execute logic on what to do post move with the remaining stack
            quickMovedSlot.onTake(player, rawStack);
        }

        return quickMovedStack; // Return the slot stack
    }

    public int getMachineSlots() {
        return 5;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(access, player, ModBlocks.CHOPPING_BOARD.get());
    }
}
