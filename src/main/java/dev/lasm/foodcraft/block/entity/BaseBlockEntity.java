package dev.lasm.foodcraft.block.entity;

import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class BaseBlockEntity extends BlockEntity {

    public BaseBlockEntity(BlockEntityType<?> type,
        BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public static void detectLit(Level level, BlockPos blockPos, BlockState blockState, boolean working) {
        if (blockState.getValue(BlockStateProperties.LIT) != working) {
            level.setBlock(blockPos, blockState.setValue(BlockStateProperties.LIT, working), 3);
        }
    }

    @Override
    @Nullable
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return this.saveWithoutMetadata(registries);
    }

    protected void inventoryChanged() {
        super.setChanged();
        if (this.level != null) {
            this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 2);
        }

    }
}
