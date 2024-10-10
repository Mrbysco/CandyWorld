package com.mrbysco.candyworld.block.chocolate;

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

public class ChocolateLeavesBlock extends LeavesBlock {

	public ChocolateLeavesBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(DISTANCE, Integer.valueOf(7)).setValue(PERSISTENT, Boolean.valueOf(false)));
	}

	@Override
	public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
		world.setBlock(pos, ChocolateLeavesBlock.updateDistance(state, world, pos), 3);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState state2, LevelAccessor world, BlockPos pos, BlockPos pos2) {
		int i = getDistanceAt(state2) + 1;
		if (i != 1 || state.getValue(DISTANCE) != i) {
			world.scheduleTick(pos, this, 1);
		}

		return state;
	}

	private static BlockState updateDistance(BlockState p_208493_0_, LevelAccessor p_208493_1_, BlockPos p_208493_2_) {
		int i = 7;
		BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();

		for (Direction direction : Direction.values()) {
			blockpos$mutable.setWithOffset(p_208493_2_, direction);
			i = Math.min(i, getDistanceAt(p_208493_1_.getBlockState(blockpos$mutable)) + 1);
			if (i == 1) {
				break;
			}
		}

		return p_208493_0_.setValue(DISTANCE, Integer.valueOf(i));
	}

	private static int getDistanceAt(BlockState state) {
		if (state.is(ModBlocks.WAFER_STICK_BLOCK.get())) {
			return 0;
		} else {
			return state.getBlock() instanceof ChocolateLeavesBlock ? state.getValue(DISTANCE) : 7;
		}
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext p_196258_1_) {
		return updateDistance(this.defaultBlockState().setValue(PERSISTENT, Boolean.valueOf(true)), p_196258_1_.getLevel(), p_196258_1_.getClickedPos());
	}

//    @Nonnull
//    @Override
//    public Item getItemDropped(BlockState state, Random rand, int fortune) {
//        return Item.getItemFromBlock(ModBlocks.CHOCOLATE_SAPLING);
//    }

//    @Override
//    public void dropApple(World worldIn, BlockPos pos, BlockState state, int chance) {
//        if (worldIn.rand.nextInt(chance / 10) == 0) {
//            spawnAsEntity(worldIn, pos, new ItemStack(ModItems.CHOCOLATE_BAR, 1, state.getValue(CHOCOLATE_TYPE).getMetadata()));
//        }
//    }

//    @Nonnull
//    @Override
//    @ParametersAreNonnullByDefault
//    protected ItemStack getSilkTouchDrop(BlockState state) {
//        return new ItemStack(Item.getItemFromBlock(this), 1, state.getValue(CHOCOLATE_TYPE).getMetadata());
//    }
}