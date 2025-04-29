package dev.lasm.foodcraft.init;

import dev.lasm.foodcraft.FoodCraft;
import dev.lasm.foodcraft.container.BrewBarrelMenu;
import java.util.function.Supplier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenus {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(
        BuiltInRegistries.MENU, FoodCraft.MOD_ID);

    public static final Supplier<MenuType<BrewBarrelMenu>> BREW_BARREL = MENU_TYPES.register("brew_barrel", () -> new MenuType<>(BrewBarrelMenu::new,
        FeatureFlags.DEFAULT_FLAGS));

}
