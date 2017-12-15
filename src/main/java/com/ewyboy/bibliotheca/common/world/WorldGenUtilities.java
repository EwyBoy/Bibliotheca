package com.ewyboy.bibliotheca.common.world;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

import static net.minecraft.init.Blocks.DIRT;
import static net.minecraft.init.Blocks.GRASS;

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
            foundGround = blockAt.equals(DIRT) || blockAt.equals(GRASS);
        }
        return y;
    }

    private void generate(WorldGenerator worldGenerator, World world, Random random, int blockX, int blockZ, int minimumSpawnHeight, int randomMin, int randomMax) {
        int numberOfStuff = randomMin + random.nextInt(randomMax - randomMin);
        for (int i = 0; i < numberOfStuff; i++) {
            int randX = blockX + random.nextInt(16);
            int randZ = blockZ + random.nextInt(16);
            worldGenerator.generate(world, random, new BlockPos(randX, minimumSpawnHeight ,randZ));
        }
    }

    private void generateUnderGround(
            Block block, World world, Random random,
            int blockX, int blockZ,
            int minSpawnHeight, int maxSpawnHeight,
            int minSpawnRate, int maxSpawnRate,
            int minVeinSize, int maxVeinSize
    ) {
        int spawnRate = minSpawnRate + random.nextInt(maxSpawnRate - minSpawnRate);
        int veinSize = minVeinSize + random.nextInt(maxVeinSize - minVeinSize);
        for (int i = 0; i < spawnRate; i++) {
            int randX = blockX + random.nextInt(16);
            int randZ = blockZ + random.nextInt(16);
            int randY = minSpawnHeight + random.nextInt(maxSpawnHeight - minSpawnHeight);

            new WorldGenMinable(block.getDefaultState(), veinSize).generate(world, random, new BlockPos(randX, randY, randZ));
        }
    }
}