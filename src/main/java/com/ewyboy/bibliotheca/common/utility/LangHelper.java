package com.ewyboy.bibliotheca.common.utility;

import net.minecraft.client.resources.I18n;

/**
 * Created by EwyBoy
 */
public class LangHelper {

    public static String lang(String modID, String key) {
        return I18n.format(modID + "." + key);
    }

    /*
    * Formats a string to displays capacity info
    * Eks: 3000 / 5000
    */
    public static String formatCapacityInfo(int currentValue, int maxValue) {
        return currentValue + " / " + maxValue;
    }

    /*
    * Formats a string to displays capacity info with a unit postfix
    * Eks: 3000 / 5000 RF
    */
    public static String formatCapacityInfo(int currentValue, int maxValue, String unit) {
        return currentValue + " / " + maxValue + " " + unit;
    }

}
