package dev.lasm.foodcraft.compat.jei;

import dev.lasm.foodcraft.FoodCraft;
import dev.lasm.foodcraft.init.ModItems;
import dev.lasm.foodcraft.init.ModTags;
import dev.lasm.foodcraft.recipe.FryingRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.AbstractRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;

public class FryingRecipeCategory extends AbstractRecipeCategory<RecipeHolder<FryingRecipe>> {
    private final static ResourceLocation TEXTURES = ResourceLocation.fromNamespaceAndPath(FoodCraft.MOD_ID, "textures/gui/jei/frying_pan.png");
    private final IDrawable background;

    protected final IDrawableStatic staticFlame;
    protected final IDrawableAnimated flame;
    protected final IDrawableAnimated arrow;

    public FryingRecipeCategory(IJeiHelpers helper) {
      super(FoodCraftJeiPlugin.FRYING_RECIPE, Component.translatable("block.foodcraft.frying_pan"), helper.getGuiHelper().createDrawableIngredient(
          VanillaTypes.ITEM_STACK, new ItemStack(ModItems.FRYING_PAN.get())), 142, 64);
        this.background = helper.getGuiHelper().createDrawable(TEXTURES, 7, 8, 142, 64);

        staticFlame = helper.getGuiHelper().createDrawable(TEXTURES, 176, 0, 14, 14);
        flame = helper.getGuiHelper().createAnimatedDrawable(staticFlame, 300, IDrawableAnimated.StartDirection.TOP, true);
        arrow = helper.getGuiHelper().drawableBuilder(TEXTURES, 176, 14, 24, 17)
            .buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    public void draw(RecipeHolder<FryingRecipe> recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics,
        double mouseX, double mouseY) {
        arrow.draw(guiGraphics, 89 - 8, 20 - 9);
        flame.draw(guiGraphics, 119 - 8, 52 - 9);
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder recipeLayout, RecipeHolder<FryingRecipe> recipeHolder,
        IFocusGroup focusGroup) {
        var recipe = recipeHolder.value();
        var recipeIngredients = recipe.getIngredients();

        recipeLayout.addSlot(RecipeIngredientRole.CATALYST, 33 - 6, 48 - 7).addIngredients(
            Ingredient.of(ModTags.COOKING_OIL));
        recipeLayout.addSlot(RecipeIngredientRole.INPUT, 54 - 6, 20 - 7).addIngredients(recipeIngredients.get(0));

        recipeLayout.addSlot(RecipeIngredientRole.OUTPUT, 126 - 6, 20 - 7).addItemStack(recipe.getResultItem(null));
    }
}
