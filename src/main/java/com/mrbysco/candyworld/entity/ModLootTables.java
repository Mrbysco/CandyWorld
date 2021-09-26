package com.mrbysco.candyworld.entity;

import net.minecraft.loot.LootTables;
import net.minecraft.util.ResourceLocation;

public class ModLootTables {
    public static ResourceLocation ENTITY_BEAR_RED;
    public static ResourceLocation ENTITY_BEAR_ORANGE;
    public static ResourceLocation ENTITY_BEAR_YELLOW;
    public static ResourceLocation ENTITY_BEAR_WHITE;
    public static ResourceLocation ENTITY_BEAR_GREEN;

    public static ResourceLocation ENTITY_MOUSE_RED;
    public static ResourceLocation ENTITY_MOUSE_ORANGE;
    public static ResourceLocation ENTITY_MOUSE_YELLOW;
    public static ResourceLocation ENTITY_MOUSE_WHITE;
    public static ResourceLocation ENTITY_MOUSE_GREEN;

    public static void init() {
        ENTITY_BEAR_RED = LootTables.register(new ResourceLocation(com.mrbysco.candyworld.CandyWorld.MOD_ID, "entities/gummy_bear/bear_red"));
        ENTITY_BEAR_ORANGE = LootTables.register(new ResourceLocation(com.mrbysco.candyworld.CandyWorld.MOD_ID, "entities/gummy_bear/bear_orange"));
        ENTITY_BEAR_YELLOW = LootTables.register(new ResourceLocation(com.mrbysco.candyworld.CandyWorld.MOD_ID, "entities/gummy_bear/bear_yellow"));
        ENTITY_BEAR_WHITE = LootTables.register(new ResourceLocation(com.mrbysco.candyworld.CandyWorld.MOD_ID, "entities/gummy_bear/bear_white"));
        ENTITY_BEAR_GREEN = LootTables.register(new ResourceLocation(com.mrbysco.candyworld.CandyWorld.MOD_ID, "entities/gummy_bear/bear_green"));

        ENTITY_MOUSE_RED = LootTables.register(new ResourceLocation(com.mrbysco.candyworld.CandyWorld.MOD_ID, "entities/gummy_mouse/mouse_red"));
        ENTITY_MOUSE_ORANGE = LootTables.register(new ResourceLocation(com.mrbysco.candyworld.CandyWorld.MOD_ID, "entities/gummy_mouse/mouse_orange"));
        ENTITY_MOUSE_YELLOW = LootTables.register(new ResourceLocation(com.mrbysco.candyworld.CandyWorld.MOD_ID, "entities/gummy_mouse/mouse_yellow"));
        ENTITY_MOUSE_WHITE = LootTables.register(new ResourceLocation(com.mrbysco.candyworld.CandyWorld.MOD_ID, "entities/gummy_mouse/mouse_white"));
        ENTITY_MOUSE_GREEN = LootTables.register(new ResourceLocation(com.mrbysco.candyworld.CandyWorld.MOD_ID, "entities/gummy_mouse/mouse_green"));
    }
}
