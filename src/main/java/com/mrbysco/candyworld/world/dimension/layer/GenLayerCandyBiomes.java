package com.mrbysco.candyworld.world.dimension.layer;

import com.google.common.collect.ImmutableList;
import com.mrbysco.candyworld.registry.ModBiomes;
import com.mrbysco.candyworld.world.dimension.CandyBiomeProvider;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IAreaTransformer0;

import java.util.List;

public enum GenLayerCandyBiomes implements IAreaTransformer0 {
	INSTANCE;

	protected static final List<RegistryKey<Biome>> commonBiomes = ImmutableList.of(
			ModBiomes.GUMMY_SWAMP,
			ModBiomes.CHOCOLATE_FOREST,
			ModBiomes.COTTON_CANDY_PLAINS
	);

	private Registry<Biome> registry;

	public GenLayerCandyBiomes setup(Registry<Biome> registry) {
		this.registry = registry;
		return this;
	}

	GenLayerCandyBiomes() {

	}

	@Override
	public int applyPixel(INoiseRandom iNoiseRandom, int x, int y) {
		return getRandomBiome(iNoiseRandom, commonBiomes);
	}

	private int getRandomBiome(INoiseRandom random, List<RegistryKey<Biome>> biomes) {
		return CandyBiomeProvider.getBiomeId(biomes.get(random.nextRandom(biomes.size())), registry);
	}
}