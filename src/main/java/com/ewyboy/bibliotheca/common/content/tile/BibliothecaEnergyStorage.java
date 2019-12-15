package com.ewyboy.bibliotheca.common.content.tile;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.energy.EnergyStorage;

public class BibliothecaEnergyStorage extends EnergyStorage {

    private static final String NBT_KEY_ENERGY_INFO = "energy-info";
    private static final String NBT_KEY_CAPACITY = "capacity";
    private static final String NBT_KEY_MAX_RECEIVE = "maxReceive";
    private static final String NBT_KEY_MAX_EXTRACT = "maxExtract";
    private static final String NBT_KEY_ENERGY = "energy";

    public BibliothecaEnergyStorage(int capacity) {
        super(capacity);
    }

    public BibliothecaEnergyStorage(int capacity, int maxTransfer) {
        super(capacity, maxTransfer);
    }

    public BibliothecaEnergyStorage(int capacity, int maxReceive, int maxExtract) {
        super(capacity, maxReceive, maxExtract);
    }

    public BibliothecaEnergyStorage(int capacity, int maxReceive, int maxExtract, int energy) {
        super(capacity, maxReceive, maxExtract, energy);
    }

    public void save(CompoundNBT compound) {
        CompoundNBT myInfo = new CompoundNBT();
        myInfo.putInt(NBT_KEY_CAPACITY, capacity);
        myInfo.putInt(NBT_KEY_MAX_RECEIVE, maxReceive);
        myInfo.putInt(NBT_KEY_MAX_EXTRACT, maxExtract);
        myInfo.putInt(NBT_KEY_ENERGY, energy);
        compound.put(NBT_KEY_ENERGY_INFO, myInfo);
    }


    public void load(CompoundNBT compound) {
        if (!compound.contains(NBT_KEY_ENERGY_INFO)) return;
        CompoundNBT myInfo = compound.getCompound(NBT_KEY_ENERGY_INFO);
        myInfo.putInt(NBT_KEY_CAPACITY, capacity);
        myInfo.putInt(NBT_KEY_MAX_RECEIVE, maxReceive);
        myInfo.putInt(NBT_KEY_MAX_EXTRACT, maxExtract);
        myInfo.putInt(NBT_KEY_ENERGY, energy);
    }
}
