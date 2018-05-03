package com.ewyboy.bibliotheca.common.item;

import net.minecraft.item.ItemStack;

/**
 * Created by EwyBoy
 */
public class ItemBaseFuel extends ItemBase {

    private int burnTime;

    public ItemBaseFuel(String name, int burnTime) {
        super(name);
        this.burnTime = burnTime;
    }

    @Override
    public int getItemBurnTime(ItemStack itemStack) {
        return burnTime;
    }
}
