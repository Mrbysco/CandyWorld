package com.mrbysco.candyworld.block.fluid;

import com.mrbysco.candyworld.registry.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.tags.FluidTags;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import java.util.function.Supplier;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class LiquidChocolateBlock extends LiquidBlock {

    public LiquidChocolateBlock(Supplier<? extends FlowingFluid> supplier, Properties properties) {
        super(supplier, properties);
    }

    @Override
    public void neighborChanged(BlockState state, Level worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        this.checkForMixing(worldIn, pos, state);
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving);
    }

    protected void checkForMixing(Level worldIn, BlockPos pos, BlockState state) {
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

    protected void triggerMixEffects(Level worldIn, BlockPos pos) {
        double d0 = (double)pos.getX();
        double d1 = (double)pos.getY();
        double d2 = (double)pos.getZ();
        worldIn.playSound(null, pos, SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS, 0.5F, 2.6F + (worldIn.random.nextFloat() - worldIn.random.nextFloat()) * 0.8F);

        for (int i = 0; i < 8; ++i) {
            worldIn.addParticle(ParticleTypes.LARGE_SMOKE, d0 + Math.random(), d1 + 1.2D, d2 + Math.random(), 0.0D, 0.0D, 0.0D);
        }
    }
}
