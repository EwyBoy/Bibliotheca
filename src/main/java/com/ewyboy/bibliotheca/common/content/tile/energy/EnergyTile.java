package com.ewyboy.bibliotheca.common.content.tile.energy;

import com.ewyboy.bibliotheca.common.content.tile.BaseTileEntity;
import com.ewyboy.bibliotheca.util.LazyOptionalHelper;
import com.google.common.collect.ImmutableSet;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;

import static com.ewyboy.bibliotheca.common.content.tile.NbtKeys.ENERGY;

public abstract class EnergyTile<E extends BibliothecaBattery> extends BaseTileEntity {

    public EnergyTile(TileEntityType<?> type) {
        super(type);
    }

    private final E battery = createNewBattery();
    public abstract E createNewBattery();
    protected E getBattery() {
        markDirty();
        return battery;
    }

    private LazyOptional<IEnergyStorage> energyOptional = LazyOptional.empty();
    private LazyOptional<IEnergyStorage> getLazyEnergy() {
        if (!energyOptional.isPresent()) {
            energyOptional = LazyOptional.of(() -> battery);
        }
        return energyOptional;
    }

    @Override
    protected void invalidateCaps() {
        super.invalidateCaps();
        energyOptional.invalidate();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side) {
        return LazyOptionalHelper.findFirst(ImmutableSet.of(
                () -> ENERGY_CAP.orEmpty(cap, getLazyEnergy()),
                () -> super.getCapability(cap, side)
        )).cast();
    }

    @Override
    public void readSharedNBT(CompoundNBT compound) {
        battery.deserializeNBT(compound.getCompound(ENERGY));
    }

    @Override
    public void writeSharedNBT(CompoundNBT compound) {
        compound.put(ENERGY, battery.serializeNBT());
    }
}
