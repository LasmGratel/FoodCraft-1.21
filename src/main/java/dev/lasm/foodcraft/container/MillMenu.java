package dev.lasm.foodcraft.container;

import dev.lasm.foodcraft.block.entity.MillBlockEntity;
import dev.lasm.foodcraft.block.entity.MillBlockEntity;
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
public class MillMenu extends BaseMachineMenu<MillBlockEntity> {
    private final ContainerData dataAccess;

    public MillMenu(
        int containerId, Inventory playerInv, FriendlyByteBuf data) {
        this(containerId, playerInv, getTileEntity(playerInv.player.level(), data, MillBlockEntity.class));
    }

    public MillMenu(int containerId, Inventory playerInv, MillBlockEntity tileEntity) {
        super(ModMenuTypes.MILL.get(), containerId, playerInv, tileEntity);


        if (!tileEntity.getLevel().isClientSide) {

            this.dataAccess = new ContainerData() {
                @Override
                public int get(int i) {
                    return switch (i) {
                        case 0 -> tileEntity.burnTime;
                        case 1 -> tileEntity.maxBurnTime;
                        case 2 -> tileEntity.cookingTime;
                        case 3 -> tileEntity.maxCookingTime;
                        default -> 0;
                    };
                }

                @Override
                public void set(int i, int i1) {
                    switch (i) {
                        case 0 -> tileEntity.burnTime = i1;
                        case 1 -> tileEntity.maxBurnTime = i1;
                        case 2 -> tileEntity.cookingTime = i1;
                        case 3 -> tileEntity.maxCookingTime = i1;
                    }
                }

                @Override
                public int getCount() {
                    return 4;
                }
            };
        } else {
            dataAccess = new SimpleContainerData(4);
        }

        var dataInventory = tileEntity.inventory;


        addSlot(new SlotItemHandler(dataInventory, 0, 80, 54));
        addSlot(new SlotItemHandler(dataInventory, 1, 49, 19));
        addSlot(new OutputSlotItemHandler(dataInventory, 2, 112, 19));


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
        return 3;
    }

    @Override
    public ContainerData getContainerData() {
        return dataAccess;
    }
}
