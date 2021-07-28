package com.ewyboy.bibliotheca.common.loaders;

import com.ewyboy.bibliotheca.common.content.block.BaseFluidBlock;
import com.ewyboy.bibliotheca.util.ModLogger;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class FluidLoader extends ContentLoader<Fluid> {

    public static final FluidLoader INSTANCE = new FluidLoader();

    private FluidLoader() {
        super(ForgeRegistries.FLUIDS);
    }

    public static BucketItem bucketItem;
    public static LiquidBlock fluidBlock;

    @Override
    protected void onRegister(String name, Fluid fluid) {

        if (fluid.isSource(fluid.defaultFluidState())) {

            if (fluid instanceof IHasCustomBucket) {
                bucketItem = ((IHasCustomBucket) fluid).getCustomBucketItem();
            } else {
                bucketItem = new BucketItem(
                        () -> fluid, new Item.Properties()
                        .craftRemainder(Items.BUCKET)
                        .stacksTo(1)
                        .tab(ContentLoader.CONTENT_GROUP)
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
