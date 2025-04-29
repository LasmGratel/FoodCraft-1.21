package dev.lasm.foodcraft.compat.jei;

import dev.lasm.foodcraft.FoodCraft;
import dev.lasm.foodcraft.init.ModItems;
import dev.lasm.foodcraft.recipe.PotRecipe;
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

public class PotRecipeCategory extends AbstractRecipeCategory<RecipeHolder<PotRecipe>> {
    private final static ResourceLocation TEXTURES = ResourceLocation.fromNamespaceAndPath(FoodCraft.MOD_ID, "textures/gui/jei/pot.png");
    private final IDrawable background;

    protected final IDrawableStatic staticFlame;
    protected final IDrawableAnimated flame;
    protected final IDrawableAnimated arrow;

    public PotRecipeCategory(IJeiHelpers helper) {
      super(FoodCraftJeiPlugin.POT_RECIPE, Component.translatable("block.foodcraft.pot"), helper.getGuiHelper().createDrawableIngredient(
          VanillaTypes.ITEM_STACK, new ItemStack(ModItems.POT.get())), 144, 56);
        this.background = helper.getGuiHelper().createDrawable(TEXTURES, 17, 16, 144, 56);

        staticFlame = helper.getGuiHelper().createDrawable(TEXTURES, 176, 0, 14, 14);
        flame = helper.getGuiHelper().createAnimatedDrawable(staticFlame, 300, IDrawableAnimated.StartDirection.TOP, true);
        arrow = helper.getGuiHelper().drawableBuilder(TEXTURES, 177, 18, 20, 9)
            .buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    public void draw(RecipeHolder<PotRecipe> recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics,
        double mouseX, double mouseY) {
        flame.draw(guiGraphics, 81 - 17, 57 - 16);
        arrow.draw(guiGraphics, 95 - 17, 21 - 16);
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder recipeLayout, RecipeHolder<PotRecipe> recipeHolder,
        IFocusGroup focusGroup) {
        var recipe = recipeHolder.value();
        var recipeIngredients = recipe.getIngredients();

        for (var x = 0; x < recipeIngredients.size(); x++) {
            var slot = recipeLayout.addSlot(RecipeIngredientRole.INPUT, x * 18 + 1, 38 - 16 + 1);
            slot.addIngredients(recipeIngredients.get(x));
        }

        var recipeStaples = recipe.getStaples();
        for (var x = 0; x < recipeStaples.size(); x++) {
            var slot = recipeLayout.addSlot(RecipeIngredientRole.INPUT, x * 18 + 1, 1);
            slot.addIngredients(recipeStaples.get(x));
        }

        recipeLayout.addSlot(RecipeIngredientRole.OUTPUT, 125 - 16, 1).addItemStack( recipe.getResultItem(null));
    }
}
