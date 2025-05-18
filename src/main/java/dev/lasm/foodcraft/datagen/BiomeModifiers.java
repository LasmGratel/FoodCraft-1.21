package dev.lasm.foodcraft.datagen;

import java.util.List;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderOwner;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.Tags.Biomes;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers.AddFeaturesBiomeModifier;
import net.neoforged.neoforge.registries.NeoForgeRegistries.Keys;
import net.neoforged.neoforge.registries.holdersets.AndHolderSet;
import net.neoforged.neoforge.registries.holdersets.NotHolderSet;
import net.neoforged.neoforge.registries.holdersets.OrHolderSet;

public class BiomeModifiers {
    private static ResourceKey<BiomeModifier> createKey(String path) {
        return ResourceKey.create(
            Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath("foodcraft", path));
    }

    public static class NotHolderSetWrapper<T> extends NotHolderSet<T> {
        public NotHolderSetWrapper(HolderSet<T> value) {
            super(null, value);
        }

        public boolean canSerializeIn(HolderOwner<T> holderOwner) {
            return true;
        }
    }

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        HolderGetter<Biome> biomeGetter = context.lookup(Registries.BIOME);
        HolderGetter<PlacedFeature> placedGetter = context.lookup(Registries.PLACED_FEATURE);

        HolderSet.Named<Biome> isForest = biomeGetter.getOrThrow(BiomeTags.IS_FOREST);
        HolderSet.Named<Biome> isWet = biomeGetter.getOrThrow(Biomes.IS_WET_OVERWORLD);
        HolderSet.Named<Biome> isHot = biomeGetter.getOrThrow(Biomes.IS_HOT_OVERWORLD);
        HolderSet.Named<Biome> isCold = biomeGetter.getOrThrow(Biomes.IS_COLD_OVERWORLD);
        HolderSet.Named<Biome> isSavanna = biomeGetter.getOrThrow(BiomeTags.IS_SAVANNA);
        HolderSet.Named<Biome> isTaiga = biomeGetter.getOrThrow(BiomeTags.IS_TAIGA);
        HolderSet.Named<Biome> isPlains = biomeGetter.getOrThrow(Biomes.IS_PLAINS);
        HolderSet.Named<Biome> isBeach = biomeGetter.getOrThrow(BiomeTags.IS_BEACH);
        HolderSet.Named<Biome> isJungle = biomeGetter.getOrThrow(BiomeTags.IS_JUNGLE);

        HolderSet<Biome> hotAndDry = new AndHolderSet<>(List.of(isHot, new NotHolderSetWrapper<>(isWet))); // 热且不湿
        HolderSet<Biome> warmAndLittleWet = new AndHolderSet<>(List.of(isHot, new NotHolderSetWrapper<>(isCold))); // 暖（热）且不冷
        HolderSet<Biome> temperateForest = new AndHolderSet<>(List.of(isForest, new NotHolderSetWrapper<>(new OrHolderSet<>(List.of(isHot, isCold))))); // 温带森林 (森林且非热非冷)
        HolderSet<Biome> tropical = isJungle; // 热带丛林
        HolderSet<Biome> temperateOrWarmPlains = new OrHolderSet<>(List.of(isPlains, warmAndLittleWet)); // 温带或暖（热）平原
        HolderSet<Biome> coldForestOrTaiga = new OrHolderSet<>(List.of(isTaiga, new AndHolderSet<>(List.of(isForest, isCold)))); // 冷森林或针叶林
        HolderSet<Biome> warmAndSlightlyWetForest = new AndHolderSet<>(List.of(isForest, isHot, isWet)); // 暖（热）且略湿的森林 (森林且热且湿)
        HolderSet<Biome> tropicalOrSubtropical = new OrHolderSet<>(List.of(isJungle, hotAndDry)); // 热带或亚热带 (丛林 或 热且不湿)
        HolderSet<Biome> savannaLike = isSavanna; // 热带草原类似区域
        HolderSet<Biome> temperateOrWarm = new NotHolderSetWrapper<>(isCold); // 温带或暖（不冷）
        HolderSet<Biome> coastalAreas = isBeach; // 沿海区域

