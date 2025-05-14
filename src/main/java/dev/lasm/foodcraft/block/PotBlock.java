package dev.lasm.foodcraft.block;

import com.mojang.serialization.MapCodec;
import dev.lasm.foodcraft.block.entity.PotBlockEntity;
import dev.lasm.foodcraft.init.ModBlockEntityTypes;
import dev.lasm.foodcraft.util.VoxelShapeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class PotBlock extends BaseMachineBlock {
    public static final MapCodec<PotBlock> CODEC = simpleCodec(PotBlock::new);

    public PotBlock(Properties properties) {
        super(properties);
    }

    public static final VoxelShape[] SHAPES = VoxelShapeHelper.make2DShapes(makeShape());

    private static VoxelShape makeShape() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.0625, 0.0625, 0.0625, 0.9375, 0.4375, 0.125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.0625, 0.0625, 0.875, 0.9375, 0.4375, 0.9375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.0625, 0.0625, 0.125, 0.125, 0.4375, 0.875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.875, 0.0625, 0.125, 0.9375, 0.4375, 0.875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1125, 0, 0.1125, 0.8875, 0.0625, 0.8875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.375, 0.3125, 0.0625, 0.4375, 0.34375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(-0.03125, 0.375, 0.3125, 0, 0.4375, 0.6875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.375, 0.65625, 0.0625, 0.4375, 0.6875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.9375, 0.375, 0.3125, 1, 0.4375, 0.34375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.9375, 0.375, 0.65625, 1, 0.4375, 0.6875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(1, 0.375, 0.3125, 1.03125, 0.4375, 0.6875), BooleanOp.OR);

        return shape;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos,
        CollisionContext context) {
        return SHAPES[state.getValue(FACING).get2DDataValue()];
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return ModBlockEntityTypes.POT.get().create(blockPos, blockState);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level,
        BlockState state, BlockEntityType<T> blockEntityType) {
        return !level.isClientSide ? createTickerHelper(blockEntityType,
            ModBlockEntityTypes.POT.get(),
            PotBlockEntity::tick) : null;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos,
        Player player, BlockHitResult hitResult) {
        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer && level.getBlockEntity(pos) instanceof PotBlockEntity blockEntity) {
            serverPlayer.openMenu(blockEntity, pos);
        }

        return InteractionResult.SUCCESS;
    }
}
