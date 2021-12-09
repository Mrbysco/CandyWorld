package com.mrbysco.candyworld.datagen.providers.client;

import com.mrbysco.candyworld.CandyWorld;
import com.mrbysco.candyworld.block.fluid.ModFluids;
import com.mrbysco.candyworld.registry.ModEntities;
import net.minecraft.data.DataGenerator;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.fml.RegistryObject;

import static com.mrbysco.candyworld.registry.ModBlocks.*;
import static com.mrbysco.candyworld.registry.ModItems.*;

public class CandyLanguage extends LanguageProvider {
	public CandyLanguage(DataGenerator gen) {
		super(gen, CandyWorld.MOD_ID, "en_us");
	}
	@Override
	protected void addTranslations() {
		this.addBlock(CANDY_GRASS_BLOCK, "Cotton Candy Grass");
		this.addBlock(CHOCOLATE_COVERED_WHITE_BROWNIE, "Chocolate Covered White Brownie");
		this.addBlock(DARK_CANDY_GRASS, "Covered Dark Brownie");
		this.addBlock(COTTON_CANDY_PLANT, "Cotton Candy Plant");
		this.addBlock(COTTON_CANDY_SAPLING, "Cotton Candy Sapling");
		this.addBlock(COTTON_CANDY_LEAVES, "Cotton Candy Leaves");
		this.addBlock(COTTON_CANDY_BUSH, "Cotton Candy Bush");
		this.addBlock(WHITE_CANDY_CANE_BLOCK, "White Candy Cane Block");
		this.addBlock(RED_CANDY_CANE_BLOCK, "Red Candy Cane Block");
		this.addBlock(GREEN_CANDY_CANE_BLOCK, "Green Candy Cane Block");
		this.addBlock(WHITE_RED_CANDY_CANE_BLOCK, "White-Red Candy Cane Block");
		this.addBlock(WHITE_GREEN_CANDY_CANE_BLOCK, "White-Green Candy Cane Block");
		this.addBlock(RED_GREEN_CANDY_CANE_BLOCK, "Red-Green Candy Cane Block");
		this.addBlock(MILK_BROWNIE_BLOCK, "Milk Brownie Block");
		this.addBlock(WHITE_BROWNIE_BLOCK, "White Brownie Block");
		this.addBlock(DARK_BROWNIE_BLOCK, "Dark Brownie Block");
		this.addBlock(MILK_CHOCOLATE_LEAVES, "Milk Chocolate Leaves");
		this.addBlock(WHITE_CHOCOLATE_LEAVES, "White Chocolate Leaves");
		this.addBlock(DARK_CHOCOLATE_LEAVES, "Dark Chocolate Leaves");
		this.addBlock(MILK_CHOCOLATE_BLOCK, "Milk Chocolate Block");
		this.addBlock(WHITE_CHOCOLATE_BLOCK, "White Chocolate Block");
		this.addBlock(DARK_CHOCOLATE_BLOCK, "Dark Chocolate Block");
		this.addBlock(MILK_CHOCOLATE_BAR_BLOCK, "Milk Chocolate Bar");
		this.addBlock(WHITE_CHOCOLATE_BAR_BLOCK, "White Chocolate Bar");
		this.addBlock(DARK_CHOCOLATE_BAR_BLOCK, "Dark Chocolate Bar");
		this.addBlock(MILK_CHOCOLATE_BRICK, "Milk Chocolate Bricks");
		this.addBlock(WHITE_CHOCOLATE_BRICK, "White Chocolate Bricks");
		this.addBlock(DARK_CHOCOLATE_BRICK, "Dark Chocolate Bricks");
		this.addBlock(WAFER_STICK_BLOCK, "Wafer Stick Block");
		this.addBlock(CHOCOLATE_SAPLING, "Chocolate Tree Sapling");
		this.addBlock(MILK_CHOCOLATE_MUSHROOM, "Milk Chocolate Mushrooms");
		this.addBlock(WHITE_CHOCOLATE_MUSHROOM, "White Chocolate Mushrooms");
		this.addBlock(DARK_CHOCOLATE_MUSHROOM, "Dark Chocolate Mushrooms");
		this.addBlock(CRYSTALLIZED_SUGAR_COOKIE_ORE, "Crystallized Sugar Cookie Ore");
		this.addBlock(COOKIE_ORE, "Cookie Ore");
		this.addBlock(CRYSTALLIZED_SUGAR, "Crystallized Sugar");
		this.addBlock(SUGAR_SAND, "Sugar");
		this.addBlock(RED_GUMMY_BLOCK, "Red Gummy Block");
		this.addBlock(ORANGE_GUMMY_BLOCK, "Orange Gummy Block");
		this.addBlock(YELLOW_GUMMY_BLOCK, "Yellow Gummy Block");
		this.addBlock(WHITE_GUMMY_BLOCK, "White Gummy Block");
		this.addBlock(GREEN_GUMMY_BLOCK, "Green Gummy Block");
		this.addBlock(RED_HARDENED_GUMMY_BLOCK, "Red Solid Gummy Block");
		this.addBlock(ORANGE_HARDENED_GUMMY_BLOCK, "Orange Solid Gummy Block");
		this.addBlock(YELLOW_HARDENED_GUMMY_BLOCK, "Yellow Solid Gummy Block");
		this.addBlock(WHITE_HARDENED_GUMMY_BLOCK, "White Solid Gummy Block");
		this.addBlock(GREEN_HARDENED_GUMMY_BLOCK, "Green Solid Gummy Block");
		this.addBlock(RED_GUMMY_WORM_BLOCK, "Red Gummy Worm Block");
		this.addBlock(ORANGE_GUMMY_WORM_BLOCK, "Orange Gummy Worm Block");
		this.addBlock(YELLOW_GUMMY_WORM_BLOCK, "Yellow Gummy Worm Block");
		this.addBlock(WHITE_GUMMY_WORM_BLOCK, "White Gummy Worm Block");
		this.addBlock(GREEN_GUMMY_WORM_BLOCK, "Green Gummy Worm Block");
		this.addBlock(TELEPORTER_ORE, "Magic Candy Ore");
		this.addBlock(WHITE_CANDY_CANE_WORKBENCH, "White Candy Cane Workbench");
		this.addBlock(RED_CANDY_CANE_WORKBENCH, "Red Candy Cane Workbench");
		this.addBlock(GREEN_CANDY_CANE_WORKBENCH, "Green Candy Cane Workbench");
		this.addBlock(WHITE_RED_CANDY_CANE_WORKBENCH, "White-Red Candy Cane Workbench");
		this.addBlock(WHITE_GREEN_CANDY_CANE_WORKBENCH, "White-Green Candy Cane Workbench");
		this.addBlock(RED_GREEN_CANDY_CANE_WORKBENCH, "Red-Green Candy Cane Workbench");
		this.addBlock(MILK_CHOCOLATE_WORKBENCH, "Milk Chocolate Workbench");
		this.addBlock(DARK_CHOCOLATE_WORKBENCH, "Dark Chocolate Workbench");
		this.addBlock(WHITE_CHOCOLATE_WORKBENCH, "White Chocolate Workbench");
		this.addBlock(RED_GUMMY_WORKBENCH, "Red Gummy Workbench");
		this.addBlock(ORANGE_GUMMY_WORKBENCH, "Orange Gummy Workbench");
		this.addBlock(YELLOW_GUMMY_WORKBENCH, "Yellow Gummy Workbench");
		this.addBlock(WHITE_GUMMY_WORKBENCH, "White Gummy Workbench");
		this.addBlock(GREEN_GUMMY_WORKBENCH, "Green Gummy Workbench");

		this.addItem(COTTON_CANDY, "Cotton Candy");
//		this.addItem(BUTTER_CHURN, "Butter Churn"); Unused
		this.addItem(BUTTER, "Butter");
		this.addItem(WHITE_CANDY_CANE, "White Candy Cane");
		this.addItem(RED_CANDY_CANE, "Red Candy Cane");
		this.addItem(GREEN_CANDY_CANE, "Green Candy Cane");
		this.addItem(WHITE_RED_CANDY_CANE, "White-Red Candy Cane");
		this.addItem(WHITE_GREEN_CANDY_CANE, "White-Green Candy Cane");
		this.addItem(RED_GREEN_CANDY_CANE, "Red-Green Candy Cane");
		this.addItem(MILK_CHOCOLATE_BAR, "Milk Chocolate");
		this.addItem(WHITE_CHOCOLATE_BAR, "White Chocolate");
		this.addItem(DARK_CHOCOLATE_BAR, "Dark Chocolate");
		this.addItem(MILK_CHOCOLATE_EGG, "Milk Chocolate Egg");
		this.addItem(WHITE_CHOCOLATE_EGG, "White Chocolate Egg");
		this.addItem(DARK_CHOCOLATE_EGG, "Dark Chocolate Egg");
		this.addItem(MILK_BROWNIE, "Milk Brownie");
		this.addItem(WHITE_BROWNIE, "White Brownie");
		this.addItem(DARK_BROWNIE, "Dark Brownie");
		this.addItem(WAFER_STICK, "Wafer Stick");
		this.addItem(SUGAR_CRYSTAL, "Sugar Crystal");
		this.addItem(RED_GUMMY, "Lump of Red Gummy");
		this.addItem(ORANGE_GUMMY, "Lump of Orange Gummy");
		this.addItem(YELLOW_GUMMY, "Lump of Yellow Gummy");
		this.addItem(WHITE_GUMMY, "Lump of White Gummy");
		this.addItem(GREEN_GUMMY, "Lump of Green Gummy");
		this.addItem(RED_GUMMY_WORM, "Red Gummy Worm");
		this.addItem(ORANGE_GUMMY_WORM, "Orange Gummy Worm");
		this.addItem(YELLOW_GUMMY_WORM, "Yellow Gummy Worm");
		this.addItem(WHITE_GUMMY_WORM, "White Gummy Worm");
		this.addItem(GREEN_GUMMY_WORM, "Green Gummy Worm");
		this.addItem(TELEPORTER, "Magic Piece of Candy");
		this.addItem(MILK_CHOCOLATE_PICKAXE, "Milk Chocolate Pickaxe");
		this.addItem(DARK_CHOCOLATE_PICKAXE, "Dark Chocolate Pickaxe");
		this.addItem(WHITE_CHOCOLATE_PICKAXE, "White Chocolate Pickaxe");
		this.addItem(MILK_CHOCOLATE_AXE, "Milk Chocolate Axe");
		this.addItem(DARK_CHOCOLATE_AXE, "Dark Chocolate Axe");
		this.addItem(WHITE_CHOCOLATE_AXE, "White Chocolate Axe");
		this.addItem(MILK_CHOCOLATE_SHOVEL, "Milk Chocolate Shovel");
		this.addItem(DARK_CHOCOLATE_SHOVEL, "Dark Chocolate Shovel");
		this.addItem(WHITE_CHOCOLATE_SHOVEL, "White Chocolate Shovel");
		this.addItem(MILK_CHOCOLATE_SWORD, "Milk Chocolate Sword");
		this.addItem(DARK_CHOCOLATE_SWORD, "Dark Chocolate Sword");
		this.addItem(WHITE_CHOCOLATE_SWORD, "White Chocolate Sword");
		this.addItem(COTTON_CANDY_PICKAXE, "Cotton Candy Pickaxe");
		this.addItem(COTTON_CANDY_AXE, "Cotton Candy Axe");
		this.addItem(COTTON_CANDY_SHOVEL, "Cotton Candy Shovel");
		this.addItem(COTTON_CANDY_SWORD, "Cotton Candy Sword");
		this.addItem(LIQUID_CHOCOLATE_BUCKET, "Liquid Chocolate Bucket");
		this.addItem(LIQUID_CANDY_BUCKET, "Liquid Candy Bucket");
		this.addItem(COTTON_CANDY_SHEEP_SPAWN_EGG, "Cotton Candy Sheep Spawn Egg");
		this.addItem(EASTER_CHICKEN_SPAWN_EGG, "Easter Chicken Spawn Egg");
		this.addItem(GUMMY_MOUSE_SPAWN_EGG, "Gummy Mouse Spawn Egg");
		this.addItem(GUMMY_BEAR_SPAWN_EGG, "Gummy Bear Spawn Egg");

		this.addFluid(ModFluids.LIQUID_CHOCOLATE_SOURCE, "Liquid Chocolate");
		this.addFluid(ModFluids.LIQUID_CANDY_SOURCE, "Liquid Candy");

		this.addEntityType(ModEntities.COTTON_CANDY_SHEEP, "Cotton Candy Sheep");
		this.addEntityType(ModEntities.EASTER_CHICKEN, "Easter Chicken");
		this.addEntityType(ModEntities.GUMMY_MOUSE, "Gummy Mouse");
		this.addEntityType(ModEntities.GUMMY_BEAR, "Gummy Bear");

		this.add("itemGroup.candyworld.items", "Candy World - Items");
		this.add("itemGroup.candyworld.blocks", "Candy World - Blocks");
		this.add("itemGroup.candyworld.tools", "Candy World - Tools");

		this.add("biome.candyworld.gummy_swamp", "Gummy Swamp");
		this.add("biome.candyworld.chocolate_forest", "Chocolate Forest");
		this.add("biome.candyworld.cotton_candy_plains", "Cotton Candy Plains");
	}


	private void addFluid(RegistryObject<? extends FlowingFluid> fluid, String name) {
		ResourceLocation id = fluid.getId();
		this.add("fluid." + id.getNamespace() + "." + id.getPath(), name);
	}
}
