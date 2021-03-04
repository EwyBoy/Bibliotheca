package com.ewyboy.bibliotheca.common.content.block;

import com.ewyboy.bibliotheca.common.loaders.ContentLoader;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FlowingFluid;

import java.util.function.Supplier;

public class BaseFluidBlock extends FlowingFluidBlock implements ContentLoader.IHasNoBlockItem {

    public BaseFluidBlock(Supplier<? extends FlowingFluid> supplier) {
        super(supplier, AbstractBlock.Properties.create(Material.WATER).doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops());
    }

}
