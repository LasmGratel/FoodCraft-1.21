package dev.lasm.foodcraft.recipe;

import com.google.gson.JsonParseException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.lasm.foodcraft.init.ModRecipeSerializers;
import dev.lasm.foodcraft.init.ModRecipeTypes;
import java.util.BitSet;
import java.util.List;
import java.util.Objects;
import javax.annotation.ParametersAreNonnullByDefault;
import net.darkhax.bookshelf.common.api.data.codecs.map.MapCodecs;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class ChoppingRecipe implements Recipe<RecipeInput> {
    public static final int INGREDIENT_SLOTS = 3;

    private final String group;
    private final ItemStack output;
    private final NonNullList<Ingredient> ingredients;

    public ChoppingRecipe(String group, ItemStack output,
        List<Ingredient> ingredients) {
        this.group = group;
        this.output = output;

        if (ingredients.isEmpty()) {
            throw new JsonParseException("No ingredients for chopping recipe");
        } else if (ingredients.size() > INGREDIENT_SLOTS) {
            throw new JsonParseException(
                "Too many ingredients for chopping recipe! The max is 3");
        }

        this.ingredients = NonNullList.copyOf(ingredients);
    }

    @Override
    public String getGroup() {
        return group;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.CHOPPING.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipeTypes.CHOPPING.get();
    }

    @Override
    public boolean matches(RecipeInput container, Level level) {
        for (int j = 0; j < ingredients.size(); ++j) {
            var stack = container.getItem(j);
            if (!ingredients.get(j).test(stack) && !(stack.isEmpty() && ingredients.get(j).isEmpty())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack assemble(RecipeInput fluidAttachedRecipeInput,
        Provider provider) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= ingredients.size();
    }

    @Override
    public ItemStack getResultItem(Provider provider) {
        return output;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChoppingRecipe that)) {
            return false;
        }
        return Objects.equals(getGroup(), that.getGroup()) && Objects.equals(output,
            that.output) && Objects.equals(getIngredients(), that.getIngredients());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGroup(), output, getIngredients());
    }

    public static class Serializer implements RecipeSerializer<ChoppingRecipe> {
        public static final MapCodec<ChoppingRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(Codec.STRING.optionalFieldOf("group", "").forGetter(ChoppingRecipe::getGroup),
                    MapCodecs.ITEM_STACK.get().fieldOf("result").forGetter((ChoppingRecipe i) -> i.output),
                    MapCodecs.INGREDIENT.getList("ingredients", ChoppingRecipe::getIngredients)
                )
                .apply(instance, ChoppingRecipe::new)
        );

        public static final StreamCodec<RegistryFriendlyByteBuf, ChoppingRecipe> STREAM_CODEC = StreamCodec.of(
            Serializer::toNetwork, Serializer::fromNetwork);

        @Override
        public MapCodec<ChoppingRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, ChoppingRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static ChoppingRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            var group = buffer.readUtf();
            var bitset = BitSet.valueOf(buffer.readByteArray());
            var ingredients = NonNullList.withSize(INGREDIENT_SLOTS, Ingredient.EMPTY);
            for (int i = 0; i < INGREDIENT_SLOTS; i++) {
                if (!bitset.get(i))
                    ingredients.set(i, Ingredient.CONTENTS_STREAM_CODEC.decode(buffer));
            }

            var output = ItemStack.STREAM_CODEC.decode(buffer);
            return new ChoppingRecipe(group, output, ingredients);
        }


        public static void toNetwork(RegistryFriendlyByteBuf buffer, ChoppingRecipe recipe) {
            buffer.writeUtf(recipe.getGroup());
            var bitset = new BitSet(INGREDIENT_SLOTS);
            for (int i = 0; i < INGREDIENT_SLOTS; i++) {
                if (recipe.getIngredients().get(i).isEmpty()) {
                    bitset.set(i);
                }
            }
            buffer.writeByteArray(bitset.toByteArray());
            for (Ingredient ingredient : recipe.getIngredients()) {
                if (!ingredient.isEmpty()) {
                    Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, ingredient);
                }
            }
            ItemStack.STREAM_CODEC.encode(buffer, recipe.output);
        }


    }
}
