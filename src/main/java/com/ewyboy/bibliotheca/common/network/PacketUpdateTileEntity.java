package com.ewyboy.bibliotheca.common.network;

import com.ewyboy.bibliotheca.common.tile.TileEntityBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by EwyBoy
 */
public class PacketUpdateTileEntity extends PacketBase {

    private int dim;
    private BlockPos pos;
    private NBTTagCompound tag;

    public PacketUpdateTileEntity() {super(Side.CLIENT);}

    public PacketUpdateTileEntity(int dim, BlockPos pos, NBTTagCompound tag) {
        super(Side.CLIENT);
        this.dim = dim;
        this.pos = pos;
        this.tag = tag;
    }

    public PacketUpdateTileEntity(TileEntityBase te) {
        this(te.getWorld().provider.getDimension(), te.getPos(), te.writeToNBT(new NBTTagCompound()));
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(dim);
        buf.writeLong(pos.toLong());
        ByteBufUtils.writeTag(buf, tag);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        dim = buf.readInt();
        pos = BlockPos.fromLong(buf.readLong());
        tag = ByteBufUtils.readTag(buf);
    }

    public static class Handler implements IMessageHandler<PacketUpdateTileEntity, IMessage> {
        @Override
        public IMessage onMessage(PacketUpdateTileEntity message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                TileEntity te = Minecraft.getMinecraft().world.getTileEntity(message.pos);
                if (te instanceof TileEntityBase) {
                    te.readFromNBT(message.tag);
                }
            });
            return null;
        }
    }
}
