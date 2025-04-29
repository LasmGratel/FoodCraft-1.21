package dev.lasm.foodcraft.init;

import dev.lasm.foodcraft.FoodCraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(
        BuiltInRegistries.MENU,FoodCraft.MOD_ID);

//    public static final RegistrySupplier<MenuType<CookingPotMenu>> COOKING_POT = MENU_TYPES
//        .register("cooking_pot", MenuType::create);
}
