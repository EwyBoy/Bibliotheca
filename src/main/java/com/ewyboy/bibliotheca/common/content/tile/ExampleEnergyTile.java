package com.ewyboy.bibliotheca.common.content.tile;

import com.ewyboy.bibliotheca.util.LazyOptionalHelper;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ExampleEnergyTile extends TileEntity {

    private static final int capacity = 3000;
    private static final int maxIn = 500;
    private ConsumerEnergyStorage battery = new ConsumerEnergyStorage(capacity, maxIn);
    private LazyOptional<IEnergyStorage> energyOptional = LazyOptional.of(() -> battery);

    public ExampleEnergyTile() {
        super(null); // Note: this is not how it should be in the real ones
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        battery.save(compound);
        return super.write(compound);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        battery.load(nbt);
        super.read(state, nbt);
    }

    @Override
    protected void invalidateCaps() {
        super.invalidateCaps();
        energyOptional.invalidate();
    }

    private LazyOptional<IEnergyStorage> getEnergyOptional() {
        if (!energyOptional.isPresent()) {
            energyOptional = LazyOptional.of(() -> battery);
        }
        return energyOptional;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return LazyOptionalHelper.findFirst(ImmutableSet.of (
                () -> CapabilityEnergy.ENERGY.orEmpty(cap, getEnergyOptional()),
                () -> super.getCapability(cap, side)
        )).cast();
    }
}
