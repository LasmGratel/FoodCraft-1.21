package dev.lasm.foodcraft.client.screen;

import dev.lasm.foodcraft.FoodCraft;
import dev.lasm.foodcraft.container.MillMenu;
import dev.lasm.foodcraft.util.GuiHelpersNeoForge;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;

public class MillScreen extends BaseContainerScreen<MillMenu> {

    private static final ResourceLocation BACKGROUND_TEXTURE = ResourceLocation.fromNamespaceAndPath(
        FoodCraft.MOD_ID, "textures/gui/container/mill.png");

    private static final ResourceLocation BURN_PROGRESS_SPRITE = ResourceLocation.fromNamespaceAndPath(FoodCraft.MOD_ID, "container/mill_progress");

    public MillScreen(MillMenu menu,
        Inventory playerInventory, Component title) {
        super(menu, playerInventory, title, BACKGROUND_TEXTURE);
    }

    @Override
    protected void renderTooltip(GuiGraphics guiGraphics, int x, int y) {
        renderLit(guiGraphics, 80, 35);
        renderBurn(guiGraphics, BURN_PROGRESS_SPRITE, 76, 20);

        super.renderTooltip(guiGraphics, x, y);
    }
}
