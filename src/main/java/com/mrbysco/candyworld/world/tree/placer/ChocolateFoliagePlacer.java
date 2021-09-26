package com.mrbysco.candyworld.world.tree.placer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mrbysco.candyworld.block.chocolate.ChocolateLeavesBlock;
import com.mrbysco.candyworld.world.ModFoliagePlacer;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;

import java.util.Random;
import java.util.Set;

public class ChocolateFoliagePlacer extends FoliagePlacer {
	public static final Codec<ChocolateFoliagePlacer> CODEC = RecordCodecBuilder.create((p_242836_0_) -> {
		return foliagePlacerParts(p_242836_0_).and(FeatureSpread.codec(0, 16, 8).fieldOf("trunk_height").forGetter((p_242835_0_) -> {
			return p_242835_0_.trunkHeight;
		})).apply(p_242836_0_, ChocolateFoliagePlacer::new);
	});
	private final FeatureSpread trunkHeight;

	public ChocolateFoliagePlacer(FeatureSpread radius, FeatureSpread offset, FeatureSpread height) {
		super(radius, offset);
		this.trunkHeight = height;
	}

	@Override
	protected FoliagePlacerType<?> type() {
		return ModFoliagePlacer.CHOCOLATE_FOLIAGE_PLACER.get();
	}

	@Override
	protected void createFoliage(IWorldGenerationReader reader, Random random, BaseTreeFeatureConfig featureConfig, int p_230372_4_, Foliage foliage, int p_230372_6_, int p_230372_7_, Set<BlockPos> blockPosSet, int p_230372_9_, MutableBoundingBox mutableBoundingBox) {
		BlockPos blockpos = foliage.foliagePos();
		BlockState leafState = featureConfig.leavesProvider.getState(random, blockpos);
		BlockState trunkState = featureConfig.trunkProvider.getState(random, blockpos);

		int height = 3;

		BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
		for (int y = blockpos.getY() - 3 + height; y <= blockpos.getY() + height; ++y) {
			int yPlusHeight = y - (blockpos.getY() + height);
			int l2 = 1 - yPlusHeight / 2;
			for (int x = blockpos.getX() - l2; x <= blockpos.getX() + l2; ++x) {
				int j1 = x - blockpos.getX();
				for (int k1 = blockpos.getZ() - l2; k1 <= blockpos.getZ() + l2; ++k1) {
					int l1 = k1 - blockpos.getZ();
					if(shouldSkipLocation(random, j1, l2, l1, yPlusHeight, false)) {
						blockpos$mutable.set(x, y, k1);
						if (isAirOrLeaves(reader, blockpos$mutable)) {
							reader.setBlock(blockpos$mutable, leafState, 19);
							mutableBoundingBox.expand(new MutableBoundingBox(blockpos$mutable, blockpos$mutable));
							blockPosSet.add(blockpos$mutable.immutable());
						}
					}
				}
			}
		}

		for (int j2 = 0; j2 < height; ++j2) {
			BlockPos abovePos = blockpos.above(j2);

			setLogBlock(reader, abovePos, 0, 0, 0, trunkState, blockPosSet, mutableBoundingBox);
		}
	}

	private void setLogBlock(IWorldGenerationReader reader, BlockPos pos, int xOffset, int yOffset, int zOffset, BlockState leafState, Set<BlockPos> blockPosSet, MutableBoundingBox mutableBoundingBox) {
		BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
		blockpos$mutable.setWithOffset(pos, xOffset, yOffset, zOffset);
		if (isAirOrLeaves(reader, blockpos$mutable)) {
			reader.setBlock(blockpos$mutable, leafState, 19);
			mutableBoundingBox.expand(new MutableBoundingBox(blockpos$mutable, blockpos$mutable));
			blockPosSet.add(blockpos$mutable.immutable());
		}
	}

	public boolean isAirOrLeaves(IWorldGenerationBaseReader reader, BlockPos pos) {
		return reader.isStateAtPosition(pos, (state) -> {
			return state.isAir() || state.getBlock() instanceof ChocolateLeavesBlock;
		});
	}

	@Override
	public int foliageHeight(Random random, int treeHeight, BaseTreeFeatureConfig featureConfig) {
		return Math.max(4, treeHeight - this.trunkHeight.sample(random));
	}

	@Override
	protected boolean shouldSkipLocation(Random random, int j1, int l2, int l1, int yPlusHeight, boolean p_230373_6_) {
		return Math.abs(j1) != l2 || Math.abs(l1) != l2 || random.nextInt(2) != 0 && yPlusHeight != 0;
	}
}
