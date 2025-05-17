package dev.lasm.foodcraft.block.entity;

import dev.lasm.foodcraft.api.FluidAttachedRecipeInput;
import dev.lasm.foodcraft.api.FluidRecipeWrapper;
import dev.lasm.foodcraft.api.ItemHandlerProvider;
import dev.lasm.foodcraft.container.MillMenu;
import dev.lasm.foodcraft.container.PressureCookerMenu;
import dev.lasm.foodcraft.init.ModBlockEntityTypes;
import dev.lasm.foodcraft.init.ModRecipeTypes;
import dev.lasm.foodcraft.recipe.MillingRecipe;
import dev.lasm.foodcraft.util.FluidHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeManager.CachedCheck;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.common.util.Lazy;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.wrapper.RangedWrapper;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MillBlockEntity extends BaseBlockEntity implements MenuProvider, ItemHandlerProvider {
    public static final int WORK_TIME = 200;

    public ItemStackHandler inventory;

    public int cookingTime = 0;
    public int burnTime = 0;
    public int maxCookingTime = 0;
    public int maxBurnTime = 0;

    private RecipeHolder<MillingRecipe> lastRecipe;

    private final CachedCheck<RecipeInput, MillingRecipe> quickCheck;

    public MillBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntityTypes.MILL.get(), pos, blockState);
        this.inventory = new ItemStackHandler(3) {
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

        this.quickCheck = RecipeManager.createCheck(ModRecipeTypes.MILLING.get());
    }

    @Override
    protected void loadAdditional(CompoundTag tag, Provider registries) {
        super.loadAdditional(tag, registries);
        if (tag.contains("Inventory"))
            inventory.deserializeNBT(registries, tag.getCompound("Inventory"));
        cookingTime = tag.getInt("CookingTime");
        burnTime = tag.getInt("BurnTime");
        maxCookingTime = tag.getInt("MaxCookingTime");
        maxBurnTime = tag.getInt("MaxBurnTime");
        if (level instanceof ServerLevel serverLevel) {
            var recipeId = ResourceLocation.tryParse(tag.getString("LastRecipe"));
            if (recipeId != null) {
                lastRecipe = (RecipeHolder<MillingRecipe>) serverLevel.getRecipeManager().byKey(recipeId).orElse(null);
            }
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("Inventory", inventory.serializeNBT(registries));
        tag.putInt("CookingTime", cookingTime);
        tag.putInt("BurnTime", burnTime);
        tag.putInt("MaxCookingTime", maxCookingTime);
        tag.putInt("MaxBurnTime", maxBurnTime);
        if (lastRecipe != null)
            tag.putString("LastRecipe", lastRecipe.id().toString());
    }

    private final Lazy<RecipeWrapper> recipeWrapperLazy = Lazy.of(() -> new RecipeWrapper(new RangedWrapper(inventory, 1, 2)));

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, @NotNull MillBlockEntity blockEntity) {
        if (blockEntity.burnTime > 0) {
            blockEntity.burnTime--;
            blockEntity.setChanged();
        }

        if (blockEntity.cookingTime > 0) {
            if (blockEntity.burnTime > 0) {
                blockEntity.cookingTime--;
                blockEntity.setChanged();
            } else {
                var fuel = blockEntity.inventory.extractItem(0, 1, true).getBurnTime(RecipeType.SMELTING);
                if (fuel > 0) {
                    blockEntity.inventory.extractItem(0, 1, false);
                    blockEntity.cookingTime--;
                    blockEntity.burnTime += fuel;
                    blockEntity.maxBurnTime = fuel;
                    blockEntity.setChanged();
                    detectLit(level, blockPos, blockState, true);
                } else {
                    detectLit(level, blockPos, blockState, false);
                }
            }
        }

        if (blockEntity.cookingTime == 0 && blockEntity.lastRecipe != null) {
            var result = blockEntity.lastRecipe.value().getResultItem(null).copy();

            if (blockEntity.inventory.insertItem(2, result, true) == ItemStack.EMPTY) {
                blockEntity.inventory.insertItem(2, result, false);
                blockEntity.setChanged();
                blockEntity.lastRecipe = null;
            } else {
                // Cannot output, is there a problem?
            }
        }

        if (blockEntity.lastRecipe == null) {
            var recipe = blockEntity.quickCheck.getRecipeFor(blockEntity.recipeWrapperLazy.get(), level).orElse(null);
            if (recipe != null) {
                if (blockEntity.burnTime == 0 && blockEntity.inventory.getStackInSlot(0).isEmpty()) {
                    return; // A tricky solution
                }
                if (!blockEntity.inventory.insertItem(2, recipe.value().getResultItem(null), true).isEmpty()) {
                    return; // Cannot insert
                }
                blockEntity.lastRecipe = recipe;
                blockEntity.cookingTime = WORK_TIME;
                blockEntity.maxCookingTime = WORK_TIME;
                blockEntity.inventory.extractItem(1, 1, false);
                blockEntity.setChanged();
                detectLit(level, blockPos, blockState, true);
            } else {
                blockEntity.lastRecipe = null;
                detectLit(level, blockPos, blockState, false);
            }
        }
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.foodcraft.mill");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory playerInv, Player player) {
        return new MillMenu(i, playerInv, this);
    }

    @Override
    public ItemStackHandler getInventory() {
        return inventory;
    }
}
