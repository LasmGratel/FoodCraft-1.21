package dev.lasm.foodcraft.compat.jei;

import dev.lasm.foodcraft.FoodCraft;
import dev.lasm.foodcraft.init.ModItems;
import dev.lasm.foodcraft.recipe.ChoppingRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.AbstractRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.Nullable;

public class ChoppingRecipeCategory extends AbstractRecipeCategory<RecipeHolder<ChoppingRecipe>> {
  private static final ResourceLocation TEXTURES = ResourceLocation.fromNamespaceAndPath(FoodCraft.MOD_ID, "textures/gui/jei/chopping_board.png");
  private final IDrawable background;

  public ChoppingRecipeCategory(IJeiHelpers helper) {
    super(FoodCraftJeiPlugin.CHOPPING_RECIPE, Component.translatable("block.foodcraft.chopping_board"), helper.getGuiHelper().createDrawableIngredient(
        VanillaTypes.ITEM_STACK, new ItemStack(ModItems.CHOPPING_BOARD.get())), 140 - 69 + 1, 69 - 20 + 1);
    this.background = helper.getGuiHelper().createDrawable(TEXTURES, 69, 20, 140 - 69 + 1, 69 - 20 + 1);
  }

  @Override
  public @Nullable IDrawable getBackground() {
    return background;
  }

  @Override
  public void setRecipe(
      IRecipeLayoutBuilder recipeLayout, RecipeHolder<ChoppingRecipe> recipeHolder,
      IFocusGroup focuses) {
    var recipe = recipeHolder.value();
    var recipeIngredients = recipe.getIngredients();

    recipeLayout.addSlot(RecipeIngredientRole.INPUT, 1, 1).addIngredients(recipeIngredients.get(0));
    recipeLayout.addSlot(RecipeIngredientRole.INPUT, 28, 1).addIngredients(recipeIngredients.get(1));
    recipeLayout.addSlot(RecipeIngredientRole.INPUT, 55, 1).addIngredients(recipeIngredients.get(2));

    recipeLayout.addSlot(RecipeIngredientRole.OUTPUT, 28, 53 - 20).addItemStack( recipe.getResultItem(null));

  }
}
