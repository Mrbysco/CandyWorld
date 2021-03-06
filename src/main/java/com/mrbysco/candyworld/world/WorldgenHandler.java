package com.mrbysco.candyworld.world;

import com.mrbysco.candyworld.config.CandyConfig;
import com.mrbysco.candyworld.registry.ModBiomes;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class WorldgenHandler {
	@SubscribeEvent(priority = EventPriority.HIGH)
	public void biomeLoadingEvent(BiomeLoadingEvent event) {
		RegistryKey<Biome> biomeKey = RegistryKey.create(Registry.BIOME_REGISTRY, event.getName());
		BiomeGenerationSettingsBuilder builder = event.getGeneration();
		if (BiomeDictionary.hasType(biomeKey, ModBiomes.CANDY)) {
			setupDefaults(builder);
			if (biomeKey.location().equals(ModBiomes.GUMMY_SWAMP.location())) {
				builder.getFeatures(Decoration.VEGETAL_DECORATION).add(() -> ModFeatureConfigs.GUMMY_WORM);
			} else if (biomeKey.location().equals(ModBiomes.COTTON_CANDY_PLAINS.location())) {
				builder.getFeatures(Decoration.VEGETAL_DECORATION).add(() -> ModFeatureConfigs.PATCH_COTTON_CANDY);
				builder.getFeatures(Decoration.VEGETAL_DECORATION).add(() -> ModFeatureConfigs.COTTON_CANDY_TREE);
				builder.getFeatures(Decoration.VEGETAL_DECORATION).add(() -> ModFeatureConfigs.PATCH_CAVE_CANDY_CANE);

				builder.getFeatures(Decoration.TOP_LAYER_MODIFICATION).add(() -> ModFeatureConfigs.SUGAR_SPIKE);
				builder.getFeatures(Decoration.TOP_LAYER_MODIFICATION).add(() -> ModFeatureConfigs.MILK_CHOCOLATE_SPIKE);
			} else if (biomeKey.location().equals(ModBiomes.CHOCOLATE_FOREST.location())) {
				builder.getFeatures(Decoration.VEGETAL_DECORATION).add(() -> ModFeatureConfigs.PATCH_CHOCOLATE_MUSHROOM);
				builder.getFeatures(Decoration.VEGETAL_DECORATION).add(() -> ModFeatureConfigs.PATCH_CHOCOLATE_BAR);
				builder.getFeatures(Decoration.VEGETAL_DECORATION).add(() -> ModFeatureConfigs.CHOCOLATE_TREE);

				builder.getFeatures(Decoration.UNDERGROUND_DECORATION).add(() -> ModFeatureConfigs.PATCH_CAVE_CHOCOLATE_BAR);

				builder.getFeatures(Decoration.TOP_LAYER_MODIFICATION).add(() -> ModFeatureConfigs.CHOCOLATE_SPIKE);
			}

			if (event.getName().equals(ModBiomes.CHOCOLATE_FOREST.location()) && CandyConfig.COMMON.weightCottonCandyPlains.get() > 0) {
				BiomeManager.removeBiome(BiomeType.WARM, new BiomeEntry(ModBiomes.CHOCOLATE_FOREST, CandyConfig.COMMON.weightCottonCandyPlains.get()));
				BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(ModBiomes.CHOCOLATE_FOREST, CandyConfig.COMMON.weightCottonCandyPlains.get()));
			}
			if (event.getName().equals(ModBiomes.COTTON_CANDY_PLAINS.location()) && CandyConfig.COMMON.weightChocolateForest.get() > 0) {
				BiomeManager.removeBiome(BiomeType.WARM, new BiomeEntry(ModBiomes.COTTON_CANDY_PLAINS, CandyConfig.COMMON.weightChocolateForest.get()));
				BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(ModBiomes.COTTON_CANDY_PLAINS, CandyConfig.COMMON.weightChocolateForest.get()));
			}
			if (event.getName().equals(ModBiomes.GUMMY_SWAMP.location()) && CandyConfig.COMMON.weightGummySwamp.get() > 0) {
				BiomeManager.removeBiome(BiomeType.WARM, new BiomeEntry(ModBiomes.GUMMY_SWAMP, CandyConfig.COMMON.weightGummySwamp.get()));
				BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(ModBiomes.GUMMY_SWAMP, CandyConfig.COMMON.weightGummySwamp.get()));
			}
		}
	}

	public void setupDefaults(BiomeGenerationSettingsBuilder builder) {
		builder.getFeatures(Decoration.LAKES).add(() -> ModFeatureConfigs.LAKE_CHOCOLATE);
		builder.getFeatures(Decoration.LAKES).add(() -> ModFeatureConfigs.LAKE_CANDY);

		builder.getFeatures(Decoration.UNDERGROUND_ORES).add(() -> ModFeatureConfigs.ORE_MILK_BROWNIE);
		builder.getFeatures(Decoration.UNDERGROUND_ORES).add(() -> ModFeatureConfigs.ORE_WHITE_BROWNIE);
		builder.getFeatures(Decoration.UNDERGROUND_ORES).add(() -> ModFeatureConfigs.ORE_DARK_BROWNIE);
		builder.getFeatures(Decoration.UNDERGROUND_ORES).add(() -> ModFeatureConfigs.ORE_TELEPORT);
		builder.getFeatures(Decoration.UNDERGROUND_ORES).add(() -> ModFeatureConfigs.ORE_SUGAR_SAND);
		builder.getFeatures(Decoration.UNDERGROUND_ORES).add(() -> ModFeatureConfigs.ORE_MILK_BROWNIE_OVERWORLD);
		builder.getFeatures(Decoration.UNDERGROUND_ORES).add(() -> ModFeatureConfigs.ORE_WHITE_BROWNIE_OVERWORLD);
		builder.getFeatures(Decoration.UNDERGROUND_ORES).add(() -> ModFeatureConfigs.ORE_DARK_BROWNIE_OVERWORLD);
		builder.getFeatures(Decoration.UNDERGROUND_ORES).add(() -> ModFeatureConfigs.ORE_SUGAR_BLOCK);
		builder.getFeatures(Decoration.UNDERGROUND_ORES).add(() -> ModFeatureConfigs.ORE_COOKIE);
		builder.getFeatures(Decoration.UNDERGROUND_ORES).add(() -> ModFeatureConfigs.ORE_SUGAR_COOKIE);
	}
}
