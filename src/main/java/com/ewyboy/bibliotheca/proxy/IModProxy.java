package com.ewyboy.bibliotheca.proxy;

import net.minecraftforge.api.distmarker.Dist;

public interface IModProxy {

    Dist getSide();

    void construct();

    void setup();

}
