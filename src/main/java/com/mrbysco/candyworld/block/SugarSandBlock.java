package com.mrbysco.candyworld.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class SugarSandBlock extends FallingBlock {

    public SugarSandBlock(Properties properties) {
        super(properties);
    }

    @Override
    public int getDustColor(BlockState state, BlockGetter reader, BlockPos pos) {
        return ~0xa6b6bf;
    }
}
