package com.mrbysco.candyworld.datagen.assets;

import com.mrbysco.candyworld.CandyWorld;
import com.mrbysco.candyworld.block.chocolate.ChocolateMushroomBlock;
import com.mrbysco.candyworld.block.cottoncandy.CottonCandyBushBlock;
import com.mrbysco.candyworld.block.fluid.ModFluids;
import com.mrbysco.candyworld.block.gummy.BaseGummyBlock;
import com.mrbysco.candyworld.block.gummy.GummyBlock;
import com.mrbysco.candyworld.block.gummy.GummyWormBlock;
import com.mrbysco.candyworld.block.workbench.GummyWorkbenchBlock;
import com.mrbysco.candyworld.registry.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.loaders.DynamicBucketModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class CandyItemModelProvider extends ItemModelProvider {
	public CandyItemModelProvider(DataGenerator gen, ExistingFileHelper helper) {
		super(gen, CandyWorld.MOD_ID, helper);
	}

	@Override
	protected void registerModels() {
		for (RegistryObject<Item> registryObject : ModItems.ITEMS.getEntries()) {
			if (registryObject.get() instanceof BlockItem blockItem) {
				if (blockItem.getBlock() instanceof SaplingBlock) {
					singleTexture(registryObject.getId().getPath(), new ResourceLocation("item/generated"),
							"layer0", new ResourceLocation(CandyWorld.MOD_ID, "block/" + registryObject.getId().getPath()));
				} else if (blockItem.getBlock() instanceof ChocolateMushroomBlock) {
					withExistingParent(registryObject.getId().getPath(),
							new ResourceLocation(CandyWorld.MOD_ID, "block/" + registryObject.getId().getPath() + "0"));
				} else if (blockItem.getBlock() instanceof CottonCandyBushBlock) {
					singleTexture(registryObject.getId().getPath(), new ResourceLocation("item/generated"),
							"layer0", new ResourceLocation(CandyWorld.MOD_ID, "block/" + registryObject.getId().getPath() + "0"));
				} else if (blockItem.getBlock() instanceof GummyBlock) {
					withExistingParent(registryObject.getId().getPath(),
							new ResourceLocation(CandyWorld.MOD_ID, "block/gummy_block"));
				} else if (blockItem.getBlock() instanceof GummyWormBlock) {
					withExistingParent(registryObject.getId().getPath(),
							new ResourceLocation(CandyWorld.MOD_ID, "block/gummy_worm_block"));
				} else if (blockItem.getBlock() instanceof GummyWorkbenchBlock) {
					withExistingParent(registryObject.getId().getPath(),
							new ResourceLocation(CandyWorld.MOD_ID, "block/gummy_workbench"));
				} else if (blockItem.getBlock() instanceof BaseGummyBlock) {
					withExistingParent(registryObject.getId().getPath(),
							new ResourceLocation(CandyWorld.MOD_ID, "block/hardened_gummy_block"));
				} else {
					withExistingParent(registryObject.getId().getPath(),
							new ResourceLocation(CandyWorld.MOD_ID, "block/" + registryObject.getId().getPath()));
				}
			} else {
				if (registryObject.get() instanceof BucketItem) {
					continue;
				}
				if (registryObject.get() instanceof TieredItem) {
					singleTexture(registryObject.getId().getPath(), new ResourceLocation("item/handheld"),
							"layer0", new ResourceLocation(CandyWorld.MOD_ID, "item/" + registryObject.getId().getPath()));
				} else if (registryObject.get() instanceof SpawnEggItem) {
					withExistingParent(registryObject.getId().getPath(), new ResourceLocation("item/template_spawn_egg"));
				} else {
					singleTexture(registryObject.getId().getPath(), new ResourceLocation("item/generated"),
							"layer0", new ResourceLocation(CandyWorld.MOD_ID, "item/" + registryObject.getId().getPath()));
				}
			}
		}
		generateBucket(ModItems.LIQUID_CANDY_BUCKET, ModFluids.LIQUID_CANDY_SOURCE);
		generateBucket(ModItems.LIQUID_CHOCOLATE_BUCKET, ModFluids.LIQUID_CANDY_SOURCE);
	}

	private void generateBucket(RegistryObject<Item> registryObject, RegistryObject<FlowingFluid> fluidRegistryObject) {
		withExistingParent(registryObject.getId().getPath(), new ResourceLocation("forge", "item/bucket"))
				.customLoader(DynamicBucketModelBuilder::begin)
				.fluid(fluidRegistryObject.get());
	}
}
