package com.mrbysco.candyworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class WaferStickBlock extends StackableBlock {

    private final VoxelShape SHAPE = Stream.of(
            Block.box(3, 0, 13, 13, 16, 16),
            Block.box(0, 0, 0, 3, 16, 16),
            Block.box(13, 0, 0, 16, 16, 16),
            Block.box(3, 0, 0, 13, 16, 3)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public WaferStickBlock(Properties properties) {
        super(properties, true, true);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}
