package com.mrbysco.candyworld.block.gummy;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;

public class GummyBlock extends BaseGummyBlock {
	public GummyBlock(Properties properties) {
		super(properties);
	}

	@Override
	public boolean isSlimeBlock(BlockState state) {
		return true;
	}

	@Override
	public boolean isStickyBlock(BlockState state) {
		return true;
	}

	@Override
	public boolean canStickTo(BlockState state, BlockState other) {
		Block stateBlock = state.getBlock();
		Block otherBlock = other.getBlock();

		if (stateBlock instanceof GummyBlock) {
			if (otherBlock == Blocks.SLIME_BLOCK || otherBlock == Blocks.HONEY_BLOCK) {
				return false;
			}
		}

		if (otherBlock instanceof GummyBlock) {
			if (stateBlock == Blocks.SLIME_BLOCK || stateBlock == Blocks.HONEY_BLOCK) {
				return false;
			}
		}

		if (stateBlock != otherBlock && stateBlock instanceof GummyBlock && otherBlock instanceof GummyBlock) {
			return false;
		}

		return super.canStickTo(state, other);
	}
}
