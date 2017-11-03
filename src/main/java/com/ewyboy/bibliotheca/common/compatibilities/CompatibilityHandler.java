package com.ewyboy.bibliotheca.common.compatibilities;

import com.ewyboy.bibliotheca.common.compatibilities.waila.WailaCompatibility;
import net.minecraftforge.fml.common.Loader;

/** Created by EwyBoy **/
public class CompatibilityHandler {
    public static void registerWaila() {
        if (Loader.isModLoaded("waila")) {
            WailaCompatibility.register();
        }
    }
}