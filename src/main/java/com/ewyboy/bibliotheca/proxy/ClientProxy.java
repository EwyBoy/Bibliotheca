package com.ewyboy.bibliotheca.proxy;

import com.ewyboy.bibliotheca.client.InternalModelLoader;
import com.ewyboy.bibliotheca.client.particles.ParticleAnyTexture;
import com.ewyboy.bibliotheca.common.helpers.ParticleHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy {

    @Override
    public Side getSide() { return Side.CLIENT; }

    @SideOnly(Side.CLIENT)
    public int getParticleSettings() {
        return Minecraft.getMinecraft().gameSettings.particleSetting;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void spawnParticle(World worldObj, ItemStack resourceDomainStack, String texturePath, double x, double y, double z, float scale, float gravity, Vec3d velocity, int maxAge, float sizeX, float sizeY) {
        ParticleHelper.spawnParticle(new ParticleAnyTexture(worldObj, resourceDomainStack, texturePath, x, y, z, scale, gravity, velocity, maxAge, sizeX, sizeY));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        InternalModelLoader.init();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }
}
