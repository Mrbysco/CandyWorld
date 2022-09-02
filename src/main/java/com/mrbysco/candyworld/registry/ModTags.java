package com.mrbysco.candyworld.registry;

import com.mrbysco.candyworld.CandyWorld;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public class ModTags {
	public static final TagKey<Item> CHOCOLATE_BARS = ItemTags.create(new ResourceLocation(CandyWorld.MOD_ID, "chocolate_bars"));
	public static final TagKey<Item> GUMMYS = ItemTags.create(new ResourceLocation(CandyWorld.MOD_ID, "gummys"));
	public static final TagKey<Item> CHOCOLATE_EGGS = ItemTags.create(new ResourceLocation(CandyWorld.MOD_ID, "chocolate_eggs"));

	public static final TagKey<Block> SUGAR = BlockTags.create(new ResourceLocation(CandyWorld.MOD_ID, "sugar"));
	public static final TagKey<Block> GUMMY = BlockTags.create(new ResourceLocation(CandyWorld.MOD_ID, "gummy"));
	public static final TagKey<Block> BROWNIE = BlockTags.create(new ResourceLocation(CandyWorld.MOD_ID, "brownie"));
	public static final TagKey<Block> COVERED_BROWNIE = BlockTags.create(new ResourceLocation(CandyWorld.MOD_ID, "covered_brownie"));
	public static final TagKey<Block> CANDY_SOIL = BlockTags.create(new ResourceLocation(CandyWorld.MOD_ID, "candy_soil"));

	public static final TagKey<Fluid> CANDY = FluidTags.create(new ResourceLocation(CandyWorld.MOD_ID, "candy"));

}
