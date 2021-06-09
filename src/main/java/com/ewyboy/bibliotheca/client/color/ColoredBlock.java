package com.ewyboy.bibliotheca.client.color;

import com.ewyboy.bibliotheca.client.interfaces.IBlockColorizer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockDisplayReader;

import javax.annotation.Nullable;

public class ColoredBlock implements IBlockColorizer {

    private final int colorIndex;
    private final Block block;

    public ColoredBlock(Block block, int tintIndex) {
        this.block = block;
        this.colorIndex = tintIndex;
    }

    public int getColorIndex() {
        return colorIndex;
    }

    public Block getBlock() {
        return block;
    }

    @Override
    public Block blockToColor() {
        return getBlock();
    }

    @Override
    public int getColor(BlockState blockState, @Nullable IBlockDisplayReader iBlockDisplayReader, @Nullable BlockPos blockPos, int tint) {
        return getColorIndex();
    }
}
