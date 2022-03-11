package com.mrbysco.candyworld.datagen;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.mrbysco.candyworld.registry.ModBlocks;
import com.mrbysco.candyworld.registry.ModTags;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.world.level.block.Block;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.HashCache;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

import javax.annotation.Nonnull;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.mrbysco.candyworld.registry.ModBlocks.*;
import static com.mrbysco.candyworld.registry.ModItems.*;

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
	}

	private static class CandyLoot extends LootTableProvider {
		public CandyLoot(DataGenerator gen) {
			super(gen);
		}

		@Override
		protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
			return ImmutableList.of(Pair.of(FarmingBlocks::new, LootContextParamSets.BLOCK));
		}

		private static class FarmingBlocks extends BlockLoot {
			private static final LootItemCondition.Builder HAS_SHEARS = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Tags.Items.SHEARS));
			private static final LootItemCondition.Builder HAS_SHEARS_OR_SILK_TOUCH = HAS_SHEARS.or(HAS_SILK_TOUCH);
			public static final LootItemCondition.Builder HAS_NO_SHEARS_OR_SILK_TOUCH = HAS_SHEARS_OR_SILK_TOUCH.invert();

			private static final float[] NORMAL_LEAVES_SAPLING_CHANCES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};

			@Override
			protected void addTables() {
				this.dropSelf(CHOCOLATE_SAPLING.get());
				this.dropSelf(WAFER_STICK_BLOCK.get());
				this.add(MILK_CHOCOLATE_LEAVES.get(), (block) -> {
					return createChocolateLeavesDrops(block, CHOCOLATE_SAPLING.get(), MILK_CHOCOLATE_BAR.get(), NORMAL_LEAVES_SAPLING_CHANCES);
				});
				this.add(WHITE_CHOCOLATE_LEAVES.get(), (block) -> {
					return createChocolateLeavesDrops(block, CHOCOLATE_SAPLING.get(), WHITE_CHOCOLATE_BAR.get(), NORMAL_LEAVES_SAPLING_CHANCES);
				});
				this.add(DARK_CHOCOLATE_LEAVES.get(), (block) -> {
					return createChocolateLeavesDrops(block, CHOCOLATE_SAPLING.get(), DARK_CHOCOLATE_BAR.get(), NORMAL_LEAVES_SAPLING_CHANCES);
				});
				this.dropOther(MILK_CHOCOLATE_BAR_BLOCK.get(), MILK_CHOCOLATE_BAR.get());
				this.dropOther(WHITE_CHOCOLATE_BAR_BLOCK.get(), WHITE_CHOCOLATE_BAR.get());
				this.dropOther(DARK_CHOCOLATE_BAR_BLOCK.get(), DARK_CHOCOLATE_BAR.get());
				this.add(MILK_CHOCOLATE_MUSHROOM.get(), (block) -> {
					return createSilkAndChanceDrop(block, MILK_CHOCOLATE_BAR.get(), 0, 1, 0.125F);
				});
				this.add(WHITE_CHOCOLATE_MUSHROOM.get(), (block) -> {
					return createSilkAndChanceDrop(block, WHITE_CHOCOLATE_BAR.get(), 0, 1, 0.125F);
				});
				this.add(DARK_CHOCOLATE_MUSHROOM.get(), (block) -> {
					return createSilkAndChanceDrop(block, DARK_CHOCOLATE_BAR.get(), 0, 1, 0.125F);
				});
				this.dropSelf(MILK_CHOCOLATE_BLOCK.get());
				this.dropSelf(WHITE_CHOCOLATE_BLOCK.get());
				this.dropSelf(DARK_CHOCOLATE_BLOCK.get());
				this.dropSelf(MILK_CHOCOLATE_BRICK.get());
				this.dropSelf(WHITE_CHOCOLATE_BRICK.get());
				this.dropSelf(DARK_CHOCOLATE_BRICK.get());
				this.dropSelf(MILK_CHOCOLATE_WORKBENCH.get());
				this.dropSelf(WHITE_CHOCOLATE_WORKBENCH.get());
				this.dropSelf(DARK_CHOCOLATE_WORKBENCH.get());
				this.dropSelf(COTTON_CANDY_SAPLING.get());
				this.add(COTTON_CANDY_LEAVES.get(), (block) -> {
					return createChocolateLeavesDrops(block, COTTON_CANDY_SAPLING.get(), COTTON_CANDY.get(), NORMAL_LEAVES_SAPLING_CHANCES);
				});
				this.add(COTTON_CANDY_PLANT.get(), (block) -> {
					return createShearsDispatchTable(block, LootItem.lootTableItem(COTTON_CANDY.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))));
				});
				this.add(COTTON_CANDY_BUSH.get(), (block) -> {
					return createSilkAndChanceDrop(block, COTTON_CANDY.get(), 0, 2, 0.125F);
				});
				this.dropSelf(WHITE_CANDY_CANE_BLOCK.get());
				this.dropSelf(RED_CANDY_CANE_BLOCK.get());
				this.dropSelf(GREEN_CANDY_CANE_BLOCK.get());
				this.dropSelf(WHITE_RED_CANDY_CANE_BLOCK.get());
				this.dropSelf(WHITE_GREEN_CANDY_CANE_BLOCK.get());
				this.dropSelf(RED_GREEN_CANDY_CANE_BLOCK.get());

				this.dropSelf(WHITE_CANDY_CANE_WORKBENCH.get());
				this.dropSelf(RED_CANDY_CANE_WORKBENCH.get());
				this.dropSelf(GREEN_CANDY_CANE_WORKBENCH.get());
				this.dropSelf(WHITE_RED_CANDY_CANE_WORKBENCH.get());
				this.dropSelf(WHITE_GREEN_CANDY_CANE_WORKBENCH.get());
				this.dropSelf(RED_GREEN_CANDY_CANE_WORKBENCH.get());

				this.add(CRYSTALLIZED_SUGAR.get(), (block) -> {
					return createSilkTouchDispatchTable(block, LootItem.lootTableItem(SUGAR_CRYSTAL.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(4))));
				});
				this.add(SUGAR_SAND.get(), (block) -> {
					return createSilkTouchDispatchTable(block, LootItem.lootTableItem(Items.SUGAR).apply(SetItemCountFunction.setCount(ConstantValue.exactly(4))));
				});
				this.add(CANDY_GRASS_BLOCK.get(), (block) -> {
					return createSingleItemTableWithSilkTouch(block, MILK_BROWNIE_BLOCK.get());
				});
				this.dropSelf(MILK_BROWNIE_BLOCK.get());
				this.add(CHOCOLATE_COVERED_WHITE_BROWNIE.get(), (block) -> {
					return createSingleItemTableWithSilkTouch(block, WHITE_BROWNIE_BLOCK.get());
				});
				this.dropSelf(WHITE_BROWNIE_BLOCK.get());
				this.add(DARK_CANDY_GRASS.get(), (block) -> {
					return createSingleItemTableWithSilkTouch(block, DARK_BROWNIE_BLOCK.get());
				});
				this.dropSelf(DARK_BROWNIE_BLOCK.get());
				this.add(CRYSTALLIZED_SUGAR_COOKIE_ORE.get(), (block) -> {
					return createOreDrop(block, Items.COOKIE);
				});
				this.add(COOKIE_ORE.get(), (block) -> {
					return createOreDrop(block, Items.COOKIE);
				});
				this.add(TELEPORTER_ORE.get(), (block) -> {
					return createOreDrop(block, TELEPORTER.get());
				});

				this.dropSelf(RED_GUMMY_BLOCK.get());
				this.dropSelf(ORANGE_GUMMY_BLOCK.get());
				this.dropSelf(YELLOW_GUMMY_BLOCK.get());
				this.dropSelf(WHITE_GUMMY_BLOCK.get());
				this.dropSelf(GREEN_GUMMY_BLOCK.get());

				this.dropSelf(RED_HARDENED_GUMMY_BLOCK.get());
				this.dropSelf(ORANGE_HARDENED_GUMMY_BLOCK.get());
				this.dropSelf(YELLOW_HARDENED_GUMMY_BLOCK.get());
				this.dropSelf(WHITE_HARDENED_GUMMY_BLOCK.get());
				this.dropSelf(GREEN_HARDENED_GUMMY_BLOCK.get());

				this.dropSelf(RED_GUMMY_WORM_BLOCK.get());
				this.dropSelf(ORANGE_GUMMY_WORM_BLOCK.get());
				this.dropSelf(YELLOW_GUMMY_WORM_BLOCK.get());
				this.dropSelf(WHITE_GUMMY_WORM_BLOCK.get());
				this.dropSelf(GREEN_GUMMY_WORM_BLOCK.get());

				this.dropSelf(RED_GUMMY_WORKBENCH.get());
				this.dropSelf(ORANGE_GUMMY_WORKBENCH.get());
				this.dropSelf(YELLOW_GUMMY_WORKBENCH.get());
				this.dropSelf(WHITE_GUMMY_WORKBENCH.get());
				this.dropSelf(GREEN_GUMMY_WORKBENCH.get());
			}

			protected static LootTable.Builder createChocolateLeavesDrops(Block block, Block sapling, Item chocolate, float... chances) {
				return createLeavesDrops(block, sapling, chances).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
						.when(HAS_NO_SHEARS_OR_SILK_TOUCH).add(applyExplosionCondition(block, LootItem.lootTableItem(chocolate))
								.when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F))));
			}

			protected static LootTable.Builder createChanceDrop(Block block, Item drop, float min, float max, float chance) {
				return LootTable.lootTable()
						.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
								.add(applyExplosionDecay(block, LootItem.lootTableItem(drop)
										.apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max))).when(LootItemRandomChanceCondition.randomChance(chance)))));
			}

			protected static LootTable.Builder createSilkAndChanceDrop(Block block, Item drop, float min, float max, float chance) {
				return createShearsDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(drop)
						.apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max))).when(LootItemRandomChanceCondition.randomChance(chance))));
			}

			protected static LootTable.Builder createShearsDispatchTable(Block p_218511_0_, LootPoolEntryContainer.Builder<?> p_218511_1_) {
				return createSelfDropDispatchTable(p_218511_0_, HAS_SHEARS, p_218511_1_);
			}

			@Override
			protected Iterable<Block> getKnownBlocks() {
				return (Iterable<Block>) ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
			}
		}

		@Override
		protected void validate(Map<ResourceLocation, LootTable> map, @Nonnull ValidationContext validationtracker) {
			map.forEach((name, table) -> LootTables.validate(validationtracker, name, table));
		}
	}


	private static class CandyRecipes extends RecipeProvider {
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
}
