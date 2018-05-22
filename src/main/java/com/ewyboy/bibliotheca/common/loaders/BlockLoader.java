package com.ewyboy.bibliotheca.common.loaders;

import com.ewyboy.bibliotheca.common.interfaces.IBlockRenderer;
import com.ewyboy.bibliotheca.common.utility.Logger;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

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
     *   Initialize this in preInit() in your mod
     *   @param modID the mod id for your mod
     *   @param blockRegister the class where your initialize your block fields
     *   Example: public static final BlockTest = new BlockTest();
     *   This @BlockLoader detects all the fields and register them for you
     *   as long as they implements @{@link IBlockRenderer}
     */
    public static void init(String modID, Class blockRegister) {
        MOD_ID = modID;
        registerBlocks(modID, blockRegister);
    }

    /**
     * Grabs the block fields from the blockRegister class you provided
     */
    private static void registerBlocks(String modID, Class blockRegister) {
        try {
            Field[] declaredFields = blockRegister.getDeclaredFields();
            for (Field field : declaredFields) {
                Object obj = field.get(null);
                if (obj instanceof Block) {
                    Block block = (Block) obj;
                    String name = "block" + field.getName().toLowerCase(Locale.ENGLISH);
                    registerBlock(modID, block, name);
                    if (block instanceof IBlockRenderer && FMLCommonHandler.instance().getEffectiveSide().isClient()) {
                        ((IBlockRenderer) block).registerBlockRenderer();
                        ((IBlockRenderer) block).registerBlockItemRenderer();
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  Registers the block to the game with the mod id you provided
     */
    public static void registerBlock(String modID, Block block, String name) {
        ForgeRegistries.BLOCKS.register(block.setRegistryName(modID, name).setUnlocalizedName(modID + "." + name));
        BLOCKS.put(block.getRegistryName().toString(), block);
        ItemBlock item = block instanceof IHasCustomItem ? ((IHasCustomItem) block).getItemBlock() : new ItemBlock(block);
        ForgeRegistries.ITEMS.register(item.setRegistryName(modID, name).setUnlocalizedName(modID + "." + name));
        Logger.info("[BLOCK]: " + name + " has been registered by Bibliotheca for the mod " + modID);
        if (block instanceof IHasCustomItem) {
            Logger.info("[ITEMBLOCK]: " + name + " | " +  item.getRegistryName() + " has been registered by Bibliotheca for the mod " + modID);
        }
    }

    public interface IHasCustomItem {
        ItemBlock getItemBlock();
    }
}
