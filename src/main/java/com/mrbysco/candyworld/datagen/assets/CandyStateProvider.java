package com.mrbysco.candyworld.datagen.assets;

import com.mrbysco.candyworld.CandyWorld;
import com.mrbysco.candyworld.registry.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

public class CandyStateProvider extends BlockStateProvider {
	public CandyStateProvider(DataGenerator gen, ExistingFileHelper helper) {
		super(gen, CandyWorld.MOD_ID, helper);
	}

	@Override
	protected void registerStatesAndModels() {
		getRandomizedBottomTop(ModBlocks.CANDY_GRASS_BLOCK,
				new ResourceLocation(CandyWorld.MOD_ID, "block/candy_grass_milk_side"),
				new ResourceLocation(CandyWorld.MOD_ID, "block/milk_brownie_block"),
				new ResourceLocation(CandyWorld.MOD_ID, "block/candy_grass_milk_top"));
		getRandomizedBottomTop(ModBlocks.CHOCOLATE_COVERED_WHITE_BROWNIE,
				new ResourceLocation(CandyWorld.MOD_ID, "block/candy_grass_white_side"),
				new ResourceLocation(CandyWorld.MOD_ID, "block/white_brownie_block"),
				new ResourceLocation(CandyWorld.MOD_ID, "block/chocolate_block_milk"));
		getRandomizedBottomTop(ModBlocks.DARK_CANDY_GRASS,
				new ResourceLocation(CandyWorld.MOD_ID, "block/dark_brownie_block"),
				new ResourceLocation(CandyWorld.MOD_ID, "block/dark_brownie_block"),
				new ResourceLocation(CandyWorld.MOD_ID, "block/candy_grass_milk_side")); //TODO: Change the top texture sometime
		getRandomizedSimple(ModBlocks.DARK_CHOCOLATE_BLOCK,
				new ResourceLocation(CandyWorld.MOD_ID, "block/chocolate_block_dark"));
		getRandomizedSimple(ModBlocks.MILK_CHOCOLATE_BLOCK,
				new ResourceLocation(CandyWorld.MOD_ID, "block/chocolate_block_milk"));
		getRandomizedSimple(ModBlocks.WHITE_CHOCOLATE_BLOCK,
				new ResourceLocation(CandyWorld.MOD_ID, "block/chocolate_block_white"));
		simpleBlock(ModBlocks.CHOCOLATE_SAPLING.get());
		randomFileState(ModBlocks.COTTON_CANDY_BUSH,
				models().getExistingFile(new ResourceLocation(CandyWorld.MOD_ID, "block/cotton_candy_bush0")),
				models().getExistingFile(new ResourceLocation(CandyWorld.MOD_ID, "block/cotton_candy_bush1")),
				models().getExistingFile(new ResourceLocation(CandyWorld.MOD_ID, "block/cotton_candy_bush2")),
				models().getExistingFile(new ResourceLocation(CandyWorld.MOD_ID, "block/cotton_candy_bush3")),
				models().getExistingFile(new ResourceLocation(CandyWorld.MOD_ID, "block/cotton_candy_bush4"))
		);
		simpleBlock(ModBlocks.COTTON_CANDY_LEAVES.get());
		simpleBlock(ModBlocks.COTTON_CANDY_PLANT.get(),
				models().getExistingFile(new ResourceLocation(CandyWorld.MOD_ID, "block/cotton_candy_plant")));
		simpleBlock(ModBlocks.COTTON_CANDY_SAPLING.get());
		simpleBlock(ModBlocks.COOKIE_ORE.get());
		simpleBlock(ModBlocks.CRYSTALLIZED_SUGAR.get());
		simpleBlock(ModBlocks.CRYSTALLIZED_SUGAR_COOKIE_ORE.get());
		simpleBlock(ModBlocks.DARK_BROWNIE_BLOCK.get());
		facingState(ModBlocks.DARK_CHOCOLATE_BAR_BLOCK,
				models().getExistingFile(new ResourceLocation(CandyWorld.MOD_ID, "block/dark_chocolate_bar_block")));
		simpleBlock(ModBlocks.DARK_CHOCOLATE_BRICK.get());
		simpleBlock(ModBlocks.DARK_CHOCOLATE_LEAVES.get());
		crossState(ModBlocks.DARK_CHOCOLATE_MUSHROOM,
				new ResourceLocation(CandyWorld.MOD_ID, "block/dark_chocolate_mushroom0"),
				new ResourceLocation(CandyWorld.MOD_ID, "block/dark_chocolate_mushroom1"));
		simpleBlock(ModBlocks.DARK_CHOCOLATE_WORKBENCH.get(),
				models().cubeBottomTop(ModBlocks.DARK_CHOCOLATE_WORKBENCH.getId().getPath(),
						new ResourceLocation(CandyWorld.MOD_ID, "block/chocolate_workbench_dark_side"),
						new ResourceLocation(CandyWorld.MOD_ID, "block/wafer_stick_top"),
						new ResourceLocation(CandyWorld.MOD_ID, "block/chocolate_workbench_dark_top")
				));

		simpleBlock(ModBlocks.MILK_BROWNIE_BLOCK.get());
		facingState(ModBlocks.MILK_CHOCOLATE_BAR_BLOCK,
				models().getExistingFile(new ResourceLocation(CandyWorld.MOD_ID, "block/milk_chocolate_bar_block")));
		simpleBlock(ModBlocks.MILK_CHOCOLATE_BRICK.get());
		simpleBlock(ModBlocks.MILK_CHOCOLATE_LEAVES.get());
		crossState(ModBlocks.MILK_CHOCOLATE_MUSHROOM,
				new ResourceLocation(CandyWorld.MOD_ID, "block/white_chocolate_mushroom0"),
				new ResourceLocation(CandyWorld.MOD_ID, "block/white_chocolate_mushroom1"));
		simpleBlock(ModBlocks.MILK_CHOCOLATE_WORKBENCH.get(),
				models().cubeBottomTop(ModBlocks.MILK_CHOCOLATE_WORKBENCH.getId().getPath(),
						new ResourceLocation(CandyWorld.MOD_ID, "block/chocolate_workbench_milk_side"),
						new ResourceLocation(CandyWorld.MOD_ID, "block/wafer_stick_top"),
						new ResourceLocation(CandyWorld.MOD_ID, "block/chocolate_workbench_milk_top")
				));
		
		simpleBlock(ModBlocks.WHITE_BROWNIE_BLOCK.get());
		facingState(ModBlocks.WHITE_CHOCOLATE_BAR_BLOCK,
				models().getExistingFile(new ResourceLocation(CandyWorld.MOD_ID, "block/white_chocolate_bar_block")));
		simpleBlock(ModBlocks.WHITE_CHOCOLATE_BRICK.get());
		simpleBlock(ModBlocks.WHITE_CHOCOLATE_LEAVES.get());
		crossState(ModBlocks.WHITE_CHOCOLATE_MUSHROOM,
				new ResourceLocation(CandyWorld.MOD_ID, "block/white_chocolate_mushroom0"),
				new ResourceLocation(CandyWorld.MOD_ID, "block/white_chocolate_mushroom1"));
		simpleBlock(ModBlocks.WHITE_CHOCOLATE_WORKBENCH.get(),
				models().cubeBottomTop(ModBlocks.WHITE_CHOCOLATE_WORKBENCH.getId().getPath(),
						new ResourceLocation(CandyWorld.MOD_ID, "block/chocolate_workbench_white_side"),
						new ResourceLocation(CandyWorld.MOD_ID, "block/wafer_stick_top"),
						new ResourceLocation(CandyWorld.MOD_ID, "block/chocolate_workbench_white_top")
				));

		simpleBlock(ModBlocks.GREEN_CANDY_CANE_BLOCK.get(),
				models().cubeColumn(ModBlocks.GREEN_CANDY_CANE_BLOCK.getId().getPath(),
						new ResourceLocation(CandyWorld.MOD_ID, "block/caneblock_green"),
						new ResourceLocation(CandyWorld.MOD_ID, "block/caneblock_green_top")
				));
		simpleBlock(ModBlocks.GREEN_CANDY_CANE_WORKBENCH.get(),
				models().cubeBottomTop(ModBlocks.GREEN_CANDY_CANE_WORKBENCH.getId().getPath(),
						new ResourceLocation(CandyWorld.MOD_ID, "block/candy_cane_workbench_green_side"),
						new ResourceLocation(CandyWorld.MOD_ID, "block/caneblock_green_top"),
						new ResourceLocation(CandyWorld.MOD_ID, "block/candy_cane_workbench_top")
				));
		simpleBlock(ModBlocks.RED_CANDY_CANE_BLOCK.get(),
				models().cubeColumn(ModBlocks.RED_CANDY_CANE_BLOCK.getId().getPath(),
						new ResourceLocation(CandyWorld.MOD_ID, "block/caneblock_red"),
						new ResourceLocation(CandyWorld.MOD_ID, "block/caneblock_red_top")
				));
		simpleBlock(ModBlocks.RED_CANDY_CANE_WORKBENCH.get(),
				models().cubeBottomTop(ModBlocks.RED_CANDY_CANE_WORKBENCH.getId().getPath(),
						new ResourceLocation(CandyWorld.MOD_ID, "block/candy_cane_workbench_red_side"),
						new ResourceLocation(CandyWorld.MOD_ID, "block/caneblock_red_top"),
						new ResourceLocation(CandyWorld.MOD_ID, "block/candy_cane_workbench_top")
				));
		simpleBlock(ModBlocks.RED_GREEN_CANDY_CANE_BLOCK.get(),
				models().cubeColumn(ModBlocks.RED_GREEN_CANDY_CANE_BLOCK.getId().getPath(),
						new ResourceLocation(CandyWorld.MOD_ID, "block/caneblock_red_green"),
						new ResourceLocation(CandyWorld.MOD_ID, "block/caneblock_red_green_top")
				));
		simpleBlock(ModBlocks.RED_GREEN_CANDY_CANE_WORKBENCH.get(),
				models().cubeBottomTop(ModBlocks.RED_GREEN_CANDY_CANE_WORKBENCH.getId().getPath(),
						new ResourceLocation(CandyWorld.MOD_ID, "block/candy_cane_workbench_red_green_side"),
						new ResourceLocation(CandyWorld.MOD_ID, "block/caneblock_red_green_top"),
						new ResourceLocation(CandyWorld.MOD_ID, "block/candy_cane_workbench_top")
				));
		simpleBlock(ModBlocks.WHITE_CANDY_CANE_BLOCK.get(),
				models().cubeColumn(ModBlocks.WHITE_CANDY_CANE_BLOCK.getId().getPath(),
						new ResourceLocation(CandyWorld.MOD_ID, "block/caneblock_white"),
						new ResourceLocation(CandyWorld.MOD_ID, "block/caneblock_white_top")
				));
		simpleBlock(ModBlocks.WHITE_CANDY_CANE_WORKBENCH.get(),
				models().cubeBottomTop(ModBlocks.WHITE_CANDY_CANE_WORKBENCH.getId().getPath(),
						new ResourceLocation(CandyWorld.MOD_ID, "block/candy_cane_workbench_white_side"),
						new ResourceLocation(CandyWorld.MOD_ID, "block/caneblock_white_top"),
						new ResourceLocation(CandyWorld.MOD_ID, "block/candy_cane_workbench_top")
				));
		simpleBlock(ModBlocks.WHITE_RED_CANDY_CANE_BLOCK.get(),
				models().cubeColumn(ModBlocks.WHITE_RED_CANDY_CANE_BLOCK.getId().getPath(),
						new ResourceLocation(CandyWorld.MOD_ID, "block/caneblock_white_red"),
						new ResourceLocation(CandyWorld.MOD_ID, "block/caneblock_white_red_top")
				));
		simpleBlock(ModBlocks.WHITE_RED_CANDY_CANE_WORKBENCH.get(),
				models().cubeBottomTop(ModBlocks.WHITE_RED_CANDY_CANE_WORKBENCH.getId().getPath(),
						new ResourceLocation(CandyWorld.MOD_ID, "block/candy_cane_workbench_white_red_side"),
						new ResourceLocation(CandyWorld.MOD_ID, "block/caneblock_white_red_top"),
						new ResourceLocation(CandyWorld.MOD_ID, "block/candy_cane_workbench_top")
				));
		simpleBlock(ModBlocks.WHITE_GREEN_CANDY_CANE_BLOCK.get(),
				models().cubeColumn(ModBlocks.WHITE_GREEN_CANDY_CANE_BLOCK.getId().getPath(),
						new ResourceLocation(CandyWorld.MOD_ID, "block/caneblock_white_green"),
						new ResourceLocation(CandyWorld.MOD_ID, "block/caneblock_white_green_top")
				));
		simpleBlock(ModBlocks.WHITE_GREEN_CANDY_CANE_WORKBENCH.get(),
				models().cubeBottomTop(ModBlocks.WHITE_GREEN_CANDY_CANE_WORKBENCH.getId().getPath(),
						new ResourceLocation(CandyWorld.MOD_ID, "block/candy_cane_workbench_white_green_side"),
						new ResourceLocation(CandyWorld.MOD_ID, "block/caneblock_white_green_top"),
						new ResourceLocation(CandyWorld.MOD_ID, "block/candy_cane_workbench_top")
				));
		simpleBlock(ModBlocks.RED_GUMMY_WORKBENCH.get(), models().getExistingFile(new ResourceLocation(CandyWorld.MOD_ID, "block/gummy_workbench")));
		simpleBlock(ModBlocks.ORANGE_GUMMY_WORKBENCH.get(), models().getExistingFile(new ResourceLocation(CandyWorld.MOD_ID, "block/gummy_workbench")));
		simpleBlock(ModBlocks.YELLOW_GUMMY_WORKBENCH.get(), models().getExistingFile(new ResourceLocation(CandyWorld.MOD_ID, "block/gummy_workbench")));
		simpleBlock(ModBlocks.WHITE_GUMMY_WORKBENCH.get(), models().getExistingFile(new ResourceLocation(CandyWorld.MOD_ID, "block/gummy_workbench")));
		simpleBlock(ModBlocks.GREEN_GUMMY_WORKBENCH.get(), models().getExistingFile(new ResourceLocation(CandyWorld.MOD_ID, "block/gummy_workbench")));
		simpleBlock(ModBlocks.RED_GUMMY_BLOCK.get(), models().getExistingFile(new ResourceLocation(CandyWorld.MOD_ID, "block/gummy_block")));
		simpleBlock(ModBlocks.ORANGE_GUMMY_BLOCK.get(), models().getExistingFile(new ResourceLocation(CandyWorld.MOD_ID, "block/gummy_block")));
		simpleBlock(ModBlocks.YELLOW_GUMMY_BLOCK.get(), models().getExistingFile(new ResourceLocation(CandyWorld.MOD_ID, "block/gummy_block")));
		simpleBlock(ModBlocks.WHITE_GUMMY_BLOCK.get(), models().getExistingFile(new ResourceLocation(CandyWorld.MOD_ID, "block/gummy_block")));
		simpleBlock(ModBlocks.GREEN_GUMMY_BLOCK.get(), models().getExistingFile(new ResourceLocation(CandyWorld.MOD_ID, "block/gummy_block")));
		simpleBlock(ModBlocks.RED_HARDENED_GUMMY_BLOCK.get(), models().getExistingFile(new ResourceLocation(CandyWorld.MOD_ID, "block/hardened_gummy_block")));
		simpleBlock(ModBlocks.ORANGE_HARDENED_GUMMY_BLOCK.get(), models().getExistingFile(new ResourceLocation(CandyWorld.MOD_ID, "block/hardened_gummy_block")));
		simpleBlock(ModBlocks.YELLOW_HARDENED_GUMMY_BLOCK.get(), models().getExistingFile(new ResourceLocation(CandyWorld.MOD_ID, "block/hardened_gummy_block")));
		simpleBlock(ModBlocks.WHITE_HARDENED_GUMMY_BLOCK.get(), models().getExistingFile(new ResourceLocation(CandyWorld.MOD_ID, "block/hardened_gummy_block")));
		simpleBlock(ModBlocks.GREEN_HARDENED_GUMMY_BLOCK.get(), models().getExistingFile(new ResourceLocation(CandyWorld.MOD_ID, "block/hardened_gummy_block")));
		wormBlock(ModBlocks.RED_GUMMY_WORM_BLOCK, models().getExistingFile(new ResourceLocation(CandyWorld.MOD_ID, "block/gummy_worm_block")));
		wormBlock(ModBlocks.ORANGE_GUMMY_WORM_BLOCK, models().getExistingFile(new ResourceLocation(CandyWorld.MOD_ID, "block/gummy_worm_block")));
		wormBlock(ModBlocks.YELLOW_GUMMY_WORM_BLOCK, models().getExistingFile(new ResourceLocation(CandyWorld.MOD_ID, "block/gummy_worm_block")));
		wormBlock(ModBlocks.WHITE_GUMMY_WORM_BLOCK, models().getExistingFile(new ResourceLocation(CandyWorld.MOD_ID, "block/gummy_worm_block")));
		wormBlock(ModBlocks.GREEN_GUMMY_WORM_BLOCK, models().getExistingFile(new ResourceLocation(CandyWorld.MOD_ID, "block/gummy_worm_block")));
		simpleBlock(ModBlocks.LIQUID_CANDY_BLOCK.get(), models().getExistingFile(new ResourceLocation(CandyWorld.MOD_ID, "block/liquid_candy")));
		simpleBlock(ModBlocks.LIQUID_CHOCOLATE_BLOCK.get(), models().getExistingFile(new ResourceLocation(CandyWorld.MOD_ID, "block/liquid_chocolate")));
		simpleBlock(ModBlocks.WAFER_STICK_BLOCK.get(), models().getExistingFile(new ResourceLocation(CandyWorld.MOD_ID, "block/wafer_stick_block")));
		simpleBlock(ModBlocks.TELEPORTER_ORE.get());
		simpleBlock(ModBlocks.SUGAR_SAND.get());
		simpleBlock(ModBlocks.LICORICE_BLOCK.get());
	}

