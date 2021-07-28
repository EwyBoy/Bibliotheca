package com.ewyboy.bibliotheca.common.helpers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ParticleHelper {

    @OnlyIn(Dist.CLIENT)
    public static void spawnParticle(Particle particle) {
        Minecraft.getInstance().particleEngine.add(particle);
    }

    @OnlyIn(Dist.CLIENT)
    public static void spawnParticle(Level world, double x, double y, double z, ParticleTypes particle, double velocityX, double velocityY, double velocityZ) {
        world.addParticle((ParticleOptions) particle, x + 0.5, y, z + 0.5, velocityX, velocityY, velocityZ);
    }

}
