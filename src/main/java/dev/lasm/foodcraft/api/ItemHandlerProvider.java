package dev.lasm.foodcraft.api;

import net.neoforged.neoforge.items.IItemHandlerModifiable;

public interface ItemHandlerProvider {
    IItemHandlerModifiable getInventory();
}
