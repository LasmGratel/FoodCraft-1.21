package dev.lasm.foodcraft.compat.jei;

import dev.lasm.foodcraft.FoodCraft;
import dev.lasm.foodcraft.init.ModItems;
import dev.lasm.foodcraft.recipe.BeverageMakingRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class BeverageMakingRecipeCategory implements IRecipeCategory<BeverageMakingRecipe> {
    private final static ResourceLocation TEXTURES = new ResourceLocation(FoodCraft.MOD_ID, "textures/gui/jei/beverage_making.png");
    private final IJeiHelpers helper;

    private final IDrawable background;
    private final IDrawable icon;

    public BeverageMakingRecipeCategory(IJeiHelpers helper) {
        this.helper = helper;
        this.background = helper.getGuiHelper().createDrawable(TEXTURES, 7, 8, 160, 54);
        this.icon = helper.getGuiHelper().createDrawableIngredient(
            VanillaTypes.ITEM_STACK, new ItemStack(ModItems.AICI.get()));
    }

    @Override
    public RecipeType<BeverageMakingRecipe> getRecipeType() {
        return FoodCraftJeiPlugin.BEVERAGE_MAKING_RECIPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.foodcraft.beverage_making");
    }

    @Override
    public IDrawable getBackground() {
        return null;
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return null;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder iRecipeLayoutBuilder,
        BeverageMakingRecipe beverageMakingRecipe, IFocusGroup iFocusGroup) {

    }
}
