package com.mrbysco.candyworld.world;

import com.mrbysco.candyworld.CandyWorld;
import com.mrbysco.candyworld.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.EnvironmentScanPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.NoiseThresholdCountPlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.levelgen.placement.SurfaceRelativeThresholdFilter;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

public class ModPlacedFeatures {
	public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, CandyWorld.MOD_ID);

	//Candy world
	public static final RegistryObject<PlacedFeature> ORE_MILK_BROWNIE = register("ore_milk_brownie",
			ModConfiguredFeatures.ORE_MILK_BROWNIE.getHolder().orElseThrow(), CountPlacement.of(16),
			HeightRangePlacement.uniform(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(64)),
			InSquarePlacement.spread(), BiomeFilter.biome());

	public static final RegistryObject<PlacedFeature> ORE_WHITE_BROWNIE = register("ore_white_brownie",
			ModConfiguredFeatures.ORE_WHITE_BROWNIE.getHolder().orElseThrow(), CountPlacement.of(16),
			HeightRangePlacement.uniform(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(64)),
			InSquarePlacement.spread(), BiomeFilter.biome());

	public static final RegistryObject<PlacedFeature> ORE_DARK_BROWNIE = register("ore_dark_brownie",
			ModConfiguredFeatures.ORE_DARK_BROWNIE.getHolder().orElseThrow(), CountPlacement.of(16),
			HeightRangePlacement.uniform(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(64)),
			InSquarePlacement.spread(), BiomeFilter.biome());

	public static final RegistryObject<PlacedFeature> ORE_SUGAR_COOKIE = register("ore_sugar_cookie",
			ModConfiguredFeatures.ORE_SUGAR_COOKIE.getHolder().orElseThrow(), CountPlacement.of(80),
			HeightRangePlacement.uniform(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(64)),
			InSquarePlacement.spread(), BiomeFilter.biome());

	public static final RegistryObject<PlacedFeature> ORE_TELEPORT = register("ore_teleport",
			ModConfiguredFeatures.ORE_TELEPORT.getHolder().orElseThrow(), CountPlacement.of(UniformInt.of(6, 10)),
			HeightRangePlacement.uniform(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(64)),
			InSquarePlacement.spread(), BiomeFilter.biome());

	public static final RegistryObject<PlacedFeature> ORE_SUGAR_SAND = register("ore_sugar_sand",
			ModConfiguredFeatures.ORE_SUGAR_SAND.getHolder().orElseThrow(), CountPlacement.of(8),
			HeightRangePlacement.uniform(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(64)),
			InSquarePlacement.spread(), BiomeFilter.biome());

	//Overworld
	public static final RegistryObject<PlacedFeature> ORE_MILK_BROWNIE_OVERWORLD = register("ore_milk_brownie_overworld",
			ModConfiguredFeatures.ORE_MILK_BROWNIE_OVERWORLD.getHolder().orElseThrow(), CountPlacement.of(1),
			HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(20), VerticalAnchor.belowTop(40)),
			InSquarePlacement.spread(), BiomeFilter.biome());

	public static final RegistryObject<PlacedFeature> ORE_WHITE_BROWNIE_OVERWORLD = register("ore_white_brownie_overworld",
			ModConfiguredFeatures.ORE_WHITE_BROWNIE_OVERWORLD.getHolder().orElseThrow(), CountPlacement.of(1),
			HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(40), VerticalAnchor.belowTop(64)),
			InSquarePlacement.spread(), BiomeFilter.biome());

	public static final RegistryObject<PlacedFeature> ORE_DARK_BROWNIE_OVERWORLD = register("ore_dark_brownie_overworld",
			ModConfiguredFeatures.ORE_DARK_BROWNIE_OVERWORLD.getHolder().orElseThrow(), CountPlacement.of(1),
			HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.belowTop(25)),
			InSquarePlacement.spread(), BiomeFilter.biome());

	public static final RegistryObject<PlacedFeature> ORE_SUGAR_BLOCK = register("ore_sugar_block",
			ModConfiguredFeatures.ORE_SUGAR_BLOCK.getHolder().orElseThrow(), CountPlacement.of(2),
			HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.belowTop(30)),
			InSquarePlacement.spread(), BiomeFilter.biome());

	public static final RegistryObject<PlacedFeature> ORE_COOKIE = register("ore_cookie",
			ModConfiguredFeatures.ORE_COOKIE.getHolder().orElseThrow(), CountPlacement.of(50),
			HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(32), VerticalAnchor.belowTop(45)),
			InSquarePlacement.spread(), BiomeFilter.biome());

	//General
	public static final RegistryObject<PlacedFeature> GUMMY_WORM = register("gummy_worm",
			ModConfiguredFeatures.GUMMY_WORM.getHolder().orElseThrow(),
			CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

	public static final RegistryObject<PlacedFeature> PATCH_COTTON_CANDY = register("patch_cotton_candy",
			ModConfiguredFeatures.PATCH_COTTON_CANDY.getHolder().orElseThrow(),
			NoiseThresholdCountPlacement.of(-0.8D, 5, 10),
			InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

	public static final RegistryObject<PlacedFeature> COTTON_CANDY_TREE = register("cotton_candy_tree",
			ModConfiguredFeatures.COTTON_CANDY_TREE.getHolder().orElseThrow(),
			() -> VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1F, 1), ModBlocks.COTTON_CANDY_SAPLING.get()));

	public static final RegistryObject<PlacedFeature> PATCH_CHOCOLATE_MUSHROOM = register("patch_chocolate_mushroom",
			ModConfiguredFeatures.PATCH_CHOCOLATE_MUSHROOM.getHolder().orElseThrow(),
			NoiseThresholdCountPlacement.of(-0.8D, 5, 10), InSquarePlacement.spread(),
			PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

	public static final RegistryObject<PlacedFeature> PATCH_CHOCOLATE_BAR = register("patch_chocolate_bar",
			ModConfiguredFeatures.PATCH_CHOCOLATE_BAR.getHolder().orElseThrow(),
			NoiseThresholdCountPlacement.of(-0.8D, 5, 10), InSquarePlacement.spread(),
			PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

	public static final RegistryObject<PlacedFeature> PATCH_CAVE_CHOCOLATE_BAR = register("patch_cave_chocolate_bar",
			ModConfiguredFeatures.PATCH_CAVE_CHOCOLATE_BAR.getHolder().orElseThrow(),
			NoiseThresholdCountPlacement.of(-0.8D, 5, 10), InSquarePlacement.spread(),
			PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

	public static final RegistryObject<PlacedFeature> CHOCOLATE_TREE = register("chocolate_tree",
			ModConfiguredFeatures.CHOCOLATE_TREE.getHolder().orElseThrow(),
			() -> VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1F, 1), ModBlocks.CHOCOLATE_SAPLING.get()));

	public static final RegistryObject<PlacedFeature> PATCH_CAVE_CANDY_CANE = register("patch_cave_candy_cane",
			ModConfiguredFeatures.PATCH_CAVE_CANDY_CANE.getHolder().orElseThrow(),
			NoiseThresholdCountPlacement.of(-0.8D, 5, 10), InSquarePlacement.spread(),
			PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

	public static final RegistryObject<PlacedFeature> SUGAR_SPIKE = register("sugar_spike",
			ModConfiguredFeatures.SUGAR_SPIKE.getHolder().orElseThrow(),
			VegetationPlacements.worldSurfaceSquaredWithCount(2));

	public static final RegistryObject<PlacedFeature> MILK_CHOCOLATE_SPIKE = register("milk_chocolate_spike",
			ModConfiguredFeatures.MILK_CHOCOLATE_SPIKE.getHolder().orElseThrow(),
			VegetationPlacements.worldSurfaceSquaredWithCount(2));

	public static final RegistryObject<PlacedFeature> CHOCOLATE_SPIKE = register("chocolate_spike",
			ModConfiguredFeatures.CHOCOLATE_SPIKE.getHolder().orElseThrow(),
			VegetationPlacements.worldSurfaceSquaredWithCount(2));


	public static final RegistryObject<PlacedFeature> LAKE_CHOCOLATE_SURFACE = register("lake_chocolate_surface",
			ModConfiguredFeatures.LAKE_CHOCOLATE.getHolder().orElseThrow(),
			RarityFilter.onAverageOnceEvery(200), InSquarePlacement.spread(),
			PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
	public static final RegistryObject<PlacedFeature> LAKE_CHOCOLATE_UNDERGROUND = register("lake_chocolate_underground",
			ModConfiguredFeatures.LAKE_CHOCOLATE.getHolder().orElseThrow(),
			RarityFilter.onAverageOnceEvery(9), InSquarePlacement.spread(),
			HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.top())),
			EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.allOf(BlockPredicate.not(BlockPredicate.ONLY_IN_AIR_PREDICATE),
					BlockPredicate.insideWorld(new BlockPos(0, -5, 0))), 32),
			SurfaceRelativeThresholdFilter.of(Heightmap.Types.OCEAN_FLOOR_WG, Integer.MIN_VALUE, -5), BiomeFilter.biome());
	public static final RegistryObject<PlacedFeature> LAKE_CANDY_SURFACE = register("lake_candy_surface",
			ModConfiguredFeatures.LAKE_CANDY.getHolder().orElseThrow(),
			RarityFilter.onAverageOnceEvery(200), InSquarePlacement.spread(),
			PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
	public static final RegistryObject<PlacedFeature> LAKE_CANDY_UNDERGROUND = register("lake_candy_underground",
			ModConfiguredFeatures.LAKE_CANDY.getHolder().orElseThrow(),
			RarityFilter.onAverageOnceEvery(9), InSquarePlacement.spread(),
			HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.top())),
			EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.allOf(BlockPredicate.not(BlockPredicate.ONLY_IN_AIR_PREDICATE),
					BlockPredicate.insideWorld(new BlockPos(0, -5, 0))), 32),
			SurfaceRelativeThresholdFilter.of(Heightmap.Types.OCEAN_FLOOR_WG, Integer.MIN_VALUE, -5), BiomeFilter.biome());


	private static RegistryObject<PlacedFeature> register(String registryName,
														  Holder<? extends ConfiguredFeature<?, ?>> configuredHolder,
														  List<PlacementModifier> placementModifiers) {
		return PLACED_FEATURES.register(registryName, () -> new PlacedFeature(Holder.hackyErase(configuredHolder), List.copyOf(placementModifiers)));
	}

	private static RegistryObject<PlacedFeature> register(String registryName,
														  Holder<? extends ConfiguredFeature<?, ?>> configuredHolder,
														  Supplier<List<PlacementModifier>> placementModifiers) {
		return PLACED_FEATURES.register(registryName, () -> new PlacedFeature(Holder.hackyErase(configuredHolder), List.copyOf(placementModifiers.get())));
	}

	private static RegistryObject<PlacedFeature> register(String registryName,
														  Holder<? extends ConfiguredFeature<?, ?>> configuredHolder,
														  PlacementModifier... placementModifiers) {
		return PLACED_FEATURES.register(registryName, () -> new PlacedFeature(Holder.hackyErase(configuredHolder), List.of(placementModifiers)));
	}
}
