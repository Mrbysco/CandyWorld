package com.mrbysco.candyworld.block;

import com.mrbysco.candyworld.config.CandyConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class StackableBlock extends Block {
    private final boolean checkStackableConfig;
    private final boolean checkRecursiveConfig;

    public StackableBlock(Properties properties, boolean checkStackableConfig, boolean checkRecursiveConfig) {
        super(properties);
        this.checkStackableConfig = checkStackableConfig;
        this.checkRecursiveConfig = checkRecursiveConfig;
    }

    public StackableBlock(Properties properties) {
        this(properties, true, true);
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if (!this.canSurvive(state, worldIn, pos)) {
            worldIn.destroyBlock(pos, true);
        }
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving);
    }

    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (!stateIn.canSurvive(worldIn, currentPos)) {
            return Blocks.AIR.defaultBlockState();
        } else {
            return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        }
    }

    @Override
    public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
        if(checkStackableConfig) {
            if (!CandyConfig.COMMON.stackableTreeTrunks.get()) {
                return super.canSurvive(state, worldIn, pos);
            }
            return worldIn.getBlockState(pos.below()).isFaceSturdy(worldIn, pos.below(), Direction.UP) || worldIn.getBlockState(pos.below()).getBlock() instanceof StackableBlock;
        } else {
            return super.canSurvive(state, worldIn, pos);
        }
    }

    @Override
    public float getDestroyProgress(BlockState state, PlayerEntity player, IBlockReader worldIn, BlockPos pos) {
        float hardness = super.getDestroyProgress(state, player, worldIn, pos);
        if(checkRecursiveConfig) {
            if (!CandyConfig.COMMON.recursiveTreeTrunks.get()) {
                return hardness;
            }
            if (worldIn.getBlockState(pos.above()).getBlock().getClass().isInstance(this)) {
                return hardness + hardness;
            }
        }
        return hardness;
    }

//    @Override
//    @ParametersAreNonnullByDefault
//    public void breakBlock(World worldIn, BlockPos pos, BlockState state) {
//        super.breakBlock(worldIn, pos, state);
//        if (isTrunkBlock) {
//            if (worldIn.isAreaLoaded(pos.add(-5, -5, -5), pos.add(5, 5, 5))) {
//                for (BlockPos blockpos : BlockPos.getAllInBox(pos.add(-4, -4, -4), pos.add(4, 4, 4))) {
//                    BlockState iblockstate = worldIn.getBlockState(blockpos);
//
//                    if (iblockstate.getBlock().isLeaves(iblockstate, worldIn, blockpos)) {
//                        iblockstate.getBlock().beginLeavesDecay(iblockstate, worldIn, blockpos);
//                    }
//                }
//            }
//        }
//    }
}
