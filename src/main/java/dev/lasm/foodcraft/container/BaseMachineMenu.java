package dev.lasm.foodcraft.container;

import java.util.Objects;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.Nullable;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public abstract class BaseMachineMenu<T extends BlockEntity> extends AbstractContainerMenu implements MenuDataAccess {
    private final ContainerLevelAccess access;
    private final Block block;

    public BaseMachineMenu(@Nullable MenuType<?> menuType, int containerId, Inventory playerInv, FriendlyByteBuf data) {
        super(menuType, containerId);
        this.access = ContainerLevelAccess.NULL;
        this.block = null;
    }

    public BaseMachineMenu(@Nullable MenuType<?> menuType, int containerId, Inventory playerInv, T tileEntity) {
        super(menuType, containerId);
        block = tileEntity.getBlockState().getBlock();
        this.access = ContainerLevelAccess.create(tileEntity.getLevel(), tileEntity.getBlockPos());
    }

    public static <E> E getTileEntity(BlockGetter level, FriendlyByteBuf data, Class<E> tileEntityClass) {
        Objects.requireNonNull(level, "playerInventory cannot be null");
        Objects.requireNonNull(data, "data cannot be null");
        var tileAtPos = level.getBlockEntity(data.readBlockPos());
        if (tileAtPos != null && tileAtPos.getClass() == tileEntityClass) {
            return (E)tileAtPos;
        } else {
            throw new IllegalStateException("Tile entity is not correct! " + tileAtPos);
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int quickMovedSlotIndex) {
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

    public abstract int getMachineSlots();

    @Override
    public boolean stillValid(Player player) {
        return AbstractContainerMenu.stillValid(this.access, player, block);
    }

}
