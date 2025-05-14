package dev.lasm.foodcraft.init;

import java.util.Optional;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class ModTreeGrowers {
    public static final TreeGrower GRAPEFRUIT;
    public static final TreeGrower CHERRY;
    public static final TreeGrower COCONUT;
    public static final TreeGrower BANANA;
    public static final TreeGrower PEACH;
    public static final TreeGrower PERSIMMON;
    public static final TreeGrower POMEGRANATE;
    public static final TreeGrower HAWTHORN;
    public static final TreeGrower LOQUAT;
    public static final TreeGrower LEMON;
    public static final TreeGrower PAPAYA;
    public static final TreeGrower LONGAN;
    public static final TreeGrower MANGO;
    public static final TreeGrower LITCHI;
    public static final TreeGrower PEAR;
    public static final TreeGrower ORANGE;
    public static final TreeGrower DATE;

    static {
        GRAPEFRUIT = new TreeGrower("foodcraft:grapefruit", Optional.empty(), Optional.of(ModFeatures.GRAPEFRUIT), Optional.empty());
        CHERRY = new TreeGrower("foodcraft:cherry", Optional.empty(), Optional.of(ModFeatures.CHERRY), Optional.empty());
        COCONUT = new TreeGrower("foodcraft:coconut", Optional.empty(), Optional.of(ModFeatures.COCONUT), Optional.empty());
        BANANA = new TreeGrower("foodcraft:banana", Optional.empty(), Optional.of(ModFeatures.BANANA), Optional.empty());
        PEACH = new TreeGrower("foodcraft:peach", Optional.empty(), Optional.of(ModFeatures.PEACH), Optional.empty());
        PERSIMMON = new TreeGrower("foodcraft:persimmon", Optional.empty(), Optional.of(ModFeatures.PERSIMMON), Optional.empty());
        POMEGRANATE = new TreeGrower("foodcraft:pomegranate", Optional.empty(), Optional.of(ModFeatures.POMEGRANATE), Optional.empty());
        HAWTHORN = new TreeGrower("foodcraft:hawthorn", Optional.empty(), Optional.of(ModFeatures.HAWTHORN), Optional.empty());
        LOQUAT = new TreeGrower("foodcraft:loquat", Optional.empty(), Optional.of(ModFeatures.LOQUAT), Optional.empty());
        LEMON = new TreeGrower("foodcraft:lemon", Optional.empty(), Optional.of(ModFeatures.LEMON), Optional.empty());
        PAPAYA = new TreeGrower("foodcraft:papaya", Optional.empty(), Optional.of(ModFeatures.PAPAYA), Optional.empty());
        LONGAN = new TreeGrower("foodcraft:longan", Optional.empty(), Optional.of(ModFeatures.LONGAN), Optional.empty());
        MANGO = new TreeGrower("foodcraft:mango", Optional.empty(), Optional.of(ModFeatures.MANGO), Optional.empty());
        LITCHI = new TreeGrower("foodcraft:litchi", Optional.empty(), Optional.of(ModFeatures.LITCHI), Optional.empty());
        PEAR = new TreeGrower("foodcraft:pear", Optional.empty(), Optional.of(ModFeatures.PEAR), Optional.empty());
        ORANGE = new TreeGrower("foodcraft:orange", Optional.empty(), Optional.of(ModFeatures.ORANGE), Optional.empty());
        DATE = new TreeGrower("foodcraft:date", Optional.empty(), Optional.of(ModFeatures.DATE), Optional.empty());
    }
}
