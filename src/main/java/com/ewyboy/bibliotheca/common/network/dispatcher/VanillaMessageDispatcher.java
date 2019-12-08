package com.ewyboy.bibliotheca.common.network.dispatcher;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public final class VanillaMessageDispatcher {

	public static void dispatchTEToNearbyPlayers(TileEntity tile) {
		World world = tile.getWorld();
		if (world instanceof ServerWorld) {
			SUpdateTileEntityPacket message = tile.getUpdatePacket();
			BlockPos pos = tile.getPos();
			for (PlayerEntity player : world.getPlayers()) {
				if (player instanceof ServerPlayerEntity && pointDistancePlane(player.posX, player.posZ, pos.getX(), pos.getZ()) < 64) {
					if (message != null) {
						((ServerPlayerEntity) player).connection.sendPacket(message);
					}
				}
			}
		}
	}

	public static void dispatchTEToNearbyPlayers(World world, BlockPos pos) {
		TileEntity tile = world.getTileEntity(pos);
		if (tile != null) {
			dispatchTEToNearbyPlayers(tile);
		}
	}

	public static float pointDistancePlane(double x1, double y1, double x2, double y2) {
		return (float) Math.hypot(x1 - x2, y1 - y2);
	}

}