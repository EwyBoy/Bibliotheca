package com.ewyboy.bibliotheca.common.content.tile.energy;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.energy.EnergyStorage;

import static com.ewyboy.bibliotheca.common.content.tile.NbtKeys.*;

public abstract class BibliothecaBattery extends EnergyStorage implements IBibEnergyStorage {

    public BibliothecaBattery(int capacity) {
        super(capacity);
    }

    public BibliothecaBattery(int capacity, int maxTransfer) {
        super(capacity, maxTransfer);
    }

    public BibliothecaBattery(int capacity, int maxReceive, int maxExtract) {
        super(capacity, maxReceive, maxExtract);
    }

    public BibliothecaBattery(int capacity, int maxReceive, int maxExtract, int energy) {
        super(capacity, maxReceive, maxExtract, energy);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        onModify();
        return super.extractEnergy(maxExtract, simulate);
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        onModify();
        return super.receiveEnergy(maxReceive, simulate);
    }

    public abstract void onModify();

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt(CAPACITY, capacity);
        nbt.putInt(MAX_RECEIVE, maxReceive);
        nbt.putInt(MAX_EXTRACT, maxExtract);
        nbt.putInt(STORED, energy);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        nbt.putInt(CAPACITY, capacity);
        nbt.putInt(MAX_RECEIVE, maxReceive);
        nbt.putInt(MAX_EXTRACT, maxExtract);
        nbt.putInt(STORED, energy);
    }
}
