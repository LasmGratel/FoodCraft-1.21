package dev.lasm.foodcraft.client.screen;

import dev.lasm.foodcraft.FoodCraft;
import dev.lasm.foodcraft.container.BrewBarrelMenu;
import dev.lasm.foodcraft.util.GuiHelpersNeoForge;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import org.jetbrains.annotations.NotNull;

public class BrewBarrelScreen extends BaseContainerScreen<BrewBarrelMenu> implements IFluidStackScreen {

    private static final ResourceLocation BACKGROUND_TEXTURE = ResourceLocation.fromNamespaceAndPath(
        FoodCraft.MOD_ID, "textures/gui/container/brew_barrel.png");

    private final FluidTank fluidTank;

    public BrewBarrelScreen(BrewBarrelMenu menu,
        Inventory playerInventory, Component title) {
        super(menu, playerInventory, title, BACKGROUND_TEXTURE);
        this.fluidTank = menu.fluidTank;
    }

    @Override
    protected void renderTooltip(@NotNull GuiGraphics guiGraphics, int x, int y) {
        if (fluidTank != null)
            GuiHelpersNeoForge.renderFluidTank(guiGraphics, fluidTank.getFluid(), menu.fluidTank.getCapacity(), 18 + getGuiLeft(), 19 + getGuiTop(), 11, 46);

        float f1 = menu.getContainerData().get(0);
        float f2 = menu.getContainerData().get(1);
        if (f2 != 0.0f) {
            GuiHelpersNeoForge.renderItemCooldown(guiGraphics, 138 + getGuiLeft(), 59 + getGuiTop(), f1 / f2);
        }

        super.renderTooltip(guiGraphics, x, y);
    }

    @Override
    public void setFluid(FluidStack fluidStack) {
        if (fluidTank != null)
            fluidTank.setFluid(fluidStack);
    }
}
