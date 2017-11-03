package com.ewyboy.bibliotheca.common.loaders;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * Created by EwyBoy
 */
public class RecipeLoader {

    public static void addRecipe(ResourceLocation group, ItemStack stack, Object ... recipe) {
        ForgeRegistries.RECIPES.register(new ShapedOreRecipe(group, stack, recipe));
    }

    public static void addSmelting(ItemStack stackIn, ItemStack stackOut, float xp) {
        GameRegistry.addSmelting(stackIn, stackOut, xp);
    }

    public static void addFuel(IFuelHandler fuel) {
        GameRegistry.registerFuelHandler(fuel);
    }
}
