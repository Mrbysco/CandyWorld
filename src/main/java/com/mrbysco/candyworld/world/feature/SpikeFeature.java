package com.mrbysco.candyworld.world.feature;

import com.mojang.serialization.Codec;
import com.mrbysco.candyworld.world.feature.config.SpikeFeatureConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import java.util.Random;

public class SpikeFeature extends Feature<SpikeFeatureConfig> {
	public SpikeFeature(Codec<SpikeFeatureConfig> configCodec) {
		super(configCodec);
	}

	public boolean place(FeaturePlaceContext<SpikeFeatureConfig> placeContext) {
		SpikeFeatureConfig spikeFeatureConfig = placeContext.config();
		WorldGenLevel reader = placeContext.level();
		Random random = placeContext.random();
		BlockPos pos = placeContext.origin();
		BlockState blockstate = spikeFeatureConfig.stateProvider.getState(random, pos);
		BlockPos blockpos;
		if (spikeFeatureConfig.project) {
			blockpos = reader.getHeightmapPos(Heightmap.Types.WORLD_SURFACE_WG, pos);
		} else {
			blockpos = pos;
		}

		BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				blockpos$mutable.setWithOffset(blockpos, i + 8, 0, j + 8);
				if (reader.getChunk(blockpos$mutable) == reader.getChunk(blockpos)) {
					int blocksToPlace = 0;
					for (int k = 255; k >= 0; k--) {
						if (spikeFeatureConfig.whitelist.contains(reader.getBlockState(blockpos$mutable).getBlock()) && random.nextInt(spikeFeatureConfig.chance) == 0) {
							int min = spikeFeatureConfig.minLength;
							int max = spikeFeatureConfig.maxLength;
							blocksToPlace = random.nextInt(max + 1 - min) + min;
						} else if (blocksToPlace > 0 && !spikeFeatureConfig.whitelist.contains(reader.getBlockState(blockpos$mutable).getBlock())) {
							reader.setBlock(blockpos$mutable, blockstate, 2);

							blocksToPlace--;
						}
						blockpos$mutable = blockpos$mutable.setWithOffset(blockpos$mutable, Direction.DOWN);
					}
				}
			}
		}
		return true;
	}
}