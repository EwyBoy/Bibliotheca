package com.ewyboy.bibliotheca.client.interfaces;

import net.minecraft.util.ResourceLocation;

public interface IHasOBJModel {

    boolean shouldRenderItem();
    boolean shouldRenderBlock();

    ResourceLocation getOBJModelLocation();

}
