package dev.lasm.foodcraft.block.entity;

import dev.lasm.foodcraft.api.HeatableBlockEntity;
import dev.lasm.foodcraft.api.ItemHandlerProvider;
import dev.lasm.foodcraft.container.PanMenu;
import dev.lasm.foodcraft.init.ModBlockEntityTypes;
import dev.lasm.foodcraft.init.ModItems;
import dev.lasm.foodcraft.init.ModRecipeTypes;
import dev.lasm.foodcraft.init.ModTags;
import dev.lasm.foodcraft.recipe.PanRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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

public class PanBlockEntity extends BaseBlockEntity implements HeatableBlockEntity, MenuProvider,
    ItemHandlerProvider {

    public ItemStackHandler inventory;
    public int cookingTime = 0;

    @Nullable
    private RecipeHolder<PanRecipe> lastRecipe = null;

    private final RecipeManager.CachedCheck<RecipeWrapper, PanRecipe> quickCheck;

    public int minCookingTime;
    public int maxCookingTime;
    public int maxTime;
    public int proficiency;

    private boolean cooked;
    private boolean overcooked;

    public PanBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntityTypes.PAN.get(), pos, blockState);
        this.inventory = new ItemStackHandler(4) {
            @Override
            public int getSlotLimit(int slot) {
                return slot == 2 ? 1 : super.getSlotLimit(slot);
            }

            @Override
            public boolean isItemValid(int slot, ItemStack stack) {
                if (slot == 0) return stack.is(ModTags.COOKING_OIL);
                return super.isItemValid(slot, stack);
            }

            @Override
            protected void onContentsChanged(int slot) {
                if (slot == 2 && getStackInSlot(slot).isEmpty()) {
                    reset();
                }
            }
        };
        this.quickCheck = RecipeManager.createCheck(ModRecipeTypes.PAN.get());
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
                lastRecipe = (RecipeHolder<PanRecipe>) serverLevel.getRecipeManager().byKey(recipeId).orElse(null);
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

    private final Lazy<RecipeWrapper> recipeWrapperLazy = Lazy.of(() -> new RecipeWrapper(new RangedWrapper(inventory, 1, 2)));

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, PanBlockEntity blockEntity) {
        if (blockEntity.lastRecipe != null) {
            if (blockEntity.cookingTime >= blockEntity.minCookingTime) {
                if (blockEntity.cookingTime >= blockEntity.maxCookingTime) {
                    if (!blockEntity.overcooked) {
                        blockEntity.cooked = false;
                        blockEntity.overcooked = true;
                        blockEntity.inventory.insertItem(3, new ItemStack(ModItems.OVERCOOKED_FOOD.get()), false);
                        blockEntity.inventory.getStackInSlot(2).setCount(0);
                        level.playSound(null, blockPos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0f, 1.0f);
                    }
                } else {
                    if (!blockEntity.cooked) {
                        blockEntity.inventory.setStackInSlot(2, blockEntity.lastRecipe.value()
                            .getResultItem(null).copy());
                        blockEntity.cooked = true;
                        level.playSound(null, blockPos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0f, 1.0f);
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
            if (recipe != null && blockEntity.isHeated(level, blockPos)
                && !blockEntity.inventory.getStackInSlot(0).isEmpty() // Has oil
                && blockEntity.inventory.insertItem(2, recipe.value().getResultItem(null), true).isEmpty()) {
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

    public void toCraft(RecipeHolder<PanRecipe> recipe) {
        lastRecipe = recipe;
        cookingTime = 0;
        minCookingTime = recipe.value().getMinTime();
        maxCookingTime = recipe.value().getMaxTime();
        maxTime = recipe.value().getMaxTime() + 50;

        inventory.extractItem(0, 1, false);
        inventory.extractItem(1, 1, false);

        setChanged();
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.foodcraft.pan");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new PanMenu(i, inventory, this);
    }

    @Override
    public ItemStackHandler getInventory() {
        return inventory;
    }
}
