package com.ewyboy.bibliotheca.proxy;

import com.ewyboy.bibliotheca.common.compatibilities.CompatibilityHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.FMLEventChannel;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy {

    public Side getSide(){return Side.SERVER;}

    public static FMLEventChannel packetHandler;

    public int getParticleSettings() {return 3;}

    public void preInit(FMLPreInitializationEvent event) {
        CompatibilityHandler.registerWaila();
    }

    public void init(FMLInitializationEvent event) {}

    public void postInit(FMLPostInitializationEvent event) {}
}
