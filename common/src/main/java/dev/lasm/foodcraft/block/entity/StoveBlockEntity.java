package dev.lasm.foodcraft.block.entity;

import net.darkhax.bookshelf.api.block.entity.InventoryBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class StoveBlockEntity extends InventoryBlockEntity {

    public StoveBlockEntity(BlockEntityType type,
        BlockPos pos, BlockState state) {
        super(type, pos, state);

    }

    @Override
    public Container createInventory() {
        return null;
    }

    @Override
    protected Component getDefaultName() {
        return null;
    }
}
