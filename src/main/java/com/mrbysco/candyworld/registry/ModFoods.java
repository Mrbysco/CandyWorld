package com.mrbysco.candyworld.registry;

import net.minecraft.world.food.FoodProperties;

public class ModFoods {
	public static final FoodProperties BUTTER = (new FoodProperties.Builder()).nutrition(1).saturationMod(1.0F).build();
	public static final FoodProperties COTTON_CANDY = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.5F).build();
	public static final FoodProperties COTTON_CANDY_TOOL = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.6F).build();
	public static final FoodProperties WAFER_STICK = (new FoodProperties.Builder()).nutrition(5).saturationMod(0.6F).build();
	public static final FoodProperties SUGAR_ROCK = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.2F).build();
	public static final FoodProperties CANDY_CANE = (new FoodProperties.Builder()).nutrition(5).saturationMod(0.6F).build();
	public static final FoodProperties BROWNIE = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.5F).build();
	public static final FoodProperties CHOCOLATE_BAR = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.6F).build();
	public static final FoodProperties CHOCOLATE_EGG = (new FoodProperties.Builder()).nutrition(7).saturationMod(0.8F).build();
	public static final FoodProperties CHOCOLATE_TOOL = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.6F).build();
	public static final FoodProperties GUMMY = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.6F).build();
	public static final FoodProperties GUMMY_WORM = (new FoodProperties.Builder()).nutrition(6).saturationMod(1.0F).build();
	public static final FoodProperties TELEPORTER = (new FoodProperties.Builder()).nutrition(1).saturationMod(1.0F).alwaysEat().build();
}
