package com.mrbysco.candyworld.registry;

import com.mrbysco.candyworld.CandyWorld;
import com.mrbysco.candyworld.config.CandyConfig;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.SurfaceBuilders;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.biome.Biome.Precipitation;
import net.minecraft.world.level.biome.Biome.TemperatureModifier;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBiomes {
	public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, CandyWorld.MOD_ID);

	public static final ResourceKey<Biome> COTTON_CANDY_PLAINS = makeKey("cotton_candy_plains");
	public static final ResourceKey<Biome> CHOCOLATE_FOREST = makeKey("chocolate_forest");
	public static final ResourceKey<Biome> GUMMY_SWAMP = makeKey("gummy_swamp");

	private static ResourceKey<Biome> makeKey(String name) {
		BIOMES.register(name, () -> new Biome.BiomeBuilder()
				.precipitation(Precipitation.RAIN)
				.biomeCategory(BiomeCategory.NONE)
				.depth(0)
				.downfall(0)
				.scale(0)
				.temperature(0)
				.specialEffects(new BiomeSpecialEffects.Builder().fogColor(0).waterColor(0).waterFogColor(0).skyColor(0).build())
				.generationSettings(new BiomeGenerationSettings.Builder().surfaceBuilder(SurfaceBuilders.GRASS).build())
				.mobSpawnSettings(new MobSpawnSettings.Builder().build())
				.temperatureAdjustment(TemperatureModifier.NONE)
				.build());
		return ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(CandyWorld.MOD_ID, name));
	}

	public static final BiomeDictionary.Type CANDY = BiomeDictionary.Type.getType("CANDY");

	public static void addBiomeTypes() {
		BiomeDictionary.addTypes(COTTON_CANDY_PLAINS, CANDY, Type.DRY, Type.SPARSE, Type.RARE, Type.OVERWORLD);
		BiomeDictionary.addTypes(CHOCOLATE_FOREST, CANDY, Type.FOREST, Type.DENSE, Type.HILLS, Type.RARE, Type.OVERWORLD);
		BiomeDictionary.addTypes(GUMMY_SWAMP, CANDY, Type.SWAMP, Type.WET, Type.RARE, Type.OVERWORLD);
	}

	public static void addBiomes() {
		if(CandyConfig.COMMON.weightCottonCandyPlains.get() > 0)
			BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(CHOCOLATE_FOREST, CandyConfig.COMMON.weightCottonCandyPlains.get()));
		if(CandyConfig.COMMON.weightChocolateForest.get() > 0)
			BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(COTTON_CANDY_PLAINS, CandyConfig.COMMON.weightChocolateForest.get()));
		if(CandyConfig.COMMON.weightGummySwamp.get() > 0)
			BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(GUMMY_SWAMP, CandyConfig.COMMON.weightGummySwamp.get()));
	}
}
