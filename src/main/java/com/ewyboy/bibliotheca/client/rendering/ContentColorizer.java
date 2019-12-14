package com.ewyboy.bibliotheca.client.rendering;

import com.ewyboy.bibliotheca.client.interfaces.IBlockColorizer;
import com.ewyboy.bibliotheca.common.loaders.ContentLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;

public class ContentColorizer {

    public static final ItemColors itemColors = Minecraft.getInstance().getItemColors();
    public static final BlockColors blockColors = Minecraft.getInstance().getBlockColors();

    public static void registerColors() {
        ContentLoader.BLOCK_LIST.values().forEach(block -> {
            if (block instanceof IBlockColorizer) {
                blockColors.register((IBlockColorizer) block, ((IBlockColorizer) block).blockToColor());
                if (((IBlockColorizer) block).isItemBlockColored()) {
                    itemColors.register((IItemColor) block, ((IBlockColorizer) block).itemToColor());
                }
            }
        });
    }

}
