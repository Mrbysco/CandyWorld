package com.mrbysco.candyworld.world.carvers;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import com.mrbysco.candyworld.block.fluid.ModFluids;
import com.mrbysco.candyworld.registry.ModBlocks;
import com.mrbysco.candyworld.registry.ModTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import org.apache.commons.lang3.mutable.MutableBoolean;

import java.util.BitSet;
import java.util.Random;
import java.util.function.Function;

public class CandyCanyonWorldCarver extends WorldCarver<ProbabilityConfig> {
	private final float[] rs = new float[1024];
	public static FluidState LIQUID_CANDY = ModFluids.LIQUID_CANDY_SOURCE.get().defaultFluidState();

	public CandyCanyonWorldCarver(Codec<ProbabilityConfig> configCodec) {
		super(configCodec, 256);
		this.replaceableBlocks = ImmutableSet.of(Blocks.STONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL,
				Blocks.GRASS_BLOCK, Blocks.TERRACOTTA, Blocks.WHITE_TERRACOTTA, Blocks.ORANGE_TERRACOTTA, Blocks.MAGENTA_TERRACOTTA, Blocks.LIGHT_BLUE_TERRACOTTA,
				Blocks.YELLOW_TERRACOTTA, Blocks.LIME_TERRACOTTA, Blocks.PINK_TERRACOTTA, Blocks.GRAY_TERRACOTTA, Blocks.LIGHT_GRAY_TERRACOTTA, Blocks.CYAN_TERRACOTTA,
				Blocks.PURPLE_TERRACOTTA, Blocks.BLUE_TERRACOTTA, Blocks.BROWN_TERRACOTTA, Blocks.GREEN_TERRACOTTA, Blocks.RED_TERRACOTTA, Blocks.BLACK_TERRACOTTA,
				Blocks.SANDSTONE, Blocks.RED_SANDSTONE, Blocks.MYCELIUM, Blocks.SNOW, Blocks.PACKED_ICE, ModBlocks.CRYSTALLIZED_SUGAR.get(), ModBlocks.SUGAR_SAND.get(),
				ModBlocks.RED_GUMMY_BLOCK.get(), ModBlocks.ORANGE_GUMMY_BLOCK.get(), ModBlocks.YELLOW_GUMMY_BLOCK.get(), ModBlocks.WHITE_GUMMY_BLOCK.get(),
				ModBlocks.GREEN_GUMMY_BLOCK.get(), ModBlocks.RED_HARDENED_GUMMY_BLOCK.get(), ModBlocks.ORANGE_HARDENED_GUMMY_BLOCK.get(), ModBlocks.YELLOW_HARDENED_GUMMY_BLOCK.get(),
				ModBlocks.WHITE_HARDENED_GUMMY_BLOCK.get(), ModBlocks.GREEN_HARDENED_GUMMY_BLOCK.get(), ModBlocks.CANDY_GRASS_BLOCK.get(), ModBlocks.WHITE_BROWNIE_BLOCK.get(),
				ModBlocks.CHOCOLATE_COVERED_WHITE_BROWNIE.get(), ModBlocks.MILK_BROWNIE_BLOCK.get(), ModBlocks.DARK_CANDY_GRASS.get(), ModBlocks.DARK_BROWNIE_BLOCK.get());
		this.liquids = ImmutableSet.of(Fluids.WATER, ModFluids.LIQUID_CANDY_SOURCE.get(), ModFluids.LIQUID_CHOCOLATE_SOURCE.get());
	}

	@Override
	protected boolean canReplaceBlock(BlockState state) {
		return this.replaceableBlocks.contains(state.getBlock());
	}

	@Override
	protected boolean canReplaceBlock(BlockState state, BlockState fluidState) {
		return this.canReplaceBlock(state) || state.is(ModBlocks.SUGAR_SAND.get()) && !fluidState.getFluidState().is(ModTags.CANDY);
	}

	public boolean isStartChunk(Random p_212868_1_, int p_212868_2_, int p_212868_3_, ProbabilityConfig p_212868_4_) {
		return p_212868_1_.nextFloat() <= p_212868_4_.probability;
	}

	public boolean carve(IChunk p_225555_1_, Function<BlockPos, Biome> p_225555_2_, Random p_225555_3_, int p_225555_4_, int p_225555_5_, int p_225555_6_, int p_225555_7_, int p_225555_8_, BitSet p_225555_9_, ProbabilityConfig p_225555_10_) {
		int i = (this.getRange() * 2 - 1) * 16;
		double d0 = (double) (p_225555_5_ * 16 + p_225555_3_.nextInt(16));
		double d1 = (double) (p_225555_3_.nextInt(p_225555_3_.nextInt(40) + 8) + 20);
		double d2 = (double) (p_225555_6_ * 16 + p_225555_3_.nextInt(16));
		float f = p_225555_3_.nextFloat() * ((float) Math.PI * 2F);
		float f1 = (p_225555_3_.nextFloat() - 0.5F) * 2.0F / 8.0F;
		double d3 = 3.0D;
		float f2 = (p_225555_3_.nextFloat() * 2.0F + p_225555_3_.nextFloat()) * 2.0F;
		int j = i - p_225555_3_.nextInt(i / 4);
		int k = 0;
		this.genCanyon(p_225555_1_, p_225555_2_, p_225555_3_.nextLong(), p_225555_4_, p_225555_7_, p_225555_8_, d0, d1, d2, f2, f, f1, 0, j, 3.0D, p_225555_9_);
		return true;
	}

