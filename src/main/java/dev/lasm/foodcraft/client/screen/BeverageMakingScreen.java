package dev.lasm.foodcraft.client.screen;

import dev.lasm.foodcraft.FoodCraft;
import dev.lasm.foodcraft.container.BeverageMakingMenu;
import dev.lasm.foodcraft.container.MenuDataAccess;
import dev.lasm.foodcraft.util.GuiHelpersNeoForge;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;

public class BeverageMakingScreen extends BaseContainerScreen<BeverageMakingMenu> implements IFluidStackScreen {

    private static final ResourceLocation BACKGROUND_TEXTURE = ResourceLocation.fromNamespaceAndPath(
        FoodCraft.MOD_ID, "textures/gui/container/beverage_making.png");

    public static final ResourceLocation COOL_PROGRESS_SPRITE = ResourceLocation.fromNamespaceAndPath(
        FoodCraft.MOD_ID, "container/cool_progress");

    private final FluidTank fluidTank;

    public BeverageMakingScreen(BeverageMakingMenu menu,
        Inventory playerInventory, Component title) {
        super(menu, playerInventory, title, BACKGROUND_TEXTURE);
        this.fluidTank = menu.fluidTank;
    }

    @Override
    protected void renderTooltip(GuiGraphics guiGraphics, int x, int y) {
        if (fluidTank != null)
            GuiHelpersNeoForge.renderFluidTank(guiGraphics, fluidTank.getFluid(), menu.fluidTank.getCapacity(), 18 + getGuiLeft(), 19 + getGuiTop(), 11, 46);
        super.renderTooltip(guiGraphics, x, y);

        renderBurn(guiGraphics, 58, 30);

        if (menu instanceof MenuDataAccess menu && menu.getContainerData().get(4) == 0) {
            renderLit(guiGraphics, 144, 23);
        } else {
            renderCool(guiGraphics, 146, 54);
        }
    }

    public void renderCool(GuiGraphics guiGraphics, int x, int y) {
        if (menu instanceof MenuDataAccess menu && menu.getContainerData().get(1) > 0) {
            var j1 = Mth.floor((getLitProgress() * 13.0F) );
            guiGraphics.blitSprite(COOL_PROGRESS_SPRITE, 13, 12, 0, 0,
                getGuiLeft() + x, getGuiTop() + y, j1, 12);
        }
    }

    @Override
    public void setFluid(FluidStack fluidStack) {
        if (fluidTank != null)
            fluidTank.setFluid(fluidStack);
    }
}
