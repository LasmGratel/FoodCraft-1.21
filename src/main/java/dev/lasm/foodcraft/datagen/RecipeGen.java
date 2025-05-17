package dev.lasm.foodcraft.datagen;

import dev.lasm.foodcraft.init.ModItems;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

public class RecipeGen extends RecipeProvider {

    public RecipeGen(PackOutput output,
        CompletableFuture<Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput output) {
        fruitSapling(ModItems.GRAPEFRUIT, ModItems.GRAPEFRUIT_SAPLING, output);
        fruitSapling(ModItems.CHERRY, ModItems.CHERRY_SAPLING, output);
        fruitSapling(ModItems.COCONUT, ModItems.COCONUT_SAPLING, output);
        fruitSapling(ModItems.BANANA, ModItems.BANANA_SAPLING, output);
        fruitSapling(ModItems.PEACH, ModItems.PEACH_SAPLING, output);
        fruitSapling(ModItems.PERSIMMON, ModItems.PERSIMMON_SAPLING, output);
        fruitSapling(ModItems.POMEGRANATE, ModItems.POMEGRANATE_SAPLING, output);
        fruitSapling(ModItems.HAWTHORN, ModItems.HAWTHORN_SAPLING, output);
        fruitSapling(ModItems.LOQUAT, ModItems.LOQUAT_SAPLING, output);
        fruitSapling(ModItems.LEMON, ModItems.LEMON_SAPLING, output);
        fruitSapling(ModItems.PAPAYA, ModItems.PAPAYA_SAPLING, output);
        fruitSapling(ModItems.LONGAN, ModItems.LONGAN_SAPLING, output);
        fruitSapling(ModItems.MANGO, ModItems.MANGO_SAPLING, output);
        fruitSapling(ModItems.LITCHI, ModItems.LITCHI_SAPLING, output);
        fruitSapling(ModItems.PEAR, ModItems.PEAR_SAPLING, output);
        fruitSapling(ModItems.ORANGE, ModItems.ORANGE_SAPLING, output);
        fruitSapling(ModItems.DATE, ModItems.DATE_SAPLING, output);
    }

    private void fruitSapling(ItemLike fruit, Holder<Item> sapling, RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, sapling.value())
            .define('#', fruit)
            .define('X', ItemTags.SAPLINGS)
            .pattern(" # ")
            .pattern("#X#")
            .pattern(" # ")
            .unlockedBy("has_tag", has(ItemTags.SAPLINGS))
            .save(recipeOutput, sapling.unwrapKey().get().location());
    }
}
