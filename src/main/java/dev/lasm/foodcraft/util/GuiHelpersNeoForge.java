package dev.lasm.foodcraft.util;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.function.Function;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidStack;
import org.apache.commons.lang3.tuple.Triple;
import org.lwjgl.opengl.GL11;

/**
 * @author rubensworks
 */
public class GuiHelpersNeoForge {
    /**
     * The default item slot size. Width and height are equal.
     */
    public static int SLOT_SIZE = 18;
    /**
     * The default inner item slot size. Width and height are equal.
     */
    public static int SLOT_SIZE_INNER = 16;

    public static TextureAtlasSprite getFluidIcon(Fluid fluid, Direction side) {
        return getFluidIcon(new FluidStack(fluid, 1000), side);
    }

    private static final Function<ResourceLocation, TextureAtlasSprite> TEXTURE_GETTER =
        location -> Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(location);

    public static Function<ResourceLocation, TextureAtlasSprite> getBlockTextureGetter() {
        return TEXTURE_GETTER;
    }

    public static TextureAtlasSprite getFluidIcon(FluidStack fluid, Direction side) {
        if(side == null) side = Direction.UP;

        var renderProperties = IClientFluidTypeExtensions.of(fluid.getFluid());
        var icon = getBlockTextureGetter().apply(renderProperties.getFlowingTexture(fluid));
        if(icon == null || (side == Direction.UP || side == Direction.DOWN)) {
            icon = getBlockTextureGetter().apply(renderProperties.getStillTexture(fluid));
        }

        return icon;
    }

    /**
     * Convert a color in integer representation to seperated r, g and b colors.
     * @param color The color in integer representation.
     * @return The separated r, g and b colors.
     */
    public static Triple<Float, Float, Float> intToRGB(int color) {
        float red, green, blue;
        red = (float)(color >> 16 & 255) / 255.0F;
        green = (float)(color >> 8 & 255) / 255.0F;
        blue = (float)(color & 255) / 255.0F;
        //this.alpha = (float)(color >> 24 & 255) / 255.0F;
        return Triple.of(red, green, blue);
    }

    public static void renderFluidTank(GuiGraphics gui, @Nullable FluidStack fluidStack, int capacity,
        int x, int y, int width, int height) {
        if (fluidStack != null && !fluidStack.isEmpty() && capacity > 0) {
            gui.pose().pushPose();
            GlStateManager._enableBlend();
            GlStateManager._blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            Lighting.setupFor3DItems();
            GL11.glEnable(GL11.GL_DEPTH_TEST);

            var level = (int) (height * (((double) fluidStack.getAmount()) / capacity));
            var icon = getFluidIcon(fluidStack, Direction.UP);
            var verticalOffset = 0;
            while(level > 0) {
                int textureHeight;
                if(level > 16) {
                    textureHeight = 16;
                    level -= 16;
                } else {
                    textureHeight = level;
                    level = 0;
                }

                // Fluids can have a custom overlay color, use this to render.
                var renderProperties = IClientFluidTypeExtensions.of(fluidStack.getFluid().getFluidType());
                var colorParts = intToRGB(renderProperties.getTintColor(fluidStack));
                // Override water color, otherwise it's gray, since it depends on world biome.
                if (fluidStack.getFluid() == Fluids.WATER || fluidStack.getFluid() == Fluids.FLOWING_WATER) {
                    colorParts = Triple.of(0F, 0.335F, 1F);
                }

                Lighting.setupForFlatItems();
                RenderSystem.setShaderColor(colorParts.getLeft(), colorParts.getMiddle(), colorParts.getRight(), 1);
                gui.blit(x, y - textureHeight - verticalOffset + height, 0, width, textureHeight, icon);
                Lighting.setupFor3DItems();
                RenderSystem.setShaderColor(1, 1, 1, 1);

                verticalOffset = verticalOffset + 16;
            }

            var textureManager = Minecraft.getInstance().getTextureManager();
            textureManager.bindForSetup(InventoryMenu.BLOCK_ATLAS);
            textureManager.getTexture(InventoryMenu.BLOCK_ATLAS).restoreLastBlurMipmap();

            Lighting.setupForFlatItems();
            gui.pose().popPose();
            GL11.glDisable(GL11.GL_DEPTH_TEST);
        }
    }

    public static void renderFluidSlot(GuiGraphics gui, @Nullable FluidStack fluidStack, int x, int y) {
        if (fluidStack != null) {
            renderFluidTank(gui, fluidStack, fluidStack.getAmount(), x, y, SLOT_SIZE_INNER, SLOT_SIZE_INNER);
        }
    }

    public static void renderOverlayedFluidTank(GuiGraphics gui, @Nullable FluidStack fluidStack, int capacity,
        int x, int y, int width, int height,
        ResourceLocation textureOverlay, int overlayTextureX, int overlayTextureY) {
        renderFluidTank(gui, fluidStack, capacity, x, y, width, height);
        if (fluidStack != null && capacity > 0) {
            GlStateManager._enableBlend();
            gui.blit(textureOverlay, x, y, overlayTextureX, overlayTextureY, width, height);
        }
    }

    public static void renderItemCooldown(GuiGraphics gui, int x, int y, float percentage) {
        if (percentage > 0.0F) {
            int i1 = y + Mth.floor(16.0F * (1.0F - percentage));
            int j1 = i1 + Mth.ceil(16.0F * percentage);
            gui.fill(RenderType.guiOverlay(), x, i1, x + 16, j1, Integer.MAX_VALUE);
        }
    }

}