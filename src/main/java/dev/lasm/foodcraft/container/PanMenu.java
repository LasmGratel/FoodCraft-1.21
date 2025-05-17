package dev.lasm.foodcraft.container;

import dev.lasm.foodcraft.block.entity.PanBlockEntity;
import dev.lasm.foodcraft.init.ModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.neoforged.neoforge.items.SlotItemHandler;

public class PanMenu extends BaseMachineMenu<PanBlockEntity> {
    private final ContainerData dataAccess;

    public PanMenu(int containerId, Inventory playerInv, FriendlyByteBuf data) {
        this(containerId, playerInv, getTileEntity(playerInv.player.level(), data, PanBlockEntity.class));
    }

    public PanMenu(int containerId, Inventory playerInv, PanBlockEntity tileEntity) {
        super(ModMenuTypes.PAN.get(), containerId, playerInv, tileEntity);
        if (!playerInv.player.level().isClientSide) {
            dataAccess = new ContainerData() {
                @Override
                public int get(int i) {
                    return switch (i) {
                        case 0 -> tileEntity.cookingTime;
                        case 1 -> tileEntity.minCookingTime;
                        case 2 -> tileEntity.maxCookingTime;
                        case 3 -> tileEntity.maxTime;
                        case 4 -> tileEntity.proficiency;
                        case 5 -> tileEntity.isHeated(tileEntity.getLevel(), tileEntity.getBlockPos()) ? 1 : 0;
                        default -> 0;
                    };
                }

                @Override
                public void set(int i, int i1) {
                    switch (i) {
                        case 0 -> tileEntity.cookingTime = i1;
                        case 1 -> tileEntity.minCookingTime = i1;
                        case 2 -> tileEntity.maxCookingTime = i1;
                        case 3 -> tileEntity.maxTime = i1;
                        case 4 -> tileEntity.proficiency = i1;
                    }
                }

                @Override
                public int getCount() {
                    return 6;
                }
            };
        } else {
            dataAccess = new SimpleContainerData(6);
        }


        addSlot(new SlotItemHandler(tileEntity.inventory, 0, 18, 39));
        addSlot(new SlotItemHandler(tileEntity.inventory, 1, 45, 39));

        addSlot(new OutputSlotItemHandler(tileEntity.inventory, 2, 108, 39));
        addSlot(new OutputSlotItemHandler(tileEntity.inventory,3, 137, 39));

        for (int k = 0; k < 3; k++) {
            for (int i1 = 0; i1 < 9; i1++) {
                this.addSlot(new Slot(playerInv, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
            }
        }

        for (int l = 0; l < 9; l++) {
            this.addSlot(new Slot(playerInv, l, 8 + l * 18, 142));
        }

        addDataSlots(dataAccess);
    }

    @Override
    public int getMachineSlots() {
        return 4;
    }

    @Override
    public ContainerData getContainerData() {
        return dataAccess;
    }
}
