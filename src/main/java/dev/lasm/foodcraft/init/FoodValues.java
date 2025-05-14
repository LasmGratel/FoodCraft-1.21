package dev.lasm.foodcraft.init;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class FoodValues {
    public static final FoodProperties RICE_PORRIDGE = new FoodProperties.Builder().nutrition(7).saturationModifier(0.4f).build();
    public static final FoodProperties EGG_CUSTARD = new FoodProperties.Builder().nutrition(8).saturationModifier(0.4f).build();
    public static final FoodProperties CARROT_SHREDDED = new FoodProperties.Builder().nutrition(1).build();

    // Plants
    public static final FoodProperties PLANT = new FoodProperties.Builder().nutrition(1).build();

    public static final FoodProperties FRUIT = new FoodProperties.Builder().nutrition(2).saturationModifier(0.1f).build();

    public static final FoodProperties FRUIT_ICE_CREAM = new FoodProperties.Builder().nutrition(9).saturationModifier(0.3f).build();
    public static final FoodProperties FRUIT_JAM = new FoodProperties.Builder().nutrition(9).saturationModifier(0.3f).build();
    public static final FoodProperties LIQUEUR = new FoodProperties.Builder().nutrition(8).saturationModifier(0.3f).alwaysEdible().build();

    public static final FoodProperties MUSHROOM_EGG_SOUP = new FoodProperties.Builder().nutrition(7).saturationModifier(0.4f).build();
    public static final FoodProperties MUSHROOM_CHICKEN_SOUP = new FoodProperties.Builder().nutrition(9).saturationModifier(0.4f).build();
    public static final FoodProperties NOODLES = new FoodProperties.Builder().nutrition(7).saturationModifier(0.4f).build();
    public static final FoodProperties CROSSING_BRIDGE_NOODLES = new FoodProperties.Builder().nutrition(7).saturationModifier(0.4f).build();
    public static final FoodProperties PASTA = new FoodProperties.Builder().nutrition(7).saturationModifier(0.4f).build();
    public static final FoodProperties PRESERVED_EGG_PORRIDGE = new FoodProperties.Builder().nutrition(7).saturationModifier(0.4f).build();
    public static final FoodProperties CHANGFEN = new FoodProperties.Builder().nutrition(7).saturationModifier(0.4f).build();
    public static final FoodProperties LABA_PORRIDGE = new FoodProperties.Builder().nutrition(9).saturationModifier(0.4f).build();
    public static final FoodProperties TOMATO_FRIED_EGG_RICE = new FoodProperties.Builder().nutrition(18).saturationModifier(0.4f).build();
    public static final FoodProperties DISANXIAN_RICE = new FoodProperties.Builder().nutrition(18).saturationModifier(0.4f).build();
    public static final FoodProperties SHREDDED_PORK_RICE = new FoodProperties.Builder().nutrition(18).saturationModifier(0.4f).build();
    public static final FoodProperties KUNG_PAO_CHICKEN_RICE = new FoodProperties.Builder().nutrition(18).saturationModifier(0.4f).build();
    public static final FoodProperties FRIED_SHREDDED_POTATO_RICE = new FoodProperties.Builder().nutrition(18).saturationModifier(0.4f).build();
    public static final FoodProperties FISH_HEAD_RICE = new FoodProperties.Builder().nutrition(18).saturationModifier(0.4f).build();
    public static final FoodProperties MAPO_TOFU_RICE = new FoodProperties.Builder().nutrition(16).saturationModifier(0.4f).build();
    public static final FoodProperties PORK_BRAISED_IN_BROWN_SAUCE_RICE = new FoodProperties.Builder().nutrition(18).saturationModifier(0.4f).build();
    public static final FoodProperties TWICE_COOKED_PORK_SLICES_RICE = new FoodProperties.Builder().nutrition(18).saturationModifier(0.4f).build();
    public static final FoodProperties ORLEAN_CHICKEN_WINGS_RICE = new FoodProperties.Builder().nutrition(20).saturationModifier(0.4f).build();
    public static final FoodProperties SLICED_COLD_CHICKEN = new FoodProperties.Builder().nutrition(18).saturationModifier(0.4f).build();
    public static final FoodProperties SCALLION_CHICKEN = new FoodProperties.Builder().nutrition(18).saturationModifier(0.4f).build();
    public static final FoodProperties CHICKEN_WITH_CHILI_SAUCE = new FoodProperties.Builder().nutrition(18).saturationModifier(0.4f).build();
    public static final FoodProperties PEPPERY_CHICKEN = new FoodProperties.Builder().nutrition(18).saturationModifier(0.4f).build();
    public static final FoodProperties STEAMED_FISH = new FoodProperties.Builder().nutrition(18).saturationModifier(0.4f).build();
    public static final FoodProperties SPICY_FISH = new FoodProperties.Builder().nutrition(18).saturationModifier(0.4f).build();
    public static final FoodProperties BOILED_FISH_WITH_PICKLED_CABBAGE = new FoodProperties.Builder().nutrition(18).saturationModifier(0.4f).build();
    public static final FoodProperties COKE_CHICKEN_RICE = new FoodProperties.Builder().nutrition(20).saturationModifier(0.4f).build();
    public static final FoodProperties CURRY_RICE = new FoodProperties.Builder().nutrition(18).saturationModifier(0.4f).build();
    public static final FoodProperties BOILED_BEEF = new FoodProperties.Builder().nutrition(18).saturationModifier(0.4f).build();
    public static final FoodProperties PORK_FRIED_PASTA = new FoodProperties.Builder().nutrition(18).saturationModifier(0.4f).build();
    public static final FoodProperties BEEF_FRIED_PASTA = new FoodProperties.Builder().nutrition(18).saturationModifier(0.4f).build();
    public static final FoodProperties CHICKEN_FRIED_PASTA = new FoodProperties.Builder().nutrition(18).saturationModifier(0.4f).build();
    public static final FoodProperties JAPANESE_TOFU_RICE = new FoodProperties.Builder().nutrition(18).saturationModifier(0.4f).build();
    public static final FoodProperties GOLDEN_GRAPE = new FoodProperties.Builder().nutrition(20).saturationModifier(0.4f).build();
    public static final FoodProperties COOKED_RICE = new FoodProperties.Builder().nutrition(3).saturationModifier(0.4f).build();
    public static final FoodProperties CHICKEN_LEG = new FoodProperties.Builder().nutrition(3).saturationModifier(0.4f).build();
    public static final FoodProperties CHICKEN_WING = new FoodProperties.Builder().nutrition(4).saturationModifier(0.4f).build();
    public static final FoodProperties POACHED_EGG = new FoodProperties.Builder().nutrition(5).saturationModifier(0.4f).build();
    public static final FoodProperties PANCAKES = new FoodProperties.Builder().nutrition(5).saturationModifier(0.4f).build();
    public static final FoodProperties DUMPLING = new FoodProperties.Builder().nutrition(8).saturationModifier(0.4f).build();
    public static final FoodProperties FRIED_DUMPLING = new FoodProperties.Builder().nutrition(10).saturationModifier(0.4f).build();
    public static final FoodProperties CHOCOLATE_BAR = new FoodProperties.Builder().nutrition(4).saturationModifier(0.4f).build();
    public static final FoodProperties TOFU = new FoodProperties.Builder().nutrition(4).saturationModifier(0.4f).build();
    public static final FoodProperties DEHYDRATED_TOFU = new FoodProperties.Builder().nutrition(6).saturationModifier(0.4f).build();
    public static final FoodProperties FRIED_POTATO_CHIPS = new FoodProperties.Builder().nutrition(7).saturationModifier(0.4f).build();
    public static final FoodProperties ZONGZI = new FoodProperties.Builder().nutrition(8).saturationModifier(0.4f).build();
    public static final FoodProperties TANGYUAN = new FoodProperties.Builder().nutrition(8).saturationModifier(0.4f).build();
    public static final FoodProperties FRIED_CHICKEN = new FoodProperties.Builder().nutrition(6).saturationModifier(0.4f).build();
    public static final FoodProperties FRENCH_FRIES = new FoodProperties.Builder().nutrition(7).saturationModifier(0.4f).build();
    public static final FoodProperties ORLEAN_CHICKEN_WINGS = new FoodProperties.Builder().nutrition(8).saturationModifier(0.4f).build();
    public static final FoodProperties POPCORN_CHICKEN = new FoodProperties.Builder().nutrition(4).saturationModifier(0.4f).build();
    public static final FoodProperties ORIGINAL_CHICKEN = new FoodProperties.Builder().nutrition(8).saturationModifier(0.4f).build();
    public static final FoodProperties DOUGH_TWIST = new FoodProperties.Builder().nutrition(7).saturationModifier(0.4f).build();
    public static final FoodProperties NIANGAO = new FoodProperties.Builder().nutrition(8).saturationModifier(0.4f).build();
    public static final FoodProperties CHUNJUAN = new FoodProperties.Builder().nutrition(8).saturationModifier(0.4f).build();
    public static final FoodProperties FRIED_TOFU = new FoodProperties.Builder().nutrition(6).saturationModifier(0.4f).build();
    public static final FoodProperties FRIED_NIANGAO = new FoodProperties.Builder().nutrition(10).saturationModifier(0.4f).build();
    public static final FoodProperties POTATO_CHIPS = new FoodProperties.Builder().nutrition(7).saturationModifier(0.4f).build();
    public static final FoodProperties FRIED_BREAD = new FoodProperties.Builder().nutrition(7).saturationModifier(0.4f).build();
    public static final FoodProperties FRIED_CHUNJUAN = new FoodProperties.Builder().nutrition(10).saturationModifier(0.4f).build();
    public static final FoodProperties FRIED_DOUGH_TWIST = new FoodProperties.Builder().nutrition(10).saturationModifier(0.4f).build();
    public static final FoodProperties FRIED_CHICKEN_LEG = new FoodProperties.Builder().nutrition(5).saturationModifier(0.4f).build();
    public static final FoodProperties MOONCAKE = new FoodProperties.Builder().nutrition(8).saturationModifier(0.4f).build();
    public static final FoodProperties STEAMED_BUNS = new FoodProperties.Builder().nutrition(4).saturationModifier(0.4f).build();
    public static final FoodProperties SAUERKRAUT_CAKE = new FoodProperties.Builder().nutrition(7).saturationModifier(0.4f).build();
    public static final FoodProperties YOUTIAO = new FoodProperties.Builder().nutrition(8).saturationModifier(0.4f).build();
    public static final FoodProperties CHILI_TOFU_STRIP = new FoodProperties.Builder().nutrition(4).saturationModifier(0.4f).build();
    public static final FoodProperties WALNUT_SHORTBREAD = new FoodProperties.Builder().nutrition(8).saturationModifier(0.4f).build();
    public static final FoodProperties AICI = new FoodProperties.Builder().nutrition(8).saturationModifier(0.4f).build();
    public static final FoodProperties CIBA = new FoodProperties.Builder().nutrition(7).saturationModifier(0.4f).build();
    public static final FoodProperties SAUSAGE = new FoodProperties.Builder().nutrition(6).saturationModifier(0.4f).build();
    public static final FoodProperties HOT_DOG = new FoodProperties.Builder().nutrition(8).saturationModifier(0.4f).build();
    public static final FoodProperties BOLONGNA = new FoodProperties.Builder().nutrition(8).saturationModifier(0.4f).build();
    public static final FoodProperties BACON = new FoodProperties.Builder().nutrition(9).saturationModifier(0.4f).build();
    public static final FoodProperties FRIED_SAUSAGE = new FoodProperties.Builder().nutrition(8).saturationModifier(0.4f).build();
    public static final FoodProperties CHEESE = new FoodProperties.Builder().nutrition(5).saturationModifier(0.4f).build();
    public static final FoodProperties PIZZA = new FoodProperties.Builder().nutrition(12).saturationModifier(0.4f).build();
    public static final FoodProperties HAMBURGER = new FoodProperties.Builder().nutrition(12).saturationModifier(0.4f).build();
    public static final FoodProperties SQUID_MEAT = new FoodProperties.Builder().nutrition(4).saturationModifier(0.4f).build();
    public static final FoodProperties COOKED_SQUID_MEAT = new FoodProperties.Builder().nutrition(6).saturationModifier(0.4f).build();
    public static final FoodProperties SQUID_SLICE = new FoodProperties.Builder().nutrition(1).saturationModifier(0.4f).build();
    public static final FoodProperties COOKED_SWEET_POTATO = new FoodProperties.Builder().nutrition(8).saturationModifier(0.4f).build();
    public static final FoodProperties COOKED_CORN = new FoodProperties.Builder().nutrition(6).saturationModifier(0.4f).build();
    public static final FoodProperties POPCORN = new FoodProperties.Builder().nutrition(6).saturationModifier(0.4f).build();
    public static final FoodProperties COOKIE = new FoodProperties.Builder().nutrition(6).saturationModifier(0.4f).build();
    public static final FoodProperties SMILEY_COOKIE = new FoodProperties.Builder().nutrition(7).saturationModifier(0.4f).build();
    public static final FoodProperties SOYMILK = new FoodProperties.Builder().nutrition(4).saturationModifier(0.4f).build();
    public static final FoodProperties HOT_CHOCOLATE = new FoodProperties.Builder().nutrition(7).saturationModifier(0.4f).build();
    public static final FoodProperties CHOCOLATE_MILK = new FoodProperties.Builder().nutrition(7).saturationModifier(0.4f).build();
    public static final FoodProperties CARROT_JUICE = new FoodProperties.Builder().nutrition(7).saturationModifier(0.4f).build();
    public static final FoodProperties APPLE_JUICE = new FoodProperties.Builder().nutrition(7).saturationModifier(0.4f).build();
    public static final FoodProperties GOLDEN_GRAPE_JUICE = new FoodProperties.Builder().nutrition(20).saturationModifier(0.4f).build();
    public static final FoodProperties GOLDEN_APPLE_JUICE = new FoodProperties.Builder().nutrition(20).saturationModifier(0.4f).build();
    public static final FoodProperties SOYBEAN_MILK = new FoodProperties.Builder().nutrition(7).saturationModifier(0.4f).build();
    public static final FoodProperties COLA = new FoodProperties.Builder().nutrition(7).saturationModifier(0.4f).build();
    public static final FoodProperties FANTA = new FoodProperties.Builder().nutrition(7).saturationModifier(0.4f).build();
    public static final FoodProperties SPRITE = new FoodProperties.Builder().nutrition(7).saturationModifier(0.4f).build();
    public static final FoodProperties MELON_JUICE = new FoodProperties.Builder().nutrition(7).saturationModifier(0.4f).build();
    public static final FoodProperties TEA = new FoodProperties.Builder().nutrition(6).saturationModifier(0.4f).build();
    public static final FoodProperties MILK_TEA = new FoodProperties.Builder().nutrition(7).saturationModifier(0.4f).build();
    public static final FoodProperties COFFEE = new FoodProperties.Builder().nutrition(7).saturationModifier(0.4f).build();
    public static final FoodProperties SUGAR_WITH_WATER = new FoodProperties.Builder().nutrition(2).saturationModifier(0.4f).build();
    public static final FoodProperties COCONUT_JUICE_MILK = new FoodProperties.Builder().nutrition(8).saturationModifier(0.4f).build();

    public static final FoodProperties WINE = liqueurFood().build();
    public static final FoodProperties SPIRIT = liqueurFood().build();

    public static final FoodProperties GRAPE_LIQUEUR = liqueurFood().build();
    public static final FoodProperties LITCHI_LIQUEUR = liqueurFood().build();
    public static final FoodProperties PEACH_LIQUEUR = liqueurFood().build();
    public static final FoodProperties MANGO_LIQUEUR = liqueurFood().build();
    public static final FoodProperties LEMON_LIQUEUR = liqueurFood().build();
    public static final FoodProperties POMEGRANATE_LIQUEUR = liqueurFood().build();

    public static final FoodProperties APPLE_LIQUEUR = liqueurFood().build();
    public static final FoodProperties GOLDEN_GRAPE_LIQUEUR = liqueurFood()
        .effect(() -> new MobEffectInstance(MobEffects.JUMP, 1800, 2), 0.5f)
        .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1800, 2), 0.5f)
        .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 1800, 2), 0.5f)
        .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 1800, 2), 0.5f)
        .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1800, 2), 0.5f)
        .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1800, 2), 0.5f)
        .build();

    public static final FoodProperties GOLDEN_APPLE_LIQUEUR = liqueurFood()
        .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 1800, 2), 0.5f)
        .effect(() -> new MobEffectInstance(MobEffects.INVISIBILITY, 900, 2), 0.5f)
        .effect(() -> new MobEffectInstance(MobEffects.WATER_BREATHING, 1800, 2), 0.5f)
        .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1800, 2), 0.5f)
        .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 1800, 2), 0.5f)
        .build();

    public static FoodProperties.Builder liqueurFood() {
        return new FoodProperties.Builder().alwaysEdible().nutrition(5).saturationModifier(0.3f);
    }
}
