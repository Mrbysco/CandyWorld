package com.mrbysco.candyworld.world.feature;

import com.mojang.serialization.Codec;
import com.mrbysco.candyworld.registry.ModTags;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer.Foliage;

import java.util.List;
import java.util.OptionalInt;
import java.util.Random;
import java.util.Set;

public class CandyTreeFeature extends TreeFeature {
	public CandyTreeFeature(Codec<BaseTreeFeatureConfig> configCodec) {
		super(configCodec);
	}

	@Override
	public boolean doPlace(IWorldGenerationReader reader, Random random, BlockPos pos, Set<BlockPos> p_225557_4_, Set<BlockPos> p_225557_5_, MutableBoundingBox p_225557_6_, BaseTreeFeatureConfig treeFeatureConfig) {
		int i = treeFeatureConfig.trunkPlacer.getTreeHeight(random);
		int j = treeFeatureConfig.foliagePlacer.foliageHeight(random, i, treeFeatureConfig);
		int k = i - j;
		int l = treeFeatureConfig.foliagePlacer.foliageRadius(random, k);
		BlockPos blockpos;
		if (!treeFeatureConfig.fromSapling) {
			int i1 = reader.getHeightmapPos(Heightmap.Type.OCEAN_FLOOR, pos).getY();
			int j1 = reader.getHeightmapPos(Heightmap.Type.WORLD_SURFACE, pos).getY();
			if (j1 - i1 > treeFeatureConfig.maxWaterDepth) {
				return false;
			}

			int k1;
			if (treeFeatureConfig.heightmap == Heightmap.Type.OCEAN_FLOOR) {
				k1 = i1;
			} else if (treeFeatureConfig.heightmap == Heightmap.Type.WORLD_SURFACE) {
				k1 = j1;
			} else {
				k1 = reader.getHeightmapPos(treeFeatureConfig.heightmap, pos).getY();
			}

			blockpos = new BlockPos(pos.getX(), k1, pos.getZ());
		} else {
			blockpos = pos;
		}

		if (blockpos.getY() >= 1 && blockpos.getY() + i + 1 <= 256) {
			if (!isSoil(reader, blockpos.below())) {
				return false;
			} else {
				OptionalInt optionalint = treeFeatureConfig.minimumSize.minClippedHeight();
				int l1 = this.getMaxFreeTreeHeight(reader, i, blockpos, treeFeatureConfig);
				if (l1 >= i || optionalint.isPresent() && l1 >= optionalint.getAsInt()) {
					List<Foliage> list = treeFeatureConfig.trunkPlacer.placeTrunk(reader, random, l1, blockpos, p_225557_4_, p_225557_6_, treeFeatureConfig);
					list.forEach((p_236407_8_) -> {
						treeFeatureConfig.foliagePlacer.createFoliage(reader, random, treeFeatureConfig, l1, p_236407_8_, j, l, p_225557_5_, p_225557_6_);
					});
					return true;
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
	}

	private static boolean isSoil(IWorldGenerationBaseReader reader, BlockPos position) {
		return reader.isStateAtPosition(position, (state) -> {
			Block block = state.getBlock();
			return isSoil(block) || isDirt(block);
		});
	}

	public static boolean isSoil(Block block) {
		return ModTags.CANDY_SOIL.contains(block);
	}
}
