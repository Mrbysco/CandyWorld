package com.mrbysco.candyworld.registry;

import com.mrbysco.candyworld.CandyWorld;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;

public class ModTags {
	public static final ITag.INamedTag<Item> CHOCOLATE_BARS = ItemTags.bind(CandyWorld.MOD_ID + ":chocolate_bars");
	public static final ITag.INamedTag<Item> GUMMYS = ItemTags.bind(CandyWorld.MOD_ID + ":gummys");
	public static final ITag.INamedTag<Item> CHOCOLATE_EGGS = ItemTags.bind(CandyWorld.MOD_ID + ":chocolate_eggs");

	public static final ITag.INamedTag<Block> SUGAR = BlockTags.bind(CandyWorld.MOD_ID + ":sugar");
	public static final ITag.INamedTag<Block> GUMMY = BlockTags.bind(CandyWorld.MOD_ID + ":gummy");
	public static final ITag.INamedTag<Block> BROWNIE = BlockTags.bind(CandyWorld.MOD_ID + ":brownie");
	public static final ITag.INamedTag<Block> COVERED_BROWNIE = BlockTags.bind(CandyWorld.MOD_ID + ":covered_brownie");
	public static final ITag.INamedTag<Block> CANDY_SOIL = BlockTags.bind(CandyWorld.MOD_ID + ":candy_soil");

	public static final ITag.INamedTag<Fluid> CANDY = FluidTags.bind(CandyWorld.MOD_ID + ":candy");

}
