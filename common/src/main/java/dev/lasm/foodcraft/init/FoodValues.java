package dev.lasm.foodcraft.init;

import net.minecraft.world.food.FoodProperties;

public class FoodValues {
    public static final FoodProperties RICE_PORRIDGE = new FoodProperties.Builder().nutrition(7).saturationMod(0.4f).build();
    public static final FoodProperties EGG_CUSTARD = new FoodProperties.Builder().nutrition(8).saturationMod(0.4f).build();
    public static final FoodProperties CARROT_SHREDDED = new FoodProperties.Builder().nutrition(1).build();

    // Plants
    public static final FoodProperties PLANT = new FoodProperties.Builder().nutrition(1).build();

    public static final FoodProperties FRUIT = new FoodProperties.Builder().nutrition(2).saturationMod(0.4f).build();

    public static final FoodProperties FRUIT_ICE_CREAM = new FoodProperties.Builder().nutrition(9).saturationMod(0.3f).build();
    public static final FoodProperties FRUIT_JAM = new FoodProperties.Builder().nutrition(9).saturationMod(0.3f).build();
    public static final FoodProperties LIQUEUR = new FoodProperties.Builder().nutrition(8).saturationMod(0.3f).alwaysEat().build();
}
