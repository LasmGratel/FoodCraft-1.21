package dev.lasm.foodcraft.init;

import dev.lasm.foodcraft.api.Colorable;

public enum FruitTypes implements Colorable {
    /**
     * Grape
     * 葡萄
     */
    GRAPE(0x6f2da8),
    /**
     * Pear
     * 梨
     */
    PEAR(0xe5db3b),

    /**
     * Litchi
     * 荔枝
     */
    LITCHI(0xf6edd0),

    /**
     * 龙眼
     * Longan
     */
    LONGAN(0xF6F3C9),

    /**
     * Peach
     * 桃
     */
    PEACH(0xffafaf),

    /**
     * Orange
     * 橘子
     */
    ORANGE(0xf6ae24),

    /**
     * Mango
     * 芒果
     */
    MANGO(0xffd986),

    /**
     * Lemon
     * 柠檬
     */
    LEMON(0xfcf393),

    /**
     * Grapefruit
     * 柚子
     */
    GRAPEFRUIT(0xece382),

    /**
     * Persimmon
     * 柿子
     */
    PERSIMMON(0xeb8c30),

    /**
     * Papaya
     * 木瓜
     */
    PAPAYA(0xf18a25),

    /**
     * 枇杷
     * Loquat
     */
    LOQUAT(0xCE7031),

    /**
     * Hawthorn
     * 山楂
     */
    HAWTHORN(0xea7b0e),

    /**
     * Pomegranate
     * 石榴
     */
    POMEGRANATE(0xf46c30),

    /**
     * Date
     * 红枣
     */
    DATE(0xb57c63),

    /**
     * Cherry
     * 樱桃
     */
    CHERRY(0xfd6d0d),

    /**
     * Coconut
     * 椰子
     */
    COCONUT(0xfcf4d6),

    /**
     * Strawberry
     * 草莓
     */
    STRAWBERRY(0xfc5a8d),

    /**
     * Banana
     * 香蕉
     */
    BANANA(0xf7eb6a);

    private final int color;

    FruitTypes(int color) {
        this.color = color;
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
