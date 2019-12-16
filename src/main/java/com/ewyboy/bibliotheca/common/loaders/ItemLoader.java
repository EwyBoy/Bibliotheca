package com.ewyboy.bibliotheca.common.loaders;

import com.ewyboy.bibliotheca.common.datagenerator.BibLanguageProvider;
import com.ewyboy.bibliotheca.common.datagenerator.IFancyTranslation;
import com.ewyboy.bibliotheca.util.ModLogger;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ItemLoader extends ContentLoader<Item> {

    public static final ItemLoader INSTANCE = new ItemLoader();

    private ItemLoader() {
        super(ForgeRegistries.ITEMS);
    }

    @Override
    protected void onRegister(String name, Item item) {
        getContentMap().put(new ResourceLocation(activeModId(), name), getRegister().register(name, () -> item));
        ModLogger.info("[ITEM]: {} has been registered by Bibliotheca for {}", name, activeModName());
    }

    @Override
    public void genData(DataGenerator dataGenerator, ExistingFileHelper existingFileHelper) {
        // Add Language DataGen
        for (Supplier<Item> sup : getContentMap().values()) {

            Item item = sup.get();
            ResourceLocation name = item.getRegistryName();
            assert name != null;

            String translation;
            if (item instanceof IFancyTranslation) {
                translation = ((IFancyTranslation) item).englishTranslation();
            } else {
                translation = BibLanguageProvider.getEnglishTranslation(name.getPath());
            }

            BibLanguageProvider.get(dataGenerator, name.getNamespace(), "en_us").add(item, translation);
        }
    }
}
