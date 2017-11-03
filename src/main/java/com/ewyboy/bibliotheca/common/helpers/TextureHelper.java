package com.ewyboy.bibliotheca.common.helpers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TextureHelper {

    @SideOnly(Side.CLIENT)
    public static TextureAtlasSprite getFluidTexture(FluidStack fluid) {
        final ResourceLocation textureLocation = fluid.getFluid().getStill(fluid);
        return getTextureAtlasLocation(textureLocation);
    }

    @SideOnly(Side.CLIENT)
    public static TextureAtlasSprite getFluidTexture(Fluid fluid) {
        final ResourceLocation textureLocation = fluid.getStill();
        return getTextureAtlasLocation(textureLocation);
    }

    @SideOnly(Side.CLIENT)
    public static TextureAtlasSprite getTextureAtlasLocation(final ResourceLocation textureLocation) {
        return Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(textureLocation.toString());
    }
}
