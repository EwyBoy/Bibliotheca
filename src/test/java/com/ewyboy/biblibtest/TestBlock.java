package com.ewyboy.biblibtest;

import com.ewyboy.bibliotheca.common.content.block.BaseBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class TestBlock extends BaseBlockEntity<TestTile> {

    @Override
    protected BlockEntityType.BlockEntitySupplier<TestTile> getTileSupplier() {
        return TestTile :: new;
    }

    public TestBlock(Properties properties) {
        super(properties);
    }

}
