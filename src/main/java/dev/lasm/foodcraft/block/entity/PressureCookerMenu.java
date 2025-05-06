package dev.lasm.foodcraft.block.entity;

import dev.lasm.foodcraft.init.ModBlocks;
import java.util.Objects;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;

public class PressureCookerMenu extends AbstractContainerMenu {
    public final PressureCookerBlockEntity blockEntity;
    public final Container inventory;
    private final ContainerData cookingPotData;
    private final ContainerLevelAccess canInteractWithCallable;
    protected final Level level;

    public PressureCookerMenu(int windowId, Inventory playerInventory, FriendlyByteBuf data) {
        this(windowId, playerInventory, getTileEntity(PressureCookerBlockEntity.class, playerInventory, data), new SimpleContainerData(4));
    }

    public PressureCookerMenu(int windowId, Inventory playerInventory, PressureCookerBlockEntity blockEntity, ContainerData cookingPotDataIn) {
        super(null, windowId);
        this.blockEntity = blockEntity;
        this.inventory = blockEntity.createInventory();
        this.cookingPotData = cookingPotDataIn;
        this.level = playerInventory.player.level();
        this.canInteractWithCallable = ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos());

        this.addDataSlots(cookingPotDataIn);
    }

    private static <T extends BlockEntity> T getTileEntity(Class<T> tileEntityType, Inventory playerInventory, FriendlyByteBuf data) {
        Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
        Objects.requireNonNull(data, "data cannot be null");
        BlockEntity tileAtPos = playerInventory.player.level().getBlockEntity(data.readBlockPos());
        if (tileAtPos != null && tileAtPos.getClass() == tileEntityType) {
            return (T)tileAtPos;
        } else {
            throw new IllegalStateException("Tile entity is not correct! " + tileAtPos);
        }
    }

    public boolean stillValid(Player playerIn) {
        return stillValid(this.canInteractWithCallable, playerIn, (Block) ModBlocks.MACHINE_CASING.get());
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }

}
