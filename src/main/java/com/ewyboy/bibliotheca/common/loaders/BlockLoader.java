package com.ewyboy.bibliotheca.common.loaders;

import com.ewyboy.bibliotheca.common.content.item.BaseItemBlock;
import com.ewyboy.bibliotheca.util.ModLogger;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockLoader extends ContentLoader<Block> {
    public static final BlockLoader INSTANCE = new BlockLoader();

    private BlockLoader() {
        super(ForgeRegistries.BLOCKS);
    }

    @Override
    protected void onRegister(String name, Block block) {
        getRegister().register(name, () -> block);
        getContentMap().put(block.getRegistryName(), block);
        ModLogger.info("[BLOCK]: {} has been registered by Bibliotheca for {}", name, activeModName());

        // Handle Block Items
        BlockItem item;
        if (block instanceof IHasCustomBlockItem) {
            item = ((IHasCustomBlockItem) block).getCustomBlockItem();
        } else {
            Item.Properties properties = new Item.Properties();
            // Add custom Item Groups
            if (block instanceof IHasCustomGroup)
                properties.group(((IHasCustomGroup) block).getCustomItemGroup());
            item = new BaseItemBlock(block, properties);
        }
        ItemLoader.INSTANCE.onRegister(name, item);
    }
}
