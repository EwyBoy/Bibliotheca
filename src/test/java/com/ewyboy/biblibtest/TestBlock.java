package com.ewyboy.biblibtest;

import com.ewyboy.bibliotheca.common.content.block.BaseTileBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class TestBlock extends BaseTileBlock<TestTile> {

    @Override
    protected BlockEntityType.BlockEntitySupplier<TestTile> getTileSupplier() {
        return TestTile :: new;
    }

    public TestBlock(Properties properties) {
        super(properties);
    }

}
