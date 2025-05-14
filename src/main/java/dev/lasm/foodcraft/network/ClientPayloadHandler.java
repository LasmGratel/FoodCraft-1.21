package dev.lasm.foodcraft.network;

import dev.lasm.foodcraft.client.screen.IFluidStackScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ClientPayloadHandler {
    public static void handleFluidStackScreen(final FluidStackPayload data, final IPayloadContext context) {
        context.enqueueWork(() -> {
            if (Minecraft.getInstance().screen instanceof IFluidStackScreen screen) {
                screen.setFluid(data.fluidStack());
            }
        }).exceptionally(e -> {
            // Handle exception
            context.disconnect(Component.translatable("multiplayer.disconnect.generic", e.getMessage()));
            return null;
        });
    }
}
