package com.ewyboy.bibliotheca.util;

import com.ewyboy.bibliotheca.Bibliotheca;
import net.minecraft.util.ResourceLocation;

public class BibLibResourceLocationHelper {

    public static ResourceLocation prefix(String path) {
        return new ResourceLocation(Bibliotheca.ID, path);
    }

}
