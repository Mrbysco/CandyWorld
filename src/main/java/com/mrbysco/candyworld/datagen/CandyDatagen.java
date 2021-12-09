package com.mrbysco.candyworld.datagen;

import com.mrbysco.candyworld.datagen.providers.CandyBlockTags;
import com.mrbysco.candyworld.datagen.providers.CandyFluidTags;
import com.mrbysco.candyworld.datagen.providers.CandyItemTags;
import com.mrbysco.candyworld.datagen.providers.CandyLootTables;
import com.mrbysco.candyworld.datagen.providers.CandyRecipes;
import com.mrbysco.candyworld.datagen.providers.client.CandyBlockModels;
import com.mrbysco.candyworld.datagen.providers.client.CandyBlockStates;
import com.mrbysco.candyworld.datagen.providers.client.CandyItemModels;
import com.mrbysco.candyworld.datagen.providers.client.CandyLanguage;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CandyDatagen {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper helper = event.getExistingFileHelper();

		if (event.includeServer()) {
			generator.addProvider(new CandyLootTables(generator));
			generator.addProvider(new CandyRecipes(generator));

			generator.addProvider(new CandyFluidTags(generator, helper));
			BlockTagsProvider blockTagsProvider = new CandyBlockTags(generator, helper);
			generator.addProvider(blockTagsProvider);
			generator.addProvider(new CandyItemTags(generator, blockTagsProvider, helper));
		}
		if (event.includeClient()) {
			generator.addProvider(new CandyLanguage(generator));

			//Unused, maybe in the future
			generator.addProvider(new CandyBlockModels(generator, helper));
			generator.addProvider(new CandyItemModels(generator, helper));
			generator.addProvider(new CandyBlockStates(generator, helper));
		}
	}
}
