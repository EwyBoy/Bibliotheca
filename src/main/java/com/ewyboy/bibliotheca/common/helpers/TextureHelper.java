package com.ewyboy.bibliotheca.common.helpers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TextureHelper {

    @OnlyIn(Dist.CLIENT)
    public static TextureAtlasSprite getTextureAtlasLocation(final ResourceLocation textureLocation) {
        return Minecraft.getInstance().getTextureMap().getAtlasSprite(textureLocation.toString());
    }

}
