package com.ewyboy.bibliotheca.client;

import com.ewyboy.bibliotheca.Bibliotheca;
import com.ewyboy.bibliotheca.client.interfaces.IHasRenderType;
import com.ewyboy.bibliotheca.client.interfaces.IHasOBJModel;
import com.ewyboy.bibliotheca.client.interfaces.IHasSpecialRenderer;
import com.ewyboy.bibliotheca.client.interfaces.INeedTexture;
import com.ewyboy.bibliotheca.common.loaders.BlockLoader;
import com.ewyboy.bibliotheca.util.ModLogger;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.model.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.IModelConfiguration;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = Bibliotheca.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BibModelLoader {

    private static final Set<String> enabledDomains = new HashSet<>();

    private static void enableResourceDomain(Block block) {
        if(block.getRegistryName() != null) {
            if(!enabledDomains.contains(block.getRegistryName().getNamespace())) {
                enabledDomains.add(block.getRegistryName().getNamespace());
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void initSpecialRenders() {
        BlockLoader.INSTANCE.getContentMap().values().stream().map(Supplier :: get).filter(block -> block instanceof IHasSpecialRenderer).forEach(block -> {
            ((IHasSpecialRenderer) block).initSpecialRenderer();
            ModLogger.info("[RENDERING] Loaded in special renderer for " + block.getRegistryName());
        });
    }

    @SubscribeEvent
    public static void onPreTextureStitch(TextureStitchEvent.Pre event) {
        BlockLoader.INSTANCE.getContentMap().values().stream().map(Supplier :: get).filter(block -> block instanceof INeedTexture).forEach(block -> {
            ((INeedTexture) block).getTextures().forEach(event :: addSprite);
            ModLogger.info("[TEXTURE] Loaded in " + ((INeedTexture) block).getTextures().size() + " custom texture for " + block.getRegistryName());
        });
    }

    @SubscribeEvent
    public static void onClientStartupEvent(FMLClientSetupEvent event) {
        BlockLoader.INSTANCE.getContentMap().values().stream().map(Supplier :: get).filter(block -> block instanceof IHasRenderType).forEach(block -> {
            RenderTypeLookup.setRenderLayer(block, ((IHasRenderType) block).getRenderType());
            ModLogger.info("[RENDER-TYPE] Loaded in special render type: "+ ((IHasRenderType) block).getRenderType() + " for " + block.getRegistryName());
        });
    }

    @SubscribeEvent
    public static void onModelBakeEvent(ModelBakeEvent event) {
        BlockLoader.INSTANCE.getContentMap().values().stream().map(Supplier :: get).filter(block -> block instanceof IHasOBJModel).forEach(block -> {
            enableResourceDomain(block);
            try {
                IUnbakedModel unbakedModel = event.getModelLoader().getModelOrMissing(((IHasOBJModel) block).getOBJModelLocation());
                if(unbakedModel instanceof OBJModel) {
                    IBakedModel bakedModel = ((OBJModel) unbakedModel).bake((IModelConfiguration) event.getModelManager(), event.getModelLoader(), ModelLoader.defaultTextureGetter(), ModelRotation.X0_Y0, event.getModelManager().getModel(((OBJModel) unbakedModel).modelLocation).getOverrides(), ((IHasOBJModel) block).getOBJModelLocation());
                    if(((IHasOBJModel) block).shouldRenderBlock()) {
                        event.getModelRegistry().put(new ModelResourceLocation(Objects.requireNonNull(block.getRegistryName()), ""), bakedModel);
                        ModLogger.info("[MODEL] Loaded in OBJ block model for " + block.getRegistryName());
                    }
                    if(((IHasOBJModel) block).shouldRenderItem()) {
                        event.getModelRegistry().put(new ModelResourceLocation(Objects.requireNonNull(block.getRegistryName()), "inventory"), bakedModel);
                        ModLogger.info("[MODEL] Loaded in OBJ item model for " + block.getRegistryName());
                    }
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        });
    }

}
