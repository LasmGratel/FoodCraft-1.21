package dev.lasm.foodcraft.util;

import net.darkhax.bookshelf.common.api.data.codecs.map.MapCodecHelper;
import net.neoforged.neoforge.fluids.FluidStack;

public class FluidHelper {
    public static final MapCodecHelper<FluidStack> FLUID_STACK = new MapCodecHelper<>(FluidStack.CODEC);
}
