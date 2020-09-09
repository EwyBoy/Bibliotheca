package com.ewyboy.bibliotheca;

import com.ewyboy.bibliotheca.common.content.BibItemGroup;
import com.ewyboy.bibliotheca.common.loaders.ContentLoader;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.tileentity.TileEntityType.Builder;
import net.minecraftforge.fml.common.Mod;

@Mod(BibliothecaTest.ID)
public class BibliothecaTest {
    public static final String ID = "biblibtest";

    public BibliothecaTest() {
        ContentLoader.init(ID, Items.BIB_GROUP, Blocks.class, Items.class, Tiles.class);
    }

    public static final class Blocks {
        public static final Block TEST_BLOCK = new Block(AbstractBlock.Properties.create(Material.IRON));
    }

    public static final class Tiles {
        public static final TileEntityType TEST_BLOCK = Builder.create(TestTile::new, Blocks.TEST_BLOCK).build(null);
    }

    public static final class Items {
        public static final BibItemGroup BIB_GROUP = new BibItemGroup(ID, ID) {
            @Override
            public ItemStack createIcon() {
                return new ItemStack(TEST_ITEM);
            }
        };

        public static final Item TEST_ITEM = new Item(new Item.Properties().group(BIB_GROUP));
    }
}