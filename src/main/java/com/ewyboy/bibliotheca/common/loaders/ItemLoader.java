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
        getContentMap().put(item.getRegistryName(), getRegister().register(name, () -> item));
        ModLogger.info("[ITEM]: {} has been registered by Bibliotheca for {}", name, activeModName());
    }
}
