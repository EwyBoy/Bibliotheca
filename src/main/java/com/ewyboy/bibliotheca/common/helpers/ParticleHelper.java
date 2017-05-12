package com.ewyboy.bibliotheca.common.helpers;

import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

/**
 * Created by EwyBoy
 */
public class ParticleHelper {

    public static void spawnParticle(World world, double x, double y, double z, EnumParticleTypes particle, double velocityX, double velocityY, double velocityZ) {
        world.spawnParticle(particle, x + 0.5, y, z + 0.5, velocityX, velocityY, velocityZ);
    }
}
