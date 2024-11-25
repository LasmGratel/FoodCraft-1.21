package dev.lasm.foodcraft.api;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import org.jetbrains.annotations.NotNull;

public class FluidRecipeWrapper extends RecipeWrapper implements FluidAttachedContainer {
    protected final IFluidHandler fluidHandler;

    public FluidRecipeWrapper(IItemHandlerModifiable inv, IFluidHandler fluidHandler) {
        super(inv);
        this.fluidHandler = fluidHandler;
    }

    @Override
    public int getTanks() {
        return fluidHandler.getTanks();
    }

    @Override
    public @NotNull FluidStack getFluidInTank(int tank) {
        return fluidHandler.getFluidInTank(tank);
    }

    @Override
    public int getTankCapacity(int tank) {
        return fluidHandler.getTankCapacity(tank);
    }

    @Override
    public boolean isFluidValid(int tank, @NotNull FluidStack stack) {
        return fluidHandler.isFluidValid(tank, stack);
    }

    @Override
    public int fill(FluidStack resource, FluidAction action) {
        return fluidHandler.fill(resource, action);
    }

    @Override
    public @NotNull FluidStack drain(FluidStack resource, FluidAction action) {
        return fluidHandler.drain(resource, action);
    }

    @Override
    public @NotNull FluidStack drain(int maxDrain, FluidAction action) {
        return fluidHandler.drain(maxDrain, action);
    }
}
