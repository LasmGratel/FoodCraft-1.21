package dev.lasm.foodcraft.container;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class ChoppingBoardResultSlot extends Slot {
    private final CraftingContainer craftSlots;
    private final Player player;
    private final Container knifeContainer;
    private int removeCount;

    public ChoppingBoardResultSlot(Player player, CraftingContainer craftSlots, Container container, Container knifeContainer, int slot, int xPosition, int yPosition) {
        super(container, slot, xPosition, yPosition);
        this.player = player;
        this.craftSlots = craftSlots;
        this.knifeContainer = knifeContainer;
    }

    /**
     * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
     */
    @Override
    public boolean mayPlace(ItemStack stack) {
        return false;
    }

    /**
     * Decrease the size of the stack in slot (first int arg) by the amount of the second int arg. Returns the new stack.
     */
    @Override
    public ItemStack remove(int amount) {
        if (this.hasItem()) {
            this.removeCount = this.removeCount + Math.min(amount, this.getItem().getCount());
        }

        return super.remove(amount);
    }

    /**
     * Typically increases an internal count, then calls {@code onCrafting(item)}.
     *
     * @param stack the output - ie, iron ingots, and pickaxes, not ore and wood.
     */
    @Override
    protected void onQuickCraft(ItemStack stack, int amount) {
        this.removeCount += amount;
        this.checkTakeAchievements(stack);
    }

    @Override
    protected void onSwapCraft(int numItemsCrafted) {
        this.removeCount += numItemsCrafted;
    }

    @Override
    public void onTake(Player player, ItemStack stack) {
        if (player instanceof ServerPlayer serverPlayer) {
            for (int i = 0; i < craftSlots.getContainerSize(); i++) {
                craftSlots.removeItem(i, 1);
                if (craftSlots.getItem(i).isEmpty()) {
                    craftSlots.setItem(i, ItemStack.EMPTY);
                }
            }
            var knifeItem = this.knifeContainer.getItem(0);
            knifeItem.hurtAndBreak(1, serverPlayer.serverLevel(), serverPlayer, item -> {
                this.knifeContainer.setItem(0, ItemStack.EMPTY);
            });
            this.knifeContainer.setItem(0, knifeItem);
            this.knifeContainer.setChanged();
        }
    }

    @Override
    public boolean isFake() {
        return true;
    }
}
