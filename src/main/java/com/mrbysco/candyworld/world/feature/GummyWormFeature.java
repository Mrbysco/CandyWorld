package com.mrbysco.candyworld.world.feature;

import com.mojang.serialization.Codec;
import com.mrbysco.candyworld.block.gummy.GummyWormBlock;
import com.mrbysco.candyworld.enums.EnumGummy;
import com.mrbysco.candyworld.registry.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class GummyWormFeature extends Feature<NoFeatureConfig>{
    private static final BlockState RED_GUMMY_WORM = ModBlocks.RED_GUMMY_WORM_BLOCK.get().defaultBlockState();
    private static final BlockState ORANGE_GUMMY_WORM = ModBlocks.ORANGE_GUMMY_WORM_BLOCK.get().defaultBlockState();
    private static final BlockState YELLOW_GUMMY_WORM = ModBlocks.YELLOW_GUMMY_WORM_BLOCK.get().defaultBlockState();
    private static final BlockState WHITE_GUMMY_WORM = ModBlocks.WHITE_GUMMY_WORM_BLOCK.get().defaultBlockState();
    private static final BlockState GREEN_GUMMY_WORM = ModBlocks.GREEN_GUMMY_WORM_BLOCK.get().defaultBlockState();

    public GummyWormFeature(Codec<NoFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        BlockPos surfacePos = reader.getHeightmapPos(Heightmap.Type.WORLD_SURFACE_WG, pos);

        if (reader.getBlockState(surfacePos.below()).getBlock() instanceof GummyWormBlock)
            return false;

        BlockState state;
        switch(EnumGummy.random(rand)) {
            default:
                state = RED_GUMMY_WORM;
                break;
            case ORANGE:
                state = ORANGE_GUMMY_WORM;
                break;
            case YELLOW:
                state = YELLOW_GUMMY_WORM;
                break;
            case WHITE:
                state = WHITE_GUMMY_WORM;
                break;
            case GREEN:
                state = GREEN_GUMMY_WORM;
                break;
        }

        int r = rand.nextInt(3);
        switch (r) {
            case 0:
                return generateWormFlat(reader, surfacePos, rand.nextInt(10) + 7, state, rand);
            case 1:
                return generateWormStraight(reader, surfacePos, rand.nextInt(12) + 6, rand.nextInt(4) + 3, state);
            case 2:
                return generateWormArc(reader, surfacePos, state, rand);
        }

        return false;
    }

    private boolean generateWormStraight(ISeedReader world, BlockPos position, int below, int above, BlockState state) {
        for (int i = -below; i < above; i++) {
            world.setBlock(position.above(i), state.setValue(BlockStateProperties.AXIS, Direction.Axis.Y), 2 | 16);
        }
        return true;
    }

    private boolean generateWormArc(ISeedReader world, BlockPos position, BlockState state, Random rand) {
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

    private boolean generateWormFlat(ISeedReader world, BlockPos position, int length, BlockState state, Random rand) {
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

    private boolean isAirOrLiquid(ISeedReader world, BlockPos pos) {
        return isAir(world, pos) && pos.getY() >= 0 || world.getBlockState(pos).getMaterial().isLiquid();
    }
}
