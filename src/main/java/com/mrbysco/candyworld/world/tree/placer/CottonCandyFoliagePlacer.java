package com.mrbysco.candyworld.world.tree.placer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mrbysco.candyworld.registry.ModBlocks;
import com.mrbysco.candyworld.world.ModFoliagePlacer;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
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

public class CottonCandyFoliagePlacer extends FoliagePlacer {
	public static final Codec<CottonCandyFoliagePlacer> CODEC = RecordCodecBuilder.create((p_242836_0_) -> {
		return foliagePlacerParts(p_242836_0_).and(FeatureSpread.codec(0, 16, 8).fieldOf("trunk_height").forGetter((p_242835_0_) -> {
			return p_242835_0_.trunkHeight;
		})).apply(p_242836_0_, CottonCandyFoliagePlacer::new);
	});
	private final FeatureSpread trunkHeight;

	public CottonCandyFoliagePlacer(FeatureSpread radius, FeatureSpread offset, FeatureSpread height) {
		super(radius, offset);
		this.trunkHeight = height;
	}

	@Override
	protected FoliagePlacerType<?> type() {
		return ModFoliagePlacer.COTTON_CANDY_FOLIAGE_PLACER.get();
	}

	@Override
	protected void createFoliage(IWorldGenerationReader reader, Random random, BaseTreeFeatureConfig featureConfig, int p_230372_4_, Foliage foliage, int p_230372_6_, int p_230372_7_, Set<BlockPos> blockPosSet, int p_230372_9_, MutableBoundingBox mutableBoundingBox) {
		BlockPos blockpos = foliage.foliagePos();
		BlockState leafState = featureConfig.leavesProvider.getState(random, blockpos);
		BlockState trunkState = featureConfig.trunkProvider.getState(random, blockpos);

		int currentY = -2;

		placeLayer1(reader, blockpos.above(currentY++), leafState, blockPosSet, mutableBoundingBox);
		placeLayer2(reader, blockpos.above(currentY++), leafState, blockPosSet, mutableBoundingBox);
		placeLayer3(reader, blockpos.above(currentY++), leafState, blockPosSet, mutableBoundingBox);
		placeLayer4(reader, blockpos.above(currentY++), leafState, blockPosSet, mutableBoundingBox);
		placeLayer4(reader, blockpos.above(currentY++), leafState, blockPosSet, mutableBoundingBox);
		placeLayer3(reader, blockpos.above(currentY++), leafState, blockPosSet, mutableBoundingBox);
		placeLayer2(reader, blockpos.above(currentY++), leafState, blockPosSet, mutableBoundingBox);
		placeLayer1(reader, blockpos.above(currentY), leafState, blockPosSet, mutableBoundingBox);

		for (int j2 = 0; j2 < 5; ++j2) {
			BlockPos abovePos = blockpos.above(j2);

			setLogBlock(reader, abovePos, 0, 0, 0, trunkState, blockPosSet, mutableBoundingBox);
		}
	}

	private void placeLayer1(IWorldGenerationReader reader, BlockPos pos, BlockState leafState, Set<BlockPos> blockPosSet, MutableBoundingBox mutableBoundingBox) {
		for (int x = -1; x <= 1; x++) {
			for (int z = -1; z <= 1; z++) {
				setLeafBlock(reader, pos, x, 0, z, leafState, blockPosSet, mutableBoundingBox);
			}
		}
	}

	private void placeLayer2(IWorldGenerationReader reader, BlockPos pos, BlockState leafState, Set<BlockPos> blockPosSet, MutableBoundingBox mutableBoundingBox) {
		placeLayerSquare(reader, pos, leafState, blockPosSet, mutableBoundingBox);
		setAir(reader, pos, 2, 0, 2, blockPosSet, mutableBoundingBox);
		setAir(reader, pos, 2, 0, -2, blockPosSet, mutableBoundingBox);
		setAir(reader, pos, -2, 0, 2, blockPosSet, mutableBoundingBox);
		setAir(reader, pos, -2, 0, -2, blockPosSet, mutableBoundingBox);
	}

	private void placeLayer3(IWorldGenerationReader reader, BlockPos pos, BlockState leafState, Set<BlockPos> blockPosSet, MutableBoundingBox mutableBoundingBox) {
		placeLayerSquare(reader, pos, leafState, blockPosSet, mutableBoundingBox);
		setLeafBlock(reader, pos, 3, 0, 0, leafState, blockPosSet, mutableBoundingBox);
		setLeafBlock(reader, pos, -3, 0, 0, leafState, blockPosSet, mutableBoundingBox);
		setLeafBlock(reader, pos, 0, 0, -3, leafState, blockPosSet, mutableBoundingBox);
		setLeafBlock(reader, pos, 0, 0, 3, leafState, blockPosSet, mutableBoundingBox);
	}

