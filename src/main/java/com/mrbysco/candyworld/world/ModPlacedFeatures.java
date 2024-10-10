package com.mrbysco.candyworld.world;

import com.mrbysco.candyworld.CandyWorld;
import com.mrbysco.candyworld.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
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
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.levelgen.placement.SurfaceRelativeThresholdFilter;

public class ModPlacedFeatures {
	//Candy world
	public static final ResourceKey<PlacedFeature> ORE_MILK_BROWNIE = createKey("ore_milk_brownie");
	public static final ResourceKey<PlacedFeature> ORE_WHITE_BROWNIE = createKey("ore_white_brownie");
	public static final ResourceKey<PlacedFeature> ORE_DARK_BROWNIE = createKey("ore_dark_brownie");
	public static final ResourceKey<PlacedFeature> ORE_SUGAR_COOKIE = createKey("ore_sugar_cookie");
	public static final ResourceKey<PlacedFeature> ORE_TELEPORT = createKey("ore_teleport");

	//Overworld
	public static final ResourceKey<PlacedFeature> ORE_MILK_BROWNIE_OVERWORLD = createKey("ore_milk_brownie_overworld");
	public static final ResourceKey<PlacedFeature> ORE_WHITE_BROWNIE_OVERWORLD = createKey("ore_white_brownie_overworld");
	public static final ResourceKey<PlacedFeature> ORE_DARK_BROWNIE_OVERWORLD = createKey("ore_dark_brownie_overworld");
	public static final ResourceKey<PlacedFeature> ORE_SUGAR_BLOCK = createKey("ore_sugar_block");
	public static final ResourceKey<PlacedFeature> ORE_COOKIE = createKey("ore_cookie");
	public static final ResourceKey<PlacedFeature> ORE_SUGAR_SAND = createKey("ore_sugar_sand");

	//General
	public static final ResourceKey<PlacedFeature> GUMMY_WORM = createKey("gummy_worm");
	public static final ResourceKey<PlacedFeature> PATCH_COTTON_CANDY = createKey("patch_cotton_candy");
	public static final ResourceKey<PlacedFeature> COTTON_CANDY_TREE = createKey("cotton_candy_tree");
	public static final ResourceKey<PlacedFeature> CHOCOLATE_TREE = createKey("chocolate_tree");
	public static final ResourceKey<PlacedFeature> PATCH_CHOCOLATE_MUSHROOM = createKey("patch_chocolate_mushroom");
	public static final ResourceKey<PlacedFeature> PATCH_CHOCOLATE_BAR = createKey("patch_chocolate_bar");
	public static final ResourceKey<PlacedFeature> PATCH_CAVE_CHOCOLATE_BAR = createKey("patch_cave_chocolate_bar");
	public static final ResourceKey<PlacedFeature> PATCH_CAVE_CANDY_CANE = createKey("patch_cave_candy_cane");
	public static final ResourceKey<PlacedFeature> SUGAR_SPIKE = createKey("sugar_spike");
	public static final ResourceKey<PlacedFeature> MILK_CHOCOLATE_SPIKE = createKey("milk_chocolate_spike");
	public static final ResourceKey<PlacedFeature> CHOCOLATE_SPIKE = createKey("chocolate_spike");

	public static final ResourceKey<PlacedFeature> LAKE_CHOCOLATE_SURFACE = createKey("lake_chocolate_surface");
	public static final ResourceKey<PlacedFeature> LAKE_CHOCOLATE_UNDERGROUND = createKey("lake_chocolate_underground");
	public static final ResourceKey<PlacedFeature> LAKE_CANDY_SURFACE = createKey("lake_candy_surface");
	public static final ResourceKey<PlacedFeature> LAKE_CANDY_UNDERGROUND = createKey("lake_candy_underground");

