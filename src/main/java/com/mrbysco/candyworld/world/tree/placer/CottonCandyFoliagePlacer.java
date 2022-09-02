package com.mrbysco.candyworld.world.tree.placer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mrbysco.candyworld.registry.ModBlocks;
import com.mrbysco.candyworld.world.ModFoliagePlacer;
import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import java.util.Random;
import java.util.function.BiConsumer;

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
	protected void createFoliage(LevelSimulatedReader reader, BiConsumer<BlockPos, BlockState> biConsumer, Random random, TreeConfiguration featureConfig, int p_161426_, FoliageAttachment foliage, int p_161428_, int p_161429_, int p_161430_) {
		BlockPos blockpos = foliage.pos();
		BlockState leafState = featureConfig.foliageProvider.getState(random, blockpos);
		BlockState trunkState = featureConfig.trunkProvider.getState(random, blockpos);

		int currentY = -2;

		placeLayer1(reader, blockpos.above(currentY++), leafState, biConsumer);
		placeLayer2(reader, blockpos.above(currentY++), leafState, biConsumer);
		placeLayer3(reader, blockpos.above(currentY++), leafState, biConsumer);
		placeLayer4(reader, blockpos.above(currentY++), leafState, biConsumer);
		placeLayer4(reader, blockpos.above(currentY++), leafState, biConsumer);
		placeLayer3(reader, blockpos.above(currentY++), leafState, biConsumer);
		placeLayer2(reader, blockpos.above(currentY++), leafState, biConsumer);
		placeLayer1(reader, blockpos.above(currentY), leafState, biConsumer);

		for (int j2 = 0; j2 < 5; ++j2) {
			BlockPos abovePos = blockpos.above(j2);

			setLogBlock(reader, abovePos, 0, 0, 0, trunkState, biConsumer);
		}
	}

	private void placeLayer1(LevelSimulatedReader reader, BlockPos pos, BlockState leafState, BiConsumer<BlockPos, BlockState> biConsumer) {
		for (int x = -1; x <= 1; x++) {
			for (int z = -1; z <= 1; z++) {
				setLeafBlock(reader, pos, x, 0, z, leafState, biConsumer);
			}
		}
	}

	private void placeLayer2(LevelSimulatedReader reader, BlockPos pos, BlockState leafState, BiConsumer<BlockPos, BlockState> biConsumer) {
		placeLayerSquare(reader, pos, leafState, biConsumer);
		setAir(reader, pos, 2, 0, 2, biConsumer);
		setAir(reader, pos, 2, 0, -2, biConsumer);
		setAir(reader, pos, -2, 0, 2, biConsumer);
		setAir(reader, pos, -2, 0, -2, biConsumer);
	}

	private void placeLayer3(LevelSimulatedReader reader, BlockPos pos, BlockState leafState, BiConsumer<BlockPos, BlockState> biConsumer) {
		placeLayerSquare(reader, pos, leafState, biConsumer);
		setLeafBlock(reader, pos, 3, 0, 0, leafState, biConsumer);
		setLeafBlock(reader, pos, -3, 0, 0, leafState, biConsumer);
		setLeafBlock(reader, pos, 0, 0, -3, leafState, biConsumer);
		setLeafBlock(reader, pos, 0, 0, 3, leafState, biConsumer);
	}

	private void placeLayer4(LevelSimulatedReader reader, BlockPos pos, BlockState leafState, BiConsumer<BlockPos, BlockState> biConsumer) {
		placeLayerSquare(reader, pos, leafState, biConsumer);
		for (int i = -1; i <= 1; i++) {
			setLeafBlock(reader, pos, i, 0, 3, leafState, biConsumer);
			setLeafBlock(reader, pos, i, 0, -3, leafState, biConsumer);
			setLeafBlock(reader, pos, 3, 0, i, leafState, biConsumer);
			setLeafBlock(reader, pos, -3, 0, i, leafState, biConsumer);
		}
	}

	private void placeLayerSquare(LevelSimulatedReader reader, BlockPos pos, BlockState leafState, BiConsumer<BlockPos, BlockState> biConsumer) {
		for (int x = -2; x <= 2; x++) {
			for (int z = -2; z <= 2; z++) {
				this.setLeafBlock(reader, pos, x, 0, z, leafState, biConsumer);
			}
		}
	}

	private void setLeafBlock(LevelSimulatedReader reader, BlockPos pos, int xOffset, int yOffset, int zOffset, BlockState leafState, BiConsumer<BlockPos, BlockState> biConsumer) {
		BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();
		blockpos$mutable.setWithOffset(pos, xOffset, yOffset, zOffset);
		if (isAirOrLeaves(reader, blockpos$mutable)) {
			biConsumer.accept(blockpos$mutable, leafState);
		}
	}

	private void setLogBlock(LevelSimulatedReader reader, BlockPos pos, int xOffset, int yOffset, int zOffset, BlockState trunkState, BiConsumer<BlockPos, BlockState> biConsumer) {
		BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();
		blockpos$mutable.setWithOffset(pos, xOffset, yOffset, zOffset);
		if (isAirOrLeaves(reader, blockpos$mutable)) {
			biConsumer.accept(blockpos$mutable, trunkState);
		}
	}

	private void setAir(LevelSimulatedReader reader, BlockPos pos, int xOffset, int yOffset, int zOffset, BiConsumer<BlockPos, BlockState> biConsumer) {
		BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();
		blockpos$mutable.setWithOffset(pos, xOffset, yOffset, zOffset);
		if (isAirOrLeaves(reader, blockpos$mutable)) {
			biConsumer.accept(blockpos$mutable, Blocks.AIR.defaultBlockState());
		}
	}

	public boolean isAirOrLeaves(LevelSimulatedReader reader, BlockPos pos) {
		return reader.isStateAtPosition(pos, (state) -> {
			return state.isAir() || state.is(ModBlocks.COTTON_CANDY_LEAVES.get());
		});
	}

	@Override
	public int foliageHeight(Random random, int treeHeight, TreeConfiguration featureConfig) {
		return Math.max(4, treeHeight - this.trunkHeight.sample(random));
	}

	@Override
	protected boolean shouldSkipLocation(Random random, int p_230373_2_, int p_230373_3_, int p_230373_4_, int p_230373_5_, boolean p_230373_6_) {
		return p_230373_2_ == p_230373_5_ && p_230373_4_ == p_230373_5_ && (random.nextInt(2) == 0 || p_230373_3_ == 0);
	}
}
