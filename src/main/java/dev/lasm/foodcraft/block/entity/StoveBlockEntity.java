package dev.lasm.foodcraft.block.entity;

import dev.lasm.foodcraft.api.FluidRecipeWrapper;
import dev.lasm.foodcraft.api.ItemHandlerProvider;
import dev.lasm.foodcraft.container.StoveMenu;
import dev.lasm.foodcraft.init.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.util.Lazy;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.wrapper.RangedWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StoveBlockEntity extends BaseBlockEntity implements MenuProvider,
    ItemHandlerProvider {
    public ItemStackHandler inventory;

    public int burnTime = 0;
    public int maxBurnTime = 0;

    public StoveBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntityTypes.STOVE.get(), pos, blockState);
        this.inventory = new ItemStackHandler(1) {
            @Override
            public boolean isItemValid(int slot, ItemStack stack) {
                return switch (slot) {
                    case 0 -> stack.getBurnTime(RecipeType.SMELTING) > 0;
                    default -> true;
                };
            }

            @Override
            protected void onContentsChanged(int slot) {
                inventoryChanged();
            }
        };
    }

    @Override
    protected void loadAdditional(CompoundTag tag, Provider registries) {
        super.loadAdditional(tag, registries);
        if (tag.contains("Inventory"))
            inventory.deserializeNBT(registries, tag.getCompound("Inventory"));
        burnTime = tag.getInt("BurnTime");
        maxBurnTime = tag.getInt("MaxBurnTime");
    }

    @Override
    protected void saveAdditional(CompoundTag tag, Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("Inventory", inventory.serializeNBT(registries));
        tag.putInt("BurnTime", burnTime);
        tag.putInt("MaxBurnTime", maxBurnTime);
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, @NotNull StoveBlockEntity blockEntity) {
        if (blockEntity.burnTime > 0) {
            blockEntity.burnTime--;
            blockEntity.setChanged();
        } else if (!blockEntity.inventory.getStackInSlot(0).isEmpty()) {
            var burnTime = blockEntity.inventory.extractItem(0, 1, true).getBurnTime(RecipeType.SMELTING);
            if (burnTime > 0) {
                burnTime *= 2;
                blockEntity.inventory.extractItem(0, 1, false);
                blockEntity.maxBurnTime = burnTime;
                blockEntity.burnTime = burnTime;
                detectLit(level, blockPos, blockState, true);
                blockEntity.setChanged();
            } else {
                detectLit(level, blockPos, blockState, false);
                blockEntity.setChanged();
            }
        } else {
            blockEntity.maxBurnTime = 0;
            detectLit(level, blockPos, blockState, false);
            blockEntity.setChanged();
        }
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.foodcraft.stove");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory playerInv, Player player) {
        return new StoveMenu(i, playerInv, this);
    }

    @Override
    public ItemStackHandler getInventory() {
        return inventory;
    }
}
