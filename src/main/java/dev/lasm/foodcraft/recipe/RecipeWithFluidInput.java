package dev.lasm.foodcraft.recipe;

import dev.lasm.foodcraft.api.FluidAttachedRecipeInput;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.util.RecipeMatcher;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler.FluidAction;
import org.jetbrains.annotations.Nullable;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public abstract class RecipeWithFluidInput<T extends RecipeWithFluidInput<T>> implements Recipe<FluidAttachedRecipeInput> {
  private final String group;
  private final ItemStack output;
  private final NonNullList<Ingredient> ingredients;
  private final FluidStack fluidInput;

  public RecipeWithFluidInput(String group, ItemStack output,
      List<Ingredient> ingredients, @Nullable FluidStack fluidInput) {
    this.group = group;
    this.output = output;
    this.ingredients = NonNullList.copyOf(ingredients);
    this.fluidInput = fluidInput;
  }

  @Override
  public String getGroup() {
    return group;
  }

  @Override
  public boolean matches(FluidAttachedRecipeInput container, Level level) {
    var inputs = new ArrayList<ItemStack>();
    var inputSize = 0;

    for (int j = 0; j < container.size(); ++j) {
      var stack = container.getItem(j);
      if (!stack.isEmpty()) {
        ++inputSize;
        inputs.add(stack);
      }
    }

    return ingredients.size() == inputSize && RecipeMatcher.findMatches(inputs, ingredients) != null &&
        container.drain(fluidInput, FluidAction.SIMULATE).equals(fluidInput);
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
  public abstract boolean equals(Object o);

  @Override
  public abstract int hashCode();
/*
  This is here for coping
  public static class Serializer<T extends RecipeWithFluidInput<T>> implements RecipeSerializer<RecipeWithFluidInput<T>> {
    public static final MapCodec<RecipeWithFluidInput> CODEC = RecordCodecBuilder.mapCodec(instance ->
        instance.group(Codec.STRING.optionalFieldOf("group", "").forGetter(RecipeWithFluidInput::getGroup),
                MapCodecs.ITEM_STACK.get().fieldOf("result").forGetter((RecipeWithFluidInput i) -> i.output),
                MapCodecs.INGREDIENT_NONEMPTY.getList("ingredients", RecipeWithFluidInput::getIngredients),
                FluidHelper.FLUID_STACK.get("fluidInput", RecipeWithFluidInput::getFluidInput),
            )
            .apply(instance, RecipeWithFluidInput::new)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, RecipeWithFluidInput> STREAM_CODEC = StreamCodec.of(
        Serializer::toNetwork, Serializer::fromNetwork);

    @Override
    public MapCodec<RecipeWithFluidInput> codec() {
      return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, RecipeWithFluidInput> streamCodec() {
      return STREAM_CODEC;
    }

    private static RecipeWithFluidInput fromNetwork(RegistryFriendlyByteBuf buffer) {
      var group = buffer.readUtf();
      var size = buffer.readVarInt();
      var ingredients = NonNullList.withSize(size, Ingredient.EMPTY);
      for (int i = 0; i < size; i++) {

        ingredients.set(i, Ingredient.CONTENTS_STREAM_CODEC.decode(buffer));
      }

      var fluidInput = FluidStack.STREAM_CODEC.decode(buffer);
      var output = ItemStack.STREAM_CODEC.decode(buffer);
      var cool = buffer.readBoolean();
      return new RecipeWithFluidInput(group, output, ingredients, fluidInput, cool);
    }


    public static void toNetwork(RegistryFriendlyByteBuf buffer, RecipeWithFluidInput recipe) {
      buffer.writeUtf(recipe.getGroup());
      buffer.writeVarInt(recipe.getIngredients().size());
      for (Ingredient ingredient : recipe.getIngredients()) {
        Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, ingredient);
      }
      FluidStack.STREAM_CODEC.encode(buffer, recipe.getFluidInput());
      ItemStack.STREAM_CODEC.encode(buffer, recipe.output);
      buffer.writeBoolean(recipe.cool);
    }


  }

 */
}
