package com.ewyboy.bibliotheca.common.loaders;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * Created by EwyBoy
 */
public class RecipeLoader {

    public static void addRecipe(ItemStack stack, Object ... recipe) {
        GameRegistry.addRecipe(new ShapedOreRecipe(stack, recipe));
    }

    public static void addSmelting(ItemStack stackIn, ItemStack stackOut, float xp) {
        GameRegistry.addSmelting(stackIn, stackOut, xp);
    }

    public static void addFuel(IFuelHandler fuel) {
        GameRegistry.registerFuelHandler(fuel);
    }
}
