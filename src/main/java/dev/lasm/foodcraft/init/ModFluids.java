package dev.lasm.foodcraft.init;

import dev.lasm.foodcraft.FoodCraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.material.EmptyFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(BuiltInRegistries.FLUID,
        FoodCraft.MOD_ID);

    static {
        FLUIDS.register("cooking_oil", () -> new EmptyFluid());
    }
}
