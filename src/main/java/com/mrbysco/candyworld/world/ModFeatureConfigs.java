package com.mrbysco.candyworld.world;

import com.google.common.collect.ImmutableSet;
import com.mrbysco.candyworld.CandyWorld;
import com.mrbysco.candyworld.registry.ModBlocks;
import com.mrbysco.candyworld.registry.ModTags;
import com.mrbysco.candyworld.world.feature.config.SpikeFeatureConfig;
import com.mrbysco.candyworld.world.tree.placer.ChocolateFoliagePlacer;
import com.mrbysco.candyworld.world.tree.placer.CottonCandyFoliagePlacer;
import net.minecraft.block.BlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.ColumnBlockPlacer;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.Features.Placements;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType;
import net.minecraft.world.gen.feature.TwoLayerFeature;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.feature.template.TagMatchRuleTest;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.NoiseDependant;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

public class ModFeatureConfigs {
	protected static final BlockState CRYSTALLIZED_SUGAR_COOKIE_ORE = ModBlocks.CRYSTALLIZED_SUGAR_COOKIE_ORE.get().defaultBlockState();
	protected static final BlockState COOKIE_ORE = ModBlocks.COOKIE_ORE.get().defaultBlockState();
	protected static final BlockState TELEPORT_ORE = ModBlocks.TELEPORTER_ORE.get().defaultBlockState();
	protected static final BlockState SUGAR_SAND = ModBlocks.SUGAR_SAND.get().defaultBlockState();
	protected static final BlockState CRYSTALLIZED_SUGAR = ModBlocks.CRYSTALLIZED_SUGAR.get().defaultBlockState();
	protected static final BlockState CANDY_GRASS_BLOCK = ModBlocks.CANDY_GRASS_BLOCK.get().defaultBlockState();
	protected static final BlockState CHOCOLATE_COVERED_WHITE_BROWNIE = ModBlocks.CHOCOLATE_COVERED_WHITE_BROWNIE.get().defaultBlockState();
	protected static final BlockState MILK_BROWNIE = ModBlocks.MILK_BROWNIE_BLOCK.get().defaultBlockState();
	protected static final BlockState WHITE_BROWNIE = ModBlocks.WHITE_BROWNIE_BLOCK.get().defaultBlockState();
	protected static final BlockState DARK_BROWNIE = ModBlocks.DARK_BROWNIE_BLOCK.get().defaultBlockState();
	protected static final BlockState COTTON_CANDY_PLANT = ModBlocks.COTTON_CANDY_PLANT.get().defaultBlockState();
	protected static final BlockState COTTON_CANDY_BUSH = ModBlocks.COTTON_CANDY_BUSH.get().defaultBlockState();

	private static final BlockState WHITE_CANDY_CANE_BLOCK = ModBlocks.WHITE_CANDY_CANE_BLOCK.get().defaultBlockState();
	private static final BlockState WHITE_RED_CANDY_CANE_BLOCK = ModBlocks.WHITE_RED_CANDY_CANE_BLOCK.get().defaultBlockState();
	private static final BlockState WHITE_GREEN_CANDY_CANE_BLOCK = ModBlocks.WHITE_GREEN_CANDY_CANE_BLOCK.get().defaultBlockState();

	private static final BlockState COTTON_CANDY_LEAVES = ModBlocks.COTTON_CANDY_LEAVES.get().defaultBlockState();

	private static final BlockState WAFER_STICK_BLOCK = ModBlocks.WAFER_STICK_BLOCK.get().defaultBlockState();
	private static final BlockState MILK_CHOCOLATE_LEAVES = ModBlocks.MILK_CHOCOLATE_LEAVES.get().defaultBlockState();
	private static final BlockState WHITE_CHOCOLATE_LEAVES = ModBlocks.WHITE_CHOCOLATE_LEAVES.get().defaultBlockState();
	private static final BlockState DARK_CHOCOLATE_LEAVES = ModBlocks.DARK_CHOCOLATE_LEAVES.get().defaultBlockState();

