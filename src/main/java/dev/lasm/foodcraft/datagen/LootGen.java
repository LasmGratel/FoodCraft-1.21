package dev.lasm.foodcraft.datagen;

import dev.lasm.foodcraft.FoodCraft;
import dev.lasm.foodcraft.block.BaseMachineBlock;
import dev.lasm.foodcraft.datagen.BiomeModifiers.NotHolderSetWrapper;
import dev.lasm.foodcraft.init.ModBlocks;
import dev.lasm.foodcraft.init.ModItems;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LocationCheck;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.neoforged.neoforge.common.Tags.Biomes;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.holdersets.AndHolderSet;
import net.neoforged.neoforge.registries.holdersets.NotHolderSet;
import net.neoforged.neoforge.registries.holdersets.OrHolderSet;

public class LootGen {
    public static ResourceKey<LootTable> modLootTable(String path) {
        return ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(FoodCraft.MOD_ID, path));
    }

    public static class Machine implements LootTableSubProvider {
        private HolderLookup.Provider context;

        public Machine(Provider context) {
            this.context = context;
        }

        @Override
        public void generate(BiConsumer<ResourceKey<LootTable>, Builder> output) {
            output.accept(modLootTable("blocks/machine_casing"), LootTable.lootTable().setParamSet(
                LootContextParamSets.BLOCK).withPool(
                LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1.0f))
                    .add(LootItem.lootTableItem(ModItems.MACHINE_CASING.get()))));
            output.accept(modLootTable("blocks/pressure_cooker"), LootTable.lootTable().setParamSet(
                LootContextParamSets.BLOCK).withPool(
                LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1.0f))
                    .add(LootItem.lootTableItem(ModItems.PRESSURE_COOKER.get()))));
            output.accept(modLootTable("blocks/pot"), LootTable.lootTable().setParamSet(
                LootContextParamSets.BLOCK).withPool(
                LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1.0f))
                    .add(LootItem.lootTableItem(ModItems.POT.get()))));
            output.accept(modLootTable("blocks/stove"), LootTable.lootTable().setParamSet(
                LootContextParamSets.BLOCK).withPool(
                LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1.0f))
                    .add(LootItem.lootTableItem(ModItems.STOVE.get()))));
            output.accept(modLootTable("blocks/beverage_making"), LootTable.lootTable().setParamSet(
                LootContextParamSets.BLOCK).withPool(
                LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1.0f))
                    .add(LootItem.lootTableItem(ModItems.BEVERAGE_MAKING.get()))));
            output.accept(modLootTable("blocks/brew_barrel"), LootTable.lootTable().setParamSet(
                LootContextParamSets.BLOCK).withPool(
                LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1.0f))
                    .add(LootItem.lootTableItem(ModItems.BREW_BARREL.get()))));
            output.accept(modLootTable("blocks/chopping_board"), LootTable.lootTable().setParamSet(
                LootContextParamSets.BLOCK).withPool(
                LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1.0f))
                    .add(LootItem.lootTableItem(ModItems.CHOPPING_BOARD.get()))));
            output.accept(modLootTable("blocks/frying_pan"), LootTable.lootTable().setParamSet(
                LootContextParamSets.BLOCK).withPool(
                LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1.0f))
                    .add(LootItem.lootTableItem(ModItems.FRYING_PAN.get()))));
            output.accept(modLootTable("blocks/pan"), LootTable.lootTable().setParamSet(
                LootContextParamSets.BLOCK).withPool(
                LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1.0f))
                    .add(LootItem.lootTableItem(ModItems.PAN.get()))));
            output.accept(modLootTable("blocks/mill"), LootTable.lootTable().setParamSet(
                LootContextParamSets.BLOCK).withPool(
                LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1.0f))
                    .add(LootItem.lootTableItem(ModItems.MILL.get()))));
        }
    }

    public static class Crop implements LootTableSubProvider {
        private HolderLookup.Provider context;

        public Crop(Provider context) {
            this.context = context;
        }

        @Override
        public void generate(BiConsumer<ResourceKey<LootTable>, Builder> output) {
            HolderGetter<Biome> biomeGetter = context.lookup(Registries.BIOME).get();
            var enchantmentGetter = context.lookup(Registries.ENCHANTMENT).get();

            var fortune = enchantmentGetter.getOrThrow(Enchantments.FORTUNE);

            // 获取常用的 Biome Tags/HolderSets
            // 确认这些 TagKey 的正确来源，Biomes.IS_...OVERWORLD 可能不是标准的 TagKey
            // 标准的 TagKey 应该通过 BiomeTags 或自定义 Tag 类获取
            HolderSet.Named<Biome> isForest = biomeGetter.getOrThrow(BiomeTags.IS_FOREST);
            // 假设 IS_WET_OVERWORLD, IS_HOT_OVERWORLD, IS_COLD_OVERWORLD 是有效的 Tag
            HolderSet<Biome> isWet = biomeGetter.getOrThrow(Biomes.IS_WET_OVERWORLD); // 假设 Tag exists
            HolderSet<Biome> isHot = biomeGetter.getOrThrow(Biomes.IS_HOT_OVERWORLD); // 假设 Tag exists
            HolderSet<Biome> isCold = biomeGetter.getOrThrow(Biomes.IS_COLD_OVERWORLD); // 假设 Tag exists
            HolderSet<Biome> isSavanna = biomeGetter.getOrThrow(BiomeTags.IS_SAVANNA);
            //HolderSet.Named<Biome> isTaiga = biomeGetter.getOrThrow(BiomeTags.IS_TAIGA); // 本次耕地作物分析似乎没用到 Taiga
            HolderSet.Named<Biome> isPlains = biomeGetter.getOrThrow(Biomes.IS_PLAINS); // 继续使用 HAS_VILLAGE_PLAINS 作为平原代理
            //HolderSet.Named<Biome> isBeach = biomeGetter.getOrThrow(BiomeTags.IS_BEACH); // 本次耕地作物分析似乎没用到 Beach
            HolderSet<Biome> isJungle = biomeGetter.getOrThrow(BiomeTags.IS_JUNGLE);
            HolderSet<Biome> isRiver = biomeGetter.getOrThrow(BiomeTags.IS_RIVER);
            HolderSet<Biome> isSwamp = biomeGetter.getOrThrow(Biomes.IS_SWAMP);
            HolderSet<Biome> isDesert = biomeGetter.getOrThrow(Biomes.IS_DESERT);
            HolderSet<Biome> isHill = biomeGetter.getOrThrow(Biomes.IS_HILL);
            HolderSet<Biome> isOverworld = biomeGetter.getOrThrow(Biomes.IS_OVERWORLD);

            HolderSet<Biome> temperateOrWarm = new NotHolderSetWrapper<>(isCold); // 温带或暖（不冷）
            HolderSet<Biome> isTemperate = new NotHolderSetWrapper<>(new OrHolderSet<>(List.of(isHot, isCold))); // 温带 (非热非冷)
            HolderSet<Biome> hotAndDry = new AndHolderSet<>(List.of(isHot, new NotHolderSetWrapper<>(isWet))); // 热且不湿

            HolderSet<Biome> temperatePlainsOrForest = new AndHolderSet<>(List.of(new OrHolderSet<>(List.of(isPlains, isForest)), new NotHolderSetWrapper<>(new OrHolderSet<>(List.of(isCold, isWet))))); // 玉米: 温带平原或森林 (平原或森林 且 非冷非湿)
            HolderSet<Biome> warmPlainsForestOrRiver = new AndHolderSet<>(List.of(temperateOrWarm, new OrHolderSet<>(List.of(isPlains, isForest, isRiver)))); // 黄瓜: 暖地区平原、森林或河流
            HolderSet<Biome> warmPlainsOrForest = new AndHolderSet<>(List.of(temperateOrWarm, new OrHolderSet<>(List.of(isPlains, isForest)))); // 茄子/青椒/番茄: 暖地区平原或森林
            HolderSet<Biome> warmSavannaDesertOrPlains = new AndHolderSet<>(List.of(temperateOrWarm, new OrHolderSet<>(List.of(isSavanna, isDesert, isPlains)))); // 花生: 暖地区草原、沙漠或平原
            HolderSet<Biome> warmWetRiverOrSwamp = new AndHolderSet<>(List.of(temperateOrWarm, new OrHolderSet<>(List.of(isWet, isRiver, isSwamp)))); // 水稻/糯米: 暖且湿地区、河流或沼泽
            HolderSet<Biome> warmPlainsOrSavanna = new AndHolderSet<>(List.of(temperateOrWarm, new OrHolderSet<>(List.of(isPlains, isSavanna)))); // 红薯: 暖地区平原或草原
            HolderSet<Biome> coldOrTemperateNotHot = new OrHolderSet<>(List.of(isCold, new AndHolderSet<>(List.of(isTemperate, new NotHolderSetWrapper<>(isHot))))); // 大白菜: 寒冷 或 温带非热
            HolderSet<Biome> warmPlainsSavannaOrForest = new AndHolderSet<>(List.of(temperateOrWarm, new OrHolderSet<>(List.of(isPlains, isSavanna, isForest)))); // 绿豆/黄豆/红豆: 暖地区平原、草原或森林
            HolderSet<Biome> warmPlainsOrForestOrHill = new AndHolderSet<>(List.of(new OrHolderSet<>(List.of(isForest, isPlains, isHill)), isOverworld));

            var builder = LootTable.lootTable().setParamSet(
                LootContextParamSets.BLOCK);

            builder.withPool(
                LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1.0f))
                    .add(AlternativesEntry.alternatives( // 多个可能的掉落项之一
                        // 玉米 - 示例中已存在
                        LootItem.lootTableItem(ModItems.CORN.get())
                            .when(
                                LootItemRandomChanceCondition.randomChance(0.0035f)) // 10% 几率掉落 (示例几率)
                            .when(LocationCheck.checkLocation(LocationPredicate.Builder.location()
                                .setBiomes(temperatePlainsOrForest)))
                            .apply(ApplyBonusCount.addUniformBonusCount(fortune, 2)), // 生物群系条件

                        // 黄瓜
                        LootItem.lootTableItem(ModItems.CUCUMBER.get())
                            .when(LootItemRandomChanceCondition.randomChance(0.0035f)) // 10% 几率
                            .when(LocationCheck.checkLocation(LocationPredicate.Builder.location()
                                .setBiomes(warmPlainsForestOrRiver)))
                            .apply(ApplyBonusCount.addUniformBonusCount(fortune, 2)), // 生物群系条件

                        // 茄子
                        LootItem.lootTableItem(ModItems.EGGPLANT.get())
                            .when(LootItemRandomChanceCondition.randomChance(0.0035f)) // 10% 几率
                            .when(LocationCheck.checkLocation(LocationPredicate.Builder.location()
                                .setBiomes(warmPlainsOrForest)))
                            .apply(ApplyBonusCount.addUniformBonusCount(fortune, 2)), // 生物群系条件

                        // 朝天椒
                        LootItem.lootTableItem(ModItems.FACING_HEAVEN_PEPPER.get())
                            .when(LootItemRandomChanceCondition.randomChance(0.0035f)) // 10% 几率
                            .when(LocationCheck.checkLocation(
                                LocationPredicate.Builder.location().setBiomes(hotAndDry)))
                            .apply(ApplyBonusCount.addUniformBonusCount(fortune, 2)),
                        // 生物群系条件

                        // 青椒
                        LootItem.lootTableItem(ModItems.GREEN_PEPPER.get())
                            .when(LootItemRandomChanceCondition.randomChance(0.0035f)) // 10% 几率
                            .when(LocationCheck.checkLocation(LocationPredicate.Builder.location()
                                .setBiomes(warmPlainsOrForest)))
                            .apply(ApplyBonusCount.addUniformBonusCount(fortune, 2)), // 生物群系条件

                        // 花生
                        LootItem.lootTableItem(ModItems.PEANUT.get())
                            .when(LootItemRandomChanceCondition.randomChance(0.0035f)) // 10% 几率
                            .when(LocationCheck.checkLocation(LocationPredicate.Builder.location()
                                .setBiomes(warmSavannaDesertOrPlains)))
                            .apply(ApplyBonusCount.addUniformBonusCount(fortune, 2)), // 生物群系条件

                        // 水稻
                        LootItem.lootTableItem(ModItems.RICE.get())
                            .when(LootItemRandomChanceCondition.randomChance(0.0035f)) // 10% 几率
                            .when(LocationCheck.checkLocation(LocationPredicate.Builder.location()
                                .setBiomes(warmWetRiverOrSwamp)))
                            .apply(ApplyBonusCount.addUniformBonusCount(fortune, 2)), // 生物群系条件

                        // 糯米
                        LootItem.lootTableItem(ModItems.STICKY_RICE.get())
                            .when(LootItemRandomChanceCondition.randomChance(0.0035f)) // 10% 几率
                            .when(LocationCheck.checkLocation(LocationPredicate.Builder.location()
                                .setBiomes(warmWetRiverOrSwamp)))
                            .apply(ApplyBonusCount.addUniformBonusCount(fortune, 2)), // 生物群系条件

                        // 红薯
                        LootItem.lootTableItem(ModItems.SWEET_POTATO.get())
                            .when(LootItemRandomChanceCondition.randomChance(0.0035f)) // 10% 几率
                            .when(LocationCheck.checkLocation(LocationPredicate.Builder.location()
                                .setBiomes(warmPlainsOrSavanna)))
                            .apply(ApplyBonusCount.addUniformBonusCount(fortune, 2)), // 生物群系条件

                        // 番茄
                        LootItem.lootTableItem(ModItems.TOMATO.get())
                            .when(LootItemRandomChanceCondition.randomChance(0.0035f)) // 10% 几率
                            .when(LocationCheck.checkLocation(LocationPredicate.Builder.location()
                                .setBiomes(warmPlainsOrForest)))
                            .apply(ApplyBonusCount.addUniformBonusCount(fortune, 2)), // 生物群系条件

                        // 白萝卜
                        LootItem.lootTableItem(ModItems.WHITE_RADISH.get())
                            .when(LootItemRandomChanceCondition.randomChance(0.0035f)) // 10% 几率
                            .when(LocationCheck.checkLocation(
                                LocationPredicate.Builder.location().setBiomes(isTemperate)))
                            .apply(ApplyBonusCount.addUniformBonusCount(fortune, 2)),
                        // 生物群系条件

                        // 大白菜
                        LootItem.lootTableItem(ModItems.CABBAGE.get())
                            .when(LootItemRandomChanceCondition.randomChance(0.0035f)) // 10% 几率
                            .when(LocationCheck.checkLocation(LocationPredicate.Builder.location()
                                .setBiomes(coldOrTemperateNotHot)))
                            .apply(ApplyBonusCount.addUniformBonusCount(fortune, 2)), // 生物群系条件

                        LootItem.lootTableItem(ModItems.GRAPE.get())
                            .when(LootItemRandomChanceCondition.randomChance(0.0035f)) // 10% 几率
                            .when(LocationCheck.checkLocation(
                                LocationPredicate.Builder.location().setBiomes(isTemperate)))
                            .apply(ApplyBonusCount.addUniformBonusCount(fortune, 2)),

                        // 草莓
                        LootItem.lootTableItem(ModItems.STRAWBERRY.get())
                            .when(LootItemRandomChanceCondition.randomChance(0.0035f)) // 10% 几率
                            .when(LocationCheck.checkLocation(
                                LocationPredicate.Builder.location().setBiomes(isTemperate)))
                            .apply(ApplyBonusCount.addUniformBonusCount(fortune, 2)),
                        // 生物群系条件

                        // 绿豆
                        LootItem.lootTableItem(ModItems.MUNG_BEAN.get())
                            .when(LootItemRandomChanceCondition.randomChance(0.0035f)) // 10% 几率
                            .when(LocationCheck.checkLocation(LocationPredicate.Builder.location()
                                .setBiomes(warmPlainsSavannaOrForest)))
                            .apply(ApplyBonusCount.addUniformBonusCount(fortune, 2)), // 生物群系条件

                        // 黄豆
                        LootItem.lootTableItem(ModItems.SOYBEAN.get())
                            .when(LootItemRandomChanceCondition.randomChance(0.0035f)) // 10% 几率
                            .when(LocationCheck.checkLocation(LocationPredicate.Builder.location()
                                .setBiomes(warmPlainsSavannaOrForest)))
                            .apply(ApplyBonusCount.addUniformBonusCount(fortune, 2)), // 生物群系条件

                        // 红豆
                        LootItem.lootTableItem(ModItems.ADZUKI_BEAN.get())
                            .when(LootItemRandomChanceCondition.randomChance(0.0035f)) // 10% 几率
                            .when(LocationCheck.checkLocation(LocationPredicate.Builder.location()
                                .setBiomes(warmPlainsSavannaOrForest)))
                            .apply(ApplyBonusCount.addUniformBonusCount(fortune, 2)), // 生物群系条件

                        // 葱
                        LootItem.lootTableItem(ModItems.GREEN_ONION.get())
                            .when(LootItemRandomChanceCondition.randomChance(0.0035f)) // 10% 几率
                            .when(LocationCheck.checkLocation(LocationPredicate.Builder.location()
                                .setBiomes(warmPlainsOrForestOrHill)))
                            .apply(ApplyBonusCount.addUniformBonusCount(fortune, 2)),

                        // 0.008 for other
                        LootItem.lootTableItem(ModItems.CORN.get()).when(LootItemRandomChanceCondition.randomChance(0.0008f)),
                        LootItem.lootTableItem(ModItems.CUCUMBER.get()).when(LootItemRandomChanceCondition.randomChance(0.0008f)),
                        LootItem.lootTableItem(ModItems.EGGPLANT.get()).when(LootItemRandomChanceCondition.randomChance(0.0008f)),
                        LootItem.lootTableItem(ModItems.FACING_HEAVEN_PEPPER.get()).when(LootItemRandomChanceCondition.randomChance(0.0008f)),
                        LootItem.lootTableItem(ModItems.GREEN_PEPPER.get()).when(LootItemRandomChanceCondition.randomChance(0.0008f)),
                        LootItem.lootTableItem(ModItems.PEANUT.get()).when(LootItemRandomChanceCondition.randomChance(0.0008f)),
                        LootItem.lootTableItem(ModItems.RICE.get()).when(LootItemRandomChanceCondition.randomChance(0.0008f)),
                        LootItem.lootTableItem(ModItems.STICKY_RICE.get()).when(LootItemRandomChanceCondition.randomChance(0.0008f)),
                        LootItem.lootTableItem(ModItems.SWEET_POTATO.get()).when(LootItemRandomChanceCondition.randomChance(0.0008f)),
                        LootItem.lootTableItem(ModItems.TOMATO.get()).when(LootItemRandomChanceCondition.randomChance(0.0008f)),
                        LootItem.lootTableItem(ModItems.GRAPE.get()).when(LootItemRandomChanceCondition.randomChance(0.0008f)),
                        LootItem.lootTableItem(ModItems.WHITE_RADISH.get()).when(LootItemRandomChanceCondition.randomChance(0.0008f)),
                        LootItem.lootTableItem(ModItems.CABBAGE.get()).when(LootItemRandomChanceCondition.randomChance(0.0008f)),
                        LootItem.lootTableItem(ModItems.STRAWBERRY.get()).when(LootItemRandomChanceCondition.randomChance(0.0008f)),
                        LootItem.lootTableItem(ModItems.MUNG_BEAN.get()).when(LootItemRandomChanceCondition.randomChance(0.0008f)),
                        LootItem.lootTableItem(ModItems.SOYBEAN.get()).when(LootItemRandomChanceCondition.randomChance(0.0008f)),
                        LootItem.lootTableItem(ModItems.ADZUKI_BEAN.get()).when(LootItemRandomChanceCondition.randomChance(0.0008f))
                    ))
            );
            output.accept(modLootTable("crops"), builder);

            // 1. 丛林木 (Jungle Tree) 掉落水果
            // 包含: 椰子(COCONUT), 香蕉(BANANA), 龙眼(LONGAN), 芒果(MANGO), 荔枝(LITCHI)
            output.accept(modLootTable("jungle_tree_drops"), LootTable.lootTable().setParamSet(
                LootContextParamSets.BLOCK).withPool(
                LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1.0f)) // 固定掉落一次
                    .add(AlternativesEntry.alternatives( // 从以下水果中选择一个尝试掉落
                        LootItem.lootTableItem(ModItems.COCONUT.get())
                            .when(LootItemRandomChanceCondition.randomChance(0.004f)) // 0.4% 几率
                            .apply(ApplyBonusCount.addUniformBonusCount(fortune, 2)), // 示例 Fortune 加成
                        LootItem.lootTableItem(ModItems.PAPAYA.get())
                            .when(LootItemRandomChanceCondition.randomChance(0.004f)) // 0.4% 几率
                            .apply(ApplyBonusCount.addUniformBonusCount(fortune, 2)), // 示例 Fortune 加成
                        LootItem.lootTableItem(ModItems.BANANA.get())
                            .when(LootItemRandomChanceCondition.randomChance(0.004f)) // 0.4% 几率
                            .apply(ApplyBonusCount.addUniformBonusCount(fortune, 2)), // 示例 Fortune 加成
                        LootItem.lootTableItem(ModItems.LONGAN.get())
                            .when(LootItemRandomChanceCondition.randomChance(0.004f)) // 0.4% 几率
                            .apply(ApplyBonusCount.addUniformBonusCount(fortune, 2)), // 示例 Fortune 加成
                        LootItem.lootTableItem(ModItems.MANGO.get())
                            .when(LootItemRandomChanceCondition.randomChance(0.004f)) // 0.4% 几率
                            .apply(ApplyBonusCount.addUniformBonusCount(fortune, 2)), // 示例 Fortune 加成
                        LootItem.lootTableItem(ModItems.LITCHI.get()) // 用户示例中已包含
                            .when(LootItemRandomChanceCondition.randomChance(0.004f)) // 0.4% 几率
                            .apply(ApplyBonusCount.addUniformBonusCount(fortune, 2)) // 示例 Fortune 加成
                        // 可以根据需要添加其他条件，例如生物群系条件
                    ))));

            // 2. 橡木 (Oak Tree) 掉落水果
            // 包含: 葡萄柚(GRAPEFRUIT), 桃子(PEACH), 柿子(PERSIMMON), 山楂(HAWTHORN), 枇杷(LOQUAT), 柠檬(LEMON), 梨(PEAR), 橙子(ORANGE)
            output.accept(modLootTable("oak_tree_drops"), LootTable.lootTable().setParamSet(
                LootContextParamSets.BLOCK).withPool(
                LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1.0f)) // 固定掉落一次
                    .add(AlternativesEntry.alternatives( // 从以下水果中选择一个尝试掉落
                        LootItem.lootTableItem(ModItems.GRAPEFRUIT.get())
                            .when(LootItemRandomChanceCondition.randomChance(0.004f)) // 0.4% 几率
                            .apply(ApplyBonusCount.addUniformBonusCount(fortune, 2)), // 示例 Fortune 加成
                        LootItem.lootTableItem(ModItems.PEACH.get())
                            .when(LootItemRandomChanceCondition.randomChance(0.004f)) // 0.4% 几率
                            .apply(ApplyBonusCount.addUniformBonusCount(fortune, 2)), // 示例 Fortune 加成
                        LootItem.lootTableItem(ModItems.PERSIMMON.get())
                            .when(LootItemRandomChanceCondition.randomChance(0.004f)) // 0.4% 几率
                            .apply(ApplyBonusCount.addUniformBonusCount(fortune, 2)), // 示例 Fortune 加成
                        LootItem.lootTableItem(ModItems.HAWTHORN.get())
                            .when(LootItemRandomChanceCondition.randomChance(0.004f)) // 0.4% 几率
                            .apply(ApplyBonusCount.addUniformBonusCount(fortune, 2)), // 示例 Fortune 加成
                        LootItem.lootTableItem(ModItems.LOQUAT.get())
                            .when(LootItemRandomChanceCondition.randomChance(0.004f)) // 0.4% 几率
                            .apply(ApplyBonusCount.addUniformBonusCount(fortune, 2)), // 示例 Fortune 加成
                        LootItem.lootTableItem(ModItems.LEMON.get())
                            .when(LootItemRandomChanceCondition.randomChance(0.004f)) // 0.4% 几率
                            .apply(ApplyBonusCount.addUniformBonusCount(fortune, 2)), // 示例 Fortune 加成
                        LootItem.lootTableItem(ModItems.PEAR.get())
                            .when(LootItemRandomChanceCondition.randomChance(0.004f)) // 0.4% 几率
                            .apply(ApplyBonusCount.addUniformBonusCount(fortune, 2)), // 示例 Fortune 加成
                        LootItem.lootTableItem(ModItems.ORANGE.get())
                            .when(LootItemRandomChanceCondition.randomChance(0.004f)) // 0.4% 几率
                            .apply(ApplyBonusCount.addUniformBonusCount(fortune, 2)) // 示例 Fortune 加成
                    ))));

            // 3. 合欢木 (Acacia Tree) 掉落水果
            // 包含: 石榴(POMEGRANATE), 红枣(DATE)
            output.accept(modLootTable("acacia_tree_drops"), LootTable.lootTable().setParamSet(
                LootContextParamSets.BLOCK).withPool(
                LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1.0f)) // 固定掉落一次
                    .add(AlternativesEntry.alternatives( // 从以下水果中选择一个尝试掉落
                        LootItem.lootTableItem(ModItems.POMEGRANATE.get())
                            .when(LootItemRandomChanceCondition.randomChance(0.004f)) // 0.4% 几率
                            .apply(ApplyBonusCount.addUniformBonusCount(fortune, 2)), // 示例 Fortune 加成
                        LootItem.lootTableItem(ModItems.DATE.get())
                            .when(LootItemRandomChanceCondition.randomChance(0.004f)) // 0.4% 几率
                            .apply(ApplyBonusCount.addUniformBonusCount(fortune, 2)) // 示例 Fortune 加成
                    ))));

            // 4. 樱桃木 (Cherry Tree) 掉落水果
            // 包含: 樱桃(CHERRY)
            // 注意：原版樱桃树本身就会掉落樱桃，如果你的模组要替换或补充原版掉落，可能需要调整原版 Loot Table
            // 以下代码仅为示例如何为你模组的 CHERRY 物品创建掉落表
            output.accept(modLootTable("cherry_tree_drops"), LootTable.lootTable().setParamSet(
                LootContextParamSets.BLOCK).withPool(
                LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1.0f)) // 固定掉落一次
                    .add(AlternativesEntry.alternatives( // 从以下水果中选择一个尝试掉落
                        LootItem.lootTableItem(ModItems.CHERRY.get())
                            .when(LootItemRandomChanceCondition.randomChance(0.004f)) // 0.4% 几率
                            .apply(ApplyBonusCount.addUniformBonusCount(fortune, 2)) // 示例 Fortune 加成
                    ))));
        }
    }

}