	private void getRandomizedBottomTop(RegistryObject<Block> registryObject, ResourceLocation side, ResourceLocation bottom, ResourceLocation top) {
		randomRotated(registryObject.get(),
				getCubeBottomTop(registryObject, side, bottom, top),
				getMirroredBottomTop(registryObject, side, bottom, top));
	}

	private void getRandomizedSimple(RegistryObject<Block> registryObject, ResourceLocation texture) {
		randomRotated(registryObject.get(),
				models().cubeAll(registryObject.getId().getPath(), texture),
				getMirroredAll(registryObject, texture));
	}

	private ModelFile getCubeBottomTop(RegistryObject<Block> registryObject, ResourceLocation side, ResourceLocation bottom, ResourceLocation top) {
		return models().cubeBottomTop(registryObject.getId().getPath(), side, bottom, top);
	}

	private ModelFile getMirroredBottomTop(RegistryObject<Block> registryObject, ResourceLocation side, ResourceLocation bottom, ResourceLocation top) {
		return models().withExistingParent(registryObject.getId().getPath() + "_mirrored", "block/cube_mirrored")
				.texture("particle", bottom)
				.texture("up", top)
				.texture("down", bottom)
				.texture("north", side)
				.texture("east", side)
				.texture("south", side)
				.texture("west", side);
	}

