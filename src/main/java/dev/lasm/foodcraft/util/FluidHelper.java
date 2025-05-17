package dev.lasm.foodcraft.util;

import dev.lasm.foodcraft.init.ModFluids;
import dev.lasm.foodcraft.init.ModTags;
import net.darkhax.bookshelf.common.api.data.codecs.map.MapCodecHelper;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.capabilities.Capabilities.FluidHandler;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.IFluidHandler.FluidAction;
import net.neoforged.neoforge.items.IItemHandlerModifiable;

public class FluidHelper {
    public static final MapCodecHelper<FluidStack> FLUID_STACK = new MapCodecHelper<>(FluidStack.CODEC);

    /**
     * Handle fluid slot per tick
     */
    public static void handleFluidSlot(IItemHandlerModifiable itemHandler, int slot, IFluidHandler fluidTank) {
        var item = itemHandler.getStackInSlot(slot);
        if (item.getCapability(FluidHandler.ITEM) != null) {
            var fluidActionResult = FluidUtil.tryEmptyContainerAndStow(item, fluidTank, itemHandler, Integer.MAX_VALUE, null, true);

            if (fluidActionResult.isSuccess()) {
                itemHandler.setStackInSlot(slot, fluidActionResult.getResult());
//                return true;
            } else {
//                return false;
            }
        } else if (item.is(ModTags.COOKING_OIL)) {
            if (fillIfWhole(fluidTank, new FluidStack(ModFluids.COOKING_OIL.get(), 1000))) {
                itemHandler.extractItem(slot, 1, false);
            }
        } else if (item.is(ModTags.WATER)) {
            if (fillIfWhole(fluidTank, new FluidStack(Fluids.WATER, 1000))) {
                itemHandler.extractItem(slot, 1, false);
            }
        } else if (item.is(ModTags.MILK)) {
            if (fillIfWhole(fluidTank, new FluidStack(NeoForgeMod.MILK.get(), 1000))) {
                itemHandler.extractItem(slot, 1, false);
            }
        }
    }


    public static boolean fillIfWhole(IFluidHandler fluidTank, FluidStack fluidStack) {
        if (fluidTank.fill(fluidStack, FluidAction.SIMULATE) == fluidStack.getAmount()) {
            fluidTank.fill(fluidStack, FluidAction.EXECUTE);
            return true;
        } else {
            return false;
        }
    }
}
