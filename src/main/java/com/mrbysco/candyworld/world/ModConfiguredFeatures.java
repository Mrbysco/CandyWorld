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
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
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
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModConfiguredFeatures {
	public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, CandyWorld.MOD_ID);


	//Candy world
	public static RegistryObject<ConfiguredFeature<OreConfiguration, ?>> ORE_MILK_BROWNIE = CONFIGURED_FEATURES.register("ore_milk_brownie",
			() -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(CustomFillerType.SUGAR, ModBlocks.MILK_BROWNIE_BLOCK.get().defaultBlockState(), 25)));

	public static RegistryObject<ConfiguredFeature<OreConfiguration, ?>> ORE_WHITE_BROWNIE = CONFIGURED_FEATURES.register("ore_white_brownie",
			() -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(CustomFillerType.SUGAR, ModBlocks.WHITE_BROWNIE_BLOCK.get().defaultBlockState(), 25)));

	public static RegistryObject<ConfiguredFeature<OreConfiguration, ?>> ORE_DARK_BROWNIE = CONFIGURED_FEATURES.register("ore_dark_brownie",
			() -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(CustomFillerType.SUGAR, ModBlocks.DARK_BROWNIE_BLOCK.get().defaultBlockState(), 25)));

	public static RegistryObject<ConfiguredFeature<OreConfiguration, ?>> ORE_SUGAR_COOKIE = CONFIGURED_FEATURES.register("ore_sugar_cookie",
			() -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(CustomFillerType.SUGAR, ModBlocks.CRYSTALLIZED_SUGAR_COOKIE_ORE.get().defaultBlockState(), 3)));

	public static RegistryObject<ConfiguredFeature<OreConfiguration, ?>> ORE_TELEPORT = CONFIGURED_FEATURES.register("ore_teleport",
			() -> new ConfiguredFeature<>(ModFeatures.TELEPORT_ORE.get(), new OreConfiguration(CustomFillerType.SUGAR, ModBlocks.TELEPORTER_ORE.get().defaultBlockState(), 1)));

	public static RegistryObject<ConfiguredFeature<OreConfiguration, ?>> ORE_SUGAR_SAND = CONFIGURED_FEATURES.register("ore_sugar_sand",
			() -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(CustomFillerType.SUGAR, ModBlocks.SUGAR_SAND.get().defaultBlockState(), 20)));

	//Overworld
	public static RegistryObject<ConfiguredFeature<OreConfiguration, ?>> ORE_MILK_BROWNIE_OVERWORLD = CONFIGURED_FEATURES.register("ore_milk_brownie_overworld",
			() -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OreFeatures.NATURAL_STONE, ModBlocks.MILK_BROWNIE_BLOCK.get().defaultBlockState(), 25)));
	public static RegistryObject<ConfiguredFeature<OreConfiguration, ?>> ORE_WHITE_BROWNIE_OVERWORLD = CONFIGURED_FEATURES.register("ore_white_brownie_overworld",
			() -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OreFeatures.NATURAL_STONE, ModBlocks.WHITE_BROWNIE_BLOCK.get().defaultBlockState(), 25)));

	public static RegistryObject<ConfiguredFeature<OreConfiguration, ?>> ORE_DARK_BROWNIE_OVERWORLD = CONFIGURED_FEATURES.register("ore_dark_brownie_overworld",
			() -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OreFeatures.NATURAL_STONE, ModBlocks.DARK_BROWNIE_BLOCK.get().defaultBlockState(), 25)));

	public static RegistryObject<ConfiguredFeature<OreConfiguration, ?>> ORE_SUGAR_BLOCK = CONFIGURED_FEATURES.register("ore_sugar_block",
			() -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OreFeatures.NATURAL_STONE, ModBlocks.CRYSTALLIZED_SUGAR.get().defaultBlockState(), 20)));

	public static RegistryObject<ConfiguredFeature<OreConfiguration, ?>> ORE_COOKIE = CONFIGURED_FEATURES.register("ore_cookie",
			() -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OreFeatures.NATURAL_STONE, ModBlocks.COOKIE_ORE.get().defaultBlockState(), 20)));

	//General
	public static final RegistryObject<ConfiguredFeature<?, ?>> GUMMY_WORM = CONFIGURED_FEATURES.register("gummy_worm",
			() -> new ConfiguredFeature<>(ModFeatures.GUMMY_WORM.get(), FeatureConfiguration.NONE));

	public static RegistryObject<ConfiguredFeature<?, ?>> PATCH_COTTON_CANDY = CONFIGURED_FEATURES.register("patch_cotton_candy",
			() -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, Configs.DEFAULT_COTTON_CANDY_CONFIG));

	public static final RegistryObject<ConfiguredFeature<?, ?>> COTTON_CANDY_TREE = CONFIGURED_FEATURES.register("cotton_candy_tree",
			() -> new ConfiguredFeature<>(ModFeatures.CANDY_TREE.get(), (new TreeConfiguration.TreeConfigurationBuilder(
					BlockStateProvider.simple(ModBlocks.WHITE_CANDY_CANE_BLOCK.get().defaultBlockState()),
					new CandyStraightTrunkPlacer(5, 2, 1),
					BlockStateProvider.simple(ModBlocks.COTTON_CANDY_LEAVES.get().defaultBlockState()),
					new CottonCandyFoliagePlacer(UniformInt.of(1, 2), UniformInt.of(0, 2),
							UniformInt.of(1, 1)),
					new TwoLayersFeatureSize(1, 0, 1)))
					.dirt(BlockStateProvider.simple(ModBlocks.MILK_BROWNIE_BLOCK.get().defaultBlockState())).ignoreVines().build()));

	public static RegistryObject<ConfiguredFeature<?, ?>> PATCH_CHOCOLATE_MUSHROOM = CONFIGURED_FEATURES.register("patch_chocolate_mushroom",
			() -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, Configs.DEFAULT_CHOCOLATE_MUSHROOM_CONFIG));

	public static RegistryObject<ConfiguredFeature<?, ?>> PATCH_CHOCOLATE_BAR = CONFIGURED_FEATURES.register("patch_chocolate_bar",
			() -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, Configs.DEFAULT_CHOCOLATE_BAR_CONFIG));

	public static RegistryObject<ConfiguredFeature<?, ?>> PATCH_CAVE_CHOCOLATE_BAR = CONFIGURED_FEATURES.register("patch_cave_chocolate_bar",
			() -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, Configs.DEFAULT_CAVE_CHOCOLATE_BAR_CONFIG));

	public static final RegistryObject<ConfiguredFeature<?, ?>> CHOCOLATE_TREE = CONFIGURED_FEATURES.register("chocolate_tree",
			() -> new ConfiguredFeature<>(ModFeatures.CANDY_TREE.get(), (new TreeConfiguration.TreeConfigurationBuilder(
					BlockStateProvider.simple(ModBlocks.WAFER_STICK_BLOCK.get().defaultBlockState()),
					new CandyStraightTrunkPlacer(5, 2, 1),
					new WeightedStateProvider(weightedBlockStateBuilder()
							.add(ModBlocks.MILK_CHOCOLATE_LEAVES.get().defaultBlockState(), 1)
							.add(ModBlocks.WHITE_CHOCOLATE_LEAVES.get().defaultBlockState(), 1)
							.add(ModBlocks.DARK_CHOCOLATE_LEAVES.get().defaultBlockState(), 1)),
					new ChocolateFoliagePlacer(UniformInt.of(1, 2), UniformInt.of(0, 2),
							UniformInt.of(1, 1)),
					new TwoLayersFeatureSize(1, 0, 1)))
					.dirt(BlockStateProvider.simple(ModBlocks.DARK_BROWNIE_BLOCK.get().defaultBlockState())).ignoreVines().build()));

	public static RegistryObject<ConfiguredFeature<?, ?>> PATCH_CAVE_CANDY_CANE = CONFIGURED_FEATURES.register("patch_cave_candy_cane",
			() -> new ConfiguredFeature<>(ModFeatures.CANDY_CANE.get(), Configs.DEFAULT_CANDY_CANE_CONFIG));

	public static final RegistryObject<ConfiguredFeature<?, ?>> SUGAR_SPIKE = CONFIGURED_FEATURES.register("sugar_spike",
			() -> new ConfiguredFeature<>(ModFeatures.SPIKE.get(), Configs.DEFAULT_SUGAR_CONFIG));
	public static final RegistryObject<ConfiguredFeature<?, ?>> MILK_CHOCOLATE_SPIKE = CONFIGURED_FEATURES.register("milk_chocolate_spike",
			() -> new ConfiguredFeature<>(ModFeatures.SPIKE.get(), Configs.DEFAULT_MILK_CHOCOLATE_CONFIG));
	public static final RegistryObject<ConfiguredFeature<?, ?>> CHOCOLATE_SPIKE = CONFIGURED_FEATURES.register("chocolate_spike",
			() -> new ConfiguredFeature<>(ModFeatures.SPIKE.get(), Configs.DEFAULT_CHOCOLATE_CONFIG));

	public static final RegistryObject<ConfiguredFeature<LakeFeature.Configuration, ?>> LAKE_CHOCOLATE = CONFIGURED_FEATURES.register("lake_chocolate",
			() -> new ConfiguredFeature<>(Feature.LAKE,
					new LakeFeature.Configuration(BlockStateProvider.simple(ModBlocks.LIQUID_CHOCOLATE_BLOCK.get().defaultBlockState()), BlockStateProvider.simple(ModBlocks.DARK_CHOCOLATE_BLOCK.get().defaultBlockState()))));

	public static final RegistryObject<ConfiguredFeature<LakeFeature.Configuration, ?>> LAKE_CANDY = CONFIGURED_FEATURES.register("lake_candy",
			() -> new ConfiguredFeature<>(Feature.LAKE,
					new LakeFeature.Configuration(BlockStateProvider.simple(ModBlocks.LIQUID_CANDY_BLOCK.get().defaultBlockState()), BlockStateProvider.simple(ModBlocks.CRYSTALLIZED_SUGAR.get().defaultBlockState()))));

	public static final class CustomFillerType {
		public static final RuleTest SUGAR = new TagMatchTest(ModTags.SUGAR);
	}

	public static final class Configs {
		public static final RandomPatchConfiguration DEFAULT_COTTON_CANDY_CONFIG = grassPatch(
				new WeightedStateProvider(weightedBlockStateBuilder()
						.add(ModBlocks.COTTON_CANDY_PLANT.get().defaultBlockState(), 1)
						.add(ModBlocks.COTTON_CANDY_BUSH.get().defaultBlockState(), 2)), 10);

		public static final RandomPatchConfiguration DEFAULT_CHOCOLATE_MUSHROOM_CONFIG = grassPatch(
				new WeightedStateProvider(weightedBlockStateBuilder()
						.add(ModBlocks.MILK_CHOCOLATE_MUSHROOM.get().defaultBlockState(), 1)
						.add(ModBlocks.WHITE_CHOCOLATE_MUSHROOM.get().defaultBlockState(), 1)
						.add(ModBlocks.DARK_CHOCOLATE_MUSHROOM.get().defaultBlockState(), 1)), 40);
		public static final RandomPatchConfiguration DEFAULT_CHOCOLATE_BAR_CONFIG = grassPatch(
				new WeightedStateProvider(getHorizontalWeightedList(List.of(
						ModBlocks.MILK_CHOCOLATE_BAR_BLOCK.get().defaultBlockState(),
						ModBlocks.WHITE_CHOCOLATE_BAR_BLOCK.get().defaultBlockState(),
						ModBlocks.DARK_CHOCOLATE_BAR_BLOCK.get().defaultBlockState()))),
				20);
		public static final RandomPatchConfiguration DEFAULT_CAVE_CHOCOLATE_BAR_CONFIG = grassPatch(
				new WeightedStateProvider(getHorizontalWeightedList(List.of(
						ModBlocks.MILK_CHOCOLATE_BAR_BLOCK.get().defaultBlockState(),
						ModBlocks.WHITE_CHOCOLATE_BAR_BLOCK.get().defaultBlockState(),
						ModBlocks.DARK_CHOCOLATE_BAR_BLOCK.get().defaultBlockState()))),
				20);

		public static final RandomPatchConfiguration DEFAULT_CANDY_CANE_CONFIG = FeatureUtils.simpleRandomPatchConfiguration(16,
				PlacementUtils.inlinePlaced(Feature.BLOCK_COLUMN, BlockColumnConfiguration.simple(BiasedToBottomInt.of(1, 16),
								new WeightedStateProvider(weightedBlockStateBuilder()
										.add(ModBlocks.WHITE_RED_CANDY_CANE_BLOCK.get().defaultBlockState(), 1).add(ModBlocks.WHITE_GREEN_CANDY_CANE_BLOCK.get().defaultBlockState(), 2))),
						BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.wouldSurvive(ModBlocks.WHITE_RED_CANDY_CANE_BLOCK.get().defaultBlockState(), BlockPos.ZERO)))));

		//Candy Plains
		public static final SpikeFeatureConfig DEFAULT_SUGAR_CONFIG = (new SpikeFeatureConfig.Builder(
				BlockStateProvider.simple(ModBlocks.CRYSTALLIZED_SUGAR.get().defaultBlockState()))).minLength(3).maxLength(8).chance(8)
				.whitelist(ImmutableSet.of(ModBlocks.CANDY_GRASS_BLOCK.get(), ModBlocks.MILK_BROWNIE_BLOCK.get())).build();
		public static final SpikeFeatureConfig DEFAULT_MILK_CHOCOLATE_CONFIG = (new SpikeFeatureConfig.Builder(
				BlockStateProvider.simple(ModBlocks.MILK_CHOCOLATE_BLOCK.get().defaultBlockState()))).minLength(5).maxLength(12).chance(24)
				.whitelist(ImmutableSet.of(ModBlocks.CANDY_GRASS_BLOCK.get(), ModBlocks.MILK_BROWNIE_BLOCK.get())).build();

		//Chocolate Forest
		public static final SpikeFeatureConfig DEFAULT_CHOCOLATE_CONFIG = (new SpikeFeatureConfig.Builder(
				new WeightedStateProvider(weightedBlockStateBuilder()
						.add(ModBlocks.MILK_CHOCOLATE_BLOCK.get().defaultBlockState(), 1)
						.add(ModBlocks.WHITE_CHOCOLATE_BLOCK.get().defaultBlockState(), 1)
						.add(ModBlocks.DARK_CHOCOLATE_BLOCK.get().defaultBlockState(), 1))))
				.minLength(3).maxLength(24).chance(16).whitelist(ImmutableSet.of(ModBlocks.CHOCOLATE_COVERED_WHITE_BROWNIE.get(), ModBlocks.WHITE_BROWNIE_BLOCK.get())).build();

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
