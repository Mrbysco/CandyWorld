package com.mrbysco.candyworld.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class CandyCaneFeature extends Feature<BlockClusterFeatureConfig> {
	public CandyCaneFeature(Codec<BlockClusterFeatureConfig> configCodec) {
		super(configCodec);
	}

	public boolean place(ISeedReader reader, ChunkGenerator generator, Random random, BlockPos pos, BlockClusterFeatureConfig clusterFeatureConfig) {
		BlockState blockstate = clusterFeatureConfig.stateProvider.getState(random, pos);
		BlockPos blockpos;
		if (clusterFeatureConfig.project) {
			blockpos = reader.getHeightmapPos(Heightmap.Type.WORLD_SURFACE_WG, pos);
		} else {
			blockpos = pos;
		}

		int i = 0;

		BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
		for (int j = 0; j < clusterFeatureConfig.tries; ++j) {
			int yOffset = 0;
			if (blockpos.getY() > generator.getSeaLevel() - 2) {
				BlockPos newPos = new BlockPos(blockpos);
				for (int offset = 0; offset < blockpos.getY(); offset++) {
					newPos = newPos.below();
					if (blockstate.canSurvive(reader, newPos) && newPos.getY() < generator.getSeaLevel() - 2) {
						yOffset = offset;
						continue;
					}
				}
				if (yOffset == 0) {
					return false;
				}
			}

			blockpos$mutable.setWithOffset(blockpos, random.nextInt(clusterFeatureConfig.xspread + 1) - random.nextInt(clusterFeatureConfig.xspread + 1), -yOffset, random.nextInt(clusterFeatureConfig.zspread + 1) - random.nextInt(clusterFeatureConfig.zspread + 1));
			BlockPos blockpos1 = blockpos$mutable.below();
			BlockState blockstate1 = reader.getBlockState(blockpos1);
			if ((reader.isEmptyBlock(blockpos$mutable) || clusterFeatureConfig.canReplace && reader.getBlockState(blockpos$mutable).getMaterial().isReplaceable()) && blockstate.canSurvive(reader, blockpos$mutable) && (clusterFeatureConfig.whitelist.isEmpty() || clusterFeatureConfig.whitelist.contains(blockstate1.getBlock())) && !clusterFeatureConfig.blacklist.contains(blockstate1) && (!clusterFeatureConfig.needWater || reader.getFluidState(blockpos1.west()).is(FluidTags.WATER) || reader.getFluidState(blockpos1.east()).is(FluidTags.WATER) || reader.getFluidState(blockpos1.north()).is(FluidTags.WATER) || reader.getFluidState(blockpos1.south()).is(FluidTags.WATER))) {
				clusterFeatureConfig.blockPlacer.place(reader, blockpos$mutable, blockstate, random);
				++i;
			}
		}

		return i > 0;
	}
}