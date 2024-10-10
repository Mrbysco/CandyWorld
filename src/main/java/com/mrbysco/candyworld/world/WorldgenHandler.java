package com.mrbysco.candyworld.world;

import com.mrbysco.candyworld.CandyWorld;
import com.mrbysco.candyworld.registry.ModTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

public class WorldgenHandler {
	public static final ResourceKey<BiomeModifier> ADD_CHOCOLATE_LAKE_SURFACE = createKey("add_chocolate_lake");
	public static final ResourceKey<BiomeModifier> ADD_LAKE_CANDY_UNDERGROUND = createKey("add_lake_candy_underground");
	public static final ResourceKey<BiomeModifier> ADD_LAKE_CANDY_SURFACE = createKey("add_lake_candy_surface");
	public static final ResourceKey<BiomeModifier> ADD_CANDY_ORES = createKey("add_candy_ores");

	private static ResourceKey<BiomeModifier> createKey(String name) {
		return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(CandyWorld.MOD_ID, name));
	}

	public static void bootstrap(BootstapContext<BiomeModifier> context) {
		HolderGetter<Biome> biomeGetter = context.lookup(Registries.BIOME);
		HolderGetter<PlacedFeature> placedGetter = context.lookup(Registries.PLACED_FEATURE);

		HolderSet.Named<Biome> candyHolder = biomeGetter.getOrThrow(ModTags.IS_CANDY);
		HolderSet.Named<PlacedFeature> candyOresHolder = placedGetter.getOrThrow(ModTags.CANDY_ORES);

		context.register(ADD_CHOCOLATE_LAKE_SURFACE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
				candyHolder,
				HolderSet.direct(placedGetter.getOrThrow(ModPlacedFeatures.LAKE_CHOCOLATE_SURFACE)),
				Decoration.LAKES
		));
		context.register(ADD_LAKE_CANDY_UNDERGROUND, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
				candyHolder,
				HolderSet.direct(placedGetter.getOrThrow(ModPlacedFeatures.LAKE_CANDY_SURFACE)),
				Decoration.LAKES
		));
		context.register(ADD_LAKE_CANDY_SURFACE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
				candyHolder,
				HolderSet.direct(placedGetter.getOrThrow(ModPlacedFeatures.LAKE_CANDY_UNDERGROUND)),
				Decoration.LAKES
		));
		context.register(ADD_CANDY_ORES, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
				candyHolder,
				candyOresHolder,
				Decoration.UNDERGROUND_ORES
		));

	}

//	@SubscribeEvent(priority = EventPriority.HIGH)
//	public void biomeLoadingEvent(BiomeLoadingEvent event) {
//		ResourceKey<Biome> biomeKey = ResourceKey.create(Registry.BIOME_REGISTRY, event.getName());
//		BiomeGenerationSettingsBuilder builder = event.getGeneration();
//		if (BiomeDictionary.hasType(biomeKey, ModBiomes.CANDY)) {
//			setupDefaults(builder);
//			if (biomeKey.location().equals(ModBiomes.GUMMY_SWAMP.location())) {
//				builder.getFeatures(Decoration.VEGETAL_DECORATION).add(ModPlacedFeatures.GUMMY_WORM.getHolder().orElseThrow());
//			} else if (biomeKey.location().equals(ModBiomes.COTTON_CANDY_PLAINS.location())) {
//				builder.getFeatures(Decoration.VEGETAL_DECORATION).add(ModPlacedFeatures.PATCH_COTTON_CANDY.getHolder().orElseThrow());
//				builder.getFeatures(Decoration.VEGETAL_DECORATION).add(ModPlacedFeatures.COTTON_CANDY_TREE.getHolder().orElseThrow());
//				builder.getFeatures(Decoration.VEGETAL_DECORATION).add(ModPlacedFeatures.PATCH_CAVE_CANDY_CANE.getHolder().orElseThrow());
//
//				builder.getFeatures(Decoration.TOP_LAYER_MODIFICATION).add(ModPlacedFeatures.SUGAR_SPIKE.getHolder().orElseThrow());
//				builder.getFeatures(Decoration.TOP_LAYER_MODIFICATION).add(ModPlacedFeatures.MILK_CHOCOLATE_SPIKE.getHolder().orElseThrow());
//			} else if (biomeKey.location().equals(ModBiomes.CHOCOLATE_FOREST.location())) {
//				builder.getFeatures(Decoration.VEGETAL_DECORATION).add(ModPlacedFeatures.PATCH_CHOCOLATE_MUSHROOM.getHolder().orElseThrow());
//				builder.getFeatures(Decoration.VEGETAL_DECORATION).add(ModPlacedFeatures.PATCH_CHOCOLATE_BAR.getHolder().orElseThrow());
//				builder.getFeatures(Decoration.VEGETAL_DECORATION).add(ModPlacedFeatures.CHOCOLATE_TREE.getHolder().orElseThrow());
//
//				builder.getFeatures(Decoration.UNDERGROUND_DECORATION).add(ModPlacedFeatures.PATCH_CAVE_CHOCOLATE_BAR.getHolder().orElseThrow());
//
//				builder.getFeatures(Decoration.TOP_LAYER_MODIFICATION).add(ModPlacedFeatures.CHOCOLATE_SPIKE.getHolder().orElseThrow());
//			}
//		}
	}
