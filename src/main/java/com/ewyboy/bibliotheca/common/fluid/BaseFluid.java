package com.ewyboy.bibliotheca.common.fluid;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

/**
 * Created by EwyBoy
 */
public class BaseFluid extends Fluid {

    public BaseFluid(String MOD_ID, String fluidName, int viscosity, int density, int luminosity) {
        super(fluidName,
                new ResourceLocation(MOD_ID + ":" + "blocks" + "/" + fluidName + "_still"),
                new ResourceLocation(MOD_ID + ":" + "blocks" + "/" + fluidName + "_flow")
        );
        this.setViscosity(viscosity);
        this.setDensity(density);
        this.setLuminosity(luminosity);
        FluidRegistry.registerFluid(this);
    }
}
