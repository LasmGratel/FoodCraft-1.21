package dev.lasm.foodcraft.network;

import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;

public class SyncFluidTank extends FluidTank {
    @Nullable
    private Runnable onContentsChanged;

    public SyncFluidTank(int capacity) {
        super(capacity);
    }

    public SyncFluidTank(int capacity,
        Predicate<FluidStack> validator) {
        super(capacity, validator);
    }

    @Override
    protected void onContentsChanged() {
        if (onContentsChanged != null)
            onContentsChanged.run();
    }

    public void setOnContentsChanged(@Nullable Runnable onContentsChanged) {
        this.onContentsChanged = onContentsChanged;
    }
}
