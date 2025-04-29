package dev.lasm.foodcraft.api;


import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class FluidRecipeWrapper extends RecipeWrapper implements FluidAttachedRecipeInput {
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
    public FluidStack getFluidInTank(int tank) {
        return fluidHandler.getFluidInTank(tank);
    }

    @Override
    public int getTankCapacity(int tank) {
        return fluidHandler.getTankCapacity(tank);
    }

    @Override
    public boolean isFluidValid(int tank, FluidStack stack) {
        return fluidHandler.isFluidValid(tank, stack);
    }

    @Override
    public int fill(FluidStack resource, FluidAction action) {
        return fluidHandler.fill(resource, action);
    }

    @Override
    public FluidStack drain(FluidStack resource, FluidAction action) {
        return fluidHandler.drain(resource, action);
    }

    @Override
    public FluidStack drain(int maxDrain, FluidAction action) {
        return fluidHandler.drain(maxDrain, action);
    }
}
