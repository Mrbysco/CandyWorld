package com.mrbysco.candyworld.world.surface;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;

public class ChocolateSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {
	public ChocolateSurfaceBuilder(Codec<SurfaceBuilderConfig> builderConfigCodec) {
		super(builderConfigCodec);
	}

	public void apply(Random random, IChunk chunk, Biome biome, int x, int z, int height, double noiseVal, BlockState state, BlockState liquidState, int seaLevel, long seed, SurfaceBuilderConfig builderConfig) {
		SurfaceBuilderConfig chocolateConfig = ModSurfaceBuilder.CONFIG_CHOCOLATE_COVERED_WHITE_BROWNIE;

		this.apply(random, chunk, biome, x, z, height, noiseVal, state, liquidState, chocolateConfig.getTopMaterial(), chocolateConfig.getUnderMaterial(), chocolateConfig.getUnderwaterMaterial(), seaLevel);
	}

	protected void apply(Random random, IChunk chunk, Biome biome, int x, int z, int height, double noiseVal, BlockState state, BlockState liquidState, BlockState topBlockstate, BlockState underState, BlockState underWaterState, int seaLevel) {
		BlockState topState = topBlockstate;
		BlockState fillerState = underState;
		BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
		int i = -1;
		int j = (int) (noiseVal / 3.0D + 3.0D + random.nextDouble() * 0.25D);
		int k = x & 15;
		int l = z & 15;

		for (int i1 = height; i1 >= 0; --i1) {
			blockpos$mutable.set(k, i1, l);
			BlockState blockstate2 = chunk.getBlockState(blockpos$mutable);
			if (blockstate2.isAir()) {
				i = -1;
			} else if (blockstate2.is(state.getBlock())) {
				if (i == -1) {
					if (j <= 0) {
						topState = Blocks.AIR.defaultBlockState();
						fillerState = state;
					} else if (i1 >= seaLevel - 4 && i1 <= seaLevel + 1) {
						topState = topBlockstate;
						fillerState = underState;
					}

					if (i1 < seaLevel && (topState == null || topState.isAir())) {
						if (biome.getTemperature(blockpos$mutable.set(x, i1, z)) < 0.15F) {
							topState = Blocks.ICE.defaultBlockState();
						} else {
							topState = liquidState;
						}

						blockpos$mutable.set(k, i1, l);
					}

					i = j;
					if (i1 >= seaLevel - 1) {
						chunk.setBlockState(blockpos$mutable, topState, false);
					} else if (i1 < seaLevel - 7 - j) {
						topState = Blocks.AIR.defaultBlockState();
						fillerState = state;
						chunk.setBlockState(blockpos$mutable, underWaterState, false);
					} else {
						chunk.setBlockState(blockpos$mutable, fillerState, false);
					}
				} else if (i > 0) {
					--i;
					chunk.setBlockState(blockpos$mutable, fillerState, false);
				}
			}
		}
	}
}
