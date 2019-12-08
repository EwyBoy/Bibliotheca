package com.ewyboy.bibliotheca.common.content.tile;

import javax.annotation.Nonnull;

import com.ewyboy.bibliotheca.common.network.dispatcher.VanillaMessageDispatcher;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public abstract class BaseTileEntity extends TileEntity {

    public BaseTileEntity(TileEntityType tileEntityType) {
        super(tileEntityType);
    }

    @Nonnull
    @Override
    public CompoundNBT write(CompoundNBT compound) {
        CompoundNBT nbt = super.write(compound);
        writeSharedNBT(compound);
        return nbt;
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        readSharedNBT(compound);
    }

    public void writeSharedNBT(CompoundNBT compound) {}

    public void readSharedNBT(CompoundNBT compound) {}

    public void sync() {
        VanillaMessageDispatcher.dispatchTEToNearbyPlayers(this);
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT cmp = new CompoundNBT();
        writeSharedNBT(cmp);
        return new SUpdateTileEntityPacket(getPos(), 0, cmp);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket packet) {
        super.onDataPacket(net, packet);
        readSharedNBT(packet.getNbtCompound());
    }

}