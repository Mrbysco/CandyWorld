package com.mrbysco.candyworld.registry;

import com.mrbysco.candyworld.CandyWorld;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.Tag;
import net.minecraft.tags.ItemTags;

public class ModTags {
	public static final Tag.Named<Item> CHOCOLATE_BARS = ItemTags.bind(CandyWorld.MOD_ID + ":chocolate_bars");
	public static final Tag.Named<Item> GUMMYS = ItemTags.bind(CandyWorld.MOD_ID + ":gummys");
	public static final Tag.Named<Item> CHOCOLATE_EGGS = ItemTags.bind(CandyWorld.MOD_ID + ":chocolate_eggs");

	public static final Tag.Named<Block> SUGAR = BlockTags.bind(CandyWorld.MOD_ID + ":sugar");
	public static final Tag.Named<Block> GUMMY = BlockTags.bind(CandyWorld.MOD_ID + ":gummy");
	public static final Tag.Named<Block> BROWNIE = BlockTags.bind(CandyWorld.MOD_ID + ":brownie");
	public static final Tag.Named<Block> COVERED_BROWNIE = BlockTags.bind(CandyWorld.MOD_ID + ":covered_brownie");
	public static final Tag.Named<Block> CANDY_SOIL = BlockTags.bind(CandyWorld.MOD_ID + ":candy_soil");

	public static final Tag.Named<Fluid> CANDY = FluidTags.bind(CandyWorld.MOD_ID + ":candy");

}