	private static final BlockState MILK_CHOCOLATE_MUSHROOM = ModBlocks.MILK_CHOCOLATE_MUSHROOM.get().defaultBlockState();
	private static final BlockState WHITE_CHOCOLATE_MUSHROOM = ModBlocks.WHITE_CHOCOLATE_MUSHROOM.get().defaultBlockState();
	private static final BlockState DARK_CHOCOLATE_MUSHROOM = ModBlocks.DARK_CHOCOLATE_MUSHROOM.get().defaultBlockState();
	private static final BlockState MILK_CHOCOLATE_BAR = ModBlocks.MILK_CHOCOLATE_BAR_BLOCK.get().defaultBlockState();
	private static final BlockState WHITE_CHOCOLATE_BAR = ModBlocks.WHITE_CHOCOLATE_BAR_BLOCK.get().defaultBlockState();
	private static final BlockState DARK_CHOCOLATE_BAR = ModBlocks.DARK_CHOCOLATE_BAR_BLOCK.get().defaultBlockState();
	private static final BlockState MILK_CHOCOLATE_BLOCK = ModBlocks.MILK_CHOCOLATE_BLOCK.get().defaultBlockState();
	private static final BlockState WHITE_CHOCOLATE_BLOCK = ModBlocks.WHITE_CHOCOLATE_BLOCK.get().defaultBlockState();
	private static final BlockState DARK_CHOCOLATE_BLOCK = ModBlocks.DARK_CHOCOLATE_BLOCK.get().defaultBlockState();

	private static final BlockState LIQUID_CHOCOLATE = ModBlocks.LIQUID_CHOCOLATE_BLOCK.get().defaultBlockState();
	private static final BlockState LIQUID_CANDY = ModBlocks.LIQUID_CANDY_BLOCK.get().defaultBlockState();

	//Candy world
	public static ConfiguredFeature<?, ?> ORE_MILK_BROWNIE = register("ore_milk_brownie",
			Feature.ORE.configured(new OreFeatureConfig(CustomFillerType.SUGAR, MILK_BROWNIE, 25))
					.range(64).squared().count(16));

	public static ConfiguredFeature<?, ?> ORE_WHITE_BROWNIE = register("ore_white_brownie",
			Feature.ORE.configured(new OreFeatureConfig(CustomFillerType.SUGAR, WHITE_BROWNIE, 25))
					.range(64).squared().count(16));

	public static ConfiguredFeature<?, ?> ORE_DARK_BROWNIE = register("ore_milk_brownie",
			Feature.ORE.configured(new OreFeatureConfig(CustomFillerType.SUGAR, DARK_BROWNIE, 25))
					.range(64).squared().count(16));

	public static ConfiguredFeature<?, ?> ORE_SUGAR_COOKIE = register("ore_sugar_cookie",
			Feature.ORE.configured(new OreFeatureConfig(CustomFillerType.SUGAR, CRYSTALLIZED_SUGAR_COOKIE_ORE, 3))
					.range(64).squared().count(80));

	public static ConfiguredFeature<?, ?> ORE_TELEPORT = register("ore_teleport",
			ModFeatures.TELEPORT_ORE.get().configured(new OreFeatureConfig(CustomFillerType.SUGAR, TELEPORT_ORE, 1))
					.range(64).squared().count(FeatureSpread.of(6, 10)));

	public static ConfiguredFeature<?, ?> ORE_SUGAR_SAND = register("ore_sugar_sand",
			Feature.ORE.configured(new OreFeatureConfig(CustomFillerType.SUGAR, SUGAR_SAND, 20))
					.range(64).squared().count(8));


	//Overworld
	public static ConfiguredFeature<?, ?> ORE_MILK_BROWNIE_OVERWORLD = register("ore_milk_brownie_overworld",
			Feature.ORE.configured(new OreFeatureConfig(FillerBlockType.NATURAL_STONE, MILK_BROWNIE, 25))
					.decorated(Placement.RANGE.configured(new TopSolidRangeConfig(0, 20, 40))).squared().count(1));

	public static ConfiguredFeature<?, ?> ORE_WHITE_BROWNIE_OVERWORLD = register("ore_white_brownie_overworld",
			Feature.ORE.configured(new OreFeatureConfig(FillerBlockType.NATURAL_STONE, WHITE_BROWNIE, 25))
					.decorated(Placement.RANGE.configured(new TopSolidRangeConfig(0, 40, 64))).squared().count(1));

	public static ConfiguredFeature<?, ?> ORE_DARK_BROWNIE_OVERWORLD = register("ore_milk_brownie_overworld",
			Feature.ORE.configured(new OreFeatureConfig(FillerBlockType.NATURAL_STONE, DARK_BROWNIE, 25))
					.decorated(Placement.RANGE.configured(new TopSolidRangeConfig(0, 0, 25))).squared().count(1));

	public static ConfiguredFeature<?, ?> ORE_SUGAR_BLOCK = register("ore_sugar_block",
			Feature.ORE.configured(new OreFeatureConfig(FillerBlockType.NATURAL_STONE, CRYSTALLIZED_SUGAR, 20))
					.decorated(Placement.RANGE.configured(new TopSolidRangeConfig(0, 0, 30))).squared().count(2));

