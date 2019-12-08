package com.ewyboy.bibliotheca.common.world;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldGenUtilities {

    public static int getBlockFromAbove(World world, int x, int z, Block targetBlock, int startingPosY) {
        int y = startingPosY;
        boolean foundBlock = false;
        while (!foundBlock && y-- >= 0) {
            Block blockAt = world.getBlockState(new BlockPos(x,y,z)).getBlock();
            foundBlock = blockAt.equals(targetBlock);
        }
        return y;
    }

    public static int getGroundFromAbove(World world, int x, int z) {
        int y = 255;
        boolean foundGround = false;
        while(!foundGround && y-- >= 0) {
            Block blockAt = world.getBlockState(new BlockPos(x,y,z)).getBlock();
            foundGround = blockAt.equals(Blocks.DIRT) || blockAt.equals(Blocks.GRASS_BLOCK);
        }
        return y;
    }

}