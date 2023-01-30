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
				builder.getFeatures(Decoration.VEGETAL_DECORATION).add(ModPlacedFeatures.GUMMY_WORM.getHolder().orElseThrow());
			} else if (biomeKey.location().equals(ModBiomes.COTTON_CANDY_PLAINS.location())) {
				builder.getFeatures(Decoration.VEGETAL_DECORATION).add(ModPlacedFeatures.PATCH_COTTON_CANDY.getHolder().orElseThrow());
				builder.getFeatures(Decoration.VEGETAL_DECORATION).add(ModPlacedFeatures.COTTON_CANDY_TREE.getHolder().orElseThrow());
				builder.getFeatures(Decoration.VEGETAL_DECORATION).add(ModPlacedFeatures.PATCH_CAVE_CANDY_CANE.getHolder().orElseThrow());

				builder.getFeatures(Decoration.TOP_LAYER_MODIFICATION).add(ModPlacedFeatures.SUGAR_SPIKE.getHolder().orElseThrow());
				builder.getFeatures(Decoration.TOP_LAYER_MODIFICATION).add(ModPlacedFeatures.MILK_CHOCOLATE_SPIKE.getHolder().orElseThrow());
			} else if (biomeKey.location().equals(ModBiomes.CHOCOLATE_FOREST.location())) {
				builder.getFeatures(Decoration.VEGETAL_DECORATION).add(ModPlacedFeatures.PATCH_CHOCOLATE_MUSHROOM.getHolder().orElseThrow());
				builder.getFeatures(Decoration.VEGETAL_DECORATION).add(ModPlacedFeatures.PATCH_CHOCOLATE_BAR.getHolder().orElseThrow());
				builder.getFeatures(Decoration.VEGETAL_DECORATION).add(ModPlacedFeatures.CHOCOLATE_TREE.getHolder().orElseThrow());

				builder.getFeatures(Decoration.UNDERGROUND_DECORATION).add(ModPlacedFeatures.PATCH_CAVE_CHOCOLATE_BAR.getHolder().orElseThrow());

				builder.getFeatures(Decoration.TOP_LAYER_MODIFICATION).add(ModPlacedFeatures.CHOCOLATE_SPIKE.getHolder().orElseThrow());
			}
		}
	}

	public void setupDefaults(BiomeGenerationSettingsBuilder builder) {
		builder.getFeatures(Decoration.LAKES).add(ModPlacedFeatures.LAKE_CHOCOLATE_SURFACE.getHolder().orElseThrow());
		builder.getFeatures(Decoration.LAKES).add(ModPlacedFeatures.LAKE_CANDY_UNDERGROUND.getHolder().orElseThrow());
		builder.getFeatures(Decoration.LAKES).add(ModPlacedFeatures.LAKE_CANDY_SURFACE.getHolder().orElseThrow());

		builder.getFeatures(Decoration.UNDERGROUND_ORES).add(ModPlacedFeatures.ORE_MILK_BROWNIE.getHolder().orElseThrow());
		builder.getFeatures(Decoration.UNDERGROUND_ORES).add(ModPlacedFeatures.ORE_WHITE_BROWNIE.getHolder().orElseThrow());
		builder.getFeatures(Decoration.UNDERGROUND_ORES).add(ModPlacedFeatures.ORE_DARK_BROWNIE.getHolder().orElseThrow());
		builder.getFeatures(Decoration.UNDERGROUND_ORES).add(ModPlacedFeatures.ORE_TELEPORT.getHolder().orElseThrow());
		builder.getFeatures(Decoration.UNDERGROUND_ORES).add(ModPlacedFeatures.ORE_SUGAR_SAND.getHolder().orElseThrow());
		builder.getFeatures(Decoration.UNDERGROUND_ORES).add(ModPlacedFeatures.ORE_MILK_BROWNIE_OVERWORLD.getHolder().orElseThrow());
		builder.getFeatures(Decoration.UNDERGROUND_ORES).add(ModPlacedFeatures.ORE_WHITE_BROWNIE_OVERWORLD.getHolder().orElseThrow());
		builder.getFeatures(Decoration.UNDERGROUND_ORES).add(ModPlacedFeatures.ORE_DARK_BROWNIE_OVERWORLD.getHolder().orElseThrow());
		builder.getFeatures(Decoration.UNDERGROUND_ORES).add(ModPlacedFeatures.ORE_SUGAR_BLOCK.getHolder().orElseThrow());
		builder.getFeatures(Decoration.UNDERGROUND_ORES).add(ModPlacedFeatures.ORE_COOKIE.getHolder().orElseThrow());
		builder.getFeatures(Decoration.UNDERGROUND_ORES).add(ModPlacedFeatures.ORE_SUGAR_COOKIE.getHolder().orElseThrow());
	}
}
