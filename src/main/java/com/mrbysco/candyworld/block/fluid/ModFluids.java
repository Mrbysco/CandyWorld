package com.mrbysco.candyworld.block.fluid;

import com.mrbysco.candyworld.CandyWorld;
import com.mrbysco.candyworld.registry.ModBlocks;
import com.mrbysco.candyworld.registry.ModItems;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluids {
	public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, CandyWorld.MOD_ID);
	public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, CandyWorld.MOD_ID);

	public static RegistryObject<FlowingFluid> LIQUID_CHOCOLATE_SOURCE;
	public static RegistryObject<FlowingFluid> LIQUID_CHOCOLATE_FLOWING;
	public static RegistryObject<FlowingFluid> LIQUID_CANDY_SOURCE;
	public static RegistryObject<FlowingFluid> LIQUID_CANDY_FLOWING;

	public static RegistryObject<FluidType> LIQUID_CHOCOLATE_FLUID_TYPE;
	public static RegistryObject<FluidType> LIQUID_CANDY_FLUID_TYPE;

	private static ForgeFlowingFluid.Properties liquidChocolateProperties() {
		return new ForgeFlowingFluid.Properties(LIQUID_CHOCOLATE_FLUID_TYPE, LIQUID_CHOCOLATE_SOURCE, LIQUID_CHOCOLATE_FLOWING)
				.bucket(ModItems.LIQUID_CHOCOLATE_BUCKET).block(ModBlocks.LIQUID_CHOCOLATE_BLOCK);
	}

	private static ForgeFlowingFluid.Properties liquidCandyProperties() {
		return new ForgeFlowingFluid.Properties(LIQUID_CANDY_FLUID_TYPE, LIQUID_CANDY_SOURCE, LIQUID_CANDY_FLOWING)
				.bucket(ModItems.LIQUID_CANDY_BUCKET).block(ModBlocks.LIQUID_CANDY_BLOCK);
	}

	public static void registerFluids() {
		LIQUID_CHOCOLATE_SOURCE = FLUIDS.register("fluid_chocolate_source", () -> new ForgeFlowingFluid.Source(liquidChocolateProperties()));
		LIQUID_CHOCOLATE_FLOWING = FLUIDS.register("fluid_chocolate_flowing", () -> new ForgeFlowingFluid.Flowing(liquidChocolateProperties()));
		LIQUID_CANDY_SOURCE = FLUIDS.register("fluid_candy_source", () -> new ForgeFlowingFluid.Source(liquidCandyProperties()));
		LIQUID_CANDY_FLOWING = FLUIDS.register("fluid_candy_flowing", () -> new ForgeFlowingFluid.Flowing(liquidCandyProperties()));

		LIQUID_CHOCOLATE_FLUID_TYPE = FLUID_TYPES.register("fluid_chocolate_fluid_type", () -> new LiquidChocolateFluidType(FluidType.Properties.create().density(1030).temperature(315).rarity(Rarity.COMMON)));
		LIQUID_CANDY_FLUID_TYPE = FLUID_TYPES.register("fluid_candy_fluid_type", () -> new LiquidChocolateFluidType(FluidType.Properties.create().lightLevel(12).density(2000).viscosity(3000).temperature(1000).rarity(Rarity.COMMON)));
	}
}