	private ModelFile getMirroredAll(RegistryObject<Block> registryObject, ResourceLocation all) {
		return models().withExistingParent(registryObject.getId().getPath() + "_mirrored", "block/cube_mirrored_all")
				.texture("all", all);
	}

	private void randomRotated(Block block, ModelFile model, ModelFile modelMirrored) {
		getVariantBuilder(block)
				.partialState()
				.addModels(
						new ConfiguredModel(model, 0, 0, false),
						new ConfiguredModel(modelMirrored, 0, 0, false),
						new ConfiguredModel(model, 0, 180, false),
						new ConfiguredModel(modelMirrored, 0, 180, false)
				);
	}

	private void crossState(RegistryObject<Block> registryObject, ResourceLocation... crossLocations) {
		List<ModelFile> crossModels = new ArrayList<>();
		for (int i = 0; i < crossLocations.length; i++) {
			crossModels.add(models().cross(registryObject.getId().getPath() + i, crossLocations[i]));
		}
		ConfiguredModel[] configuredModels = new ConfiguredModel[crossModels.size()];
		for (int i = 0; i < crossModels.size(); i++) {
			configuredModels[i] = new ConfiguredModel(crossModels.get(i));
		}
		getVariantBuilder(registryObject.get())
				.partialState()
				.addModels(configuredModels);
	}

