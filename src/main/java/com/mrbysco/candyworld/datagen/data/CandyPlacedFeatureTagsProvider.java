package com.mrbysco.candyworld.datagen.data;

import com.mrbysco.candyworld.CandyWorld;
import com.mrbysco.candyworld.registry.ModTags;
import com.mrbysco.candyworld.world.ModPlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class CandyPlacedFeatureTagsProvider extends TagsProvider<PlacedFeature> {
	public CandyPlacedFeatureTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture,
	                                         @Nullable ExistingFileHelper existingFileHelper) {
		super(output, Registries.PLACED_FEATURE, completableFuture, CandyWorld.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		this.tag(ModTags.CANDY_ORES).add(
				ModPlacedFeatures.ORE_MILK_BROWNIE,
				ModPlacedFeatures.ORE_WHITE_BROWNIE,
				ModPlacedFeatures.ORE_DARK_BROWNIE,
				ModPlacedFeatures.ORE_TELEPORT,
				ModPlacedFeatures.ORE_SUGAR_SAND,
				ModPlacedFeatures.ORE_MILK_BROWNIE_OVERWORLD,
				ModPlacedFeatures.ORE_WHITE_BROWNIE_OVERWORLD,
				ModPlacedFeatures.ORE_DARK_BROWNIE_OVERWORLD,
				ModPlacedFeatures.ORE_SUGAR_BLOCK,
				ModPlacedFeatures.ORE_COOKIE,
				ModPlacedFeatures.ORE_SUGAR_COOKIE
		);
	}
}
