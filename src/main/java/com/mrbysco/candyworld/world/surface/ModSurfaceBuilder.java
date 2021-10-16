package com.mrbysco.candyworld.world.surface;

import com.mrbysco.candyworld.registry.ModBlocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderBaseConfiguration;

public class ModSurfaceBuilder {
	private static final BlockState RED_GUMMY = ModBlocks.RED_GUMMY_BLOCK.get().defaultBlockState();
	private static final BlockState ORANGE_GUMMY = ModBlocks.ORANGE_GUMMY_BLOCK.get().defaultBlockState();
	private static final BlockState YELLOW_GUMMY = ModBlocks.YELLOW_GUMMY_BLOCK.get().defaultBlockState();
	private static final BlockState WHITE_GUMMY = ModBlocks.WHITE_GUMMY_BLOCK.get().defaultBlockState();
	private static final BlockState GREEN_GUMMY = ModBlocks.GREEN_GUMMY_BLOCK.get().defaultBlockState();
	private static final BlockState HARDENED_RED_GUMMY = ModBlocks.RED_HARDENED_GUMMY_BLOCK.get().defaultBlockState();
	private static final BlockState HARDENED_ORANGE_GUMMY = ModBlocks.ORANGE_HARDENED_GUMMY_BLOCK.get().defaultBlockState();
	private static final BlockState HARDENED_YELLOW_GUMMY = ModBlocks.YELLOW_HARDENED_GUMMY_BLOCK.get().defaultBlockState();
	private static final BlockState HARDENED_WHITE_GUMMY = ModBlocks.WHITE_HARDENED_GUMMY_BLOCK.get().defaultBlockState();
	private static final BlockState HARDENED_GREEN_GUMMY = ModBlocks.GREEN_HARDENED_GUMMY_BLOCK.get().defaultBlockState();

	private static final BlockState CANDY_GRASS = ModBlocks.CANDY_GRASS_BLOCK.get().defaultBlockState();
	private static final BlockState MILK_BROWNIE = ModBlocks.MILK_BROWNIE_BLOCK.get().defaultBlockState();
	private static final BlockState CHOCOLATE_COVERED_WHITE_BROWNIE = ModBlocks.CHOCOLATE_COVERED_WHITE_BROWNIE.get().defaultBlockState();
	private static final BlockState WHITE_BROWNIE = ModBlocks.WHITE_BROWNIE_BLOCK.get().defaultBlockState();

	public static final SurfaceBuilderBaseConfiguration CONFIG_RED_GUMMY = new SurfaceBuilderBaseConfiguration(RED_GUMMY, HARDENED_RED_GUMMY, RED_GUMMY);
	public static final SurfaceBuilderBaseConfiguration CONFIG_ORANGE_GUMMY = new SurfaceBuilderBaseConfiguration(ORANGE_GUMMY, HARDENED_ORANGE_GUMMY, ORANGE_GUMMY);
	public static final SurfaceBuilderBaseConfiguration CONFIG_YELLOW_GUMMY = new SurfaceBuilderBaseConfiguration(YELLOW_GUMMY, HARDENED_YELLOW_GUMMY, YELLOW_GUMMY);
	public static final SurfaceBuilderBaseConfiguration CONFIG_WHITE_GUMMY = new SurfaceBuilderBaseConfiguration(WHITE_GUMMY, HARDENED_WHITE_GUMMY, WHITE_GUMMY);
	public static final SurfaceBuilderBaseConfiguration CONFIG_GREEN_GUMMY = new SurfaceBuilderBaseConfiguration(GREEN_GUMMY, HARDENED_GREEN_GUMMY, GREEN_GUMMY);
	public static final SurfaceBuilderBaseConfiguration CONFIG_CHOCOLATE_COVERED_WHITE_BROWNIE = new SurfaceBuilderBaseConfiguration(CHOCOLATE_COVERED_WHITE_BROWNIE, WHITE_BROWNIE, WHITE_BROWNIE);
	public static final SurfaceBuilderBaseConfiguration CONFIG_CANDY_GRASS = new SurfaceBuilderBaseConfiguration(CANDY_GRASS, MILK_BROWNIE, MILK_BROWNIE);
}
