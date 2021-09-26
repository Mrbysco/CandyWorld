package com.mrbysco.candyworld.registry;

import net.minecraft.item.Food;

public class ModFoods {
	public static final Food BUTTER = (new Food.Builder()).nutrition(1).saturationMod(1.0F).build();
	public static final Food COTTON_CANDY = (new Food.Builder()).nutrition(4).saturationMod(0.5F).build();
	public static final Food COTTON_CANDY_TOOL = (new Food.Builder()).nutrition(1).saturationMod(0.6F).build();
	public static final Food WAFER_STICK = (new Food.Builder()).nutrition(5).saturationMod(0.6F).build();
	public static final Food SUGAR_ROCK = (new Food.Builder()).nutrition(4).saturationMod(0.2F).build();
	public static final Food CANDY_CANE = (new Food.Builder()).nutrition(5).saturationMod(0.6F).build();
	public static final Food BROWNIE = (new Food.Builder()).nutrition(4).saturationMod(0.5F).build();
	public static final Food CHOCOLATE_BAR = (new Food.Builder()).nutrition(6).saturationMod(0.6F).build();
	public static final Food CHOCOLATE_EGG = (new Food.Builder()).nutrition(7).saturationMod(0.8F).build();
	public static final Food CHOCOLATE_TOOL = (new Food.Builder()).nutrition(6).saturationMod(0.6F).build();
	public static final Food GUMMY = (new Food.Builder()).nutrition(4).saturationMod(0.6F).build();
	public static final Food GUMMY_WORM = (new Food.Builder()).nutrition(6).saturationMod(1.0F).build();
	public static final Food TELEPORTER = (new Food.Builder()).nutrition(1).saturationMod(1.0F).alwaysEat().build();
}
