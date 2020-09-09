package com.ewyboy.bibliotheca.common.event;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.GenericEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class EventHandler {

    public static final EventHandler FORGE = new EventHandler(() -> MinecraftForge.EVENT_BUS);
    // WARNING: DO NOT CHANGE THIS TO USE A METHOD REFERENCE
    public static final EventHandler MOD = new EventHandler(() -> FMLJavaModLoadingContext.get().getModEventBus());
    private final Supplier<IEventBus> bus;

    public EventHandler(Supplier<IEventBus> bus) {
        this.bus = bus;
    }

    public <T extends Event> void register(Consumer<T> consumer) {
        bus.get().addListener(consumer);
    }

    public <T extends GenericEvent<? extends F>, F> void registerGeneric(Class<F> genericClassFilter, Consumer<T> consumer) {
        bus.get().addGenericListener(genericClassFilter, consumer);
    }

}
