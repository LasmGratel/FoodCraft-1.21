package dev.lasm.foodcraft.block.entity;

import dev.lasm.foodcraft.api.FluidAttachedRecipeInput;
import dev.lasm.foodcraft.api.FluidRecipeWrapper;
import dev.lasm.foodcraft.container.FryingPanMenu;
import dev.lasm.foodcraft.init.ModBlockEntityTypes;
import dev.lasm.foodcraft.init.ModFluids;
import dev.lasm.foodcraft.init.ModRecipeTypes;
import dev.lasm.foodcraft.init.ModTags;
import dev.lasm.foodcraft.recipe.FryingRecipe;
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
import net.minecraft.world.item.crafting.RecipeManager.CachedCheck;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.common.util.Lazy;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler.FluidAction;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

public class FryingPanBlockEntity extends BaseBlockEntity implements MenuProvider {
    public static final int WORK_TIME = 200;

    public ItemStackHandler inventory;
    private FluidTank fluidTank;

    private int cookingTime = 0;
    private int burnTime = 0;

    private RecipeHolder<FryingRecipe> lastRecipe;

    private final CachedCheck<FluidAttachedRecipeInput, FryingRecipe> quickCheck;

    public FryingPanBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntityTypes.FRYING_PAN.get(), pos, blockState);
        this.inventory = new ItemStackHandler(4) {
            @Override
            public boolean isItemValid(int slot, ItemStack stack) {
                return switch (slot) {
                    case 2 -> stack.is(ModTags.COOKING_OIL);
                    case 3 -> stack.getBurnTime(RecipeType.SMELTING) > 0;
                    default -> true;
                };
            }
        };

        this.fluidTank = new FluidTank(4000);
        this.quickCheck = RecipeManager.createCheck(ModRecipeTypes.FRYING.get());
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
        burnTime = tag.getInt("BurnTime");
        if (level instanceof ServerLevel serverLevel) {
            var recipeId = ResourceLocation.tryParse(tag.getString("LastRecipe"));
            if (recipeId != null) {
                lastRecipe = (RecipeHolder<FryingRecipe>) serverLevel.getRecipeManager().byKey(recipeId).orElse(null);
            }
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, Provider registries) {
        super.saveAdditional(tag, registries);
        fluidTank.writeToNBT(registries, tag);
        tag.put("Inventory", inventory.serializeNBT(registries));
        tag.putInt("CookingTime", cookingTime);
        tag.putInt("BurnTime", burnTime);
        if (lastRecipe != null)
            tag.putString("LastRecipe", lastRecipe.id().toString());
    }

    private final Lazy<FluidRecipeWrapper> recipeWrapperLazy = Lazy.of(() -> new FluidRecipeWrapper(inventory, fluidTank));


    public static void tick(Level level, BlockPos blockPos, BlockState blockState, FryingPanBlockEntity blockEntity) {
        if (blockEntity.burnTime > 0) {
            blockEntity.burnTime--;
            blockEntity.setChanged();
        }

        if (blockEntity.inventory.extractItem(2, 1, true).is(ModTags.COOKING_OIL)) {
            if (blockEntity.fluidTank.fill(new FluidStack(ModFluids.COOKING_OIL.get(), 1000), FluidAction.SIMULATE) == 1000) {
                blockEntity.fluidTank.fill(new FluidStack(ModFluids.COOKING_OIL.get(), 1000), FluidAction.EXECUTE);
                blockEntity.inventory.extractItem(2, 1, false);
                blockEntity.setChanged();
            }
        }

        if (blockEntity.cookingTime > 0) {
            if (blockEntity.burnTime > 0) {
                blockEntity.cookingTime--;
                blockEntity.setChanged();
            } else {
                var fuel = blockEntity.inventory.extractItem(3, 1, true).getBurnTime(RecipeType.SMELTING);
                if (fuel > 0) {
                    blockEntity.inventory.extractItem(3, 1, false);
                    blockEntity.cookingTime--;
                    blockEntity.burnTime += fuel;
                    blockEntity.setChanged();
                    detectLit(level, blockPos, blockState, true);
                } else {
                    detectLit(level, blockPos, blockState, false);
                }
            }
        }

        if (blockEntity.cookingTime == 0 && blockEntity.lastRecipe != null) {
            var result = blockEntity.lastRecipe.value().getResultItem(null).copy();

            if (blockEntity.inventory.insertItem(1, result, true) == ItemStack.EMPTY) {
                blockEntity.inventory.insertItem(1, result, false);
                blockEntity.setChanged();
                blockEntity.lastRecipe = null;
            } else {
                // Cannot output, is there a problem?
            }
        }

        if (blockEntity.lastRecipe == null) {
            var recipe = blockEntity.quickCheck.getRecipeFor(blockEntity.recipeWrapperLazy.get(), level).orElse(null);
            if (recipe != null) {
                if (blockEntity.burnTime == 0 && blockEntity.inventory.getStackInSlot(1).isEmpty()) {
                    return; // A tricky solution
                }
                blockEntity.lastRecipe = recipe;
                blockEntity.cookingTime = WORK_TIME;
                blockEntity.inventory.extractItem(0, 1, false);
                blockEntity.setChanged();
                detectLit(level, blockPos, blockState, true);
            } else {
                blockEntity.lastRecipe = null;
                detectLit(level, blockPos, blockState, false);
            }
        }
    }

    public static void detectLit(Level level, BlockPos blockPos, BlockState blockState, boolean working) {
        if (blockState.getValue(BlockStateProperties.LIT).booleanValue() != working) {
            level.setBlock(blockPos, blockState.setValue(BlockStateProperties.LIT, working), 3);
        }
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.foodcraft.frying_pan");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory playerInv, Player player) {
        return new FryingPanMenu(i, playerInv, this);
    }
}
