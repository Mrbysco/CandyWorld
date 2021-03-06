package com.mrbysco.candyworld;

import com.mrbysco.candyworld.block.fluid.ModFluids;
import com.mrbysco.candyworld.client.ClientHandler;
import com.mrbysco.candyworld.config.CandyConfig;
import com.mrbysco.candyworld.item.CustomSpawnEggItem;
import com.mrbysco.candyworld.registry.ModBiomes;
import com.mrbysco.candyworld.registry.ModBlocks;
import com.mrbysco.candyworld.registry.ModDimension;
import com.mrbysco.candyworld.registry.ModEntities;
import com.mrbysco.candyworld.registry.ModItems;
import com.mrbysco.candyworld.registry.ModLootTables;
import com.mrbysco.candyworld.world.ModFeatures;
import com.mrbysco.candyworld.world.ModFoliagePlacer;
import com.mrbysco.candyworld.world.ModSurfaceBuilders;
import com.mrbysco.candyworld.world.ModWorldCarvers;
import com.mrbysco.candyworld.world.WorldgenHandler;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(CandyWorld.MOD_ID)
public class CandyWorld {
	public static final String MOD_ID = "candyworld";
	public static final Logger LOGGER = LogManager.getLogger();

	public CandyWorld() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CandyConfig.commonSpec);
		eventBus.register(CandyConfig.class);

		eventBus.addListener(this::setup);

		ModFluids.registerFluids();
		ModLootTables.init();

		ModItems.ITEMS.register(eventBus);
		ModBlocks.BLOCKS.register(eventBus);
		ModFluids.FLUIDS.register(eventBus);
		ModEntities.ENTITIES.register(eventBus);
		ModBiomes.BIOMES.register(eventBus);
		ModSurfaceBuilders.SURFACE_BUILDERS.register(eventBus);
		ModWorldCarvers.WORLD_CARVERS.register(eventBus);
		ModFoliagePlacer.FOLIAGE_PLACERS.register(eventBus);
		ModFeatures.FEATURES.register(eventBus);

		MinecraftForge.EVENT_BUS.addListener(ModEntities::addSpawns);
		MinecraftForge.EVENT_BUS.register(new WorldgenHandler());
		eventBus.addListener(ModEntities::registerEntityAttributes);

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			eventBus.addListener(ClientHandler::onClientSetup);
			eventBus.addListener(ClientHandler::registerBlockColors);
			eventBus.addListener(ClientHandler::registerItemColors);
		});
	}

	private void setup(final FMLCommonSetupEvent event) {
		ModEntities.registerSpawnPlacement();
		ModBiomes.addBiomeTypes();
		ModDimension.registerStuff();

		event.enqueueWork(() -> {
			for (RegistryObject<Item> registryObject : ModItems.ITEMS.getEntries()) {
				if (registryObject.get() instanceof CustomSpawnEggItem) {
					CustomSpawnEggItem spawnEgg = (CustomSpawnEggItem) registryObject.get();
					SpawnEggItem.BY_ID.put(spawnEgg.entityType.get(), spawnEgg);
				}
			}
		});
	}
}