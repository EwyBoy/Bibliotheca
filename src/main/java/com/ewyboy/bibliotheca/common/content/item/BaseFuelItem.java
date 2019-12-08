package com.ewyboy.bibliotheca.common.content.item;

import net.minecraft.item.ItemStack;

public class BaseFuelItem extends BaseItem {

    private int burnTime;

    public BaseFuelItem(Properties properties, int burnTime) {
        super(properties);
        this.burnTime = burnTime;
    }

    @Override
    public int getBurnTime(ItemStack itemStack) {
        return burnTime;
    }
}
