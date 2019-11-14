package com.ewyboy.bibliotheca.common.compatibilities.waila;

import com.ewyboy.bibliotheca.common.loaders.BlockLoader;
import com.ewyboy.bibliotheca.common.utility.Reference;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInterModComms;

import javax.annotation.Nonnull;
import java.util.List;

/** Created by EwyBoy **/
public class WailaCompatibility implements IWailaDataProvider {

    public static final WailaCompatibility INSTANCE = new WailaCompatibility();

    private WailaCompatibility() {}

    private static boolean registered;
    private static boolean loaded;

    public static void load(IWailaRegistrar registrar) {
        if (!registered) {
            throw new RuntimeException("Please register this handler using the provided method.");
        }

        if (!loaded) {
            BlockLoader.BLOCKS.values().stream().filter(block -> block instanceof IWailaInformationUser).forEachOrdered(block -> registrar.registerBodyProvider(INSTANCE, block.getClass()));
            BlockLoader.BLOCKS.values().stream().filter(block -> block instanceof IWailaCamouflageUser).forEachOrdered(block -> registrar.registerStackProvider(INSTANCE, block.getClass()));
            loaded = true;
        }
    }

    public static void register() {
        if (registered) return; registered = true;
        FMLInterModComms.sendMessage("waila", "register", Reference.Path.WAILA_PATH);
    }


    @Override
    @Nonnull
    public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, BlockPos pos) {
        return tag;
    }

    @Override
    @Nonnull
    public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
        Block block = accessor.getBlock();
        return block instanceof IWailaCamouflageUser ? ((IWailaCamouflageUser) block).getWailaStack(accessor, config) : null;
    }

    @Override
    @Nonnull
    public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return currenttip;
    }

    @Override
    @Nonnull
    public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        Block block = accessor.getBlock();
        if (block instanceof IWailaInformationUser) {
            return ((IWailaInformationUser) block).getWailaBody(itemStack, currenttip, accessor, config);
        }
        return currenttip;
    }

    @Override
    @Nonnull
    public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return currenttip;
    }
}
