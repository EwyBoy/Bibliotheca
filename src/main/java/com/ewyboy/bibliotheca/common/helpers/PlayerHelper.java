package com.ewyboy.bibliotheca.common.helpers;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by EwyBoy
 */
public class PlayerHelper {

    public static boolean canPlayerFit(World world, BlockPos pos) {
        return world.isAirBlock(pos) && world.isAirBlock(pos.up(1));
    }
}
