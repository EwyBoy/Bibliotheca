package com.ewyboy.bibliotheca.common.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

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

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
        tooltip.add("Meta: " + this.getMetadata(stack));
    }
}
