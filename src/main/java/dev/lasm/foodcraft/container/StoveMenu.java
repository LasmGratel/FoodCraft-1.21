package dev.lasm.foodcraft.container;

import dev.lasm.foodcraft.block.entity.StoveBlockEntity;
import dev.lasm.foodcraft.init.ModMenuTypes;
import dev.lasm.foodcraft.network.FluidStackPayload;
import dev.lasm.foodcraft.network.SyncFluidTank;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.neoforged.neoforge.network.PacketDistributor;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class StoveMenu extends BaseMachineMenu<StoveBlockEntity> {
    private final ContainerData dataAccess;

    public StoveMenu(
        int containerId, Inventory playerInv, FriendlyByteBuf data) {
        this(containerId, playerInv, getTileEntity(playerInv.player.level(), data, StoveBlockEntity.class));
    }

    public StoveMenu(int containerId, Inventory playerInv, StoveBlockEntity tileEntity) {
        super(ModMenuTypes.STOVE.get(), containerId, playerInv, tileEntity);

        if (!tileEntity.getLevel().isClientSide) {
            this.dataAccess = new ContainerData() {
                @Override
                public int get(int i) {
                    return switch (i) {
                        case 0 -> tileEntity.burnTime;
                        case 1 -> tileEntity.maxBurnTime;
                        default -> 0;
                    };
                }

                @Override
                public void set(int i, int i1) {
                    switch (i) {
                        case 0 -> tileEntity.burnTime = i1;
                        case 1 -> tileEntity.maxBurnTime = i1;
                    }
                }

                @Override
                public int getCount() {
                    return 2;
                }
            };
        } else {
            dataAccess = new SimpleContainerData(2);
        }

        var dataInventory = tileEntity.inventory;

        addSlot(new SlotItemHandler(dataInventory, 0, 80, 54));

        for (int k = 0; k < 3; k++) {
            for (int i1 = 0; i1 < 9; i1++) {
                this.addSlot(new Slot(playerInv, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
            }
        }

        for (int l = 0; l < 9; l++) {
            this.addSlot(new Slot(playerInv, l, 8 + l * 18, 142));
        }

        this.addDataSlots(dataAccess);
    }

    public int getMachineSlots() {
        return 1;
    }

    @Override
    public ContainerData getContainerData() {
        return dataAccess;
    }
}
