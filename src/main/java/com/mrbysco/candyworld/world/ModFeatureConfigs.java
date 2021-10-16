package com.mrbysco.candyworld.world;

import com.google.common.collect.ImmutableSet;
import com.mrbysco.candyworld.CandyWorld;
import com.mrbysco.candyworld.registry.ModBlocks;
import com.mrbysco.candyworld.registry.ModTags;
import com.mrbysco.candyworld.world.feature.config.SpikeFeatureConfig;
import com.mrbysco.candyworld.world.tree.placer.ChocolateFoliagePlacer;
import com.mrbysco.candyworld.world.tree.placer.CottonCandyFoliagePlacer;
import com.mrbysco.candyworld.world.tree.trunkplacers.CandyStraightTrunkPlacer;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.Features;
import net.minecraft.data.worldgen.Features.Decorators;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.blockplacers.ColumnPlacer;
import net.minecraft.world.level.levelgen.feature.blockplacers.SimpleBlockPlacer;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoiseDependantDecoratorConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration.Predicates;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RangeDecoratorConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.heightproviders.BiasedToBottomHeight;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.placement.ChanceDecoratorConfiguration;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;
import net.minecraft.world.level.levelgen.placement.FrequencyWithExtraChanceDecoratorConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

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
	private static final BlockState COTTON_CANDY_SAPLING = ModBlocks.COTTON_CANDY_SAPLING.get().defaultBlockState();

	private static final BlockState WAFER_STICK_BLOCK = ModBlocks.WAFER_STICK_BLOCK.get().defaultBlockState();
	private static final BlockState MILK_CHOCOLATE_LEAVES = ModBlocks.MILK_CHOCOLATE_LEAVES.get().defaultBlockState();
	private static final BlockState WHITE_CHOCOLATE_LEAVES = ModBlocks.WHITE_CHOCOLATE_LEAVES.get().defaultBlockState();
	private static final BlockState DARK_CHOCOLATE_LEAVES = ModBlocks.DARK_CHOCOLATE_LEAVES.get().defaultBlockState();
	private static final BlockState CHOCOLATE_SAPLING = ModBlocks.CHOCOLATE_SAPLING.get().defaultBlockState();

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
			Feature.ORE.configured(new OreConfiguration(CustomFillerType.SUGAR, MILK_BROWNIE, 25))
					.rangeUniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(64)).squared().count(16));

	public static ConfiguredFeature<?, ?> ORE_WHITE_BROWNIE = register("ore_white_brownie",
			Feature.ORE.configured(new OreConfiguration(CustomFillerType.SUGAR, WHITE_BROWNIE, 25))
					.rangeUniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(64)).squared().count(16));

	public static ConfiguredFeature<?, ?> ORE_DARK_BROWNIE = register("ore_milk_brownie",
			Feature.ORE.configured(new OreConfiguration(CustomFillerType.SUGAR, DARK_BROWNIE, 25))
					.rangeUniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(64)).squared().count(16));

	public static ConfiguredFeature<?, ?> ORE_SUGAR_COOKIE = register("ore_sugar_cookie",
			Feature.ORE.configured(new OreConfiguration(CustomFillerType.SUGAR, CRYSTALLIZED_SUGAR_COOKIE_ORE, 3))
					.rangeUniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(64)).squared().count(80));

	public static ConfiguredFeature<?, ?> ORE_TELEPORT = register("ore_teleport",
			ModFeatures.TELEPORT_ORE.get().configured(new OreConfiguration(CustomFillerType.SUGAR, TELEPORT_ORE, 1))
					.rangeUniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(64)).squared().count(UniformInt.of(6, 10)));

	public static ConfiguredFeature<?, ?> ORE_SUGAR_SAND = register("ore_sugar_sand",
			Feature.ORE.configured(new OreConfiguration(CustomFillerType.SUGAR, SUGAR_SAND, 20))
					.rangeUniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(64)).squared().count(8));

	//Overworld
	public static ConfiguredFeature<?, ?> ORE_MILK_BROWNIE_OVERWORLD = register("ore_milk_brownie_overworld",
			Feature.ORE.configured(new OreConfiguration(Predicates.NATURAL_STONE, MILK_BROWNIE, 25))
					.decorated(FeatureDecorator.RANGE.configured(new RangeDecoratorConfiguration(UniformHeight.of(VerticalAnchor.aboveBottom(20), VerticalAnchor.belowTop(40))))
							.squared().count(1)));
	public static ConfiguredFeature<?, ?> ORE_WHITE_BROWNIE_OVERWORLD = register("ore_white_brownie_overworld",
			Feature.ORE.configured(new OreConfiguration(Predicates.NATURAL_STONE, WHITE_BROWNIE, 25))
					.decorated(FeatureDecorator.RANGE.configured(new RangeDecoratorConfiguration(UniformHeight.of(VerticalAnchor.aboveBottom(40), VerticalAnchor.belowTop(64))))
			.squared().count(1)));

	public static ConfiguredFeature<?, ?> ORE_DARK_BROWNIE_OVERWORLD = register("ore_milk_brownie_overworld",
			Feature.ORE.configured(new OreConfiguration(Predicates.NATURAL_STONE, DARK_BROWNIE, 25))
					.decorated(FeatureDecorator.RANGE.configured(new RangeDecoratorConfiguration(UniformHeight.of(VerticalAnchor.aboveBottom(0), VerticalAnchor.belowTop(25))))
			.squared().count(1)));

	public static ConfiguredFeature<?, ?> ORE_SUGAR_BLOCK = register("ore_sugar_block",
			Feature.ORE.configured(new OreConfiguration(Predicates.NATURAL_STONE, CRYSTALLIZED_SUGAR, 20))
					.decorated(FeatureDecorator.RANGE.configured(new RangeDecoratorConfiguration(UniformHeight.of(VerticalAnchor.aboveBottom(0), VerticalAnchor.belowTop(30))))
			.squared().count(2)));

	public static ConfiguredFeature<?, ?> ORE_COOKIE = register("ore_cookie",
			Feature.ORE.configured(new OreConfiguration(Predicates.NATURAL_STONE, COOKIE_ORE, 20))
					.decorated(FeatureDecorator.RANGE.configured(new RangeDecoratorConfiguration(UniformHeight.of(VerticalAnchor.aboveBottom(32), VerticalAnchor.belowTop(45))))
			.squared().count(50)));

	//General
	public static final ConfiguredFeature<?, ?> GUMMY_WORM = register("gummy_worm", ModFeatures.GUMMY_WORM.get()
			.configured(FeatureConfiguration.NONE).decorated(Features.Decorators.HEIGHTMAP_SQUARE).rarity(1));

	public static ConfiguredFeature<?, ?> PATCH_COTTON_CANDY = register("patch_cotton_candy",
			Feature.RANDOM_PATCH.configured(Configs.DEFAULT_COTTON_CANDY_CONFIG).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE)
					.decorated(FeatureDecorator.COUNT_NOISE.configured(new NoiseDependantDecoratorConfiguration(-0.8D, 5, 10))));

	public static final ConfiguredFeature<TreeConfiguration, ?> COTTON_CANDY = register("cotton_candy",
			ModFeatures.CANDY_TREE.get().configured((new TreeConfiguration.TreeConfigurationBuilder(
					new SimpleStateProvider(WHITE_CANDY_CANE_BLOCK),
					new CandyStraightTrunkPlacer(5, 2, 1),
					new SimpleStateProvider(COTTON_CANDY_LEAVES),
					new SimpleStateProvider(COTTON_CANDY_SAPLING),
					new CottonCandyFoliagePlacer(UniformInt.of(1, 2), UniformInt.of(0, 2),
							UniformInt.of(1, 1)),
					new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

	public static final ConfiguredFeature<?, ?> COTTON_CANDY_TREE = register("cotton_candy_tree",
			COTTON_CANDY.decorated(Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(3, 0.1F, 1))));

	public static ConfiguredFeature<?, ?> PATCH_CHOCOLATE_MUSHROOM = register("patch_chocolate_mushroom",
			Feature.RANDOM_PATCH.configured(Configs.DEFAULT_CHOCOLATE_MUSHROOM_CONFIG).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE)
					.decorated(FeatureDecorator.COUNT_NOISE.configured(new NoiseDependantDecoratorConfiguration(-0.8D, 5, 10))));

	public static ConfiguredFeature<?, ?> PATCH_CHOCOLATE_BAR = register("patch_chocolate_bar",
			ModFeatures.RANDOM_ROTATED_PATCH.get().configured(Configs.DEFAULT_CHOCOLATE_BAR_CONFIG).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE)
					.decorated(FeatureDecorator.COUNT_NOISE.configured(new NoiseDependantDecoratorConfiguration(-0.8D, 5, 10))));

	public static ConfiguredFeature<?, ?> PATCH_CAVE_CHOCOLATE_BAR = register("patch_cave_chocolate_bar",
			ModFeatures.RANDOM_ROTATED_PATCH.get().configured(Configs.DEFAULT_CAVE_CHOCOLATE_BAR_CONFIG).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE)
					.decorated(FeatureDecorator.COUNT_NOISE.configured(new NoiseDependantDecoratorConfiguration(-0.8D, 5, 10))));

	public static final ConfiguredFeature<TreeConfiguration, ?> CHOCOLATE = register("chocolate",
			ModFeatures.CANDY_TREE.get().configured((new TreeConfiguration.TreeConfigurationBuilder(
					new SimpleStateProvider(WAFER_STICK_BLOCK),
					new CandyStraightTrunkPlacer(5, 2, 1),
					new WeightedStateProvider(weightedBlockStateBuilder().add(MILK_CHOCOLATE_LEAVES, 1).add(WHITE_CHOCOLATE_LEAVES, 1).add(DARK_CHOCOLATE_LEAVES, 1)),
					new SimpleStateProvider(CHOCOLATE_SAPLING),
					new ChocolateFoliagePlacer(UniformInt.of(1, 2), UniformInt.of(0, 2),
							UniformInt.of(1, 1)),
					new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build()));

	public static final ConfiguredFeature<?, ?> CHOCOLATE_TREE = register("chocolate_tree",
			CHOCOLATE.decorated(Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(3, 0.1F, 1))));

	public static ConfiguredFeature<?, ?> PATCH_CAVE_CANDY_CANE = register("patch_cave_candy_cane",
			ModFeatures.CANDY_CANE.get().configured(Configs.DEFAULT_CANDY_CANE_CONFIG).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE)
					.decorated(FeatureDecorator.COUNT_NOISE.configured(new NoiseDependantDecoratorConfiguration(-0.8D, 5, 10))));

	public static final ConfiguredFeature<?, ?> SUGAR_SPIKE = register("sugar_spike",
			ModFeatures.SPIKE.get().configured(Configs.DEFAULT_SUGAR_CONFIG).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE));
	public static final ConfiguredFeature<?, ?> MILK_CHOCOLATE_SPIKE = register("milk_chocolate_spike",
			ModFeatures.SPIKE.get().configured(Configs.DEFAULT_MILK_CHOCOLATE_CONFIG).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE));
	public static final ConfiguredFeature<?, ?> CHOCOLATE_SPIKE = register("chocolate_spike",
			ModFeatures.SPIKE.get().configured(Configs.DEFAULT_CHOCOLATE_CONFIG).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE));

	public static final ConfiguredFeature<?, ?> LAKE_CHOCOLATE = register("lake_chocolate", ModFeatures.CANDY_LAKES.get().configured(new BlockStateConfiguration(LIQUID_CHOCOLATE)).range(Features.Decorators.FULL_RANGE).squared().rarity(4));
	public static final ConfiguredFeature<?, ?> LAKE_CANDY = register("lake_candy", ModFeatures.CANDY_LAKES.get().configured(new BlockStateConfiguration(LIQUID_CANDY)).decorated(FeatureDecorator.LAVA_LAKE.configured(new ChanceDecoratorConfiguration(80))).range(new RangeDecoratorConfiguration(BiasedToBottomHeight.of(VerticalAnchor.bottom(), VerticalAnchor.top(), 8))).squared().rarity(8));

	private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> feature) {
		return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(CandyWorld.MOD_ID, key), feature);
	}

	public static final class CustomFillerType {
		public static final RuleTest SUGAR = new TagMatchTest(ModTags.SUGAR);
	}

	public static final class Configs {
		public static final RandomPatchConfiguration DEFAULT_COTTON_CANDY_CONFIG = (new RandomPatchConfiguration.GrassConfigurationBuilder(
				new WeightedStateProvider(weightedBlockStateBuilder().add(COTTON_CANDY_PLANT, 1).add(COTTON_CANDY_BUSH, 2)), SimpleBlockPlacer.INSTANCE)).tries(10).build();
		public static final RandomPatchConfiguration DEFAULT_CHOCOLATE_MUSHROOM_CONFIG = (new RandomPatchConfiguration.GrassConfigurationBuilder(
				new WeightedStateProvider(weightedBlockStateBuilder().add(MILK_CHOCOLATE_MUSHROOM, 1).add(WHITE_CHOCOLATE_MUSHROOM, 1).add(DARK_CHOCOLATE_MUSHROOM, 1)),
				SimpleBlockPlacer.INSTANCE)).tries(40).build();
		public static final RandomPatchConfiguration DEFAULT_CHOCOLATE_BAR_CONFIG = (new RandomPatchConfiguration.GrassConfigurationBuilder(
				new WeightedStateProvider(weightedBlockStateBuilder().add(MILK_CHOCOLATE_BAR, 1).add(WHITE_CHOCOLATE_BAR, 1).add(DARK_CHOCOLATE_BAR, 1)),
				SimpleBlockPlacer.INSTANCE)).tries(20).build();
		public static final RandomPatchConfiguration DEFAULT_CAVE_CHOCOLATE_BAR_CONFIG = (new RandomPatchConfiguration.GrassConfigurationBuilder(
				new WeightedStateProvider(weightedBlockStateBuilder().add(MILK_CHOCOLATE_BAR, 1).add(WHITE_CHOCOLATE_BAR, 1).add(DARK_CHOCOLATE_BAR, 1)),
				SimpleBlockPlacer.INSTANCE)).tries(20).noProjection().build();

		public static final RandomPatchConfiguration DEFAULT_CANDY_CANE_CONFIG = (new RandomPatchConfiguration.GrassConfigurationBuilder(
				new WeightedStateProvider(weightedBlockStateBuilder().add(WHITE_RED_CANDY_CANE_BLOCK, 1).add(WHITE_GREEN_CANDY_CANE_BLOCK, 2)),
				new ColumnPlacer(UniformInt.of(1, 16)))).tries(16).noProjection().build();

		//Candy Plains
		public static final SpikeFeatureConfig DEFAULT_SUGAR_CONFIG = (new SpikeFeatureConfig.Builder(
				new SimpleStateProvider(CRYSTALLIZED_SUGAR))).minLength(3).maxLength(8).chance(8)
				.whitelist(ImmutableSet.of(CANDY_GRASS_BLOCK.getBlock(), MILK_BROWNIE.getBlock())).build();
		public static final SpikeFeatureConfig DEFAULT_MILK_CHOCOLATE_CONFIG = (new SpikeFeatureConfig.Builder(
				new SimpleStateProvider(MILK_CHOCOLATE_BLOCK))).minLength(5).maxLength(12).chance(24)
				.whitelist(ImmutableSet.of(CANDY_GRASS_BLOCK.getBlock(), MILK_BROWNIE.getBlock())).build();

		//Chocolate Forest
		public static final SpikeFeatureConfig DEFAULT_CHOCOLATE_CONFIG = (new SpikeFeatureConfig.Builder(
				new WeightedStateProvider(weightedBlockStateBuilder().add(MILK_CHOCOLATE_BLOCK, 1).add(WHITE_CHOCOLATE_BLOCK, 1).add(DARK_CHOCOLATE_BLOCK, 1))))
				.minLength(3).maxLength(24).chance(16).whitelist(ImmutableSet.of(CHOCOLATE_COVERED_WHITE_BROWNIE.getBlock(), WHITE_BROWNIE.getBlock())).build();
	}

	static SimpleWeightedRandomList.Builder<BlockState> weightedBlockStateBuilder() {
		return SimpleWeightedRandomList.builder();
	}
}
