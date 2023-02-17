package com.mrbysco.candyworld.datagen.assets;

import com.mrbysco.candyworld.CandyWorld;
import com.mrbysco.candyworld.block.fluid.ModFluids;
import com.mrbysco.candyworld.registry.ModBlocks;
import com.mrbysco.candyworld.registry.ModEntities;
import com.mrbysco.candyworld.registry.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;

public class CandyLanguageProvider extends LanguageProvider {
	public CandyLanguageProvider(DataGenerator gen) {
		super(gen, CandyWorld.MOD_ID, "en_us");
	}

	@Override
	protected void addTranslations() {
		addBlock(ModBlocks.CANDY_GRASS_BLOCK, "Cotton Candy Grass");
		addBlock(ModBlocks.CHOCOLATE_COVERED_WHITE_BROWNIE, "Chocolate Covered White Brownie");
		addBlock(ModBlocks.DARK_CANDY_GRASS, "Covered Dark Brownie");
		addBlock(ModBlocks.COTTON_CANDY_PLANT, "Cotton Candy Plant");
		addBlock(ModBlocks.COTTON_CANDY_SAPLING, "Cotton Candy Sapling");
		addBlock(ModBlocks.COTTON_CANDY_LEAVES, "Cotton Candy Leaves");
		addBlock(ModBlocks.COTTON_CANDY_BUSH, "Cotton Candy Bush");
		addBlock(ModBlocks.WHITE_CANDY_CANE_BLOCK, "White Candy Cane Block");
		addBlock(ModBlocks.RED_CANDY_CANE_BLOCK, "Red Candy Cane Block");
		addBlock(ModBlocks.GREEN_CANDY_CANE_BLOCK, "Green Candy Cane Block");
		addBlock(ModBlocks.WHITE_RED_CANDY_CANE_BLOCK, "White-Red Candy Cane Block");
		addBlock(ModBlocks.WHITE_GREEN_CANDY_CANE_BLOCK, "White-Green Candy Cane Block");
		addBlock(ModBlocks.RED_GREEN_CANDY_CANE_BLOCK, "Red-Green Candy Cane Block");
		addBlock(ModBlocks.MILK_BROWNIE_BLOCK, "Milk Brownie Block");
		addBlock(ModBlocks.WHITE_BROWNIE_BLOCK, "White Brownie Block");
		addBlock(ModBlocks.DARK_BROWNIE_BLOCK, "Dark Brownie Block");
		addBlock(ModBlocks.MILK_CHOCOLATE_LEAVES, "Milk Chocolate Leaves");
		addBlock(ModBlocks.WHITE_CHOCOLATE_LEAVES, "White Chocolate Leaves");
		addBlock(ModBlocks.DARK_CHOCOLATE_LEAVES, "Dark Chocolate Leaves");
		addBlock(ModBlocks.MILK_CHOCOLATE_BLOCK, "Milk Chocolate Block");
		addBlock(ModBlocks.WHITE_CHOCOLATE_BLOCK, "White Chocolate Block");
		addBlock(ModBlocks.DARK_CHOCOLATE_BLOCK, "Dark Chocolate Block");
		addBlock(ModBlocks.MILK_CHOCOLATE_BAR_BLOCK, "Milk Chocolate Bar");
		addBlock(ModBlocks.WHITE_CHOCOLATE_BAR_BLOCK, "White Chocolate Bar");
		addBlock(ModBlocks.DARK_CHOCOLATE_BAR_BLOCK, "Dark Chocolate Bar");
		addBlock(ModBlocks.MILK_CHOCOLATE_BRICK, "Milk Chocolate Bricks");
		addBlock(ModBlocks.WHITE_CHOCOLATE_BRICK, "White Chocolate Bricks");
		addBlock(ModBlocks.DARK_CHOCOLATE_BRICK, "Dark Chocolate Bricks");
		addBlock(ModBlocks.WAFER_STICK_BLOCK, "Wafer Stick Block");
		addBlock(ModBlocks.CHOCOLATE_SAPLING, "Chocolate Tree Sapling");
		addBlock(ModBlocks.MILK_CHOCOLATE_MUSHROOM, "Milk Chocolate Mushrooms");
		addBlock(ModBlocks.WHITE_CHOCOLATE_MUSHROOM, "White Chocolate Mushrooms");
		addBlock(ModBlocks.DARK_CHOCOLATE_MUSHROOM, "Dark Chocolate Mushrooms");
		addBlock(ModBlocks.CRYSTALLIZED_SUGAR_COOKIE_ORE, "Crystallized Sugar Cookie Ore");
		addBlock(ModBlocks.COOKIE_ORE, "Cookie Ore");
		addBlock(ModBlocks.CRYSTALLIZED_SUGAR, "Crystallized Sugar");
		addBlock(ModBlocks.SUGAR_SAND, "Sugar");
		addBlock(ModBlocks.RED_GUMMY_BLOCK, "Red Gummy Block");
		addBlock(ModBlocks.ORANGE_GUMMY_BLOCK, "Orange Gummy Block");
		addBlock(ModBlocks.YELLOW_GUMMY_BLOCK, "Yellow Gummy Block");
		addBlock(ModBlocks.WHITE_GUMMY_BLOCK, "White Gummy Block");
		addBlock(ModBlocks.GREEN_GUMMY_BLOCK, "Green Gummy Block");
		addBlock(ModBlocks.RED_HARDENED_GUMMY_BLOCK, "Red Solid Gummy Block");
		addBlock(ModBlocks.ORANGE_HARDENED_GUMMY_BLOCK, "Orange Solid Gummy Block");
		addBlock(ModBlocks.YELLOW_HARDENED_GUMMY_BLOCK, "Yellow Solid Gummy Block");
		addBlock(ModBlocks.WHITE_HARDENED_GUMMY_BLOCK, "White Solid Gummy Block");
		addBlock(ModBlocks.GREEN_HARDENED_GUMMY_BLOCK, "Green Solid Gummy Block");
		addBlock(ModBlocks.RED_GUMMY_WORM_BLOCK, "Red Gummy Worm Block");
		addBlock(ModBlocks.ORANGE_GUMMY_WORM_BLOCK, "Orange Gummy Worm Block");
		addBlock(ModBlocks.YELLOW_GUMMY_WORM_BLOCK, "Yellow Gummy Worm Block");
		addBlock(ModBlocks.WHITE_GUMMY_WORM_BLOCK, "White Gummy Worm Block");
		addBlock(ModBlocks.GREEN_GUMMY_WORM_BLOCK, "Green Gummy Worm Block");
		addBlock(ModBlocks.TELEPORTER_ORE, "Magic Candy Ore");
		addBlock(ModBlocks.WHITE_CANDY_CANE_WORKBENCH, "White Candy Cane Workbench");
		addBlock(ModBlocks.RED_CANDY_CANE_WORKBENCH, "Red Candy Cane Workbench");
		addBlock(ModBlocks.GREEN_CANDY_CANE_WORKBENCH, "Green Candy Cane Workbench");
		addBlock(ModBlocks.WHITE_RED_CANDY_CANE_WORKBENCH, "White-Red Candy Cane Workbench");
		addBlock(ModBlocks.WHITE_GREEN_CANDY_CANE_WORKBENCH, "White-Green Candy Cane Workbench");
		addBlock(ModBlocks.RED_GREEN_CANDY_CANE_WORKBENCH, "Red-Green Candy Cane Workbench");
		addBlock(ModBlocks.MILK_CHOCOLATE_WORKBENCH, "Milk Chocolate Workbench");
		addBlock(ModBlocks.DARK_CHOCOLATE_WORKBENCH, "Dark Chocolate Workbench");
		addBlock(ModBlocks.WHITE_CHOCOLATE_WORKBENCH, "White Chocolate Workbench");
		addBlock(ModBlocks.RED_GUMMY_WORKBENCH, "Red Gummy Workbench");
		addBlock(ModBlocks.ORANGE_GUMMY_WORKBENCH, "Orange Gummy Workbench");
		addBlock(ModBlocks.YELLOW_GUMMY_WORKBENCH, "Yellow Gummy Workbench");
		addBlock(ModBlocks.WHITE_GUMMY_WORKBENCH, "White Gummy Workbench");
		addBlock(ModBlocks.GREEN_GUMMY_WORKBENCH, "Green Gummy Workbench");

		addItem(ModItems.COTTON_CANDY, "Cotton Candy");
		add("item.candyworld.butter_churn", "Butter Churn"); //Unused
		addItem(ModItems.BUTTER, "Butter");
		addItem(ModItems.WHITE_CANDY_CANE, "White Candy Cane");
		addItem(ModItems.RED_CANDY_CANE, "Red Candy Cane");
		addItem(ModItems.GREEN_CANDY_CANE, "Green Candy Cane");
		addItem(ModItems.WHITE_RED_CANDY_CANE, "White-Red Candy Cane");
		addItem(ModItems.WHITE_GREEN_CANDY_CANE, "White-Green Candy Cane");
		addItem(ModItems.RED_GREEN_CANDY_CANE, "Red-Green Candy Cane");
		addItem(ModItems.MILK_CHOCOLATE_BAR, "Milk Chocolate");
		addItem(ModItems.WHITE_CHOCOLATE_BAR, "White Chocolate");
		addItem(ModItems.DARK_CHOCOLATE_BAR, "Dark Chocolate");
		addItem(ModItems.MILK_CHOCOLATE_EGG, "Milk Chocolate Egg");
		addItem(ModItems.WHITE_CHOCOLATE_EGG, "White Chocolate Egg");
		addItem(ModItems.DARK_CHOCOLATE_EGG, "Dark Chocolate Egg");
		addItem(ModItems.MILK_BROWNIE, "Milk Brownie");
		addItem(ModItems.WHITE_BROWNIE, "White Brownie");
		addItem(ModItems.DARK_BROWNIE, "Dark Brownie");
		addItem(ModItems.WAFER_STICK, "Wafer Stick");
		addItem(ModItems.SUGAR_CRYSTAL, "Sugar Crystal");
		addItem(ModItems.RED_GUMMY, "Lump of Red Gummy");
		addItem(ModItems.ORANGE_GUMMY, "Lump of Orange Gummy");
		addItem(ModItems.YELLOW_GUMMY, "Lump of Yellow Gummy");
		addItem(ModItems.WHITE_GUMMY, "Lump of White Gummy");
		addItem(ModItems.GREEN_GUMMY, "Lump of Green Gummy");
		addItem(ModItems.RED_GUMMY_WORM, "Red Gummy Worm");
		addItem(ModItems.ORANGE_GUMMY_WORM, "Orange Gummy Worm");
		addItem(ModItems.YELLOW_GUMMY_WORM, "Yellow Gummy Worm");
		addItem(ModItems.WHITE_GUMMY_WORM, "White Gummy Worm");
		addItem(ModItems.GREEN_GUMMY_WORM, "Green Gummy Worm");
		addItem(ModItems.TELEPORTER, "Magic Piece of Candy");
		addItem(ModItems.MILK_CHOCOLATE_PICKAXE, "Milk Chocolate Pickaxe");
		addItem(ModItems.DARK_CHOCOLATE_PICKAXE, "Dark Chocolate Pickaxe");
		addItem(ModItems.WHITE_CHOCOLATE_PICKAXE, "White Chocolate Pickaxe");
		addItem(ModItems.MILK_CHOCOLATE_AXE, "Milk Chocolate Axe");
		addItem(ModItems.DARK_CHOCOLATE_AXE, "Dark Chocolate Axe");
		addItem(ModItems.WHITE_CHOCOLATE_AXE, "White Chocolate Axe");
		addItem(ModItems.MILK_CHOCOLATE_SHOVEL, "Milk Chocolate Shovel");
		addItem(ModItems.DARK_CHOCOLATE_SHOVEL, "Dark Chocolate Shovel");
		addItem(ModItems.WHITE_CHOCOLATE_SHOVEL, "White Chocolate Shovel");
		addItem(ModItems.MILK_CHOCOLATE_SWORD, "Milk Chocolate Sword");
		addItem(ModItems.DARK_CHOCOLATE_SWORD, "Dark Chocolate Sword");
		addItem(ModItems.WHITE_CHOCOLATE_SWORD, "White Chocolate Sword");
		addItem(ModItems.COTTON_CANDY_PICKAXE, "Cotton Candy Pickaxe");
		addItem(ModItems.COTTON_CANDY_AXE, "Cotton Candy Axe");
		addItem(ModItems.COTTON_CANDY_SHOVEL, "Cotton Candy Shovel");
		addItem(ModItems.COTTON_CANDY_SWORD, "Cotton Candy Sword");
		addItem(ModItems.LIQUID_CHOCOLATE_BUCKET, "Liquid Chocolate Bucket");
		addItem(ModItems.LIQUID_CANDY_BUCKET, "Liquid Candy Bucket");
		addItem(ModItems.COTTON_CANDY_SHEEP_SPAWN_EGG, "Cotton Candy Sheep Spawn Egg");
		addItem(ModItems.EASTER_CHICKEN_SPAWN_EGG, "Easter Chicken Spawn Egg");
		addItem(ModItems.GUMMY_MOUSE_SPAWN_EGG, "Gummy Mouse Spawn Egg");
		addItem(ModItems.GUMMY_BEAR_SPAWN_EGG, "Gummy Bear Spawn Egg");

		addFluid(ModFluids.LIQUID_CHOCOLATE_SOURCE, "Liquid Chocolate");
		addFluid(ModFluids.LIQUID_CHOCOLATE_FLOWING, "Flowing Liquid Chocolate");
		addFluid(ModFluids.LIQUID_CANDY_SOURCE, "Liquid Candy");
		addFluid(ModFluids.LIQUID_CANDY_FLOWING, "Flowing Liquid Candy");

		addEntityType(ModEntities.COTTON_CANDY_SHEEP, "Cotton Candy Sheep");
		addEntityType(ModEntities.EASTER_CHICKEN, "Easter Chicken");
		addEntityType(ModEntities.GUMMY_MOUSE, "Gummy Mouse");
		addEntityType(ModEntities.GUMMY_BEAR, "Gummy Bear");

		add("itemGroup.candyworld.items", "Candy World - Items");
		add("itemGroup.candyworld.blocks", "Candy World - Blocks");
		add("itemGroup.candyworld.tools", "Candy World - Tools");

		add("biome.candyworld.gummy_swamp", "Gummy Swamp");
		add("biome.candyworld.chocolate_forest", "Chocolate Forest");
		add("biome.candyworld.cotton_candy_plains", "Cotton Candy Plains");
	}

	private void addFluid(RegistryObject<FlowingFluid> registryObject, String name) {
		add("fluid." + registryObject.getId().getNamespace() + "." + registryObject.getId().getPath(), name);
	}
}
