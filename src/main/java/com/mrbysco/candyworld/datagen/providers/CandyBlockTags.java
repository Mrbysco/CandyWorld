package com.mrbysco.candyworld.datagen.providers;

import com.mrbysco.candyworld.CandyWorld;
import com.mrbysco.candyworld.registry.ModTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

import static com.mrbysco.candyworld.registry.ModBlocks.*;

public class CandyBlockTags extends BlockTagsProvider {
	public CandyBlockTags(DataGenerator dataGenerator, @Nullable ExistingFileHelper existingFileHelper) {
		super(dataGenerator, CandyWorld.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		this.tag(ModTags.BROWNIE).add(MILK_BROWNIE_BLOCK.get(), WHITE_BROWNIE_BLOCK.get(), DARK_BROWNIE_BLOCK.get());
		this.tag(ModTags.COVERED_BROWNIE).add(CANDY_GRASS_BLOCK.get(), CHOCOLATE_COVERED_WHITE_BROWNIE.get(), DARK_CANDY_GRASS.get());
		this.tag(ModTags.CANDY_SOIL).addTags(ModTags.BROWNIE, ModTags.COVERED_BROWNIE);

		this.tag(ModTags.GUMMY).add(RED_GUMMY_BLOCK.get(), ORANGE_GUMMY_BLOCK.get(), YELLOW_GUMMY_BLOCK.get(), WHITE_GUMMY_BLOCK.get(), GREEN_GUMMY_BLOCK.get());
		this.tag(ModTags.SUGAR).add(CRYSTALLIZED_SUGAR.get());

		//Minecraft tags
		this.tag(BlockTags.SAPLINGS).add(CHOCOLATE_SAPLING.get(), COTTON_CANDY_SAPLING.get());
		this.tag(BlockTags.LEAVES).add(COTTON_CANDY_LEAVES.get(), MILK_CHOCOLATE_LEAVES.get(), DARK_CHOCOLATE_LEAVES.get(), WHITE_CHOCOLATE_LEAVES.get());
	}
}
