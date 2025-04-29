package dev.lasm.foodcraft.recipe;

import com.google.gson.JsonParseException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.lasm.foodcraft.api.FluidAttachedRecipeInput;
import dev.lasm.foodcraft.init.ModRecipeSerializers;
import dev.lasm.foodcraft.init.ModRecipeTypes;
import java.util.ArrayList;
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
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class MillingRecipe implements Recipe<FluidAttachedRecipeInput> {
    public static final int INGREDIENT_SLOTS = 1;

    private final String group;
    private final ItemStack output;
    private final NonNullList<Ingredient> ingredients;

    public MillingRecipe(String group, ItemStack output,
        List<Ingredient> ingredients) {
        this.group = group;
        this.output = output;

        if (ingredients.isEmpty()) {
            throw new JsonParseException("No ingredients for milling recipe");
        } else if (ingredients.size() > INGREDIENT_SLOTS) {
            throw new JsonParseException(
                "Too many ingredients for milling recipe! The max is 3");
        }

        this.ingredients = NonNullList.copyOf(ingredients);
    }

    @Override
    public String getGroup() {
        return group;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.MILLING.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipeTypes.MILLING.get();
    }

    @Override
    public boolean matches(FluidAttachedRecipeInput container, Level level) {
        var inputs = new ArrayList<ItemStack>();
        var inputSize = 0;

        for (int j = 0; j < INGREDIENT_SLOTS; ++j) {
            var stack = container.getItem(j);
            if (!stack.isEmpty()) {
                ++inputSize;
                inputs.add(stack);
            }
        }

        return ingredients.size() == inputSize && net.neoforged.neoforge.common.util.RecipeMatcher.findMatches(inputs, ingredients) != null;
    }

    @Override
    public ItemStack assemble(FluidAttachedRecipeInput fluidAttachedRecipeInput,
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
        if (!(o instanceof MillingRecipe that)) {
            return false;
        }
        return Objects.equals(getGroup(), that.getGroup()) && Objects.equals(output,
            that.output) && Objects.equals(getIngredients(), that.getIngredients());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGroup(), output, getIngredients());
    }

    public static class Serializer implements RecipeSerializer<MillingRecipe> {
        public static final MapCodec<MillingRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(Codec.STRING.optionalFieldOf("group", "").forGetter(MillingRecipe::getGroup),
                    MapCodecs.ITEM_STACK.get().fieldOf("result").forGetter((MillingRecipe i) -> i.output),
                    MapCodecs.INGREDIENT_NONEMPTY.getList("ingredients", MillingRecipe::getIngredients)
                )
                .apply(instance, MillingRecipe::new)
        );

        public static final StreamCodec<RegistryFriendlyByteBuf, MillingRecipe> STREAM_CODEC = StreamCodec.of(
            Serializer::toNetwork, Serializer::fromNetwork);

        @Override
        public MapCodec<MillingRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, MillingRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static MillingRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            var group = buffer.readUtf();
            var size = buffer.readVarInt();
            var ingredients = NonNullList.withSize(size, Ingredient.EMPTY);
            for (int i = 0; i < size; i++) {

                ingredients.set(i, Ingredient.CONTENTS_STREAM_CODEC.decode(buffer));
            }

            var output = ItemStack.STREAM_CODEC.decode(buffer);
            return new MillingRecipe(group, output, ingredients);
        }


        public static void toNetwork(RegistryFriendlyByteBuf buffer, MillingRecipe recipe) {
            buffer.writeUtf(recipe.getGroup());
            buffer.writeVarInt(recipe.getIngredients().size());
            for (Ingredient ingredient : recipe.getIngredients()) {
                Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, ingredient);
            }
            ItemStack.STREAM_CODEC.encode(buffer, recipe.output);
        }


    }
}