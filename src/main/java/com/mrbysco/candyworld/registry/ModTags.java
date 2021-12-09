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
	public static final ITag.INamedTag<Item> CHOCOLATE_BARS_DARK = ItemTags.bind(CandyWorld.MOD_ID + ":chocolate_bars/dark");
	public static final ITag.INamedTag<Item> CHOCOLATE_BARS_MILK = ItemTags.bind(CandyWorld.MOD_ID + ":chocolate_bars/milk");
	public static final ITag.INamedTag<Item> CHOCOLATE_BARS_WHITE = ItemTags.bind(CandyWorld.MOD_ID + ":chocolate_bars/white");

	public static final ITag.INamedTag<Item> CHOCOLATE_EGGS = ItemTags.bind(CandyWorld.MOD_ID + ":chocolate_eggs");
	public static final ITag.INamedTag<Item> CHOCOLATE_EGGS_DARK = ItemTags.bind(CandyWorld.MOD_ID + ":chocolate_eggs/dark");
	public static final ITag.INamedTag<Item> CHOCOLATE_EGGS_MILK = ItemTags.bind(CandyWorld.MOD_ID + ":chocolate_eggs/milk");
	public static final ITag.INamedTag<Item> CHOCOLATE_EGGS_WHITE = ItemTags.bind(CandyWorld.MOD_ID + ":chocolate_eggs/white");

	public static final ITag.INamedTag<Item> GUMMYS = ItemTags.bind(CandyWorld.MOD_ID + ":gummys");
	public static final ITag.INamedTag<Item> GUMMYS_GREEN = ItemTags.bind(CandyWorld.MOD_ID + ":gummys/green");
	public static final ITag.INamedTag<Item> GUMMYS_ORANGE = ItemTags.bind(CandyWorld.MOD_ID + ":gummys/orange");
	public static final ITag.INamedTag<Item> GUMMYS_RED = ItemTags.bind(CandyWorld.MOD_ID + ":gummys/red");
	public static final ITag.INamedTag<Item> GUMMYS_WHITE = ItemTags.bind(CandyWorld.MOD_ID + ":gummys/white");
	public static final ITag.INamedTag<Item> GUMMYS_YELLOW = ItemTags.bind(CandyWorld.MOD_ID + ":gummys/yellow");

	public static final ITag.INamedTag<Item> GUMMY_WORMS = ItemTags.bind(CandyWorld.MOD_ID + ":gummy_worms");
	public static final ITag.INamedTag<Item> GUMMY_WORMS_GREEN = ItemTags.bind(CandyWorld.MOD_ID + ":gummy_worms/green");
	public static final ITag.INamedTag<Item> GUMMY_WORMS_ORANGE = ItemTags.bind(CandyWorld.MOD_ID + ":gummy_worms/orange");
	public static final ITag.INamedTag<Item> GUMMY_WORMS_RED = ItemTags.bind(CandyWorld.MOD_ID + ":gummy_worms/red");
	public static final ITag.INamedTag<Item> GUMMY_WORMS_WHITE = ItemTags.bind(CandyWorld.MOD_ID + ":gummy_worms/white");
	public static final ITag.INamedTag<Item> GUMMY_WORMS_YELLOW = ItemTags.bind(CandyWorld.MOD_ID + ":gummy_worms/yellow");

	public static final ITag.INamedTag<Block> SUGAR = BlockTags.bind(CandyWorld.MOD_ID + ":sugar");
	public static final ITag.INamedTag<Block> GUMMY = BlockTags.bind(CandyWorld.MOD_ID + ":gummy");
	public static final ITag.INamedTag<Block> BROWNIE = BlockTags.bind(CandyWorld.MOD_ID + ":brownie");
	public static final ITag.INamedTag<Block> COVERED_BROWNIE = BlockTags.bind(CandyWorld.MOD_ID + ":covered_brownie");
	public static final ITag.INamedTag<Block> CANDY_SOIL = BlockTags.bind(CandyWorld.MOD_ID + ":candy_soil");

	public static final ITag.INamedTag<Fluid> CANDY = FluidTags.bind(CandyWorld.MOD_ID + ":candy");

}
