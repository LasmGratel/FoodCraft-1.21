/**
 * MIT License
 *
 * Copyright (c) 2018 BlakeBr0
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package dev.lasm.foodcraft.client.screen;

import dev.lasm.foodcraft.FoodCraft;
import dev.lasm.foodcraft.container.MenuDataAccess;
import java.text.NumberFormat;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;

@ParametersAreNonnullByDefault
public abstract class BaseContainerScreen<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> {
    private static final ResourceLocation LIT_PROGRESS_SPRITE = ResourceLocation.fromNamespaceAndPath(
        FoodCraft.MOD_ID, "container/lit_progress");
    private static final ResourceLocation BURN_PROGRESS_SPRITE = ResourceLocation.fromNamespaceAndPath(FoodCraft.MOD_ID, "container/burn_progress");

    protected ResourceLocation bgTexture;
    protected int bgImgWidth;
    protected int bgImgHeight;

    public BaseContainerScreen(T menu, Inventory playerInventory, Component title,
        ResourceLocation bgTexture) {
        this(menu, playerInventory, title, bgTexture, 176, 166);
    }

    public BaseContainerScreen(T container, Inventory inventory, Component title, ResourceLocation bgTexture, int bgWidth, int bgHeight) {
        this(container, inventory, title, bgTexture, bgWidth, bgHeight, 256, 256);
    }

    public BaseContainerScreen(T container, Inventory inventory, Component title, ResourceLocation bgTexture, int bgWidth, int bgHeight, int bgImgWidth, int bgImgHeight) {
        super(container, inventory, title);
        this.imageWidth = bgWidth;
        this.imageHeight = bgHeight;
        this.bgTexture = bgTexture;
        this.bgImgWidth = bgImgWidth;
        this.bgImgHeight = bgImgHeight;
    }

    @Override
    public void render(GuiGraphics gfx, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(gfx, mouseX, mouseY, partialTicks);
        super.render(gfx, mouseX, mouseY, partialTicks);
        this.renderTooltip(gfx, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics gfx, float partialTicks, int mouseX, int mouseY) {
        int x = this.getGuiLeft();
        int y = this.getGuiTop();

        gfx.blit(this.bgTexture, x, y, 0, 0, this.imageWidth, this.imageHeight, this.bgImgWidth, this.bgImgHeight);
    }

    public float getBurnProgress() {
        if (menu instanceof MenuDataAccess menu) {
            var data = menu.getContainerData();
            int i = data.get(2);
            int j = data.get(3);
            return j != 0 && i != 0 ? Mth.clamp((float)i / (float)j, 0.0F, 1.0F) : 0.0F;
        }
        return 0f;
    }

    public float getLitProgress() {
        if (menu instanceof MenuDataAccess menu) {
            var data = menu.getContainerData();
            int i = data.get(1);
            if (i == 0) {
                i = 200;
            }

            return Mth.clamp((float) data.get(0) / (float) i, 0.0F, 1.0F);
        }
        return 0f;
    }

    public void renderLit(GuiGraphics guiGraphics, int x, int y) {
        if (menu instanceof MenuDataAccess menu && menu.getContainerData().get(1) > 0) {
            var j1 = Mth.ceil((getLitProgress() * 13.0F) );
            guiGraphics.blitSprite(LIT_PROGRESS_SPRITE, 14, 14, 0, 14 - j1, getGuiLeft() + x, getGuiTop() + y - j1 + 14, 14, j1);
        }
    }

    public void renderBurn(GuiGraphics guiGraphics, int x, int y) {
        if (getBurnProgress() > 0) {
            var j1 = Mth.floor(((1.0f - getBurnProgress()) * 24.0F));
            guiGraphics.blitSprite(BURN_PROGRESS_SPRITE, 24, 16, 0, 0, getGuiLeft() + x, getGuiTop() + y, j1, 16);
        }
    }

    protected static String number(Object number) {
        return NumberFormat.getInstance().format(number);
    }
}