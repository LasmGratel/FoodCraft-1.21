package dev.lasm.foodcraft.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class FluidHelper {
    public static boolean canExtractFluidExact() {
        return false;
    }

    public static FluidStack getFluidStack(@Nullable JsonObject json) {
        if (json == null) return FluidStack.EMPTY;

        var fluidName = new ResourceLocation(GsonHelper.getAsString(json, "fluid"));
        var fluid = ForgeRegistries.FLUIDS.getValue(fluidName);
        if (fluid == null) {
            throw new JsonParseException("Could not find fluid " + fluidName);
        }

        var amount = GsonHelper.getAsInt(json, "amount");

        if (json.has("tag")) {
            var tag = CraftingHelper.getNBT(json.get("tag"));
            return new FluidStack(fluid, amount, tag);
        } else {
            return new FluidStack(fluid, amount);
        }
    }
}
