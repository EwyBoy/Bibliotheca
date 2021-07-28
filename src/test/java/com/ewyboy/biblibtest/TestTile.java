package com.ewyboy.biblibtest;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TestTile extends BlockEntity {

    public TestTile(BlockPos pos, BlockState state) {
        super(BibliothecaTest.Tiles.TEST_TILE, pos, state);
    }
}
