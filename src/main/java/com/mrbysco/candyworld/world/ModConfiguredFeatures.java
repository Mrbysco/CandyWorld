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
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
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
	//Candy world
	public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_MILK_BROWNIE = createKey("ore_milk_brownie");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_WHITE_BROWNIE = createKey("ore_white_brownie");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_DARK_BROWNIE = createKey("ore_dark_brownie");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SUGAR_COOKIE = createKey("ore_sugar_cookie");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_TELEPORT = createKey("ore_teleport");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SUGAR_SAND = createKey("ore_sugar_sand");

	//Overworld
	public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_MILK_BROWNIE_OVERWORLD = createKey("ore_milk_brownie_overworld");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_WHITE_BROWNIE_OVERWORLD = createKey("ore_white_brownie_overworld");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_DARK_BROWNIE_OVERWORLD = createKey("ore_dark_brownie_overworld");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SUGAR_BLOCK = createKey("ore_sugar_block");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_COOKIE = createKey("ore_cookie");

	//General
	public static final ResourceKey<ConfiguredFeature<?, ?>> GUMMY_WORM = createKey("gummy_worm");
	public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_COTTON_CANDY = createKey("patch_cotton_candy");
	public static final ResourceKey<ConfiguredFeature<?, ?>> COTTON_CANDY_TREE = createKey("cotton_candy_tree");
	public static final ResourceKey<ConfiguredFeature<?, ?>> CHOCOLATE_TREE = createKey("chocolate_tree");

	public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_CHOCOLATE_MUSHROOM = createKey("patch_chocolate_mushroom");
	public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_CHOCOLATE_BAR = createKey("patch_chocolate_bar");
	public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_CAVE_CHOCOLATE_BAR = createKey("patch_cave_chocolate_bar");
	public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_CAVE_CANDY_CANE = createKey("patch_cave_candy_cane");

	public static final ResourceKey<ConfiguredFeature<?, ?>> SUGAR_SPIKE = createKey("sugar_spike");
	public static final ResourceKey<ConfiguredFeature<?, ?>> MILK_CHOCOLATE_SPIKE = createKey("milk_chocolate_spike");
	public static final ResourceKey<ConfiguredFeature<?, ?>> CHOCOLATE_SPIKE = createKey("chocolate_spike");

	public static final ResourceKey<ConfiguredFeature<?, ?>> LAKE_CHOCOLATE = createKey("lake_chocolate");
	public static final ResourceKey<ConfiguredFeature<?, ?>> LAKE_CANDY = createKey("lake_candy");

	public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
		return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(CandyWorld.MOD_ID, name));
	}

	public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
		RuleTest stoneRuleTest = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
		RuleTest sugarRuleTest = new TagMatchTest(ModTags.SUGAR);

		FeatureUtils.register(context, ORE_MILK_BROWNIE, Feature.ORE, new OreConfiguration(sugarRuleTest, ModBlocks.MILK_BROWNIE_BLOCK.get().defaultBlockState(), 25));
		FeatureUtils.register(context, ORE_WHITE_BROWNIE, Feature.ORE, new OreConfiguration(sugarRuleTest, ModBlocks.WHITE_BROWNIE_BLOCK.get().defaultBlockState(), 25));
		FeatureUtils.register(context, ORE_DARK_BROWNIE, Feature.ORE, new OreConfiguration(sugarRuleTest, ModBlocks.DARK_BROWNIE_BLOCK.get().defaultBlockState(), 25));
		FeatureUtils.register(context, ORE_SUGAR_COOKIE, Feature.ORE, new OreConfiguration(sugarRuleTest, ModBlocks.CRYSTALLIZED_SUGAR_COOKIE_ORE.get().defaultBlockState(), 3));
		FeatureUtils.register(context, ORE_TELEPORT, ModFeatures.TELEPORT_ORE.get(), new OreConfiguration(sugarRuleTest, ModBlocks.TELEPORTER_ORE.get().defaultBlockState(), 1));
		FeatureUtils.register(context, ORE_SUGAR_SAND, Feature.ORE, new OreConfiguration(sugarRuleTest, ModBlocks.SUGAR_SAND.get().defaultBlockState(), 20));

		FeatureUtils.register(context, ORE_MILK_BROWNIE_OVERWORLD, Feature.ORE, new OreConfiguration(stoneRuleTest, ModBlocks.MILK_BROWNIE_BLOCK.get().defaultBlockState(), 25));
		FeatureUtils.register(context, ORE_WHITE_BROWNIE_OVERWORLD, Feature.ORE, new OreConfiguration(stoneRuleTest, ModBlocks.WHITE_BROWNIE_BLOCK.get().defaultBlockState(), 25));
		FeatureUtils.register(context, ORE_DARK_BROWNIE_OVERWORLD, Feature.ORE, new OreConfiguration(stoneRuleTest, ModBlocks.DARK_BROWNIE_BLOCK.get().defaultBlockState(), 25));
		FeatureUtils.register(context, ORE_SUGAR_BLOCK, Feature.ORE, new OreConfiguration(stoneRuleTest, ModBlocks.CRYSTALLIZED_SUGAR.get().defaultBlockState(), 20));
		FeatureUtils.register(context, ORE_COOKIE, Feature.ORE, new OreConfiguration(stoneRuleTest, ModBlocks.COOKIE_ORE.get().defaultBlockState(), 20));

		FeatureUtils.register(context, GUMMY_WORM, ModFeatures.GUMMY_WORM.get(), FeatureConfiguration.NONE);
		FeatureUtils.register(context, PATCH_COTTON_CANDY, Feature.RANDOM_PATCH, grassPatch(
				new WeightedStateProvider(weightedBlockStateBuilder()
						.add(ModBlocks.COTTON_CANDY_PLANT.get().defaultBlockState(), 1)
						.add(ModBlocks.COTTON_CANDY_BUSH.get().defaultBlockState(), 2)), 10));

		FeatureUtils.register(context, COTTON_CANDY_TREE, ModFeatures.CANDY_TREE.get(), (new TreeConfiguration.TreeConfigurationBuilder(
				BlockStateProvider.simple(ModBlocks.WHITE_CANDY_CANE_BLOCK.get().defaultBlockState()),
				new CandyStraightTrunkPlacer(5, 2, 1),
				BlockStateProvider.simple(ModBlocks.COTTON_CANDY_LEAVES.get().defaultBlockState()),
				new CottonCandyFoliagePlacer(UniformInt.of(1, 2), UniformInt.of(0, 2),
						UniformInt.of(1, 1)),
				new TwoLayersFeatureSize(1, 0, 1)))
				.dirt(BlockStateProvider.simple(ModBlocks.MILK_BROWNIE_BLOCK.get().defaultBlockState())).ignoreVines().build());

		FeatureUtils.register(context, CHOCOLATE_TREE, ModFeatures.CANDY_TREE.get(), (new TreeConfiguration.TreeConfigurationBuilder(
				BlockStateProvider.simple(ModBlocks.WAFER_STICK_BLOCK.get().defaultBlockState()),
				new CandyStraightTrunkPlacer(5, 2, 1),
				new WeightedStateProvider(weightedBlockStateBuilder()
						.add(ModBlocks.MILK_CHOCOLATE_LEAVES.get().defaultBlockState(), 1)
						.add(ModBlocks.WHITE_CHOCOLATE_LEAVES.get().defaultBlockState(), 1)
						.add(ModBlocks.DARK_CHOCOLATE_LEAVES.get().defaultBlockState(), 1)),
				new ChocolateFoliagePlacer(UniformInt.of(1, 2), UniformInt.of(0, 2),
						UniformInt.of(1, 1)),
				new TwoLayersFeatureSize(1, 0, 1)))
				.dirt(BlockStateProvider.simple(ModBlocks.DARK_BROWNIE_BLOCK.get().defaultBlockState())).ignoreVines().build());

		FeatureUtils.register(context, PATCH_CHOCOLATE_MUSHROOM, Feature.RANDOM_PATCH, grassPatch(
				new WeightedStateProvider(weightedBlockStateBuilder()
						.add(ModBlocks.MILK_CHOCOLATE_MUSHROOM.get().defaultBlockState(), 1)
						.add(ModBlocks.WHITE_CHOCOLATE_MUSHROOM.get().defaultBlockState(), 1)
						.add(ModBlocks.DARK_CHOCOLATE_MUSHROOM.get().defaultBlockState(), 1)), 40));
		FeatureUtils.register(context, PATCH_CHOCOLATE_BAR, Feature.RANDOM_PATCH, grassPatch(
				new WeightedStateProvider(getHorizontalWeightedList(List.of(
						ModBlocks.MILK_CHOCOLATE_BAR_BLOCK.get().defaultBlockState(),
						ModBlocks.WHITE_CHOCOLATE_BAR_BLOCK.get().defaultBlockState(),
						ModBlocks.DARK_CHOCOLATE_BAR_BLOCK.get().defaultBlockState()))),
				20));
		FeatureUtils.register(context, PATCH_CAVE_CHOCOLATE_BAR, Feature.RANDOM_PATCH, grassPatch(
				new WeightedStateProvider(getHorizontalWeightedList(List.of(
						ModBlocks.MILK_CHOCOLATE_BAR_BLOCK.get().defaultBlockState(),
						ModBlocks.WHITE_CHOCOLATE_BAR_BLOCK.get().defaultBlockState(),
						ModBlocks.DARK_CHOCOLATE_BAR_BLOCK.get().defaultBlockState()))),
				20));
		FeatureUtils.register(context, PATCH_CAVE_CANDY_CANE, ModFeatures.CANDY_CANE.get(), FeatureUtils.simpleRandomPatchConfiguration(16,
				PlacementUtils.inlinePlaced(Feature.BLOCK_COLUMN, BlockColumnConfiguration.simple(BiasedToBottomInt.of(1, 16),
								new WeightedStateProvider(weightedBlockStateBuilder()
										.add(ModBlocks.WHITE_RED_CANDY_CANE_BLOCK.get().defaultBlockState(), 1)
										.add(ModBlocks.WHITE_GREEN_CANDY_CANE_BLOCK.get().defaultBlockState(), 2))),
						BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
								BlockPredicate.wouldSurvive(ModBlocks.WHITE_RED_CANDY_CANE_BLOCK.get().defaultBlockState(), BlockPos.ZERO))))));

		FeatureUtils.register(context, SUGAR_SPIKE, ModFeatures.SPIKE.get(), (new SpikeFeatureConfig.Builder(
				BlockStateProvider.simple(ModBlocks.CRYSTALLIZED_SUGAR.get().defaultBlockState()))).minLength(3).maxLength(8).chance(8)
				.whitelist(ImmutableSet.of(ModBlocks.CANDY_GRASS_BLOCK.get(), ModBlocks.MILK_BROWNIE_BLOCK.get())).build());
		FeatureUtils.register(context, MILK_CHOCOLATE_SPIKE, ModFeatures.SPIKE.get(), (new SpikeFeatureConfig.Builder(
				BlockStateProvider.simple(ModBlocks.MILK_CHOCOLATE_BLOCK.get().defaultBlockState()))).minLength(5).maxLength(12).chance(24)
				.whitelist(ImmutableSet.of(ModBlocks.CANDY_GRASS_BLOCK.get(), ModBlocks.MILK_BROWNIE_BLOCK.get())).build());
		FeatureUtils.register(context, CHOCOLATE_SPIKE, ModFeatures.SPIKE.get(), (new SpikeFeatureConfig.Builder(
				new WeightedStateProvider(weightedBlockStateBuilder()
						.add(ModBlocks.MILK_CHOCOLATE_BLOCK.get().defaultBlockState(), 1)
						.add(ModBlocks.WHITE_CHOCOLATE_BLOCK.get().defaultBlockState(), 1)
						.add(ModBlocks.DARK_CHOCOLATE_BLOCK.get().defaultBlockState(), 1))))
				.minLength(3).maxLength(24).chance(16).whitelist(ImmutableSet.of(
						ModBlocks.CHOCOLATE_COVERED_WHITE_BROWNIE.get(),
						ModBlocks.WHITE_BROWNIE_BLOCK.get())
				).build());

		FeatureUtils.register(context, LAKE_CHOCOLATE, Feature.LAKE, new LakeFeature.Configuration(
				BlockStateProvider.simple(ModBlocks.LIQUID_CHOCOLATE_BLOCK.get().defaultBlockState()),
				BlockStateProvider.simple(ModBlocks.DARK_CHOCOLATE_BLOCK.get().defaultBlockState())
		));
		FeatureUtils.register(context, LAKE_CANDY, Feature.LAKE, new LakeFeature.Configuration(
				BlockStateProvider.simple(ModBlocks.LIQUID_CANDY_BLOCK.get().defaultBlockState()),
				BlockStateProvider.simple(ModBlocks.CRYSTALLIZED_SUGAR.get().defaultBlockState())
		));



	}

	static RandomPatchConfiguration grassPatch(BlockStateProvider stateProvider, int tries) {
		return FeatureUtils.simpleRandomPatchConfiguration(tries, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(stateProvider)));
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
