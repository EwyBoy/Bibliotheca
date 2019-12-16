package com.ewyboy.bibliotheca.compatibilities.hwyla;

import mcp.mobius.waila.api.IDataAccessor;
import mcp.mobius.waila.api.IPluginConfig;
import net.minecraft.item.ItemStack;

public interface IWailaCamouflage {

    ItemStack decorateBlock(IDataAccessor accessor, IPluginConfig config);

}
