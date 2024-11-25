package dev.lasm.foodcraft.recipe;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import dev.lasm.foodcraft.api.FluidAttachedContainer;
import dev.lasm.foodcraft.init.ModRecipeSerializers;
import dev.lasm.foodcraft.init.ModRecipeTypes;
import dev.lasm.foodcraft.util.FluidHelper;
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
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BeverageMakingRecipe implements Recipe<FluidAttachedContainer> {
    public static final int INGREDIENT_SLOTS = 1;

    private final ResourceLocation id;
    private final String group;
    private final ItemStack output;
    private final NonNullList<Ingredient> ingredients;
    private final FluidStack fluidInput;
    private final boolean cool;

    public BeverageMakingRecipe(ResourceLocation id, String group, ItemStack output,
        NonNullList<Ingredient> ingredients, @Nullable FluidStack fluidInput, boolean cool) {
        this.id = id;
        this.group = group;
        this.output = output;
        this.ingredients = ingredients;
        this.fluidInput = fluidInput;
        this.cool = cool;
    }

    @Override
    public String getGroup() {
        return group;
    }

    @Override
    public ResourceLocation getId() {
        return id;
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
    public boolean matches(FluidAttachedContainer container, Level level) {
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
            container.drain(fluidInput, FluidAction.SIMULATE).equals(fluidInput);
    }

    @Override
    public ItemStack assemble(FluidAttachedContainer container, RegistryAccess registryAccess) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= ingredients.size();
    }

    @Override
    public ItemStack getResultItem(@Nullable RegistryAccess pRegistryAccess) {
        return output;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return ingredients;
    }

    public FluidStack getFluidInput() {
        return fluidInput;
    }

    public boolean isCool() {
        return cool;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BeverageMakingRecipe that)) {
            return false;
        }
        return cool == that.cool && Objects.equals(getId(), that.getId())
            && Objects.equals(getGroup(), that.getGroup()) && Objects.equals(output,
            that.output) && Objects.equals(getIngredients(), that.getIngredients())
            && Objects.equals(getFluidInput(), that.getFluidInput());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getGroup(), output, getIngredients(), getFluidInput(), cool);
    }

    public static class Serializer implements RecipeSerializer<BeverageMakingRecipe> {

        @Override
        public @NotNull BeverageMakingRecipe fromJson(ResourceLocation id, JsonObject json) {
            var group = GsonHelper.getAsString(json, "group", "");
            var ingredients = IngredientHelper.readIngredients(GsonHelper.getAsJsonArray(json, "ingredients"));

            if (ingredients.isEmpty()) {
                throw new JsonParseException("No ingredients for brewing recipe");
            } else if (ingredients.size() > INGREDIENT_SLOTS) {
                throw new JsonParseException(
                    "Too many ingredients for brewing recipe! The max is 3");
            }

            var fluidInput = FluidHelper.getFluidStack(json.getAsJsonObject("fluidInput"));
            var output = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "result"), true);
            var cool = GsonHelper.getAsBoolean(json, "cool", false);

            return new BeverageMakingRecipe(id, group, output, ingredients, fluidInput, cool);
        }

        @Override
        public @Nullable BeverageMakingRecipe fromNetwork(ResourceLocation id,
            FriendlyByteBuf buffer) {
            var group = buffer.readUtf();
            var size = buffer.readVarInt();
            var ingredients = NonNullList.withSize(size, Ingredient.EMPTY);
            for (int i = 0; i < size; i++) {
                ingredients.set(i, Ingredient.fromNetwork(buffer));
            }

            var fluidInput = buffer.readFluidStack();
            var output = buffer.readItem();
            var cool = buffer.readBoolean();
            return new BeverageMakingRecipe(id, group, output, ingredients, fluidInput, cool);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, BeverageMakingRecipe recipe) {
            buffer.writeUtf(recipe.getGroup());
            buffer.writeVarInt(recipe.getIngredients().size());
            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.toNetwork(buffer);
            }
            buffer.writeFluidStack(recipe.getFluidInput());
            buffer.writeItem(recipe.output);
            buffer.writeBoolean(recipe.cool);
        }
    }
}
