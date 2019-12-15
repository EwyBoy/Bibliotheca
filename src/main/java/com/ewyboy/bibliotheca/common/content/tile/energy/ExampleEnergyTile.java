package com.ewyboy.bibliotheca.common.content.tile.energy;

import net.minecraft.tileentity.ITickableTileEntity;

public class ExampleEnergyTile extends EnergyTile<ConsumerBattery> {
    /**
     * Capacity of the battery, should be in a config somewhere for a real tile
     */
    private static final int capacity = 3000;
    /**
     * Max input of the consuming battery, should be in a config somewhere for a real tile
     */
    private static final int maxIn = 500;

    public ExampleEnergyTile() {
        super(null); // Note: this is not how it should be in the real ones
    }

    @Override
    public ConsumerBattery createNewBattery() {
        return new ConsumerBattery(capacity, maxIn) {
            @Override
            public void onModify() {
                markDirty();
                sync();
            }
        };
    }
}
