package com.mrbysco.candyworld.world;

import com.mrbysco.candyworld.registry.ModBiomes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class WorldgenHandler {
	@SubscribeEvent(priority = EventPriority.HIGH)
	public void biomeLoadingEvent(BiomeLoadingEvent event) {
		ResourceKey<Biome> biomeKey = ResourceKey.create(Registry.BIOME_REGISTRY, event.getName());
		BiomeGenerationSettingsBuilder builder = event.getGeneration();
		if (BiomeDictionary.hasType(biomeKey, ModBiomes.CANDY)) {
			setupDefaults(builder);
			if (biomeKey.location().equals(ModBiomes.GUMMY_SWAMP.location())) {
				builder.getFeatures(Decoration.VEGETAL_DECORATION).add(ModPlacedFeatures.GUMMY_WORM);
			} else if (biomeKey.location().equals(ModBiomes.COTTON_CANDY_PLAINS.location())) {
				builder.getFeatures(Decoration.VEGETAL_DECORATION).add(ModPlacedFeatures.PATCH_COTTON_CANDY);
				builder.getFeatures(Decoration.VEGETAL_DECORATION).add(ModPlacedFeatures.COTTON_CANDY_TREE);
				builder.getFeatures(Decoration.VEGETAL_DECORATION).add(ModPlacedFeatures.PATCH_CAVE_CANDY_CANE);

				builder.getFeatures(Decoration.TOP_LAYER_MODIFICATION).add(ModPlacedFeatures.SUGAR_SPIKE);
				builder.getFeatures(Decoration.TOP_LAYER_MODIFICATION).add(ModPlacedFeatures.MILK_CHOCOLATE_SPIKE);
			} else if (biomeKey.location().equals(ModBiomes.CHOCOLATE_FOREST.location())) {
				builder.getFeatures(Decoration.VEGETAL_DECORATION).add(ModPlacedFeatures.PATCH_CHOCOLATE_MUSHROOM);
				builder.getFeatures(Decoration.VEGETAL_DECORATION).add(ModPlacedFeatures.PATCH_CHOCOLATE_BAR);
				builder.getFeatures(Decoration.VEGETAL_DECORATION).add(ModPlacedFeatures.CHOCOLATE_TREE);

				builder.getFeatures(Decoration.UNDERGROUND_DECORATION).add(ModPlacedFeatures.PATCH_CAVE_CHOCOLATE_BAR);

				builder.getFeatures(Decoration.TOP_LAYER_MODIFICATION).add(ModPlacedFeatures.CHOCOLATE_SPIKE);
			}
		}
	}

	public void setupDefaults(BiomeGenerationSettingsBuilder builder) {
		builder.getFeatures(Decoration.LAKES).add(ModPlacedFeatures.LAKE_CHOCOLATE_SURFACE);
		builder.getFeatures(Decoration.LAKES).add(ModPlacedFeatures.LAKE_CANDY_UNDERGROUND);
		builder.getFeatures(Decoration.LAKES).add(ModPlacedFeatures.LAKE_CANDY_SURFACE);

		builder.getFeatures(Decoration.UNDERGROUND_ORES).add(ModPlacedFeatures.ORE_MILK_BROWNIE);
		builder.getFeatures(Decoration.UNDERGROUND_ORES).add(ModPlacedFeatures.ORE_WHITE_BROWNIE);
		builder.getFeatures(Decoration.UNDERGROUND_ORES).add(ModPlacedFeatures.ORE_DARK_BROWNIE);
		builder.getFeatures(Decoration.UNDERGROUND_ORES).add(ModPlacedFeatures.ORE_TELEPORT);
		builder.getFeatures(Decoration.UNDERGROUND_ORES).add(ModPlacedFeatures.ORE_SUGAR_SAND);
		builder.getFeatures(Decoration.UNDERGROUND_ORES).add(ModPlacedFeatures.ORE_MILK_BROWNIE_OVERWORLD);
		builder.getFeatures(Decoration.UNDERGROUND_ORES).add(ModPlacedFeatures.ORE_WHITE_BROWNIE_OVERWORLD);
		builder.getFeatures(Decoration.UNDERGROUND_ORES).add(ModPlacedFeatures.ORE_DARK_BROWNIE_OVERWORLD);
		builder.getFeatures(Decoration.UNDERGROUND_ORES).add(ModPlacedFeatures.ORE_SUGAR_BLOCK);
		builder.getFeatures(Decoration.UNDERGROUND_ORES).add(ModPlacedFeatures.ORE_COOKIE);
		builder.getFeatures(Decoration.UNDERGROUND_ORES).add(ModPlacedFeatures.ORE_SUGAR_COOKIE);
	}
}
