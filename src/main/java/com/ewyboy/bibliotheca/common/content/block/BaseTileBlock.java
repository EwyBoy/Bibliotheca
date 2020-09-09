package com.ewyboy.bibliotheca.common.content.block;

import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public abstract class BaseTileBlock<T extends TileEntity> extends BaseBlock {

    protected abstract Class<T> getTileClass();

    public BaseTileBlock(Properties properties) {
        super(properties);
    }

    protected T getTileEntity(World world, BlockPos pos) {
        return (T) world.getTileEntity(pos);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        try {
            return getTileClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

}
