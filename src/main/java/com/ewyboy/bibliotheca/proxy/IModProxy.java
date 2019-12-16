package com.ewyboy.bibliotheca.proxy;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public interface IModProxy {

    Dist getSide();

    void construct();

    void setup(final FMLCommonSetupEvent event);

}
