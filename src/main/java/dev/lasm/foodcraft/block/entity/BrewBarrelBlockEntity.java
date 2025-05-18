package dev.lasm.foodcraft.block.entity;

import dev.lasm.foodcraft.api.FluidAttachedRecipeInput;
import dev.lasm.foodcraft.api.FluidRecipeWrapper;
import dev.lasm.foodcraft.api.ItemHandlerProvider;
import dev.lasm.foodcraft.container.BrewBarrelMenu;
import dev.lasm.foodcraft.init.ModBlockEntityTypes;
import dev.lasm.foodcraft.init.ModRecipeTypes;
import dev.lasm.foodcraft.network.SyncFluidTank;
import dev.lasm.foodcraft.recipe.BrewingRecipe;
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
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.common.util.Lazy;
import net.neoforged.neoforge.fluids.capability.IFluidHandler.FluidAction;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.wrapper.RangedWrapper;
import org.jetbrains.annotations.Nullable;

public class BrewBarrelBlockEntity extends BaseBlockEntity implements MenuProvider,
    ItemHandlerProvider {
    public static final int WORK_TIME = 8000;

    public ItemStackHandler inventory;
    public SyncFluidTank fluidTank;

    public int cookingTime = 0;

    @Nullable
    private RecipeHolder<BrewingRecipe> lastRecipe = null;

    private final RecipeManager.CachedCheck<FluidAttachedRecipeInput, BrewingRecipe> quickCheck;
    public int maxCookingTime;

    public BrewBarrelBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntityTypes.BREW_BARREL.get(), pos, blockState);
        this.inventory = new ItemStackHandler(6);
        this.fluidTank = new SyncFluidTank(4000);
        fluidTank.setValidator(fluidStack -> fluidStack.getFluid().isSame(Fluids.WATER));
        this.quickCheck = RecipeManager.createCheck(ModRecipeTypes.BREWING.get());
    }

    @Override
    protected void loadAdditional(CompoundTag tag, Provider registries) {
        super.loadAdditional(tag, registries);
        fluidTank.readFromNBT(registries, tag);
        if (tag.contains("Inventory"))
            inventory.deserializeNBT(registries, tag.getCompound("Inventory"));
        cookingTime = tag.getInt("CookingTime");
        maxCookingTime = tag.getInt("MaxCookingTime");
        if (level instanceof ServerLevel serverLevel) {
            var recipeId = ResourceLocation.tryParse(tag.getString("LastRecipe"));
            if (recipeId != null) {
                lastRecipe = (RecipeHolder<BrewingRecipe>) serverLevel.getRecipeManager().byKey(recipeId).orElse(null);
            }
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, Provider registries) {
        super.saveAdditional(tag, registries);
        fluidTank.writeToNBT(registries, tag);
        tag.put("Inventory", inventory.serializeNBT(registries));
        tag.putInt("CookingTime", cookingTime);
        tag.putInt("MaxCookingTime", maxCookingTime);
        if (lastRecipe != null)
            tag.putString("LastRecipe", lastRecipe.id().toString());
    }

    private final Lazy<FluidRecipeWrapper> recipeWrapperLazy = Lazy.of(() -> new FluidRecipeWrapper(new RangedWrapper(inventory, 1, 4), fluidTank));


    public static void tick(Level level, BlockPos blockPos, BlockState blockState, BrewBarrelBlockEntity blockEntity) {
        FluidHelper.handleFluidSlot(blockEntity.inventory, 0, blockEntity.fluidTank);

        if (blockEntity.cookingTime > 0) {
            blockEntity.cookingTime--;
            blockEntity.setChanged();
        }

        if (blockEntity.cookingTime == 0 && blockEntity.lastRecipe != null) {
            var result = blockEntity.lastRecipe.value().getResultItem(null).copy();

            if (blockEntity.inventory.insertItem(4, result, true) == ItemStack.EMPTY) {
                blockEntity.inventory.insertItem(4, result, false);
                blockEntity.inventory.setStackInSlot(5, ItemStack.EMPTY);
                blockEntity.setChanged();
                blockEntity.lastRecipe = null;
            } else {
                // Cannot output, is there a problem?
            }
        }

        if (blockEntity.lastRecipe == null) {
            var recipe = blockEntity.quickCheck.getRecipeFor(blockEntity.recipeWrapperLazy.get(), level).orElse(null);
            if (recipe != null) {
                if (!blockEntity.inventory.insertItem(4, recipe.value().getResultItem(null), true).isEmpty()) {
                    return; // Cannot insert
                }
                blockEntity.lastRecipe = recipe;
                blockEntity.cookingTime = WORK_TIME;
                blockEntity.maxCookingTime = WORK_TIME;
                blockEntity.inventory.extractItem(1, 1, false);
                blockEntity.inventory.extractItem(2, 1, false);
                blockEntity.inventory.extractItem(3, 1, false);

                blockEntity.fluidTank.drain(recipe.value().getFluidInput(), FluidAction.EXECUTE);
                blockEntity.inventory.setStackInSlot(5, recipe.value().getResultItem(null).copy());
                blockEntity.setChanged();
                detectLit(level, blockPos, blockState, true);
            } else {
                blockEntity.lastRecipe = null;
                blockEntity.inventory.setStackInSlot(5, ItemStack.EMPTY);
                detectLit(level, blockPos, blockState, false);
            }
        }
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.foodcraft.brew_barrel");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory playerInv, Player player) {
        return new BrewBarrelMenu(i, playerInv, this);
    }

    @Override
    public ItemStackHandler getInventory() {
        return inventory;
    }
}
