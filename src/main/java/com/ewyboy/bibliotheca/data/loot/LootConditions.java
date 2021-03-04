package com.ewyboy.bibliotheca.data.loot;

import com.ewyboy.bibliotheca.data.loot.conitions.PlayerIsSneaking;
import net.minecraft.loot.LootConditionType;
import net.minecraft.util.registry.Registry;

import static com.ewyboy.bibliotheca.util.BibLibResourceLocationHelper.prefix;

public class LootConditions {

    // See LootConditionManager.class for reference

    public static final LootConditionType PLAYER_IS_SNEAKING = new LootConditionType(new PlayerIsSneaking.Serializer());

    public static void init() {
        Registry.register(Registry.LOOT_CONDITION_TYPE, prefix("player_is_sneaking"), PLAYER_IS_SNEAKING);
    }

}
