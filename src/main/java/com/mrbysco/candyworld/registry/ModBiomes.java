package com.mrbysco.candyworld.registry;

import com.mrbysco.candyworld.config.CandyConfig;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.biome.Biome.TemperatureModifier;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBiomes {
	public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, com.mrbysco.candyworld.CandyWorld.MOD_ID);

	public static final RegistryKey<Biome> COTTON_CANDY_PLAINS = makeKey("cotton_candy_plains");
	public static final RegistryKey<Biome> CHOCOLATE_FOREST = makeKey("chocolate_forest");
	public static final RegistryKey<Biome> GUMMY_SWAMP = makeKey("gummy_swamp");

	private static RegistryKey<Biome> makeKey(String name) {
		BIOMES.register(name, () -> new Biome.Builder()
				.precipitation(RainType.RAIN)
				.biomeCategory(Category.NONE)
				.depth(0)
				.downfall(0)
				.scale(0)
				.temperature(0)
				.specialEffects(new BiomeAmbience.Builder().fogColor(0).waterColor(0).waterFogColor(0).skyColor(0).build())
				.generationSettings(new BiomeGenerationSettings.Builder().surfaceBuilder(ConfiguredSurfaceBuilders.GRASS).build())
				.mobSpawnSettings(new MobSpawnInfo.Builder().build())
				.temperatureAdjustment(TemperatureModifier.NONE)
				.build());
		return RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(com.mrbysco.candyworld.CandyWorld.MOD_ID, name));
	}

	public static final BiomeDictionary.Type CANDY = BiomeDictionary.Type.getType("CANDY");

	public static void addBiomeTypes() {
		BiomeDictionary.addTypes(COTTON_CANDY_PLAINS, CANDY, Type.DRY, Type.SPARSE, Type.RARE);
		BiomeDictionary.addTypes(CHOCOLATE_FOREST, CANDY, Type.FOREST, Type.DENSE, Type.HILLS, Type.RARE);
		BiomeDictionary.addTypes(GUMMY_SWAMP, CANDY, Type.SWAMP, Type.WET, Type.RARE);
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
