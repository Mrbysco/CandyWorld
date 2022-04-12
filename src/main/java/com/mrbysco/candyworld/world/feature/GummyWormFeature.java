package com.mrbysco.candyworld.world.feature;

import com.mojang.serialization.Codec;
import com.mrbysco.candyworld.block.gummy.GummyWormBlock;
import com.mrbysco.candyworld.enums.EnumGummy;
import com.mrbysco.candyworld.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Random;

public class GummyWormFeature extends Feature<NoneFeatureConfiguration> {
	private static final BlockState RED_GUMMY_WORM = ModBlocks.RED_GUMMY_WORM_BLOCK.get().defaultBlockState();
	private static final BlockState ORANGE_GUMMY_WORM = ModBlocks.ORANGE_GUMMY_WORM_BLOCK.get().defaultBlockState();
	private static final BlockState YELLOW_GUMMY_WORM = ModBlocks.YELLOW_GUMMY_WORM_BLOCK.get().defaultBlockState();
	private static final BlockState WHITE_GUMMY_WORM = ModBlocks.WHITE_GUMMY_WORM_BLOCK.get().defaultBlockState();
	private static final BlockState GREEN_GUMMY_WORM = ModBlocks.GREEN_GUMMY_WORM_BLOCK.get().defaultBlockState();

	public GummyWormFeature(Codec<NoneFeatureConfiguration> configCodec) {
		super(configCodec);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> placeContext) {
		WorldGenLevel reader = placeContext.level();
		Random random = placeContext.random();
		BlockPos pos = placeContext.origin();
		BlockPos surfacePos = reader.getHeightmapPos(Heightmap.Types.WORLD_SURFACE_WG, pos);

		if (reader.getBlockState(surfacePos.below()).getBlock() instanceof GummyWormBlock)
			return false;

		BlockState state = switch (EnumGummy.random(random)) {
			default -> RED_GUMMY_WORM;
			case ORANGE -> ORANGE_GUMMY_WORM;
			case YELLOW -> YELLOW_GUMMY_WORM;
			case WHITE -> WHITE_GUMMY_WORM;
			case GREEN -> GREEN_GUMMY_WORM;
		};

		int r = random.nextInt(3);
		return switch (r) {
			case 0 -> generateWormFlat(reader, surfacePos, random.nextInt(10) + 7, state, random);
			case 1 -> generateWormStraight(reader, surfacePos, random.nextInt(12) + 6, random.nextInt(4) + 3, state);
			case 2 -> generateWormArc(reader, surfacePos, state, random);
			default -> false;
		};

	}

	private boolean generateWormStraight(WorldGenLevel world, BlockPos position, int below, int above, BlockState state) {
		for (int i = -below; i < above; i++) {
			world.setBlock(position.above(i), state.setValue(BlockStateProperties.AXIS, Direction.Axis.Y), 2 | 16);
		}
		return true;
	}

	private boolean generateWormArc(WorldGenLevel world, BlockPos position, BlockState state, Random rand) {
		int height = rand.nextInt(2) + 2;
		int startDepth = rand.nextInt(4) + 4;
		Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(rand);

		state = state.setValue(BlockStateProperties.AXIS, Direction.Axis.Y);
		BlockPos pos = position.below(startDepth);
		for (int i = 0; i <= height + startDepth; i++) {
			pos = pos.above();
			world.setBlock(pos, state, 2 | 16);
		}

		state = state.setValue(BlockStateProperties.AXIS, direction.getAxis());
		for (int i = 0; i <= 2 + rand.nextInt(2); i++) {
			if (world.isAreaLoaded(pos.relative(direction), 2)) {
				pos = pos.relative(direction);
				world.setBlock(pos, state, 2 | 16);
			} else {
				break;
			}
		}

		state = state.setValue(BlockStateProperties.AXIS, Direction.Axis.Y);
		while (isAirOrLiquid(world, pos.below())) {
			pos = pos.below();
			world.setBlock(pos, state, 2 | 16);
		}
		for (int i = 0; i <= 4 + rand.nextInt(4); i++) {
			pos = pos.below();
			world.setBlock(pos, state, 2 | 16);
		}
		return true;
	}

	private boolean generateWormFlat(WorldGenLevel world, BlockPos position, int length, BlockState state, Random rand) {
		BlockPos pos = position.above();
		Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(rand);
		int lastTurnDir = 0;
		boolean hasTurned = false;
		for (int i = 0; i <= length; i++) {
			world.setBlock(pos, state.setValue(BlockStateProperties.AXIS, direction.getAxis()), 2 | 16);

			// randomly change direction
			if (hasTurned) {
				hasTurned = false;
			} else if (rand.nextInt(3) == 0) {
				direction = direction.getClockWise();
				if (lastTurnDir == 1 || (lastTurnDir == 0 && rand.nextBoolean())) {
					direction = direction.getClockWise().getClockWise();
					lastTurnDir = -1;
				} else {
					lastTurnDir = 1;
				}
				hasTurned = true;
			}

			if (!world.isAreaLoaded(pos.relative(direction), 2)) {
				break;
			}


			// fall
			while (isAirOrLiquid(world, pos.below())) {
				pos = pos.below();
				world.setBlock(pos, state.setValue(BlockStateProperties.AXIS, Direction.Axis.Y), 2 | 16);
				i++;
			}

			// climb
			while (!isAirOrLiquid(world, pos.relative(direction))) {
				pos = pos.above();
				if (!isAirOrLiquid(world, pos)) {
					return true;
				}
				world.setBlock(pos, state.setValue(BlockStateProperties.AXIS, Direction.Axis.Y), 2 | 16);
				i++;
			}

			pos = pos.relative(direction);
		}
		return true;
	}

	private boolean isAirOrLiquid(WorldGenLevel world, BlockPos pos) {
		return isAir(world, pos) && pos.getY() >= 0 || world.getBlockState(pos).getMaterial().isLiquid();
	}
}
