package com.ewyboy.bibliotheca.client.particles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/**
 * Created by EwyBoy
 */
public class ParticleAnyTexture extends Particle {

    public ParticleAnyTexture(World world, ItemStack resourceDomainStack, String texturePath, double x, double y, double z, float scale, float gravity, Vec3d velocity, int maxAge, float sizeX, float sizeY) {
        super(world, x, y, z, velocity.x, velocity.y, velocity.z);
        particleGravity = gravity;
        this.particleMaxAge = maxAge;
        setSize(sizeX, sizeY);
        this.particleScale = scale;
        this.canCollide = true;
        motionX = velocity.x;
        motionY = velocity.y;
        motionZ = velocity.z;
        String icon = resourceDomainStack.getItem().getRegistryName().getResourceDomain() + ":" + texturePath;
        setParticleTexture(Minecraft.getMinecraft().getTextureMapBlocks().getTextureExtry(icon));
    }

    @Override
    public int getFXLayer() {
        return 1;
    }
}
