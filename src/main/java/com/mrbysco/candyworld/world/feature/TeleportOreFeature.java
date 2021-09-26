package com.mrbysco.candyworld.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.OreFeature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.Random;

public class TeleportOreFeature extends OreFeature {
	public TeleportOreFeature(Codec<OreFeatureConfig> featureConfigCodec) {
		super(featureConfigCodec);
	}

	@Override
	public boolean place(ISeedReader reader, ChunkGenerator generator, Random random, BlockPos pos, OreFeatureConfig oreFeatureConfig) {
		if(reader.getLevel().dimension().location().equals(new ResourceLocation(com.mrbysco.candyworld.CandyWorld.MOD_ID, "candy_world"))) {
			return super.place(reader, generator, random, pos, oreFeatureConfig);
		}
		return false;
	}
}
