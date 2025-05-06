package dev.lasm.foodcraft.container;

import dev.lasm.foodcraft.block.entity.FryingPanBlockEntity;
import dev.lasm.foodcraft.init.ModBlocks;
import dev.lasm.foodcraft.init.ModMenuTypes;
import java.util.Objects;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.SlotItemHandler;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class FryingPanMenu extends AbstractContainerMenu {
    private final ContainerLevelAccess access;

    public FryingPanMenu(
        int containerId, Inventory playerInv, FriendlyByteBuf data) {
        this(containerId, playerInv, getTileEntity(playerInv, data, FryingPanBlockEntity.class));
    }

    private static <T extends BlockEntity> T getTileEntity(Inventory playerInventory, FriendlyByteBuf data, Class<T> tileEntityClass) {
        Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
        Objects.requireNonNull(data, "data cannot be null");
        var tileAtPos = playerInventory.player.level().getBlockEntity(data.readBlockPos());
        if (tileAtPos != null && tileAtPos.getClass() == tileEntityClass) {
            return (T)tileAtPos;
        } else {
            throw new IllegalStateException("Tile entity is not correct! " + tileAtPos);
        }
    }

    public FryingPanMenu(int containerId, Inventory playerInv, FryingPanBlockEntity tileEntity) {
        super(ModMenuTypes.FRYING_PAN.get(), containerId);
        this.access = ContainerLevelAccess.create(tileEntity.getLevel(), tileEntity.getBlockPos());
        var dataInventory = tileEntity.inventory;

        addSlot(new SlotItemHandler(dataInventory, 0, 58, 31));

        addSlot(new SlotItemHandler(dataInventory, 2, 37, 59));
        addSlot(new SlotItemHandler(dataInventory, 3, 95, 59));

        addSlot(new OutputSlotItemHandler(dataInventory, 1, 130, 31));


        for (int k = 0; k < 3; k++) {
            for (int i1 = 0; i1 < 9; i1++) {
                this.addSlot(new Slot(playerInv, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
            }
        }

        for (int l = 0; l < 9; l++) {
            this.addSlot(new Slot(playerInv, l, 8 + l * 18, 142));
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return AbstractContainerMenu.stillValid(this.access, player, ModBlocks.FRYING_PAN.get());
    }

}
