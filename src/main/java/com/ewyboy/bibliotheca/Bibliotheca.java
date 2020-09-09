package com.ewyboy.bibliotheca;

import com.ewyboy.bibliotheca.client.ModelLoader;
import com.ewyboy.bibliotheca.common.event.EventHandler;
import com.ewyboy.bibliotheca.common.loaders.ContentLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

import static com.ewyboy.bibliotheca.Bibliotheca.ID;

@Mod(ID)
public class Bibliotheca {
    public static final String ID = "bibliotheca";
    public static final String NAME = "Bibliotheca";

    public Bibliotheca() {
        EventHandler.MOD.register(this::onCommonSetup);
        EventHandler.MOD.register(this::onClientSetup);
    }

    private void onCommonSetup(final FMLCommonSetupEvent event) {
        // NO-OP
    }

    private void onClientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(ModelLoader::initSpecialRenders);
    }
}
