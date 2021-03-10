package com.ewyboy.bibliotheca.client.rendering;

import com.ewyboy.bibliotheca.common.loaders.BlockLoader;
import com.ewyboy.bibliotheca.common.loaders.ItemLoader;
import com.ewyboy.bibliotheca.util.ModLogger;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.BlockItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ContentColorizer {

    // Collect and register once

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void registerBlockColors(ColorHandlerEvent.Block event) {
        BlockColors blockColors = event.getBlockColors();
        BlockLoader.INSTANCE.getContentMap().values().stream().map(Supplier :: get).forEach(block -> {
            if(block instanceof IBlockColor) {
                ModLogger.info("[COLOR] registered for block " + block.getRegistryName());
                blockColors.register(((IBlockColor) block), block);
            }
        });
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void registerItemColors(ColorHandlerEvent.Item event) {
        ItemColors itemColors = event.getItemColors();
        BlockColors blockColors = event.getBlockColors();
        ItemLoader.INSTANCE.getContentMap().values().stream().map(Supplier :: get).forEach(item -> {
            if(item instanceof IItemColor) {
                ModLogger.info("[COLOR] registered for item " + item.getRegistryName());

                IItemColor getColorFromBlock = (stack, tintIndex) -> {
                    BlockState state = ((BlockItem) stack.getItem()).getBlock().getDefaultState();
                    return blockColors.getColor(state, null, null, tintIndex);
                };

                itemColors.register(getColorFromBlock, item);
            }
        });
    }

}
