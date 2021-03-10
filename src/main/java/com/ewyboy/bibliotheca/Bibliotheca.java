package com.ewyboy.bibliotheca;

import com.ewyboy.bibliotheca.client.BibModelLoader;
import com.ewyboy.bibliotheca.common.event.EventHandler;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static com.ewyboy.bibliotheca.Bibliotheca.ID;

@Mod(ID)
public class Bibliotheca {

    public static final String ID = "bibliotheca";
    public static final String NAME = "Bibliotheca";

    public Bibliotheca() {
        EventHandler.MOD.register(this :: onCommonSetup);
        EventHandler.MOD.register(this :: onClientSetup);

        //LootConditions.init();

        DistExecutor.callWhenOn(Dist.CLIENT, () -> () -> {
            FMLJavaModLoadingContext.get().getModEventBus().addListener(BibModelLoader :: initRenderTypes);
            FMLJavaModLoadingContext.get().getModEventBus().addListener(BibModelLoader :: initSpecialRenders);
            return null;
        });
    }

    private void onCommonSetup(final FMLCommonSetupEvent event) {

    }

    private void onClientSetup(final FMLClientSetupEvent event) {

    }

}
