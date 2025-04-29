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
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS;

    // Recipe serializer fields for each cooking method
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<?>> BEVERAGE_MAKING;
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<?>> BREWING;
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<?>> CHOPPING;
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<?>> FRYING;
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<?>> MILLING;
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<?>> PAN;
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<?>> POT;
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<?>> PRESSURE_COOKING;

    static {
        RECIPE_SERIALIZERS = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, FoodCraft.MOD_ID);

        // Register all recipe serializers
        BEVERAGE_MAKING = RECIPE_SERIALIZERS.register("beverage_making", BeverageMakingRecipe.Serializer::new);
        BREWING = RECIPE_SERIALIZERS.register("brewing", BrewingRecipe.Serializer::new);
        CHOPPING = RECIPE_SERIALIZERS.register("chopping", ChoppingRecipe.Serializer::new);
        FRYING = RECIPE_SERIALIZERS.register("frying", FryingRecipe.Serializer::new);
        MILLING = RECIPE_SERIALIZERS.register("milling", MillingRecipe.Serializer::new);
        PAN = RECIPE_SERIALIZERS.register("pan", PanRecipe.Serializer::new);
        POT = RECIPE_SERIALIZERS.register("pot", PotRecipe.Serializer::new);
        PRESSURE_COOKING = RECIPE_SERIALIZERS.register("pressure_cooking", PressureCookingRecipe.Serializer::new);
    }
}
