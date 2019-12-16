package com.ewyboy.bibliotheca.proxy;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class CommonProxy implements IModProxy {

    public Dist getSide() {
        return Dist.DEDICATED_SERVER;
    }

    @Override
    public void construct() {}

    @Override
    public void setup(final FMLCommonSetupEvent event) {
    }

}
