package com.ewyboy.bibliotheca.common.loaders;

import com.ewyboy.bibliotheca.common.interfaces.IItemRenderer;
import com.ewyboy.bibliotheca.common.utility.Logger;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by EwyBoy
 **/
public class ItemLoader {

    public static String MOD_ID;
    public static final HashMap<String, Item> ITEMS = new HashMap<>();

    /**
     *   Initialize this in preInit() in your mod
     *   @param modid the mod id for your mod
     *   @param itemRegister the class where your initialize your item fields
     *   Example: public static final ItemTest = new ItemTest();
     *   This @ItemLoader detects all the fields and register them for you
     *   as long as they implements @{@link IItemRenderer}
     */
    public static void init(String modid, Class itemRegister) {
        MOD_ID = modid;
        registerItems(modid, itemRegister);
    }

    /**
     * Grabs the item fields from the itemRegister class you provided
     */
    private static void registerItems(String modID, Class itemRegister) {
        try {
            for (Field field : itemRegister.getDeclaredFields()) {
                Object obj = field.get(null);
                if (obj instanceof Item) {
                    Item item = (Item) obj;
                    String name = "item" + field.getName().toLowerCase(Locale.ENGLISH);
                    registerItem(modID, item, name);
                    if (item instanceof IItemRenderer && FMLCommonHandler.instance().getEffectiveSide().isClient()) {
                        ((IItemRenderer) item).registerItemRenderer();
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  Registers the item to the game with the mod id you provided
     */
    public static void registerItem(String modID, Item item, String name) {
        String itemName = name.toLowerCase(Locale.ENGLISH);
        ForgeRegistries.ITEMS.register(item.setRegistryName(modID, itemName).setUnlocalizedName(modID + "." + itemName));
        ITEMS.put(item.getRegistryName().toString(), item);
        Logger.info("[ITEM]: " + name + " has been registered by Bibliotheca for the mod " + modID);
    }
}