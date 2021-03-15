package com.ewyboy.bibliotheca.common.loaders;

import com.ewyboy.bibliotheca.common.content.block.BaseFluidBlock;
import com.ewyboy.bibliotheca.util.ModLogger;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class FluidLoader extends ContentLoader<Fluid> {

    public static final FluidLoader INSTANCE = new FluidLoader();

    private FluidLoader() {
        super(ForgeRegistries.FLUIDS);
    }

    public static BucketItem bucketItem;
    public static FlowingFluidBlock fluidBlock;

    @Override
    protected void onRegister(String name, Fluid fluid) {

        if (fluid.isSource(fluid.getDefaultState())) {

            if (fluid instanceof IHasCustomBucket) {
                bucketItem = ((IHasCustomBucket) fluid).getCustomBucketItem();
            } else {
                bucketItem = new BucketItem(
                        () -> fluid, new Item.Properties()
                        .containerItem(Items.BUCKET)
                        .maxStackSize(1)
                        .group(ContentLoader.CONTENT_GROUP)
                );
            }

            ItemLoader.INSTANCE.onRegister(name + "bucket", bucketItem);

            fluidBlock = new BaseFluidBlock(
                    () -> (FlowingFluid) fluid
            );

            BlockLoader.INSTANCE.onRegister(name, fluidBlock);
        }

        getContentMap().put(new ResourceLocation(activeModId(), name), getRegister().register(name, () -> fluid));
        ModLogger.info("[FLUID]: {} has been registered by Bibliotheca for {}", name, activeModName());

    }

    @Override
    public void genData(DataGenerator dataGenerator, ExistingFileHelper existingFileHelper) {
        // TODO fluid data generator
    }

}
