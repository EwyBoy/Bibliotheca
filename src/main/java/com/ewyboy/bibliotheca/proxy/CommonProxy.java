package com.ewyboy.bibliotheca.proxy;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy implements IModProxy {

    public Side getSide(){return Side.SERVER;}

    public int getParticleSettings() {return 3;}

    public void spawnParticle(World worldObj, ItemStack resourceDomainStack, String texturePath, double x, double y, double z, float scale, float gravity, Vec3d velocity, int maxAge, float sizeX, float sizeY) {}

    public void preInit(FMLPreInitializationEvent event) {}

    public void init(FMLInitializationEvent event) {}

    public void postInit(FMLPostInitializationEvent event) {}
}
