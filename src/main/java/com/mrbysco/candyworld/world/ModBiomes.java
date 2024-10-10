package com.mrbysco.candyworld.world;

import com.mrbysco.candyworld.CandyWorld;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBiomes {

	public static final ResourceKey<Biome> CHOCOLATE_FOREST = createKey("chocolate_forest");
	public static final ResourceKey<Biome> COTTON_CANDY_FOREST = createKey("cotton_candy_forest");
	public static final ResourceKey<Biome> GUMMY_SWAMP = createKey("gummy_swamp");

	private static ResourceKey<Biome> createKey(String name) {
		return ResourceKey.create(ForgeRegistries.Keys.BIOMES, new ResourceLocation(CandyWorld.MOD_ID, name));
	}

	public static void bootstrap(BootstapContext<Biome> context) {
		//Insert biome bootstrap here (but that requires the Carvers to be defined properly)
	}
}
