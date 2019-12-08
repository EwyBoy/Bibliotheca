package com.ewyboy.bibliotheca;

import com.ewyboy.bibliotheca.proxy.ClientProxy;
import com.ewyboy.bibliotheca.proxy.CommonProxy;
import com.ewyboy.bibliotheca.proxy.IModProxy;
import com.ewyboy.bibliotheca.util.Reference;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Reference.ModInfo.MOD_ID)
public class Bibliotheca {

    private static final IModProxy proxy = DistExecutor.runForDist(
        () -> ClientProxy :: new,
        () -> CommonProxy :: new
    );

    public Bibliotheca() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this :: setup);
        proxy.construct();
    }

    private void setup(final FMLCommonSetupEvent event) {
        proxy.setup();
    }

}
