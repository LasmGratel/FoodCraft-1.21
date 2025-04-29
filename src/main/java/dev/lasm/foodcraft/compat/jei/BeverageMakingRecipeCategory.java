package dev.lasm.foodcraft.compat.jei;

import dev.lasm.foodcraft.FoodCraft;
import dev.lasm.foodcraft.init.ModItems;
import dev.lasm.foodcraft.recipe.BeverageMakingRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.AbstractRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.Nullable;

public class BeverageMakingRecipeCategory extends AbstractRecipeCategory<RecipeHolder<BeverageMakingRecipe>> {
    private final static ResourceLocation TEXTURES = ResourceLocation.fromNamespaceAndPath(FoodCraft.MOD_ID, "textures/gui/jei/beverage_making.png");
    private final IJeiHelpers helper;

    private final IDrawable background;
    private final IDrawableAnimated arrow;

    public BeverageMakingRecipeCategory(IJeiHelpers helper) {
      super(FoodCraftJeiPlugin.BEVERAGE_MAKING_RECIPE, Component.translatable("block.foodcraft.beverage_making"), helper.getGuiHelper().createDrawableIngredient(
          VanillaTypes.ITEM_STACK, new ItemStack(ModItems.BEVERAGE_MAKING.get())), 150, 64);
      this.helper = helper;
        this.background = helper.getGuiHelper().createDrawable(TEXTURES, 7, 8, 150, 64);
        this.arrow = helper.getGuiHelper().drawableBuilder(TEXTURES, 176, 14, 24, 17)
            .buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    @Nullable
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public void draw(RecipeHolder<BeverageMakingRecipe> recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        arrow.draw(guiGraphics, 47, 18);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder recipeLayout, RecipeHolder<BeverageMakingRecipe> recipeHolder,
        IFocusGroup focuses) {
        var recipe = recipeHolder.value();
        var recipeIngredients = recipe.getIngredients();

        recipeLayout.addSlot(RecipeIngredientRole.INPUT, 33 - 6, 26 - 7).addIngredients(recipeIngredients.get(0));
        recipeLayout.addSlot(RecipeIngredientRole.INPUT, 9, 5).addFluidStack(recipe.getFluidInput().getFluid(), recipe.getFluidInput().getAmount());

        recipeLayout.addSlot(RecipeIngredientRole.OUTPUT, 81 - 6, 26 - 7).addItemStack( recipe.getResultItem(null));

    }
}
