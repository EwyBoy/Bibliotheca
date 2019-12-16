package com.ewyboy.bibliotheca.common.datagenerator;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class BibLanguageProvider extends LanguageProvider {
    private BibLanguageProvider(DataGenerator gen, String modid, String locale) {
        super(gen, modid, locale);
    }

    public static String getEnglishTranslation(String name) {
        StringBuilder builder = new StringBuilder();
        boolean upper = true;
        for (char c : name.toCharArray()) {
            switch (c) {
                case '_':
                case '.':
                    builder.append(' ');
                    upper = true;
                    break;
                default:
                    builder.append(upper ? Character.toUpperCase(c) : c);
                    upper = false;
                    break;
            }
        }
        return builder.toString();
    }

    @Override
    public void addTranslations() {
        // NOOP
    }

    @Override
    public void addBlock(Supplier<? extends Block> key, String name) {
        super.addBlock(key, name);
    }

    @Override
    public void add(Block key, String name) {
        super.add(key, name);
    }

    @Override
    public void addItem(Supplier<? extends Item> key, String name) {
        super.addItem(key, name);
    }

    @Override
    public void add(Item key, String name) {
        super.add(key, name);
    }

    @Override
    public void addItemStack(Supplier<ItemStack> key, String name) {
        super.addItemStack(key, name);
    }

    @Override
    public void add(ItemStack key, String name) {
        super.add(key, name);
    }

    @Override
    public void addEnchantment(Supplier<? extends Enchantment> key, String name) {
        super.addEnchantment(key, name);
    }

    @Override
    public void add(Enchantment key, String name) {
        super.add(key, name);
    }

    @Override
    public void addBiome(Supplier<? extends Biome> key, String name) {
        super.addBiome(key, name);
    }

    @Override
    public void add(Biome key, String name) {
        super.add(key, name);
    }

    @Override
    public void addEffect(Supplier<? extends Effect> key, String name) {
        super.addEffect(key, name);
    }

    @Override
    public void add(Effect key, String name) {
        super.add(key, name);
    }

    @Override
    public void addEntityType(Supplier<? extends EntityType<?>> key, String name) {
        super.addEntityType(key, name);
    }

    @Override
    public void add(EntityType<?> key, String name) {
        super.add(key, name);
    }

    @Override
    public void add(String key, String value) {
        super.add(key, value);
    }

    private static final Map<String, BibLanguageProvider> LANGUAGE_PROVIDER_MAP = new HashMap<>();

    public static BibLanguageProvider get(DataGenerator gen, String modid, String locale) {
        return LANGUAGE_PROVIDER_MAP.computeIfAbsent(modid, s -> create(gen, s, locale));
    }

    private static BibLanguageProvider create(DataGenerator gen, String modid, String locale) {
        BibLanguageProvider languageProvider = new BibLanguageProvider(gen, modid, locale);
        gen.addProvider(languageProvider);
        return languageProvider;
    }
}
