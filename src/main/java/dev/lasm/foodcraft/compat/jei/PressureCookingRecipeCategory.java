package dev.lasm.foodcraft.compat.jei;

import dev.lasm.foodcraft.FoodCraft;
import dev.lasm.foodcraft.init.ModItems;
import dev.lasm.foodcraft.recipe.PressureCookingRecipe;
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

public class PressureCookingRecipeCategory extends AbstractRecipeCategory<RecipeHolder<PressureCookingRecipe>> {
    private final static ResourceLocation TEXTURES = ResourceLocation.fromNamespaceAndPath(FoodCraft.MOD_ID, "textures/gui/jei/pressure_cooker.png");
    private final IDrawable background;

    protected final IDrawableStatic staticFlame;
    protected final IDrawableAnimated flame;
    protected final IDrawableAnimated arrow;

    public PressureCookingRecipeCategory(IJeiHelpers helper) {
      super(FoodCraftJeiPlugin.PRESSURE_COOKING_RECIPE, Component.translatable("block.foodcraft.pressure_cooker"), helper.getGuiHelper().createDrawableIngredient(
          VanillaTypes.ITEM_STACK, new ItemStack(ModItems.PRESSURE_COOKER.get())), 148, 64);
        this.background = helper.getGuiHelper().createDrawable(TEXTURES, 7, 8, 148, 64);

        staticFlame = helper.getGuiHelper().createDrawable(TEXTURES, 176, 0, 14, 14);
        flame = helper.getGuiHelper().createAnimatedDrawable(staticFlame, 300, IDrawableAnimated.StartDirection.TOP, true);
        arrow = helper.getGuiHelper().drawableBuilder(TEXTURES, 176, 14, 24, 17)
            .buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    public void draw(RecipeHolder<PressureCookingRecipe> recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics,
        double mouseX, double mouseY) {
        arrow.draw(guiGraphics, 103 - 5, 24 - 6);
        flame.draw(guiGraphics, 106 - 5, 55 - 6);
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder recipeLayout, RecipeHolder<PressureCookingRecipe> recipeHolder,
        IFocusGroup focusGroup) {
        var recipe = recipeHolder.value();
        var recipeIngredients = recipe.getIngredients();

        for (var x = 0; x < 3; x++) {
            var slot = recipeLayout.addSlot(RecipeIngredientRole.INPUT, x * 24 + 33 - 6, 26 - 7);
            slot.addIngredients(recipeIngredients.get(x));
        }

        recipeLayout.addSlot(RecipeIngredientRole.CATALYST, 11 - 7, 27 - 8).addFluidStack(recipe.getFluidInput().getFluid(), recipe.getFluidInput().getAmount());

        recipeLayout.addSlot(RecipeIngredientRole.OUTPUT, 131 - 6, 26 - 7).addItemStack( recipe.getResultItem(null));
    }
}
