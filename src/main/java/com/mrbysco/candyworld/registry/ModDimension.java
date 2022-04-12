package com.mrbysco.candyworld.registry;

import com.mrbysco.candyworld.CandyWorld;
import com.mrbysco.candyworld.world.dimension.CandyBiomeProvider;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public final class ModDimension {
	public static final ResourceKey<Level> candy_world = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(CandyWorld.MOD_ID, "candy_world"));

	public static void registerStuff() {
		Registry.register(Registry.BIOME_SOURCE, new ResourceLocation(CandyWorld.MOD_ID, "biome_provider"), CandyBiomeProvider.CODEC);
	}
}
