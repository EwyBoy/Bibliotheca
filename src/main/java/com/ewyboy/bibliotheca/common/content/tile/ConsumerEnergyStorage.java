package com.ewyboy.bibliotheca.common.content.tile;

public class ConsumerEnergyStorage extends BibliothecaEnergyStorage {
    public ConsumerEnergyStorage(int capacity) {
        super(capacity);
    }

    public ConsumerEnergyStorage(int capacity, int maxReceive) {
        super(capacity, maxReceive, 0);
    }

    public void addEnergy(int amount) {
        energy += amount;
    }

    public void useEnergy(int amount) {
        addEnergy(-amount);
    }

}
