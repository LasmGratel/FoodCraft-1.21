package dev.lasm.foodcraft.compat.jei;

import dev.lasm.foodcraft.recipe.PanRecipe;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PanRecipeCategory implements IRecipeCategory<PanRecipe> {

    @Override
    public @NotNull RecipeType<PanRecipe> getRecipeType() {
        return null;
    }

    @Override
    public @NotNull Component getTitle() {
        return null;
    }

    @Override
    public @NotNull IDrawable getBackground() {
        return null;
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return null;

    }

    @Override
    public void setRecipe(@NotNull IRecipeLayoutBuilder iRecipeLayoutBuilder, @NotNull PanRecipe panRecipe,
        @NotNull IFocusGroup iFocusGroup) {

    }
}
