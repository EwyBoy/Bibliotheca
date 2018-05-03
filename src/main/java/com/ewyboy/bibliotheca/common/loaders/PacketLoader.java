package com.ewyboy.bibliotheca.common.loaders;

import com.ewyboy.bibliotheca.common.network.PacketBase;
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
     *   This @PacketLoader detects all the fields and register them for you
     *   as long as they extend @{@link PacketBase}
     */
    public static void init(String modid, Class packetRegister) {
        registerPackets(modid, packetRegister);
    }

    /**
     * Grabs the packet fields from the packetRegister class you provided & registers the packets
     */
    private static void registerPackets(String modid, Class packetRegister) {
        wrapper = NetworkRegistry.INSTANCE.newSimpleChannel(modid);
        PacketBase.PacketBaseHandler packetHandler = new PacketBase.PacketBaseHandler();
        try {
            for (Field field : packetRegister.getDeclaredFields()) {
                Object obj = field.get(null);
                if (obj instanceof PacketBase.PacketBaseHandler) {
                    packetHandler = (PacketBase.PacketBaseHandler) obj;
                }
                if (obj instanceof PacketBase) {
                    PacketBase packet = (PacketBase) obj;
                    Logger.info("[PACKET]: " + packet.getClass().getSimpleName() + " has been registered on the " + packet.getSide().name() + " side with packet ID: " + packetID + " by Bibliotheca for the mod " + modid);
                    wrapper.registerMessage(packetHandler, packet.getClass(), getPacketID(), packet.getSide());
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
