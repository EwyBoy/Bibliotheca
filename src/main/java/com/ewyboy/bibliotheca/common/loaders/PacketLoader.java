package com.ewyboy.bibliotheca.common.loaders;

import com.ewyboy.bibliotheca.common.network.PacketBase;
import com.ewyboy.bibliotheca.common.network.PacketBaseHandler;
import com.ewyboy.bibliotheca.common.utility.Logger;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

import java.lang.reflect.Field;

/**
 * Created by EwyBoy
 */
public class PacketLoader {

    private static int packetID = 0;
    public static SimpleNetworkWrapper wrapper;

    private static int getPacketID() {
        return packetID++;
    }

    /**
     *   Initialize this in preInit() in your mod
     *   @param modid the mod id for your mod
     *   @param packetRegister the class where your initialize your packet fields
     *   Example: public static TestPacket = new TestPacket();
     *   This @{@link PacketLoader detects all the fields and register them for you
     *   as long as they extend @{@link PacketBase}
     */
    public static void init(String modid, Class packetRegister) {
        registerPackets(modid, packetRegister);
    }

    private static void registerPacketsX(String modid, Class packetRegister, Class packetHandlerRegister) {
        wrapper = NetworkRegistry.INSTANCE.newSimpleChannel(modid);

        try {

            //TODO Finish PacketLoader
            for (Field field : packetRegister.getDeclaredFields()) {
                for (Field fieldHandler : packetHandlerRegister.getDeclaredFields()) {
                    Object obj = fieldHandler.get(null);
                    if (obj instanceof PacketBaseHandler) {
                        PacketBaseHandler packetHandler = (PacketBaseHandler) obj;

                       /* if (packetHandler.toString().contains());*/

                    }
                }

                Object obj = field.get(null);
                if (obj instanceof PacketBase) {
                    PacketBase packet = (PacketBase) obj;
                }
            }

            for (Field field : packetHandlerRegister.getDeclaredFields()) {}

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Grabs the packet fields from the packetRegister class you provided & registers the packets
     */
    private static void registerPackets(String modid, Class packetRegister) {
        wrapper = NetworkRegistry.INSTANCE.newSimpleChannel(modid);

        try {
            for (Field field : packetRegister.getDeclaredFields()) {
                Object obj = field.get(null);

                if (obj instanceof PacketBaseHandler) {
                    PacketBaseHandler packetHandler = (PacketBaseHandler) obj;
                }
                if (obj instanceof PacketBase) {
                    PacketBase packet = (PacketBase) obj;
                    if (packet.getSide() != null) {
                        Logger.info("[PACKET]: " + packet.getClass().getSimpleName() + " has been registered on the " + packet.getSide().name() + " side with packet ID: " + packetID + " by Bibliotheca for the mod " + modid);
                        //wrapper.registerMessage(wrapper, packet.getClass(), getPacketID(), packet.getSide());
                    } else {
                        Logger.info("[ERROR]: " + packet.getClass().getSimpleName() + " has not specified a side");
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
