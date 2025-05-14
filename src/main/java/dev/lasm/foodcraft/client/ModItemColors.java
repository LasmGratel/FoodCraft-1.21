package dev.lasm.foodcraft.client;

import dev.lasm.foodcraft.FoodCraft;
import dev.lasm.foodcraft.init.ModItems;
import net.minecraft.client.color.item.ItemColors;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@EventBusSubscriber(modid = FoodCraft.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModItemColors extends ItemColors {
    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        event.register((stack, tintIndex) -> tintIndex == 1 ? 0xff722f37 : -1, ModItems.WINE.get());
        event.register((stack, tintIndex) -> tintIndex == 1 ? 0xffffffff : -1, ModItems.SPIRIT.get());
        event.register((stack, tintIndex) -> tintIndex == 1 ? 0xff6f2da8 : -1, ModItems.GRAPE_LIQUEUR.get());
        event.register((stack, tintIndex) -> tintIndex == 1 ? 0xfff6edd0 : -1, ModItems.LITCHI_LIQUEUR.get());
        event.register((stack, tintIndex) -> tintIndex == 1 ? 0xffffafaf : -1, ModItems.PEACH_LIQUEUR.get());
        event.register((stack, tintIndex) -> tintIndex == 1 ? 0xffffd986 : -1, ModItems.MANGO_LIQUEUR.get());
        event.register((stack, tintIndex) -> tintIndex == 1 ? 0xfffcf393 : -1, ModItems.LEMON_LIQUEUR.get());
        event.register((stack, tintIndex) -> tintIndex == 1 ? 0xfff46c30 : -1, ModItems.POMEGRANATE_LIQUEUR.get());
        event.register((stack, tintIndex) -> tintIndex == 1 ? 0xffFA853D : -1, ModItems.APPLE_LIQUEUR.get());
        event.register((stack, tintIndex) -> tintIndex == 1 ? 0xffe0da42 : -1, ModItems.GOLDEN_GRAPE_LIQUEUR.get());
        event.register((stack, tintIndex) -> tintIndex == 1 ? 0xffe7e480 : -1, ModItems.GOLDEN_APPLE_LIQUEUR.get());
    }
}
