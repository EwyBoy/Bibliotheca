package com.ewyboy.bibliotheca.common.loaders;

import com.ewyboy.bibliotheca.common.interfaces.IBlockRenderer;
import com.ewyboy.bibliotheca.common.utility.Logger;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by EwyBoy
 **/
public class BlockLoader {

    public static String MOD_ID;
    public static final HashMap<String, Block> BLOCKS = new HashMap<>();

    /**
     *   Call this from your preInit() in your mod
     *   @param modID the mod id of your mod
     *   @param blockRegister a class where your initialize your block fields
     *   Example: public static final BlockTest = new BlockTest();
     *   This @BlockLoader detects all the fields and register them for you
     *   as long as they implements @{@link IBlockRenderer}
     */
    public static void init(String modID, Class blockRegister) {
        MOD_ID = modID;
        registerBlocks(modID, blockRegister);
        initModels();
        initItemModels();
    }

    /**
     * Initializes the block model
     */
    public static void initModels() {
        BLOCKS.values().stream().filter(block -> block instanceof IBlockRenderer).forEachOrdered(block -> ((IBlockRenderer) block).registerBlockRenderer());
    }

    /**
     * Initializes the item-block model
     */
    public static void initItemModels() {
        BLOCKS.values().stream().filter(block -> block instanceof IBlockRenderer).forEachOrdered(block -> ((IBlockRenderer) block).registerBlockItemRenderer());
    }

    /**
     * Grabs the block fields from the blockRegister class you provided
     */
    private static void registerBlocks(String modID, Class blockRegister) {
        try {
            for (Field field : blockRegister.getDeclaredFields()) {
                Object obj = field.get(null);
                if (obj instanceof Block) {
                    Block block = (Block) obj;
                    String name = "block" + field.getName().toLowerCase(Locale.ENGLISH);
                    registerBlock(modID, block, name);
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  Registers the block to the game with the mod id you provided
     */
    private static void registerBlock(String modID, Block block, String name) {
        ForgeRegistries.BLOCKS.register(block.setRegistryName(modID, name).setUnlocalizedName(modID + "." + name));
        BLOCKS.put(block.getRegistryName().toString(), block);
        ItemBlock item;
        item = block instanceof IHasCustomItem ? ((IHasCustomItem) block).getItemBlock() : new ItemBlock(block);
        ForgeRegistries.ITEMS.register(item.setRegistryName(modID, name).setUnlocalizedName(modID + "." + name));
        Logger.info("[BLOCK]: " + block.getUnlocalizedName() + " has been registered by Bibliotheca for the mod " + modID);
    }

    public interface IHasCustomItem {
        ItemBlock getItemBlock();
    }
}
