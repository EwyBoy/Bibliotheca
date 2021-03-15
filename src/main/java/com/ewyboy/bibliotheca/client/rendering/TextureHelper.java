package com.ewyboy.bibliotheca.client.rendering;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidStack;

public class TextureHelper {

    @OnlyIn(Dist.CLIENT)
    public static TextureAtlasSprite getFluidTexture(FluidStack fluid) {
        final ResourceLocation textureLocation = fluid.getFluid().getRegistryName();
        return getTextureAtlasLocation(textureLocation);
    }

    @OnlyIn(Dist.CLIENT)
    public static TextureAtlasSprite getFluidTexture(Fluid fluid) {
        final ResourceLocation textureLocation = fluid.getRegistryName();
        return getTextureAtlasLocation(textureLocation);
    }

    @OnlyIn(Dist.CLIENT)
    public static TextureAtlasSprite getTextureAtlasLocation(final ResourceLocation textureLocation) {
        return (TextureAtlasSprite) Minecraft.getInstance().getAtlasSpriteGetter(textureLocation);
    }

}
