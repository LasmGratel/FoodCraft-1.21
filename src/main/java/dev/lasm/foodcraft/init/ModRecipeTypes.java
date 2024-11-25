package dev.lasm.foodcraft.init;

import dev.lasm.foodcraft.FoodCraft;
import dev.lasm.foodcraft.recipe.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipeTypes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES;

    public static final RegistryObject<RecipeType<BeverageMakingRecipe>> BEVERAGE_MAKING;
    public static final RegistryObject<RecipeType<BrewingRecipe>> BREWING;
    public static final RegistryObject<RecipeType<ChoppingRecipe>> CHOPPING;
    public static final RegistryObject<RecipeType<FryingRecipe>> FRYING;
    public static final RegistryObject<RecipeType<MillingRecipe>> MILLING;
    public static final RegistryObject<RecipeType<PanRecipe>> PAN;
    public static final RegistryObject<RecipeType<PotRecipe>> POT;
    public static final RegistryObject<RecipeType<PressureCookingRecipe>> PRESSURE_COOKING;


    public static <T extends Recipe<?>> RecipeType<T> registerRecipeType(final String identifier) {
        return RecipeType.simple(new ResourceLocation(FoodCraft.MOD_ID, identifier));
    }

    static {
        RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, FoodCraft.MOD_ID);

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
