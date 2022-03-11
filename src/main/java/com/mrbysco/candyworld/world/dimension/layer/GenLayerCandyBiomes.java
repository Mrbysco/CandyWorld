//package com.mrbysco.candyworld.world.dimension.layer;
//
//import com.google.common.collect.ImmutableList;
//import com.mrbysco.candyworld.registry.ModBiomes;
//import com.mrbysco.candyworld.world.dimension.CandyBiomeProvider;
//import net.minecraft.core.Registry;
//import net.minecraft.resources.ResourceKey;
//import net.minecraft.world.level.biome.Biome;
//import net.minecraft.world.level.newbiome.context.Context;
//import net.minecraft.world.level.newbiome.layer.traits.AreaTransformer0;
//
//import java.util.List;
//
//public enum GenLayerCandyBiomes implements AreaTransformer0 {
//	INSTANCE;
//
//	private static final List<ResourceKey<Biome>> commonBiomes = ImmutableList.of(
//			ModBiomes.GUMMY_SWAMP,
//			ModBiomes.CHOCOLATE_FOREST,
//			ModBiomes.COTTON_CANDY_PLAINS
//	);
//
//	private Registry<Biome> registry;
//
//	public GenLayerCandyBiomes setup(Registry<Biome> registry) {
//		this.registry = registry;
//		return this;
//	}
//
//	GenLayerCandyBiomes() {
//
//	}
//
//	@Override
//	public int applyPixel(Context iNoiseRandom, int x, int y) {
//		return getRandomBiome(iNoiseRandom, commonBiomes);
//	}
//
//	private int getRandomBiome(Context random, List<ResourceKey<Biome>> biomes) {
//		return CandyBiomeProvider.getBiomeId(biomes.get(random.nextRandom(biomes.size())), registry);
//	}
//}