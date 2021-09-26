package com.mrbysco.candyworld.block.chocolate;

import com.mrbysco.candyworld.registry.ModBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.IForgeShearable;
import net.minecraftforge.common.IPlantable;

import javax.annotation.Nonnull;

public class ChocolateMushroomBlock extends BushBlock implements IForgeShearable, IPlantable {
    protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);

    public ChocolateMushroomBlock(Properties properties) {
        super(properties);
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.is(ModBlocks.CANDY_GRASS_BLOCK.get()) || state.is(ModBlocks.MILK_BROWNIE_BLOCK.get()) ||
                state.is(ModBlocks.CHOCOLATE_COVERED_WHITE_BROWNIE.get()) || state.is(ModBlocks.WHITE_BROWNIE_BLOCK.get()) ||
                state.is(ModBlocks.DARK_CANDY_GRASS.get()) || state.is(ModBlocks.DARK_BROWNIE_BLOCK.get()) ||
                super.mayPlaceOn(state, worldIn, pos);
    }

    @Override
    public boolean canBeReplaced(BlockState state, Fluid fluid) {
        return true;
    }

//    @Nonnull
//    @Override
//    public Item getItemDropped(BlockState state, Random rand, int fortune) {
//        return ModItems.CHOCOLATE_BAR;
//    }


//    @Override
//    public int quantityDropped(Random random) {
//        return random.nextInt(15) == 0 ? 1 : 0;
//    }

    @Nonnull
    @Override
    public AbstractBlock.OffsetType getOffsetType() {
        return AbstractBlock.OffsetType.XYZ;
    }
}
