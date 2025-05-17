package dev.lasm.foodcraft.client.screen;

import dev.lasm.foodcraft.FoodCraft;
import dev.lasm.foodcraft.container.PressureCookerMenu;
import dev.lasm.foodcraft.util.GuiHelpersNeoForge;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;

public class PressureCookerScreen extends BaseContainerScreen<PressureCookerMenu> implements IFluidStackScreen {

    private static final ResourceLocation BACKGROUND_TEXTURE = ResourceLocation.fromNamespaceAndPath(
        FoodCraft.MOD_ID, "textures/gui/container/pressure_cooker.png");

    private final FluidTank fluidTank;

    public PressureCookerScreen(PressureCookerMenu menu,
        Inventory playerInventory, Component title) {
        super(menu, playerInventory, title, BACKGROUND_TEXTURE);
        this.fluidTank = menu.fluidTank;
    }

    @Override
    protected void renderTooltip(GuiGraphics guiGraphics, int x, int y) {
        if (fluidTank != null)
            GuiHelpersNeoForge.renderFluidTank(guiGraphics, fluidTank.getFluid(), menu.fluidTank.getCapacity(), 18 + getGuiLeft(), 19 + getGuiTop(), 11, 46);
        super.renderTooltip(guiGraphics, x, y);

        renderLit(guiGraphics, 121, 61);
        renderBurn(guiGraphics, 92, 30);
    }

    @Override
    public void setFluid(FluidStack fluidStack) {
        if (fluidTank != null)
            fluidTank.setFluid(fluidStack);
    }
}
