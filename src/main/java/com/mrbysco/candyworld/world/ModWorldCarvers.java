package com.mrbysco.candyworld.world;

import com.mrbysco.candyworld.CandyWorld;
import com.mrbysco.candyworld.world.carvers.CandyCanyonWorldCarver;
import com.mrbysco.candyworld.world.carvers.CandyCavesWorldCarver;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModWorldCarvers {
	public static final DeferredRegister<WorldCarver<?>> WORLD_CARVERS = DeferredRegister.create(ForgeRegistries.WORLD_CARVERS, CandyWorld.MOD_ID);

	public static final RegistryObject<WorldCarver<?>> CANDY_CAVES = WORLD_CARVERS.register("candy_caves", () -> new CandyCavesWorldCarver(ProbabilityConfig.CODEC, 256));
	public static final RegistryObject<WorldCarver<?>> CANDY_CANYON = WORLD_CARVERS.register("candy_canyon", () -> new CandyCanyonWorldCarver(ProbabilityConfig.CODEC));
}
