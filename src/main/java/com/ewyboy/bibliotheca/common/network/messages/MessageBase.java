package com.ewyboy.bibliotheca.common.network.messages;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageBase {

    public static void encode(MessageBase pkt, PacketBuffer buffer) {}

    public static MessageBase decode(PacketBuffer buffer) {
        return new MessageBase();
    }

    public static void handle(MessageBase message, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> ctx.get().setPacketHandled(true));
    }

}
