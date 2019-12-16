package com.ewyboy.bibliotheca.common.content;

import com.ewyboy.bibliotheca.common.datagenerator.BibLanguageProvider;
import com.ewyboy.bibliotheca.common.datagenerator.IFancyTranslation;
import com.ewyboy.bibliotheca.common.loaders.ContentLoader;
import net.minecraft.item.ItemGroup;

public abstract class BibItemGroup extends ItemGroup implements IFancyTranslation {
    private final String modid;
    private final String name;

    public BibItemGroup(String modid, String name) {
        super(String.format("%s.%s", modid, name));
        this.modid = modid;
        this.name = name;
        ContentLoader.add(this);
    }

    public String getModid() {
        return modid;
    }

    @Override
    public String englishTranslation() {
        return BibLanguageProvider.getEnglishTranslation(name);
    }
}
