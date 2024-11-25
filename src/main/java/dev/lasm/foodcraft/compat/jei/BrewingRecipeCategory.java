package dev.lasm.foodcraft.compat.jei;

import dev.lasm.foodcraft.FoodCraft;
import dev.lasm.foodcraft.init.ModItems;
import dev.lasm.foodcraft.recipe.BrewingRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;

public class BrewingRecipeCategory implements IRecipeCategory<BrewingRecipe> {
    private final static ResourceLocation TEXTURES = new ResourceLocation(FoodCraft.MOD_ID, "textures/gui/jei/brew_barrel.png");
    private final IJeiHelpers helper;

    private final IDrawable background;
    private final IDrawable icon;

    public BrewingRecipeCategory(IJeiHelpers helper) {
        this.helper = helper;
        this.background = helper.getGuiHelper().createDrawable(TEXTURES, 7, 8, 150, 64);
        this.icon = helper.getGuiHelper().createDrawableIngredient(
            VanillaTypes.ITEM_STACK, new ItemStack(ModItems.AICI.get()));

//        helper.getGuiHelper().createDrawableIngredient(ForgeTypes.FLUID_STACK, FluidStack.EMPTY).draw();
    }

    @Override
    public RecipeType<BrewingRecipe> getRecipeType() {
        return FoodCraftJeiPlugin.BREWING_RECIPE;
    }

    @Override
    public void draw(BrewingRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics,
        double mouseX, double mouseY) {
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.foodcraft.brew_barrel");
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder recipeLayout, BrewingRecipe recipe,
        IFocusGroup focusGroup) {
        var recipeIngredients = recipe.getIngredients();

        recipeLayout.addSlot(RecipeIngredientRole.INPUT, 44, 20).addIngredients(recipeIngredients.get(0));
        recipeLayout.addSlot(RecipeIngredientRole.INPUT, 68, 20).addIngredients(recipeIngredients.get(1));
        recipeLayout.addSlot(RecipeIngredientRole.INPUT, 91, 20).addIngredients(recipeIngredients.get(2));
        recipeLayout.addSlot(RecipeIngredientRole.INPUT, 9, 5).addFluidStack(recipe.getFluidInput().getFluid(), recipe.getFluidInput().getAmount());

        recipeLayout.addSlot(RecipeIngredientRole.OUTPUT, 128, 20).addItemStack( recipe.getResultItem(null));

    }
}
