package com.mrbysco.candyworld.world;

import com.mrbysco.candyworld.world.feature.CandyCaneFeature;
import com.mrbysco.candyworld.world.feature.CandyTreeFeature;
import com.mrbysco.candyworld.world.feature.CustomLakesFeature;
import com.mrbysco.candyworld.world.feature.GummyWormFeature;
import com.mrbysco.candyworld.world.feature.RandomRotatedPatchFeature;
import com.mrbysco.candyworld.world.feature.SpikeFeature;
import com.mrbysco.candyworld.world.feature.TeleportOreFeature;
import com.mrbysco.candyworld.world.feature.config.SpikeFeatureConfig;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModFeatures {
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, com.mrbysco.candyworld.CandyWorld.MOD_ID);

	public static final RegistryObject<Feature<NoFeatureConfig>> GUMMY_WORM = FEATURES.register("gummy_worm", () -> new GummyWormFeature(NoFeatureConfig.CODEC));
	public static final RegistryObject<Feature<OreFeatureConfig>> TELEPORT_ORE = FEATURES.register("teleport_ore", () -> new TeleportOreFeature(OreFeatureConfig.CODEC));
	public static final RegistryObject<Feature<BaseTreeFeatureConfig>> CANDY_TREE = FEATURES.register("candy_tree", () -> new CandyTreeFeature(BaseTreeFeatureConfig.CODEC));
	public static final RegistryObject<Feature<BlockClusterFeatureConfig>> RANDOM_ROTATED_PATCH = FEATURES.register("random_patch", () -> new RandomRotatedPatchFeature(BlockClusterFeatureConfig.CODEC));
	public static final RegistryObject<Feature<SpikeFeatureConfig>> SPIKE = FEATURES.register("spike", () -> new SpikeFeature(SpikeFeatureConfig.CODEC));
	public static final RegistryObject<Feature<BlockClusterFeatureConfig>> CANDY_CANE = FEATURES.register("candy_cane", () -> new CandyCaneFeature(BlockClusterFeatureConfig.CODEC));
	public static final RegistryObject<Feature<BlockStateFeatureConfig>> CANDY_LAKES = FEATURES.register("candy_lakes", () -> new CustomLakesFeature(BlockStateFeatureConfig.CODEC));

}
