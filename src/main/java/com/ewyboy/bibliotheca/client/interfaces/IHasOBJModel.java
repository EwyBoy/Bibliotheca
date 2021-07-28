package com.ewyboy.bibliotheca.client.interfaces;

import net.minecraft.resources.ResourceLocation;

public interface IHasOBJModel {

    boolean shouldRenderItem();
    boolean shouldRenderBlock();

    ResourceLocation getOBJModelLocation();

}
