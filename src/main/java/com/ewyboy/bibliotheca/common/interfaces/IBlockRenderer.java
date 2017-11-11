package com.ewyboy.bibliotheca.common.interfaces;

/**
 * Created by EwyBoy
 */
public interface IBlockRenderer {

    int[] modelMetas();

    void registerBlockRenderer();

    void registerBlockItemRenderer();
}