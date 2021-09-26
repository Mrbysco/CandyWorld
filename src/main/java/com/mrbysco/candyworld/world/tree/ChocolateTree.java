package com.mrbysco.candyworld.world.tree;

import com.mrbysco.candyworld.world.ModFeatureConfigs;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Random;

public class ChocolateTree extends Tree {
	/**
	 * Get a {@link ConfiguredFeature} of tree
	 */
	@Nullable
	@Override
	protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredFeature(Random random, boolean hasFlowers) {
		return ModFeatureConfigs.CHOCOLATE;
	}
}
