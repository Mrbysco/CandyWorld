package com.mrbysco.candyworld.world.tree;

import com.mrbysco.candyworld.world.ModFeatureConfigs;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

import javax.annotation.Nullable;
import java.util.Random;

public class CottonCandyTree extends AbstractTreeGrower {
	@Nullable
	@Override
	protected ConfiguredFeature<TreeConfiguration, ?> getConfiguredFeature(Random random, boolean hasFlowers) {
		return ModFeatureConfigs.COTTON_CANDY;
	}
}
