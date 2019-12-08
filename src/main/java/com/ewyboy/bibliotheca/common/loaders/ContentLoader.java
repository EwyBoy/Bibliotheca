package com.ewyboy.bibliotheca.common.loaders;

import com.ewyboy.bibliotheca.util.ModLogger;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ContentLoader<ContentType extends IForgeRegistryEntry<ContentType>> {

    public static ItemGroup CONTENT_GROUP;

    public static final HashMap<String, Block> BLOCK_LIST = new HashMap<>();

    public static final ContentLoader<Block> BLOCKS = new ContentLoader<>(ForgeRegistries.BLOCKS);
    public static final ContentLoader<Item> ITEMS = new ContentLoader<>(ForgeRegistries.ITEMS);
    public static final ContentLoader<TileEntityType<?>> TILES = new ContentLoader<>(ForgeRegistries.TILE_ENTITIES);

    public static void init(String modID, ItemGroup contentGroup, Class blockRegister, Class itemRegister, Class tileRegister) {
        ModLogger.info("Registering content for " + modID);
        CONTENT_GROUP = contentGroup;
        BLOCKS.register(blockRegister);
        ITEMS.register(itemRegister);
        TILES.register(tileRegister);
    }

    private final IForgeRegistry<ContentType> registry;
    private final Map<String, DeferredRegister<ContentType>> REGISTERS = Maps.newHashMap();
    private final Map<ResourceLocation, ContentType> CONTENT_MAP = Maps.newHashMap();

    public Map<ResourceLocation, ContentType> getContentMap() {
        return CONTENT_MAP;
    }

    private ContentLoader(IForgeRegistry<ContentType> registry) {
        this.registry = registry;
    }

    private void register(Class contentRegister) {
        try {
            Class<ContentType> superType = registry.getRegistrySuperType();
            for (Field field : contentRegister.getDeclaredFields()) {
                Object obj = field.get(null);
                String fieldName = field.getName().toLowerCase();

                if (superType.isInstance(obj)) {
                    ContentType contentType = superType.cast(obj);
                    String contentTypeName = superType.getSimpleName();
                    ItemGroup contentGroup = contentType instanceof IHasCustomGroup ? ((IHasCustomGroup) contentType).getCustomItemGroup() : CONTENT_GROUP;

                    if (contentType instanceof Item) ITEMS.getRegister().register(fieldName, () -> contentType instanceof IHasNoGroup ? new Item(new Item.Properties()) : new Item(new Item.Properties().group(contentGroup)));
                    else getRegister().register(fieldName, () -> contentType);

                    CONTENT_MAP.put(contentType.getRegistryName(), contentType);
                    ModLogger.info("[" + contentTypeName.toUpperCase() + "]: " + fieldName + " has been registered by Bibliotheca for the mod " + ModLoadingContext.get().getActiveContainer().getModInfo().getDisplayName());

                    if (contentType instanceof Block) {
                        BLOCK_LIST.put(fieldName, ((Block) contentType).getBlock());
                        BlockItem blockItem;
                        if (contentType instanceof IHasCustomBlockItem) {
                            blockItem = ((IHasCustomBlockItem) contentType).getCustomBlockItem();
                        } else {
                            if (contentType instanceof IHasNoGroup) {
                                blockItem = new BlockItem((Block) contentType, new Item.Properties());
                            } else {
                                blockItem = new BlockItem((Block) contentType, new Item.Properties().group(contentGroup));
                            }
                        }
                        ITEMS.getRegister().register(fieldName, () -> blockItem);
                        ITEMS.CONTENT_MAP.put(contentType.getRegistryName(), blockItem);
                        ModLogger.info("[BLOCK-ITEM]: " + fieldName + " has been registered by Bibliotheca for the mod " + ModLoadingContext.get().getActiveContainer().getModInfo().getDisplayName());
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private DeferredRegister<ContentType> getRegister(String modID) {
        return REGISTERS.computeIfAbsent(modID, id -> {
            DeferredRegister<ContentType> register = new DeferredRegister<>(registry, id);
            register.register(FMLJavaModLoadingContext.get().getModEventBus());
            return register;
        });
    }

    private DeferredRegister<ContentType> getRegister() {
        return getRegister(ModLoadingContext.get().getActiveContainer().getModId());
    }

    public interface IHasNoGroup {}

    public interface IHasCustomGroup {
        ItemGroup getCustomItemGroup();
    }

    public interface IHasCustomBlockItem {
        BlockItem getCustomBlockItem();
    }

}
