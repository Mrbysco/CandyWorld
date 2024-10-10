package com.mrbysco.candyworld.block.cottoncandy;

import com.mrbysco.candyworld.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class CottonCandyLeavesBlock extends LeavesBlock {

	public CottonCandyLeavesBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(DISTANCE, Integer.valueOf(7)).setValue(PERSISTENT, Boolean.valueOf(false)));
	}

	@Override
	public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
		world.setBlock(pos, CottonCandyLeavesBlock.updateDistance(state, world, pos), 3);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState state2, LevelAccessor world, BlockPos pos, BlockPos pos2) {
		int i = CottonCandyLeavesBlock.getDistanceAt(state2) + 1;
		if (i != 1 || state.getValue(DISTANCE) != i) {
			world.scheduleTick(pos, this, 1);
		}

		return state;
	}

	private static BlockState updateDistance(BlockState state, LevelAccessor world, BlockPos pos) {
		int i = 7;
		BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();

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
	public BlockState getStateForPlacement(BlockPlaceContext p_196258_1_) {
		return updateDistance(this.defaultBlockState().setValue(PERSISTENT, Boolean.valueOf(true)), p_196258_1_.getLevel(), p_196258_1_.getClickedPos());
	}
}
