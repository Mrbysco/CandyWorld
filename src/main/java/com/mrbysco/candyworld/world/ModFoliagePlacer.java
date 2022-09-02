package com.mrbysco.candyworld.world;

import com.mrbysco.candyworld.CandyWorld;
import com.mrbysco.candyworld.world.tree.placer.ChocolateFoliagePlacer;
import com.mrbysco.candyworld.world.tree.placer.CottonCandyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFoliagePlacer {
	public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS = DeferredRegister.create(ForgeRegistries.FOLIAGE_PLACER_TYPES, CandyWorld.MOD_ID);

	public static final RegistryObject<FoliagePlacerType<CottonCandyFoliagePlacer>> COTTON_CANDY_FOLIAGE_PLACER =
			FOLIAGE_PLACERS.register("cotton_candy_foliage_placer", () -> new FoliagePlacerType<>(CottonCandyFoliagePlacer.CODEC));
	public static final RegistryObject<FoliagePlacerType<ChocolateFoliagePlacer>> CHOCOLATE_FOLIAGE_PLACER =
			FOLIAGE_PLACERS.register("chocolate_foliage_placer", () -> new FoliagePlacerType<>(ChocolateFoliagePlacer.CODEC));
}
