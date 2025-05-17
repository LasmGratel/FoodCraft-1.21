package dev.lasm.foodcraft.client.screen;

import dev.lasm.foodcraft.FoodCraft;
import dev.lasm.foodcraft.container.MenuDataAccess;
import dev.lasm.foodcraft.container.PressureCookerMenu;
import dev.lasm.foodcraft.container.StoveMenu;
import dev.lasm.foodcraft.util.GuiHelpersNeoForge;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;

public class StoveScreen extends BaseContainerScreen<StoveMenu> {

    private static final ResourceLocation BACKGROUND_TEXTURE = ResourceLocation.fromNamespaceAndPath(
        FoodCraft.MOD_ID, "textures/gui/container/stove.png");

    public static final ResourceLocation STOVE_SPRITE = ResourceLocation.fromNamespaceAndPath(FoodCraft.MOD_ID, "container/stove_lit");

    public StoveScreen(StoveMenu menu,
        Inventory playerInventory, Component title) {
        super(menu, playerInventory, title, BACKGROUND_TEXTURE);
    }

    @Override
    protected void renderTooltip(GuiGraphics guiGraphics, int x, int y) {
        renderLit(guiGraphics, 80, 35);

        if (menu instanceof MenuDataAccess menu && menu.getContainerData().get(1) > 0)
            guiGraphics.blitSprite(STOVE_SPRITE, 9, 9, 0, 0, 83 + getGuiLeft(), 22 + getGuiTop(), 9, 9);

        super.renderTooltip(guiGraphics, x, y);
    }
}
