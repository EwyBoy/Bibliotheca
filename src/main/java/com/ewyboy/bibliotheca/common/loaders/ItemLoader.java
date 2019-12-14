package com.ewyboy.bibliotheca.common.loaders;

import com.ewyboy.bibliotheca.util.ModLogger;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemLoader extends ContentLoader<Item> {

    public static final ItemLoader INSTANCE = new ItemLoader();

    private ItemLoader() {
        super(ForgeRegistries.ITEMS);
    }

    @Override
    protected void onRegister(String name, Item item) {
        getRegister().register(name, () -> item);
        getContentMap().put(item.getRegistryName(), item);
        ModLogger.info("[ITEM]: {} has been registered by Bibliotheca for {}", name, activeModName());
    }
}
