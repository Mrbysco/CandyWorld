package com.mrbysco.candyworld.client;

import com.mrbysco.candyworld.client.renderer.CandySheepRenderer;
import com.mrbysco.candyworld.client.renderer.EasterChickenRenderer;
import com.mrbysco.candyworld.client.renderer.GummyBearRenderer;
import com.mrbysco.candyworld.client.renderer.GummyMouseRenderer;
import com.mrbysco.candyworld.item.CustomSpawnEggItem;
import com.mrbysco.candyworld.registry.ModBlocks;
import com.mrbysco.candyworld.registry.ModEntities;
import com.mrbysco.candyworld.registry.ModItems;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientHandler {
	public static void onClientSetup(final FMLClientSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.COTTON_CANDY_SHEEP.get(), CandySheepRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.EASTER_CHICKEN.get(), EasterChickenRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.GUMMY_MOUSE.get(), GummyMouseRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.GUMMY_BEAR.get(), GummyBearRenderer::new);

		RenderTypeLookup.setRenderLayer(ModBlocks.CHOCOLATE_SAPLING.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(ModBlocks.COTTON_CANDY_SAPLING.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(ModBlocks.MILK_CHOCOLATE_MUSHROOM.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(ModBlocks.DARK_CHOCOLATE_MUSHROOM.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(ModBlocks.WHITE_CHOCOLATE_MUSHROOM.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(ModBlocks.MILK_CHOCOLATE_LEAVES.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(ModBlocks.DARK_CHOCOLATE_LEAVES.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(ModBlocks.WHITE_CHOCOLATE_LEAVES.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(ModBlocks.COTTON_CANDY_BUSH.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(ModBlocks.COTTON_CANDY_PLANT.get(), RenderType.cutout());

		RenderTypeLookup.setRenderLayer(ModBlocks.RED_GUMMY_BLOCK.get(), RenderType.translucent());
		RenderTypeLookup.setRenderLayer(ModBlocks.ORANGE_GUMMY_BLOCK.get(), RenderType.translucent());
		RenderTypeLookup.setRenderLayer(ModBlocks.YELLOW_GUMMY_BLOCK.get(), RenderType.translucent());
		RenderTypeLookup.setRenderLayer(ModBlocks.WHITE_GUMMY_BLOCK.get(), RenderType.translucent());
		RenderTypeLookup.setRenderLayer(ModBlocks.GREEN_GUMMY_BLOCK.get(), RenderType.translucent());
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

		for(RegistryObject<Item> registryObject : ModItems.ITEMS.getEntries()) {
			if(registryObject.get() instanceof CustomSpawnEggItem) {
				CustomSpawnEggItem spawnEgg = (CustomSpawnEggItem) registryObject.get();
				colors.register((stack, tintIndex) -> spawnEgg.getColor(tintIndex), spawnEgg);
			}
		}
	}
}
