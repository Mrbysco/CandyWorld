package com.mrbysco.candyworld.world.feature;

import com.mojang.serialization.Codec;
import com.mrbysco.candyworld.CandyWorld;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;

public class TeleportOreFeature extends OreFeature {
	public TeleportOreFeature(Codec<OreConfiguration> featureConfigCodec) {
		super(featureConfigCodec);
	}

	@Override
	public boolean place(FeaturePlaceContext<OreConfiguration> placeContext) {
		WorldGenLevel reader = placeContext.level();
		if (reader.getLevel().dimension().location().equals(new ResourceLocation(CandyWorld.MOD_ID, "candy_world"))) {
			return super.place(placeContext);
		}
		return false;
	}
}
