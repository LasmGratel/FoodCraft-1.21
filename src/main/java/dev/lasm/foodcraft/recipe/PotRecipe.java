package dev.lasm.foodcraft.recipe;

import com.google.gson.JsonParseException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.lasm.foodcraft.init.ModRecipeSerializers;
import dev.lasm.foodcraft.init.ModRecipeTypes;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.ParametersAreNonnullByDefault;
import net.darkhax.bookshelf.common.api.data.codecs.map.MapCodecs;
import net.darkhax.bookshelf.common.api.data.codecs.stream.StreamCodecs;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.util.RecipeMatcher;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class PotRecipe implements Recipe<RecipeWrapper> {
    public static final int INGREDIENT_SLOTS = 8;
    public static final int STAPLE_SLOTS = 4;

    private final String group;
    private final ItemStack output;
    private final NonNullList<Ingredient> ingredients;
    private final NonNullList<Ingredient> staples;
    private final int minTime;
    private final int maxTime;

    public PotRecipe(String group, ItemStack output,
        List<Ingredient> ingredients, List<Ingredient> staples, int minTime, int maxTime) {
        this.group = group;
        this.output = output;

        if (ingredients.isEmpty()) {
            throw new JsonParseException("No ingredients for pot recipe");
        } else if (ingredients.size() > INGREDIENT_SLOTS) {
            throw new JsonParseException(
                "Too many ingredients for pot recipe! The max is 8");
        }

        if (staples.size() > STAPLE_SLOTS) {
            throw new JsonParseException(
                "Too many staples for pot recipe! The max is 4");
        }

        this.ingredients = NonNullList.copyOf(ingredients);
        this.staples = NonNullList.copyOf(staples);
        this.minTime = minTime;
        this.maxTime = maxTime;
    }

    @Override
    public String getGroup() {
        return group;
    }

    public int getMinTime() {
        return minTime;
    }

    public int getMaxTime() {
        return maxTime;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.POT.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipeTypes.POT.get();
    }

    @Override
    public boolean matches(RecipeWrapper container, Level level) {
        var inputs = new ArrayList<ItemStack>();
        var inputSize = 0;

        for (int j = 0; j < INGREDIENT_SLOTS; ++j) {
            var stack = container.getItem(j);
            if (!stack.isEmpty()) {
                ++inputSize;
                inputs.add(stack);
            }
        }

        var stapleInputs = new ArrayList<ItemStack>();
        var stapleSize = 0;

        for (int j = 0; j < STAPLE_SLOTS; ++j) {
            var stack = container.getItem(INGREDIENT_SLOTS + j);
            if (!stack.isEmpty()) {
                ++stapleSize;
                stapleInputs.add(stack);
            }
        }

        return ingredients.size() <= inputSize && RecipeMatcher.findMatches(inputs, ingredients) != null
            && staples.size() <= stapleSize && RecipeMatcher.findMatches(stapleInputs, staples) != null;
    }

    @Override
    public ItemStack assemble(RecipeWrapper RecipeWrapper,
        Provider provider) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= ingredients.size() + staples.size();
    }

    @Override
    public ItemStack getResultItem(Provider provider) {
        return output;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return ingredients;
    }

    public NonNullList<Ingredient> getStaples() {
        return staples;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PotRecipe potRecipe)) {
            return false;
        }
      return getMinTime() == potRecipe.getMinTime() && getMaxTime() == potRecipe.getMaxTime()
            && Objects.equals(getGroup(), potRecipe.getGroup())
            && Objects.equals(output, potRecipe.output)
            && Objects.equals(getIngredients(), potRecipe.getIngredients())
            && Objects.equals(getStaples(), potRecipe.getStaples());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGroup(), output, getIngredients(), getStaples(), getMinTime(),
            getMaxTime());
    }

    public static class Serializer implements RecipeSerializer<PotRecipe> {
        public static final MapCodec<PotRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(Codec.STRING.optionalFieldOf("group", "").forGetter(PotRecipe::getGroup),
                    MapCodecs.ITEM_STACK.get().fieldOf("result").forGetter((PotRecipe i) -> i.output),
                    MapCodecs.INGREDIENT_NONEMPTY.getList("ingredients", PotRecipe::getIngredients),
                    MapCodecs.INGREDIENT_NONEMPTY.getList("staples", PotRecipe::getStaples),
                    MapCodecs.INT.get("minTime", PotRecipe::getMinTime, 400),
                    MapCodecs.INT.get("maxTime", PotRecipe::getMaxTime, 1000)
                )
                .apply(instance, PotRecipe::new)
        );

        public static final StreamCodec<RegistryFriendlyByteBuf, PotRecipe> STREAM_CODEC = StreamCodec.of(
            PotRecipe.Serializer::toNetwork, PotRecipe.Serializer::fromNetwork);

        @Override
        public MapCodec<PotRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, PotRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static PotRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            var group = buffer.readUtf();
            var ingredients = StreamCodecs.list(StreamCodecs.INGREDIENT_NON_EMPTY).decode(buffer);
            var staples = StreamCodecs.list(StreamCodecs.INGREDIENT_NON_EMPTY).decode(buffer);

            var output = ItemStack.STREAM_CODEC.decode(buffer);
            var minTime = buffer.readVarInt();
            var maxTime = buffer.readVarInt();
            return new PotRecipe(group, output, ingredients, staples, minTime, maxTime);
        }


        public static void toNetwork(RegistryFriendlyByteBuf buffer, PotRecipe recipe) {
            buffer.writeUtf(recipe.getGroup());
            StreamCodecs.list(StreamCodecs.INGREDIENT_NON_EMPTY).encode(buffer, recipe.getIngredients());
            StreamCodecs.list(StreamCodecs.INGREDIENT_NON_EMPTY).encode(buffer, recipe.getStaples());
            ItemStack.STREAM_CODEC.encode(buffer, recipe.output);
            buffer.writeVarInt(recipe.getMinTime());
            buffer.writeVarInt(recipe.getMaxTime());
        }
    }
}
