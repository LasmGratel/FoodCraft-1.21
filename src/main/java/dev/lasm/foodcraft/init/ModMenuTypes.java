package dev.lasm.foodcraft.init;

import dev.lasm.foodcraft.FoodCraft;
import dev.lasm.foodcraft.container.BrewBarrelMenu;
import dev.lasm.foodcraft.container.FryingPanMenu;
import java.util.function.Supplier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(
        BuiltInRegistries.MENU, FoodCraft.MOD_ID);

    public static final Supplier<MenuType<BrewBarrelMenu>> BREW_BARREL = MENU_TYPES.register("brew_barrel", () -> IMenuTypeExtension.create(BrewBarrelMenu::new));

    public static final Supplier<MenuType<FryingPanMenu>> FRYING_PAN = MENU_TYPES.register("frying_pan", () -> IMenuTypeExtension.create(FryingPanMenu::new));

}
