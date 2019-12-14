package com.ewyboy.bibliotheca.client.interfaces;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IEnviromentBlockReader;

import javax.annotation.Nullable;

public interface IBlockColorizer extends IBlockColor {

    Item itemToColor();
    Block blockToColor();

    boolean isItemBlockColored();

    @Override
    int getColor(BlockState blockState, @Nullable IEnviromentBlockReader iEnviromentBlockReader, @Nullable BlockPos blockPos, int i);

}