        context.register(createKey("pomegranate_placed"), new AddFeaturesBiomeModifier(hotAndDry, HolderSet.direct(placedGetter.getOrThrow(ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath("foodcraft", "pomegranate_placed")))), Decoration.VEGETAL_DECORATION)); // 调整为 hotAndDry
        context.register(createKey("cherry_placed"), new AddFeaturesBiomeModifier(temperateForest, HolderSet.direct(placedGetter.getOrThrow(ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath("foodcraft", "cherry_placed")))), Decoration.VEGETAL_DECORATION));
        context.register(createKey("coconut_placed"), new AddFeaturesBiomeModifier(tropicalOrSubtropical, HolderSet.direct(placedGetter.getOrThrow(ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath("foodcraft", "coconut_placed")))), Decoration.VEGETAL_DECORATION));
        context.register(createKey("banana_placed"), new AddFeaturesBiomeModifier(tropical, HolderSet.direct(placedGetter.getOrThrow(ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath("foodcraft", "banana_placed")))), Decoration.VEGETAL_DECORATION));
        context.register(createKey("peach_placed"), new AddFeaturesBiomeModifier(temperateOrWarmPlains, HolderSet.direct(placedGetter.getOrThrow(ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath("foodcraft", "peach_placed")))), Decoration.VEGETAL_DECORATION));
        context.register(createKey("persimmon_placed"), new AddFeaturesBiomeModifier(temperateForest, HolderSet.direct(placedGetter.getOrThrow(ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath("foodcraft", "persimmon_placed")))), Decoration.VEGETAL_DECORATION));
        context.register(createKey("hawthorn_placed"), new AddFeaturesBiomeModifier(temperateOrWarmPlains, HolderSet.direct(placedGetter.getOrThrow(ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath("foodcraft", "hawthorn_placed")))), Decoration.VEGETAL_DECORATION));
        context.register(createKey("loquat_placed"), new AddFeaturesBiomeModifier(warmAndLittleWet, HolderSet.direct(placedGetter.getOrThrow(ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath("foodcraft", "loquat_placed")))), Decoration.VEGETAL_DECORATION));
        context.register(createKey("lemon_placed"), new AddFeaturesBiomeModifier(warmAndSlightlyWetForest, HolderSet.direct(placedGetter.getOrThrow(ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath("foodcraft", "lemon_placed")))), Decoration.VEGETAL_DECORATION));
        context.register(createKey("papaya_placed"), new AddFeaturesBiomeModifier(tropical, HolderSet.direct(placedGetter.getOrThrow(ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath("foodcraft", "papaya_placed")))), Decoration.VEGETAL_DECORATION));
        context.register(createKey("longan_placed"), new AddFeaturesBiomeModifier(tropicalOrSubtropical, HolderSet.direct(placedGetter.getOrThrow(ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath("foodcraft", "longan_placed")))), Decoration.VEGETAL_DECORATION));
        context.register(createKey("mango_placed"), new AddFeaturesBiomeModifier(savannaLike, HolderSet.direct(placedGetter.getOrThrow(ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath("foodcraft", "mango_placed")))), Decoration.VEGETAL_DECORATION)); // 调整为 savannaLike
        context.register(createKey("litchi_placed"), new AddFeaturesBiomeModifier(warmAndSlightlyWetForest, HolderSet.direct(placedGetter.getOrThrow(ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath("foodcraft", "litchi_placed")))), Decoration.VEGETAL_DECORATION));
        context.register(createKey("pear_placed"), new AddFeaturesBiomeModifier(temperateForest, HolderSet.direct(placedGetter.getOrThrow(ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath("foodcraft", "pear_placed")))), Decoration.VEGETAL_DECORATION));
        context.register(createKey("orange_placed"), new AddFeaturesBiomeModifier(warmAndSlightlyWetForest, HolderSet.direct(placedGetter.getOrThrow(ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath("foodcraft", "orange_placed")))), Decoration.VEGETAL_DECORATION));
        context.register(createKey("date_placed"), new AddFeaturesBiomeModifier(hotAndDry, HolderSet.direct(placedGetter.getOrThrow(ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath("foodcraft", "date_placed")))), Decoration.VEGETAL_DECORATION));

    }
}
