package com.mrbysco.candyworld.world.tree;

import com.mrbysco.candyworld.world.ModConfiguredFeatures;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Random;

public class CottonCandyTree extends AbstractTreeGrower {
	@Nullable
	@Override
	protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(Random random, boolean hasFlowers) {
		return ModConfiguredFeatures.COTTON_CANDY_TREE.getHolder().orElse(null);
	}
}
