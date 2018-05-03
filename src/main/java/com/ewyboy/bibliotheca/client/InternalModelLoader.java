package com.ewyboy.bibliotheca.client;

import com.ewyboy.bibliotheca.common.fluid.BaseFluidBlock;
import com.ewyboy.bibliotheca.common.interfaces.IBlockRenderer;
import com.ewyboy.bibliotheca.common.interfaces.IItemRenderer;
import com.ewyboy.bibliotheca.common.item.ItemBase;
import com.ewyboy.bibliotheca.common.loaders.BlockLoader;
import com.ewyboy.bibliotheca.common.loaders.ItemLoader;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by EwyBoy
 */
public class InternalModelLoader {

    @SideOnly(Side.CLIENT)
    public static void init() {
        initBlockModels();
        initItemModels();
        initFluidModels();
    }

    private static void initFluidModels() {
        BlockLoader.BLOCKS.values().stream().filter(block -> block instanceof BaseFluidBlock).forEachOrdered(block -> registerFluidBlockRendering(block, block.getRegistryName().toString().replace(BlockLoader.MOD_ID + ":block", "")));
    }

    @SideOnly(Side.CLIENT)
    private static void initItemModels() {
        ItemLoader.ITEMS.values().stream().filter(item -> item instanceof IItemRenderer).forEachOrdered(item -> {
            for (int i : ((IItemRenderer) item).modelMetas()) {
                ModelBakery.registerItemVariants(item, new ResourceLocation(ItemLoader.MOD_ID + ":" + ((ItemBase) item).itemName(i)));
                ModelLoader.setCustomModelResourceLocation(item, i, new ModelResourceLocation(ItemLoader.MOD_ID + ":" + ((ItemBase) item).itemName(i), "inventory"));
            }
        });
    }

    @SideOnly(Side.CLIENT)
    private static void initBlockModels() {
        BlockLoader.BLOCKS.values().stream().filter(block -> block instanceof IBlockRenderer).forEachOrdered(block -> {
            for (int i : ((IBlockRenderer) block).modelMetas()) {
                ModelBakery.registerItemVariants(Item.getItemFromBlock(block), block.getRegistryName());
                ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), i, new ModelResourceLocation(block.getRegistryName(), "inventory"));
            }
        });
    }

    @SideOnly(Side.CLIENT)
    private static void registerFluidBlockRendering(Block block, String name) {
        final ModelResourceLocation fluidLocation = new ModelResourceLocation(BlockLoader.MOD_ID + ":" + "fluids", name);
        ModelLoader.setCustomStateMapper(block, new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return fluidLocation;
            }
        });
    }
}
