package com.mrbysco.candyworld.registry;

import com.mrbysco.candyworld.CandyWorld;
import com.mrbysco.candyworld.block.fluid.ModFluids;
import com.mrbysco.candyworld.item.teleporter.TeleporterItem;
import com.mrbysco.candyworld.item.tools.EdibleAxeItem;
import com.mrbysco.candyworld.item.tools.EdiblePickaxeItem;
import com.mrbysco.candyworld.item.tools.EdibleShovelItem;
import com.mrbysco.candyworld.item.tools.EdibleSwordItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModItems {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CandyWorld.MOD_ID);

	// simple food items
	public static final RegistryObject<Item> BUTTER = ITEMS.register("butter", () -> new Item(new Item.Properties().food(ModFoods.BUTTER).tab(ModGroups.ITEMS)));
	public static final RegistryObject<Item> COTTON_CANDY = ITEMS.register("cotton_candy", () -> new Item(new Item.Properties().food(ModFoods.COTTON_CANDY).tab(ModGroups.ITEMS)));
	public static final RegistryObject<Item> WAFER_STICK = ITEMS.register("wafer_stick", () -> new Item(new Item.Properties().food(ModFoods.WAFER_STICK).tab(ModGroups.ITEMS)));
	public static final RegistryObject<Item> SUGAR_CRYSTAL = ITEMS.register("sugar_crystal", () -> new Item(new Item.Properties().food(ModFoods.SUGAR_ROCK).tab(ModGroups.ITEMS)));

	// food items
	public static final RegistryObject<Item> WHITE_CANDY_CANE = ITEMS.register("white_candy_cane", () -> new Item(new Item.Properties().food(ModFoods.CANDY_CANE).tab(ModGroups.ITEMS)));
	public static final RegistryObject<Item> RED_CANDY_CANE = ITEMS.register("red_candy_cane", () -> new Item(new Item.Properties().food(ModFoods.CANDY_CANE).tab(ModGroups.ITEMS)));
	public static final RegistryObject<Item> GREEN_CANDY_CANE = ITEMS.register("green_candy_cane", () -> new Item(new Item.Properties().food(ModFoods.CANDY_CANE).tab(ModGroups.ITEMS)));
	public static final RegistryObject<Item> WHITE_RED_CANDY_CANE = ITEMS.register("white_red_candy_cane", () -> new Item(new Item.Properties().food(ModFoods.CANDY_CANE).tab(ModGroups.ITEMS)));
	public static final RegistryObject<Item> WHITE_GREEN_CANDY_CANE = ITEMS.register("white_green_candy_cane", () -> new Item(new Item.Properties().food(ModFoods.CANDY_CANE).tab(ModGroups.ITEMS)));
	public static final RegistryObject<Item> RED_GREEN_CANDY_CANE = ITEMS.register("red_green_candy_cane", () -> new Item(new Item.Properties().food(ModFoods.CANDY_CANE).tab(ModGroups.ITEMS)));

	public static final RegistryObject<Item> MILK_BROWNIE = ITEMS.register("milk_brownie", () -> new Item(new Item.Properties().food(ModFoods.BROWNIE).tab(ModGroups.ITEMS)));
	public static final RegistryObject<Item> WHITE_BROWNIE = ITEMS.register("white_brownie", () -> new Item(new Item.Properties().food(ModFoods.BROWNIE).tab(ModGroups.ITEMS)));
	public static final RegistryObject<Item> DARK_BROWNIE = ITEMS.register("dark_brownie", () -> new Item(new Item.Properties().food(ModFoods.BROWNIE).tab(ModGroups.ITEMS)));

	public static final RegistryObject<Item> MILK_CHOCOLATE_BAR = ITEMS.register("milk_chocolate_bar", () -> new Item(new Item.Properties().food(ModFoods.CHOCOLATE_BAR).tab(ModGroups.ITEMS)));
	public static final RegistryObject<Item> WHITE_CHOCOLATE_BAR = ITEMS.register("white_chocolate_bar", () -> new Item(new Item.Properties().food(ModFoods.CHOCOLATE_BAR).tab(ModGroups.ITEMS)));
	public static final RegistryObject<Item> DARK_CHOCOLATE_BAR = ITEMS.register("dark_chocolate_bar", () -> new Item(new Item.Properties().food(ModFoods.CHOCOLATE_BAR).tab(ModGroups.ITEMS)));

	public static final RegistryObject<Item> MILK_CHOCOLATE_EGG = ITEMS.register("milk_chocolate_egg", () -> new Item(new Item.Properties().food(ModFoods.CHOCOLATE_EGG).tab(ModGroups.ITEMS)));
	public static final RegistryObject<Item> WHITE_CHOCOLATE_EGG = ITEMS.register("white_chocolate_egg", () -> new Item(new Item.Properties().food(ModFoods.CHOCOLATE_EGG).tab(ModGroups.ITEMS)));
	public static final RegistryObject<Item> DARK_CHOCOLATE_EGG = ITEMS.register("dark_chocolate_egg", () -> new Item(new Item.Properties().food(ModFoods.CHOCOLATE_EGG).tab(ModGroups.ITEMS)));

	public static final RegistryObject<Item> RED_GUMMY = ITEMS.register("red_gummy", () -> new Item(new Item.Properties().food(ModFoods.GUMMY).tab(ModGroups.ITEMS)));
	public static final RegistryObject<Item> ORANGE_GUMMY = ITEMS.register("orange_gummy", () -> new Item(new Item.Properties().food(ModFoods.GUMMY).tab(ModGroups.ITEMS)));
	public static final RegistryObject<Item> YELLOW_GUMMY = ITEMS.register("yellow_gummy", () -> new Item(new Item.Properties().food(ModFoods.GUMMY).tab(ModGroups.ITEMS)));
	public static final RegistryObject<Item> WHITE_GUMMY = ITEMS.register("white_gummy", () -> new Item(new Item.Properties().food(ModFoods.GUMMY).tab(ModGroups.ITEMS)));
	public static final RegistryObject<Item> GREEN_GUMMY = ITEMS.register("green_gummy", () -> new Item(new Item.Properties().food(ModFoods.GUMMY).tab(ModGroups.ITEMS)));

	public static final RegistryObject<Item> RED_GUMMY_WORM = ITEMS.register("red_gummy_worm", () -> new Item(new Item.Properties().food(ModFoods.GUMMY_WORM).tab(ModGroups.ITEMS)));
	public static final RegistryObject<Item> ORANGE_GUMMY_WORM = ITEMS.register("orange_gummy_worm", () -> new Item(new Item.Properties().food(ModFoods.GUMMY_WORM).tab(ModGroups.ITEMS)));
	public static final RegistryObject<Item> YELLOW_GUMMY_WORM = ITEMS.register("yellow_gummy_worm", () -> new Item(new Item.Properties().food(ModFoods.GUMMY_WORM).tab(ModGroups.ITEMS)));
	public static final RegistryObject<Item> WHITE_GUMMY_WORM = ITEMS.register("white_gummy_worm", () -> new Item(new Item.Properties().food(ModFoods.GUMMY_WORM).tab(ModGroups.ITEMS)));
	public static final RegistryObject<Item> GREEN_GUMMY_WORM = ITEMS.register("green_gummy_worm", () -> new Item(new Item.Properties().food(ModFoods.GUMMY_WORM).tab(ModGroups.ITEMS)));

	public static final RegistryObject<Item> TELEPORTER = ITEMS.register("teleporter", () -> new TeleporterItem(new Item.Properties().food(ModFoods.TELEPORTER).tab(ModGroups.ITEMS)));

	public static final RegistryObject<Item> MILK_CHOCOLATE_AXE = ITEMS.register("milk_chocolate_axe", () -> new EdibleAxeItem(ModItemTier.CHOCOLATE, 5.5F, -3F, new Item.Properties().food(ModFoods.CHOCOLATE_TOOL)));
	public static final RegistryObject<Item> MILK_CHOCOLATE_PICKAXE = ITEMS.register("milk_chocolate_pickaxe", () -> new EdiblePickaxeItem(ModItemTier.CHOCOLATE, 1, -2.8F, new Item.Properties().food(ModFoods.CHOCOLATE_TOOL)));
	public static final RegistryObject<Item> MILK_CHOCOLATE_SHOVEL = ITEMS.register("milk_chocolate_shovel", () -> new EdibleShovelItem(ModItemTier.CHOCOLATE, 1.5F, -3.0F, new Item.Properties().food(ModFoods.CHOCOLATE_TOOL)));
	public static final RegistryObject<Item> MILK_CHOCOLATE_SWORD = ITEMS.register("milk_chocolate_sword", () -> new EdibleSwordItem(ModItemTier.CHOCOLATE, 3, -2.4F, new Item.Properties().food(ModFoods.CHOCOLATE_TOOL)));

	public static final RegistryObject<Item> WHITE_CHOCOLATE_AXE = ITEMS.register("white_chocolate_axe", () -> new EdibleAxeItem(ModItemTier.CHOCOLATE, 5.5F, -3F, new Item.Properties().food(ModFoods.CHOCOLATE_TOOL)));
	public static final RegistryObject<Item> WHITE_CHOCOLATE_PICKAXE = ITEMS.register("white_chocolate_pickaxe", () -> new EdiblePickaxeItem(ModItemTier.CHOCOLATE, 1, -2.8F, new Item.Properties().food(ModFoods.CHOCOLATE_TOOL)));
	public static final RegistryObject<Item> WHITE_CHOCOLATE_SHOVEL = ITEMS.register("white_chocolate_shovel", () -> new EdibleShovelItem(ModItemTier.CHOCOLATE, 1.5F, -3.0F, new Item.Properties().food(ModFoods.CHOCOLATE_TOOL)));
	public static final RegistryObject<Item> WHITE_CHOCOLATE_SWORD = ITEMS.register("white_chocolate_sword", () -> new EdibleSwordItem(ModItemTier.CHOCOLATE, 3, -2.4F, new Item.Properties().food(ModFoods.CHOCOLATE_TOOL)));

	public static final RegistryObject<Item> DARK_CHOCOLATE_AXE = ITEMS.register("dark_chocolate_axe", () -> new EdibleAxeItem(ModItemTier.CHOCOLATE, 5.5F, -3F, new Item.Properties().food(ModFoods.CHOCOLATE_TOOL)));
	public static final RegistryObject<Item> DARK_CHOCOLATE_PICKAXE = ITEMS.register("dark_chocolate_pickaxe", () -> new EdiblePickaxeItem(ModItemTier.CHOCOLATE, 1, -2.8F, new Item.Properties().food(ModFoods.CHOCOLATE_TOOL)));
	public static final RegistryObject<Item> DARK_CHOCOLATE_SHOVEL = ITEMS.register("dark_chocolate_shovel", () -> new EdibleShovelItem(ModItemTier.CHOCOLATE, 1.5F, -3.0F, new Item.Properties().food(ModFoods.CHOCOLATE_TOOL)));
	public static final RegistryObject<Item> DARK_CHOCOLATE_SWORD = ITEMS.register("dark_chocolate_sword", () -> new EdibleSwordItem(ModItemTier.CHOCOLATE, 3, -2.4F, new Item.Properties().food(ModFoods.CHOCOLATE_TOOL)));

	public static final RegistryObject<Item> COTTON_CANDY_AXE = ITEMS.register("cotton_candy_axe", () -> new EdibleAxeItem(ModItemTier.COTTON_CANDY, 5F, -3F, new Item.Properties().food(ModFoods.COTTON_CANDY_TOOL)));
	public static final RegistryObject<Item> COTTON_CANDY_PICKAXE = ITEMS.register("cotton_candy_pickaxe", () -> new EdiblePickaxeItem(ModItemTier.COTTON_CANDY, 1, -2.8F, new Item.Properties().food(ModFoods.COTTON_CANDY_TOOL)));
	public static final RegistryObject<Item> COTTON_CANDY_SHOVEL = ITEMS.register("cotton_candy_shovel", () -> new EdibleShovelItem(ModItemTier.COTTON_CANDY, 1.5F, -3.0F, new Item.Properties().food(ModFoods.COTTON_CANDY_TOOL)));
	public static final RegistryObject<Item> COTTON_CANDY_SWORD = ITEMS.register("cotton_candy_sword", () -> new EdibleSwordItem(ModItemTier.COTTON_CANDY, 3, -2.4F, new Item.Properties().food(ModFoods.COTTON_CANDY_TOOL)));

	public static final RegistryObject<Item> COTTON_CANDY_SHEEP_SPAWN_EGG = ITEMS.register("cotton_candy_sheep_spawn_egg", () ->
			new ForgeSpawnEggItem(() -> ModEntities.COTTON_CANDY_SHEEP.get(), 0xff33ff, 0xffccff, new Item.Properties().tab(ModGroups.ITEMS)));
	public static final RegistryObject<Item> EASTER_CHICKEN_SPAWN_EGG = ITEMS.register("easter_chicken_spawn_egg", () ->
			new ForgeSpawnEggItem(() -> ModEntities.EASTER_CHICKEN.get(), 0x996611, 0x774411, new Item.Properties().tab(ModGroups.ITEMS)));
	public static final RegistryObject<Item> GUMMY_MOUSE_SPAWN_EGG = ITEMS.register("gummy_mouse_spawn_egg", () ->
			new ForgeSpawnEggItem(() -> ModEntities.GUMMY_MOUSE.get(), 0x00ff00, 0x33bb33, new Item.Properties().tab(ModGroups.ITEMS)));
	public static final RegistryObject<Item> GUMMY_BEAR_SPAWN_EGG = ITEMS.register("gummy_bear_spawn_egg", () ->
			new ForgeSpawnEggItem(() -> ModEntities.GUMMY_BEAR.get(), 0x00ff00, 0x33bb33, new Item.Properties().tab(ModGroups.ITEMS)));

	public static final RegistryObject<Item> LIQUID_CHOCOLATE_BUCKET = ITEMS.register("liquid_chocolate_bucket", () ->
			new BucketItem(() -> ModFluids.LIQUID_CHOCOLATE_SOURCE.get(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1).tab(ModGroups.ITEMS)));
	public static final RegistryObject<Item> LIQUID_CANDY_BUCKET = ITEMS.register("liquid_candy_bucket", () ->
			new BucketItem(() -> ModFluids.LIQUID_CANDY_SOURCE.get(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1).tab(ModGroups.ITEMS)));

}
