package com.ewyboy.biblibtest;

import com.ewyboy.bibliotheca.common.content.BibItemGroup;
import com.ewyboy.bibliotheca.common.event.EventHandler;
import com.ewyboy.bibliotheca.common.loaders.ContentLoader;
import com.google.common.collect.Sets;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod(BibliothecaTest.ID)
public class BibliothecaTest {
    public static final String ID = "biblibtest";

    public BibliothecaTest() {
        ContentLoader.init(ID, Items.BIB_GROUP, Blocks.class, Items.class, Tiles.class);
        EventHandler.MOD.register(this::onGatherData);
    }

    private void onGatherData(final GatherDataEvent event) {
        for (ContentLoader<?> contentLoader : ContentLoader.getLoaders()) {
            contentLoader.genData(event.getGenerator(), event.getExistingFileHelper());
        }
    }

    public static final class Blocks {
        public static final Block TEST_BLOCK = new TestBlock(BlockBehaviour.Properties.of(Material.BAMBOO));
    }

    public static final class Tiles {
        public static final BlockEntityType<TestTile> TEST_TILE = new BlockEntityType<>(TestTile :: new, Sets.newHashSet(Blocks.TEST_BLOCK), null);
    }

    public static final class Items {
        public static final BibItemGroup BIB_GROUP = new BibItemGroup(ID, ID) {
            @Override
            public ItemStack makeIcon() {
                return new ItemStack(TEST_ITEM);
            }
        };

        public static final Item TEST_ITEM = new Item(new Item.Properties().tab(BIB_GROUP));
    }
}