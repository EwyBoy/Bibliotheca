package com.ewyboy.bibliotheca.common.loaders;

import com.ewyboy.bibliotheca.common.utility.Logger;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by EwyBoy
 */
public class TileEntityLoader {

    public static final HashMap<String, TileEntity> TILE_ENTITIES = new HashMap<>();

    /**
     *   Call this from your preInit() in your mod after you have registered blocks
     *   @param tileEntityRegister a class where your initialize your block fields
     *   Example: public static final TileEntityTest = new TileEntityTest();
     *   This @{@link TileEntityLoader detects all the fields and register them for you
     */
    public static void init(Class tileEntityRegister) {
        registerTileEntity(tileEntityRegister);
    }

    /**
     * Grabs the block fields from the blockRegister class you provided
     */
    private static void registerTileEntity(Class tileEntityRegister) {
        try {
            for (Field field : tileEntityRegister.getDeclaredFields()) {
                Object obj = field.get(null);
                if (obj instanceof TileEntity) {
                    TileEntity tileEntity = (TileEntity) obj;
                    String name = field.getName().toLowerCase(Locale.ENGLISH);
                    registerTileEntity(tileEntity, name);
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  Registers the tile entities to the game
     */
    private static void registerTileEntity(TileEntity tileEntity, String name) {
        GameRegistry.registerTileEntity(tileEntity.getClass(), name);
        TILE_ENTITIES.put(tileEntity.toString(), tileEntity);
        Logger.info("[TILE ENTITY]: " + tileEntity.getClass().getSimpleName() + " has been registered by Bibliotheca");
    }
}