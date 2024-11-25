package dev.lasm.foodcraft.recipe;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import dev.lasm.foodcraft.init.ModRecipeSerializers;
import dev.lasm.foodcraft.init.ModRecipeTypes;
import dev.lasm.foodcraft.util.IngredientHelper;
import java.util.ArrayList;
import java.util.Objects;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.util.RecipeMatcher;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MillingRecipe implements Recipe<RecipeWrapper> {
    public static final int INGREDIENT_SLOTS = 1;

    private final ResourceLocation id;
    private final String group;
    private final NonNullList<Ingredient> ingredients;
    private final ItemStack output;

    public MillingRecipe(ResourceLocation id, String group,
        NonNullList<Ingredient> ingredients, ItemStack output) {
        this.id = id;
        this.group = group;
        this.ingredients = ingredients;
        this.output = output;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public @NotNull String getGroup() {
        return group;
    }

    @Override
    public boolean matches(@NotNull RecipeWrapper container, @NotNull Level level) {
        var inputs = new ArrayList<ItemStack>();
        var inputSize = 0;

        for (int j = 0; j < INGREDIENT_SLOTS; ++j) {
            var stack = container.getItem(j);
            if (!stack.isEmpty()) {
                ++inputSize;
                inputs.add(stack);
            }
        }

        return inputSize == this.ingredients.size() && RecipeMatcher.findMatches(inputs, this.ingredients) != null;
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull RecipeWrapper pContainer, @NotNull RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= this.ingredients.size();
    }

    @Override
    public @NotNull ItemStack getResultItem(@Nullable RegistryAccess pRegistryAccess) {
        return output;
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return id;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.POT.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return ModRecipeTypes.POT.get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MillingRecipe recipe)) {
            return false;
        }
        return Objects.equals(
            getId(), recipe.getId()) && Objects.equals(getGroup(), recipe.getGroup())
            && Objects.equals(
            getIngredients(), recipe.getIngredients()) && Objects.equals(output,
            recipe.output);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getGroup(), getIngredients(), output);
    }

    public static class Serializer implements RecipeSerializer<MillingRecipe> {

        @Override
        public @NotNull MillingRecipe fromJson(@NotNull ResourceLocation id, @NotNull JsonObject json) {
            var group = GsonHelper.getAsString(json, "group", "");
            var ingredients = IngredientHelper.readIngredients(GsonHelper.getAsJsonArray(json, "ingredients"));

            if (ingredients.isEmpty()) {
                throw new JsonParseException("No ingredients for milling recipe");
            } else if (ingredients.size() > INGREDIENT_SLOTS) {
                throw new JsonParseException(
                    "Too many ingredients for milling recipe! The max is 1");
            }
            var output = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "result"), true);
            return new MillingRecipe(id, group, ingredients, output);
        }

        @Override
        public @Nullable MillingRecipe fromNetwork(@NotNull ResourceLocation id,
            @NotNull FriendlyByteBuf buffer) {
            var group = buffer.readUtf();
            var size = buffer.readVarInt();
            var ingredients = NonNullList.withSize(size, Ingredient.EMPTY);
            for (int i = 0; i < size; i++) {
                ingredients.set(i, Ingredient.fromNetwork(buffer));
            }

            var item = buffer.readItem();
            return new MillingRecipe(id, group, ingredients, item);
        }

        @Override
        public void toNetwork(@NotNull FriendlyByteBuf buffer, @NotNull MillingRecipe recipe) {
            buffer.writeUtf(recipe.getGroup());
            buffer.writeVarInt(recipe.getIngredients().size());
            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.toNetwork(buffer);
            }
            buffer.writeItem(recipe.output);
        }
    }
}
