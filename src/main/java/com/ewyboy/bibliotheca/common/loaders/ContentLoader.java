package com.ewyboy.bibliotheca.common.loaders;

import com.ewyboy.bibliotheca.util.ModLogger;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public abstract class ContentLoader<ContentType extends IForgeRegistryEntry<ContentType>> {

    public static ItemGroup CONTENT_GROUP;

    public static final HashMap<String, Block> BLOCK_LIST = new HashMap<>();

    ContentLoader(IForgeRegistry<ContentType> registry) {
        this.registry = registry;
    }

    private final IForgeRegistry<ContentType> registry;
    private final Map<String, DeferredRegister<ContentType>> REGISTERS = Maps.newHashMap();
    private final Map<ResourceLocation, Supplier<ContentType>> CONTENT_MAP = Maps.newHashMap();

    public Map<ResourceLocation, Supplier<ContentType>> getContentMap() {
        return CONTENT_MAP;
    }

    public static void init(String modID, ItemGroup contentGroup, Class<?> blockRegister, Class<?> itemRegister, Class<?> tileRegister) {
        ModLogger.info("Registering content for " + modID);
        CONTENT_GROUP = contentGroup;
        BlockLoader.INSTANCE.register(blockRegister);
        ItemLoader.INSTANCE.register(itemRegister);
        TileLoader.INSTANCE.register(tileRegister);
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

    protected DeferredRegister<ContentType> getRegister(String modID) {
        return REGISTERS.computeIfAbsent(modID, id -> {
            DeferredRegister<ContentType> register = new DeferredRegister<>(registry, id);
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

    public interface IHasNoGroup {
    }

    public interface IHasCustomGroup {
        ItemGroup getCustomItemGroup();
    }

    public interface IHasCustomBlockItem {
        BlockItem getCustomBlockItem();
    }
}
