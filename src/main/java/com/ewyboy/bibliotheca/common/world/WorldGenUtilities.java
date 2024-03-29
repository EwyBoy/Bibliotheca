package com.ewyboy.bibliotheca.common.world;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public class WorldGenUtilities {

    public static int getBlockFromAbove(Level world, int x, int z, Block targetBlock, int startingPosY) {
        int y = startingPosY;
        boolean foundBlock = false;
        while (!foundBlock && y-- >= 0) {
            Block blockAt = world.getBlockState(new BlockPos(x,y,z)).getBlock();
            foundBlock = blockAt.equals(targetBlock);
        }
        return y;
    }

    public static int getGroundFromAbove(Level world, int x, int z) {
        int y = 255;
        boolean foundGround = false;
        while(!foundGround && y-- >= 0) {
            Block blockAt = world.getBlockState(new BlockPos(x,y,z)).getBlock();
            foundGround = blockAt.equals(Blocks.DIRT) || blockAt.equals(Blocks.GRASS_BLOCK);
        }
        return y;
    }

}