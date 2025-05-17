package dev.lasm.foodcraft.container;

import dev.lasm.foodcraft.block.entity.BrewBarrelBlockEntity;
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
public class BrewBarrelMenu extends BaseMachineMenu<BrewBarrelBlockEntity> {
    public final SyncFluidTank fluidTank;
    private final ContainerData dataAccess;

    public BrewBarrelMenu(
        int containerId, Inventory playerInv, FriendlyByteBuf data) {
        this(containerId, playerInv,  getTileEntity(playerInv.player.level(), data, BrewBarrelBlockEntity.class));
    }

    public BrewBarrelMenu(int containerId, Inventory playerInv, BrewBarrelBlockEntity tileEntity) {
        super(ModMenuTypes.BREW_BARREL.get(), containerId, playerInv, tileEntity);

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
                        case 0 -> tileEntity.cookingTime;
                        case 1 -> tileEntity.maxCookingTime;
                        default -> 0;
                    };
                }

                @Override
                public void set(int i, int i1) {
                    switch (i) {
                        case 0 -> tileEntity.cookingTime = i1;
                        case 1 -> tileEntity.maxCookingTime = i1;
                    }
                }

                @Override
                public int getCount() {
                    return 2;
                }
            };
        } else {
            dataAccess = new SimpleContainerData(4);
        }

        var dataInventory = tileEntity.inventory;

        addSlot(new SlotItemHandler(dataInventory, 0, 37, 59));
        addSlot(new SlotItemHandler(dataInventory, 1, 54, 32));
        addSlot(new SlotItemHandler(dataInventory, 2, 78, 32));
        addSlot(new SlotItemHandler(dataInventory, 3, 101, 32));

        addSlot(new OutputSlotItemHandler(dataInventory, 4, 138, 32));
        addSlot(new NonInteractiveSlot(dataInventory, 5, 138, 59));


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
        return 6;
    }

    @Override
    public ContainerData getContainerData() {
        return dataAccess;
    }
}
