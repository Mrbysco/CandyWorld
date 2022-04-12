package com.mrbysco.candyworld.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import java.util.stream.Stream;

public class WaferStickBlock extends StackableBlock {

	private final VoxelShape SHAPE = Stream.of(
			Block.box(3, 0, 13, 13, 16, 16),
			Block.box(0, 0, 0, 3, 16, 16),
			Block.box(13, 0, 0, 16, 16, 16),
			Block.box(3, 0, 0, 13, 16, 3)
	).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();

	public WaferStickBlock(Properties properties) {
		super(properties, true, true);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}
}
