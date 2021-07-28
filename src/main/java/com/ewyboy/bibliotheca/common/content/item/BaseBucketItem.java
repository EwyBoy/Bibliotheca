package com.ewyboy.bibliotheca.common.content.item;

import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.capability.wrappers.FluidBucketWrapper;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class BaseBucketItem extends BucketItem {

    public BaseBucketItem(Fluid fluid, Properties builder) {
        super(fluid, builder);
    }

    public BaseBucketItem(Supplier<? extends Fluid> supplier_fluid, Properties builder) {
        super(supplier_fluid, builder);
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new FluidBucketWrapper(stack);
    }

}
