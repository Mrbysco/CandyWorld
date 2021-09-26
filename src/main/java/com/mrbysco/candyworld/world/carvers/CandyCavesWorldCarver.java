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

public class CandyCavesWorldCarver extends WorldCarver<ProbabilityConfig> {
	public static FluidState LIQUID_CANDY = ModFluids.LIQUID_CANDY_SOURCE.get().defaultFluidState();

	public CandyCavesWorldCarver(Codec<ProbabilityConfig> configCodec, int height) {
		super(configCodec, height);
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

	public boolean isStartChunk(Random random, int p_212868_2_, int p_212868_3_, ProbabilityConfig probabilityConfig) {
		return random.nextFloat() <= probabilityConfig.probability;
	}

	public boolean carve(IChunk chunk, Function<BlockPos, Biome> posBiomeFunction, Random random, int p_225555_4_, int p_225555_5_, int p_225555_6_, int p_225555_7_, int p_225555_8_, BitSet bitSet, ProbabilityConfig probabilityConfig) {
		int i = (this.getRange() * 2 - 1) * 16;
		int j = random.nextInt(random.nextInt(random.nextInt(this.getCaveBound()) + 1) + 1);

		for(int k = 0; k < j; ++k) {
			double d0 = (double)(p_225555_5_ * 16 + random.nextInt(16));
			double d1 = (double)this.getCaveY(random);
			double d2 = (double)(p_225555_6_ * 16 + random.nextInt(16));
			int l = 1;
			if (random.nextInt(4) == 0) {
				double d3 = 0.5D;
				float f1 = 1.0F + random.nextFloat() * 6.0F;
				this.genRoom(chunk, posBiomeFunction, random.nextLong(), p_225555_4_, p_225555_7_, p_225555_8_, d0, d1, d2, f1, 0.5D, bitSet);
				l += random.nextInt(4);
			}

			for(int k1 = 0; k1 < l; ++k1) {
				float f = random.nextFloat() * ((float)Math.PI * 2F);
				float f3 = (random.nextFloat() - 0.5F) / 4.0F;
				float f2 = this.getThickness(random);
				int i1 = i - random.nextInt(i / 4);
				int j1 = 0;
				this.genTunnel(chunk, posBiomeFunction, random.nextLong(), p_225555_4_, p_225555_7_, p_225555_8_, d0, d1, d2, f2, f, f3, 0, i1, this.getYScale(), bitSet);
			}
		}

		return true;
	}

	protected int getCaveBound() {
		return 15;
	}

	protected float getThickness(Random random) {
		float f = random.nextFloat() * 2.0F + random.nextFloat();
		if (random.nextInt(10) == 0) {
			f *= random.nextFloat() * random.nextFloat() * 3.0F + 1.0F;
		}

		return f;
	}

	protected double getYScale() {
		return 1.0D;
	}

	protected int getCaveY(Random random) {
		return random.nextInt(random.nextInt(120) + 8);
	}

	protected void genRoom(IChunk chunk, Function<BlockPos, Biome> posBiomeFunction, long p_227205_3_, int p_227205_5_, int p_227205_6_, int p_227205_7_, double p_227205_8_, double p_227205_10_, double p_227205_12_, float p_227205_14_, double p_227205_15_, BitSet bitSet) {
		double d0 = 1.5D + (double)(MathHelper.sin(((float)Math.PI / 2F)) * p_227205_14_);
		double d1 = d0 * p_227205_15_;
		this.carveSphere(chunk, posBiomeFunction, p_227205_3_, p_227205_5_, p_227205_6_, p_227205_7_, p_227205_8_ + 1.0D, p_227205_10_, p_227205_12_, d0, d1, bitSet);
	}

	protected void genTunnel(IChunk chunk, Function<BlockPos, Biome> posBiomeFunction, long p_227206_3_, int p_227206_5_, int p_227206_6_, int p_227206_7_, double p_227206_8_, double p_227206_10_, double p_227206_12_, float p_227206_14_, float p_227206_15_, float p_227206_16_, int p_227206_17_, int p_227206_18_, double p_227206_19_, BitSet bitSet) {
		Random random = new Random(p_227206_3_);
		int i = random.nextInt(p_227206_18_ / 2) + p_227206_18_ / 4;
		boolean flag = random.nextInt(6) == 0;
		float f = 0.0F;
		float f1 = 0.0F;

		for(int j = p_227206_17_; j < p_227206_18_; ++j) {
			double d0 = 1.5D + (double)(MathHelper.sin((float)Math.PI * (float)j / (float)p_227206_18_) * p_227206_14_);
			double d1 = d0 * p_227206_19_;
			float f2 = MathHelper.cos(p_227206_16_);
			p_227206_8_ += (double)(MathHelper.cos(p_227206_15_) * f2);
			p_227206_10_ += (double)MathHelper.sin(p_227206_16_);
			p_227206_12_ += (double)(MathHelper.sin(p_227206_15_) * f2);
			p_227206_16_ = p_227206_16_ * (flag ? 0.92F : 0.7F);
			p_227206_16_ = p_227206_16_ + f1 * 0.1F;
			p_227206_15_ += f * 0.1F;
			f1 = f1 * 0.9F;
			f = f * 0.75F;
			f1 = f1 + (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
			f = f + (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;
			if (j == i && p_227206_14_ > 1.0F) {
				this.genTunnel(chunk, posBiomeFunction, random.nextLong(), p_227206_5_, p_227206_6_, p_227206_7_, p_227206_8_, p_227206_10_, p_227206_12_, random.nextFloat() * 0.5F + 0.5F, p_227206_15_ - ((float)Math.PI / 2F), p_227206_16_ / 3.0F, j, p_227206_18_, 1.0D, bitSet);
				this.genTunnel(chunk, posBiomeFunction, random.nextLong(), p_227206_5_, p_227206_6_, p_227206_7_, p_227206_8_, p_227206_10_, p_227206_12_, random.nextFloat() * 0.5F + 0.5F, p_227206_15_ + ((float)Math.PI / 2F), p_227206_16_ / 3.0F, j, p_227206_18_, 1.0D, bitSet);
				return;
			}

			if (random.nextInt(4) != 0) {
				if (!this.canReach(p_227206_6_, p_227206_7_, p_227206_8_, p_227206_12_, j, p_227206_18_, p_227206_14_)) {
					return;
				}

				this.carveSphere(chunk, posBiomeFunction, p_227206_3_, p_227206_5_, p_227206_6_, p_227206_7_, p_227206_8_, p_227206_10_, p_227206_12_, d0, d1, bitSet);
			}
		}

	}

	protected boolean skip(double p_222708_1_, double p_222708_3_, double p_222708_5_, int p_222708_7_) {
		return p_222708_3_ <= -0.7D || p_222708_1_ * p_222708_1_ + p_222708_3_ * p_222708_3_ + p_222708_5_ * p_222708_5_ >= 1.0D;
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