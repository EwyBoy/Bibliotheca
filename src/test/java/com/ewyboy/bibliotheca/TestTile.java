package com.ewyboy.bibliotheca;

import com.ewyboy.bibliotheca.BibliothecaTest.Tiles;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class TestTile  extends TileEntity {
    public TestTile() {
        super(Tiles.TEST_BLOCK);
    }
}
