package com.ewyboy.bibliotheca;

import com.ewyboy.bibliotheca.common.content.block.BaseTileBlock;

public class TestTileBlock extends BaseTileBlock<TestTile> {

    public TestTileBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected Class<TestTile> getTileClass() {
        return TestTile.class;
    }
}
