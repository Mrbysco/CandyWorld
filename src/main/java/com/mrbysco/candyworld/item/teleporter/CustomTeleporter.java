package com.mrbysco.candyworld.item.teleporter;

import com.mrbysco.candyworld.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.portal.PortalInfo;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.ITeleporter;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.Function;

public class CustomTeleporter implements ITeleporter {
	private static final ResourceKey<Level> dimension = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation("candyworld", "candy_world"));

	@Nullable
	@Override
	public PortalInfo getPortalInfo(Entity entity, ServerLevel destWorld, Function<ServerLevel, PortalInfo> defaultPortalInfo) {
		PortalInfo pos;

		pos = placeInWorld(destWorld, entity, entity.blockPosition(), entity instanceof Player);
		pos = moveToSafeCoords(destWorld, entity, pos != null ? new BlockPos(pos.pos) : entity.blockPosition());

		return pos;
	}

	public CustomTeleporter() {
	}

	@Nullable
	private PortalInfo placeInWorld(ServerLevel destWorld, Entity entity, BlockPos pos, boolean isPlayer) {
		boolean isToOverworld = destWorld.dimension() == Level.OVERWORLD;
		boolean isFromCandyWorld = entity.level.dimension() == dimension && isToOverworld;
		BlockPos blockpos = destWorld.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, destWorld.getSharedSpawnPos());
		if (!isFromCandyWorld) {
			blockpos = pos.offset(0, 255, 0);
			BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();
			for (int y = 255; y >= 1; y--) {
				blockpos$mutable.set(blockpos.getX(), y, blockpos.getZ());
				if (!destWorld.getBlockState(blockpos$mutable).isAir()) {
					blockpos = blockpos$mutable;
					break;
				}
			}
		}
		float angle = entity.getXRot();

		if (isPlayer && entity instanceof ServerPlayer serverPlayer) {
			BlockPos respawnPos = serverPlayer.getRespawnPosition();
			float respawnAngle = serverPlayer.getRespawnAngle();
			Optional<Vec3> optional;
			if (serverPlayer != null && respawnPos != null) {
				optional = Player.findRespawnPositionAndUseSpawnBlock(destWorld, respawnPos, respawnAngle, false, false);
			} else {
				optional = Optional.empty();
			}

			if (optional.isPresent()) {
				BlockState blockstate = destWorld.getBlockState(respawnPos);
				boolean blockIsRespawnAnchor = blockstate.is(Blocks.RESPAWN_ANCHOR);
				Vec3 vector3d = optional.get();
				float f1;
				if (!blockstate.is(BlockTags.BEDS) && !blockIsRespawnAnchor) {
					f1 = respawnAngle;
				} else {
					Vec3 vector3d1 = Vec3.atBottomCenterOf(respawnPos).subtract(vector3d).normalize();
					f1 = (float) Mth.wrapDegrees(Mth.atan2(vector3d1.z, vector3d1.x) * (double) (180F / (float) Math.PI) - 90.0D);
				}
				angle = f1;
				blockpos = new BlockPos(vector3d.x, vector3d.y, vector3d.z);
			}
		}
		return new PortalInfo(new Vec3((double) blockpos.getX() + 0.5D, blockpos.getY(), (double) blockpos.getZ() + 0.5D), entity.getDeltaMovement(), angle, entity.getXRot());
	}

	private PortalInfo moveToSafeCoords(ServerLevel world, Entity entity, BlockPos pos) {
		boolean toCandyWorld = world.dimension() == dimension;

		if (toCandyWorld) {
			if (!world.getFluidState(pos).isEmpty()) {
				int x = pos.getX();
				int y = pos.getY();
				int z = pos.getZ();
				BlockPos.betweenClosed(x - 1, y, z - 1, x + 1, y, z + 1).forEach((blockPos) -> {
					if (!world.getFluidState(blockPos).isEmpty() || world.getBlockState(blockPos).getDestroySpeed(world, blockPos) >= 0) {
						world.setBlockAndUpdate(blockPos, ModBlocks.CRYSTALLIZED_SUGAR.get().defaultBlockState());
					}
				});
			}
			return makePortalInfo(entity, pos.getX(), pos.getY() + 2, pos.getZ());
		}
		return makePortalInfo(entity, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public Entity placeEntity(Entity entity, ServerLevel currentWorld, ServerLevel destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
		if (!(entity instanceof Player)) {
			throw new IllegalArgumentException("This teleporter can only teleport players");
		}
		entity.fallDistance = 0;
//            entity.moveToBlockPosAndAngles(findTargetPos((PlayerEntity) entity, world.provider.getDimension()), yaw, entity.rotationPitch);
		return repositionEntity.apply(false); //Must be false or we fall on vanilla
	}

	private PortalInfo makePortalInfo(Entity entity, double x, double y, double z) {
		return makePortalInfo(entity, new Vec3(x, y, z));
	}

	private PortalInfo makePortalInfo(Entity entity, Vec3 pos) {
		return new PortalInfo(pos, Vec3.ZERO, entity.getYRot(), entity.getXRot());
	}
}
