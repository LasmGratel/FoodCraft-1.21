package dev.lasm.foodcraft.compat.jei;

import dev.lasm.foodcraft.FoodCraft;
import dev.lasm.foodcraft.init.ModItems;
import dev.lasm.foodcraft.recipe.MillingRecipe;
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
import net.minecraft.world.item.crafting.RecipeHolder;

public class MillingRecipeCategory extends AbstractRecipeCategory<RecipeHolder<MillingRecipe>> {
    private final static ResourceLocation TEXTURES = ResourceLocation.fromNamespaceAndPath(FoodCraft.MOD_ID, "textures/gui/jei/mill.png");
    private final IDrawable background;

    protected final IDrawableStatic staticFlame;
    protected final IDrawableAnimated flame;
    protected final IDrawableAnimated arrow;

    public MillingRecipeCategory(IJeiHelpers helper) {
        super(FoodCraftJeiPlugin.MILLING_RECIPE, Component.translatable("block.foodcraft.mill"), helper.getGuiHelper().createDrawableIngredient(
            VanillaTypes.ITEM_STACK, new ItemStack(ModItems.MILL.get())), 93, 64);
        this.background = helper.getGuiHelper().createDrawable(TEXTURES, 42, 12, 93, 64);

        staticFlame = helper.getGuiHelper().createDrawable(TEXTURES, 176, 0, 14, 14);
        flame = helper.getGuiHelper().createAnimatedDrawable(staticFlame, 300, IDrawableAnimated.StartDirection.TOP, true);
        arrow = helper.getGuiHelper().drawableBuilder(TEXTURES, 176, 14, 24, 17)
            .buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    public void draw(RecipeHolder<MillingRecipe> recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        arrow.draw(guiGraphics, 74 - 40, 19 - 10);
        flame.draw(guiGraphics, 78 - 40, 33 - 10);
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder recipeLayout, RecipeHolder<MillingRecipe> recipeHolder,
        IFocusGroup focusGroup) {
        var recipe = recipeHolder.value();
        var recipeIngredients = recipe.getIngredients();

        recipeLayout.addSlot(RecipeIngredientRole.INPUT, 48 - 41, 18 - 11).addIngredients(recipeIngredients.get(0));

        recipeLayout.addSlot(RecipeIngredientRole.OUTPUT, 111 - 41, 18 - 11).addItemStack(recipe.getResultItem(null));
    }
}
