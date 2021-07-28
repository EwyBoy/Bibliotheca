package com.ewyboy.bibliotheca.common.content.item;

import net.minecraft.world.item.ItemStack;

import net.minecraft.world.item.crafting.RecipeType;

import javax.annotation.Nullable;

public class BaseFuelItem extends BaseItem {

    private int burnTime;

    public BaseFuelItem(Properties properties, int burnTime) {
        super(properties);
        this.burnTime = burnTime;
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return burnTime;
    }
}
