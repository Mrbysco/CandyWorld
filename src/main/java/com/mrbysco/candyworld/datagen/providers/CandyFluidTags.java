package com.mrbysco.candyworld.datagen.providers;

import com.mrbysco.candyworld.CandyWorld;
import com.mrbysco.candyworld.block.fluid.ModFluids;
import com.mrbysco.candyworld.registry.ModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.FluidTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class CandyFluidTags extends FluidTagsProvider {
	public CandyFluidTags(DataGenerator dataGenerator, @Nullable ExistingFileHelper existingFileHelper) {
		super(dataGenerator, CandyWorld.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		this.tag(ModTags.CANDY).add(ModFluids.LIQUID_CHOCOLATE_SOURCE.get(), ModFluids.LIQUID_CHOCOLATE_FLOWING.get(),
				ModFluids.LIQUID_CANDY_SOURCE.get(), ModFluids.LIQUID_CANDY_FLOWING.get());
	}
}
