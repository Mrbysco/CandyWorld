package com.mrbysco.candyworld.block.ore;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorldReader;

public class TeleporterOreBlock extends Block {

	public TeleporterOreBlock(Properties properties) {
		super(properties);
	}

//    @Override
//    @Nonnull
//    public Item getItemDropped(BlockState state, Random rand, int fortune) {
//        return ModItems.TELEPORTER;
//    }
//
//    @Override
//    @SuppressWarnings("deprecation")
//    public boolean canSilkHarvest() {
//        return true;
//    }
//
//    @Override
//    @ParametersAreNonnullByDefault
//    public int quantityDroppedWithBonus(int fortune, Random random) {
//        if (fortune > 0) {
//            int i = random.nextInt(fortune + 2) - 1;
//
//            if (i < 0) {
//                i = 0;
//            }
//            return this.quantityDropped(random) * (i + 1);
//        }
//        return this.quantityDropped(random);
//    }

	@Override
	public int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune, int silktouch) {
		return MathHelper.nextInt(RANDOM, 3, 7);
	}
}
