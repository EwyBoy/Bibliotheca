package com.ewyboy.bibliotheca.common.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

/**
 * Created by EwyBoy
 */
public class PacketHandler {

    private static int packetID = 0;
    public static SimpleNetworkWrapper INSTANCE = null;

    public PacketHandler() {}

    private static int nextID () {
        return packetID++;
    }

    public static void registerMessages(String channelName) {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(channelName);
        registerMessages();
    }

    //INSTANCE.registerMessage(Class.Handler.class, Class.class, nextID(), Side.CLIENT/SERVER);
    private static void registerMessages() {}
}
