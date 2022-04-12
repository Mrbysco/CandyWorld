package com.mrbysco.candyworld.block.chocolate;

import com.mrbysco.candyworld.registry.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class ChocolateLeavesBlock extends LeavesBlock {

	public ChocolateLeavesBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(DISTANCE, Integer.valueOf(7)).setValue(PERSISTENT, Boolean.valueOf(false)));
	}

	@Override
	public void tick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		world.setBlock(pos, ChocolateLeavesBlock.updateDistance(state, world, pos), 3);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState state2, IWorld world, BlockPos pos, BlockPos pos2) {
		int i = getDistanceAt(state2) + 1;
		if (i != 1 || state.getValue(DISTANCE) != i) {
			world.getBlockTicks().scheduleTick(pos, this, 1);
		}

		return state;
	}

	private static BlockState updateDistance(BlockState p_208493_0_, IWorld p_208493_1_, BlockPos p_208493_2_) {
		int i = 7;
		BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

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
	public BlockState getStateForPlacement(BlockItemUseContext p_196258_1_) {
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