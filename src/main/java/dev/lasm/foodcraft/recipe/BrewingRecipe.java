package dev.lasm.foodcraft.recipe;

import com.google.gson.JsonParseException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.lasm.foodcraft.api.FluidAttachedRecipeInput;
import dev.lasm.foodcraft.init.ModRecipeSerializers;
import dev.lasm.foodcraft.init.ModRecipeTypes;
import dev.lasm.foodcraft.util.FluidHelper;
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
import net.neoforged.neoforge.common.util.RecipeMatcher;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.Nullable;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class BrewingRecipe implements Recipe<FluidAttachedRecipeInput> {
    public static final int INGREDIENT_SLOTS = 3;

    private final String group;
    private final ItemStack output;
    private final NonNullList<Ingredient> ingredients;
    private final FluidStack fluidInput;

    public BrewingRecipe(String group, ItemStack output,
        List<Ingredient> ingredients, @Nullable FluidStack fluidInput) {
        this.group = group;
        this.output = output;

        if (ingredients.isEmpty()) {
            throw new JsonParseException("No ingredients for brewing recipe");
        } else if (ingredients.size() > INGREDIENT_SLOTS) {
            throw new JsonParseException(
                "Too many ingredients for brewing recipe! The max is 3");
        }

        this.ingredients = NonNullList.copyOf(ingredients);
        this.fluidInput = fluidInput;
    }

    @Override
    public String getGroup() {
        return group;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.BREWING.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipeTypes.BREWING.get();
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

        return ingredients.size() == inputSize && RecipeMatcher.findMatches(inputs, ingredients) != null &&
            container.drain(fluidInput, IFluidHandler.FluidAction.SIMULATE).equals(fluidInput);
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

    public FluidStack getFluidInput() {
        return fluidInput;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BrewingRecipe that)) {
            return false;
        }
        return Objects.equals(getGroup(), that.getGroup()) && Objects.equals(output,
            that.output) && Objects.equals(getIngredients(), that.getIngredients())
            && Objects.equals(getFluidInput(), that.getFluidInput());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGroup(), output, getIngredients(), getFluidInput());
    }

    public static class Serializer implements RecipeSerializer<BrewingRecipe> {
        public static final MapCodec<BrewingRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(Codec.STRING.optionalFieldOf("group", "").forGetter(BrewingRecipe::getGroup),
                    MapCodecs.ITEM_STACK.get().fieldOf("result").forGetter((BrewingRecipe i) -> i.output),
                    MapCodecs.INGREDIENT_NONEMPTY.getList("ingredients", BrewingRecipe::getIngredients),
                    FluidHelper.FLUID_STACK.get("fluidInput", BrewingRecipe::getFluidInput)
                )
                .apply(instance, BrewingRecipe::new)
        );

        public static final StreamCodec<RegistryFriendlyByteBuf, BrewingRecipe> STREAM_CODEC = StreamCodec.of(
            Serializer::toNetwork, Serializer::fromNetwork);

        @Override
        public MapCodec<BrewingRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, BrewingRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static BrewingRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            var group = buffer.readUtf();
            var size = buffer.readVarInt();
            var ingredients = NonNullList.withSize(size, Ingredient.EMPTY);
            for (int i = 0; i < size; i++) {

                ingredients.set(i, Ingredient.CONTENTS_STREAM_CODEC.decode(buffer));
            }

            var fluidInput = FluidStack.STREAM_CODEC.decode(buffer);
            var output = ItemStack.STREAM_CODEC.decode(buffer);
            return new BrewingRecipe(group, output, ingredients, fluidInput);
        }


        public static void toNetwork(RegistryFriendlyByteBuf buffer, BrewingRecipe recipe) {
            buffer.writeUtf(recipe.getGroup());
            buffer.writeVarInt(recipe.getIngredients().size());
            for (Ingredient ingredient : recipe.getIngredients()) {
                Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, ingredient);
            }
            FluidStack.STREAM_CODEC.encode(buffer, recipe.getFluidInput());
            ItemStack.STREAM_CODEC.encode(buffer, recipe.output);
        }


    }
}

