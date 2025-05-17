package dev.lasm.foodcraft.container;

import dev.lasm.foodcraft.block.entity.BeverageMakingBlockEntity;
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
public class BeverageMakingMenu extends BaseMachineMenu<BeverageMakingBlockEntity> {
    public final SyncFluidTank fluidTank;
    private final ContainerData dataAccess;

    public BeverageMakingMenu(
        int containerId, Inventory playerInv, FriendlyByteBuf data) {
        this(containerId, playerInv, getTileEntity(playerInv.player.level(), data, BeverageMakingBlockEntity.class));
    }

    public BeverageMakingMenu(int containerId, Inventory playerInv, BeverageMakingBlockEntity tileEntity) {
        super(ModMenuTypes.BEVERAGE_MAKING.get(), containerId, playerInv, tileEntity);

        this.fluidTank = tileEntity.fluidTank;

        if (!tileEntity.getLevel().isClientSide) {
            this.fluidTank.setOnContentsChanged(() -> {
                if (!this.fluidTank.getFluid().isEmpty())
                    PacketDistributor.sendToPlayer((ServerPlayer) playerInv.player, new FluidStackPayload(this.fluidTank.getFluid()));
            });

            this.dataAccess = new ContainerData() {
                @Override
                public int get(int i) {
                    return switch (i) {
                        case 0 -> tileEntity.burnTime;
                        case 1 -> tileEntity.maxBurnTime;
                        case 2 -> tileEntity.cookingTime;
                        case 3 -> tileEntity.maxCookingTime;
                        case 4 -> tileEntity.isCooling ? 1 : 0;
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
                        case 4 -> tileEntity.isCooling = i1 == 1;
                    }
                }

                @Override
                public int getCount() {
                    return 5;
                }
            };
        } else {
            dataAccess = new SimpleContainerData(5);
        }

        var dataInventory = tileEntity.inventory;

        addSlot(new SlotItemHandler(dataInventory, 0, 118, 20));
        addSlot(new SlotItemHandler(dataInventory, 1, 118, 52));

        addSlot(new SlotItemHandler(dataInventory, 2, 37, 59));
        addSlot(new SlotItemHandler(dataInventory, 3, 37, 31));

        addSlot(new OutputSlotItemHandler(dataInventory, 4, 85, 31));


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
        return 5;
    }

    @Override
    public ContainerData getContainerData() {
        return dataAccess;
    }
}
