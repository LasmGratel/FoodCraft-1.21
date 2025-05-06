package dev.lasm.foodcraft.client.screen;

import dev.lasm.foodcraft.FoodCraft;
import dev.lasm.foodcraft.container.FryingPanMenu;
import dev.lasm.foodcraft.util.GuiHelpersNeoForge;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.fluids.FluidStack;

public class FryingPanScreen extends BaseContainerScreen<FryingPanMenu> {

    private static final ResourceLocation BACKGROUND_TEXTURE = ResourceLocation.fromNamespaceAndPath(
        FoodCraft.MOD_ID, "textures/gui/container/frying_pan.png");

    public FryingPanScreen(FryingPanMenu menu,
        Inventory playerInventory, Component title) {
        super(menu, playerInventory, title, BACKGROUND_TEXTURE);
    }

    @Override
    protected void renderTooltip(GuiGraphics guiGraphics, int x, int y) {
        super.renderTooltip(guiGraphics, x, y);
        GuiHelpersNeoForge.renderFluidTank(guiGraphics, new FluidStack(Fluids.WATER, 1000), 4000, 18 + getGuiLeft(), 14 + getGuiTop(), 11, 59);
    }
}
