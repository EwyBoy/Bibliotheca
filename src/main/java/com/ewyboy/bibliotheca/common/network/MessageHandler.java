package com.ewyboy.bibliotheca.common.network;

import com.ewyboy.bibliotheca.Bibliotheca;
import com.ewyboy.bibliotheca.common.network.messages.MessageBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class MessageHandler {

    private static int messageID = 0;
    private static final String PROTOCOL_VERSION = Integer.toString(1);

    public static final SimpleChannel CHANNEL = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(Bibliotheca.ID, "network"))
        .clientAcceptedVersions(PROTOCOL_VERSION :: equals)
        .serverAcceptedVersions(PROTOCOL_VERSION :: equals)
        .networkProtocolVersion(() ->  PROTOCOL_VERSION)
        .simpleChannel()
    ;

    private static int nextID() {
        return messageID++;
    }

    public static void init() {
        CHANNEL.registerMessage(nextID(), MessageBase.class, MessageBase :: encode, MessageBase :: decode, MessageBase :: handle);
    }


}
