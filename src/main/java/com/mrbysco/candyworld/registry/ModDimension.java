package com.mrbysco.candyworld.registry;

import com.mrbysco.candyworld.CandyWorld;
import com.mrbysco.candyworld.world.dimension.CandyBiomeProvider;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public final class ModDimension {
	public static final RegistryKey<World> candy_world = RegistryKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(CandyWorld.MOD_ID, "candy_world"));

	public static void registerStuff() {
		Registry.register(Registry.BIOME_SOURCE, new ResourceLocation(CandyWorld.MOD_ID, "biome_provider"), CandyBiomeProvider.CODEC);
	}
}
