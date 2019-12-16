package com.ewyboy.bibliotheca;

import com.ewyboy.bibliotheca.common.content.BibItemGroup;
import com.ewyboy.bibliotheca.common.event.EventHandler;
import com.ewyboy.bibliotheca.common.loaders.ContentLoader;
import com.ewyboy.bibliotheca.proxy.ClientProxy;
import com.ewyboy.bibliotheca.proxy.CommonProxy;
import com.ewyboy.bibliotheca.proxy.IModProxy;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

import static com.ewyboy.bibliotheca.Bibliotheca.ID;

@Mod(ID)
public class Bibliotheca {

    public static final String ID = "bibliotheca";
    public static final String NAME = "Bibliotheca";

    private static final IModProxy proxy = DistExecutor.runForDist(
            () -> ClientProxy::new,
            () -> CommonProxy::new
    );

    public Bibliotheca() {
        EventHandler.MOD.register(proxy::setup);
        EventHandler.MOD.register(this::onGatherData);
        proxy.construct();
        ContentLoader.init(ID, null, null, Items.class, null);
    }

    private void onGatherData(final GatherDataEvent event) {
        for (ContentLoader<?> contentLoader : ContentLoader.getLoaders()) {
            contentLoader.genData(event.getGenerator(), event.getExistingFileHelper());
        }
    }

    public static class Items {
        public static final BibItemGroup BIB_GROUP = new BibItemGroup(ID, ID) {
            @Override
            public ItemStack createIcon() {
                return new ItemStack(TEST_ITEM);
            }
        };

        public static final Item TEST_ITEM = new Item(new Item.Properties().group(BIB_GROUP));
    }
}
