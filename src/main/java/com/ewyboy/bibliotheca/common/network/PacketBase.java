package com.ewyboy.bibliotheca.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by EwyBoy
 */
public class PacketBase implements IMessage {

    public Side side;

    public PacketBase() {}

    public PacketBase(Side side) {
        this.side = side;
    }

    public Side getSide() {
        return side;
    }

    @Override
    public void fromBytes(ByteBuf buf) {}

    @Override
    public void toBytes(ByteBuf buf) {}

}
