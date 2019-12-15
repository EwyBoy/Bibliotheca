package com.ewyboy.bibliotheca.common.content.tile.energy;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.IEnergyStorage;

public interface IBibEnergyStorage extends IEnergyStorage, INBTSerializable<CompoundNBT> {
}
