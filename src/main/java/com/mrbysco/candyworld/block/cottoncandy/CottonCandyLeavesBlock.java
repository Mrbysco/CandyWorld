package com.mrbysco.candyworld.block.cottoncandy;

import com.mrbysco.candyworld.registry.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class CottonCandyLeavesBlock extends LeavesBlock {

	public CottonCandyLeavesBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(DISTANCE, Integer.valueOf(7)).setValue(PERSISTENT, Boolean.valueOf(false)));
	}

	@Override
	public void tick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		world.setBlock(pos, CottonCandyLeavesBlock.updateDistance(state, world, pos), 3);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState state2, IWorld world, BlockPos pos, BlockPos pos2) {
		int i = CottonCandyLeavesBlock.getDistanceAt(state2) + 1;
		if (i != 1 || state.getValue(DISTANCE) != i) {
			world.getBlockTicks().scheduleTick(pos, this, 1);
		}

		return state;
	}

	private static BlockState updateDistance(BlockState state, IWorld world, BlockPos pos) {
		int i = 7;
		BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

		for (Direction direction : Direction.values()) {
			blockpos$mutable.setWithOffset(pos, direction);
			i = Math.min(i, CottonCandyLeavesBlock.getDistanceAt(world.getBlockState(blockpos$mutable)) + 1);
			if (i == 1) {
				break;
			}
		}

		return state.setValue(DISTANCE, Integer.valueOf(i));
	}

	private static int getDistanceAt(BlockState state) {
		if (state.is(ModBlocks.WHITE_CANDY_CANE_BLOCK.get())) {
			return 0;
		} else {
			return state.getBlock() instanceof CottonCandyLeavesBlock ? state.getValue(DISTANCE) : 7;
		}
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext p_196258_1_) {
		return updateDistance(this.defaultBlockState().setValue(PERSISTENT, Boolean.valueOf(true)), p_196258_1_.getLevel(), p_196258_1_.getClickedPos());
	}
}