	private void randomFileState(RegistryObject<Block> registryObject, ModelFile... modelFiles) {
		ConfiguredModel[] configuredModels = new ConfiguredModel[modelFiles.length];
		for (int i = 0; i < modelFiles.length; i++) {
			configuredModels[i] = new ConfiguredModel(modelFiles[i]);
		}
		getVariantBuilder(registryObject.get())
				.partialState()
				.addModels(configuredModels);
	}

	private void facingState(RegistryObject<Block> registryObject, ModelFile file) {
		getVariantBuilder(registryObject.get())
				.partialState().with(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)
				.modelForState().modelFile(file).addModel()
				.partialState().with(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST)
				.modelForState().modelFile(file).rotationY(90).addModel()
				.partialState().with(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH)
				.modelForState().modelFile(file).rotationY(180).addModel()
				.partialState().with(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST)
				.modelForState().modelFile(file).rotationY(270).addModel();
	}

	private void wormBlock(RegistryObject<Block> registryObject, ModelFile file) {
		getVariantBuilder(registryObject.get())
				.partialState().with(BlockStateProperties.AXIS, Direction.Axis.X)
				.modelForState().modelFile(file).rotationX(90).rotationY(90).addModel()
				.partialState().with(BlockStateProperties.AXIS, Direction.Axis.Y)
				.modelForState().modelFile(file).addModel()
				.partialState().with(BlockStateProperties.AXIS, Direction.Axis.Z)
				.modelForState().modelFile(file).rotationX(90).addModel();
	}
}
