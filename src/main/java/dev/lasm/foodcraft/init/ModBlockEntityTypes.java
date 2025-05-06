package dev.lasm.foodcraft.init;

import dev.lasm.foodcraft.FoodCraft;
import dev.lasm.foodcraft.block.entity.BrewBarrelBlockEntity;
import dev.lasm.foodcraft.block.entity.FryingPanBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
        DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, FoodCraft.MOD_ID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BrewBarrelBlockEntity>> BREW_BARREL = BLOCK_ENTITY_TYPES.register(
        "brew_barrel",
        () -> BlockEntityType.Builder.of(BrewBarrelBlockEntity::new, ModBlocks.BREW_BARREL.get()).build(null)
    );

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<FryingPanBlockEntity>> FRYING_PAN = BLOCK_ENTITY_TYPES.register(
        "frying_pan",
        () -> BlockEntityType.Builder.of(FryingPanBlockEntity::new, ModBlocks.FRYING_PAN.get()).build(null)
    );

}
