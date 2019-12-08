package com.ewyboy.bibliotheca.common.content.tile;

import com.ewyboy.bibliotheca.util.ModLogger;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;

public abstract class EnergyBaseTileEntity extends BaseTileEntity implements IEnergyStorage {

    private static EnergyStorage storage;

    public EnergyStorage getStorage() {
        return storage;
    }

    public static void setStorage(EnergyStorage storage) {
        EnergyBaseTileEntity.storage = storage;
    }

    public EnergyBaseTileEntity(TileEntityType tileEntityType) {
        super(tileEntityType);
    }

    public EnergyBaseTileEntity(TileEntityType tileEntityType, int capacity) {
        super(tileEntityType);
        storage = new EnergyStorage(capacity);
    }

    public EnergyBaseTileEntity(TileEntityType tileEntityType, int capacity, int maxTransfer) {
        super(tileEntityType);
        storage = new EnergyStorage(capacity, maxTransfer);
    }

    public EnergyBaseTileEntity(TileEntityType tileEntityType, int capacity, int maxTransfer, int maxExtract) {
        super(tileEntityType);
        storage = new EnergyStorage(capacity, maxTransfer, maxExtract);
    }

    public EnergyBaseTileEntity(TileEntityType tileEntityType, int capacity, int maxTransfer, int maxExtract, int energy) {
        super(tileEntityType);
        storage = new EnergyStorage(capacity, maxTransfer, maxExtract, energy);
    }

    public void generateEnergy(int amount) {
        if (storage.getEnergyStored() < storage.getMaxEnergyStored()) {
            storage.receiveEnergy(amount, false);
        }
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(pos, 0, write(new CompoundNBT()));
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket packet) {
        this.read(packet.getNbtCompound());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return this.write(new CompoundNBT());
    }

    @Nonnull
    @Override
    public CompoundNBT write(CompoundNBT compound) {
        ModLogger.info("Writing data into NBT ({})", pos);
        compound.putInt("energy", storage.getEnergyStored());
        return compound;
    }

    @Override
    public void read(CompoundNBT compound) {
        ModLogger.info("Restoring data from NBT {}", compound);
        super.read(compound);
        if (compound.contains("energy")) {
            generateEnergy(compound.getInt("energy"));
        }
    }

    @Override
    public void sync() {
        super.sync();
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return storage.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return storage.extractEnergy(maxExtract, simulate);
    }

    @Override
    public int getEnergyStored() {
        return storage.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored() {
        return storage.getMaxEnergyStored();
    }

    @Override
    // Override this to send energy
    public boolean canExtract() {
        return false;
    }

    @Override
    // Override this to receive energy
    public boolean canReceive() {
        return false;
    }
}
