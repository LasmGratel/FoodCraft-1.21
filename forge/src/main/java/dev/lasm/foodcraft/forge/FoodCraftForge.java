package dev.lasm.foodcraft.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import dev.lasm.foodcraft.FoodCraft;

@Mod(FoodCraft.MOD_ID)
public final class FoodCraftForge {
    public FoodCraftForge() {
        // Submit our event bus to let Architectury API register our content on the right time.
        EventBuses.registerModEventBus(FoodCraft.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());

        // Run our common setup.
        FoodCraft.init();


    }
}
