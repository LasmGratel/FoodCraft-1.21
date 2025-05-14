package dev.lasm.foodcraft.init;

import dev.lasm.foodcraft.FoodCraft;
import java.util.function.Supplier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.BaseFlowingFluid.Properties;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(BuiltInRegistries.FLUID,
        FoodCraft.MOD_ID);

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(
        NeoForgeRegistries.Keys.FLUID_TYPES, FoodCraft.MOD_ID);

    public static Supplier<Fluid> COOKING_OIL;
    public static Supplier<FlowingFluid> COOKING_OIL_FLOWING;

    public static final Supplier<FluidType> COOKING_OIL_TYPE;


    static {
        COOKING_OIL_TYPE = FLUID_TYPES.register("cooking_oil", () -> new FluidType(FluidType.Properties.create().density(3000)
            .canSwim(false)
            .canDrown(false)));
        COOKING_OIL = FLUIDS.register("cooking_oil",
            () -> new BaseFlowingFluid.Source(new Properties(COOKING_OIL_TYPE::get, COOKING_OIL::get, COOKING_OIL_FLOWING::get)));

        COOKING_OIL_FLOWING = FLUIDS.register("cooking_oil_flowing",
            () -> new BaseFlowingFluid.Flowing(new Properties(COOKING_OIL_TYPE::get, COOKING_OIL::get, COOKING_OIL_FLOWING::get)));
    }
}
