package com.mrbysco.candyworld.block.fluid;

import com.mrbysco.candyworld.registry.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.function.Supplier;

public class LiquidChocolateBlock extends FlowingFluidBlock {

    public LiquidChocolateBlock(Supplier<? extends FlowingFluid> supplier, Properties properties) {
        super(supplier, properties);
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        this.checkForMixing(worldIn, pos, state);
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving);
    }

    protected void checkForMixing(World worldIn, BlockPos pos, BlockState state) {
        if (state.getValue(LEVEL) != 0) {
            return;
        }
        boolean flag = false;
        for (Direction direction : Direction.values()) {
            if (direction != Direction.DOWN) {
                BlockPos blockpos = pos.relative(direction);
                if (worldIn.getFluidState(blockpos).is(FluidTags.WATER)) {
                    flag = true;
                    break;
                }
            }
        }
        if (flag) {
            worldIn.setBlockAndUpdate(pos, ModBlocks.MILK_CHOCOLATE_BLOCK.get().defaultBlockState());
            this.triggerMixEffects(worldIn, pos);
        }
    }

    protected void triggerMixEffects(World worldIn, BlockPos pos) {
        double d0 = (double)pos.getX();
        double d1 = (double)pos.getY();
        double d2 = (double)pos.getZ();
        worldIn.playSound(null, pos, SoundEvents.LAVA_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (worldIn.random.nextFloat() - worldIn.random.nextFloat()) * 0.8F);

        for (int i = 0; i < 8; ++i) {
            worldIn.addParticle(ParticleTypes.LARGE_SMOKE, d0 + Math.random(), d1 + 1.2D, d2 + Math.random(), 0.0D, 0.0D, 0.0D);
        }
    }
}
