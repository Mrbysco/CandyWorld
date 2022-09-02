package com.mrbysco.candyworld.world;

import com.mrbysco.candyworld.CandyWorld;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
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
	public static final Holder<PlacedFeature> ORE_MILK_BROWNIE = PlacementUtils.register(new ResourceLocation(CandyWorld.MOD_ID, "ore_milk_brownie").toString(),
			ModConfiguredFeatures.ORE_MILK_BROWNIE, CountPlacement.of(16),
			HeightRangePlacement.uniform(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(64)),
			InSquarePlacement.spread(), BiomeFilter.biome());

	public static final Holder<PlacedFeature> ORE_WHITE_BROWNIE = PlacementUtils.register(new ResourceLocation(CandyWorld.MOD_ID, "ore_white_brownie").toString(),
			ModConfiguredFeatures.ORE_WHITE_BROWNIE, CountPlacement.of(16),
			HeightRangePlacement.uniform(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(64)),
			InSquarePlacement.spread(), BiomeFilter.biome());

	public static final Holder<PlacedFeature> ORE_DARK_BROWNIE = PlacementUtils.register(new ResourceLocation(CandyWorld.MOD_ID, "ore_dark_brownie").toString(),
			ModConfiguredFeatures.ORE_DARK_BROWNIE, CountPlacement.of(16),
			HeightRangePlacement.uniform(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(64)),
			InSquarePlacement.spread(), BiomeFilter.biome());

	public static final Holder<PlacedFeature> ORE_SUGAR_COOKIE = PlacementUtils.register(new ResourceLocation(CandyWorld.MOD_ID, "ore_sugar_cookie").toString(),
			ModConfiguredFeatures.ORE_SUGAR_COOKIE, CountPlacement.of(80),
			HeightRangePlacement.uniform(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(64)),
			InSquarePlacement.spread(), BiomeFilter.biome());

	public static final Holder<PlacedFeature> ORE_TELEPORT = PlacementUtils.register(new ResourceLocation(CandyWorld.MOD_ID, "ore_teleport").toString(),
			ModConfiguredFeatures.ORE_TELEPORT, CountPlacement.of(UniformInt.of(6, 10)),
			HeightRangePlacement.uniform(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(64)),
			InSquarePlacement.spread(), BiomeFilter.biome());

	public static final Holder<PlacedFeature> ORE_SUGAR_SAND = PlacementUtils.register(new ResourceLocation(CandyWorld.MOD_ID, "ore_sugar_sand").toString(),
			ModConfiguredFeatures.ORE_SUGAR_SAND, CountPlacement.of(8),
			HeightRangePlacement.uniform(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(64)),
			InSquarePlacement.spread(), BiomeFilter.biome());

	//Overworld
	public static final Holder<PlacedFeature> ORE_MILK_BROWNIE_OVERWORLD = PlacementUtils.register(new ResourceLocation(CandyWorld.MOD_ID, "ore_milk_brownie_overworld").toString(),
			ModConfiguredFeatures.ORE_MILK_BROWNIE_OVERWORLD, CountPlacement.of(1),
			HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(20), VerticalAnchor.belowTop(40)),
			InSquarePlacement.spread(), BiomeFilter.biome());

	public static final Holder<PlacedFeature> ORE_WHITE_BROWNIE_OVERWORLD = PlacementUtils.register(new ResourceLocation(CandyWorld.MOD_ID, "ore_white_brownie_overworld").toString(),
			ModConfiguredFeatures.ORE_WHITE_BROWNIE_OVERWORLD, CountPlacement.of(1),
			HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(40), VerticalAnchor.belowTop(64)),
			InSquarePlacement.spread(), BiomeFilter.biome());

	public static final Holder<PlacedFeature> ORE_DARK_BROWNIE_OVERWORLD = PlacementUtils.register(new ResourceLocation(CandyWorld.MOD_ID, "ore_dark_brownie_overworld").toString(),
			ModConfiguredFeatures.ORE_DARK_BROWNIE_OVERWORLD, CountPlacement.of(1),
			HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.belowTop(25)),
			InSquarePlacement.spread(), BiomeFilter.biome());

	public static final Holder<PlacedFeature> ORE_SUGAR_BLOCK = PlacementUtils.register(new ResourceLocation(CandyWorld.MOD_ID, "ore_sugar_block").toString(),
			ModConfiguredFeatures.ORE_SUGAR_BLOCK, CountPlacement.of(2),
			HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.belowTop(30)),
			InSquarePlacement.spread(), BiomeFilter.biome());

	public static final Holder<PlacedFeature> ORE_COOKIE = PlacementUtils.register(new ResourceLocation(CandyWorld.MOD_ID, "ore_cookie").toString(),
			ModConfiguredFeatures.ORE_COOKIE, CountPlacement.of(50),
			HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(32), VerticalAnchor.belowTop(45)),
			InSquarePlacement.spread(), BiomeFilter.biome());

	//General
	public static final Holder<PlacedFeature> GUMMY_WORM = PlacementUtils.register(new ResourceLocation(CandyWorld.MOD_ID, "gummy_worm").toString(),
			ModConfiguredFeatures.GUMMY_WORM, CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

	public static final Holder<PlacedFeature> PATCH_COTTON_CANDY = PlacementUtils.register(new ResourceLocation(CandyWorld.MOD_ID, "patch_cotton_candy").toString(),
			ModConfiguredFeatures.PATCH_COTTON_CANDY, NoiseThresholdCountPlacement.of(-0.8D, 5, 10),
			InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

	public static final Holder<PlacedFeature> COTTON_CANDY_TREE = PlacementUtils.register(new ResourceLocation(CandyWorld.MOD_ID, "cotton_candy_tree").toString(),
			ModConfiguredFeatures.COTTON_CANDY_TREE,
			VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1F, 1)));

	public static final Holder<PlacedFeature> PATCH_CHOCOLATE_MUSHROOM = PlacementUtils.register(new ResourceLocation(CandyWorld.MOD_ID, "patch_chocolate_mushroom").toString(),
			ModConfiguredFeatures.PATCH_CHOCOLATE_MUSHROOM,
			NoiseThresholdCountPlacement.of(-0.8D, 5, 10), InSquarePlacement.spread(),
			PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

	public static final Holder<PlacedFeature> PATCH_CHOCOLATE_BAR = PlacementUtils.register(new ResourceLocation(CandyWorld.MOD_ID, "patch_chocolate_bar").toString(),
			ModConfiguredFeatures.PATCH_CHOCOLATE_BAR,
			NoiseThresholdCountPlacement.of(-0.8D, 5, 10), InSquarePlacement.spread(),
			PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

	public static final Holder<PlacedFeature> PATCH_CAVE_CHOCOLATE_BAR = PlacementUtils.register(new ResourceLocation(CandyWorld.MOD_ID, "patch_cave_chocolate_bar").toString(),
			ModConfiguredFeatures.PATCH_CAVE_CHOCOLATE_BAR,
			NoiseThresholdCountPlacement.of(-0.8D, 5, 10), InSquarePlacement.spread(),
			PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

	public static final Holder<PlacedFeature> CHOCOLATE_TREE = PlacementUtils.register("chocolate_tree",
			ModConfiguredFeatures.CHOCOLATE_TREE, VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1F, 1)));

	public static final Holder<PlacedFeature> PATCH_CAVE_CANDY_CANE = PlacementUtils.register(new ResourceLocation(CandyWorld.MOD_ID, "patch_cave_candy_cane").toString(),
			ModConfiguredFeatures.PATCH_CAVE_CANDY_CANE,
			NoiseThresholdCountPlacement.of(-0.8D, 5, 10), InSquarePlacement.spread(),
			PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

	public static final Holder<PlacedFeature> SUGAR_SPIKE = PlacementUtils.register(new ResourceLocation(CandyWorld.MOD_ID, "sugar_spike").toString(),
			ModConfiguredFeatures.SUGAR_SPIKE,
			VegetationPlacements.worldSurfaceSquaredWithCount(2));

	public static final Holder<PlacedFeature> MILK_CHOCOLATE_SPIKE = PlacementUtils.register(new ResourceLocation(CandyWorld.MOD_ID, "milk_chocolate_spike").toString(),
			ModConfiguredFeatures.MILK_CHOCOLATE_SPIKE,
			VegetationPlacements.worldSurfaceSquaredWithCount(2));

	public static final Holder<PlacedFeature> CHOCOLATE_SPIKE = PlacementUtils.register(new ResourceLocation(CandyWorld.MOD_ID, "chocolate_spike").toString(),
			ModConfiguredFeatures.CHOCOLATE_SPIKE,
			VegetationPlacements.worldSurfaceSquaredWithCount(2));


	public static final Holder<PlacedFeature> LAKE_CHOCOLATE_SURFACE = PlacementUtils.register("lake_chocolate_surface", ModConfiguredFeatures.LAKE_CHOCOLATE,
			RarityFilter.onAverageOnceEvery(200), InSquarePlacement.spread(),
			PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
	public static final Holder<PlacedFeature> LAKE_CHOCOLATE_UNDERGROUND = PlacementUtils.register("lake_chocolate_underground", ModConfiguredFeatures.LAKE_CHOCOLATE,
			RarityFilter.onAverageOnceEvery(9), InSquarePlacement.spread(),
			HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.top())),
			EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.allOf(BlockPredicate.not(BlockPredicate.ONLY_IN_AIR_PREDICATE),
					BlockPredicate.insideWorld(new BlockPos(0, -5, 0))), 32),
			SurfaceRelativeThresholdFilter.of(Heightmap.Types.OCEAN_FLOOR_WG, Integer.MIN_VALUE, -5), BiomeFilter.biome());
	public static final Holder<PlacedFeature> LAKE_CANDY_SURFACE = PlacementUtils.register("lake_candy_surface", ModConfiguredFeatures.LAKE_CANDY,
			RarityFilter.onAverageOnceEvery(200), InSquarePlacement.spread(),
			PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
	public static final Holder<PlacedFeature> LAKE_CANDY_UNDERGROUND = PlacementUtils.register("lake_candy_underground", ModConfiguredFeatures.LAKE_CANDY,
			RarityFilter.onAverageOnceEvery(9), InSquarePlacement.spread(),
			HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.top())),
			EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.allOf(BlockPredicate.not(BlockPredicate.ONLY_IN_AIR_PREDICATE),
					BlockPredicate.insideWorld(new BlockPos(0, -5, 0))), 32),
			SurfaceRelativeThresholdFilter.of(Heightmap.Types.OCEAN_FLOOR_WG, Integer.MIN_VALUE, -5), BiomeFilter.biome());

}
