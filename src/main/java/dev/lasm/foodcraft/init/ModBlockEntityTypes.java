package dev.lasm.foodcraft.init;

import dev.lasm.foodcraft.FoodCraft;
import dev.lasm.foodcraft.block.entity.BeverageMakingBlockEntity;
import dev.lasm.foodcraft.block.entity.BrewBarrelBlockEntity;
import dev.lasm.foodcraft.block.entity.FryingPanBlockEntity;
import dev.lasm.foodcraft.block.entity.MillBlockEntity;
import dev.lasm.foodcraft.block.entity.PanBlockEntity;
import dev.lasm.foodcraft.block.entity.PotBlockEntity;
import dev.lasm.foodcraft.block.entity.PressureCookerBlockEntity;
import dev.lasm.foodcraft.block.entity.StoveBlockEntity;
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

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PotBlockEntity>> POT = BLOCK_ENTITY_TYPES.register(
        "pot",
        () -> BlockEntityType.Builder.of(PotBlockEntity::new, ModBlocks.POT.get()).build(null)
    );

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PressureCookerBlockEntity>> PRESSURE_COOKER = BLOCK_ENTITY_TYPES.register(
        "pressure_cooker",
        () -> BlockEntityType.Builder.of(PressureCookerBlockEntity::new, ModBlocks.PRESSURE_COOKER.get()).build(null)
    );

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<MillBlockEntity>> MILL = BLOCK_ENTITY_TYPES.register(
        "mill",
        () -> BlockEntityType.Builder.of(MillBlockEntity::new, ModBlocks.MILL.get()).build(null)
    );

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PanBlockEntity>> PAN = BLOCK_ENTITY_TYPES.register(
        "pan",
        () -> BlockEntityType.Builder.of(PanBlockEntity::new, ModBlocks.PAN.get()).build(null)
    );

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<StoveBlockEntity>> STOVE = BLOCK_ENTITY_TYPES.register(
        "stove",
        () -> BlockEntityType.Builder.of(StoveBlockEntity::new, ModBlocks.STOVE.get()).build(null)
    );

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BeverageMakingBlockEntity>> BEVERAGE_MAKING = BLOCK_ENTITY_TYPES.register(
        "beverage_making",
        () -> BlockEntityType.Builder.of(BeverageMakingBlockEntity::new, ModBlocks.BEVERAGE_MAKING.get()).build(null)
    );
}
