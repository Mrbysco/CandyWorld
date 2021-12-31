package com.mrbysco.candyworld.datagen.providers.client;

import com.mrbysco.candyworld.CandyWorld;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class CandyBlockStates extends BlockStateProvider {
	public CandyBlockStates(DataGenerator gen, ExistingFileHelper helper) {
		super(gen, CandyWorld.MOD_ID, helper);
	}

	@Override
	protected void registerStatesAndModels() {

	}
}
