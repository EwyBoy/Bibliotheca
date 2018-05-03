package com.ewyboy.bibliotheca.common.tile;

import com.ewyboy.bibliotheca.common.loaders.PacketLoader;
import com.ewyboy.bibliotheca.common.network.PacketUpdateTileEntity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.network.NetworkRegistry;

/**
 * Created by EwyBoy
 **/
public abstract class TileEntityBase extends TileEntity {

    public void sync() {
        PacketLoader.wrapper.sendToAllAround(new PacketUpdateTileEntity(this), new NetworkRegistry.TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 64));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        return tag;
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
    }
}