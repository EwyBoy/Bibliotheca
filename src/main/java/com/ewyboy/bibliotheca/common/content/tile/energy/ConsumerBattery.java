package com.ewyboy.bibliotheca.common.content.tile.energy;

/**
 * This is a battery for use in machines that do not want to have energy taken from them. For example, a furnace.
 */
public abstract class ConsumerBattery extends BibliothecaBattery {
    public ConsumerBattery(int capacity) {
        super(capacity, capacity, 0, 0);
    }

    public ConsumerBattery(int capacity, int maxReceive) {
        super(capacity, maxReceive, 0);
    }

    /**
     * This is a safe way for you to try and use energy.
     * @param amount The amount of energy you want to use
     * @return whether there was enough energy to use the specified amount
     */
    public boolean useEnergy(int amount) {
        if (this.energy < amount) {
            return false;
        }
        onModify();
        this.energy -= amount;
        return true;
    }
}
