package dev.lasm.foodcraft.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.lasm.foodcraft.FoodCraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(FoodCraft.MOD_ID, Registries.BLOCK);

    public static final RegistrySupplier<Block> MACHINE_CASING;

    public static BlockBehaviour.Properties miscBlock() {
        return BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK);
    }

    static {
        MACHINE_CASING = BLOCKS.register("machine_casing", () -> new Block(miscBlock()));

    }
}
