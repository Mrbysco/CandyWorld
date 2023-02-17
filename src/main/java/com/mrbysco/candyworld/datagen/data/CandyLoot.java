package com.mrbysco.candyworld.datagen.data;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mrbysco.candyworld.registry.ModBlocks;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.mrbysco.candyworld.registry.ModBlocks.*;
import static com.mrbysco.candyworld.registry.ModItems.*;

public class CandyLoot extends LootTableProvider {
	public CandyLoot(DataGenerator gen) {
		super(gen);
	}

	@Override
	protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
		return ImmutableList.of(
				Pair.of(FarmingBlocks::new, LootContextParamSets.BLOCK)
		);
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
			this.dropSelf(LICORICE_BLOCK.get());
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