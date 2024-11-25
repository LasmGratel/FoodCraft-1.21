package dev.lasm.foodcraft.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import dev.lasm.foodcraft.init.ModRecipeSerializers;
import dev.lasm.foodcraft.init.ModRecipeTypes;
import dev.lasm.foodcraft.util.IngredientHelper;
import java.util.ArrayList;
import java.util.List;
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

public class PotRecipe implements Recipe<RecipeWrapper> {
    public static final int INGREDIENT_SLOTS = 8;
    public static final int STAPLE_SLOTS = 4;

    private final ResourceLocation id;
    private final String group;
    private final NonNullList<Ingredient> staples;
    private final NonNullList<Ingredient> ingredients;
    private final ItemStack output;
    private final int minTime;
    private final int maxTime;

    public PotRecipe(ResourceLocation id, String group, NonNullList<Ingredient> staples,
        NonNullList<Ingredient> ingredients, ItemStack output, int minTime, int maxTime) {
        this.id = id;
        this.group = group;
        this.staples = staples;
        this.ingredients = ingredients;
        this.output = output;
        this.minTime = minTime;
        this.maxTime = maxTime;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return ingredients;
    }

    public NonNullList<Ingredient> getStaples() {
        return staples;
    }

    public int getMinTime() {
        return minTime;
    }

    public int getMaxTime() {
        return maxTime;
    }

    @Override
    public @NotNull String getGroup() {
        return group;
    }

    @Override
    public boolean matches(@NotNull RecipeWrapper container, @NotNull Level level) {
        var inputs = new ArrayList<ItemStack>();
        var inputSize = 0;
        var staples = new ArrayList<ItemStack>();
        var stapleSize = 0;

        for (int j = 0; j < INGREDIENT_SLOTS; ++j) {
            var stack = container.getItem(j);
            if (!stack.isEmpty()) {
                ++inputSize;
                inputs.add(stack);
            }
        }

        for (int j = 0; j < STAPLE_SLOTS; ++j) {
            var stack = container.getItem(INGREDIENT_SLOTS + j);
            if (!stack.isEmpty()) {
                ++stapleSize;
                staples.add(stack);
            }
        }

        return inputSize == this.ingredients.size() && RecipeMatcher.findMatches(inputs, this.ingredients) != null
            && stapleSize == this.staples.size() && RecipeMatcher.findMatches(staples, this.staples) != null;
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull RecipeWrapper pContainer, @NotNull RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= this.ingredients.size() + this.staples.size();
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
        if (!(o instanceof PotRecipe potRecipe)) {
            return false;
        }
        return minTime == potRecipe.minTime && maxTime == potRecipe.maxTime && Objects.equals(
            getId(), potRecipe.getId()) && Objects.equals(getGroup(), potRecipe.getGroup())
            && Objects.equals(staples, potRecipe.staples) && Objects.equals(
            getIngredients(), potRecipe.getIngredients()) && Objects.equals(output,
            potRecipe.output);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getGroup(), staples, getIngredients(), output, minTime,
            maxTime);
    }

    public static class Serializer implements RecipeSerializer<PotRecipe> {

        @Override
        public @NotNull PotRecipe fromJson(@NotNull ResourceLocation id, @NotNull JsonObject json) {
            var group = GsonHelper.getAsString(json, "group", "");
            var ingredients = IngredientHelper.readIngredients(GsonHelper.getAsJsonArray(json, "ingredients"));
            var staples = IngredientHelper.readIngredients(GsonHelper.getAsJsonArray(json, "staples"));

            if (ingredients.isEmpty()) {
                throw new JsonParseException("No ingredients for pot recipe");
            } else if (ingredients.size() > INGREDIENT_SLOTS) {
                throw new JsonParseException(
                    "Too many ingredients for pot recipe! The max is 8");
            } else if (staples.size() > STAPLE_SLOTS) {
                throw new JsonParseException(
                    "Too many staples for pot recipe! The max is 4");
            }
            var output = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "result"), true);
            var minTime = GsonHelper.getAsInt(json, "minTime", 400);
            var maxTime = GsonHelper.getAsInt(json, "minTime", 1000);
            return new PotRecipe(id, group, staples, ingredients, output, minTime, maxTime);
        }

        @Override
        public @Nullable PotRecipe fromNetwork(@NotNull ResourceLocation id,
            @NotNull FriendlyByteBuf buffer) {
            var group = buffer.readUtf();
            var size = buffer.readVarInt();
            var ingredients = NonNullList.withSize(size, Ingredient.EMPTY);
            for (int i = 0; i < size; i++) {
                ingredients.set(i, Ingredient.fromNetwork(buffer));
            }
            size = buffer.readVarInt();
            var staples = NonNullList.withSize(size, Ingredient.EMPTY);
            for (int i = 0; i < size; i++) {
                staples.set(i, Ingredient.fromNetwork(buffer));
            }

            var item = buffer.readItem();
            var minTime = buffer.readVarInt();
            var maxTime = buffer.readVarInt();
            return new PotRecipe(id, group, ingredients, staples, item, minTime, maxTime);
        }

        @Override
        public void toNetwork(@NotNull FriendlyByteBuf buffer, @NotNull PotRecipe recipe) {
            buffer.writeUtf(recipe.getGroup());
            buffer.writeVarInt(recipe.getIngredients().size());
            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.toNetwork(buffer);
            }
            buffer.writeVarInt(recipe.getStaples().size());
            for (Ingredient ingredient : recipe.getStaples()) {
                ingredient.toNetwork(buffer);
            }
            buffer.writeItem(recipe.output);
            buffer.writeVarInt(recipe.getMinTime());
            buffer.writeVarInt(recipe.getMaxTime());
        }
    }
}
