package com.mrbysco.candyworld.datagen.data;

import com.google.gson.JsonObject;
import com.mrbysco.candyworld.registry.ModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.HashCache;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;

import java.nio.file.Path;
import java.util.function.Consumer;

import static com.mrbysco.candyworld.registry.ModBlocks.*;
import static com.mrbysco.candyworld.registry.ModItems.*;

public class CandyRecipes extends RecipeProvider {
	public CandyRecipes(DataGenerator gen) {
		super(gen);
	}

	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.SUGAR),
				COTTON_CANDY.get(), 0.35F, 200).unlockedBy("has_sugar", has(Items.SUGAR)).save(consumer);

		SimpleCookingRecipeBuilder.smelting(Ingredient.of(MILK_CHOCOLATE_EGG.get()),
						MILK_CHOCOLATE_BAR.get(), 0.4F, 200).unlockedBy("has_milk_chocolate_egg", has(MILK_CHOCOLATE_EGG.get()))
				.save(consumer, "candyworld:milk_chocolate_bar_from_smelting");
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(WHITE_CHOCOLATE_EGG.get()),
						WHITE_CHOCOLATE_BAR.get(), 0.4F, 200).unlockedBy("has_white_chocolate_egg", has(WHITE_CHOCOLATE_EGG.get()))
				.save(consumer, "candyworld:white_chocolate_bar_from_smelting");
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(DARK_CHOCOLATE_EGG.get()),
						DARK_CHOCOLATE_EGG.get(), 0.4F, 200).unlockedBy("has_dark_chocolate_egg", has(DARK_CHOCOLATE_EGG.get()))
				.save(consumer, "candyworld:dark_chocolate_bar_from_smelting");

		SimpleCookingRecipeBuilder.smelting(Ingredient.of(MILK_CHOCOLATE_BRICK.get()),
						MILK_CHOCOLATE_BLOCK.get(), 0.4F, 200).unlockedBy("has_milk_chocolate_brick", has(MILK_CHOCOLATE_BRICK.get()))
				.save(consumer, "candyworld:milk_chocolate_block_from_smelting");
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(WHITE_CHOCOLATE_BRICK.get()),
						WHITE_CHOCOLATE_BLOCK.get(), 0.4F, 200).unlockedBy("has_white_chocolate_brick", has(WHITE_CHOCOLATE_BRICK.get()))
				.save(consumer, "candyworld:white_chocolate_block_from_smelting");
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(DARK_CHOCOLATE_BRICK.get()),
						DARK_CHOCOLATE_BLOCK.get(), 0.4F, 200).unlockedBy("has_dark_chocolate_brick", has(DARK_CHOCOLATE_BRICK.get()))
				.save(consumer, "candyworld:dark_chocolate_block_from_smelting");

		ShapedRecipeBuilder.shaped(BUTTER.get())
				.define('S', Tags.Items.RODS_WOODEN).define('M', Items.MILK_BUCKET)
				.pattern("S").pattern("M").unlockedBy("has_milk_bucket", has(Items.MILK_BUCKET)).save(consumer);


		ShapedRecipeBuilder.shaped(WAFER_STICK.get(), 3)
				.define('W', Tags.Items.CROPS_WHEAT).define('C', ModTags.CHOCOLATE_BARS)
				.pattern("WW").pattern("WC").unlockedBy("has_wheat", has(Items.WHEAT)).save(consumer);

		ShapelessRecipeBuilder.shapeless(WAFER_STICK.get(), 4)
				.requires(WAFER_STICK_BLOCK.get()).unlockedBy("has_wafer_stick_block", has(WAFER_STICK_BLOCK.get()))
				.save(consumer, "candyworld:wafer_stick_from_block");

		ShapedRecipeBuilder.shaped(WAFER_STICK_BLOCK.get())
				.define('#', WAFER_STICK.get()).pattern("##").pattern("##").unlockedBy("has_wafer_stick", has(WAFER_STICK.get())).save(consumer);

		ShapelessRecipeBuilder.shapeless(SUGAR_CRYSTAL.get(), 4)
				.requires(CRYSTALLIZED_SUGAR.get()).unlockedBy("has_crystallized_sugar", has(CRYSTALLIZED_SUGAR.get()))
				.save(consumer);

		ShapedRecipeBuilder.shaped(CRYSTALLIZED_SUGAR.get())
				.define('#', SUGAR_CRYSTAL.get()).pattern("##").pattern("##").unlockedBy("has_sugar_crystal", has(SUGAR_CRYSTAL.get())).save(consumer);

		ShapelessRecipeBuilder.shapeless(WHITE_CANDY_CANE.get(), 4)
				.requires(WHITE_CANDY_CANE_BLOCK.get()).unlockedBy("has_white_candy_cane_block", has(WHITE_CANDY_CANE_BLOCK.get()))
				.save(consumer, "candyworld:white_candy_cane_from_block");
		ShapelessRecipeBuilder.shapeless(WHITE_CANDY_CANE.get(), 4)
				.requires(Items.SUGAR).requires(Items.SUGAR).requires(Items.WATER_BUCKET).unlockedBy("has_sugar", has(Items.SUGAR))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RED_CANDY_CANE.get(), 4)
				.requires(RED_CANDY_CANE_BLOCK.get()).unlockedBy("has_red_candy_cane_block", has(RED_CANDY_CANE_BLOCK.get()))
				.save(consumer, "candyworld:red_candy_cane_from_block");
		ShapelessRecipeBuilder.shapeless(RED_CANDY_CANE.get(), 4)
				.requires(Items.SUGAR).requires(Items.SUGAR).requires(Items.WATER_BUCKET).requires(Tags.Items.DYES_RED)
				.unlockedBy("has_sugar", has(Items.SUGAR)).save(consumer);

		ShapelessRecipeBuilder.shapeless(GREEN_CANDY_CANE.get(), 4)
				.requires(GREEN_CANDY_CANE_BLOCK.get()).unlockedBy("has_green_candy_cane_block", has(GREEN_CANDY_CANE_BLOCK.get()))
				.save(consumer, "candyworld:green_candy_cane_from_block");
		ShapelessRecipeBuilder.shapeless(GREEN_CANDY_CANE.get(), 4)
				.requires(Items.SUGAR).requires(Items.SUGAR).requires(Items.WATER_BUCKET).requires(Tags.Items.DYES_GREEN)
				.unlockedBy("has_sugar", has(Items.SUGAR)).save(consumer);

		ShapelessRecipeBuilder.shapeless(WHITE_RED_CANDY_CANE.get(), 4)
				.requires(WHITE_RED_CANDY_CANE_BLOCK.get()).unlockedBy("has_white_red_candy_cane_block", has(WHITE_RED_CANDY_CANE_BLOCK.get()))
				.save(consumer, "candyworld:white_red_candy_cane_from_block");
		ShapelessRecipeBuilder.shapeless(WHITE_RED_CANDY_CANE.get(), 4)
				.requires(Items.SUGAR).requires(Items.SUGAR).requires(Items.WATER_BUCKET).requires(Tags.Items.DYES_RED).requires(Tags.Items.DYES_WHITE)
				.unlockedBy("has_sugar", has(Items.SUGAR)).save(consumer);

		ShapelessRecipeBuilder.shapeless(WHITE_GREEN_CANDY_CANE.get(), 4)
				.requires(WHITE_GREEN_CANDY_CANE_BLOCK.get()).unlockedBy("has_white_green_candy_cane_block", has(WHITE_GREEN_CANDY_CANE_BLOCK.get()))
				.save(consumer, "candyworld:white_green_candy_cane_from_block");
		ShapelessRecipeBuilder.shapeless(WHITE_GREEN_CANDY_CANE.get(), 4)
				.requires(Items.SUGAR).requires(Items.SUGAR).requires(Items.WATER_BUCKET).requires(Tags.Items.DYES_GREEN).requires(Tags.Items.DYES_WHITE)
				.unlockedBy("has_sugar", has(Items.SUGAR)).save(consumer);

		ShapelessRecipeBuilder.shapeless(RED_GREEN_CANDY_CANE.get(), 4)
				.requires(RED_GREEN_CANDY_CANE_BLOCK.get()).unlockedBy("red_green_candy_cane_block", has(RED_GREEN_CANDY_CANE_BLOCK.get()))
				.save(consumer, "candyworld:red_green_candy_cane_from_block");
		ShapelessRecipeBuilder.shapeless(RED_GREEN_CANDY_CANE.get(), 4)
				.requires(Items.SUGAR).requires(Items.SUGAR).requires(Items.WATER_BUCKET).requires(Tags.Items.DYES_GREEN).requires(Tags.Items.DYES_RED)
				.unlockedBy("has_sugar", has(Items.SUGAR)).save(consumer);

		ShapelessRecipeBuilder.shapeless(MILK_BROWNIE.get(), 6)
				.requires(Items.SUGAR).requires(MILK_CHOCOLATE_EGG.get()).requires(Tags.Items.CROPS_WHEAT).requires(BUTTER.get())
				.unlockedBy("has_butter", has(BUTTER.get())).save(consumer);
		ShapelessRecipeBuilder.shapeless(MILK_BROWNIE.get(), 6)
				.requires(Items.SUGAR).requires(Tags.Items.EGGS).requires(MILK_CHOCOLATE_BAR.get()).requires(Tags.Items.CROPS_WHEAT).requires(BUTTER.get())
				.unlockedBy("has_butter", has(BUTTER.get())).save(consumer, "candyworld:milk_brownie_from_bar");
		ShapelessRecipeBuilder.shapeless(MILK_BROWNIE.get(), 4)
				.requires(MILK_BROWNIE_BLOCK.get()).unlockedBy("has_milk_brownie_block", has(MILK_BROWNIE_BLOCK.get()))
				.save(consumer, "candyworld:milk_brownie_from_block");

		ShapelessRecipeBuilder.shapeless(WHITE_BROWNIE.get(), 6)
				.requires(Items.SUGAR).requires(WHITE_CHOCOLATE_EGG.get()).requires(Tags.Items.CROPS_WHEAT).requires(BUTTER.get())
				.unlockedBy("has_butter", has(BUTTER.get())).save(consumer);
		ShapelessRecipeBuilder.shapeless(WHITE_BROWNIE.get(), 6)
				.requires(Items.SUGAR).requires(Tags.Items.EGGS).requires(WHITE_CHOCOLATE_BAR.get()).requires(Tags.Items.CROPS_WHEAT).requires(BUTTER.get())
				.unlockedBy("has_butter", has(BUTTER.get())).save(consumer, "candyworld:white_brownie_from_bar");
		ShapelessRecipeBuilder.shapeless(WHITE_BROWNIE.get(), 4)
				.requires(WHITE_BROWNIE_BLOCK.get()).unlockedBy("has_white_brownie_block", has(WHITE_BROWNIE_BLOCK.get()))
				.save(consumer, "candyworld:white_brownie_from_block");

		ShapelessRecipeBuilder.shapeless(DARK_BROWNIE.get(), 6)
				.requires(Items.SUGAR).requires(DARK_CHOCOLATE_EGG.get()).requires(Tags.Items.CROPS_WHEAT).requires(BUTTER.get())
				.unlockedBy("has_butter", has(BUTTER.get())).save(consumer);
		ShapelessRecipeBuilder.shapeless(DARK_BROWNIE.get(), 6)
				.requires(Items.SUGAR).requires(Tags.Items.EGGS).requires(DARK_CHOCOLATE_BAR.get()).requires(Tags.Items.CROPS_WHEAT).requires(BUTTER.get())
				.unlockedBy("has_butter", has(BUTTER.get())).save(consumer, "candyworld:dark_brownie_from_bar");
		ShapelessRecipeBuilder.shapeless(DARK_BROWNIE.get(), 4)
				.requires(DARK_BROWNIE_BLOCK.get()).unlockedBy("has_dark_brownie_block", has(DARK_BROWNIE_BLOCK.get()))
				.save(consumer, "candyworld:dark_brownie_from_block");

		ShapelessRecipeBuilder.shapeless(MILK_CHOCOLATE_BAR.get(), 4)
				.requires(MILK_CHOCOLATE_BLOCK.get()).unlockedBy("has_milk_chocolate_block", has(MILK_CHOCOLATE_BLOCK.get()))
				.save(consumer, "candyworld:milk_chocolate_bar_from_block");
		ShapelessRecipeBuilder.shapeless(MILK_CHOCOLATE_BAR.get(), 4)
				.requires(MILK_CHOCOLATE_BRICK.get()).unlockedBy("has_milk_chocolate_block", has(MILK_CHOCOLATE_BRICK.get()))
				.save(consumer, "candyworld:milk_chocolate_bar_from_brick");
		ShapelessRecipeBuilder.shapeless(MILK_CHOCOLATE_BAR.get(), 3)
				.requires(Items.COCOA_BEANS).requires(Items.SUGAR).requires(Items.MILK_BUCKET)
				.unlockedBy("has_cocoa_beans", has(Items.COCOA_BEANS))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(WHITE_CHOCOLATE_BAR.get(), 4)
				.requires(WHITE_CHOCOLATE_BLOCK.get()).unlockedBy("has_white_chocolate_block", has(WHITE_CHOCOLATE_BLOCK.get()))
				.save(consumer, "candyworld:white_chocolate_bar_from_block");
		ShapelessRecipeBuilder.shapeless(WHITE_CHOCOLATE_BAR.get(), 4)
				.requires(WHITE_CHOCOLATE_BRICK.get()).unlockedBy("has_white_chocolate_block", has(WHITE_CHOCOLATE_BRICK.get()))
				.save(consumer, "candyworld:white_chocolate_bar_from_brick");
		ShapelessRecipeBuilder.shapeless(WHITE_CHOCOLATE_BAR.get(), 3)
				.requires(Items.COCOA_BEANS).requires(Items.SUGAR).requires(Items.SUGAR).requires(Items.MILK_BUCKET).requires(Items.MILK_BUCKET)
				.unlockedBy("has_cocoa_beans", has(Items.COCOA_BEANS))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(DARK_CHOCOLATE_BAR.get(), 4)
				.requires(DARK_CHOCOLATE_BLOCK.get()).unlockedBy("has_dark_chocolate_block", has(DARK_CHOCOLATE_BLOCK.get()))
				.save(consumer, "candyworld:dark_chocolate_bar_from_block");
		ShapelessRecipeBuilder.shapeless(DARK_CHOCOLATE_BAR.get(), 4)
				.requires(DARK_CHOCOLATE_BRICK.get()).unlockedBy("has_dark_chocolate_block", has(DARK_CHOCOLATE_BRICK.get()))
				.save(consumer, "candyworld:dark_chocolate_bar_from_brick");
		ShapelessRecipeBuilder.shapeless(DARK_CHOCOLATE_BAR.get(), 3)
				.requires(Items.COCOA_BEANS).requires(Items.COCOA_BEANS).requires(Items.SUGAR).requires(Items.MILK_BUCKET)
				.unlockedBy("has_cocoa_beans", has(Items.COCOA_BEANS))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(RED_GUMMY.get(), 4)
				.requires(Items.SUGAR).requires(Items.SUGAR).requires(Items.WATER_BUCKET)
				.requires(Tags.Items.DYES_WHITE).requires(Tags.Items.DYES_WHITE).requires(Tags.Items.DYES_RED)
				.unlockedBy("has_red_dye", has(Tags.Items.DYES_RED))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(RED_GUMMY.get(), 3)
				.requires(RED_GUMMY_WORM.get()).unlockedBy("has_red_gummy_worm", has(RED_GUMMY_WORM.get()))
				.save(consumer, "candyworld:red_gummy_from_worm");
		ShapelessRecipeBuilder.shapeless(RED_GUMMY.get(), 4)
				.requires(RED_GUMMY_BLOCK.get()).unlockedBy("has_red_gummy_block", has(RED_GUMMY_BLOCK.get()))
				.save(consumer, "candyworld:red_gummy_from_block");

		ShapelessRecipeBuilder.shapeless(ORANGE_GUMMY.get(), 4)
				.requires(Items.SUGAR).requires(Items.SUGAR).requires(Items.WATER_BUCKET)
				.requires(Tags.Items.DYES_WHITE).requires(Tags.Items.DYES_WHITE).requires(Tags.Items.DYES_ORANGE)
				.unlockedBy("has_orange_dye", has(Tags.Items.DYES_ORANGE))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(ORANGE_GUMMY.get(), 3)
				.requires(ORANGE_GUMMY_WORM.get()).unlockedBy("has_orange_gummy_worm", has(ORANGE_GUMMY_WORM.get()))
				.save(consumer, "candyworld:orange_gummy_from_worm");
		ShapelessRecipeBuilder.shapeless(ORANGE_GUMMY.get(), 4)
				.requires(ORANGE_GUMMY_BLOCK.get()).unlockedBy("has_orange_gummy_block", has(ORANGE_GUMMY_BLOCK.get()))
				.save(consumer, "candyworld:orange_gummy_from_block");

		ShapelessRecipeBuilder.shapeless(YELLOW_GUMMY.get(), 4)
				.requires(Items.SUGAR).requires(Items.SUGAR).requires(Items.WATER_BUCKET)
				.requires(Tags.Items.DYES_WHITE).requires(Tags.Items.DYES_WHITE).requires(Tags.Items.DYES_YELLOW)
				.unlockedBy("has_yellow_dye", has(Tags.Items.DYES_YELLOW))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(YELLOW_GUMMY.get(), 3)
				.requires(YELLOW_GUMMY_WORM.get()).unlockedBy("has_yellow_gummy_worm", has(YELLOW_GUMMY_WORM.get()))
				.save(consumer, "candyworld:yellow_gummy_from_worm");
		ShapelessRecipeBuilder.shapeless(YELLOW_GUMMY.get(), 4)
				.requires(YELLOW_GUMMY_BLOCK.get()).unlockedBy("has_yellow_gummy_block", has(YELLOW_GUMMY_BLOCK.get()))
				.save(consumer, "candyworld:yellow_gummy_from_block");

		ShapelessRecipeBuilder.shapeless(WHITE_GUMMY.get(), 4)
				.requires(Items.SUGAR).requires(Items.SUGAR).requires(Items.WATER_BUCKET)
				.requires(Tags.Items.DYES_WHITE).requires(Tags.Items.DYES_WHITE).requires(Tags.Items.DYES_WHITE)
				.unlockedBy("has_white_dye", has(Tags.Items.DYES_WHITE))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(WHITE_GUMMY.get(), 3)
				.requires(WHITE_GUMMY_WORM.get()).unlockedBy("has_white_gummy_worm", has(WHITE_GUMMY_WORM.get()))
				.save(consumer, "candyworld:white_gummy_from_worm");
		ShapelessRecipeBuilder.shapeless(WHITE_GUMMY.get(), 4)
				.requires(WHITE_GUMMY_BLOCK.get()).unlockedBy("has_white_gummy_block", has(WHITE_GUMMY_BLOCK.get()))
				.save(consumer, "candyworld:white_gummy_from_block");

		ShapelessRecipeBuilder.shapeless(GREEN_GUMMY.get(), 4)
				.requires(Items.SUGAR).requires(Items.SUGAR).requires(Items.WATER_BUCKET)
				.requires(Tags.Items.DYES_WHITE).requires(Tags.Items.DYES_WHITE).requires(Tags.Items.DYES_GREEN)
				.unlockedBy("has_green_dye", has(Tags.Items.DYES_GREEN))
				.save(consumer);
		ShapelessRecipeBuilder.shapeless(GREEN_GUMMY.get(), 3)
				.requires(GREEN_GUMMY_WORM.get()).unlockedBy("has_green_gummy_worm", has(GREEN_GUMMY_WORM.get()))
				.save(consumer, "candyworld:green_gummy_from_worm");
		ShapelessRecipeBuilder.shapeless(GREEN_GUMMY.get(), 4)
				.requires(GREEN_GUMMY_BLOCK.get()).unlockedBy("has_green_gummy_block", has(GREEN_GUMMY_BLOCK.get()))
				.save(consumer, "candyworld:green_gummy_from_block");

		ShapedRecipeBuilder.shaped(RED_GUMMY_WORM.get())
				.define('#', RED_GUMMY.get()).pattern("  #").pattern(" # ").pattern("#  ")
				.unlockedBy("has_red_gummy", has(RED_GUMMY.get())).save(consumer);
		ShapelessRecipeBuilder.shapeless(RED_GUMMY_WORM.get(), 4)
				.requires(RED_GUMMY_WORM_BLOCK.get()).unlockedBy("has_red_gummy_worm_block", has(RED_GUMMY_WORM_BLOCK.get()))
				.save(consumer, "candyworld:red_gummy_worm_from_block");

		ShapedRecipeBuilder.shaped(ORANGE_GUMMY_WORM.get())
				.define('#', ORANGE_GUMMY.get()).pattern("  #").pattern(" # ").pattern("#  ")
				.unlockedBy("has_orange_gummy", has(ORANGE_GUMMY.get())).save(consumer);
		ShapelessRecipeBuilder.shapeless(ORANGE_GUMMY_WORM.get(), 4)
				.requires(ORANGE_GUMMY_WORM_BLOCK.get()).unlockedBy("has_orange_gummy_worm_block", has(ORANGE_GUMMY_WORM_BLOCK.get()))
				.save(consumer, "candyworld:orange_gummy_worm_from_block");

		ShapedRecipeBuilder.shaped(YELLOW_GUMMY_WORM.get())
				.define('#', YELLOW_GUMMY.get()).pattern("  #").pattern(" # ").pattern("#  ")
				.unlockedBy("has_yellow_gummy", has(YELLOW_GUMMY.get())).save(consumer);
		ShapelessRecipeBuilder.shapeless(YELLOW_GUMMY_WORM.get(), 4)
				.requires(YELLOW_GUMMY_WORM_BLOCK.get()).unlockedBy("has_yellow_gummy_worm_block", has(YELLOW_GUMMY_WORM_BLOCK.get()))
				.save(consumer, "candyworld:yellow_gummy_worm_from_block");

		ShapedRecipeBuilder.shaped(WHITE_GUMMY_WORM.get())
				.define('#', WHITE_GUMMY.get()).pattern("  #").pattern(" # ").pattern("#  ")
				.unlockedBy("has_white_gummy", has(WHITE_GUMMY.get())).save(consumer);
		ShapelessRecipeBuilder.shapeless(WHITE_GUMMY_WORM.get(), 4)
				.requires(WHITE_GUMMY_WORM_BLOCK.get()).unlockedBy("has_white_gummy_worm_block", has(WHITE_GUMMY_WORM_BLOCK.get()))
				.save(consumer, "candyworld:white_gummy_worm_from_block");

		ShapedRecipeBuilder.shaped(GREEN_GUMMY_WORM.get())
				.define('#', GREEN_GUMMY.get()).pattern("  #").pattern(" # ").pattern("#  ")
				.unlockedBy("has_green_gummy", has(GREEN_GUMMY.get())).save(consumer);
		ShapelessRecipeBuilder.shapeless(GREEN_GUMMY_WORM.get(), 4)
				.requires(GREEN_GUMMY_WORM_BLOCK.get()).unlockedBy("has_green_gummy_worm_block", has(GREEN_GUMMY_WORM_BLOCK.get()))
				.save(consumer, "candyworld:green_gummy_worm_from_block");

		ShapedRecipeBuilder.shaped(TELEPORTER.get())
				.define('P', Items.BLAZE_POWDER)
				.define('B', ModTags.CHOCOLATE_BARS)
				.define('C', Items.CHORUS_FRUIT)
				.pattern("PBP").pattern("BCB").pattern("PBP")
				.unlockedBy("has_chorus_fruit", has(Items.CHORUS_FRUIT)).save(consumer);


		ShapedRecipeBuilder.shaped(MILK_CHOCOLATE_PICKAXE.get())
				.define('B', MILK_CHOCOLATE_BAR.get()).define('W', WAFER_STICK.get())
				.pattern("BBB").pattern(" W ").pattern(" W ")
				.unlockedBy("has_milk_chocolate_bar", has(MILK_CHOCOLATE_BAR.get())).save(consumer);
		ShapedRecipeBuilder.shaped(MILK_CHOCOLATE_AXE.get())
				.define('B', MILK_CHOCOLATE_BAR.get()).define('W', WAFER_STICK.get())
				.pattern("BB ").pattern("BW ").pattern(" W ")
				.unlockedBy("has_milk_chocolate_bar", has(MILK_CHOCOLATE_BAR.get())).save(consumer);
		ShapedRecipeBuilder.shaped(MILK_CHOCOLATE_SHOVEL.get())
				.define('B', MILK_CHOCOLATE_BAR.get()).define('W', WAFER_STICK.get())
				.pattern(" B ").pattern(" W ").pattern(" W ")
				.unlockedBy("has_milk_chocolate_bar", has(MILK_CHOCOLATE_BAR.get())).save(consumer);
		ShapedRecipeBuilder.shaped(MILK_CHOCOLATE_SWORD.get())
				.define('B', MILK_CHOCOLATE_BAR.get()).define('W', WAFER_STICK.get())
				.pattern(" B ").pattern(" B ").pattern(" W ")
				.unlockedBy("has_milk_chocolate_bar", has(MILK_CHOCOLATE_BAR.get())).save(consumer);

		ShapedRecipeBuilder.shaped(WHITE_CHOCOLATE_PICKAXE.get())
				.define('B', WHITE_CHOCOLATE_BAR.get()).define('W', WAFER_STICK.get())
				.pattern("BBB").pattern(" W ").pattern(" W ")
				.unlockedBy("has_white_chocolate_bar", has(WHITE_CHOCOLATE_BAR.get())).save(consumer);
		ShapedRecipeBuilder.shaped(WHITE_CHOCOLATE_AXE.get())
				.define('B', WHITE_CHOCOLATE_BAR.get()).define('W', WAFER_STICK.get())
				.pattern("BB ").pattern("BW ").pattern(" W ")
				.unlockedBy("has_white_chocolate_bar", has(WHITE_CHOCOLATE_BAR.get())).save(consumer);
		ShapedRecipeBuilder.shaped(WHITE_CHOCOLATE_SHOVEL.get())
				.define('B', WHITE_CHOCOLATE_BAR.get()).define('W', WAFER_STICK.get())
				.pattern(" B ").pattern(" W ").pattern(" W ")
				.unlockedBy("has_white_chocolate_bar", has(WHITE_CHOCOLATE_BAR.get())).save(consumer);
		ShapedRecipeBuilder.shaped(WHITE_CHOCOLATE_SWORD.get())
				.define('B', WHITE_CHOCOLATE_BAR.get()).define('W', WAFER_STICK.get())
				.pattern(" B ").pattern(" B ").pattern(" W ")
				.unlockedBy("has_white_chocolate_bar", has(WHITE_CHOCOLATE_BAR.get())).save(consumer);

		ShapedRecipeBuilder.shaped(DARK_CHOCOLATE_PICKAXE.get())
				.define('B', DARK_CHOCOLATE_BAR.get()).define('W', WAFER_STICK.get())
				.pattern("BBB").pattern(" W ").pattern(" W ")
				.unlockedBy("has_dark_chocolate_bar", has(DARK_CHOCOLATE_BAR.get())).save(consumer);
		ShapedRecipeBuilder.shaped(DARK_CHOCOLATE_AXE.get())
				.define('B', DARK_CHOCOLATE_BAR.get()).define('W', WAFER_STICK.get())
				.pattern("BB ").pattern("BW ").pattern(" W ")
				.unlockedBy("has_dark_chocolate_bar", has(DARK_CHOCOLATE_BAR.get())).save(consumer);
		ShapedRecipeBuilder.shaped(DARK_CHOCOLATE_SHOVEL.get())
				.define('B', DARK_CHOCOLATE_BAR.get()).define('W', WAFER_STICK.get())
				.pattern(" B ").pattern(" W ").pattern(" W ")
				.unlockedBy("has_dark_chocolate_bar", has(DARK_CHOCOLATE_BAR.get())).save(consumer);
		ShapedRecipeBuilder.shaped(DARK_CHOCOLATE_SWORD.get())
				.define('B', DARK_CHOCOLATE_BAR.get()).define('W', WAFER_STICK.get())
				.pattern(" B ").pattern(" B ").pattern(" W ")
				.unlockedBy("has_dark_chocolate_bar", has(DARK_CHOCOLATE_BAR.get())).save(consumer);

		ShapedRecipeBuilder.shaped(COTTON_CANDY_PICKAXE.get())
				.define('B', COTTON_CANDY.get()).define('W', WAFER_STICK.get())
				.pattern("BBB").pattern(" W ").pattern(" W ")
				.unlockedBy("has_cotton_candy", has(COTTON_CANDY.get())).save(consumer);
		ShapedRecipeBuilder.shaped(COTTON_CANDY_AXE.get())
				.define('B', COTTON_CANDY.get()).define('W', WAFER_STICK.get())
				.pattern("BB ").pattern("BW ").pattern(" W ")
				.unlockedBy("has_cotton_candy", has(COTTON_CANDY.get())).save(consumer);
		ShapedRecipeBuilder.shaped(COTTON_CANDY_SHOVEL.get())
				.define('B', COTTON_CANDY.get()).define('W', WAFER_STICK.get())
				.pattern(" B ").pattern(" W ").pattern(" W ")
				.unlockedBy("has_cotton_candy", has(COTTON_CANDY.get())).save(consumer);
		ShapedRecipeBuilder.shaped(COTTON_CANDY_SWORD.get())
				.define('B', COTTON_CANDY.get()).define('W', WAFER_STICK.get())
				.pattern(" B ").pattern(" B ").pattern(" W ")
				.unlockedBy("has_cotton_candy", has(COTTON_CANDY.get())).save(consumer);

		ShapedRecipeBuilder.shaped(SUGAR_SAND.get())
				.define('#', Items.SUGAR).pattern("##").pattern("##")
				.unlockedBy("has_sugar", has(Items.SUGAR)).save(consumer);

		ShapedRecipeBuilder.shaped(WHITE_CANDY_CANE_BLOCK.get())
				.define('#', WHITE_CANDY_CANE.get()).pattern("##").pattern("##")
				.unlockedBy("has_white_candy_cane", has(WHITE_CANDY_CANE.get())).save(consumer);

		ShapedRecipeBuilder.shaped(RED_CANDY_CANE_BLOCK.get())
				.define('#', RED_CANDY_CANE.get()).pattern("##").pattern("##")
				.unlockedBy("has_red_candy_cane", has(RED_CANDY_CANE.get())).save(consumer);

		ShapedRecipeBuilder.shaped(GREEN_CANDY_CANE_BLOCK.get())
				.define('#', GREEN_CANDY_CANE.get()).pattern("##").pattern("##")
				.unlockedBy("has_green_candy_cane", has(GREEN_CANDY_CANE.get())).save(consumer);

		ShapedRecipeBuilder.shaped(WHITE_RED_CANDY_CANE_BLOCK.get())
				.define('#', WHITE_RED_CANDY_CANE.get()).pattern("##").pattern("##")
				.unlockedBy("has_white_red_candy_cane", has(WHITE_RED_CANDY_CANE.get())).save(consumer);
		ShapedRecipeBuilder.shaped(WHITE_RED_CANDY_CANE_BLOCK.get())
				.define('#', WHITE_CANDY_CANE.get())
				.define('@', RED_CANDY_CANE.get())
				.pattern("#@").pattern("@#").unlockedBy("has_red_candy_cane", has(RED_CANDY_CANE.get()))
				.save(consumer, "candyworld:white_red_candy_cane_block_from_colored_cane");

		ShapedRecipeBuilder.shaped(WHITE_GREEN_CANDY_CANE_BLOCK.get())
				.define('#', WHITE_GREEN_CANDY_CANE.get()).pattern("##").pattern("##")
				.unlockedBy("has_white_green_candy_cane", has(WHITE_GREEN_CANDY_CANE.get())).save(consumer);
		ShapedRecipeBuilder.shaped(WHITE_RED_CANDY_CANE_BLOCK.get())
				.define('#', WHITE_CANDY_CANE.get())
				.define('@', GREEN_CANDY_CANE.get())
				.pattern("#@").pattern("@#").unlockedBy("has_green_candy_cane", has(GREEN_CANDY_CANE.get()))
				.save(consumer, "candyworld:white_green_candy_cane_block_from_colored_cane");

		ShapedRecipeBuilder.shaped(RED_GREEN_CANDY_CANE_BLOCK.get())
				.define('#', RED_GREEN_CANDY_CANE.get()).pattern("##").pattern("##")
				.unlockedBy("has_red_green_candy_cane", has(RED_GREEN_CANDY_CANE.get())).save(consumer);
		ShapedRecipeBuilder.shaped(WHITE_RED_CANDY_CANE_BLOCK.get())
				.define('#', RED_CANDY_CANE.get())
				.define('@', GREEN_CANDY_CANE.get())
				.pattern("#@").pattern("@#").unlockedBy("has_green_candy_cane", has(GREEN_CANDY_CANE.get()))
				.save(consumer, "candyworld:red_green_candy_cane_block_from_colored_canes");

		ShapedRecipeBuilder.shaped(MILK_BROWNIE_BLOCK.get())
				.define('#', MILK_BROWNIE.get()).pattern("##").pattern("##").unlockedBy("has_milk_brownie",
						has(MILK_BROWNIE.get())).save(consumer);
		ShapedRecipeBuilder.shaped(WHITE_BROWNIE_BLOCK.get())
				.define('#', WHITE_BROWNIE.get()).pattern("##").pattern("##").unlockedBy("has_white_brownie",
						has(WHITE_BROWNIE.get())).save(consumer);
		ShapedRecipeBuilder.shaped(DARK_BROWNIE_BLOCK.get())
				.define('#', DARK_BROWNIE.get()).pattern("##").pattern("##").unlockedBy("has_dark_brownie",
						has(DARK_BROWNIE.get())).save(consumer);

		ShapedRecipeBuilder.shaped(MILK_CHOCOLATE_BRICK.get())
				.define('#', MILK_CHOCOLATE_BAR.get()).pattern("##").pattern("##").unlockedBy("has_milk_chocolate_bar",
						has(MILK_CHOCOLATE_BAR.get())).save(consumer);
		ShapedRecipeBuilder.shaped(WHITE_CHOCOLATE_BRICK.get())
				.define('#', WHITE_CHOCOLATE_BAR.get()).pattern("##").pattern("##").unlockedBy("has_white_chocolate_bar",
						has(WHITE_CHOCOLATE_BAR.get())).save(consumer);
		ShapedRecipeBuilder.shaped(DARK_CHOCOLATE_BRICK.get())
				.define('#', DARK_CHOCOLATE_BAR.get()).pattern("##").pattern("##").unlockedBy("has_dark_chocolate_bar",
						has(DARK_CHOCOLATE_BAR.get())).save(consumer);

		ShapedRecipeBuilder.shaped(MILK_CHOCOLATE_BRICK.get(), 4)
				.define('#', MILK_CHOCOLATE_BLOCK.get()).pattern("##").pattern("##").unlockedBy("has_milk_chocolate_block",
						has(MILK_CHOCOLATE_BLOCK.get())).save(consumer, "candyworld:milk_chocolate_brick_from_block");
		ShapedRecipeBuilder.shaped(WHITE_CHOCOLATE_BRICK.get(), 4)
				.define('#', WHITE_CHOCOLATE_BLOCK.get()).pattern("##").pattern("##").unlockedBy("has_white_chocolate_block",
						has(WHITE_CHOCOLATE_BLOCK.get())).save(consumer, "candyworld:white_chocolate_brick_from_block");
		ShapedRecipeBuilder.shaped(DARK_CHOCOLATE_BRICK.get(), 4)
				.define('#', DARK_CHOCOLATE_BLOCK.get()).pattern("##").pattern("##").unlockedBy("has_dark_chocolate_block",
						has(DARK_CHOCOLATE_BLOCK.get())).save(consumer, "candyworld:dark_chocolate_brick_from_block");

		ShapedRecipeBuilder.shaped(RED_GUMMY_BLOCK.get())
				.define('#', RED_GUMMY.get()).pattern("##").pattern("##").unlockedBy("has_red_gummy",
						has(RED_GUMMY.get())).save(consumer);
		ShapedRecipeBuilder.shaped(ORANGE_GUMMY_BLOCK.get())
				.define('#', ORANGE_GUMMY.get()).pattern("##").pattern("##").unlockedBy("has_orange_gummy",
						has(ORANGE_GUMMY.get())).save(consumer);
		ShapedRecipeBuilder.shaped(YELLOW_GUMMY_BLOCK.get())
				.define('#', YELLOW_GUMMY.get()).pattern("##").pattern("##").unlockedBy("has_yellow_gummy",
						has(YELLOW_GUMMY.get())).save(consumer);
		ShapedRecipeBuilder.shaped(WHITE_GUMMY_BLOCK.get())
				.define('#', WHITE_GUMMY.get()).pattern("##").pattern("##").unlockedBy("has_white_gummy",
						has(WHITE_GUMMY.get())).save(consumer);
		ShapedRecipeBuilder.shaped(GREEN_GUMMY_BLOCK.get())
				.define('#', GREEN_GUMMY.get()).pattern("##").pattern("##").unlockedBy("has_green_gummy",
						has(GREEN_GUMMY.get())).save(consumer);

		ShapedRecipeBuilder.shaped(RED_GUMMY_WORM_BLOCK.get())
				.define('#', RED_GUMMY_WORM.get()).pattern("##").pattern("##").unlockedBy("has_red_gummy_worm",
						has(RED_GUMMY_WORM.get())).save(consumer);
		ShapedRecipeBuilder.shaped(ORANGE_GUMMY_WORM_BLOCK.get())
				.define('#', ORANGE_GUMMY_WORM.get()).pattern("##").pattern("##").unlockedBy("has_orange_gummy_worm",
						has(ORANGE_GUMMY_WORM.get())).save(consumer);
		ShapedRecipeBuilder.shaped(YELLOW_GUMMY_WORM_BLOCK.get())
				.define('#', YELLOW_GUMMY_WORM.get()).pattern("##").pattern("##").unlockedBy("has_yellow_gummy_worm",
						has(YELLOW_GUMMY_WORM.get())).save(consumer);
		ShapedRecipeBuilder.shaped(WHITE_GUMMY_WORM_BLOCK.get())
				.define('#', WHITE_GUMMY_WORM.get()).pattern("##").pattern("##").unlockedBy("has_white_gummy_worm",
						has(WHITE_GUMMY_WORM.get())).save(consumer);
		ShapedRecipeBuilder.shaped(GREEN_GUMMY_WORM_BLOCK.get())
				.define('#', GREEN_GUMMY_WORM.get()).pattern("##").pattern("##").unlockedBy("has_green_gummy_worm",
						has(GREEN_GUMMY_WORM.get())).save(consumer);

		ShapedRecipeBuilder.shaped(MILK_CHOCOLATE_WORKBENCH.get())
				.define('@', MILK_CHOCOLATE_BAR.get()).define('#', WAFER_STICK_BLOCK.get())
				.pattern("@@").pattern("##").unlockedBy("has_wafer_stick_block",
						has(WAFER_STICK_BLOCK.get())).save(consumer);
		ShapedRecipeBuilder.shaped(WHITE_CHOCOLATE_WORKBENCH.get())
				.define('@', WHITE_CHOCOLATE_BAR.get()).define('#', WAFER_STICK_BLOCK.get())
				.pattern("@@").pattern("##").unlockedBy("has_wafer_stick_block",
						has(WAFER_STICK_BLOCK.get())).save(consumer);
		ShapedRecipeBuilder.shaped(DARK_CHOCOLATE_WORKBENCH.get())
				.define('@', DARK_CHOCOLATE_BAR.get()).define('#', WAFER_STICK_BLOCK.get())
				.pattern("@@").pattern("##").unlockedBy("has_wafer_stick_block",
						has(WAFER_STICK_BLOCK.get())).save(consumer);

		ShapedRecipeBuilder.shaped(WHITE_CANDY_CANE_WORKBENCH.get())
				.define('@', COTTON_CANDY.get()).define('#', WHITE_CANDY_CANE_BLOCK.get())
				.pattern("@@").pattern("##").unlockedBy("has_white_candy_cane_block",
						has(WHITE_CANDY_CANE_BLOCK.get())).save(consumer);
		ShapedRecipeBuilder.shaped(RED_CANDY_CANE_WORKBENCH.get())
				.define('@', COTTON_CANDY.get()).define('#', RED_CANDY_CANE_BLOCK.get())
				.pattern("@@").pattern("##").unlockedBy("has_red_candy_cane_block",
						has(RED_CANDY_CANE_BLOCK.get())).save(consumer);
		ShapedRecipeBuilder.shaped(GREEN_CANDY_CANE_WORKBENCH.get())
				.define('@', COTTON_CANDY.get()).define('#', GREEN_CANDY_CANE_BLOCK.get())
				.pattern("@@").pattern("##").unlockedBy("has_green_candy_cane_block",
						has(GREEN_CANDY_CANE_BLOCK.get())).save(consumer);
		ShapedRecipeBuilder.shaped(WHITE_RED_CANDY_CANE_WORKBENCH.get())
				.define('@', COTTON_CANDY.get()).define('#', WHITE_RED_CANDY_CANE_BLOCK.get())
				.pattern("@@").pattern("##").unlockedBy("has_white_red_candy_cane_block",
						has(WHITE_RED_CANDY_CANE_BLOCK.get())).save(consumer);
		ShapedRecipeBuilder.shaped(WHITE_GREEN_CANDY_CANE_WORKBENCH.get())
				.define('@', COTTON_CANDY.get()).define('#', WHITE_GREEN_CANDY_CANE_BLOCK.get())
				.pattern("@@").pattern("##").unlockedBy("has_white_green_candy_cane_block",
						has(WHITE_GREEN_CANDY_CANE_BLOCK.get())).save(consumer);
		ShapedRecipeBuilder.shaped(RED_GREEN_CANDY_CANE_WORKBENCH.get())
				.define('@', COTTON_CANDY.get()).define('#', RED_GREEN_CANDY_CANE_BLOCK.get())
				.pattern("@@").pattern("##").unlockedBy("has_red_green_candy_cane_block",
						has(RED_GREEN_CANDY_CANE_BLOCK.get())).save(consumer);

		ShapedRecipeBuilder.shaped(RED_GUMMY_WORKBENCH.get())
				.define('@', RED_GUMMY.get()).define('#', RED_GUMMY_WORM_BLOCK.get())
				.pattern("@@").pattern("##").unlockedBy("has_red_gummy_worm_blockk",
						has(RED_GUMMY_WORM_BLOCK.get())).save(consumer);
		ShapedRecipeBuilder.shaped(ORANGE_GUMMY_WORKBENCH.get())
				.define('@', ORANGE_GUMMY.get()).define('#', ORANGE_GUMMY_WORM_BLOCK.get())
				.pattern("@@").pattern("##").unlockedBy("has_orange_gummy_worm_blockk",
						has(ORANGE_GUMMY_WORM_BLOCK.get())).save(consumer);
		ShapedRecipeBuilder.shaped(YELLOW_GUMMY_WORKBENCH.get())
				.define('@', YELLOW_GUMMY.get()).define('#', YELLOW_GUMMY_WORM_BLOCK.get())
				.pattern("@@").pattern("##").unlockedBy("has_yellow_gummy_worm_blockk",
						has(YELLOW_GUMMY_WORM_BLOCK.get())).save(consumer);
		ShapedRecipeBuilder.shaped(WHITE_GUMMY_WORKBENCH.get())
				.define('@', WHITE_GUMMY.get()).define('#', WHITE_GUMMY_WORM_BLOCK.get())
				.pattern("@@").pattern("##").unlockedBy("has_white_gummy_worm_blockk",
						has(WHITE_GUMMY_WORM_BLOCK.get())).save(consumer);
		ShapedRecipeBuilder.shaped(GREEN_GUMMY_WORKBENCH.get())
				.define('@', GREEN_GUMMY.get()).define('#', GREEN_GUMMY_WORM_BLOCK.get())
				.pattern("@@").pattern("##").unlockedBy("has_green_gummy_worm_blockk",
						has(GREEN_GUMMY_WORM_BLOCK.get())).save(consumer);
	}

	@Override
	protected void saveAdvancement(HashCache cache, JsonObject advancementJson, Path path) {
		// Nope
	}
}