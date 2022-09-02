package com.mrbysco.candyworld.registry;

import com.mrbysco.candyworld.CandyWorld;
import com.mrbysco.candyworld.config.CandyConfig;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.Biome;
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

	public static final ResourceKey<Biome> COTTON_CANDY_PLAINS = makeKey("cotton_candy_plains", cottonCandyPlains());
	public static final ResourceKey<Biome> CHOCOLATE_FOREST = makeKey("chocolate_forest", chocolateForest());
	public static final ResourceKey<Biome> GUMMY_SWAMP = makeKey("gummy_swamp", gummySwamp());

	private static Biome cottonCandyPlains() {
		MobSpawnSettings.Builder mobspawnsettings$builder = new MobSpawnSettings.Builder();
		BiomeDefaultFeatures.plainsSpawns(mobspawnsettings$builder);

		BiomeGenerationSettings.Builder biomegenerationsettings$builder = new BiomeGenerationSettings.Builder();
		BiomeDefaultFeatures.addDefaultOres(biomegenerationsettings$builder);
		BiomeDefaultFeatures.addDefaultSoftDisks(biomegenerationsettings$builder);
		globalGeneration(biomegenerationsettings$builder, true);

		return (new Biome.BiomeBuilder())
				.precipitation(Biome.Precipitation.RAIN).biomeCategory(Biome.BiomeCategory.PLAINS).temperature(0.8F).downfall(0.3F)
				.specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011)
						.fogColor(12638463).skyColor(16755438)
						.ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(null).build())
				.mobSpawnSettings(mobspawnsettings$builder.build()).generationSettings(biomegenerationsettings$builder.build()).build();
	}

	private static Biome chocolateForest() {
		MobSpawnSettings.Builder mobspawnsettings$builder = new MobSpawnSettings.Builder();
		BiomeDefaultFeatures.commonSpawns(mobspawnsettings$builder);

		BiomeGenerationSettings.Builder biomegenerationsettings$builder = new BiomeGenerationSettings.Builder();
		BiomeDefaultFeatures.addDefaultOres(biomegenerationsettings$builder);
		BiomeDefaultFeatures.addDefaultSoftDisks(biomegenerationsettings$builder);
		globalGeneration(biomegenerationsettings$builder, true);

		return (new Biome.BiomeBuilder())
				.precipitation(Biome.Precipitation.RAIN).biomeCategory(Biome.BiomeCategory.FOREST).temperature(0.8F).downfall(0.3F)
				.specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011)
						.fogColor(12638463).skyColor(16768426)
						.ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(null).build())
				.mobSpawnSettings(mobspawnsettings$builder.build()).generationSettings(biomegenerationsettings$builder.build()).build();
	}

	private static Biome gummySwamp() {
		MobSpawnSettings.Builder mobspawnsettings$builder = new MobSpawnSettings.Builder();
		BiomeDefaultFeatures.commonSpawns(mobspawnsettings$builder);

		BiomeGenerationSettings.Builder biomegenerationsettings$builder = new BiomeGenerationSettings.Builder();
		BiomeDefaultFeatures.addDefaultOres(biomegenerationsettings$builder);
		BiomeDefaultFeatures.addSwampClayDisk(biomegenerationsettings$builder);
		globalGeneration(biomegenerationsettings$builder, false);

		return (new Biome.BiomeBuilder()).precipitation(Biome.Precipitation.RAIN).biomeCategory(Biome.BiomeCategory.SWAMP).
				temperature(0.9F).downfall(0.8F).specialEffects((new BiomeSpecialEffects.Builder())
						.waterColor(6388580).waterFogColor(2302743).fogColor(12638463).skyColor(10746879)
						.foliageColorOverride(6975545).grassColorModifier(BiomeSpecialEffects.GrassColorModifier.SWAMP)
						.ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(mobspawnsettings$builder.build())
				.generationSettings(biomegenerationsettings$builder.build()).build();
	}

	private static void globalGeneration(BiomeGenerationSettings.Builder builder, boolean springs) {
		//TODO: Add own carvers and lakes / springs
		BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
		BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
		if (springs) {
			BiomeDefaultFeatures.addDefaultSprings(builder);
		}
	}

	private static ResourceKey<Biome> makeKey(String name, Biome biome) {
		BIOMES.register(name, () -> biome);
		return ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(CandyWorld.MOD_ID, name));
	}

	public static final BiomeDictionary.Type CANDY = BiomeDictionary.Type.getType("CANDY");

	public static void addBiomeTypes() {
		BiomeDictionary.addTypes(COTTON_CANDY_PLAINS, CANDY, Type.DRY, Type.SPARSE, Type.RARE, Type.OVERWORLD);
		BiomeDictionary.addTypes(CHOCOLATE_FOREST, CANDY, Type.FOREST, Type.DENSE, Type.HILLS, Type.RARE, Type.OVERWORLD);
		BiomeDictionary.addTypes(GUMMY_SWAMP, CANDY, Type.SWAMP, Type.WET, Type.RARE, Type.OVERWORLD);
	}

	public static void addBiomes() {
		if (CandyConfig.COMMON.weightCottonCandyPlains.get() > 0)
			BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(CHOCOLATE_FOREST, CandyConfig.COMMON.weightCottonCandyPlains.get()));
		if (CandyConfig.COMMON.weightChocolateForest.get() > 0)
			BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(COTTON_CANDY_PLAINS, CandyConfig.COMMON.weightChocolateForest.get()));
		if (CandyConfig.COMMON.weightGummySwamp.get() > 0)
			BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(GUMMY_SWAMP, CandyConfig.COMMON.weightGummySwamp.get()));
	}
}
