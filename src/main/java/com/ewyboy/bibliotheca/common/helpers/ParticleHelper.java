package com.ewyboy.bibliotheca.common.helpers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ParticleHelper {

    @OnlyIn(Dist.CLIENT)
    public static void spawnParticle(Particle particle) {
        Minecraft.getInstance().particles.addEffect(particle);
    }

    @OnlyIn(Dist.CLIENT)
    public static void spawnParticle(World world, double x, double y, double z, ParticleTypes particle, double velocityX, double velocityY, double velocityZ) {
        world.addParticle((IParticleData) particle, x + 0.5, y, z + 0.5, velocityX, velocityY, velocityZ);
    }

}
