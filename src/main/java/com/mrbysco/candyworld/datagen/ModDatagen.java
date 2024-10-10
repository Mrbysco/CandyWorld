package com.mrbysco.candyworld.datagen;

import com.mrbysco.candyworld.CandyWorld;
import com.mrbysco.candyworld.datagen.assets.CandyItemModelProvider;
import com.mrbysco.candyworld.datagen.assets.CandyLanguageProvider;
import com.mrbysco.candyworld.datagen.assets.CandyStateProvider;
import com.mrbysco.candyworld.datagen.data.CandyBiomeTagsProvider;
import com.mrbysco.candyworld.datagen.data.CandyPlacedFeatureTagsProvider;
import com.mrbysco.candyworld.datagen.data.CandyRecipes;
import com.mrbysco.candyworld.datagen.data.LootGenerator;
import com.mrbysco.candyworld.world.ModBiomes;
import com.mrbysco.candyworld.world.ModConfiguredFeatures;
import com.mrbysco.candyworld.world.ModPlacedFeatures;
import com.mrbysco.candyworld.world.WorldgenHandler;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModDatagen {
	@SubscribeEvent
	public static void gatherData(final GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();
		CompletableFuture<HolderLookup.Provider> lookupProvider = CompletableFuture.supplyAsync(ModDatagen::getProvider);
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

		generator.addProvider(event.includeClient(), new CandyItemModelProvider(output, existingFileHelper));
		generator.addProvider(event.includeClient(), new CandyLanguageProvider(output));
		generator.addProvider(event.includeClient(), new CandyStateProvider(output, existingFileHelper));

		generator.addProvider(event.includeServer(), new CandyRecipes(output));
		generator.addProvider(event.includeServer(), new LootGenerator(output, lookupProvider));
		generator.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(
				output, CompletableFuture.supplyAsync(ModDatagen::getProvider), Set.of(CandyWorld.MOD_ID)));
		generator.addProvider(event.includeServer(), new CandyBiomeTagsProvider(output, lookupProvider, existingFileHelper));
		generator.addProvider(event.includeServer(), new CandyPlacedFeatureTagsProvider(output, lookupProvider, existingFileHelper));
	}

	private static HolderLookup.Provider getProvider() {
		final RegistrySetBuilder registryBuilder = new RegistrySetBuilder();
		registryBuilder.add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
		registryBuilder.add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
		registryBuilder.add(ForgeRegistries.Keys.BIOME_MODIFIERS, WorldgenHandler::bootstrap);
		// We need the BIOME registry to be present so we can use a biome tag, doesn't matter if it's empty
		registryBuilder.add(Registries.BIOME, ModBiomes::bootstrap);
		RegistryAccess.Frozen regAccess = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY);
		return registryBuilder.buildPatch(regAccess, VanillaRegistries.createLookup());
	}
}
