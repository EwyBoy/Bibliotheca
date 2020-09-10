package com.ewyboy.bibliotheca;

import com.ewyboy.bibliotheca.client.BibModelLoader;
import com.ewyboy.bibliotheca.common.event.EventHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

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
        event.enqueueWork(BibModelLoader::initSpecialRenders);
    }
}
