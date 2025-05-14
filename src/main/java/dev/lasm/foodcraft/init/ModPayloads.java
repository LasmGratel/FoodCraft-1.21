package dev.lasm.foodcraft.init;


import dev.lasm.foodcraft.FoodCraft;
import dev.lasm.foodcraft.network.ClientPayloadHandler;
import dev.lasm.foodcraft.network.FluidStackPayload;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.HandlerThread;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = FoodCraft.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModPayloads {
    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1").executesOn(HandlerThread.NETWORK);
        registrar.playToClient(FluidStackPayload.TYPE, FluidStackPayload.STREAM_CODEC,
            ClientPayloadHandler::handleFluidStackScreen);
    }
}
