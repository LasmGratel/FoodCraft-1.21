package dev.lasm.foodcraft.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.lasm.foodcraft.FoodCraft;
import dev.lasm.foodcraft.container.PotMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;

public class PotScreen extends BaseContainerScreen<PotMenu> {
    private static final ResourceLocation BACKGROUND_TEXTURE = ResourceLocation.fromNamespaceAndPath(
        FoodCraft.MOD_ID, "textures/gui/container/pot.png");


    public PotScreen(PotMenu menu, Inventory playerInventory,
        Component title) {
        super(menu, playerInventory, title, BACKGROUND_TEXTURE);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
        super.renderBg(guiGraphics, partialTicks, mouseX, mouseY);

        renderLit(guiGraphics, 80, 64);
        renderCookBar(guiGraphics, partialTicks);
    }

    private static final ResourceLocation COOK_PROGRESS_SPRITE = ResourceLocation.fromNamespaceAndPath(FoodCraft.MOD_ID, "container/cook_progress");
    private static final ResourceLocation POT_PROGRESS_SPRITE = ResourceLocation.fromNamespaceAndPath(FoodCraft.MOD_ID, "container/pot_progress");
    private static final ResourceLocation COOKED_INDICATOR_SPRITE = ResourceLocation.fromNamespaceAndPath(FoodCraft.MOD_ID, "container/cooked_indicator");
    private static final ResourceLocation OVERCOOKED_INDICATOR_SPRITE = ResourceLocation.fromNamespaceAndPath(FoodCraft.MOD_ID, "container/overcooked_indicator");

    private void renderCookBar(GuiGraphics guiGraphics, float partialTicks) {
        var data = menu.getContainerData();
        if (data.get(2) != 0 && data.get(3) != 0) {
            var cookingTime = data.get(0);
            var minCookingTime = data.get(1);
            var maxCookingTime = data.get(2);
            var maxTime = data.get(3);

            guiGraphics.blitSprite(POT_PROGRESS_SPRITE, 24, 17, 0, 0, 93 + getGuiLeft(), 19 + getGuiTop(),
                Math.min(Mth.floor(24 * ((float)cookingTime / ((maxCookingTime + minCookingTime) / 2.0f))), 24), 17);



            var cookedX = Mth.floor(78 * ((float)minCookingTime / maxTime));
            var overcookedX = Mth.floor(78 * ((float)maxCookingTime / maxTime));

            if (cookingTime <= minCookingTime) {
                guiGraphics.blitSprite(COOK_PROGRESS_SPRITE, 78, 3, 0, 0, 48 + getGuiLeft(), 38 + getGuiTop(),
                    Mth.floor(78 * ((float)cookingTime / maxTime)), 3);
            } else if (cookingTime <= maxCookingTime) {
                guiGraphics.blitSprite(COOK_PROGRESS_SPRITE, 78, 3, 0, 0, 48 + getGuiLeft(), 38 + getGuiTop(),
                    cookedX, 3);
                RenderSystem.enableBlend();
                guiGraphics.setColor(0.2f, 1.0f, 0.2f, 1.0f);
                guiGraphics.blitSprite(COOK_PROGRESS_SPRITE, 78, 3, cookedX, 0,
                    48 + cookedX + getGuiLeft(), 38 + getGuiTop(), Mth.floor((overcookedX - cookedX) * ((float) (cookingTime - minCookingTime) / (maxCookingTime - minCookingTime))), 3);
                guiGraphics.setColor(1.0f, 1.0f, 1.0f, 1.0f);
                RenderSystem.disableBlend();
            } else {
                guiGraphics.blitSprite(COOK_PROGRESS_SPRITE, 78, 3, 0, 0, 48 + getGuiLeft(), 38 + getGuiTop(),
                    overcookedX, 3);
                RenderSystem.enableBlend();
                guiGraphics.setColor(1.0f, 0.2f, 0.2f, 1.0f);
                guiGraphics.blitSprite(COOK_PROGRESS_SPRITE, 78, 3, overcookedX, 0,
                    48 + overcookedX + getGuiLeft(), 38 + getGuiTop(), Mth.floor((78 - overcookedX) * ((float) (cookingTime - maxCookingTime) / (maxTime - maxCookingTime))), 3);
                guiGraphics.setColor(1.0f, 1.0f, 1.0f, 1.0f);
                RenderSystem.disableBlend();
            }

            guiGraphics.blitSprite(COOKED_INDICATOR_SPRITE, 1, 5, 0, 0, 48 + cookedX + getGuiLeft(), 37 + getGuiTop(), 1, 5);
            guiGraphics.blitSprite(OVERCOOKED_INDICATOR_SPRITE, 1, 5, 0, 0, 48 + overcookedX + getGuiLeft(), 37 + getGuiTop(), 1, 5);



        }

    }
}
