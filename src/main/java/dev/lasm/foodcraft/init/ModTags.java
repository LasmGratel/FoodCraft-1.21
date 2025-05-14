package dev.lasm.foodcraft.init;

import dev.lasm.foodcraft.FoodCraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static final TagKey<Item> CROPS = commonItemTag("crops");

    public static final TagKey<Item> STARCHES = modItemTag("starches");
    public static final TagKey<Item> COOKED_RICE = modItemTag("cooked_rice");

    public static final TagKey<Item> SALT = commonItemTag("salt");
    public static final TagKey<Item> COOKING_OIL = commonItemTag("cookingoil");

    // Vegetables
    public static final TagKey<Item> VEGETABLES = commonItemTag("vegetables");
    public static final TagKey<Item> CORN = commonItemTag("vegetables/corn");
    public static final TagKey<Item> CUCUMBER = commonItemTag("vegetables/cucumber");
    public static final TagKey<Item> EGGPLANT = commonItemTag("vegetables/eggplant");
    public static final TagKey<Item> FACING_HEAVEN_PEPPER = commonItemTag("vegetables/facing_heaven_pepper");
    public static final TagKey<Item> GREEN_PEPPER = commonItemTag("vegetables/green_pepper");
    public static final TagKey<Item> PEANUT = commonItemTag("vegetables/peanut");
    public static final TagKey<Item> RICE = commonItemTag("vegetables/rice");
    public static final TagKey<Item> STICKY_RICE = commonItemTag("crops/sticky_rice");
    public static final TagKey<Item> SWEET_POTATO = commonItemTag("vegetables/sweet_potato");
    public static final TagKey<Item> TOMATO = commonItemTag("vegetables/tomato");
    public static final TagKey<Item> WHITE_RADISH = commonItemTag("vegetables/white_radish");
    public static final TagKey<Item> CABBAGE = commonItemTag("vegetables/cabbage");
    public static final TagKey<Item> GREEN_ONION = commonItemTag("vegetables/green_onion");
    public static final TagKey<Item> ADZUKI_BEAN = commonItemTag("crops/adzuki_bean");
    public static final TagKey<Item> SOYBEAN = commonItemTag("crops/soybean");
    public static final TagKey<Item> MUNG_BEAN = commonItemTag("crops/mung_bean");

    // Fruits
    public static final TagKey<Item> FRUITS = commonItemTag("fruits");
    public static final TagKey<Item> STRAWBERRY = commonItemTag("fruits/strawberry");
    public static final TagKey<Item> GRAPEFRUIT = commonItemTag("fruits/grapefruit");
    public static final TagKey<Item> CHERRY = commonItemTag("fruits/cherry");
    public static final TagKey<Item> COCONUT = commonItemTag("fruits/coconut");
    public static final TagKey<Item> BANANA = commonItemTag("fruits/banana");
    public static final TagKey<Item> PEACH = commonItemTag("fruits/peach");
    public static final TagKey<Item> PERSIMMON = commonItemTag("fruits/persimmon");
    public static final TagKey<Item> POMEGRANATE = commonItemTag("fruits/pomegranate");
    public static final TagKey<Item> HAWTHORN = commonItemTag("fruits/hawthorn");
    public static final TagKey<Item> LOQUAT = commonItemTag("fruits/loquat");
    public static final TagKey<Item> LEMON = commonItemTag("fruits/lemon");
    public static final TagKey<Item> PAPAYA = commonItemTag("fruits/papaya");
    public static final TagKey<Item> LONGAN = commonItemTag("fruits/longan");
    public static final TagKey<Item> MANGO = commonItemTag("fruits/mango");
    public static final TagKey<Item> LITCHI = commonItemTag("fruits/litchi");
    public static final TagKey<Item> PEAR = commonItemTag("fruits/pear");
    public static final TagKey<Item> ORANGE = commonItemTag("fruits/orange");
    public static final TagKey<Item> DATE = commonItemTag("fruits/date");

    public static TagKey<Item> commonItemTag(String path) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", path));
    }

    public static TagKey<Item> commonBlockTag(String path) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", path));
    }

    public static TagKey<Item> modItemTag(String path) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath(FoodCraft.MOD_ID, path));
    }

    public static TagKey<Block> modBlockTag(String path) {
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath(FoodCraft.MOD_ID, path));
    }
}
