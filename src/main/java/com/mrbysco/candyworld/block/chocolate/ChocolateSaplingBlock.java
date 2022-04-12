package com.mrbysco.candyworld.block.chocolate;

import com.mrbysco.candyworld.registry.ModBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.trees.Tree;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class ChocolateSaplingBlock extends SaplingBlock {

	public ChocolateSaplingBlock(Tree treeIn, Properties properties) {
		super(treeIn, properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(STAGE, Integer.valueOf(0)));
	}

	@Override
	public boolean canBeReplaced(BlockState state, Fluid fluid) {
		return false;
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return state.is(ModBlocks.CANDY_GRASS_BLOCK.get()) || state.is(ModBlocks.MILK_BROWNIE_BLOCK.get()) ||
				state.is(ModBlocks.CHOCOLATE_COVERED_WHITE_BROWNIE.get()) || state.is(ModBlocks.WHITE_BROWNIE_BLOCK.get()) ||
				state.is(ModBlocks.DARK_CANDY_GRASS.get()) || state.is(ModBlocks.DARK_BROWNIE_BLOCK.get()) ||
				super.mayPlaceOn(state, worldIn, pos);
	}

	@Override
	public OffsetType getOffsetType() {
		return AbstractBlock.OffsetType.XZ;
	}
}
