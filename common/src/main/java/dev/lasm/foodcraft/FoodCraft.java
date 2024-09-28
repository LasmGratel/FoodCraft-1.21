package dev.lasm.foodcraft;

import com.google.common.base.Suppliers;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrarManager;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.lasm.foodcraft.init.ModBlocks;
import dev.lasm.foodcraft.init.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public final class FoodCraft {
    public static final String MOD_ID = "foodcraft";

    public static final Supplier<RegistrarManager> REGISTRIES = Suppliers.memoize(() -> RegistrarManager.get(MOD_ID));

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(MOD_ID, Registries.CREATIVE_MODE_TAB);
    public static final RegistrySupplier<CreativeModeTab> CREATIVE_TAB = TABS.register("main", () ->
            CreativeTabRegistry.create(Component.translatable("itemGroup." + MOD_ID + ".main"),
                    () -> new ItemStack(ModItems.RICE_PORRIDGE.get())));

    public static void init() {
        TABS.register();
        ModBlocks.BLOCKS.register();
        ModItems.ITEMS.register();
    }
}
