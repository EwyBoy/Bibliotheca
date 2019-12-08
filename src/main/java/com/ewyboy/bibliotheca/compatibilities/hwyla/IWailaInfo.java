package com.ewyboy.bibliotheca.compatibilities.hwyla;

import mcp.mobius.waila.api.IDataAccessor;
import mcp.mobius.waila.api.IPluginConfig;
import net.minecraft.util.text.ITextComponent;

import java.util.List;

/**
 * Created by EwyBoy
 **/
public interface IWailaInfo {
    void getWailaBody(List<ITextComponent> tooltip, IDataAccessor accessor, IPluginConfig config);
}
