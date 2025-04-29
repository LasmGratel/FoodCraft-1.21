package dev.lasm.foodcraft.init;

import dev.lasm.foodcraft.FoodCraft;
import dev.lasm.foodcraft.block.BeverageMakingMachineBlock;
import dev.lasm.foodcraft.block.BrewBarrelBlock;
import dev.lasm.foodcraft.block.ChoppingBoardBlock;
import dev.lasm.foodcraft.block.FryingPanBlock;
import dev.lasm.foodcraft.block.MillBlock;
import dev.lasm.foodcraft.block.PanBlock;
import dev.lasm.foodcraft.block.PotBlock;
import dev.lasm.foodcraft.block.PressureCookerBlock;
import dev.lasm.foodcraft.block.StoveBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
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

    public static Properties miscBlock() {
        return Properties.ofFullCopy(Blocks.IRON_BLOCK);
    }

    public static Properties machineBlock() {
        return Properties.ofFullCopy(Blocks.CRAFTER);
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
    }
}
