package com.mrbysco.candyworld.datagen.providers;

import com.mrbysco.candyworld.CandyWorld;
import com.mrbysco.candyworld.registry.ModTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import static com.mrbysco.candyworld.registry.ModItems.*;

import javax.annotation.Nullable;

public class CandyItemTags extends ItemTagsProvider {
	public CandyItemTags(DataGenerator dataGenerator, BlockTagsProvider blockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
		super(dataGenerator, blockTagsProvider, CandyWorld.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		this.tag(ModTags.CHOCOLATE_BARS_DARK).add(DARK_CHOCOLATE_BAR.get());
		this.tag(ModTags.CHOCOLATE_BARS_MILK).add(MILK_CHOCOLATE_BAR.get());
		this.tag(ModTags.CHOCOLATE_BARS_WHITE).add(WHITE_CHOCOLATE_BAR.get());
		this.tag(ModTags.CHOCOLATE_BARS).addTags(ModTags.CHOCOLATE_BARS_DARK, ModTags.CHOCOLATE_BARS_MILK, ModTags.CHOCOLATE_BARS_WHITE);

		this.tag(ModTags.CHOCOLATE_EGGS_DARK).add(DARK_CHOCOLATE_EGG.get());
		this.tag(ModTags.CHOCOLATE_EGGS_MILK).add(MILK_CHOCOLATE_EGG.get());
		this.tag(ModTags.CHOCOLATE_EGGS_WHITE).add(WHITE_CHOCOLATE_EGG.get());
		this.tag(ModTags.CHOCOLATE_EGGS).addTags(ModTags.CHOCOLATE_EGGS_DARK, ModTags.CHOCOLATE_EGGS_MILK, ModTags.CHOCOLATE_EGGS_WHITE);

		this.tag(ModTags.GUMMYS_GREEN).add(GREEN_GUMMY.get());
		this.tag(ModTags.GUMMYS_ORANGE).add(ORANGE_GUMMY.get());
		this.tag(ModTags.GUMMYS_RED).add(RED_GUMMY.get());
		this.tag(ModTags.GUMMYS_WHITE).add(WHITE_GUMMY.get());
		this.tag(ModTags.GUMMYS_YELLOW).add(YELLOW_GUMMY.get());
		this.tag(ModTags.GUMMY_WORMS_GREEN).add(GREEN_GUMMY_WORM.get());
		this.tag(ModTags.GUMMY_WORMS_ORANGE).add(ORANGE_GUMMY_WORM.get());
		this.tag(ModTags.GUMMY_WORMS_RED).add(RED_GUMMY_WORM.get());
		this.tag(ModTags.GUMMY_WORMS_WHITE).add(WHITE_GUMMY_WORM.get());
		this.tag(ModTags.GUMMY_WORMS_YELLOW).add(YELLOW_GUMMY_WORM.get());
		this.tag(ModTags.GUMMY_WORMS).addTags(ModTags.GUMMY_WORMS_GREEN, ModTags.GUMMY_WORMS_ORANGE, ModTags.GUMMY_WORMS_RED,
				ModTags.GUMMY_WORMS_WHITE, ModTags.GUMMY_WORMS_YELLOW);
		this.tag(ModTags.GUMMYS).addTags(ModTags.GUMMYS_GREEN, ModTags.GUMMYS_ORANGE, ModTags.GUMMYS_RED, ModTags.GUMMYS_WHITE,
				ModTags.GUMMYS_YELLOW, ModTags.GUMMY_WORMS);
	}
}
