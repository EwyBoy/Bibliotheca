package com.ewyboy.bibliotheca.data.loot.conitions;

import com.ewyboy.bibliotheca.data.loot.LootConditions;
import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.entity.Entity;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.ILootCondition;

import java.util.Set;

public class PlayerIsSneaking implements ILootCondition {

    private static final PlayerIsSneaking INSTANCE = new PlayerIsSneaking();

    private PlayerIsSneaking() {}

    public LootConditionType func_230419_b_() {
        return LootConditions.PLAYER_IS_SNEAKING;
    }

    public Set<LootParameter<?>> getRequiredParameters() {
        return ImmutableSet.of(LootParameters.THIS_ENTITY);
    }

    // Looting condition to test for
    public boolean test(LootContext ctx) {
        Entity entity = null;
        if (ctx.has(LootParameters.THIS_ENTITY)) {
             entity = ctx.get(LootParameters.THIS_ENTITY);
        }
        return entity != null && entity.isSneaking();
    }

    public static IBuilder builder() {
        return () -> INSTANCE;
    }

    public static class Serializer implements ILootSerializer<PlayerIsSneaking> {
        
        public void serialize(JsonObject object, PlayerIsSneaking playerIsSneaking, JsonSerializationContext ctx) {}

        public PlayerIsSneaking deserialize(JsonObject object, JsonDeserializationContext ctx) {
            return PlayerIsSneaking.INSTANCE;
        }
    }
}

