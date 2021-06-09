package com.ewyboy.bibliotheca.client.interfaces;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface IItemColorizer extends IItemColor {

    Item itemToColor();

    int getColor(ItemStack itemStack, int tint);
}
