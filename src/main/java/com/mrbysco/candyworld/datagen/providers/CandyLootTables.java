package com.mrbysco.candyworld.datagen.providers;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mrbysco.candyworld.registry.ModBlocks;
import com.mrbysco.candyworld.registry.ModEntities;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.data.loot.EntityLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootEntry;
import net.minecraft.loot.LootParameterSet;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTable.Builder;
import net.minecraft.loot.LootTableManager;
import net.minecraft.loot.RandomValueRange;
import net.minecraft.loot.TableLootEntry;
import net.minecraft.loot.ValidationTracker;
import net.minecraft.loot.conditions.EntityHasProperty;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.MatchTool;
import net.minecraft.loot.conditions.RandomChance;
import net.minecraft.loot.conditions.TableBonus;
import net.minecraft.loot.functions.LootingEnchantBonus;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.loot.functions.Smelt;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fml.RegistryObject;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.mrbysco.candyworld.registry.ModBlocks.*;
import static com.mrbysco.candyworld.registry.ModItems.*;
import static com.mrbysco.candyworld.registry.ModEntities.*;
import static com.mrbysco.candyworld.registry.ModLootTables.*;

public class CandyLootTables extends LootTableProvider {

	public CandyLootTables(DataGenerator gen) {
		super(gen);
	}

	@Override
	protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, Builder>>>, LootParameterSet>> getTables() {
		return ImmutableList.of(Pair.of(CandyBlocks::new, LootParameterSets.BLOCK), Pair.of(CandyEntities::new, LootParameterSets.ENTITY));
	}

	private static class CandyBlocks extends BlockLootTables {
		private static final ILootCondition.IBuilder HAS_SHEARS = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Tags.Items.SHEARS));
		private static final ILootCondition.IBuilder HAS_SHEARS_OR_SILK_TOUCH = HAS_SHEARS.or(HAS_SILK_TOUCH);
		public static final ILootCondition.IBuilder HAS_NO_SHEARS_OR_SILK_TOUCH = HAS_SHEARS_OR_SILK_TOUCH.invert();

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
				return createShearsDispatchTable(block, ItemLootEntry.lootTableItem(COTTON_CANDY.get()).apply(SetCount.setCount(ConstantRange.exactly(1))));
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
				return createSilkTouchDispatchTable(block, ItemLootEntry.lootTableItem(SUGAR_CRYSTAL.get()).apply(SetCount.setCount(ConstantRange.exactly(4))));
			});
			this.add(SUGAR_SAND.get(), (block) -> {
				return createSilkTouchDispatchTable(block, ItemLootEntry.lootTableItem(Items.SUGAR).apply(SetCount.setCount(ConstantRange.exactly(4))));
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
			return createLeavesDrops(block, sapling, chances).withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
					.when(HAS_NO_SHEARS_OR_SILK_TOUCH).add(applyExplosionCondition(block, ItemLootEntry.lootTableItem(chocolate))
							.when(TableBonus.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F))));
		}

		protected static LootTable.Builder createChanceDrop(Block block, Item drop, float min, float max, float chance) {
			return LootTable.lootTable()
					.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
							.add(applyExplosionDecay(block, ItemLootEntry.lootTableItem(drop)
									.apply(SetCount.setCount(RandomValueRange.between(min, max))).when(RandomChance.randomChance(chance)))));
		}

		protected static LootTable.Builder createSilkAndChanceDrop(Block block, Item drop, float min, float max, float chance) {
			return createShearsDispatchTable(block, applyExplosionDecay(block, ItemLootEntry.lootTableItem(drop)
					.apply(SetCount.setCount(RandomValueRange.between(min, max))).when(RandomChance.randomChance(chance))));
		}

		protected static LootTable.Builder createShearsDispatchTable(Block p_218511_0_, LootEntry.Builder<?> p_218511_1_) {
			return createSelfDropDispatchTable(p_218511_0_, HAS_SHEARS, p_218511_1_);
		}

		@Override
		protected Iterable<Block> getKnownBlocks() {
			return (Iterable<Block>) ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
		}
	}

	private static class CandyEntities extends EntityLootTables {
		@Override
		protected void addTables() {
			//TODO: See if the animals should drop candy variants of the vanilla drops instead
			this.add(COTTON_CANDY_SHEEP.get(),
					LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
							.add(ItemLootEntry.lootTableItem(Items.MUTTON)
									.apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))
									.apply(Smelt.smelted().when(EntityHasProperty.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE)))
									.apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F))))));

			this.add(EASTER_CHICKEN.get(),
					LootTable.lootTable()
							.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
									.add(ItemLootEntry.lootTableItem(Items.FEATHER)
										.apply(SetCount.setCount(RandomValueRange.between(0.0F, 2.0F)))
										.apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F)))))
							.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
									.add(ItemLootEntry.lootTableItem(Items.CHICKEN)
											.apply(Smelt.smelted().when(EntityHasProperty.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE)))
											.apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F))))));

			this.add(GUMMY_BEAR.get(), LootTable.lootTable());
			this.add(ENTITY_BEAR_GREEN, createGummyBearTable(GREEN_GUMMY.get()));
			this.add(ENTITY_BEAR_ORANGE, createGummyBearTable(ORANGE_GUMMY.get()));
			this.add(ENTITY_BEAR_RED, createGummyBearTable(RED_GUMMY.get()));
			this.add(ENTITY_BEAR_WHITE, createGummyBearTable(WHITE_GUMMY.get()));
			this.add(ENTITY_BEAR_YELLOW, createGummyBearTable(YELLOW_GUMMY.get()));

			this.add(GUMMY_MOUSE.get(), LootTable.lootTable());
			this.add(ENTITY_MOUSE_GREEN, createGummyMouseTable(GREEN_GUMMY.get()));
			this.add(ENTITY_MOUSE_ORANGE, createGummyMouseTable(ORANGE_GUMMY.get()));
			this.add(ENTITY_MOUSE_RED, createGummyMouseTable(RED_GUMMY.get()));
			this.add(ENTITY_MOUSE_WHITE, createGummyMouseTable(WHITE_GUMMY.get()));
			this.add(ENTITY_MOUSE_YELLOW, createGummyMouseTable(YELLOW_GUMMY.get()));
		}

		private static LootTable.Builder createGummyBearTable(IItemProvider itemProvider) {
			return LootTable.lootTable()
					.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
							.add(ItemLootEntry.lootTableItem(itemProvider)
									.apply(SetCount.setCount(RandomValueRange.between(0.0F, 20.0F)))
									.apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F)))))
					.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(TableLootEntry.lootTableReference(GUMMY_BEAR.get().getDefaultLootTable())));
		}

		private static LootTable.Builder createGummyMouseTable(IItemProvider itemProvider) {
			return LootTable.lootTable()
					.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
							.add(ItemLootEntry.lootTableItem(itemProvider)
									.apply(SetCount.setCount(RandomValueRange.between(0.0F, 2.0F)))
									.apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F)))))
					.withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(TableLootEntry.lootTableReference(GUMMY_MOUSE.get().getDefaultLootTable())));
		}

		@Override
		protected Iterable<EntityType<?>> getKnownEntities() {
			Stream<EntityType<?>> entities = ModEntities.ENTITIES.getEntries().stream().map(RegistryObject::get);
			return (Iterable<EntityType<?>>)entities::iterator;
		}
	}

	@Override
	protected void validate(Map<ResourceLocation, LootTable> map, @Nonnull ValidationTracker validationtracker) {
		map.forEach((name, table) -> LootTableManager.validate(validationtracker, name, table));
	}
}