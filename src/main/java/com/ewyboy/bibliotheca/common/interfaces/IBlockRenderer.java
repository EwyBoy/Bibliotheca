package com.ewyboy.bibliotheca.common.interfaces;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by EwyBoy
 */
public interface IBlockRenderer {

    int[] modelMetas();

    @SideOnly(Side.CLIENT)
    void registerBlockRenderer();

    @SideOnly(Side.CLIENT)
    void registerBlockItemRenderer();
}