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
    public static final RegistrySupplier<Item> CARROT_SHREDDED;

    // Plants
    public static final RegistrySupplier<Item> CORN;
    public static final RegistrySupplier<Item> CUCUMBER;
    public static final RegistrySupplier<Item> EGGPLANT;
    public static final RegistrySupplier<Item> FACING_HEAVEN_PEPPER;
    public static final RegistrySupplier<Item> GREEN_PEPPER;
    public static final RegistrySupplier<Item> PEANUT;
    public static final RegistrySupplier<Item> RICE;
    public static final RegistrySupplier<Item> STICKY_RICE;
    public static final RegistrySupplier<Item> SWEET_POTATO;
    public static final RegistrySupplier<Item> TOMATO;
    public static final RegistrySupplier<Item> WHITE_RADISH;
    public static final RegistrySupplier<Item> CABBAGE;
    public static final RegistrySupplier<Item> STRAWBERRY;
    public static final RegistrySupplier<Item> GRAPEFRUIT;
    public static final RegistrySupplier<Item> CHERRY;
    public static final RegistrySupplier<Item> COCONUT;
    public static final RegistrySupplier<Item> BANANA;
    public static final RegistrySupplier<Item> PEACH;
    public static final RegistrySupplier<Item> PERSIMMON;
    public static final RegistrySupplier<Item> POMEGRANATE;
    public static final RegistrySupplier<Item> HAWTHORN;
    public static final RegistrySupplier<Item> LOQUAT;
    public static final RegistrySupplier<Item> LEMON;
    public static final RegistrySupplier<Item> PAPAYA;
    public static final RegistrySupplier<Item> LONGAN;
    public static final RegistrySupplier<Item> MANGO;
    public static final RegistrySupplier<Item> LITCHI;
    public static final RegistrySupplier<Item> PEAR;
    public static final RegistrySupplier<Item> ORANGE;
    public static final RegistrySupplier<Item> DATE;
    public static final RegistrySupplier<Item> GREEN_ONION;

    public static final RegistrySupplier<Item> GRAPEFRUIT_ICE_CREAM;
    public static final RegistrySupplier<Item> CHERRY_ICE_CREAM;
    public static final RegistrySupplier<Item> COCONUT_ICE_CREAM;
    public static final RegistrySupplier<Item> BANANA_ICE_CREAM;
    public static final RegistrySupplier<Item> PEACH_ICE_CREAM;
    public static final RegistrySupplier<Item> PERSIMMON_ICE_CREAM;
    public static final RegistrySupplier<Item> POMEGRANATE_ICE_CREAM;
    public static final RegistrySupplier<Item> HAWTHORN_ICE_CREAM;
    public static final RegistrySupplier<Item> LOQUAT_ICE_CREAM;
    public static final RegistrySupplier<Item> LEMON_ICE_CREAM;
    public static final RegistrySupplier<Item> PAPAYA_ICE_CREAM;
    public static final RegistrySupplier<Item> LONGAN_ICE_CREAM;
    public static final RegistrySupplier<Item> MANGO_ICE_CREAM;
    public static final RegistrySupplier<Item> LITCHI_ICE_CREAM;
    public static final RegistrySupplier<Item> PEAR_ICE_CREAM;
    public static final RegistrySupplier<Item> ORANGE_ICE_CREAM;
    public static final RegistrySupplier<Item> DATE_ICE_CREAM;

    public static final RegistrySupplier<Item> GRAPEFRUIT_JAM;
    public static final RegistrySupplier<Item> CHERRY_JAM;
    public static final RegistrySupplier<Item> COCONUT_JAM;
    public static final RegistrySupplier<Item> BANANA_JAM;
    public static final RegistrySupplier<Item> PEACH_JAM;
    public static final RegistrySupplier<Item> PERSIMMON_JAM;
    public static final RegistrySupplier<Item> POMEGRANATE_JAM;
    public static final RegistrySupplier<Item> HAWTHORN_JAM;
    public static final RegistrySupplier<Item> LOQUAT_JAM;
    public static final RegistrySupplier<Item> LEMON_JAM;
    public static final RegistrySupplier<Item> PAPAYA_JAM;
    public static final RegistrySupplier<Item> LONGAN_JAM;
    public static final RegistrySupplier<Item> MANGO_JAM;
    public static final RegistrySupplier<Item> LITCHI_JAM;
    public static final RegistrySupplier<Item> PEAR_JAM;
    public static final RegistrySupplier<Item> ORANGE_JAM;
    public static final RegistrySupplier<Item> DATE_JAM;

    public static Item.Properties foodItem(FoodProperties food) {
        return (new Item.Properties()).food(food).arch$tab(FoodCraft.CREATIVE_TAB.getKey());
    }

    static {
        RICE_PORRIDGE = ITEMS.register("rice_porridge", () -> new Item(foodItem(FoodValues.RICE_PORRIDGE)));

        CARROT_SHREDDED = ITEMS.register("carrot_shredded", () -> new Item(foodItem(FoodValues.CARROT_SHREDDED)));

        CORN = ITEMS.register("corn", () -> new Item(foodItem(FoodValues.PLANT)));
        CUCUMBER = ITEMS.register("cucumber", () -> new Item(foodItem(FoodValues.PLANT)));
        EGGPLANT = ITEMS.register("eggplant", () -> new Item(foodItem(FoodValues.PLANT)));
        FACING_HEAVEN_PEPPER = ITEMS.register("facing_heaven_pepper", () -> new Item(foodItem(FoodValues.PLANT)));
        GREEN_PEPPER = ITEMS.register("green_pepper", () -> new Item(foodItem(FoodValues.PLANT)));
        PEANUT = ITEMS.register("peanut", () -> new Item(foodItem(FoodValues.PLANT)));
        RICE = ITEMS.register("rice", () -> new Item(foodItem(FoodValues.PLANT)));
        STICKY_RICE = ITEMS.register("sticky_rice", () -> new Item(foodItem(FoodValues.PLANT)));
        SWEET_POTATO = ITEMS.register("sweet_potato", () -> new Item(foodItem(FoodValues.PLANT)));
        TOMATO = ITEMS.register("tomato", () -> new Item(foodItem(FoodValues.PLANT)));
        WHITE_RADISH = ITEMS.register("white_radish", () -> new Item(foodItem(FoodValues.PLANT)));
        CABBAGE = ITEMS.register("cabbage", () -> new Item(foodItem(FoodValues.PLANT)));
        GREEN_ONION = ITEMS.register("date", () -> new Item(foodItem(FoodValues.PLANT)));

        STRAWBERRY = ITEMS.register("strawberry", () -> new Item(foodItem(FoodValues.FRUIT)));
        GRAPEFRUIT = ITEMS.register("grapefruit", () -> new Item(foodItem(FoodValues.FRUIT)));
        CHERRY = ITEMS.register("cherry", () -> new Item(foodItem(FoodValues.FRUIT)));
        COCONUT = ITEMS.register("coconut", () -> new Item(foodItem(FoodValues.FRUIT)));
        BANANA = ITEMS.register("banana", () -> new Item(foodItem(FoodValues.FRUIT)));
        PEACH = ITEMS.register("peach", () -> new Item(foodItem(FoodValues.FRUIT)));
        PERSIMMON = ITEMS.register("persimmon", () -> new Item(foodItem(FoodValues.FRUIT)));
        POMEGRANATE = ITEMS.register("pomegranate", () -> new Item(foodItem(FoodValues.FRUIT)));
        HAWTHORN = ITEMS.register("hawthorn", () -> new Item(foodItem(FoodValues.FRUIT)));
        LOQUAT = ITEMS.register("loquat", () -> new Item(foodItem(FoodValues.FRUIT)));
        LEMON = ITEMS.register("lemon", () -> new Item(foodItem(FoodValues.FRUIT)));
        PAPAYA = ITEMS.register("papaya", () -> new Item(foodItem(FoodValues.FRUIT)));
        LONGAN = ITEMS.register("longan", () -> new Item(foodItem(FoodValues.FRUIT)));
        MANGO = ITEMS.register("mango", () -> new Item(foodItem(FoodValues.FRUIT)));
        LITCHI = ITEMS.register("litchi", () -> new Item(foodItem(FoodValues.FRUIT)));
        PEAR = ITEMS.register("pear", () -> new Item(foodItem(FoodValues.FRUIT)));
        ORANGE = ITEMS.register("orange", () -> new Item(foodItem(FoodValues.FRUIT)));
        DATE = ITEMS.register("date", () -> new Item(foodItem(FoodValues.FRUIT)));

        GRAPEFRUIT_ICE_CREAM = ITEMS.register("grapefruit_ice_cream", () -> new Item(foodItem(FoodValues.FRUIT_ICE_CREAM)));
        CHERRY_ICE_CREAM = ITEMS.register("cherry_ice_cream", () -> new Item(foodItem(FoodValues.FRUIT_ICE_CREAM)));
        COCONUT_ICE_CREAM = ITEMS.register("coconut_ice_cream", () -> new Item(foodItem(FoodValues.FRUIT_ICE_CREAM)));
        BANANA_ICE_CREAM = ITEMS.register("banana_ice_cream", () -> new Item(foodItem(FoodValues.FRUIT_ICE_CREAM)));
        PEACH_ICE_CREAM = ITEMS.register("peach_ice_cream", () -> new Item(foodItem(FoodValues.FRUIT_ICE_CREAM)));
        PERSIMMON_ICE_CREAM = ITEMS.register("persimmon_ice_cream", () -> new Item(foodItem(FoodValues.FRUIT_ICE_CREAM)));
        POMEGRANATE_ICE_CREAM = ITEMS.register("pomegranate_ice_cream", () -> new Item(foodItem(FoodValues.FRUIT_ICE_CREAM)));
        HAWTHORN_ICE_CREAM = ITEMS.register("hawthorn_ice_cream", () -> new Item(foodItem(FoodValues.FRUIT_ICE_CREAM)));
        LOQUAT_ICE_CREAM = ITEMS.register("loquat_ice_cream", () -> new Item(foodItem(FoodValues.FRUIT_ICE_CREAM)));
        LEMON_ICE_CREAM = ITEMS.register("lemon_ice_cream", () -> new Item(foodItem(FoodValues.FRUIT_ICE_CREAM)));
        PAPAYA_ICE_CREAM = ITEMS.register("papaya_ice_cream", () -> new Item(foodItem(FoodValues.FRUIT_ICE_CREAM)));
        LONGAN_ICE_CREAM = ITEMS.register("longan_ice_cream", () -> new Item(foodItem(FoodValues.FRUIT_ICE_CREAM)));
        MANGO_ICE_CREAM = ITEMS.register("mango_ice_cream", () -> new Item(foodItem(FoodValues.FRUIT_ICE_CREAM)));
        LITCHI_ICE_CREAM = ITEMS.register("litchi_ice_cream", () -> new Item(foodItem(FoodValues.FRUIT_ICE_CREAM)));
        PEAR_ICE_CREAM = ITEMS.register("pear_ice_cream", () -> new Item(foodItem(FoodValues.FRUIT_ICE_CREAM)));
        ORANGE_ICE_CREAM = ITEMS.register("orange_ice_cream", () -> new Item(foodItem(FoodValues.FRUIT_ICE_CREAM)));
        DATE_ICE_CREAM = ITEMS.register("date_ice_cream", () -> new Item(foodItem(FoodValues.FRUIT_ICE_CREAM)));

        GRAPEFRUIT_JAM = ITEMS.register("grapefruit_jam", () -> new Item(foodItem(FoodValues.FRUIT_JAM)));
        CHERRY_JAM = ITEMS.register("cherry_jam", () -> new Item(foodItem(FoodValues.FRUIT_JAM)));
        COCONUT_JAM = ITEMS.register("coconut_jam", () -> new Item(foodItem(FoodValues.FRUIT_JAM)));
        BANANA_JAM = ITEMS.register("banana_jam", () -> new Item(foodItem(FoodValues.FRUIT_JAM)));
        PEACH_JAM = ITEMS.register("peach_jam", () -> new Item(foodItem(FoodValues.FRUIT_JAM)));
        PERSIMMON_JAM = ITEMS.register("persimmon_jam", () -> new Item(foodItem(FoodValues.FRUIT_JAM)));
        POMEGRANATE_JAM = ITEMS.register("pomegranate_jam", () -> new Item(foodItem(FoodValues.FRUIT_JAM)));
        HAWTHORN_JAM = ITEMS.register("hawthorn_jam", () -> new Item(foodItem(FoodValues.FRUIT_JAM)));
        LOQUAT_JAM = ITEMS.register("loquat_jam", () -> new Item(foodItem(FoodValues.FRUIT_JAM)));
        LEMON_JAM = ITEMS.register("lemon_jam", () -> new Item(foodItem(FoodValues.FRUIT_JAM)));
        PAPAYA_JAM = ITEMS.register("papaya_jam", () -> new Item(foodItem(FoodValues.FRUIT_JAM)));
        LONGAN_JAM = ITEMS.register("longan_jam", () -> new Item(foodItem(FoodValues.FRUIT_JAM)));
        MANGO_JAM = ITEMS.register("mango_jam", () -> new Item(foodItem(FoodValues.FRUIT_JAM)));
        LITCHI_JAM = ITEMS.register("litchi_jam", () -> new Item(foodItem(FoodValues.FRUIT_JAM)));
        PEAR_JAM = ITEMS.register("pear_jam", () -> new Item(foodItem(FoodValues.FRUIT_JAM)));
        ORANGE_JAM = ITEMS.register("orange_jam", () -> new Item(foodItem(FoodValues.FRUIT_JAM)));
        DATE_JAM = ITEMS.register("date_jam", () -> new Item(foodItem(FoodValues.FRUIT_JAM)));


    }
}
