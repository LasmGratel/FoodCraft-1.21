package dev.lasm.foodcraft.fabric;

import net.fabricmc.api.ModInitializer;

import dev.lasm.foodcraft.FoodCraft;

public final class FoodCraftFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        FoodCraft.init();
    }
}
