package dev.lasm.foodcraft.init;

import dev.architectury.registry.client.rendering.ColorHandlerRegistry;

public class ModColors {
    public static void register() {

        ColorHandlerRegistry.registerItemColors((itemStack, i) -> {
            if (i == 0) return -1;
            else return 1;
        }, ModItems.BANANA_ICE_CREAM);
    }
}
