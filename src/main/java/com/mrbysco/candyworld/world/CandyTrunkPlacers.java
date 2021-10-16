package com.mrbysco.candyworld.world;

import com.mojang.serialization.Codec;
import com.mrbysco.candyworld.world.tree.trunkplacers.CandyStraightTrunkPlacer;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

public class CandyTrunkPlacers {
	public static final TrunkPlacerType<CandyStraightTrunkPlacer> CANDY_STRAIGHT_TRUNK_PLACER = register("candy_straight_trunk_placer", CandyStraightTrunkPlacer.CODEC);

	private static <P extends TrunkPlacer> TrunkPlacerType<P> register(String p_70327_, Codec<P> p_70328_) {
		return Registry.register(Registry.TRUNK_PLACER_TYPES, p_70327_, new TrunkPlacerType<>(p_70328_));
	}
}
