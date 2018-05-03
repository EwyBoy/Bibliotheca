package com.ewyboy.bibliotheca.common.network;

import com.ewyboy.bibliotheca.common.loaders.PacketLoader;
import com.ewyboy.bibliotheca.common.tile.TileEntityBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by EwyBoy
 */
public class PacketRequestUpdateTileEntity extends PacketBase {

    private BlockPos pos;
    private int dim;

    public PacketRequestUpdateTileEntity() {super(Side.SERVER);}

    public PacketRequestUpdateTileEntity(BlockPos pos, int dim) {
        super(Side.SERVER);
        this.pos = pos;
        this.dim = dim;
    }

    public PacketRequestUpdateTileEntity(TileEntityBase te) {
        this(te.getPos(), te.getWorld().provider.getDimension());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(pos.toLong());
        buf.writeInt(dim);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        pos = BlockPos.fromLong(buf.readLong());
        dim = buf.readInt();
    }

    public static class Handler implements IMessageHandler<PacketRequestUpdateTileEntity, PacketUpdateTileEntity> {
        @Override
        public PacketUpdateTileEntity onMessage(PacketRequestUpdateTileEntity message, MessageContext ctx) {
            MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
            int dim = message.dim;
            BlockPos pos = message.pos;
            server.addScheduledTask(() -> {
                TileEntity te = server.getWorld(dim).getTileEntity(pos);
                if (te instanceof TileEntityBase) {
                    PacketLoader.wrapper.sendToAllAround(new PacketUpdateTileEntity((TileEntityBase)te), new NetworkRegistry.TargetPoint(dim, pos.getX(), pos.getY(), pos.getZ(), 64));
                }
            });
            return null;
        }
    }

}
