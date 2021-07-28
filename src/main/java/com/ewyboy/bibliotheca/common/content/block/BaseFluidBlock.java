package com.ewyboy.bibliotheca.common.content.block;

import com.ewyboy.bibliotheca.common.loaders.ContentLoader;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.FlowingFluid;

import java.util.function.Supplier;

public class BaseFluidBlock extends LiquidBlock implements ContentLoader.IHasNoBlockItem {

    public BaseFluidBlock(Supplier<? extends FlowingFluid> supplier) {
        super(supplier, BlockBehaviour.Properties.of(Material.WATER).noCollission().strength(100.0F).noDrops());
    }

}
