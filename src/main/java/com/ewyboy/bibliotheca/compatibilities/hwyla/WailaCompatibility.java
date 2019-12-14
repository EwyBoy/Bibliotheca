package com.ewyboy.bibliotheca.compatibilities.hwyla;

import com.ewyboy.bibliotheca.common.loaders.BlockLoader;
import com.ewyboy.bibliotheca.util.ModLogger;
import mcp.mobius.waila.api.*;
import net.minecraft.util.text.ITextComponent;

import java.util.List;
import java.util.function.Supplier;

@WailaPlugin
public class WailaCompatibility implements IComponentProvider, IWailaPlugin {

    private static boolean loaded;
    private static final WailaCompatibility INSTANCE = new WailaCompatibility();

    @Override
    public void appendBody(List<ITextComponent> tooltip, IDataAccessor accessor, IPluginConfig config) {
        if (accessor.getBlock() instanceof IWailaInfo) {
            ((IWailaInfo) accessor.getBlock()).getWailaBody(tooltip, accessor, config);
        }
    }

    @Override
    public void register(IRegistrar registrar) {
        if (!loaded) {
            BlockLoader.INSTANCE.getContentMap().values().stream().map(Supplier::get).forEach(block -> {
                if (block instanceof IWailaInfo) {
                    ModLogger.info("Waila information registered for " + block.getRegistryName());
                    registrar.registerComponentProvider(INSTANCE, TooltipPosition.BODY, block.getClass());
                }
            });
            /*ContentLoader.BLOCK_LIST.values().forEach(block -> {
                if (block instanceof IWailaInfo) {
                    ModLogger.info("Waila information registered for " + block.getRegistryName());
                    registrar.registerComponentProvider(INSTANCE, TooltipPosition.BODY, block.getClass());
                }
            });*/
        }
        loaded = true;
    }
}
