package com.ewyboy.bibliotheca.common.utility;

import net.minecraft.client.resources.I18n;

/**
 * Created by EwyBoy
 */
public class LangHelper {

    public static String lang(String modID, String key) {
        return I18n.format(modID + "." + key);
    }

}
