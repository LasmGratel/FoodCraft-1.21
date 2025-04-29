package dev.lasm.foodcraft.compat.jei;

import dev.lasm.foodcraft.FoodCraft;
import dev.lasm.foodcraft.init.ModItems;
import dev.lasm.foodcraft.recipe.BrewingRecipe;
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

public class BrewingRecipeCategory extends AbstractRecipeCategory<RecipeHolder<BrewingRecipe>> {
    private static final ResourceLocation TEXTURES = ResourceLocation.fromNamespaceAndPath(FoodCraft.MOD_ID, "textures/gui/jei/brew_barrel.png");
    private final IDrawable background;

    public BrewingRecipeCategory(IJeiHelpers helper) {
      super(FoodCraftJeiPlugin.BREWING_RECIPE, Component.translatable("block.foodcraft.brew_barrel"), helper.getGuiHelper().createDrawableIngredient(
          VanillaTypes.ITEM_STACK, new ItemStack(ModItems.BREW_BARREL.get())), 150, 64);
        this.background = helper.getGuiHelper().createDrawable(TEXTURES, 7, 8, 150, 64);

//        helper.getGuiHelper().createDrawableIngredient(ForgeTypes.FLUID_STACK, FluidStack.EMPTY).draw();
    }

    @Override
    public @Nullable IDrawable getBackground() {
        return background;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder recipeLayout, RecipeHolder<BrewingRecipe> recipeHolder,
        IFocusGroup focuses) {
        var recipe = recipeHolder.value();
        var recipeIngredients = recipe.getIngredients();

        recipeLayout.addSlot(RecipeIngredientRole.INPUT, 44, 20).addIngredients(recipeIngredients.get(0));
        recipeLayout.addSlot(RecipeIngredientRole.INPUT, 68, 20).addIngredients(recipeIngredients.get(1));
        recipeLayout.addSlot(RecipeIngredientRole.INPUT, 91, 20).addIngredients(recipeIngredients.get(2));
        recipeLayout.addSlot(RecipeIngredientRole.INPUT, 9, 5).addFluidStack(recipe.getFluidInput().getFluid(), recipe.getFluidInput().getAmount());

        recipeLayout.addSlot(RecipeIngredientRole.OUTPUT, 128, 20).addItemStack( recipe.getResultItem(null));

    }
}
