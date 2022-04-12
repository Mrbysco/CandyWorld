package com.mrbysco.candyworld.world.feature;

import com.mojang.serialization.Codec;
import com.mrbysco.candyworld.registry.ModBlocks;
import com.mrbysco.candyworld.registry.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.BaseStoneSource;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.material.Material;

import java.util.Random;

public class CustomLakesFeature extends Feature<BlockStateConfiguration> {
	private static final BlockState AIR = Blocks.CAVE_AIR.defaultBlockState();

	public CustomLakesFeature(Codec<BlockStateConfiguration> configCodec) {
		super(configCodec);
	}

	public boolean place(FeaturePlaceContext<BlockStateConfiguration> context) {
		BlockPos pos = context.origin();
		WorldGenLevel reader = context.level();
		Random random = context.random();

		BlockStateConfiguration stateFeatureConfig;
		for (stateFeatureConfig = context.config(); pos.getY() > reader.getMinBuildHeight() + 5 && reader.isEmptyBlock(pos); pos = pos.below()) {
		}

		if (pos.getY() <= 4) {
			return false;
		} else {
			pos = pos.below(4);
			if (reader.startsForFeature(SectionPos.of(pos), StructureFeature.VILLAGE).findAny().isPresent()) {
				return false;
			} else {
				boolean[] aboolean = new boolean[2048];
				int i = random.nextInt(4) + 4;

				for (int j = 0; j < i; ++j) {
					double d0 = random.nextDouble() * 6.0D + 3.0D;
					double d1 = random.nextDouble() * 4.0D + 2.0D;
					double d2 = random.nextDouble() * 6.0D + 3.0D;
					double d3 = random.nextDouble() * (16.0D - d0 - 2.0D) + 1.0D + d0 / 2.0D;
					double d4 = random.nextDouble() * (8.0D - d1 - 4.0D) + 2.0D + d1 / 2.0D;
					double d5 = random.nextDouble() * (16.0D - d2 - 2.0D) + 1.0D + d2 / 2.0D;

					for (int l = 1; l < 15; ++l) {
						for (int i1 = 1; i1 < 15; ++i1) {
							for (int j1 = 1; j1 < 7; ++j1) {
								double d6 = ((double) l - d3) / (d0 / 2.0D);
								double d7 = ((double) j1 - d4) / (d1 / 2.0D);
								double d8 = ((double) i1 - d5) / (d2 / 2.0D);
								double d9 = d6 * d6 + d7 * d7 + d8 * d8;
								if (d9 < 1.0D) {
									aboolean[(l * 16 + i1) * 8 + j1] = true;
								}
							}
						}
					}
				}

				for (int k1 = 0; k1 < 16; ++k1) {
					for (int l2 = 0; l2 < 16; ++l2) {
						for (int k = 0; k < 8; ++k) {
							boolean flag = !aboolean[(k1 * 16 + l2) * 8 + k] && (k1 < 15 && aboolean[((k1 + 1) * 16 + l2) * 8 + k] || k1 > 0 && aboolean[((k1 - 1) * 16 + l2) * 8 + k] || l2 < 15 && aboolean[(k1 * 16 + l2 + 1) * 8 + k] || l2 > 0 && aboolean[(k1 * 16 + (l2 - 1)) * 8 + k] || k < 7 && aboolean[(k1 * 16 + l2) * 8 + k + 1] || k > 0 && aboolean[(k1 * 16 + l2) * 8 + (k - 1)]);
							if (flag) {
								Material material = reader.getBlockState(pos.offset(k1, k, l2)).getMaterial();
								if (k >= 4 && material.isLiquid()) {
									return false;
								}

								if (k < 4 && !material.isSolid() && reader.getBlockState(pos.offset(k1, k, l2)) != stateFeatureConfig.state) {
									return false;
								}
							}
						}
					}
				}

				for (int l1 = 0; l1 < 16; ++l1) {
					for (int l2 = 0; l2 < 16; ++l2) {
						for (int l3 = 0; l3 < 8; ++l3) {
							if (aboolean[(l1 * 16 + l2) * 8 + l3]) {
								BlockPos blockpos2 = pos.offset(l1, l3, l2);
								boolean flag1 = l3 >= 4;
								reader.setBlock(blockpos2, flag1 ? AIR : stateFeatureConfig.state, 2);
								if (flag1) {
									reader.getBlockTicks().scheduleTick(blockpos2, AIR.getBlock(), 0);
									this.markAboveForPostProcessing(reader, blockpos2);
								}
							}
						}
					}
				}

				for (int i2 = 0; i2 < 16; ++i2) {
					for (int j3 = 0; j3 < 16; ++j3) {
						for (int j4 = 4; j4 < 8; ++j4) {
							if (aboolean[(i2 * 16 + j3) * 8 + j4]) {
								BlockPos blockpos = pos.offset(i2, j4 - 1, j3);
								if (isSoil(reader, blockpos) && reader.getBrightness(LightLayer.SKY, pos.offset(i2, j4, j3)) > 0) {
									reader.setBlock(blockpos, ModBlocks.CRYSTALLIZED_SUGAR.get().defaultBlockState(), 2);
								}
							}
						}
					}
				}

				if (stateFeatureConfig.state.getMaterial() == Material.LAVA) {
					BaseStoneSource basestonesource = context.chunkGenerator().getBaseStoneSource();

					for (int j3 = 0; j3 < 16; ++j3) {
						for (int j4 = 0; j4 < 16; ++j4) {
							for (int l4 = 0; l4 < 8; ++l4) {
								boolean flag2 = !aboolean[(j3 * 16 + j4) * 8 + l4] && (j3 < 15 && aboolean[((j3 + 1) * 16 + j4) * 8 + l4] || j3 > 0 && aboolean[((j3 - 1) * 16 + j4) * 8 + l4] || j4 < 15 && aboolean[(j3 * 16 + j4 + 1) * 8 + l4] || j4 > 0 && aboolean[(j3 * 16 + (j4 - 1)) * 8 + l4] || l4 < 7 && aboolean[(j3 * 16 + j4) * 8 + l4 + 1] || l4 > 0 && aboolean[(j3 * 16 + j4) * 8 + (l4 - 1)]);
								if (flag2 && (l4 < 4 || random.nextInt(2) != 0)) {
									BlockState blockstate = reader.getBlockState(pos.offset(j3, l4, j4));
									if (blockstate.getMaterial().isSolid() && !blockstate.is(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE)) {
										BlockPos blockpos1 = pos.offset(j3, l4, j4);
										reader.setBlock(blockpos1, basestonesource.getBaseBlock(blockpos1), 2);
										this.markAboveForPostProcessing(reader, blockpos1);
									}
								}
							}
						}
					}
				}

				return true;
			}
		}
	}

	private static boolean isSoil(LevelSimulatedReader reader, BlockPos position) {
		return reader.isStateAtPosition(position, (state) -> {
			Block block = state.getBlock();
			return isSoil(block) || isDirt(state);
		});
	}

	private static boolean isSoil(Block block) {
		return ModTags.CANDY_SOIL.contains(block);
	}
}