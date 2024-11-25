package dev.lasm.foodcraft.util;

import com.google.gson.JsonArray;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.crafting.Ingredient;

public class IngredientHelper {
    public static NonNullList<Ingredient> readIngredients(JsonArray ingredientArray) {
        NonNullList<Ingredient> nonnulllist = NonNullList.create();
        ingredientArray.asList().stream()
            .map(Ingredient::fromJson)
            .filter(x -> !x.isEmpty())
            .forEach(nonnulllist::add);

        return nonnulllist;
    }
}
