package dev.lasm.foodcraft.block.entity;

import dev.lasm.foodcraft.api.FluidAttachedRecipeInput;
import dev.lasm.foodcraft.api.FluidRecipeWrapper;
import dev.lasm.foodcraft.container.BrewBarrelMenu;
import dev.lasm.foodcraft.init.ModBlockEntityTypes;
import dev.lasm.foodcraft.init.ModRecipeTypes;
import dev.lasm.foodcraft.recipe.BrewingRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.util.Lazy;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

public class BrewBarrelBlockEntity extends BaseBlockEntity implements MenuProvider {
    public static final int WORK_TIME = 180 * 20;

    public ItemStackHandler inventory;
    private FluidTank fluidTank;

    private int cookingTime = 0;

    private final RecipeManager.CachedCheck<FluidAttachedRecipeInput, BrewingRecipe> quickCheck;

    public BrewBarrelBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntityTypes.BREW_BARREL.get(), pos, blockState);
        this.inventory = new ItemStackHandler(6);
        this.fluidTank = new FluidTank(4000);
        this.quickCheck = RecipeManager.createCheck(ModRecipeTypes.BREWING.get());
    }

    @Override
    public CompoundTag getUpdateTag(Provider registries) {
        return super.getUpdateTag(registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, Provider registries) {
        super.loadAdditional(tag, registries);
        fluidTank.readFromNBT(registries, tag);
        if (tag.contains("Inventory"))
            inventory.deserializeNBT(registries, tag.getCompound("Inventory"));
        cookingTime = tag.getInt("CookingTime");

    }

    @Override
    protected void saveAdditional(CompoundTag tag, Provider registries) {
        super.saveAdditional(tag, registries);
        fluidTank.writeToNBT(registries, tag);
        tag.put("Inventory", inventory.serializeNBT(registries));
        tag.putInt("CookingTime", cookingTime);
    }

    private final Lazy<FluidRecipeWrapper> recipeWrapperLazy = Lazy.of(() -> new FluidRecipeWrapper(inventory, fluidTank));


    public static void tick(Level level, BlockPos blockPos, BlockState blockState, BrewBarrelBlockEntity blockEntity) {
        var recipe = blockEntity.quickCheck.getRecipeFor(blockEntity.recipeWrapperLazy.get(), level).orElse(null);
        if (recipe != null) {
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
}
