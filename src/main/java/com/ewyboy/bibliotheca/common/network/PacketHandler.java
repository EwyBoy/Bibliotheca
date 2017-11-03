package com.ewyboy.bibliotheca.common.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

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

    public static void registerMessages(String channelName, Class handler, Class packet, Side side) {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(channelName);
        INSTANCE.registerMessage(handler, packet, nextID(), side);
    }
}
