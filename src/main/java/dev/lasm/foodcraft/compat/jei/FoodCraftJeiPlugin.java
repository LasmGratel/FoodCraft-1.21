package dev.lasm.foodcraft.compat.jei;

import dev.lasm.foodcraft.FoodCraft;
import dev.lasm.foodcraft.init.ModRecipeTypes;
import dev.lasm.foodcraft.recipe.*;
import javax.annotation.ParametersAreNonnullByDefault;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.resources.ResourceLocation;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@JeiPlugin
public class FoodCraftJeiPlugin implements IModPlugin {
    public static final RecipeType<BeverageMakingRecipe> BEVERAGE_MAKING_RECIPE = new RecipeType<>(new ResourceLocation(FoodCraft.MOD_ID, "beverage_making"), BeverageMakingRecipe.class);
    public static final RecipeType<BrewingRecipe> BREWING_RECIPE = new RecipeType<>(new ResourceLocation(FoodCraft.MOD_ID, "brewing"), BrewingRecipe.class);
    public static final RecipeType<ChoppingRecipe> CHOPPING_RECIPE = new RecipeType<>(new ResourceLocation(FoodCraft.MOD_ID, "chopping"), ChoppingRecipe.class);
    public static final RecipeType<FryingRecipe> FRYING_RECIPE = new RecipeType<>(new ResourceLocation(FoodCraft.MOD_ID, "frying"), FryingRecipe.class);
    public static final RecipeType<MillingRecipe> MILLING_RECIPE = new RecipeType<>(new ResourceLocation(FoodCraft.MOD_ID, "milling"), MillingRecipe.class);
    public static final RecipeType<PanRecipe> PAN_RECIPE = new RecipeType<>(new ResourceLocation(FoodCraft.MOD_ID, "pan"), PanRecipe.class);
    public static final RecipeType<PotRecipe> POT_RECIPE = new RecipeType<>(new ResourceLocation(FoodCraft.MOD_ID, "pot"), PotRecipe.class);
    public static final RecipeType<PressureCookingRecipe> PRESSURE_COOKING_RECIPE = new RecipeType<>(new ResourceLocation(FoodCraft.MOD_ID, "pressure_cooking"), PressureCookingRecipe.class);

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(FoodCraft.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new PotRecipeCategory(registry.getJeiHelpers()));
        registry.addRecipeCategories(new BrewingRecipeCategory(registry.getJeiHelpers()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        var modRecipes = new FCRecipes();

        // Register all recipe types
//        registration.addRecipes(BEVERAGE_MAKING_RECIPE, modRecipes.getRecipes(ModRecipeTypes.BEVERAGE_MAKING.get()));
        registration.addRecipes(BREWING_RECIPE, modRecipes.getRecipes(ModRecipeTypes.BREWING.get()));
//        registration.addRecipes(CHOPPING_RECIPE, modRecipes.getRecipes(ModRecipeTypes.CHOPPING.get()));
//        registration.addRecipes(FRYING_RECIPE, modRecipes.getRecipes(ModRecipeTypes.FRYING.get()));
//        registration.addRecipes(MILLING_RECIPE, modRecipes.getRecipes(ModRecipeTypes.MILLING.get()));
//        registration.addRecipes(PAN_RECIPE, modRecipes.getRecipes(ModRecipeTypes.PAN.get()));
        registration.addRecipes(POT_RECIPE, modRecipes.getRecipes(ModRecipeTypes.POT.get()));
//        registration.addRecipes(PRESSURE_COOKING_RECIPE, modRecipes.getRecipes(ModRecipeTypes.PRESSURE_COOKING.get()));
    }
}
