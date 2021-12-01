package com.ewyboy.bibliotheca.common.network.messages;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageBase {

    public static void encode(MessageBase pkt, FriendlyByteBuf buffer) {}

    public static MessageBase decode(FriendlyByteBuf buffer) {
        return new MessageBase();
    }

    public static void handle(MessageBase message, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> ctx.get().setPacketHandled(true));
    }

}
