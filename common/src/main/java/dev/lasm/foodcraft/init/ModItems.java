package dev.lasm.foodcraft.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.lasm.foodcraft.FoodCraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(FoodCraft.MOD_ID, Registries.ITEM);
    public static final RegistrySupplier<Item> RICE_PORRIDGE;
    public static final RegistrySupplier<Item> PEANUT;


    public static Item.Properties foodItem(FoodProperties food) {
        return (new Item.Properties()).food(food).arch$tab(FoodCraft.CREATIVE_TAB.getKey());
    }

    static {
        PEANUT = ITEMS.register("peanut", () -> new Item(foodItem(FoodValues.PEANUT)));

        RICE_PORRIDGE = ITEMS.register("rice_porridge", () -> new Item(foodItem(FoodValues.RICE_PORRIDGE)));
    }
}
