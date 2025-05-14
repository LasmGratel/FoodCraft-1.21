package dev.lasm.foodcraft.block.entity;

import dev.lasm.foodcraft.api.HeatableBlockEntity;
import dev.lasm.foodcraft.container.PotMenu;
import dev.lasm.foodcraft.init.ModBlockEntityTypes;
import dev.lasm.foodcraft.init.ModItems;
import dev.lasm.foodcraft.init.ModRecipeTypes;
import dev.lasm.foodcraft.recipe.PotRecipe;
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
import net.neoforged.neoforge.common.util.Lazy;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.wrapper.RangedWrapper;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;
import org.jetbrains.annotations.Nullable;

public class PotBlockEntity extends BaseBlockEntity implements HeatableBlockEntity, MenuProvider {

    public ItemStackHandler inventory;
    public int cookingTime = 0;

    @Nullable
    private RecipeHolder<PotRecipe> lastRecipe = null;

    private final RecipeManager.CachedCheck<RecipeWrapper, PotRecipe> quickCheck;

    public int minCookingTime;
    public int maxCookingTime;
    public int maxTime;
    public int proficiency;

    private boolean cooked;
    private boolean overcooked;

    public PotBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntityTypes.POT.get(), pos, blockState);
        this.inventory = new ItemStackHandler(14) {
            @Override
            public int getSlotLimit(int slot) {
                return slot == 12 ? 1 : super.getSlotLimit(slot);
            }

            @Override
            protected void onContentsChanged(int slot) {
                if (slot == 12 && getStackInSlot(slot).isEmpty()) {
                    reset();
                }
            }
        };
        this.quickCheck = RecipeManager.createCheck(ModRecipeTypes.POT.get());
    }

    @Override
    protected void loadAdditional(CompoundTag tag, Provider registries) {
        super.loadAdditional(tag, registries);
        if (tag.contains("Inventory"))
            inventory.deserializeNBT(registries, tag.getCompound("Inventory"));
        cookingTime = tag.getInt("CookingTime");
        minCookingTime = tag.getInt("MinCookingTime");
        maxCookingTime = tag.getInt("MaxCookingTime");
        maxTime = tag.getInt("MaxTime");
        if (level instanceof ServerLevel serverLevel) {
            var recipeId = ResourceLocation.tryParse(tag.getString("LastRecipe"));
            if (recipeId != null) {
                lastRecipe = (RecipeHolder<PotRecipe>) serverLevel.getRecipeManager().byKey(recipeId).orElse(null);
            }
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("Inventory", inventory.serializeNBT(registries));
        tag.putInt("CookingTime", cookingTime);
        tag.putInt("MinCookingTime", minCookingTime);
        tag.putInt("MaxCookingTime", maxCookingTime);
        tag.putInt("MaxTime", maxTime);
        if (lastRecipe != null)
            tag.putString("LastRecipe", lastRecipe.id().toString());
    }

    private final Lazy<RecipeWrapper> recipeWrapperLazy = Lazy.of(() -> new RecipeWrapper(new RangedWrapper(inventory, 0, 12)));

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, PotBlockEntity blockEntity) {
        if (blockEntity.lastRecipe != null) {
            if (blockEntity.cookingTime >= blockEntity.minCookingTime) {
                if (blockEntity.cookingTime >= blockEntity.maxCookingTime) {
                    if (!blockEntity.overcooked) {
                        blockEntity.cooked = false;
                        blockEntity.overcooked = true;
                        blockEntity.inventory.insertItem(13, new ItemStack(ModItems.OVERCOOKED_FOOD.get()), false);
                        blockEntity.inventory.getStackInSlot(12).setCount(0);
                    }
                } else {
                    if (!blockEntity.cooked) {
                        blockEntity.inventory.setStackInSlot(12, blockEntity.lastRecipe.value()
                            .getResultItem(null).copy());
                        blockEntity.cooked = true;
                    }
                }
            }
            if (blockEntity.isHeated(level, blockPos)) {
                blockEntity.cookingTime += blockEntity.proficiency + 1;
            }
            if (blockEntity.cookingTime >= blockEntity.maxTime) {
                blockEntity.reset();
            }
        }

        if (blockEntity.lastRecipe == null) {
            var recipe = blockEntity.quickCheck.getRecipeFor(blockEntity.recipeWrapperLazy.get(), level).orElse(null);
            if (recipe != null && blockEntity.isHeated(level, blockPos)) {
                blockEntity.toCraft(recipe);
                detectLit(level, blockPos, blockState, true);
            } else {
                blockEntity.reset();
                detectLit(level, blockPos, blockState, false);
            }
        }
    }

    public void reset() {
        lastRecipe = null;
        cookingTime = 0;
        minCookingTime = 0;
        maxCookingTime = 0;
        maxTime = 0;
        cooked = false;
        overcooked = false;
    }

    public void toCraft(RecipeHolder<PotRecipe> recipe) {
        lastRecipe = recipe;
        cookingTime = 0;
        minCookingTime = recipe.value().getMinTime();
        maxCookingTime = recipe.value().getMaxTime();
        maxTime = recipe.value().getMaxTime() + 50;

        for (var i = 0; i < 12; i++) {
            inventory.extractItem(i, 1, false);
        }

        setChanged();
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.foodcraft.pot");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new PotMenu(i, inventory, this);
    }
}
