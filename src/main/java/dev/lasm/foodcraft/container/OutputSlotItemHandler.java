package dev.lasm.foodcraft.container;

import java.util.function.BiConsumer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A basic output slot implementation.
 */
public class OutputSlotItemHandler extends SlotItemHandler {
    @Nullable
    private final BiConsumer<Player, ItemStack> takeFunc;

    public OutputSlotItemHandler(IItemHandler itemHandler, int index,
        int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
        takeFunc = null;
    }

    public OutputSlotItemHandler(IItemHandler itemHandler, int index,
        int xPosition, int yPosition, @Nullable BiConsumer<Player, ItemStack> takeFunc) {
        super(itemHandler, index, xPosition, yPosition);
        this.takeFunc = takeFunc;
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return false;
    }

    @Override
    public void onTake(@NotNull Player player, @NotNull ItemStack stack) {
        super.onTake(player, stack);
        if (this.takeFunc != null) {
            this.takeFunc.accept(player, stack);
        }
    }
}