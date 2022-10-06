package com.mrbysco.candyworld.world.tree.trunkplacers;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mrbysco.candyworld.registry.ModTags;
import com.mrbysco.candyworld.world.CandyTrunkPlacers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer.FoliageAttachment;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

public class CandyStraightTrunkPlacer extends TrunkPlacer {
	public static final Codec<CandyStraightTrunkPlacer> CODEC = RecordCodecBuilder.create((instance) -> {
		return trunkPlacerParts(instance).apply(instance, CandyStraightTrunkPlacer::new);
	});

	public CandyStraightTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
		super(baseHeight, heightRandA, heightRandB);
	}


	protected TrunkPlacerType<?> type() {
		return CandyTrunkPlacers.CANDY_STRAIGHT_TRUNK_PLACER.get();
	}

	public List<FoliageAttachment> placeTrunk(LevelSimulatedReader reader, BiConsumer<BlockPos, BlockState> p_161860_, Random random, int height, BlockPos pos, TreeConfiguration treeConfiguration) {
		setSoilAt(reader, p_161860_, random, pos.below(), treeConfiguration);

		for (int i = 0; i < height; ++i) {
			placeLog(reader, p_161860_, random, pos.above(i), treeConfiguration);
		}

		return ImmutableList.of(new FoliagePlacer.FoliageAttachment(pos.above(height), 0, false));
	}

	protected static void setSoilAt(LevelSimulatedReader reader, BiConsumer<BlockPos, BlockState> p_161882_, Random random, BlockPos pos, TreeConfiguration treeConfiguration) {
		if (treeConfiguration.forceDirt || !isSoil(reader, pos)) {
			p_161882_.accept(pos, treeConfiguration.dirtProvider.getState(random, pos));
		}

	}

	private static boolean isSoil(LevelSimulatedReader reader, BlockPos position) {
		return reader.isStateAtPosition(position, (state) -> {
			Block block = state.getBlock();
			return isSoil(block) || isDirt(reader, position);
		});
	}

	private static boolean isSoil(Block block) {
		return ForgeRegistries.BLOCKS.tags().getTag(ModTags.CANDY_SOIL).contains(block);
	}
}