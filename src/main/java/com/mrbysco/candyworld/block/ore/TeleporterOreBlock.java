package com.mrbysco.candyworld.block.ore;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class TeleporterOreBlock extends Block {

	public TeleporterOreBlock(Properties properties) {
		super(properties);
	}

	@Override
	public int getExpDrop(BlockState state, LevelReader level, RandomSource randomSource, BlockPos pos, int fortuneLevel, int silkTouchLevel) {
		//ToDO: Figure out how to adjust range to 3 - 7
		IntProvider xpRange = ConstantInt.of(7);
		return silkTouchLevel == 0 ? xpRange.sample(randomSource) : 0;
	}
}
