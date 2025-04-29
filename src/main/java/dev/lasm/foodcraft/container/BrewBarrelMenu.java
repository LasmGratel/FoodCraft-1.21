package dev.lasm.foodcraft.container;

import dev.lasm.foodcraft.init.ModMenus;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;

public class BrewBarrelMenu extends AbstractContainerMenu {
    public BrewBarrelMenu(
        int containerId, Inventory playerInv) {
        super(ModMenus.BREW_BARREL.get(), containerId);
    }

    public BrewBarrelMenu(
        int containerId, Inventory playerInv, IItemHandler dataInventory, DataSlot dataSingle) {
        super(ModMenus.BREW_BARREL.get(), containerId);

        addSlot(new Slot(playerInv, 1, 1, 1));
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return false;
    }
}
