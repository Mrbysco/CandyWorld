package com.mrbysco.candyworld.world.tree.placer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mrbysco.candyworld.registry.ModBlocks;
import com.mrbysco.candyworld.world.ModFoliagePlacer;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class CottonCandyFoliagePlacer extends FoliagePlacer {
	public static final Codec<CottonCandyFoliagePlacer> CODEC = RecordCodecBuilder.create((instance) -> {
		return foliagePlacerParts(instance).and(IntProvider.codec(0, 24).fieldOf("trunk_height").forGetter((placer) -> {
			return placer.trunkHeight;
		})).apply(instance, CottonCandyFoliagePlacer::new);
	});
	private final IntProvider trunkHeight;

	public CottonCandyFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider height) {
		super(radius, offset);
		this.trunkHeight = height;
	}

	@Override
	protected FoliagePlacerType<?> type() {
		return ModFoliagePlacer.COTTON_CANDY_FOLIAGE_PLACER.get();
	}

	@Override
	protected void createFoliage(LevelSimulatedReader reader, FoliageSetter foliageSetter, RandomSource random, TreeConfiguration featureConfig, int i, FoliageAttachment foliage, int i1, int i2, int i3) {
		BlockPos blockpos = foliage.pos();
		BlockState leafState = featureConfig.foliageProvider.getState(random, blockpos);
		BlockState trunkState = featureConfig.trunkProvider.getState(random, blockpos);

		int currentY = -2;

		placeLayer1(reader, blockpos.above(currentY++), leafState, foliageSetter);
		placeLayer2(reader, blockpos.above(currentY++), leafState, foliageSetter);
		placeLayer3(reader, blockpos.above(currentY++), leafState, foliageSetter);
		placeLayer4(reader, blockpos.above(currentY++), leafState, foliageSetter);
		placeLayer4(reader, blockpos.above(currentY++), leafState, foliageSetter);
		placeLayer3(reader, blockpos.above(currentY++), leafState, foliageSetter);
		placeLayer2(reader, blockpos.above(currentY++), leafState, foliageSetter);
		placeLayer1(reader, blockpos.above(currentY), leafState, foliageSetter);

		for (int j2 = 0; j2 < 5; ++j2) {
			BlockPos abovePos = blockpos.above(j2);

			foliageSetter.set(abovePos, trunkState);
		}
	}

	private void placeLayer1(LevelSimulatedReader reader, BlockPos pos, BlockState leafState, FoliageSetter foliageSetter) {
		for (int x = -1; x <= 1; x++) {
			for (int z = -1; z <= 1; z++) {
				setLeafBlock(reader, pos, x, 0, z, leafState, foliageSetter);
			}
		}
	}

	private void placeLayer2(LevelSimulatedReader reader, BlockPos pos, BlockState leafState, FoliageSetter foliageSetter) {
		placeLayerSquare(reader, pos, leafState, foliageSetter);
		setAir(reader, pos, 2, 0, 2, foliageSetter);
		setAir(reader, pos, 2, 0, -2, foliageSetter);
		setAir(reader, pos, -2, 0, 2, foliageSetter);
		setAir(reader, pos, -2, 0, -2, foliageSetter);
	}

	private void placeLayer3(LevelSimulatedReader reader, BlockPos pos, BlockState leafState, FoliageSetter foliageSetter) {
		placeLayerSquare(reader, pos, leafState, foliageSetter);
		setLeafBlock(reader, pos, 3, 0, 0, leafState, foliageSetter);
		setLeafBlock(reader, pos, -3, 0, 0, leafState, foliageSetter);
		setLeafBlock(reader, pos, 0, 0, -3, leafState, foliageSetter);
		setLeafBlock(reader, pos, 0, 0, 3, leafState, foliageSetter);
	}

	private void placeLayer4(LevelSimulatedReader reader, BlockPos pos, BlockState leafState, FoliageSetter foliageSetter) {
		placeLayerSquare(reader, pos, leafState, foliageSetter);
		for (int i = -1; i <= 1; i++) {
			setLeafBlock(reader, pos, i, 0, 3, leafState, foliageSetter);
			setLeafBlock(reader, pos, i, 0, -3, leafState, foliageSetter);
			setLeafBlock(reader, pos, 3, 0, i, leafState, foliageSetter);
			setLeafBlock(reader, pos, -3, 0, i, leafState, foliageSetter);
		}
	}

	private void placeLayerSquare(LevelSimulatedReader reader, BlockPos pos, BlockState leafState, FoliageSetter foliageSetter) {
		for (int x = -2; x <= 2; x++) {
			for (int z = -2; z <= 2; z++) {
				this.setLeafBlock(reader, pos, x, 0, z, leafState, foliageSetter);
			}
		}
	}

	private void setLeafBlock(LevelSimulatedReader reader, BlockPos pos, int xOffset, int yOffset, int zOffset, BlockState leafState, FoliageSetter foliageSetter) {
		BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();
		blockpos$mutable.setWithOffset(pos, xOffset, yOffset, zOffset);
		if (isAirOrLeaves(reader, blockpos$mutable)) {
			foliageSetter.set(blockpos$mutable, leafState);
		}
	}

	private void setLogBlock(LevelSimulatedReader reader, BlockPos pos, int xOffset, int yOffset, int zOffset, BlockState trunkState, FoliageSetter foliageSetter) {
		BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();
		blockpos$mutable.setWithOffset(pos, xOffset, yOffset, zOffset);
		if (isAirOrLeaves(reader, blockpos$mutable)) {
			foliageSetter.set(blockpos$mutable, trunkState);
		}
	}

	private void setAir(LevelSimulatedReader reader, BlockPos pos, int xOffset, int yOffset, int zOffset, FoliageSetter foliageSetter) {
		BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();
		blockpos$mutable.setWithOffset(pos, xOffset, yOffset, zOffset);
		if (isAirOrLeaves(reader, blockpos$mutable)) {
			foliageSetter.set(blockpos$mutable, Blocks.AIR.defaultBlockState());
		}
	}

	public boolean isAirOrLeaves(LevelSimulatedReader reader, BlockPos pos) {
		return reader.isStateAtPosition(pos, (state) -> {
			return state.isAir() || state.is(ModBlocks.COTTON_CANDY_LEAVES.get());
		});
	}

	@Override
	public int foliageHeight(RandomSource random, int treeHeight, TreeConfiguration featureConfig) {
		return Math.max(4, treeHeight - this.trunkHeight.sample(random));
	}

	@Override
	protected boolean shouldSkipLocation(RandomSource random, int p_230373_2_, int p_230373_3_, int p_230373_4_, int p_230373_5_, boolean p_230373_6_) {
		return p_230373_2_ == p_230373_5_ && p_230373_4_ == p_230373_5_ && (random.nextInt(2) == 0 || p_230373_3_ == 0);
	}
}
