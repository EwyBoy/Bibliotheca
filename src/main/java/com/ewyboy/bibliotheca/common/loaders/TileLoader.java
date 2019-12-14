package com.ewyboy.bibliotheca.common.loaders;

import com.ewyboy.bibliotheca.util.ModLogger;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class TileLoader extends ContentLoader<TileEntityType<?>> {
    public static final TileLoader INSTANCE = new TileLoader();

    private TileLoader() {
        super(ForgeRegistries.TILE_ENTITIES);
    }

    @Override
    protected void onRegister(String name, TileEntityType<?> tileType) {
        getContentMap().put(new ResourceLocation(activeModId(), name), getRegister().register(name, () -> tileType));
        ModLogger.info("[TILE-TYPE]: {} has been registered by Bibliotheca for {}", name, activeModName());
    }
}
