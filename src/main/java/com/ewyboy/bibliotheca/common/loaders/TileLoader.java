package com.ewyboy.bibliotheca.common.loaders;

import com.ewyboy.bibliotheca.util.ModLogger;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class TileLoader extends ContentLoader<BlockEntityType<?>> {

    public static final TileLoader INSTANCE = new TileLoader();

    private TileLoader() {
        super(ForgeRegistries.BLOCK_ENTITIES);
    }

    @Override
    protected void onRegister(String name, BlockEntityType<?> tileType) {
        getContentMap().put(new ResourceLocation(activeModId(), name), getRegister().register(name, () -> tileType));
        ModLogger.info("[BLOCK-ENTITY]: {} has been registered by Bibliotheca for {}", name, activeModName());
    }

    @Override
    public void genData(DataGenerator dataGenerator, ExistingFileHelper existingFileHelper) {
        // NOOP
    }
}