	private void placeLayer4(IWorldGenerationReader reader, BlockPos pos, BlockState leafState, Set<BlockPos> blockPosSet, MutableBoundingBox mutableBoundingBox) {
		placeLayerSquare(reader, pos, leafState, blockPosSet, mutableBoundingBox);
		for (int i = -1; i <= 1; i++) {
			setLeafBlock(reader, pos, i, 0, 3, leafState, blockPosSet, mutableBoundingBox);
			setLeafBlock(reader, pos, i, 0, -3, leafState, blockPosSet, mutableBoundingBox);
			setLeafBlock(reader, pos, 3, 0, i, leafState, blockPosSet, mutableBoundingBox);
			setLeafBlock(reader, pos, -3, 0, i, leafState, blockPosSet, mutableBoundingBox);
		}
	}

	private void placeLayerSquare(IWorldGenerationReader reader, BlockPos pos, BlockState leafState, Set<BlockPos> blockPosSet, MutableBoundingBox mutableBoundingBox) {
		for (int x = -2; x <= 2; x++) {
			for (int z = -2; z <= 2; z++) {
				this.setLeafBlock(reader, pos, x, 0, z, leafState, blockPosSet, mutableBoundingBox);
			}
		}
	}

	private void setLeafBlock(IWorldGenerationReader reader, BlockPos pos, int xOffset, int yOffset, int zOffset, BlockState leafState, Set<BlockPos> blockPosSet, MutableBoundingBox mutableBoundingBox) {
		BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
		blockpos$mutable.setWithOffset(pos, xOffset, yOffset, zOffset);
		if (isAirOrLeaves(reader, blockpos$mutable)) {
			reader.setBlock(blockpos$mutable, leafState, 19);
			mutableBoundingBox.expand(new MutableBoundingBox(blockpos$mutable, blockpos$mutable));
			blockPosSet.add(blockpos$mutable.immutable());
		}
	}

	private void setLogBlock(IWorldGenerationReader reader, BlockPos pos, int xOffset, int yOffset, int zOffset, BlockState trunkState, Set<BlockPos> blockPosSet, MutableBoundingBox mutableBoundingBox) {
		BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
		blockpos$mutable.setWithOffset(pos, xOffset, yOffset, zOffset);
		if (isAirOrLeaves(reader, blockpos$mutable)) {
			reader.setBlock(blockpos$mutable, trunkState, 19);
			mutableBoundingBox.expand(new MutableBoundingBox(blockpos$mutable, blockpos$mutable));
			blockPosSet.add(blockpos$mutable.immutable());
		}
	}

	private void setAir(IWorldGenerationReader reader, BlockPos pos, int xOffset, int yOffset, int zOffset, Set<BlockPos> blockPosSet, MutableBoundingBox mutableBoundingBox) {
		BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
		blockpos$mutable.setWithOffset(pos, xOffset, yOffset, zOffset);
		if (isAirOrLeaves(reader, blockpos$mutable)) {
			reader.setBlock(blockpos$mutable, Blocks.AIR.defaultBlockState(), 19);
			mutableBoundingBox.expand(new MutableBoundingBox(blockpos$mutable, blockpos$mutable));
			blockPosSet.add(blockpos$mutable.immutable());
		}
	}

	public boolean isAirOrLeaves(IWorldGenerationBaseReader reader, BlockPos pos) {
		return reader.isStateAtPosition(pos, (state) -> {
			return state.isAir() || state.is(ModBlocks.COTTON_CANDY_LEAVES.get());
		});
	}

	@Override
	public int foliageHeight(Random random, int treeHeight, BaseTreeFeatureConfig featureConfig) {
		return Math.max(4, treeHeight - this.trunkHeight.sample(random));
	}

	@Override
	protected boolean shouldSkipLocation(Random random, int p_230373_2_, int p_230373_3_, int p_230373_4_, int p_230373_5_, boolean p_230373_6_) {
		return p_230373_2_ == p_230373_5_ && p_230373_4_ == p_230373_5_ && (random.nextInt(2) == 0 || p_230373_3_ == 0);
	}
}
