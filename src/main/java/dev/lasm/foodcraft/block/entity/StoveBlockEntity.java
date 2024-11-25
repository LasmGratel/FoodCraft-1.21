package dev.lasm.foodcraft.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class StoveBlockEntity extends BaseContainerBlockEntity {

    @Override
    public void deserializeNBT(CompoundTag nbt) {

    }

    public StoveBlockEntity(BlockEntityType type,
        BlockPos pos, BlockState state) {
        super(type, pos, state);

    }

    @Override
    protected Component getDefaultName() {
        return null;
    }

    public SimpleContainer createInventory() {
        return new SimpleContainer();
    }
}
