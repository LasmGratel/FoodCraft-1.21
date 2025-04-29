package dev.lasm.foodcraft.block;

import com.mojang.serialization.MapCodec;
import dev.lasm.foodcraft.block.entity.BrewBarrelBlockEntity;
import dev.lasm.foodcraft.init.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class BrewBarrelBlock extends BaseMachineBlock {
    public static final MapCodec<BrewBarrelBlock> CODEC = simpleCodec(BrewBarrelBlock::new);

    public BrewBarrelBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return ModBlockEntityTypes.BREW_BARREL.get().create(blockPos, blockState);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level,
        BlockState state, BlockEntityType<T> blockEntityType) {
        return !level.isClientSide ? createTickerHelper(blockEntityType,
            ModBlockEntityTypes.BREW_BARREL.get(),
            BrewBarrelBlockEntity::tick) : null;
    }
}
