package com.mrbysco.candyworld.world.tree.placer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mrbysco.candyworld.block.chocolate.ChocolateLeavesBlock;
import com.mrbysco.candyworld.world.ModFoliagePlacer;
import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import java.util.Random;
import java.util.function.BiConsumer;

public class ChocolateFoliagePlacer extends FoliagePlacer {
	public static final Codec<ChocolateFoliagePlacer> CODEC = RecordCodecBuilder.create((instance) -> {
		return foliagePlacerParts(instance).and(IntProvider.codec(0, 24).fieldOf("trunk_height").forGetter((placer) -> {
			return placer.trunkHeight;
		})).apply(instance, ChocolateFoliagePlacer::new);
	});
	private final IntProvider trunkHeight;

	public ChocolateFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider height) {
		super(radius, offset);
		this.trunkHeight = height;
	}

	@Override
	protected FoliagePlacerType<?> type() {
		return ModFoliagePlacer.CHOCOLATE_FOLIAGE_PLACER.get();
	}

	@Override
	protected void createFoliage(LevelSimulatedReader reader, BiConsumer<BlockPos, BlockState> biConsumer, Random random, TreeConfiguration featureConfig, int p_161426_, FoliageAttachment foliage, int p_161428_, int p_161429_, int p_161430_) {
		BlockPos blockpos = foliage.pos();
		BlockState leafState = featureConfig.foliageProvider.getState(random, blockpos);
		BlockState trunkState = featureConfig.trunkProvider.getState(random, blockpos);

		int height = 3;

		BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();
		for (int y = blockpos.getY() - 3 + height; y <= blockpos.getY() + height; ++y) {
			int yPlusHeight = y - (blockpos.getY() + height);
			int l2 = 1 - yPlusHeight / 2;
			for (int x = blockpos.getX() - l2; x <= blockpos.getX() + l2; ++x) {
				int j1 = x - blockpos.getX();
				for (int k1 = blockpos.getZ() - l2; k1 <= blockpos.getZ() + l2; ++k1) {
					int l1 = k1 - blockpos.getZ();
					if (shouldSkipLocation(random, j1, l2, l1, yPlusHeight, false)) {
						blockpos$mutable.set(x, y, k1);
						if (isAirOrLeaves(reader, blockpos$mutable)) {
							biConsumer.accept(blockpos$mutable, leafState);
						}
					}
				}
			}
		}

		for (int j2 = 0; j2 < height; ++j2) {
			BlockPos abovePos = blockpos.above(j2);

			setLogBlock(reader, abovePos, 0, 0, 0, trunkState, biConsumer);
		}
	}

	private void setLogBlock(LevelSimulatedReader reader, BlockPos pos, int xOffset, int yOffset, int zOffset, BlockState leafState, BiConsumer<BlockPos, BlockState> biConsumer) {
		BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();
		blockpos$mutable.setWithOffset(pos, xOffset, yOffset, zOffset);
		if (isAirOrLeaves(reader, blockpos$mutable)) {
			biConsumer.accept(blockpos$mutable, leafState);
		}
	}

	public boolean isAirOrLeaves(LevelSimulatedReader reader, BlockPos pos) {
		return reader.isStateAtPosition(pos, (state) -> {
			return state.isAir() || state.getBlock() instanceof ChocolateLeavesBlock;
		});
	}

	@Override
	public int foliageHeight(Random random, int treeHeight, TreeConfiguration featureConfig) {
		return Math.max(4, treeHeight - this.trunkHeight.sample(random));
	}

	@Override
	protected boolean shouldSkipLocation(Random random, int j1, int l2, int l1, int yPlusHeight, boolean p_230373_6_) {
		return Math.abs(j1) != l2 || Math.abs(l1) != l2 || random.nextInt(2) != 0 && yPlusHeight != 0;
	}
}
