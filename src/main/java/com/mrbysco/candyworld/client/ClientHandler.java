package com.mrbysco.candyworld.client;

import com.mrbysco.candyworld.CandyWorld;
import com.mrbysco.candyworld.client.model.CandySheepFlossModel;
import com.mrbysco.candyworld.client.model.CandySheepModel;
import com.mrbysco.candyworld.client.model.GummyBearModel;
import com.mrbysco.candyworld.client.model.GummyMouseModel;
import com.mrbysco.candyworld.client.model.GummyMouseOuterModel;
import com.mrbysco.candyworld.client.renderer.CandySheepRenderer;
import com.mrbysco.candyworld.client.renderer.EasterChickenRenderer;
import com.mrbysco.candyworld.client.renderer.GummyBearRenderer;
import com.mrbysco.candyworld.client.renderer.GummyMouseRenderer;
import com.mrbysco.candyworld.registry.ModBlocks;
import com.mrbysco.candyworld.registry.ModEntities;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientHandler {
	public static final ModelLayerLocation EASTER_CHICKEN = new ModelLayerLocation(new ResourceLocation(CandyWorld.MOD_ID, "main"), "easter_chicken");
	public static final ModelLayerLocation CANDY_SHEEP = new ModelLayerLocation(new ResourceLocation(CandyWorld.MOD_ID, "main"), "candy_sheep");
	public static final ModelLayerLocation CANDY_SHEEP_FLOSS = new ModelLayerLocation(new ResourceLocation(CandyWorld.MOD_ID, "floss"), "candy_sheep");
	public static final ModelLayerLocation GUMMY_BEAR = new ModelLayerLocation(new ResourceLocation(CandyWorld.MOD_ID, "main"), "gummy_bear");
	public static final ModelLayerLocation GUMMY_MOUSE = new ModelLayerLocation(new ResourceLocation(CandyWorld.MOD_ID, "main"), "gummy_mouse");
	public static final ModelLayerLocation GUMMY_MOUSE_OUTER = new ModelLayerLocation(new ResourceLocation(CandyWorld.MOD_ID, "outer"), "gummy_mouse");

	public static void onClientSetup(final FMLClientSetupEvent event) {
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.CHOCOLATE_SAPLING.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.COTTON_CANDY_SAPLING.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.MILK_CHOCOLATE_MUSHROOM.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.DARK_CHOCOLATE_MUSHROOM.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.WHITE_CHOCOLATE_MUSHROOM.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.MILK_CHOCOLATE_LEAVES.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.DARK_CHOCOLATE_LEAVES.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.WHITE_CHOCOLATE_LEAVES.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.COTTON_CANDY_BUSH.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.COTTON_CANDY_PLANT.get(), RenderType.cutout());

		ItemBlockRenderTypes.setRenderLayer(ModBlocks.RED_GUMMY_BLOCK.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.ORANGE_GUMMY_BLOCK.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.YELLOW_GUMMY_BLOCK.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.WHITE_GUMMY_BLOCK.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.GREEN_GUMMY_BLOCK.get(), RenderType.translucent());
	}

	public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(ModEntities.COTTON_CANDY_SHEEP.get(), CandySheepRenderer::new);
		event.registerEntityRenderer(ModEntities.EASTER_CHICKEN.get(), EasterChickenRenderer::new);
		event.registerEntityRenderer(ModEntities.GUMMY_MOUSE.get(), GummyMouseRenderer::new);
		event.registerEntityRenderer(ModEntities.GUMMY_BEAR.get(), GummyBearRenderer::new);
	}

	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(EASTER_CHICKEN, () -> ChickenModel.createBodyLayer());
		event.registerLayerDefinition(CANDY_SHEEP, () -> CandySheepModel.createBodyLayer());
		event.registerLayerDefinition(CANDY_SHEEP_FLOSS, () -> CandySheepFlossModel.createBodyLayer());
		event.registerLayerDefinition(GUMMY_BEAR, () -> GummyBearModel.createBodyLayer());
		event.registerLayerDefinition(GUMMY_MOUSE, () -> GummyMouseModel.createBodyLayer());
		event.registerLayerDefinition(GUMMY_MOUSE_OUTER, () -> GummyMouseOuterModel.createBodyLayer());
	}

	public static void registerBlockColors(final ColorHandlerEvent.Block event) {
		BlockColors colors = event.getBlockColors();

		colors.register((state, reader, pos, tintIndex) -> 0xff4530, ModBlocks.RED_GUMMY_BLOCK.get(), ModBlocks.RED_HARDENED_GUMMY_BLOCK.get(),
				ModBlocks.RED_GUMMY_WORKBENCH.get(), ModBlocks.RED_GUMMY_WORM_BLOCK.get());
		colors.register((state, reader, pos, tintIndex) -> 0xff9b4f, ModBlocks.ORANGE_GUMMY_BLOCK.get(), ModBlocks.ORANGE_HARDENED_GUMMY_BLOCK.get(),
				ModBlocks.ORANGE_GUMMY_WORKBENCH.get(), ModBlocks.ORANGE_GUMMY_WORM_BLOCK.get());
		colors.register((state, reader, pos, tintIndex) -> 0xffe563, ModBlocks.YELLOW_GUMMY_BLOCK.get(), ModBlocks.YELLOW_HARDENED_GUMMY_BLOCK.get(),
				ModBlocks.YELLOW_GUMMY_WORKBENCH.get(), ModBlocks.YELLOW_GUMMY_WORM_BLOCK.get());
		colors.register((state, reader, pos, tintIndex) -> 0xfffeb0, ModBlocks.WHITE_GUMMY_BLOCK.get(), ModBlocks.WHITE_HARDENED_GUMMY_BLOCK.get(),
				ModBlocks.WHITE_GUMMY_WORKBENCH.get(), ModBlocks.WHITE_GUMMY_WORM_BLOCK.get());
		colors.register((state, reader, pos, tintIndex) -> 0x80e22b, ModBlocks.GREEN_GUMMY_BLOCK.get(), ModBlocks.GREEN_HARDENED_GUMMY_BLOCK.get(),
				ModBlocks.GREEN_GUMMY_WORKBENCH.get(), ModBlocks.GREEN_GUMMY_WORM_BLOCK.get());
	}

	public static void registerItemColors(final ColorHandlerEvent.Item event) {
		ItemColors colors = event.getItemColors();

		colors.register((stack, tintIndex) -> 0xff4530, ModBlocks.RED_GUMMY_BLOCK.get(), ModBlocks.RED_HARDENED_GUMMY_BLOCK.get(),
				ModBlocks.RED_GUMMY_WORKBENCH.get(), ModBlocks.RED_GUMMY_WORM_BLOCK.get());
		colors.register((stack, tintIndex) -> 0xff9b4f, ModBlocks.ORANGE_GUMMY_BLOCK.get(), ModBlocks.ORANGE_HARDENED_GUMMY_BLOCK.get(),
				ModBlocks.ORANGE_GUMMY_WORKBENCH.get(), ModBlocks.ORANGE_GUMMY_WORM_BLOCK.get());
		colors.register((stack, tintIndex) -> 0xffe563, ModBlocks.YELLOW_GUMMY_BLOCK.get(), ModBlocks.YELLOW_HARDENED_GUMMY_BLOCK.get(),
				ModBlocks.YELLOW_GUMMY_WORKBENCH.get(), ModBlocks.YELLOW_GUMMY_WORM_BLOCK.get());
		colors.register((stack, tintIndex) -> 0xfffeb0, ModBlocks.WHITE_GUMMY_BLOCK.get(), ModBlocks.WHITE_HARDENED_GUMMY_BLOCK.get(),
				ModBlocks.WHITE_GUMMY_WORKBENCH.get(), ModBlocks.WHITE_GUMMY_WORM_BLOCK.get());
		colors.register((stack, tintIndex) -> 0x80e22b, ModBlocks.GREEN_GUMMY_BLOCK.get(), ModBlocks.GREEN_HARDENED_GUMMY_BLOCK.get(),
				ModBlocks.GREEN_GUMMY_WORKBENCH.get(), ModBlocks.GREEN_GUMMY_WORM_BLOCK.get());
	}
}
