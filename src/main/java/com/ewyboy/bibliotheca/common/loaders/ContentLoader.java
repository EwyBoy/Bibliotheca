package com.ewyboy.bibliotheca.common.loaders;

import com.ewyboy.bibliotheca.common.content.BibItemGroup;
import com.ewyboy.bibliotheca.util.ModLogger;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public abstract class ContentLoader<ContentType extends IForgeRegistryEntry<ContentType>> {
    protected static Set<BibItemGroup> groups = new HashSet<>();
    public static ItemGroup CONTENT_GROUP;

    ContentLoader(IForgeRegistry<ContentType> registry) {
        this.registry = registry;
    }

    private final IForgeRegistry<ContentType> registry;
    private final Map<String, DeferredRegister<ContentType>> REGISTERS = Maps.newHashMap();
    private final Map<ResourceLocation, Supplier<ContentType>> CONTENT_MAP = Maps.newHashMap();

    public static Set<ContentLoader<?>> getLoaders() {
        return ImmutableSet.of(
                BlockLoader.INSTANCE,
                ItemLoader.INSTANCE,
                TileLoader.INSTANCE
        );
    }

    public static void add(BibItemGroup itemGroup) {
        groups.add(itemGroup);
    }

    public Map<ResourceLocation, Supplier<ContentType>> getContentMap() {
        return CONTENT_MAP;
    }

    public static void init(String modID, ItemGroup contentGroup, Class<?> blockRegister, Class<?> itemRegister, Class<?> tileRegister) {
        ModLogger.info("Registering content for " + modID);
        if (contentGroup == null) {
            CONTENT_GROUP = ItemGroup.MISC;
        } else {
            CONTENT_GROUP = contentGroup;
        }
        if (blockRegister != null) {
            BlockLoader.INSTANCE.register(blockRegister);
        }
        if (itemRegister != null) {
            ItemLoader.INSTANCE.register(itemRegister);
        }
        if (tileRegister != null) {
            TileLoader.INSTANCE.register(tileRegister);
        }
    }

    protected void register(Class<?> contentRegister) {
        try {
            Class<ContentType> superType = registry.getRegistrySuperType();
            for (Field field : contentRegister.getDeclaredFields()) {
                Object obj = field.get(null);
                String fieldName = field.getName().toLowerCase();

                if (superType.isInstance(obj)) {
                    onRegister(fieldName, superType.cast(obj));
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    protected abstract void onRegister(String name, ContentType obj);

    public abstract void genData(DataGenerator dataGenerator, ExistingFileHelper existingFileHelper);

    protected DeferredRegister<ContentType> getRegister(String modID) {
        return REGISTERS.computeIfAbsent(modID, id -> {
            DeferredRegister<ContentType> register = DeferredRegister.create(registry, id);
            register.register(FMLJavaModLoadingContext.get().getModEventBus());
            return register;
        });
    }

    protected DeferredRegister<ContentType> getRegister() {
        return getRegister(ModLoadingContext.get().getActiveContainer().getModId());
    }

    protected String activeModName() {
        return ModLoadingContext.get().getActiveContainer().getModInfo().getDisplayName();
    }

    protected String activeModId() {
        return ModLoadingContext.get().getActiveContainer().getModInfo().getModId();
    }

    public interface IHasNoGroup {
    }

    public interface IHasCustomGroup {
        ItemGroup getCustomItemGroup();
    }

    public interface IHasCustomBlockItem {
        BlockItem getCustomBlockItem();
    }
}
