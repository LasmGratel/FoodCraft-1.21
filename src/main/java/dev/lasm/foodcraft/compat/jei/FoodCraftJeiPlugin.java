package dev.lasm.foodcraft.compat.jei;

import dev.lasm.foodcraft.FoodCraft;
import dev.lasm.foodcraft.client.screen.BrewBarrelScreen;
import dev.lasm.foodcraft.client.screen.FryingPanScreen;
import dev.lasm.foodcraft.container.BeverageMakingMenu;
import dev.lasm.foodcraft.container.BrewBarrelMenu;
import dev.lasm.foodcraft.container.ChoppingBoardMenu;
import dev.lasm.foodcraft.container.FryingPanMenu;
import dev.lasm.foodcraft.container.MillMenu;
import dev.lasm.foodcraft.container.PanMenu;
import dev.lasm.foodcraft.container.PotMenu;
import dev.lasm.foodcraft.container.PressureCookerMenu;
import dev.lasm.foodcraft.init.ModBlocks;
import dev.lasm.foodcraft.init.ModMenuTypes;
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
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
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
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(PotMenu.class, ModMenuTypes.POT.get(), POT_RECIPE, 0, 12, 13, 36);
        registration.addRecipeTransferHandler(PanMenu.class, ModMenuTypes.PAN.get(), PAN_RECIPE, 0, 2, 4, 36);
        registration.addRecipeTransferHandler(BrewBarrelMenu.class, ModMenuTypes.BREW_BARREL.get(), BREWING_RECIPE, 1, 3, 6, 36);
        registration.addRecipeTransferHandler(FryingPanMenu.class, ModMenuTypes.FRYING_PAN.get(), FRYING_RECIPE, 2, 1, 4, 36);
        registration.addRecipeTransferHandler(ChoppingBoardMenu.class, ModMenuTypes.CHOPPING_BOARD.get(), CHOPPING_RECIPE, 1, 3, 5, 36);
        registration.addRecipeTransferHandler(PressureCookerMenu.class, ModMenuTypes.PRESSURE_COOKER.get(), PRESSURE_COOKING_RECIPE, 2, 3, 6, 36);
        registration.addRecipeTransferHandler(BeverageMakingMenu.class, ModMenuTypes.BEVERAGE_MAKING.get(), BEVERAGE_MAKING_RECIPE, 3, 1, 5, 36);
        registration.addRecipeTransferHandler(MillMenu.class, ModMenuTypes.MILL.get(), MILLING_RECIPE, 1, 1, 3, 36);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(BrewBarrelScreen.class, 119, 31, 28, 23, BREWING_RECIPE);
        registration.addRecipeClickArea(FryingPanScreen.class, 92, 30, 28, 23, FRYING_RECIPE);
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

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(ModBlocks.BEVERAGE_MAKING.get(), BEVERAGE_MAKING_RECIPE);
        registration.addRecipeCatalyst(ModBlocks.BREW_BARREL.get(), BREWING_RECIPE);
        registration.addRecipeCatalyst(ModBlocks.CHOPPING_BOARD.get(), CHOPPING_RECIPE);
        registration.addRecipeCatalyst(ModBlocks.FRYING_PAN.get(), FRYING_RECIPE);
        registration.addRecipeCatalyst(ModBlocks.MILL.get(), MILLING_RECIPE);
        registration.addRecipeCatalyst(ModBlocks.PAN.get(), PAN_RECIPE);
        registration.addRecipeCatalyst(ModBlocks.POT.get(), POT_RECIPE);
        registration.addRecipeCatalyst(ModBlocks.PRESSURE_COOKER.get(), PRESSURE_COOKING_RECIPE);
    }
}
