package com.mrbysco.candyworld.world;

import com.mrbysco.candyworld.CandyWorld;
import com.mrbysco.candyworld.world.feature.CandyCaneFeature;
import com.mrbysco.candyworld.world.feature.CandyTreeFeature;
import com.mrbysco.candyworld.world.feature.GummyWormFeature;
import com.mrbysco.candyworld.world.feature.SpikeFeature;
import com.mrbysco.candyworld.world.feature.TeleportOreFeature;
import com.mrbysco.candyworld.world.feature.config.SpikeFeatureConfig;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFeatures {
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, CandyWorld.MOD_ID);

	public static final RegistryObject<Feature<NoneFeatureConfiguration>> GUMMY_WORM = FEATURES.register("gummy_worm", () -> new GummyWormFeature(NoneFeatureConfiguration.CODEC));
	public static final RegistryObject<Feature<OreConfiguration>> TELEPORT_ORE = FEATURES.register("teleport_ore", () -> new TeleportOreFeature(OreConfiguration.CODEC));
	public static final RegistryObject<Feature<TreeConfiguration>> CANDY_TREE = FEATURES.register("candy_tree", () -> new CandyTreeFeature(TreeConfiguration.CODEC));
	//	public static final RegistryObject<Feature<RandomPatchConfiguration>> RANDOM_ROTATED_PATCH = FEATURES.register("random_patch", () -> new RandomRotatedPatchFeature(RandomPatchConfiguration.CODEC));
	public static final RegistryObject<Feature<SpikeFeatureConfig>> SPIKE = FEATURES.register("spike", () -> new SpikeFeature(SpikeFeatureConfig.CODEC));
	public static final RegistryObject<Feature<RandomPatchConfiguration>> CANDY_CANE = FEATURES.register("candy_cane", () -> new CandyCaneFeature(RandomPatchConfiguration.CODEC));

}
