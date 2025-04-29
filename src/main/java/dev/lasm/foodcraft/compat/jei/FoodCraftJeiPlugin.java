package dev.lasm.foodcraft.compat.jei;

import dev.lasm.foodcraft.FoodCraft;
import dev.lasm.foodcraft.init.ModRecipeTypes;
import dev.lasm.foodcraft.recipe.BeverageMakingRecipe;
import dev.lasm.foodcraft.recipe.BrewingRecipe;
import dev.lasm.foodcraft.recipe.ChoppingRecipe;
import dev.lasm.foodcraft.recipe.FryingRecipe;
import dev.lasm.foodcraft.recipe.MillingRecipe;
import dev.lasm.foodcraft.recipe.PanRecipe;
import dev.lasm.foodcraft.recipe.PotRecipe;
import dev.lasm.foodcraft.recipe.PressureCookingRecipe;
import javax.annotation.ParametersAreNonnullByDefault;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@JeiPlugin
public class FoodCraftJeiPlugin implements IModPlugin {
    public static final RecipeType<RecipeHolder<BeverageMakingRecipe>> BEVERAGE_MAKING_RECIPE = RecipeType.createRecipeHolderType(ResourceLocation.fromNamespaceAndPath(FoodCraft.MOD_ID, "beverage_making"));
    public static final RecipeType<RecipeHolder<BrewingRecipe>> BREWING_RECIPE = RecipeType.createRecipeHolderType(ResourceLocation.fromNamespaceAndPath(FoodCraft.MOD_ID, "brewing"));
    public static final RecipeType<RecipeHolder<ChoppingRecipe>> CHOPPING_RECIPE = RecipeType.createRecipeHolderType(ResourceLocation.fromNamespaceAndPath(FoodCraft.MOD_ID, "chopping"));
    public static final RecipeType<RecipeHolder<FryingRecipe>> FRYING_RECIPE = RecipeType.createRecipeHolderType(ResourceLocation.fromNamespaceAndPath(FoodCraft.MOD_ID, "frying"));
    public static final RecipeType<RecipeHolder<MillingRecipe>> MILLING_RECIPE = RecipeType.createRecipeHolderType(ResourceLocation.fromNamespaceAndPath(FoodCraft.MOD_ID, "milling"));
    public static final RecipeType<RecipeHolder<PanRecipe>> PAN_RECIPE = RecipeType.createRecipeHolderType(ResourceLocation.fromNamespaceAndPath(FoodCraft.MOD_ID, "pan"));
    public static final RecipeType<RecipeHolder<PotRecipe>> POT_RECIPE = RecipeType.createRecipeHolderType(ResourceLocation.fromNamespaceAndPath(FoodCraft.MOD_ID, "pot"));
    public static final RecipeType<RecipeHolder<PressureCookingRecipe>> PRESSURE_COOKING_RECIPE = RecipeType.createRecipeHolderType(ResourceLocation.fromNamespaceAndPath(FoodCraft.MOD_ID, "pressure_cooking"));

    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(FoodCraft.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new PotRecipeCategory(registry.getJeiHelpers()));
        registry.addRecipeCategories(new BrewingRecipeCategory(registry.getJeiHelpers()));
        registry.addRecipeCategories(new BeverageMakingRecipeCategory(registry.getJeiHelpers()));
        registry.addRecipeCategories(new PanRecipeCategory(registry.getJeiHelpers()));
        registry.addRecipeCategories(new ChoppingRecipeCategory(registry.getJeiHelpers()));
        registry.addRecipeCategories(new FryingRecipeCategory(registry.getJeiHelpers()));
        registry.addRecipeCategories(new MillingRecipeCategory(registry.getJeiHelpers()));
        registry.addRecipeCategories(new PressureCookingRecipeCategory(registry.getJeiHelpers()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        var modRecipes = new FCRecipes();

        // Register all recipe types
        registration.addRecipes(BEVERAGE_MAKING_RECIPE, modRecipes.getRecipes(ModRecipeTypes.BEVERAGE_MAKING.get()));
        registration.addRecipes(BREWING_RECIPE, modRecipes.getRecipes(ModRecipeTypes.BREWING.get()));
        registration.addRecipes(CHOPPING_RECIPE, modRecipes.getRecipes(ModRecipeTypes.CHOPPING.get()));
        registration.addRecipes(FRYING_RECIPE, modRecipes.getRecipes(ModRecipeTypes.FRYING.get()));
        registration.addRecipes(MILLING_RECIPE, modRecipes.getRecipes(ModRecipeTypes.MILLING.get()));
        registration.addRecipes(PAN_RECIPE, modRecipes.getRecipes(ModRecipeTypes.PAN.get()));
        registration.addRecipes(POT_RECIPE, modRecipes.getRecipes(ModRecipeTypes.POT.get()));
        registration.addRecipes(PRESSURE_COOKING_RECIPE, modRecipes.getRecipes(ModRecipeTypes.PRESSURE_COOKING.get()));
    }
}
