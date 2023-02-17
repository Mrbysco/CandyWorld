package com.mrbysco.candyworld.datagen;

import com.mrbysco.candyworld.datagen.assets.CandyItemModelProvider;
import com.mrbysco.candyworld.datagen.assets.CandyLanguageProvider;
import com.mrbysco.candyworld.datagen.assets.CandyStateProvider;
import com.mrbysco.candyworld.datagen.data.CandyLoot;
import com.mrbysco.candyworld.datagen.data.CandyRecipes;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModDatagen {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper helper = event.getExistingFileHelper();

		if (event.includeServer()) {
			generator.addProvider(new CandyLoot(generator));
			generator.addProvider(new CandyRecipes(generator));
		}
		if (event.includeClient()) {
			generator.addProvider(new CandyStateProvider(generator, helper));
			generator.addProvider(new CandyItemModelProvider(generator, helper));
			generator.addProvider(new CandyLanguageProvider(generator));
		}
	}
}