	private void genCanyon(IChunk p_227204_1_, Function<BlockPos, Biome> p_227204_2_, long p_227204_3_, int p_227204_5_, int p_227204_6_, int p_227204_7_, double p_227204_8_, double p_227204_10_, double p_227204_12_, float p_227204_14_, float p_227204_15_, float p_227204_16_, int p_227204_17_, int p_227204_18_, double p_227204_19_, BitSet p_227204_21_) {
		Random random = new Random(p_227204_3_);
		float f = 1.0F;

		for (int i = 0; i < 256; ++i) {
			if (i == 0 || random.nextInt(3) == 0) {
				f = 1.0F + random.nextFloat() * random.nextFloat();
			}

			this.rs[i] = f * f;
		}

		float f4 = 0.0F;
		float f1 = 0.0F;

		for (int j = p_227204_17_; j < p_227204_18_; ++j) {
			double d0 = 1.5D + (double) (MathHelper.sin((float) j * (float) Math.PI / (float) p_227204_18_) * p_227204_14_);
			double d1 = d0 * p_227204_19_;
			d0 = d0 * ((double) random.nextFloat() * 0.25D + 0.75D);
			d1 = d1 * ((double) random.nextFloat() * 0.25D + 0.75D);
			float f2 = MathHelper.cos(p_227204_16_);
			float f3 = MathHelper.sin(p_227204_16_);
			p_227204_8_ += (double) (MathHelper.cos(p_227204_15_) * f2);
			p_227204_10_ += (double) f3;
			p_227204_12_ += (double) (MathHelper.sin(p_227204_15_) * f2);
			p_227204_16_ = p_227204_16_ * 0.7F;
			p_227204_16_ = p_227204_16_ + f1 * 0.05F;
			p_227204_15_ += f4 * 0.05F;
			f1 = f1 * 0.8F;
			f4 = f4 * 0.5F;
			f1 = f1 + (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
			f4 = f4 + (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;
			if (random.nextInt(4) != 0) {
				if (!this.canReach(p_227204_6_, p_227204_7_, p_227204_8_, p_227204_12_, j, p_227204_18_, p_227204_14_)) {
					return;
				}

				this.carveSphere(p_227204_1_, p_227204_2_, p_227204_3_, p_227204_5_, p_227204_6_, p_227204_7_, p_227204_8_, p_227204_10_, p_227204_12_, d0, d1, p_227204_21_);
			}
		}

	}

	protected boolean skip(double p_222708_1_, double p_222708_3_, double p_222708_5_, int p_222708_7_) {
		return (p_222708_1_ * p_222708_1_ + p_222708_5_ * p_222708_5_) * (double) this.rs[p_222708_7_ - 1] + p_222708_3_ * p_222708_3_ / 6.0D >= 1.0D;
	}

	@Override
	protected boolean carveBlock(IChunk chunk, Function<BlockPos, Biome> posBiomeFunction, BitSet bitSet, Random random, Mutable mutable, Mutable p_230358_6_, Mutable p_230358_7_, int p_230358_8_, int p_230358_9_, int p_230358_10_, int p_230358_11_, int p_230358_12_, int p_230358_13_, int p_230358_14_, int p_230358_15_, MutableBoolean isGrass) {
		int i = p_230358_13_ | p_230358_15_ << 4 | p_230358_14_ << 8;
		if (bitSet.get(i)) {
			return false;
		} else {
			bitSet.set(i);
			mutable.set(p_230358_11_, p_230358_14_, p_230358_12_);
			BlockState blockstate = chunk.getBlockState(mutable);
			BlockState blockstate1 = chunk.getBlockState(p_230358_6_.setWithOffset(mutable, Direction.UP));
			if (blockstate.is(Blocks.GRASS_BLOCK) || blockstate.is(Blocks.MYCELIUM) || blockstate.is(ModTags.COVERED_BROWNIE)) {
				isGrass.setTrue();
			}

			if (!this.canReplaceBlock(blockstate, blockstate1)) {
				return false;
			} else {
				if (p_230358_14_ < 11) {
					chunk.setBlockState(mutable, LIQUID_CANDY.createLegacyBlock(), false);
				} else {
					chunk.setBlockState(mutable, CAVE_AIR, false);
					if (isGrass.isTrue()) {
						p_230358_7_.setWithOffset(mutable, Direction.DOWN);
						if (chunk.getBlockState(p_230358_7_).is(Blocks.DIRT) || chunk.getBlockState(p_230358_7_).is(ModTags.BROWNIE)) {
							chunk.setBlockState(p_230358_7_, posBiomeFunction.apply(mutable).getGenerationSettings().getSurfaceBuilderConfig().getTopMaterial(), false);
						}
					}
				}

				return true;
			}
		}
	}
}