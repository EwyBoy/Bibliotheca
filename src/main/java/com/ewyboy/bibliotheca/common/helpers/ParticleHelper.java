package com.ewyboy.bibliotheca.common.helpers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by EwyBoy
 */
public class ParticleHelper {

    @SideOnly(Side.CLIENT)
    private static void spawnParticle(Particle particle) {
        Minecraft.getMinecraft().effectRenderer.addEffect(particle);
    }

    @SideOnly(Side.CLIENT)
    public static void spawnParticle(World world, double x, double y, double z, EnumParticleTypes particle, double velocityX, double velocityY, double velocityZ) {
        world.spawnParticle(particle, x + 0.5, y, z + 0.5, velocityX, velocityY, velocityZ);
    }
}
