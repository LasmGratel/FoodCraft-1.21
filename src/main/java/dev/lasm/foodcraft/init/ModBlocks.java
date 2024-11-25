package dev.lasm.foodcraft.init;

import dev.lasm.foodcraft.FoodCraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, FoodCraft.MOD_ID);

    public static final RegistryObject<Block> MACHINE_CASING;

    public static BlockBehaviour.Properties miscBlock() {
        return BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK);
    }

    static {
        MACHINE_CASING = BLOCKS.register("machine_casing", () -> new Block(miscBlock()));
    }
}
