package com.ewyboy.bibliotheca.client;

import com.ewyboy.bibliotheca.client.interfaces.IHasOBJModel;
import com.ewyboy.bibliotheca.client.interfaces.IHasSpecialRenderer;
import com.ewyboy.bibliotheca.client.interfaces.INeedTexture;
import com.ewyboy.bibliotheca.common.loaders.BlockLoader;
import com.ewyboy.bibliotheca.common.loaders.ContentLoader;
import com.ewyboy.bibliotheca.util.ModLogger;
import com.ewyboy.bibliotheca.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.IUnbakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.BasicState;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = Reference.ModInfo.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModelLoader {

    private static final Set<String> enabledDomains = new HashSet<>();

    private static void enableResourceDomain(Block block) {
        if (block.getRegistryName() != null) {
            if (!enabledDomains.contains(block.getRegistryName().getNamespace())) {
                enabledDomains.add(block.getRegistryName().getNamespace());
                OBJLoader.INSTANCE.addDomain(block.getRegistryName().getNamespace());
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void initSpecialRenders() {
        BlockLoader.INSTANCE.getContentMap().values().stream().map(Supplier:: get).filter(block -> block instanceof IHasSpecialRenderer).forEach(block -> {
            ((IHasSpecialRenderer)block).initSpecialRenderer();
            ModLogger.info("[RENDERING] Loaded in special renderer for " + block.getRegistryName());
        });
    }

    @SubscribeEvent
    public static void onPreTextureStitch(TextureStitchEvent.Pre event) {
        BlockLoader.INSTANCE.getContentMap().values().stream().map(Supplier:: get).filter(block -> block instanceof INeedTexture).forEach(block -> {
            ((INeedTexture) block).getTextures().forEach(event :: addSprite);
            ModLogger.info("[TEXTURE] Loaded in " + ((INeedTexture) block).getTextures().size() + " custom texture for " + block.getRegistryName());
        });
    }

    @SubscribeEvent
    public static void onModelBakeEvent(ModelBakeEvent event) {
        BlockLoader.INSTANCE.getContentMap().values().stream().map(Supplier:: get).filter(block -> block instanceof IHasOBJModel).forEach(block -> {
            enableResourceDomain(block);
            try {
                IUnbakedModel model = ModelLoaderRegistry.getModelOrMissing(((IHasOBJModel) block).getOBJModelLocation());
                if (model instanceof OBJModel) {
                    IBakedModel bakedModel = model.bake(event.getModelLoader(), net.minecraftforge.client.model.ModelLoader.defaultTextureGetter(), new BasicState(model.getDefaultState(), false), DefaultVertexFormats.POSITION_TEX_NORMAL);
                    if (((IHasOBJModel) block).shouldRenderBlock()) {
                        event.getModelRegistry().put(new ModelResourceLocation(Objects.requireNonNull(block.getRegistryName()), ""), bakedModel);
                        ModLogger.info("[MODEL] Loaded in OBJ block model for " + block.getRegistryName());
                    }
                    if (((IHasOBJModel) block).shouldRenderItem()) {
                        event.getModelRegistry().put(new ModelResourceLocation(Objects.requireNonNull(block.getRegistryName()), "inventory"), bakedModel);
                        ModLogger.info("[MODEL] Loaded in OBJ item model for " + block.getRegistryName());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
