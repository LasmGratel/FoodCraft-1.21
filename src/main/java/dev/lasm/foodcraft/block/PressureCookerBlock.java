package dev.lasm.foodcraft.block;

import com.mojang.serialization.MapCodec;
import dev.lasm.foodcraft.util.VoxelShapeHelper;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class PressureCookerBlock extends BaseMachineBlock {
    public static final MapCodec<PressureCookerBlock> CODEC = simpleCodec(PressureCookerBlock::new);

    public PressureCookerBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    public static final VoxelShape[] SHAPES = VoxelShapeHelper.make2DShapes(makeShape());

    private static VoxelShape makeShape() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.125, 0, 0.125, 0.4375, 0.0625, 0.875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5625, 0, 0.125, 0.875, 0.0625, 0.875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.0625, 0.0625, 0.0625, 0.9375, 0.8125, 0.9375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.75, 0.3125, 0.0625, 0.8125, 0.625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.9375, 0.75, 0.3125, 1, 0.8125, 0.625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.125, 0.8125, 0.125, 0.875, 0.9375, 0.875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.125, 0.9375, 0.40625, 0.875, 0.96875, 0.53125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.09375, 0.84375, 0.40625, 0.125, 0.96875, 0.53125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.875, 0.84375, 0.40625, 0.90625, 0.96875, 0.53125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.125, 0.96875, 0.40625, 0.15625, 1.09375, 0.53125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.4375, 0.96875, 0.40625, 0.46875, 1.09375, 0.53125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.125, 1.09375, 0.40625, 0.46875, 1.125, 0.53125), BooleanOp.OR);

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
        return null;
    }
}
