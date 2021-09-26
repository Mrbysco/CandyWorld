package com.mrbysco.candyworld.world.feature;

import com.mojang.serialization.Codec;
import com.mrbysco.candyworld.world.feature.config.SpikeFeatureConfig;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class SpikeFeature extends Feature<SpikeFeatureConfig> {
	public SpikeFeature(Codec<SpikeFeatureConfig> configCodec) {
		super(configCodec);
	}

	public boolean place(ISeedReader reader, ChunkGenerator generator, Random random, BlockPos pos, SpikeFeatureConfig spikeFeatureConfig) {
		BlockState blockstate = spikeFeatureConfig.stateProvider.getState(random, pos);
		BlockPos blockpos;
		if (spikeFeatureConfig.project) {
			blockpos = reader.getHeightmapPos(Heightmap.Type.WORLD_SURFACE_WG, pos);
		} else {
			blockpos = pos;
		}

		BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				blockpos$mutable.setWithOffset(blockpos, i + 8, 0, j + 8);
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
		return true;
	}
}