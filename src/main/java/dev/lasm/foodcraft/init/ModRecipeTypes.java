package dev.lasm.foodcraft.init;

import dev.lasm.foodcraft.FoodCraft;
import dev.lasm.foodcraft.recipe.BeverageMakingRecipe;
import dev.lasm.foodcraft.recipe.BrewingRecipe;
import dev.lasm.foodcraft.recipe.ChoppingRecipe;
import dev.lasm.foodcraft.recipe.FryingRecipe;
import dev.lasm.foodcraft.recipe.MillingRecipe;
import dev.lasm.foodcraft.recipe.PanRecipe;
import dev.lasm.foodcraft.recipe.PotRecipe;
import dev.lasm.foodcraft.recipe.PressureCookingRecipe;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipeTypes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES;

    public static final DeferredHolder<RecipeType<?>, RecipeType<BeverageMakingRecipe>> BEVERAGE_MAKING;
    public static final DeferredHolder<RecipeType<?>, RecipeType<BrewingRecipe>> BREWING;
    public static final DeferredHolder<RecipeType<?>, RecipeType<ChoppingRecipe>> CHOPPING;
    public static final DeferredHolder<RecipeType<?>, RecipeType<FryingRecipe>> FRYING;
    public static final DeferredHolder<RecipeType<?>, RecipeType<MillingRecipe>> MILLING;
    public static final DeferredHolder<RecipeType<?>, RecipeType<PanRecipe>> PAN;
    public static final DeferredHolder<RecipeType<?>, RecipeType<PotRecipe>> POT;
    public static final DeferredHolder<RecipeType<?>, RecipeType<PressureCookingRecipe>> PRESSURE_COOKING;


    public static <T extends Recipe<?>> RecipeType<T> registerRecipeType(final String identifier) {
        return RecipeType.simple(ResourceLocation.fromNamespaceAndPath(FoodCraft.MOD_ID, identifier));
    }

    static {
        RECIPE_TYPES = DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, FoodCraft.MOD_ID);

        BEVERAGE_MAKING = RECIPE_TYPES.register("beverage_making", () -> registerRecipeType("beverage_making"));
        BREWING = RECIPE_TYPES.register("brewing", () -> registerRecipeType("brewing"));
        CHOPPING = RECIPE_TYPES.register("chopping", () -> registerRecipeType("chopping"));
        FRYING = RECIPE_TYPES.register("frying", () -> registerRecipeType("frying"));
        MILLING = RECIPE_TYPES.register("milling", () -> registerRecipeType("milling"));
        PAN = RECIPE_TYPES.register("pan", () -> registerRecipeType("pan"));
        POT = RECIPE_TYPES.register("pot", () -> registerRecipeType("pot"));
        PRESSURE_COOKING = RECIPE_TYPES.register("pressure_cooking", () -> registerRecipeType("pressure_cooking"));
    }
}
