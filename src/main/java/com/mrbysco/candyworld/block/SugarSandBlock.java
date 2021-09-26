package com.mrbysco.candyworld.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class SugarSandBlock extends FallingBlock {

    public SugarSandBlock(Properties properties) {
        super(properties);
    }

    @Override
    public int getDustColor(BlockState state, IBlockReader reader, BlockPos pos) {
        return ~0xa6b6bf;
    }
}
