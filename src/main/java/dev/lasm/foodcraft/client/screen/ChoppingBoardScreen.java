package dev.lasm.foodcraft.client.screen;

import dev.lasm.foodcraft.FoodCraft;
import dev.lasm.foodcraft.container.ChoppingBoardMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ChoppingBoardScreen extends BaseContainerScreen<ChoppingBoardMenu> {
    private static final ResourceLocation BACKGROUND_TEXTURE = ResourceLocation.fromNamespaceAndPath(
        FoodCraft.MOD_ID, "textures/gui/container/chopping_board.png");

    public ChoppingBoardScreen(ChoppingBoardMenu menu,
        Inventory playerInventory,
        Component title) {
        super(menu, playerInventory, title, BACKGROUND_TEXTURE);
    }
}
