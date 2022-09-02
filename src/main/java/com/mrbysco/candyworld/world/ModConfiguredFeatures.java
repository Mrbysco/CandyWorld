package com.mrbysco.candyworld.world;

import com.google.common.collect.ImmutableSet;
import com.mrbysco.candyworld.CandyWorld;
import com.mrbysco.candyworld.registry.ModBlocks;
import com.mrbysco.candyworld.registry.ModTags;
import com.mrbysco.candyworld.world.feature.config.SpikeFeatureConfig;
import com.mrbysco.candyworld.world.tree.placer.ChocolateFoliagePlacer;
import com.mrbysco.candyworld.world.tree.placer.CottonCandyFoliagePlacer;
import com.mrbysco.candyworld.world.tree.trunkplacers.CandyStraightTrunkPlacer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.BiasedToBottomInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockColumnConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
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
	public static Holder<ConfiguredFeature<OreConfiguration, ?>> ORE_MILK_BROWNIE = register("ore_milk_brownie",
			Feature.ORE, new OreConfiguration(CustomFillerType.SUGAR, MILK_BROWNIE, 25));

	public static Holder<ConfiguredFeature<OreConfiguration, ?>> ORE_WHITE_BROWNIE = register("ore_white_brownie",
			Feature.ORE, new OreConfiguration(CustomFillerType.SUGAR, WHITE_BROWNIE, 25));

	public static Holder<ConfiguredFeature<OreConfiguration, ?>> ORE_DARK_BROWNIE = register("ore_milk_brownie",
			Feature.ORE, new OreConfiguration(CustomFillerType.SUGAR, DARK_BROWNIE, 25));

	public static Holder<ConfiguredFeature<OreConfiguration, ?>> ORE_SUGAR_COOKIE = register("ore_sugar_cookie",
			Feature.ORE, new OreConfiguration(CustomFillerType.SUGAR, CRYSTALLIZED_SUGAR_COOKIE_ORE, 3));

	public static Holder<ConfiguredFeature<OreConfiguration, ?>> ORE_TELEPORT = register("ore_teleport",
			ModFeatures.TELEPORT_ORE.get(), new OreConfiguration(CustomFillerType.SUGAR, TELEPORT_ORE, 1));

	public static Holder<ConfiguredFeature<OreConfiguration, ?>> ORE_SUGAR_SAND = register("ore_sugar_sand",
			Feature.ORE, new OreConfiguration(CustomFillerType.SUGAR, SUGAR_SAND, 20));

	//Overworld
	public static Holder<ConfiguredFeature<OreConfiguration, ?>> ORE_MILK_BROWNIE_OVERWORLD = register("ore_milk_brownie_overworld",
			Feature.ORE, new OreConfiguration(OreFeatures.NATURAL_STONE, MILK_BROWNIE, 25));
	public static Holder<ConfiguredFeature<OreConfiguration, ?>> ORE_WHITE_BROWNIE_OVERWORLD = register("ore_white_brownie_overworld",
			Feature.ORE, new OreConfiguration(OreFeatures.NATURAL_STONE, WHITE_BROWNIE, 25));

	public static Holder<ConfiguredFeature<OreConfiguration, ?>> ORE_DARK_BROWNIE_OVERWORLD = register("ore_milk_brownie_overworld",
			Feature.ORE, new OreConfiguration(OreFeatures.NATURAL_STONE, DARK_BROWNIE, 25));

	public static Holder<ConfiguredFeature<OreConfiguration, ?>> ORE_SUGAR_BLOCK = register("ore_sugar_block",
			Feature.ORE, new OreConfiguration(OreFeatures.NATURAL_STONE, CRYSTALLIZED_SUGAR, 20));

	public static Holder<ConfiguredFeature<OreConfiguration, ?>> ORE_COOKIE = register("ore_cookie",
			Feature.ORE, new OreConfiguration(OreFeatures.NATURAL_STONE, COOKIE_ORE, 20));

	//General
	public static final Holder<? extends ConfiguredFeature<?, ?>> GUMMY_WORM = register("gummy_worm", ModFeatures.GUMMY_WORM.get()
			, FeatureConfiguration.NONE);

	public static Holder<? extends ConfiguredFeature<?, ?>> PATCH_COTTON_CANDY = register("patch_cotton_candy",
			Feature.RANDOM_PATCH, Configs.DEFAULT_COTTON_CANDY_CONFIG);

	public static final Holder<? extends ConfiguredFeature<?, ?>> COTTON_CANDY_TREE = register("cotton_candy",
			ModFeatures.CANDY_TREE.get(), (new TreeConfiguration.TreeConfigurationBuilder(
					BlockStateProvider.simple(WHITE_CANDY_CANE_BLOCK),
					new CandyStraightTrunkPlacer(5, 2, 1),
					BlockStateProvider.simple(COTTON_CANDY_LEAVES),
					new CottonCandyFoliagePlacer(UniformInt.of(1, 2), UniformInt.of(0, 2),
							UniformInt.of(1, 1)),
					new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build());

	public static Holder<? extends ConfiguredFeature<?, ?>> PATCH_CHOCOLATE_MUSHROOM = register("patch_chocolate_mushroom",
			Feature.RANDOM_PATCH, Configs.DEFAULT_CHOCOLATE_MUSHROOM_CONFIG);

	public static Holder<? extends ConfiguredFeature<?, ?>> PATCH_CHOCOLATE_BAR = register("patch_chocolate_bar",
			Feature.RANDOM_PATCH, Configs.DEFAULT_CHOCOLATE_BAR_CONFIG);

	public static Holder<? extends ConfiguredFeature<?, ?>> PATCH_CAVE_CHOCOLATE_BAR = register("patch_cave_chocolate_bar",
			Feature.RANDOM_PATCH, Configs.DEFAULT_CAVE_CHOCOLATE_BAR_CONFIG);

	public static final Holder<? extends ConfiguredFeature<?, ?>> CHOCOLATE_TREE = register("chocolate",
			ModFeatures.CANDY_TREE.get(), (new TreeConfiguration.TreeConfigurationBuilder(
					BlockStateProvider.simple(WAFER_STICK_BLOCK),
					new CandyStraightTrunkPlacer(5, 2, 1),
					new WeightedStateProvider(weightedBlockStateBuilder().add(MILK_CHOCOLATE_LEAVES, 1).add(WHITE_CHOCOLATE_LEAVES, 1).add(DARK_CHOCOLATE_LEAVES, 1)),
					new ChocolateFoliagePlacer(UniformInt.of(1, 2), UniformInt.of(0, 2),
							UniformInt.of(1, 1)),
					new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build());

	public static Holder<? extends ConfiguredFeature<?, ?>> PATCH_CAVE_CANDY_CANE = register("patch_cave_candy_cane",
			ModFeatures.CANDY_CANE.get(), Configs.DEFAULT_CANDY_CANE_CONFIG);

	public static final Holder<? extends ConfiguredFeature<?, ?>> SUGAR_SPIKE = register("sugar_spike",
			ModFeatures.SPIKE.get(), Configs.DEFAULT_SUGAR_CONFIG);
	public static final Holder<? extends ConfiguredFeature<?, ?>> MILK_CHOCOLATE_SPIKE = register("milk_chocolate_spike",
			ModFeatures.SPIKE.get(), Configs.DEFAULT_MILK_CHOCOLATE_CONFIG);
	public static final Holder<? extends ConfiguredFeature<?, ?>> CHOCOLATE_SPIKE = register("chocolate_spike",
			ModFeatures.SPIKE.get(), Configs.DEFAULT_CHOCOLATE_CONFIG);

	public static final Holder<ConfiguredFeature<LakeFeature.Configuration, ?>> LAKE_CHOCOLATE = FeatureUtils.register("lake_chocolate", Feature.LAKE,
			new LakeFeature.Configuration(BlockStateProvider.simple(LIQUID_CHOCOLATE), BlockStateProvider.simple(DARK_CHOCOLATE_BLOCK)));

	public static final Holder<ConfiguredFeature<LakeFeature.Configuration, ?>> LAKE_CANDY = FeatureUtils.register("lake_candy", Feature.LAKE,
			new LakeFeature.Configuration(BlockStateProvider.simple(LIQUID_CANDY), BlockStateProvider.simple(CRYSTALLIZED_SUGAR)));

	public static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<FC, ?>> register(String key, F feature, FC featureConfig) {
		return BuiltinRegistries.registerExact(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(CandyWorld.MOD_ID, key).toString(),
				new ConfiguredFeature<>(feature, featureConfig));
	}

	public static final class CustomFillerType {
		public static final RuleTest SUGAR = new TagMatchTest(ModTags.SUGAR);
	}

	public static final class Configs {
		public static final RandomPatchConfiguration DEFAULT_COTTON_CANDY_CONFIG = grassPatch(
				new WeightedStateProvider(weightedBlockStateBuilder().add(COTTON_CANDY_PLANT, 1).add(COTTON_CANDY_BUSH, 2)), 10);

		public static final RandomPatchConfiguration DEFAULT_CHOCOLATE_MUSHROOM_CONFIG = grassPatch(
				new WeightedStateProvider(weightedBlockStateBuilder().add(MILK_CHOCOLATE_MUSHROOM, 1).add(WHITE_CHOCOLATE_MUSHROOM, 1)
						.add(DARK_CHOCOLATE_MUSHROOM, 1)), 40);
		public static final RandomPatchConfiguration DEFAULT_CHOCOLATE_BAR_CONFIG = grassPatch(
				new WeightedStateProvider(getHorizontalWeightedList(List.of(MILK_CHOCOLATE_BAR, WHITE_CHOCOLATE_BAR, DARK_CHOCOLATE_BAR))),
				20);
		public static final RandomPatchConfiguration DEFAULT_CAVE_CHOCOLATE_BAR_CONFIG = grassPatch(
				new WeightedStateProvider(getHorizontalWeightedList(List.of(MILK_CHOCOLATE_BAR, WHITE_CHOCOLATE_BAR, DARK_CHOCOLATE_BAR))),
				20);

		public static final RandomPatchConfiguration DEFAULT_CANDY_CANE_CONFIG = FeatureUtils.simpleRandomPatchConfiguration(16,
				PlacementUtils.inlinePlaced(Feature.BLOCK_COLUMN, BlockColumnConfiguration.simple(BiasedToBottomInt.of(1, 16),
								new WeightedStateProvider(weightedBlockStateBuilder().add(WHITE_RED_CANDY_CANE_BLOCK, 1).add(WHITE_GREEN_CANDY_CANE_BLOCK, 2))),
						BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.wouldSurvive(WHITE_RED_CANDY_CANE_BLOCK, BlockPos.ZERO)))));

		//Candy Plains
		public static final SpikeFeatureConfig DEFAULT_SUGAR_CONFIG = (new SpikeFeatureConfig.Builder(
				BlockStateProvider.simple(CRYSTALLIZED_SUGAR))).minLength(3).maxLength(8).chance(8)
				.whitelist(ImmutableSet.of(CANDY_GRASS_BLOCK.getBlock(), MILK_BROWNIE.getBlock())).build();
		public static final SpikeFeatureConfig DEFAULT_MILK_CHOCOLATE_CONFIG = (new SpikeFeatureConfig.Builder(
				BlockStateProvider.simple(MILK_CHOCOLATE_BLOCK))).minLength(5).maxLength(12).chance(24)
				.whitelist(ImmutableSet.of(CANDY_GRASS_BLOCK.getBlock(), MILK_BROWNIE.getBlock())).build();

		//Chocolate Forest
		public static final SpikeFeatureConfig DEFAULT_CHOCOLATE_CONFIG = (new SpikeFeatureConfig.Builder(
				new WeightedStateProvider(weightedBlockStateBuilder().add(MILK_CHOCOLATE_BLOCK, 1).add(WHITE_CHOCOLATE_BLOCK, 1).add(DARK_CHOCOLATE_BLOCK, 1))))
				.minLength(3).maxLength(24).chance(16).whitelist(ImmutableSet.of(CHOCOLATE_COVERED_WHITE_BROWNIE.getBlock(), WHITE_BROWNIE.getBlock())).build();

		private static RandomPatchConfiguration grassPatch(BlockStateProvider stateProvider, int tries) {
			return FeatureUtils.simpleRandomPatchConfiguration(tries, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(stateProvider)));
		}
	}


	static SimpleWeightedRandomList.Builder<BlockState> getHorizontalWeightedList(List<BlockState> states) {
		SimpleWeightedRandomList.Builder<BlockState> builder = weightedBlockStateBuilder();
		for (BlockState state : states) {
			if (state.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
				BlockStateProperties.HORIZONTAL_FACING.getAllValues().forEach(direction -> builder.add(state.setValue(BlockStateProperties.HORIZONTAL_FACING, direction.value()), 1));
			}
		}

		return builder;
	}

	static SimpleWeightedRandomList.Builder<BlockState> weightedBlockStateBuilder() {
		return SimpleWeightedRandomList.builder();
	}
}
