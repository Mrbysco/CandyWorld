package com.mrbysco.candyworld.block.ore;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class CookieOreBlock extends Block {
	public CookieOreBlock(Properties properties) {
		super(properties);
	}

	@Override
	public int getExpDrop(BlockState state, LevelReader level, RandomSource randomSource, BlockPos pos, int fortuneLevel, int silkTouchLevel) {
		IntProvider xpRange = ConstantInt.of(3);
		return silkTouchLevel == 0 ? xpRange.sample(randomSource) : 0;
	}
}
