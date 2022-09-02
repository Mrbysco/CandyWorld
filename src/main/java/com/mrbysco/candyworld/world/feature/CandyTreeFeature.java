package com.mrbysco.candyworld.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

public class CandyTreeFeature extends TreeFeature {
	public CandyTreeFeature(Codec<TreeConfiguration> configCodec) {
		super(configCodec);
	}
}
