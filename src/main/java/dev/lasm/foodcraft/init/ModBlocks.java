package dev.lasm.foodcraft.init;

import dev.lasm.foodcraft.FoodCraft;
import dev.lasm.foodcraft.block.BeverageMakingMachineBlock;
import dev.lasm.foodcraft.block.BrewBarrelBlock;
import dev.lasm.foodcraft.block.ChoppingBoardBlock;
import dev.lasm.foodcraft.block.FruitBlock;
import dev.lasm.foodcraft.block.FryingPanBlock;
import dev.lasm.foodcraft.block.MillBlock;
import dev.lasm.foodcraft.block.ModCropBlock;
import dev.lasm.foodcraft.block.PanBlock;
import dev.lasm.foodcraft.block.PotBlock;
import dev.lasm.foodcraft.block.PressureCookerBlock;
import dev.lasm.foodcraft.block.StoveBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.SugarCaneBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(FoodCraft.MOD_ID);

    public static final DeferredBlock<Block> MACHINE_CASING;
    public static final DeferredBlock<Block> PRESSURE_COOKER;
    public static final DeferredBlock<Block> POT;
    public static final DeferredBlock<Block> STOVE;
    public static final DeferredBlock<Block> BEVERAGE_MAKING;
    public static final DeferredBlock<Block> BREW_BARREL;
    public static final DeferredBlock<Block> CHOPPING_BOARD;
    public static final DeferredBlock<Block> FRYING_PAN;
    public static final DeferredBlock<Block> PAN;
    public static final DeferredBlock<Block> MILL;

    public static final DeferredBlock<LiquidBlock> COOKING_OIL_FLOWING;

    public static final DeferredBlock<Block> GRAPEFRUIT;
    public static final DeferredBlock<Block> CHERRY;
    public static final DeferredBlock<Block> COCONUT;
    public static final DeferredBlock<Block> BANANA;
    public static final DeferredBlock<Block> PEACH;
    public static final DeferredBlock<Block> PERSIMMON;
    public static final DeferredBlock<Block> POMEGRANATE;
    public static final DeferredBlock<Block> HAWTHORN;
    public static final DeferredBlock<Block> LOQUAT;
    public static final DeferredBlock<Block> LEMON;
    public static final DeferredBlock<Block> PAPAYA;
    public static final DeferredBlock<Block> LONGAN;
    public static final DeferredBlock<Block> MANGO;
    public static final DeferredBlock<Block> LITCHI;
    public static final DeferredBlock<Block> PEAR;
    public static final DeferredBlock<Block> ORANGE;
    public static final DeferredBlock<Block> DATE;

    public static final DeferredBlock<Block> GRAPEFRUIT_SAPLING;
    public static final DeferredBlock<Block> CHERRY_SAPLING;
    public static final DeferredBlock<Block> COCONUT_SAPLING;
    public static final DeferredBlock<Block> BANANA_SAPLING;
    public static final DeferredBlock<Block> PEACH_SAPLING;
    public static final DeferredBlock<Block> PERSIMMON_SAPLING;
    public static final DeferredBlock<Block> POMEGRANATE_SAPLING;
    public static final DeferredBlock<Block> HAWTHORN_SAPLING;
    public static final DeferredBlock<Block> LOQUAT_SAPLING;
    public static final DeferredBlock<Block> LEMON_SAPLING;
    public static final DeferredBlock<Block> PAPAYA_SAPLING;
    public static final DeferredBlock<Block> LONGAN_SAPLING;
    public static final DeferredBlock<Block> MANGO_SAPLING;
    public static final DeferredBlock<Block> LITCHI_SAPLING;
    public static final DeferredBlock<Block> PEAR_SAPLING;
    public static final DeferredBlock<Block> ORANGE_SAPLING;
    public static final DeferredBlock<Block> DATE_SAPLING;

    public static final DeferredBlock<ModCropBlock> CORN;
    public static final DeferredBlock<ModCropBlock> CUCUMBER;
    public static final DeferredBlock<ModCropBlock> EGGPLANT;
    public static final DeferredBlock<ModCropBlock> FACING_HEAVEN_PEPPER;
    public static final DeferredBlock<ModCropBlock> GREEN_PEPPER;
    public static final DeferredBlock<ModCropBlock> PEANUT;
    public static final DeferredBlock<ModCropBlock> RICE;
    public static final DeferredBlock<ModCropBlock> STICKY_RICE;
    public static final DeferredBlock<ModCropBlock> SWEET_POTATO;
    public static final DeferredBlock<ModCropBlock> TOMATO;
    public static final DeferredBlock<ModCropBlock> WHITE_RADISH;
    public static final DeferredBlock<ModCropBlock> CABBAGE;
    public static final DeferredBlock<ModCropBlock> STRAWBERRY;
    public static final DeferredBlock<ModCropBlock> GRAPE;
    public static final DeferredBlock<ModCropBlock> MUNG_BEAN;
    public static final DeferredBlock<ModCropBlock> SOYBEAN;
    public static final DeferredBlock<ModCropBlock> ADZUKI_BEAN;

    public static final DeferredBlock<SugarCaneBlock> GREEN_ONION;

    public static Properties miscBlock() {
        return Properties.ofFullCopy(Blocks.IRON_BLOCK);
    }

    public static Properties machineBlock() {
        return Properties.ofFullCopy(Blocks.CRAFTER);
    }

    public static Properties fruitBlock() {
        return Properties.of().noOcclusion().mapColor(MapColor.PLANT).sound(SoundType.CROP).pushReaction(PushReaction.DESTROY);
    }

    public static Properties saplingBlock() {
        return Properties.of().noCollission().randomTicks().instabreak().sound(SoundType.GRASS);
    }

    public static Properties cropBlock() {
        return Properties.of()
            .mapColor(MapColor.PLANT)
            .noCollission()
            .randomTicks()
            .instabreak()
            .sound(SoundType.CROP)
            .pushReaction(PushReaction.DESTROY);
    }

    static {
        MACHINE_CASING = BLOCKS.register("machine_casing", () -> new Block(miscBlock()));
        PRESSURE_COOKER = BLOCKS.register("pressure_cooker", () -> new PressureCookerBlock(machineBlock()));
        STOVE = BLOCKS.register("stove", () -> new StoveBlock(machineBlock()));
        BEVERAGE_MAKING = BLOCKS.register("beverage_making", () -> new BeverageMakingMachineBlock(machineBlock()));
        BREW_BARREL = BLOCKS.register("brew_barrel", () -> new BrewBarrelBlock(machineBlock()));
        CHOPPING_BOARD = BLOCKS.register("chopping_board", () -> new ChoppingBoardBlock(machineBlock()));
        FRYING_PAN = BLOCKS.register("frying_pan", () -> new FryingPanBlock(machineBlock()));
        PAN = BLOCKS.register("pan", () -> new PanBlock(machineBlock()));
        POT = BLOCKS.register("pot", () -> new PotBlock(machineBlock()));
        MILL = BLOCKS.register("mill", () -> new MillBlock(machineBlock()));

        COOKING_OIL_FLOWING = BLOCKS.register("cooking_oil_flow", () -> new LiquidBlock(ModFluids.COOKING_OIL_FLOWING.get(), Properties.of()));

        GRAPEFRUIT = BLOCKS.register("grapefruit", () -> new FruitBlock(fruitBlock()));
        CHERRY = BLOCKS.register("cherry", () -> new FruitBlock(fruitBlock()));
        COCONUT = BLOCKS.register("coconut", () -> new FruitBlock(fruitBlock()));
        BANANA = BLOCKS.register("banana", () -> new FruitBlock(fruitBlock()));
        PEACH = BLOCKS.register("peach", () -> new FruitBlock(fruitBlock()));
        PERSIMMON = BLOCKS.register("persimmon", () -> new FruitBlock(fruitBlock()));
        POMEGRANATE = BLOCKS.register("pomegranate", () -> new FruitBlock(fruitBlock()));
        HAWTHORN = BLOCKS.register("hawthorn", () -> new FruitBlock(fruitBlock()));
        LOQUAT = BLOCKS.register("loquat", () -> new FruitBlock(fruitBlock()));
        LEMON = BLOCKS.register("lemon", () -> new FruitBlock(fruitBlock()));
        PAPAYA = BLOCKS.register("papaya", () -> new FruitBlock(fruitBlock()));
        LONGAN = BLOCKS.register("longan", () -> new FruitBlock(fruitBlock()));
        MANGO = BLOCKS.register("mango", () -> new FruitBlock(fruitBlock()));
        LITCHI = BLOCKS.register("litchi", () -> new FruitBlock(fruitBlock()));
        PEAR = BLOCKS.register("pear", () -> new FruitBlock(fruitBlock()));
        ORANGE = BLOCKS.register("orange", () -> new FruitBlock(fruitBlock()));
        DATE = BLOCKS.register("date", () -> new FruitBlock(fruitBlock()));

        GRAPEFRUIT_SAPLING = BLOCKS.register("grapefruit_sapling", () -> new SaplingBlock(ModTreeGrowers.GRAPEFRUIT, saplingBlock()));
        CHERRY_SAPLING = BLOCKS.register("cherry_sapling", () -> new SaplingBlock(ModTreeGrowers.CHERRY, saplingBlock()));
        COCONUT_SAPLING = BLOCKS.register("coconut_sapling", () -> new SaplingBlock(ModTreeGrowers.COCONUT, saplingBlock()));
        BANANA_SAPLING = BLOCKS.register("banana_sapling", () -> new SaplingBlock(ModTreeGrowers.BANANA, saplingBlock()));
        PEACH_SAPLING = BLOCKS.register("peach_sapling", () -> new SaplingBlock(ModTreeGrowers.PEACH, saplingBlock()));
        PERSIMMON_SAPLING = BLOCKS.register("persimmon_sapling", () -> new SaplingBlock(ModTreeGrowers.PERSIMMON, saplingBlock()));
        POMEGRANATE_SAPLING = BLOCKS.register("pomegranate_sapling", () -> new SaplingBlock(ModTreeGrowers.POMEGRANATE, saplingBlock()));
        HAWTHORN_SAPLING = BLOCKS.register("hawthorn_sapling", () -> new SaplingBlock(ModTreeGrowers.HAWTHORN, saplingBlock()));
        LOQUAT_SAPLING = BLOCKS.register("loquat_sapling", () -> new SaplingBlock(ModTreeGrowers.LOQUAT, saplingBlock()));
        LEMON_SAPLING = BLOCKS.register("lemon_sapling", () -> new SaplingBlock(ModTreeGrowers.LEMON, saplingBlock()));
        PAPAYA_SAPLING = BLOCKS.register("papaya_sapling", () -> new SaplingBlock(ModTreeGrowers.PAPAYA, saplingBlock()));
        LONGAN_SAPLING = BLOCKS.register("longan_sapling", () -> new SaplingBlock(ModTreeGrowers.LONGAN, saplingBlock()));
        MANGO_SAPLING = BLOCKS.register("mango_sapling", () -> new SaplingBlock(ModTreeGrowers.MANGO, saplingBlock()));
        LITCHI_SAPLING = BLOCKS.register("litchi_sapling", () -> new SaplingBlock(ModTreeGrowers.LITCHI, saplingBlock()));
        PEAR_SAPLING = BLOCKS.register("pear_sapling", () -> new SaplingBlock(ModTreeGrowers.PEAR, saplingBlock()));
        ORANGE_SAPLING = BLOCKS.register("orange_sapling", () -> new SaplingBlock(ModTreeGrowers.ORANGE, saplingBlock()));
        DATE_SAPLING = BLOCKS.register("date_sapling", () -> new SaplingBlock(ModTreeGrowers.DATE, saplingBlock()));

        CORN = BLOCKS.register("corn", () -> new ModCropBlock(cropBlock(), ModItems.CORN));
        CUCUMBER = BLOCKS.register("cucumber", () -> new ModCropBlock(cropBlock(), ModItems.CUCUMBER));
        EGGPLANT = BLOCKS.register("eggplant", () -> new ModCropBlock(cropBlock(), ModItems.EGGPLANT));
        FACING_HEAVEN_PEPPER = BLOCKS.register("facing_heaven_pepper", () -> new ModCropBlock(cropBlock(), ModItems.FACING_HEAVEN_PEPPER));
        GREEN_PEPPER = BLOCKS.register("green_pepper", () -> new ModCropBlock(cropBlock(), ModItems.GREEN_PEPPER));
        PEANUT = BLOCKS.register("peanut", () -> new ModCropBlock(cropBlock(), ModItems.PEANUT));
        RICE = BLOCKS.register("rice", () -> new ModCropBlock(cropBlock(), ModItems.RICE));
        STICKY_RICE = BLOCKS.register("sticky_rice", () -> new ModCropBlock(cropBlock(), ModItems.STICKY_RICE));
        SWEET_POTATO = BLOCKS.register("sweet_potato", () -> new ModCropBlock(cropBlock(), ModItems.SWEET_POTATO));
        TOMATO = BLOCKS.register("tomato", () -> new ModCropBlock(cropBlock(), ModItems.TOMATO));
        WHITE_RADISH = BLOCKS.register("white_radish", () -> new ModCropBlock(cropBlock(), ModItems.WHITE_RADISH));
        CABBAGE = BLOCKS.register("cabbage", () -> new ModCropBlock(cropBlock(), ModItems.CABBAGE));
        STRAWBERRY = BLOCKS.register("strawberry", () -> new ModCropBlock(cropBlock(), ModItems.STRAWBERRY));
        GRAPE = BLOCKS.register("grape", () -> new ModCropBlock(cropBlock(), ModItems.GRAPE));
        MUNG_BEAN = BLOCKS.register("mung_bean", () -> new ModCropBlock(cropBlock(), ModItems.MUNG_BEAN));
        SOYBEAN = BLOCKS.register("soybean", () -> new ModCropBlock(cropBlock(), ModItems.SOYBEAN));
        ADZUKI_BEAN = BLOCKS.register("adzuki_bean", () -> new ModCropBlock(cropBlock(), ModItems.ADZUKI_BEAN));

        GREEN_ONION = BLOCKS.register("green_onion", () -> new SugarCaneBlock(Properties.ofFullCopy(Blocks.SUGAR_CANE)));
    }
}
