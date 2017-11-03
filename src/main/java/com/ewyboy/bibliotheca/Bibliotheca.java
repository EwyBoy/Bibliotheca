package com.ewyboy.bibliotheca;

import com.ewyboy.bibliotheca.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static com.ewyboy.bibliotheca.common.utility.Reference.Info.MOD_ID;
import static com.ewyboy.bibliotheca.common.utility.Reference.Info.NAME;
import static com.ewyboy.bibliotheca.common.utility.Reference.Info.VERSION;
import static com.ewyboy.bibliotheca.common.utility.Reference.Path.CLIENT_PROXY;
import static com.ewyboy.bibliotheca.common.utility.Reference.Path.COMMON_PROXY;

@Mod(modid = MOD_ID, name = NAME, version = VERSION)
public class Bibliotheca {

    @Mod.Instance(MOD_ID)
    public static Bibliotheca INSTANCE;

    @SidedProxy(clientSide = CLIENT_PROXY, serverSide = COMMON_PROXY)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
