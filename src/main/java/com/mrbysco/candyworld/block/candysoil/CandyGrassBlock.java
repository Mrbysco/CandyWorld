package com.mrbysco.candyworld.block.candysoil;

import com.mrbysco.candyworld.registry.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IGrowable;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.IPlantable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

public class CandyGrassBlock extends Block implements IGrowable {

    public CandyGrassBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if (!worldIn.isClientSide) {
            if (!worldIn.isAreaLoaded(pos, 3)) {
                return;
            }

            // grass block is converted to brownie
            if (worldIn.getLightEmission(pos.above()) < 4 && worldIn.getBlockState(pos.above()).getLightValue(worldIn, pos.above()) > 2) {
                if(state.is(ModBlocks.CANDY_GRASS_BLOCK.get())) {
                    worldIn.setBlockAndUpdate(pos, ModBlocks.MILK_BROWNIE_BLOCK.get().defaultBlockState());
                } else if(state.is(ModBlocks.CHOCOLATE_COVERED_WHITE_BROWNIE.get())) {
                    worldIn.setBlockAndUpdate(pos, ModBlocks.WHITE_BROWNIE_BLOCK.get().defaultBlockState());
                } else {
                    worldIn.setBlockAndUpdate(pos, ModBlocks.DARK_BROWNIE_BLOCK.get().defaultBlockState());
                }
            } else {
                if (worldIn.getLightEmission(pos.above()) >= 9) {
                    // grass tries to spread 3 times
                    for (int i = 0; i < 3; i++) {
                        final BlockPos blockpos = pos.offset(RANDOM.nextInt(3) - 1, RANDOM.nextInt(5) - 3, RANDOM.nextInt(3) - 1);

                        // block not loaded
                        if (blockpos.getY() >= 0 && blockpos.getY() < 256 && !worldIn.hasChunkAt(blockpos)) {
                            return;
                        }

                        final BlockState iblockstate = worldIn.getBlockState(blockpos.above());
                        final BlockState iblockstate1 = worldIn.getBlockState(blockpos);

                        // block is valid

                        if(worldIn.getLightEmission(blockpos.above()) >= 4
                                && iblockstate.getLightValue(worldIn, pos.above()) <= 2) {
                            if(iblockstate1.is(ModBlocks.MILK_BROWNIE_BLOCK.get())) {
                                worldIn.setBlockAndUpdate(blockpos, ModBlocks.CANDY_GRASS_BLOCK.get().defaultBlockState());
                            } else if(iblockstate1.is(ModBlocks.WHITE_BROWNIE_BLOCK.get())) {
                                worldIn.setBlockAndUpdate(blockpos, ModBlocks.CHOCOLATE_COVERED_WHITE_BROWNIE.get().defaultBlockState());
                            } else {
                                worldIn.setBlockAndUpdate(blockpos, ModBlocks.DARK_CANDY_GRASS.get().defaultBlockState());
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean isValidBonemealTarget(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return state.is(ModBlocks.DARK_CANDY_GRASS.get()) && state.canSustainPlant(worldIn, pos, Direction.UP, (IPlantable) ModBlocks.COTTON_CANDY_PLANT.get());
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean isBonemealSuccess(World world, Random rand, BlockPos pos, BlockState state) {
        return state.is(ModBlocks.CANDY_GRASS_BLOCK.get());
    }

    @Override
    public void performBonemeal(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        worldIn.setBlockAndUpdate(pos.above(), ModBlocks.COTTON_CANDY_PLANT.get().defaultBlockState());
    }
}
