package com.mrbysco.candyworld.world;

import com.mrbysco.candyworld.CandyWorld;
import com.mrbysco.candyworld.world.surface.CandySurfaceBuilder;
import com.mrbysco.candyworld.world.surface.ChocolateSurfaceBuilder;
import com.mrbysco.candyworld.world.surface.GummySurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSurfaceBuilders {
	public static final DeferredRegister<SurfaceBuilder<?>> SURFACE_BUILDERS = DeferredRegister.create(ForgeRegistries.SURFACE_BUILDERS, CandyWorld.MOD_ID);

	public static final RegistryObject<SurfaceBuilder<?>> GUMMY = SURFACE_BUILDERS.register("gummy", () -> new GummySurfaceBuilder(SurfaceBuilderConfig.CODEC));
	public static final RegistryObject<SurfaceBuilder<?>> CHOCOLATE = SURFACE_BUILDERS.register("chocolate", () -> new ChocolateSurfaceBuilder(SurfaceBuilderConfig.CODEC));
	public static final RegistryObject<SurfaceBuilder<?>> CANDY = SURFACE_BUILDERS.register("candy", () -> new CandySurfaceBuilder(SurfaceBuilderConfig.CODEC));
}
