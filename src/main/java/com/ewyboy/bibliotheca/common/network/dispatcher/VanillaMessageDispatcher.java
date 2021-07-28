package com.ewyboy.biblibtest.common.network.dispatcher;

import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

public final class VanillaMessageDispatcher {

	public static void dispatchTEToNearbyPlayers(BlockEntity tile) {
		Level world = tile.getLevel();
		if (world instanceof ServerLevel) {
			ClientboundBlockEntityDataPacket message = tile.getUpdatePacket();
			BlockPos pos = tile.getBlockPos();
			for (Player player : world.players()) {
				if (player instanceof ServerPlayer && pointDistancePlane(player.getX(), player.getZ(), pos.getX(), pos.getZ()) < 64) {
					if (message != null) {
						((ServerPlayer) player).connection.send(message);
					}
				}
			}
		}
	}

	public static void dispatchTEToNearbyPlayers(Level world, BlockPos pos) {
		BlockEntity tile = world.getBlockEntity(pos);
		if (tile != null) {
			dispatchTEToNearbyPlayers(tile);
		}
	}

	public static float pointDistancePlane(double x1, double y1, double x2, double y2) {
		return (float) Math.hypot(x1 - x2, y1 - y2);
	}

}