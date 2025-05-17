package dev.lasm.foodcraft.init;

import dev.lasm.foodcraft.FoodCraft;
import dev.lasm.foodcraft.container.BeverageMakingMenu;
import dev.lasm.foodcraft.container.BrewBarrelMenu;
import dev.lasm.foodcraft.container.ChoppingBoardMenu;
import dev.lasm.foodcraft.container.FryingPanMenu;
import dev.lasm.foodcraft.container.MillMenu;
import dev.lasm.foodcraft.container.PotMenu;
import dev.lasm.foodcraft.container.PanMenu;
import dev.lasm.foodcraft.container.PressureCookerMenu;
import dev.lasm.foodcraft.container.StoveMenu;
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

    public static final Supplier<MenuType<PotMenu>> POT = MENU_TYPES.register("pot", () -> IMenuTypeExtension.create(PotMenu::new));

    public static final Supplier<MenuType<ChoppingBoardMenu>> CHOPPING_BOARD = MENU_TYPES.register("chopping_board", () -> IMenuTypeExtension.create(ChoppingBoardMenu::new));

    public static final Supplier<MenuType<PressureCookerMenu>> PRESSURE_COOKER = MENU_TYPES.register("pressure_cooker", () -> IMenuTypeExtension.create(PressureCookerMenu::new));

    public static final Supplier<MenuType<MillMenu>> MILL = MENU_TYPES.register("mill", () -> IMenuTypeExtension.create(MillMenu::new));

    public static final Supplier<MenuType<PanMenu>> PAN = MENU_TYPES.register("pan", () -> IMenuTypeExtension.create(PanMenu::new));

    public static final Supplier<MenuType<StoveMenu>> STOVE = MENU_TYPES.register("stove", () -> IMenuTypeExtension.create(StoveMenu::new));

    public static final Supplier<MenuType<BeverageMakingMenu>> BEVERAGE_MAKING = MENU_TYPES.register("beverage_making", () -> IMenuTypeExtension.create(
        BeverageMakingMenu::new));

}
