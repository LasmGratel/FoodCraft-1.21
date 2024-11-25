package dev.lasm.foodcraft.init;

import dev.lasm.foodcraft.api.Colorable;

public enum VegetableTypes implements Colorable {
    /**
     * 茄子
     * Eggplant
     */
    EGGPLANT(0xc300ff),

    /**
     * 黄瓜
     * Cucumber
     */
    CUCUMBER(0x06ad1a),

    /**
     * 白菜
     * Cabbage
     */
    CABBAGE(0xe2ffe6),

    /**
     * 莴苣(生菜)
     * Lettuce
     */
    LETTUCE(0x98f9a7),

    /**
     * 蒿子杆
     * Artemisia stalk
     */
    ARTEMISIA_STALK(0x61f979),

    /**
     * 菠菜
     * Spinach
     */
    SPINACH(0x027714),

    /**
     * 芹菜
     * Celery
     */
    CELERY(0x15541f),

    /**
     * 番茄
     * Tomato
     */
    TOMATO(0xff2121),

    /**
     * 大米
     * Rice
     */
    RICE(0xeeeedd),

    /**
     * 糯米
     * Sticky rice
     */
    STICKY_RICE(0xffffdd),

    /**
     * 黑米
     * Black rice
     */
    BLACK_RICE(0xaaaaaa),

    /**
     * 玉米
     * Corn
     */
    CORN(0xffff99),

    /**
     * 花生
     * Peanut
     */
    PEANUT(0xeedd00),

    /**
     * 水萝卜(小萝卜)
     * Radish
     */
    RADISH(0xff4444),

    /**
     * 白萝卜
     * White radish
     */
    WHITE_RADISH(0xffdddd),

    /**
     * 红椒
     * Red pepper
     */
    RED_PEPPER(0xFF7F39),

    /**
     * 青椒
     * Green pepper
     */
    GREEN_PEPPER(0x75dd09),

    /**
     * 朝天椒
     * Facing heaven pepper
     */
    FACING_HEAVEN_PEPPER(0xb21816),

    /**
     * 胡椒
     * Pepper
     */
    PEPPER(0x463337),

    /**
     * 花椒
     * Zanthoxylum
     */
    ZANTHOXYLUM(0x374832),

    /**
     * 红薯
     * Sweet potato
     */
    SWEET_POTATO(0xFFA265),

    /**
     * 白薯
     * White sweet potato
     */
    WHITE_SWEET_POTATO(0xFFF3EC),

    /**
     * 紫薯
     * Purple sweet potato
     */
    PURPLE_SWEET_POTATO(0xC56C97),

    /**
     * 芝麻
     * Sesame
     */
    SESAME(0xFFF973),

    /**
     * 蒜
     * Garlic
     */
    GARLIC(0xFBF6FF),

    /**
     * 姜
     * Ginger
     */
    GINGER(0xDBB121),

    /**
     * 洋葱
     * Onion
     */
    ONION(0xDCD8AE),

    /**
     * 葱
     * Green onion
     */
    GREEN_ONION(0x84F941);

    private final int color;

    VegetableTypes(int color) {
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
