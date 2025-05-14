package dev.lasm.foodcraft.network;

import dev.lasm.foodcraft.FoodCraft;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.fluids.FluidStack;

public record FluidStackPayload(FluidStack fluidStack) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<FluidStackPayload> TYPE = new CustomPacketPayload.Type<>(
        ResourceLocation.fromNamespaceAndPath(FoodCraft.MOD_ID, "fluid_tank"));

    public static final StreamCodec<RegistryFriendlyByteBuf, FluidStackPayload> STREAM_CODEC = StreamCodec.composite(
        FluidStack.STREAM_CODEC,
    FluidStackPayload::fluidStack,
    FluidStackPayload::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
