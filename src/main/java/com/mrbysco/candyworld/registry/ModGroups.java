package com.mrbysco.candyworld.registry;

import com.mrbysco.candyworld.CandyWorld;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ModGroups {
	public static final ItemGroup BLOCKS = (new ItemGroup(CandyWorld.MOD_ID + ".blocks") {
		@OnlyIn(Dist.CLIENT)
		public ItemStack makeIcon() {
			return new ItemStack(ModBlocks.WAFER_STICK_BLOCK.get());
		}
	});

	public static final ItemGroup ITEMS = (new ItemGroup(CandyWorld.MOD_ID + ".items") {
		@OnlyIn(Dist.CLIENT)
		public ItemStack makeIcon() {
			return new ItemStack(ModItems.WAFER_STICK.get());
		}
	});

	public static final ItemGroup TOOLS = (new ItemGroup(CandyWorld.MOD_ID + ".tools") {
		@OnlyIn(Dist.CLIENT)
		public ItemStack makeIcon() {
			return new ItemStack(ModItems.MILK_CHOCOLATE_PICKAXE.get());
		}
	});
}
