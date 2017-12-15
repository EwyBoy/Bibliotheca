package com.ewyboy.bibliotheca.common.item;

import net.minecraft.creativetab.CreativeTabs;

    public class ItemBaseMeta extends ItemBase {

    public ItemBaseMeta(String name, int maxDmg) {
        super(name);
        setMaxDamage(maxDmg);
    }

    public ItemBaseMeta(String name, int maxDmg, CreativeTabs creativeTab) {
        super(name);
        setMaxDamage(maxDmg);
        setCreativeTab(creativeTab);
    }
}
