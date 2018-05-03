package com.ewyboy.bibliotheca.common.item;

import com.ewyboy.bibliotheca.common.interfaces.IItemRenderer;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;

/**
 * Created by EwyBoy
 */
public class ItemBaseSeed extends ItemSeeds implements IItemRenderer {

    private String name;
    public int dropWeight;

    public ItemBaseSeed(String name, Block crops, Block soil) {
        super(crops, soil);
        this.name = name;
    }

    public ItemBaseSeed(String name, Block crops, Block soil, int dropWeight) {
        super(crops, soil);
        this.name = name;
        this.dropWeight = dropWeight;
        MinecraftForge.addGrassSeed(new ItemStack(this), dropWeight);
    }

    public String getName() {
        return name;
    }

    @Override
    public int[] modelMetas() {
        return new int[0];
    }

    @Override
    public void registerItemRenderer() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
