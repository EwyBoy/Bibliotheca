package com.ewyboy.bibliotheca.client.interfaces;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockDisplayReader;

import javax.annotation.Nullable;

public interface IBlockColorizer extends IBlockColor {

    Block blockToColor();

    @Override
    int getColor(BlockState state, @Nullable IBlockDisplayReader blockDisplayReader, @Nullable BlockPos pos, int tint);

}
