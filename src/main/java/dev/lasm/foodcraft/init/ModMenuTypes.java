package dev.lasm.foodcraft.init;

import dev.lasm.foodcraft.FoodCraft;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(
        ForgeRegistries.MENU_TYPES ,FoodCraft.MOD_ID);

//    public static final RegistrySupplier<MenuType<CookingPotMenu>> COOKING_POT = MENU_TYPES
//        .register("cooking_pot", MenuType::create);
}
