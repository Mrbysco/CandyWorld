package com.mrbysco.candyworld.registry;

import com.mrbysco.candyworld.CandyWorld;
import com.mrbysco.candyworld.config.CandyConfig;
import com.mrbysco.candyworld.entity.CandySheepEntity;
import com.mrbysco.candyworld.entity.EasterChickenEntity;
import com.mrbysco.candyworld.entity.GummyBearEntity;
import com.mrbysco.candyworld.entity.GummyMouseEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo.Spawners;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, CandyWorld.MOD_ID);

    public static final RegistryObject<EntityType<CandySheepEntity>> COTTON_CANDY_SHEEP = ENTITIES.register("cotton_candy_sheep", () ->
            register("cotton_candy_sheep", EntityType.Builder.<CandySheepEntity>of(CandySheepEntity::new, EntityClassification.CREATURE)
                    .sized(0.9F, 1.3F).clientTrackingRange(10)));

    public static final RegistryObject<EntityType<EasterChickenEntity>> EASTER_CHICKEN = ENTITIES.register("easter_chicken", () ->
            register("easter_chicken", EntityType.Builder.<EasterChickenEntity>of(EasterChickenEntity::new, EntityClassification.CREATURE)
                    .sized(0.4F, 0.7F).clientTrackingRange(10)));

    public static final RegistryObject<EntityType<GummyMouseEntity>> GUMMY_MOUSE = ENTITIES.register("gummy_mouse", () ->
            register("gummy_mouse", EntityType.Builder.<GummyMouseEntity>of(GummyMouseEntity::new, EntityClassification.CREATURE)
                    .sized(0.5F, 0.4F).clientTrackingRange(10)));

    public static final RegistryObject<EntityType<GummyBearEntity>> GUMMY_BEAR = ENTITIES.register("gummy_bear", () ->
            register("gummy_bear", EntityType.Builder.<GummyBearEntity>of(GummyBearEntity::new, EntityClassification.CREATURE)
                    .sized(1.4F, 1.4F).clientTrackingRange(10)));


    public static void addSpawns(BiomeLoadingEvent event) {
        RegistryKey<Biome> biomeKey = RegistryKey.create(Registry.BIOME_REGISTRY, event.getName());
        if(BiomeDictionary.hasType(biomeKey, ModBiomes.CANDY)) {
            if(biomeKey.location().equals(ModBiomes.GUMMY_SWAMP.location())) {
                if (CandyConfig.COMMON.weightGummyMouse.get() > 0) {
                    event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new Spawners(GUMMY_MOUSE.get(), CandyConfig.COMMON.weightGummyMouse.get(), 4, 10));
                }
                if (CandyConfig.COMMON.weightGummyBear.get() > 0) {
                    event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new Spawners(GUMMY_BEAR.get(), CandyConfig.COMMON.weightGummyBear.get(), 2, 5));
                }
            } else if(biomeKey.location().equals(ModBiomes.CHOCOLATE_FOREST.location())) {
                if (CandyConfig.COMMON.weightEasterChicken.get() > 0) {
                    event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new Spawners(EASTER_CHICKEN.get(), CandyConfig.COMMON.weightEasterChicken.get(), 3, 7));
                }
            } else if(biomeKey.location().equals(ModBiomes.COTTON_CANDY_PLAINS.location())) {
                if (CandyConfig.COMMON.weightCottonCandySheep.get() > 0) {
                    event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new Spawners(COTTON_CANDY_SHEEP.get(), CandyConfig.COMMON.weightCottonCandySheep.get(), 3, 6));
                }
            }
        }
    }

    public static void registerSpawnPlacement() {
        EntitySpawnPlacementRegistry.register(COTTON_CANDY_SHEEP.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, CandySheepEntity::canSheepSpawn);
        EntitySpawnPlacementRegistry.register(EASTER_CHICKEN.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EasterChickenEntity::canChickenSpawn);
        EntitySpawnPlacementRegistry.register(GUMMY_MOUSE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GummyMouseEntity::canGummySpawn);
        EntitySpawnPlacementRegistry.register(GUMMY_BEAR.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GummyBearEntity::canGummySpawn);
    }

    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(COTTON_CANDY_SHEEP.get(), CandySheepEntity.registerAttributes().build());
        event.put(EASTER_CHICKEN.get(), EasterChickenEntity.registerAttributes().build());
        event.put(GUMMY_MOUSE.get(), GummyMouseEntity.registerAttributes().build());
        event.put(GUMMY_BEAR.get(), GummyBearEntity.registerAttributes().build());
    }

    public static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> builder) {
        return builder.build(id);
    }
}