	public static ConfiguredFeature<?, ?> ORE_COOKIE = register("ore_cookie",
			Feature.ORE.configured(new OreFeatureConfig(FillerBlockType.NATURAL_STONE, COOKIE_ORE, 20))
					.decorated(Placement.RANGE.configured(new TopSolidRangeConfig(0, 32, 45))).squared().count(50));

	//General
	public static final ConfiguredFeature<?, ?> GUMMY_WORM = register("gummy_worm", ModFeatures.GUMMY_WORM.get().configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_SQUARE).chance(1));

	public static ConfiguredFeature<?, ?> PATCH_COTTON_CANDY = register("patch_cotton_candy",
			Feature.RANDOM_PATCH.configured(Configs.DEFAULT_COTTON_CANDY_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE)
					.decorated(Placement.COUNT_NOISE.configured(new NoiseDependant(-0.8D, 5, 10))));

	public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> COTTON_CANDY = register("cotton_candy",
			ModFeatures.CANDY_TREE.get().configured((new BaseTreeFeatureConfig.Builder(
					new SimpleBlockStateProvider(WHITE_CANDY_CANE_BLOCK),
					new SimpleBlockStateProvider(COTTON_CANDY_LEAVES),
					new CottonCandyFoliagePlacer(FeatureSpread.of(2, 1), FeatureSpread.of(0, 2),
							FeatureSpread.of(1, 1)),
					new StraightTrunkPlacer(5, 2, 1),
					new TwoLayerFeature(1, 0, 1))).ignoreVines().build()));

	public static final ConfiguredFeature<?, ?> COTTON_CANDY_TREE = register("cotton_candy_tree",
			COTTON_CANDY.decorated(Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(3, 0.1F, 1))));

	public static ConfiguredFeature<?, ?> PATCH_CHOCOLATE_MUSHROOM = register("patch_chocolate_mushroom",
			Feature.RANDOM_PATCH.configured(Configs.DEFAULT_CHOCOLATE_MUSHROOM_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE)
					.decorated(Placement.COUNT_NOISE.configured(new NoiseDependant(-0.8D, 5, 10))));

	public static ConfiguredFeature<?, ?> PATCH_CHOCOLATE_BAR = register("patch_chocolate_bar",
			ModFeatures.RANDOM_ROTATED_PATCH.get().configured(Configs.DEFAULT_CHOCOLATE_BAR_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE)
					.decorated(Placement.COUNT_NOISE.configured(new NoiseDependant(-0.8D, 5, 10))));

	public static ConfiguredFeature<?, ?> PATCH_CAVE_CHOCOLATE_BAR = register("patch_cave_chocolate_bar",
			ModFeatures.RANDOM_ROTATED_PATCH.get().configured(Configs.DEFAULT_CAVE_CHOCOLATE_BAR_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE)
					.decorated(Placement.COUNT_NOISE.configured(new NoiseDependant(-0.8D, 5, 10))));

	public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> CHOCOLATE = register("chocolate",
			ModFeatures.CANDY_TREE.get().configured((new BaseTreeFeatureConfig.Builder(
					new SimpleBlockStateProvider(WAFER_STICK_BLOCK),
					new WeightedBlockStateProvider().add(MILK_CHOCOLATE_LEAVES, 1).add(WHITE_CHOCOLATE_LEAVES, 1).add(DARK_CHOCOLATE_LEAVES, 1),
					new ChocolateFoliagePlacer(FeatureSpread.of(2, 1), FeatureSpread.of(0, 2),
							FeatureSpread.of(1, 1)),
					new StraightTrunkPlacer(5, 2, 1),
					new TwoLayerFeature(1, 0, 1))).ignoreVines().build()));

	public static final ConfiguredFeature<?, ?> CHOCOLATE_TREE = register("chocolate_tree",
			CHOCOLATE.decorated(Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(3, 0.1F, 1))));

	public static ConfiguredFeature<?, ?> PATCH_CAVE_CANDY_CANE = register("patch_cave_candy_cane",
			ModFeatures.CANDY_CANE.get().configured(Configs.DEFAULT_CANDY_CANE_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE)
					.decorated(Placement.COUNT_NOISE.configured(new NoiseDependant(-0.8D, 5, 10))));

	public static final ConfiguredFeature<?, ?> SUGAR_SPIKE = register("sugar_spike",
			ModFeatures.SPIKE.get().configured(Configs.DEFAULT_SUGAR_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
	public static final ConfiguredFeature<?, ?> MILK_CHOCOLATE_SPIKE = register("milk_chocolate_spike",
			ModFeatures.SPIKE.get().configured(Configs.DEFAULT_MILK_CHOCOLATE_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
	public static final ConfiguredFeature<?, ?> CHOCOLATE_SPIKE = register("chocolate_spike",
			ModFeatures.SPIKE.get().configured(Configs.DEFAULT_CHOCOLATE_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));

	public static final ConfiguredFeature<?, ?> LAKE_CHOCOLATE = register("lake_chocolate",
			ModFeatures.CANDY_LAKES.get().configured(new BlockStateFeatureConfig(LIQUID_CHOCOLATE)).decorated(Placement.WATER_LAKE.configured(new ChanceConfig(4))));
	public static final ConfiguredFeature<?, ?> LAKE_CANDY = register("lake_candy",
			ModFeatures.CANDY_LAKES.get().configured(new BlockStateFeatureConfig(LIQUID_CANDY)).decorated(Placement.LAVA_LAKE.configured(new ChanceConfig(80))));

	private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> feature) {
		return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(CandyWorld.MOD_ID, key), feature);
	}

	public static final class CustomFillerType {
		public static final RuleTest SUGAR = new TagMatchRuleTest(ModTags.SUGAR);
	}

	public static final class Configs {
		public static final BlockClusterFeatureConfig DEFAULT_COTTON_CANDY_CONFIG = (new BlockClusterFeatureConfig.Builder(
				new WeightedBlockStateProvider().add(COTTON_CANDY_PLANT, 1).add(COTTON_CANDY_BUSH, 2), SimpleBlockPlacer.INSTANCE)).tries(10).build();
		public static final BlockClusterFeatureConfig DEFAULT_CHOCOLATE_MUSHROOM_CONFIG = (new BlockClusterFeatureConfig.Builder(
				new WeightedBlockStateProvider().add(MILK_CHOCOLATE_MUSHROOM, 1).add(WHITE_CHOCOLATE_MUSHROOM, 1).add(DARK_CHOCOLATE_MUSHROOM, 1),
				SimpleBlockPlacer.INSTANCE)).tries(40).build();
		public static final BlockClusterFeatureConfig DEFAULT_CHOCOLATE_BAR_CONFIG = (new BlockClusterFeatureConfig.Builder(
				new WeightedBlockStateProvider().add(MILK_CHOCOLATE_BAR, 1).add(WHITE_CHOCOLATE_BAR, 1).add(DARK_CHOCOLATE_BAR, 1),
				SimpleBlockPlacer.INSTANCE)).tries(20).build();
		public static final BlockClusterFeatureConfig DEFAULT_CAVE_CHOCOLATE_BAR_CONFIG = (new BlockClusterFeatureConfig.Builder(
				new WeightedBlockStateProvider().add(MILK_CHOCOLATE_BAR, 1).add(WHITE_CHOCOLATE_BAR, 1).add(DARK_CHOCOLATE_BAR, 1),
				SimpleBlockPlacer.INSTANCE)).tries(20).noProjection().build();

		public static final BlockClusterFeatureConfig DEFAULT_CANDY_CANE_CONFIG = (new BlockClusterFeatureConfig.Builder(
				new WeightedBlockStateProvider().add(WHITE_RED_CANDY_CANE_BLOCK, 1).add(WHITE_GREEN_CANDY_CANE_BLOCK, 2),
				new ColumnBlockPlacer(1, 16))).tries(16).noProjection().build();

		//Candy Plains
		public static final SpikeFeatureConfig DEFAULT_SUGAR_CONFIG = (new SpikeFeatureConfig.Builder(
				new SimpleBlockStateProvider(CRYSTALLIZED_SUGAR))).minLength(3).maxLength(8).chance(8)
				.whitelist(ImmutableSet.of(CANDY_GRASS_BLOCK.getBlock(), MILK_BROWNIE.getBlock())).build();
		public static final SpikeFeatureConfig DEFAULT_MILK_CHOCOLATE_CONFIG = (new SpikeFeatureConfig.Builder(
				new SimpleBlockStateProvider(MILK_CHOCOLATE_BLOCK))).minLength(5).maxLength(12).chance(24)
				.whitelist(ImmutableSet.of(CANDY_GRASS_BLOCK.getBlock(), MILK_BROWNIE.getBlock())).build();

		//Chocolate Forest
		public static final SpikeFeatureConfig DEFAULT_CHOCOLATE_CONFIG = (new SpikeFeatureConfig.Builder(
				new WeightedBlockStateProvider().add(MILK_CHOCOLATE_BLOCK, 1).add(WHITE_CHOCOLATE_BLOCK, 1).add(DARK_CHOCOLATE_BLOCK, 1)))
				.minLength(3).maxLength(24).chance(16).whitelist(ImmutableSet.of(CHOCOLATE_COVERED_WHITE_BROWNIE.getBlock(), WHITE_BROWNIE.getBlock())).build();
	}
}
