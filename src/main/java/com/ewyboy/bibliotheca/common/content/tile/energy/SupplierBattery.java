package com.ewyboy.bibliotheca.common.content.tile.energy;

/**
 * This is a battery for use in machines that do not want to receive energy. An example would be a generator.
 */
public class SupplierBattery extends BibliothecaBattery {
    public SupplierBattery(int capacity) {
        super(capacity, 0, capacity, 0);
    }

    public SupplierBattery(int capacity, int maxOut) {
        super(capacity, 0, maxOut, 0);
    }

    /**
     * This is a safe way to generate energy in this battery.
     * @param amount the amount of energy to generate
     * @return whether there was enough room left for the generated energy
     */
    public boolean generate(int amount) {
        if (this.energy + amount > this.capacity) {
            return false;
        }
        onModify();
        this.energy += amount;
        return true;
    }

    /**
     * This is an unsafe way to generate energy in this battery. It forces the battery to fill if there is any room left.
     * @param amount the amount of energy to generate
     */
    public void forceGenerate(int amount) {
        onModify();
        this.energy += Math.min(amount, this.capacity - this.energy);
    }
}