	public static ResourceKey<PlacedFeature> createKey(String key) {
		return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(CandyWorld.MOD_ID, key));
	}

	public static void bootstrap(BootstapContext<PlacedFeature> context) {
		HolderGetter<ConfiguredFeature<?, ?>> holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);

		PlacementUtils.register(context, ORE_MILK_BROWNIE, holdergetter.getOrThrow(ModConfiguredFeatures.ORE_MILK_BROWNIE),
				CountPlacement.of(16),
				HeightRangePlacement.uniform(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(64)),
				InSquarePlacement.spread(), BiomeFilter.biome());
		PlacementUtils.register(context, ORE_WHITE_BROWNIE, holdergetter.getOrThrow(ModConfiguredFeatures.ORE_WHITE_BROWNIE),
				CountPlacement.of(16),
				HeightRangePlacement.uniform(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(64)),
				InSquarePlacement.spread(), BiomeFilter.biome());
		PlacementUtils.register(context, ORE_DARK_BROWNIE, holdergetter.getOrThrow(ModConfiguredFeatures.ORE_DARK_BROWNIE),
				CountPlacement.of(16),
				HeightRangePlacement.uniform(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(64)),
				InSquarePlacement.spread(), BiomeFilter.biome());
		PlacementUtils.register(context, ORE_SUGAR_COOKIE, holdergetter.getOrThrow(ModConfiguredFeatures.ORE_SUGAR_COOKIE),
				CountPlacement.of(80),
				HeightRangePlacement.uniform(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(64)),
				InSquarePlacement.spread(), BiomeFilter.biome());
		PlacementUtils.register(context, ORE_TELEPORT, holdergetter.getOrThrow(ModConfiguredFeatures.ORE_TELEPORT),
				CountPlacement.of(UniformInt.of(6, 10)),
				HeightRangePlacement.uniform(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(64)),
				InSquarePlacement.spread(), BiomeFilter.biome());

		PlacementUtils.register(context, ORE_MILK_BROWNIE_OVERWORLD, holdergetter.getOrThrow(ModConfiguredFeatures.ORE_MILK_BROWNIE_OVERWORLD),
				CountPlacement.of(1), HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(20), VerticalAnchor.belowTop(40)),
				InSquarePlacement.spread(), BiomeFilter.biome());
		PlacementUtils.register(context, ORE_WHITE_BROWNIE_OVERWORLD, holdergetter.getOrThrow(ModConfiguredFeatures.ORE_WHITE_BROWNIE_OVERWORLD),
				CountPlacement.of(1), HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(40), VerticalAnchor.belowTop(64)),
				InSquarePlacement.spread(), BiomeFilter.biome());
		PlacementUtils.register(context, ORE_DARK_BROWNIE_OVERWORLD, holdergetter.getOrThrow(ModConfiguredFeatures.ORE_DARK_BROWNIE_OVERWORLD),
				CountPlacement.of(1), HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.belowTop(25)),
				InSquarePlacement.spread(), BiomeFilter.biome());
		PlacementUtils.register(context, ORE_SUGAR_BLOCK, holdergetter.getOrThrow(ModConfiguredFeatures.ORE_SUGAR_BLOCK),
				HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.belowTop(30)),
				InSquarePlacement.spread(), BiomeFilter.biome());
		PlacementUtils.register(context, ORE_COOKIE, holdergetter.getOrThrow(ModConfiguredFeatures.ORE_COOKIE),
				HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(32), VerticalAnchor.belowTop(45)),
				InSquarePlacement.spread(), BiomeFilter.biome());
		PlacementUtils.register(context, ORE_SUGAR_SAND, holdergetter.getOrThrow(ModConfiguredFeatures.ORE_SUGAR_SAND),
				CountPlacement.of(8),
				HeightRangePlacement.uniform(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(64)),
				InSquarePlacement.spread(), BiomeFilter.biome());

		PlacementUtils.register(context, GUMMY_WORM, holdergetter.getOrThrow(ModConfiguredFeatures.GUMMY_WORM),
				CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
		PlacementUtils.register(context, PATCH_COTTON_CANDY, holdergetter.getOrThrow(ModConfiguredFeatures.PATCH_COTTON_CANDY),
				NoiseThresholdCountPlacement.of(-0.8D, 5, 10), InSquarePlacement.spread(),
				PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
		PlacementUtils.register(context, COTTON_CANDY_TREE, holdergetter.getOrThrow(ModConfiguredFeatures.COTTON_CANDY_TREE),
				VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1F, 1), ModBlocks.COTTON_CANDY_SAPLING.get()));
		PlacementUtils.register(context, CHOCOLATE_TREE, holdergetter.getOrThrow(ModConfiguredFeatures.CHOCOLATE_TREE),
				VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1F, 1), ModBlocks.CHOCOLATE_SAPLING.get()));
		PlacementUtils.register(context, PATCH_CHOCOLATE_MUSHROOM, holdergetter.getOrThrow(ModConfiguredFeatures.PATCH_CHOCOLATE_MUSHROOM),
				NoiseThresholdCountPlacement.of(-0.8D, 5, 10), InSquarePlacement.spread(),
				PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
		PlacementUtils.register(context, PATCH_CHOCOLATE_BAR, holdergetter.getOrThrow(ModConfiguredFeatures.PATCH_CHOCOLATE_BAR),
				NoiseThresholdCountPlacement.of(-0.8D, 5, 10), InSquarePlacement.spread(),
				PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
		PlacementUtils.register(context, PATCH_CAVE_CHOCOLATE_BAR, holdergetter.getOrThrow(ModConfiguredFeatures.PATCH_CAVE_CHOCOLATE_BAR),
				NoiseThresholdCountPlacement.of(-0.8D, 5, 10), InSquarePlacement.spread(),
				PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
		PlacementUtils.register(context, PATCH_CAVE_CANDY_CANE, holdergetter.getOrThrow(ModConfiguredFeatures.PATCH_CAVE_CANDY_CANE),
				NoiseThresholdCountPlacement.of(-0.8D, 5, 10), InSquarePlacement.spread(),
				PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
		PlacementUtils.register(context, SUGAR_SPIKE, holdergetter.getOrThrow(ModConfiguredFeatures.SUGAR_SPIKE),
				VegetationPlacements.worldSurfaceSquaredWithCount(2));
		PlacementUtils.register(context, MILK_CHOCOLATE_SPIKE, holdergetter.getOrThrow(ModConfiguredFeatures.MILK_CHOCOLATE_SPIKE),
				VegetationPlacements.worldSurfaceSquaredWithCount(2));
		PlacementUtils.register(context, CHOCOLATE_SPIKE, holdergetter.getOrThrow(ModConfiguredFeatures.CHOCOLATE_SPIKE),
				VegetationPlacements.worldSurfaceSquaredWithCount(2));

		PlacementUtils.register(context, LAKE_CHOCOLATE_SURFACE, holdergetter.getOrThrow(ModConfiguredFeatures.LAKE_CHOCOLATE),
				RarityFilter.onAverageOnceEvery(200), InSquarePlacement.spread(),
				PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
		PlacementUtils.register(context, LAKE_CHOCOLATE_UNDERGROUND, holdergetter.getOrThrow(ModConfiguredFeatures.LAKE_CHOCOLATE),
				RarityFilter.onAverageOnceEvery(9), InSquarePlacement.spread(),
				HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.top())),
				EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.allOf(BlockPredicate.not(BlockPredicate.ONLY_IN_AIR_PREDICATE),
						BlockPredicate.insideWorld(new BlockPos(0, -5, 0))), 32),
				SurfaceRelativeThresholdFilter.of(Heightmap.Types.OCEAN_FLOOR_WG, Integer.MIN_VALUE, -5), BiomeFilter.biome());

		PlacementUtils.register(context, LAKE_CANDY_SURFACE, holdergetter.getOrThrow(ModConfiguredFeatures.LAKE_CANDY),
				RarityFilter.onAverageOnceEvery(200), InSquarePlacement.spread(),
				PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
		PlacementUtils.register(context, LAKE_CANDY_UNDERGROUND, holdergetter.getOrThrow(ModConfiguredFeatures.LAKE_CANDY),
				RarityFilter.onAverageOnceEvery(9), InSquarePlacement.spread(),
				HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.top())),
				EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.allOf(BlockPredicate.not(BlockPredicate.ONLY_IN_AIR_PREDICATE),
						BlockPredicate.insideWorld(new BlockPos(0, -5, 0))), 32),
				SurfaceRelativeThresholdFilter.of(Heightmap.Types.OCEAN_FLOOR_WG, Integer.MIN_VALUE, -5), BiomeFilter.biome());
	}
}
