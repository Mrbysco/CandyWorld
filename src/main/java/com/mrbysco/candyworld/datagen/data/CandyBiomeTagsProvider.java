package com.mrbysco.candyworld.datagen.data;

import com.mrbysco.candyworld.CandyWorld;
import com.mrbysco.candyworld.registry.ModTags;
import com.mrbysco.candyworld.world.ModBiomes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class CandyBiomeTagsProvider extends BiomeTagsProvider {
	public CandyBiomeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture,
	                              @Nullable ExistingFileHelper existingFileHelper) {
		super(output, completableFuture, CandyWorld.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		//TODO: Replace this with a regular add() once ModBiomes is properly defined!
		this.tag(ModTags.IS_CANDY).addOptional(ModBiomes.CHOCOLATE_FOREST.location());
		this.tag(ModTags.IS_CANDY).addOptional(ModBiomes.COTTON_CANDY_FOREST.location());
		this.tag(ModTags.IS_CANDY).addOptional(ModBiomes.GUMMY_SWAMP.location());
	}
}
