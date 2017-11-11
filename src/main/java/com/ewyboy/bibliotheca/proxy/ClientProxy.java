package com.ewyboy.bibliotheca.proxy;

import com.ewyboy.bibliotheca.client.InternalModelLoader;
import net.minecraft.client.Minecraft;
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
