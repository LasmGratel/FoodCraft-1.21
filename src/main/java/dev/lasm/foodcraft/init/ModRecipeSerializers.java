package dev.lasm.foodcraft.init;

import dev.lasm.foodcraft.FoodCraft;
import dev.lasm.foodcraft.recipe.*;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS;

    // Recipe serializer fields for each cooking method
    public static final RegistryObject<RecipeSerializer<?>> BEVERAGE_MAKING;
    public static final RegistryObject<RecipeSerializer<?>> BREWING;
    public static final RegistryObject<RecipeSerializer<?>> CHOPPING;
    public static final RegistryObject<RecipeSerializer<?>> FRYING;
    public static final RegistryObject<RecipeSerializer<?>> MILLING;
    public static final RegistryObject<RecipeSerializer<?>> PAN;
    public static final RegistryObject<RecipeSerializer<?>> POT;
    public static final RegistryObject<RecipeSerializer<?>> PRESSURE_COOKING;

    static {
        RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, FoodCraft.MOD_ID);

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
