package com.mrbysco.candyworld.world;

import com.mrbysco.candyworld.CandyWorld;
import com.mrbysco.candyworld.world.tree.trunkplacers.CandyStraightTrunkPlacer;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CandyTrunkPlacers {
	public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS = DeferredRegister.create(Registry.TRUNK_PLACER_TYPE_REGISTRY, CandyWorld.MOD_ID);

	public static final RegistryObject<TrunkPlacerType<CandyStraightTrunkPlacer>> CANDY_STRAIGHT_TRUNK_PLACER =
			TRUNK_PLACERS.register("candy_straight_trunk_placer", () -> new TrunkPlacerType<>(CandyStraightTrunkPlacer.CODEC));
}
