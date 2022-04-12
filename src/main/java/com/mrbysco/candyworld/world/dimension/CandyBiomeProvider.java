package com.mrbysco.candyworld.world.dimension;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mrbysco.candyworld.registry.ModBiomes;
import com.mrbysco.candyworld.world.dimension.layer.GenLayerCandyBiomes;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.LazyAreaLayerContext;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.area.LazyArea;
import net.minecraft.world.gen.layer.Layer;
import net.minecraft.world.gen.layer.MixOceansLayer;
import net.minecraft.world.gen.layer.MixRiverLayer;
import net.minecraft.world.gen.layer.RemoveTooMuchOceanLayer;
import net.minecraft.world.gen.layer.SmoothLayer;
import net.minecraft.world.gen.layer.StartRiverLayer;
import net.minecraft.world.gen.layer.ZoomLayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
import java.util.Optional;
import java.util.function.LongFunction;

public class CandyBiomeProvider extends BiomeProvider {
	public static final Codec<CandyBiomeProvider> CODEC = RecordCodecBuilder.create((generatorInstance) -> {
		return generatorInstance.group(Codec.LONG.fieldOf("seed").stable().forGetter((generator) -> {
			return generator.seed;
		}), RegistryLookupCodec.create(Registry.BIOME_REGISTRY).forGetter((generator) -> {
			return generator.biomes;
		})).apply(generatorInstance, generatorInstance.stable(CandyBiomeProvider::new));
	});
	private final Layer noiseBiomeLayer;
	private static final List<RegistryKey<Biome>> BIOMES = ImmutableList.of(ModBiomes.GUMMY_SWAMP, ModBiomes.CHOCOLATE_FOREST, ModBiomes.COTTON_CANDY_PLAINS);
	private final long seed;
	private final Registry<Biome> biomes;

	public CandyBiomeProvider(long seed, Registry<Biome> biomeRegistry) {
		super(BIOMES
				.stream()
				.map(RegistryKey::location)
				.map(biomeRegistry::getOptional)
				.filter(Optional::isPresent)
				.map(opt -> opt::get)
		);
		this.seed = seed;
		this.biomes = biomeRegistry;
		this.noiseBiomeLayer = makeLayers(seed, biomeRegistry);
	}

	private static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> makeLayers(LongFunction<C> seed, Registry<Biome> registry) {
		IAreaFactory<T> factory = GenLayerCandyBiomes.INSTANCE.setup(registry).run(seed.apply(1L));

		factory = ZoomLayer.FUZZY.run(seed.apply(2000L), factory);

		factory = ZoomLayer.NORMAL.run(seed.apply(2000L), factory);

		for (int i = 0; i < 5; i++) {
			factory = ZoomLayer.NORMAL.run(seed.apply(2100L), factory);
		}

		factory = RemoveTooMuchOceanLayer.INSTANCE.run(seed.apply(2L), factory);

		IAreaFactory<T> factory2 = StartRiverLayer.INSTANCE.run(seed.apply(100L), factory);
		factory = SmoothLayer.INSTANCE.run(seed.apply(1000L), factory);
		factory = MixRiverLayer.INSTANCE.run(seed.apply(100L), factory, factory2);
		return MixOceansLayer.INSTANCE.run(seed.apply(100L), factory, factory2);
	}

	public static Layer makeLayers(long seed, Registry<Biome> registry) {
		IAreaFactory<LazyArea> areaFactory = makeLayers((context) -> new LazyAreaLayerContext(25, seed, context), registry);
		return new Layer(areaFactory) {
			@Override
			public Biome get(Registry<Biome> p_242936_1_, int p_242936_2_, int p_242936_3_) {
				int i = this.area.get(p_242936_2_, p_242936_3_);
				Biome biome = registry.byId(i);
				if (biome == null)
					throw new IllegalStateException("Unknown biome id emitted by layers: " + i);
				return biome;
			}
		};
	}

	@Override
	protected Codec<? extends BiomeProvider> codec() {
		return CODEC;
	}

	@OnlyIn(Dist.CLIENT)
	public BiomeProvider withSeed(long seed) {
		return new CandyBiomeProvider(seed, this.biomes);
	}

	public Biome getNoiseBiome(int x, int y, int z) {
		return this.noiseBiomeLayer.get(this.biomes, x, z);
	}

	public static int getBiomeId(RegistryKey<Biome> biome, Registry<Biome> registry) {
		return registry.getId(registry.get(biome));
	}
}