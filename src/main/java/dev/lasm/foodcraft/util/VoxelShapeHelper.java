package dev.lasm.foodcraft.util;

import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class VoxelShapeHelper {

    /**
     * Rotate a VoxelShape horizontally, assuming default shape towards north
     * See <a href="https://forums.minecraftforge.net/topic/74979-1144-rotate-voxel-shapes/">Forge forums</a>
     * @param to Block in world direction
     * @param shape Shape to rotate
     * @return Rotated shape
     */
    public static VoxelShape rotateShape(Direction to, VoxelShape shape) {
        VoxelShape[] buffer = new VoxelShape[]{shape, Shapes.empty()};

        int times = switch (to) {
            case SOUTH -> 2;
            case WEST -> 3;
            case EAST -> 1;
            default -> 0;
        };
        for (int i = 0; i < times; i++) {
            buffer[0].forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) -> buffer[1] = Shapes.or(buffer[1], Shapes.create(1 - maxZ, minY, minX, 1 - minZ, maxY, maxX)));
            buffer[0] = buffer[1];
            buffer[1] = Shapes.empty();
        }

        return buffer[0];
    }

    public static VoxelShape[] makeHorizontalShapes(VoxelShape shape) {
        return new VoxelShape[]{shape, rotateShape(Direction.SOUTH, shape), rotateShape(Direction.WEST, shape), rotateShape(Direction.EAST, shape)};
    }

    /**
     * @see Direction#get2DDataValue()
     */
    public static VoxelShape[] make2DShapes(VoxelShape shape) {
        return new VoxelShape[]{rotateShape(Direction.SOUTH, shape), rotateShape(Direction.WEST, shape), shape, rotateShape(Direction.EAST, shape)};
    }
}
