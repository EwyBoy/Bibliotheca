package com.ewyboy.bibliotheca.client.color;

import com.ewyboy.bibliotheca.client.interfaces.IBlockColorizer;
import com.ewyboy.bibliotheca.util.ModLogger;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.BlockItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Objects;

@OnlyIn(Dist.CLIENT)
public class ColorLoader {

    public static final HashMap<String, ColoredBlock> COLORED_BLOCKS = new HashMap<>();
    public static final HashMap<String, ColoredItem> COLORED_ITEMS = new HashMap<>();

    @OnlyIn(Dist.CLIENT)
    public static void init(String modID, Class coloredBlockRegister, Class coloredItemsRegister) {
        if (coloredBlockRegister != null) registerColoredBlocks(modID, coloredBlockRegister);
        if (coloredItemsRegister != null) registerColoredItems(modID, coloredItemsRegister);
    }

    @OnlyIn(Dist.CLIENT)
    private static void registerColoredBlocks(String modID, Class coloredBlockRegister) {
        try {
            for (Field field : coloredBlockRegister.getDeclaredFields()) {
                Object obj = field.get(null);
                if (obj instanceof ColoredBlock) {
                    ColoredBlock block = (ColoredBlock) obj;
                    String name = Objects.requireNonNull(block.blockToColor().getRegistryName()).toString();
                    registerColoredBlock(modID, block, name);
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerColoredBlock(String modID, ColoredBlock coloredBlock, String name) {
        COLORED_BLOCKS.put(name, coloredBlock);
        BlockColors blockColors = Minecraft.getInstance().getBlockColors();
        blockColors.register(coloredBlock, ((IBlockColorizer) coloredBlock).blockToColor());
        ModLogger.info("[BLOCK-COLOR]: {} :: {} has been registered by Bibliotheca for {}", Integer.toHexString(coloredBlock.getColorIndex()), name, modID);
    }

    @OnlyIn(Dist.CLIENT)
    private static void registerColoredItems(String modID, Class coloredItemRegister) {
        try {
            for (Field field : coloredItemRegister.getDeclaredFields()) {
                Object obj = field.get(null);
                if (obj instanceof ColoredItem) {
                    ColoredItem item = (ColoredItem) obj;
                    String name = Objects.requireNonNull(item.itemToColor().getRegistryName()).toString();
                    registerColoredItem(modID, item, name);
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerColoredItem(String modID, ColoredItem coloredItem, String name) {
        COLORED_ITEMS.put(Objects.requireNonNull(coloredItem.itemToColor().getRegistryName()).toString(), coloredItem);

        BlockColors blockColors = Minecraft.getInstance().getBlockColors();
        ItemColors itemColors = Minecraft.getInstance().getItemColors();

        IItemColor getColorFromBlock = (stack, tintIndex) -> {
            BlockState state = ((BlockItem) stack.getItem()).getBlock().getDefaultState();
            return blockColors.getColor(state, null, null, tintIndex);
        };

        itemColors.register(getColorFromBlock, coloredItem.itemToColor());
        ModLogger.info("[ITEM-COLOR]: {} :: {} has been registered by Bibliotheca for {}", Integer.toHexString(coloredItem.getColorIndex()), name, modID);
    }

}
