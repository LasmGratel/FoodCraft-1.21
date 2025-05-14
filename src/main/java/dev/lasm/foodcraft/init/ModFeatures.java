package dev.lasm.foodcraft.init;

import com.mojang.serialization.MapCodec;
import dev.lasm.foodcraft.FoodCraft;
import dev.lasm.foodcraft.worldgen.FruitBlockFoliagePlacer;
import java.util.function.Supplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModFeatures {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACER_TYPES;

    public static final Supplier<FoliagePlacerType<?>> GRAPEFRUIT_PLACER;
    public static final Supplier<FruitBlockFoliagePlacer> GRAPEFRUIT_PLACER_SUPPLIER = () ->
        new FruitBlockFoliagePlacer(ModBlocks.GRAPEFRUIT.get().defaultBlockState()) {
            @Override
            protected FoliagePlacerType<?> type() {
                return GRAPEFRUIT_PLACER.get();
            }
        };
    public static final MapCodec<FruitBlockFoliagePlacer> GRAPEFRUIT_PLACER_CODEC = MapCodec.unit(GRAPEFRUIT_PLACER_SUPPLIER);
    public static final ResourceKey<ConfiguredFeature<?, ?>> GRAPEFRUIT;

    public static final Supplier<FoliagePlacerType<?>> CHERRY_PLACER;
    public static final Supplier<FruitBlockFoliagePlacer> CHERRY_PLACER_SUPPLIER = () ->
        new FruitBlockFoliagePlacer(ModBlocks.CHERRY.get().defaultBlockState()) {
            @Override
            protected FoliagePlacerType<?> type() {
                return CHERRY_PLACER.get();
            }
        };
    public static final MapCodec<FruitBlockFoliagePlacer> CHERRY_PLACER_CODEC = MapCodec.unit(CHERRY_PLACER_SUPPLIER);
    public static final ResourceKey<ConfiguredFeature<?, ?>> CHERRY;

    public static final Supplier<FoliagePlacerType<?>> COCONUT_PLACER;
    public static final Supplier<FruitBlockFoliagePlacer> COCONUT_PLACER_SUPPLIER = () ->
        new FruitBlockFoliagePlacer(ModBlocks.COCONUT.get().defaultBlockState()) {
            @Override
            protected FoliagePlacerType<?> type() {
                return COCONUT_PLACER.get();
            }
        };
    public static final MapCodec<FruitBlockFoliagePlacer> COCONUT_PLACER_CODEC = MapCodec.unit(COCONUT_PLACER_SUPPLIER);
    public static final ResourceKey<ConfiguredFeature<?, ?>> COCONUT;

    public static final Supplier<FoliagePlacerType<?>> BANANA_PLACER;
    public static final Supplier<FruitBlockFoliagePlacer> BANANA_PLACER_SUPPLIER = () ->
        new FruitBlockFoliagePlacer(ModBlocks.BANANA.get().defaultBlockState()) {
            @Override
            protected FoliagePlacerType<?> type() {
                return BANANA_PLACER.get();
            }
        };
    public static final MapCodec<FruitBlockFoliagePlacer> BANANA_PLACER_CODEC = MapCodec.unit(BANANA_PLACER_SUPPLIER);
    public static final ResourceKey<ConfiguredFeature<?, ?>> BANANA;

    public static final Supplier<FoliagePlacerType<?>> PEACH_PLACER;
    public static final Supplier<FruitBlockFoliagePlacer> PEACH_PLACER_SUPPLIER = () ->
        new FruitBlockFoliagePlacer(ModBlocks.PEACH.get().defaultBlockState()) {
            @Override
            protected FoliagePlacerType<?> type() {
                return PEACH_PLACER.get();
            }
        };
    public static final MapCodec<FruitBlockFoliagePlacer> PEACH_PLACER_CODEC = MapCodec.unit(PEACH_PLACER_SUPPLIER);
    public static final ResourceKey<ConfiguredFeature<?, ?>> PEACH;

    public static final Supplier<FoliagePlacerType<?>> PERSIMMON_PLACER;
    public static final Supplier<FruitBlockFoliagePlacer> PERSIMMON_PLACER_SUPPLIER = () ->
        new FruitBlockFoliagePlacer(ModBlocks.PERSIMMON.get().defaultBlockState()) {
            @Override
            protected FoliagePlacerType<?> type() {
                return PERSIMMON_PLACER.get();
            }
        };
    public static final MapCodec<FruitBlockFoliagePlacer> PERSIMMON_PLACER_CODEC = MapCodec.unit(PERSIMMON_PLACER_SUPPLIER);
    public static final ResourceKey<ConfiguredFeature<?, ?>> PERSIMMON;

    public static final Supplier<FoliagePlacerType<?>> POMEGRANATE_PLACER;
    public static final Supplier<FruitBlockFoliagePlacer> POMEGRANATE_PLACER_SUPPLIER = () ->
        new FruitBlockFoliagePlacer(ModBlocks.POMEGRANATE.get().defaultBlockState()) {
            @Override
            protected FoliagePlacerType<?> type() {
                return POMEGRANATE_PLACER.get();
            }
        };
    public static final MapCodec<FruitBlockFoliagePlacer> POMEGRANATE_PLACER_CODEC = MapCodec.unit(POMEGRANATE_PLACER_SUPPLIER);
    public static final ResourceKey<ConfiguredFeature<?, ?>> POMEGRANATE;

    public static final Supplier<FoliagePlacerType<?>> HAWTHORN_PLACER;
    public static final Supplier<FruitBlockFoliagePlacer> HAWTHORN_PLACER_SUPPLIER = () ->
        new FruitBlockFoliagePlacer(ModBlocks.HAWTHORN.get().defaultBlockState()) {
            @Override
            protected FoliagePlacerType<?> type() {
                return HAWTHORN_PLACER.get();
            }
        };
    public static final MapCodec<FruitBlockFoliagePlacer> HAWTHORN_PLACER_CODEC = MapCodec.unit(HAWTHORN_PLACER_SUPPLIER);
    public static final ResourceKey<ConfiguredFeature<?, ?>> HAWTHORN;

    public static final Supplier<FoliagePlacerType<?>> LOQUAT_PLACER;
    public static final Supplier<FruitBlockFoliagePlacer> LOQUAT_PLACER_SUPPLIER = () ->
        new FruitBlockFoliagePlacer(ModBlocks.LOQUAT.get().defaultBlockState()) {
            @Override
            protected FoliagePlacerType<?> type() {
                return LOQUAT_PLACER.get();
            }
        };
    public static final MapCodec<FruitBlockFoliagePlacer> LOQUAT_PLACER_CODEC = MapCodec.unit(LOQUAT_PLACER_SUPPLIER);
    public static final ResourceKey<ConfiguredFeature<?, ?>> LOQUAT;

    public static final Supplier<FoliagePlacerType<?>> LEMON_PLACER;
    public static final Supplier<FruitBlockFoliagePlacer> LEMON_PLACER_SUPPLIER = () ->
        new FruitBlockFoliagePlacer(ModBlocks.LEMON.get().defaultBlockState()) {
            @Override
            protected FoliagePlacerType<?> type() {
                return LEMON_PLACER.get();
            }
        };
    public static final MapCodec<FruitBlockFoliagePlacer> LEMON_PLACER_CODEC = MapCodec.unit(LEMON_PLACER_SUPPLIER);
    public static final ResourceKey<ConfiguredFeature<?, ?>> LEMON;

    public static final Supplier<FoliagePlacerType<?>> PAPAYA_PLACER;
    public static final Supplier<FruitBlockFoliagePlacer> PAPAYA_PLACER_SUPPLIER = () ->
        new FruitBlockFoliagePlacer(ModBlocks.PAPAYA.get().defaultBlockState()) {
            @Override
            protected FoliagePlacerType<?> type() {
                return PAPAYA_PLACER.get();
            }
        };
    public static final MapCodec<FruitBlockFoliagePlacer> PAPAYA_PLACER_CODEC = MapCodec.unit(PAPAYA_PLACER_SUPPLIER);
    public static final ResourceKey<ConfiguredFeature<?, ?>> PAPAYA;

    public static final Supplier<FoliagePlacerType<?>> LONGAN_PLACER;
    public static final Supplier<FruitBlockFoliagePlacer> LONGAN_PLACER_SUPPLIER = () ->
        new FruitBlockFoliagePlacer(ModBlocks.LONGAN.get().defaultBlockState()) {
            @Override
            protected FoliagePlacerType<?> type() {
                return LONGAN_PLACER.get();
            }
        };
    public static final MapCodec<FruitBlockFoliagePlacer> LONGAN_PLACER_CODEC = MapCodec.unit(LONGAN_PLACER_SUPPLIER);
    public static final ResourceKey<ConfiguredFeature<?, ?>> LONGAN;

    public static final Supplier<FoliagePlacerType<?>> MANGO_PLACER;
    public static final Supplier<FruitBlockFoliagePlacer> MANGO_PLACER_SUPPLIER = () ->
        new FruitBlockFoliagePlacer(ModBlocks.MANGO.get().defaultBlockState()) {
            @Override
            protected FoliagePlacerType<?> type() {
                return MANGO_PLACER.get();
            }
        };
    public static final MapCodec<FruitBlockFoliagePlacer> MANGO_PLACER_CODEC = MapCodec.unit(MANGO_PLACER_SUPPLIER);
    public static final ResourceKey<ConfiguredFeature<?, ?>> MANGO;

    public static final Supplier<FoliagePlacerType<?>> LITCHI_PLACER;
    public static final Supplier<FruitBlockFoliagePlacer> LITCHI_PLACER_SUPPLIER = () ->
        new FruitBlockFoliagePlacer(ModBlocks.LITCHI.get().defaultBlockState()) {
            @Override
            protected FoliagePlacerType<?> type() {
                return LITCHI_PLACER.get();
            }
        };
    public static final MapCodec<FruitBlockFoliagePlacer> LITCHI_PLACER_CODEC = MapCodec.unit(LITCHI_PLACER_SUPPLIER);
    public static final ResourceKey<ConfiguredFeature<?, ?>> LITCHI;

    public static final Supplier<FoliagePlacerType<?>> PEAR_PLACER;
    public static final Supplier<FruitBlockFoliagePlacer> PEAR_PLACER_SUPPLIER = () ->
        new FruitBlockFoliagePlacer(ModBlocks.PEAR.get().defaultBlockState()) {
            @Override
            protected FoliagePlacerType<?> type() {
                return PEAR_PLACER.get();
            }
        };
    public static final MapCodec<FruitBlockFoliagePlacer> PEAR_PLACER_CODEC = MapCodec.unit(PEAR_PLACER_SUPPLIER);
    public static final ResourceKey<ConfiguredFeature<?, ?>> PEAR;

    public static final Supplier<FoliagePlacerType<?>> ORANGE_PLACER;
    public static final Supplier<FruitBlockFoliagePlacer> ORANGE_PLACER_SUPPLIER = () ->
        new FruitBlockFoliagePlacer(ModBlocks.ORANGE.get().defaultBlockState()) {
            @Override
            protected FoliagePlacerType<?> type() {
                return ORANGE_PLACER.get();
            }
        };
    public static final MapCodec<FruitBlockFoliagePlacer> ORANGE_PLACER_CODEC = MapCodec.unit(ORANGE_PLACER_SUPPLIER);
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORANGE;

    public static final Supplier<FoliagePlacerType<?>> DATE_PLACER;
    public static final Supplier<FruitBlockFoliagePlacer> DATE_PLACER_SUPPLIER = () ->
        new FruitBlockFoliagePlacer(ModBlocks.DATE.get().defaultBlockState()) {
            @Override
            protected FoliagePlacerType<?> type() {
                return DATE_PLACER.get();
            }
        };
    public static final MapCodec<FruitBlockFoliagePlacer> DATE_PLACER_CODEC = MapCodec.unit(DATE_PLACER_SUPPLIER);
    public static final ResourceKey<ConfiguredFeature<?, ?>> DATE;

    static {
        FOLIAGE_PLACER_TYPES = DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, FoodCraft.MOD_ID);
        GRAPEFRUIT_PLACER = FOLIAGE_PLACER_TYPES.register("grapefruit_placer", () -> new FoliagePlacerType<>(GRAPEFRUIT_PLACER_CODEC));
        CHERRY_PLACER = FOLIAGE_PLACER_TYPES.register("cherry_placer", () -> new FoliagePlacerType<>(CHERRY_PLACER_CODEC));
        COCONUT_PLACER = FOLIAGE_PLACER_TYPES.register("coconut_placer", () -> new FoliagePlacerType<>(COCONUT_PLACER_CODEC));
        BANANA_PLACER = FOLIAGE_PLACER_TYPES.register("banana_placer", () -> new FoliagePlacerType<>(BANANA_PLACER_CODEC));
        PEACH_PLACER = FOLIAGE_PLACER_TYPES.register("peach_placer", () -> new FoliagePlacerType<>(PEACH_PLACER_CODEC));
        PERSIMMON_PLACER = FOLIAGE_PLACER_TYPES.register("persimmon_placer", () -> new FoliagePlacerType<>(PERSIMMON_PLACER_CODEC));
        POMEGRANATE_PLACER = FOLIAGE_PLACER_TYPES.register("pomegranate_placer", () -> new FoliagePlacerType<>(POMEGRANATE_PLACER_CODEC));
        HAWTHORN_PLACER = FOLIAGE_PLACER_TYPES.register("hawthorn_placer", () -> new FoliagePlacerType<>(HAWTHORN_PLACER_CODEC));
        LOQUAT_PLACER = FOLIAGE_PLACER_TYPES.register("loquat_placer", () -> new FoliagePlacerType<>(LOQUAT_PLACER_CODEC));
        LEMON_PLACER = FOLIAGE_PLACER_TYPES.register("lemon_placer", () -> new FoliagePlacerType<>(LEMON_PLACER_CODEC));
        PAPAYA_PLACER = FOLIAGE_PLACER_TYPES.register("papaya_placer", () -> new FoliagePlacerType<>(PAPAYA_PLACER_CODEC));
        LONGAN_PLACER = FOLIAGE_PLACER_TYPES.register("longan_placer", () -> new FoliagePlacerType<>(LONGAN_PLACER_CODEC));
        MANGO_PLACER = FOLIAGE_PLACER_TYPES.register("mango_placer", () -> new FoliagePlacerType<>(MANGO_PLACER_CODEC));
        LITCHI_PLACER = FOLIAGE_PLACER_TYPES.register("litchi_placer", () -> new FoliagePlacerType<>(LITCHI_PLACER_CODEC));
        PEAR_PLACER = FOLIAGE_PLACER_TYPES.register("pear_placer", () -> new FoliagePlacerType<>(PEAR_PLACER_CODEC));
        ORANGE_PLACER = FOLIAGE_PLACER_TYPES.register("orange_placer", () -> new FoliagePlacerType<>(ORANGE_PLACER_CODEC));
        DATE_PLACER = FOLIAGE_PLACER_TYPES.register("date_placer", () -> new FoliagePlacerType<>(DATE_PLACER_CODEC));

        GRAPEFRUIT = createKey("grapefruit");
        CHERRY = createKey("cherry");
        COCONUT = createKey("coconut");
        BANANA = createKey("banana");
        PEACH = createKey("peach");
        PERSIMMON = createKey("persimmon");
        POMEGRANATE = createKey("pomegranate");
        HAWTHORN = createKey("hawthorn");
        LOQUAT = createKey("loquat");
        LEMON = createKey("lemon");
        PAPAYA = createKey("papaya");
        LONGAN = createKey("longan");
        MANGO = createKey("mango");
        LITCHI = createKey("litchi");
        PEAR = createKey("pear");
        ORANGE = createKey("orange");
        DATE = createKey("date");
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> createKey(String pName) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(FoodCraft.MOD_ID, pName));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(
        BootstrapContext<ConfiguredFeature<?, ?>> pContext, ResourceKey<ConfiguredFeature<?, ?>> pKey, F pFeature, FC pConfig) {
        pContext.register(pKey, new ConfiguredFeature<>(pFeature, pConfig));
    }

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        register(context, GRAPEFRUIT, Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(Blocks.JUNGLE_LOG), new StraightTrunkPlacer(5, 1, 3), BlockStateProvider.simple(Blocks.JUNGLE_LEAVES),
            GRAPEFRUIT_PLACER_SUPPLIER.get(),
            new TwoLayersFeatureSize(1, 0, 2))).build());
        register(context, CHERRY, Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(Blocks.JUNGLE_LOG), new StraightTrunkPlacer(5, 1, 3), BlockStateProvider.simple(Blocks.JUNGLE_LEAVES),
            CHERRY_PLACER_SUPPLIER.get(),
            new TwoLayersFeatureSize(1, 0, 2))).build());
        register(context, COCONUT, Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(Blocks.JUNGLE_LOG), new StraightTrunkPlacer(5, 1, 3), BlockStateProvider.simple(Blocks.JUNGLE_LEAVES),
            COCONUT_PLACER_SUPPLIER.get(),
            new TwoLayersFeatureSize(1, 0, 2))).build());
        register(context, BANANA, Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(Blocks.JUNGLE_LOG), new StraightTrunkPlacer(5, 1, 3), BlockStateProvider.simple(Blocks.JUNGLE_LEAVES),
            BANANA_PLACER_SUPPLIER.get(),
            new TwoLayersFeatureSize(1, 0, 2))).build());
        register(context, PEACH, Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(Blocks.JUNGLE_LOG), new StraightTrunkPlacer(5, 1, 3), BlockStateProvider.simple(Blocks.JUNGLE_LEAVES),
            PEACH_PLACER_SUPPLIER.get(),
            new TwoLayersFeatureSize(1, 0, 2))).build());
        register(context, PERSIMMON, Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(Blocks.JUNGLE_LOG), new StraightTrunkPlacer(5, 1, 3), BlockStateProvider.simple(Blocks.JUNGLE_LEAVES),
            PERSIMMON_PLACER_SUPPLIER.get(),
            new TwoLayersFeatureSize(1, 0, 2))).build());
        register(context, POMEGRANATE, Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(Blocks.JUNGLE_LOG), new StraightTrunkPlacer(5, 1, 3), BlockStateProvider.simple(Blocks.JUNGLE_LEAVES),
            POMEGRANATE_PLACER_SUPPLIER.get(),
            new TwoLayersFeatureSize(1, 0, 2))).build());
        register(context, HAWTHORN, Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(Blocks.JUNGLE_LOG), new StraightTrunkPlacer(5, 1, 3), BlockStateProvider.simple(Blocks.JUNGLE_LEAVES),
            HAWTHORN_PLACER_SUPPLIER.get(),
            new TwoLayersFeatureSize(1, 0, 2))).build());
        register(context, LOQUAT, Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(Blocks.JUNGLE_LOG), new StraightTrunkPlacer(5, 1, 3), BlockStateProvider.simple(Blocks.JUNGLE_LEAVES),
            LOQUAT_PLACER_SUPPLIER.get(),
            new TwoLayersFeatureSize(1, 0, 2))).build());
        register(context, LEMON, Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(Blocks.JUNGLE_LOG), new StraightTrunkPlacer(5, 1, 3), BlockStateProvider.simple(Blocks.JUNGLE_LEAVES),
            LEMON_PLACER_SUPPLIER.get(),
            new TwoLayersFeatureSize(1, 0, 2))).build());
        register(context, PAPAYA, Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(Blocks.JUNGLE_LOG), new StraightTrunkPlacer(5, 1, 3), BlockStateProvider.simple(Blocks.JUNGLE_LEAVES),
            PAPAYA_PLACER_SUPPLIER.get(),
            new TwoLayersFeatureSize(1, 0, 2))).build());
        register(context, LONGAN, Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(Blocks.JUNGLE_LOG), new StraightTrunkPlacer(5, 1, 3), BlockStateProvider.simple(Blocks.JUNGLE_LEAVES),
            LONGAN_PLACER_SUPPLIER.get(),
            new TwoLayersFeatureSize(1, 0, 2))).build());
        register(context, MANGO, Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(Blocks.JUNGLE_LOG), new StraightTrunkPlacer(5, 1, 3), BlockStateProvider.simple(Blocks.JUNGLE_LEAVES),
            MANGO_PLACER_SUPPLIER.get(),
            new TwoLayersFeatureSize(1, 0, 2))).build());
        register(context, LITCHI, Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(Blocks.JUNGLE_LOG), new StraightTrunkPlacer(5, 1, 3), BlockStateProvider.simple(Blocks.JUNGLE_LEAVES),
            LITCHI_PLACER_SUPPLIER.get(),
            new TwoLayersFeatureSize(1, 0, 2))).build());
        register(context, PEAR, Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(Blocks.JUNGLE_LOG), new StraightTrunkPlacer(5, 1, 3), BlockStateProvider.simple(Blocks.JUNGLE_LEAVES),
            PEAR_PLACER_SUPPLIER.get(),
            new TwoLayersFeatureSize(1, 0, 2))).build());
        register(context, ORANGE, Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(Blocks.JUNGLE_LOG), new StraightTrunkPlacer(5, 1, 3), BlockStateProvider.simple(Blocks.JUNGLE_LEAVES),
            ORANGE_PLACER_SUPPLIER.get(),
            new TwoLayersFeatureSize(1, 0, 2))).build());
        register(context, DATE, Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(Blocks.JUNGLE_LOG), new StraightTrunkPlacer(5, 1, 3), BlockStateProvider.simple(Blocks.JUNGLE_LEAVES),
            DATE_PLACER_SUPPLIER.get(),
            new TwoLayersFeatureSize(1, 0, 2))).build());
    }
}
