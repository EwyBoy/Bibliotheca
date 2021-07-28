package com.ewyboy.bibliotheca.common.loaders;

import com.ewyboy.bibliotheca.common.content.BibItemGroup;
import com.ewyboy.bibliotheca.common.content.item.BaseBlockItem;
import com.ewyboy.bibliotheca.common.datagenerator.BibLanguageProvider;
import com.ewyboy.bibliotheca.common.datagenerator.IFancyTranslation;
import com.ewyboy.bibliotheca.util.ModLogger;
import net.minecraft.world.level.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class BlockLoader extends ContentLoader<Block> {

    public static final BlockLoader INSTANCE = new BlockLoader();

    private BlockLoader() {
        super(ForgeRegistries.BLOCKS);
    }

    @Override
    protected void onRegister(String name, Block block) {
        getContentMap().put(new ResourceLocation(activeModId(), name), getRegister().register(name, () -> block));
        ModLogger.info("[BLOCK]: {} has been registered by Bibliotheca for {}", name, activeModName());

        // Handle Block Items
        BlockItem blockItem;

        if(block instanceof IHasNoBlockItem) {
            return;
        }

        Item.Properties properties = new Item.Properties();

        if(block instanceof IHasCustomBlockItem) {
            blockItem = ((IHasCustomBlockItem) block).getCustomBlockItem();
        } else {
            if(block instanceof IHasCustomGroup) {
                properties.tab(((IHasCustomGroup) block).getCustomItemGroup());
            } else if(!(block instanceof IHasNoGroup)) {
                properties.tab(CONTENT_GROUP);
            }
            blockItem = new BaseBlockItem(block, properties);
        }

        if (block instanceof IHasCustomNameExtension) {
            name = name + ((IHasCustomNameExtension) block).getCustomNameExtension();
        }

        ItemLoader.INSTANCE.onRegister(name, blockItem);
    }

    @Override
    public void genData(DataGenerator dataGenerator, ExistingFileHelper existingFileHelper) {
        // Add Language DataGen
        for(Supplier<Block> sup : getContentMap().values()) {

            Block block = sup.get();
            ResourceLocation name = block.getRegistryName();
            assert name != null;

            String translation;
            if(block instanceof IFancyTranslation) {
                translation = ((IFancyTranslation) block).englishTranslation();
            } else {
                translation = BibLanguageProvider.getEnglishTranslation(name.getPath());
            }

            BibLanguageProvider.get(dataGenerator, name.getNamespace(), "en_us").add(block, translation);
        }

        for(BibItemGroup group : groups) {
            BibLanguageProvider.get(dataGenerator, group.getModid(), "en_us").add("itemGroup." + group.getRecipeFolderName(), group.englishTranslation());
        }
    }

}
