package com.ewyboy.bibliotheca.common.content.tile;

import javax.annotation.Nonnull;

import com.ewyboy.bibliotheca.common.network.dispatcher.VanillaMessageDispatcher;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public abstract class BaseBlockEntity extends BlockEntity {

    public BaseBlockEntity(BlockEntityType tileEntityType, BlockPos pos, BlockState state) {
        super(tileEntityType, pos, state);
    }

    @Nonnull
    @Override
    public CompoundTag save(CompoundTag compound) {
        CompoundTag nbt = super.save(compound);
        writeSharedNBT(compound);
        return nbt;
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        readSharedNBT(tag);
    }

    public void writeSharedNBT(CompoundTag tag) {}

    public void readSharedNBT(CompoundTag tag) {}

    public void sync() {
        VanillaMessageDispatcher.dispatchTEToNearbyPlayers(this);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
       return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket packet) {
        super.onDataPacket(net, packet);
        readSharedNBT(packet.getTag());
    }

}