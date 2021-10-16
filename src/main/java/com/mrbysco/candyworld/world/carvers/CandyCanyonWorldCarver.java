package com.mrbysco.candyworld.world.carvers;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import com.mrbysco.candyworld.block.fluid.ModFluids;
import com.mrbysco.candyworld.registry.ModBlocks;
import com.mrbysco.candyworld.registry.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.levelgen.BaseStoneSource;
import net.minecraft.world.level.levelgen.SingleBaseStoneSource;
import net.minecraft.world.level.levelgen.carver.CanyonCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CarvingContext;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.apache.commons.lang3.mutable.MutableBoolean;

import javax.annotation.Nullable;
import java.util.BitSet;
import java.util.Random;
import java.util.function.Function;

public class CandyCanyonWorldCarver extends WorldCarver<CanyonCarverConfiguration> {
	public static FluidState LIQUID_CANDY = ModFluids.LIQUID_CANDY_SOURCE.get().defaultFluidState();
	public static BlockState CRYSTALLIZED_SUGAR = ModBlocks.CRYSTALLIZED_SUGAR.get().defaultBlockState();
	protected static final BaseStoneSource CRYSTALLIZED_SUGAR_SOURCE = new SingleBaseStoneSource(ModBlocks.CRYSTALLIZED_SUGAR.get().defaultBlockState());

	public CandyCanyonWorldCarver(Codec<CanyonCarverConfiguration> configCodec) {
		super(configCodec);
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

	@Override
	public boolean isStartChunk(CanyonCarverConfiguration carverConfiguration, Random random) {
		return random.nextFloat() <= carverConfiguration.probability;
	}

	@Override
	public boolean carve(CarvingContext p_159052_, CanyonCarverConfiguration p_159053_, ChunkAccess p_159054_, Function<BlockPos, Biome> p_159055_, Random p_159056_, Aquifer p_159057_, ChunkPos p_159058_, BitSet p_159059_) {
		int i = (this.getRange() * 2 - 1) * 16;
		double d0 = (double)p_159058_.getBlockX(p_159056_.nextInt(16));
		int j = p_159053_.y.sample(p_159056_, p_159052_);
		double d1 = (double)p_159058_.getBlockZ(p_159056_.nextInt(16));
		float f = p_159056_.nextFloat() * ((float)Math.PI * 2F);
		float f1 = p_159053_.verticalRotation.sample(p_159056_);
		double d2 = (double)p_159053_.yScale.sample(p_159056_);
		float f2 = p_159053_.shape.thickness.sample(p_159056_);
		int k = (int)((float)i * p_159053_.shape.distanceFactor.sample(p_159056_));
		int l = 0;
		this.doCarve(p_159052_, p_159053_, p_159054_, p_159055_, p_159056_.nextLong(), p_159057_, d0, (double)j, d1, f2, f, f1, 0, k, d2, p_159059_);
		return true;
	}

	private void doCarve(CarvingContext p_159035_, CanyonCarverConfiguration p_159036_, ChunkAccess p_159037_, Function<BlockPos, Biome> p_159038_, long p_159039_, Aquifer p_159040_, double p_159041_, double p_159042_, double p_159043_, float p_159044_, float p_159045_, float p_159046_, int p_159047_, int p_159048_, double p_159049_, BitSet p_159050_) {
		Random random = new Random(p_159039_);
		float[] afloat = this.initWidthFactors(p_159035_, p_159036_, random);
		float f = 0.0F;
		float f1 = 0.0F;

		for(int i = p_159047_; i < p_159048_; ++i) {
			double d0 = 1.5D + (double) (Mth.sin((float) i * (float) Math.PI / (float) p_159048_) * p_159044_);
			double d1 = d0 * p_159049_;
			d0 = d0 * (double) p_159036_.shape.horizontalRadiusFactor.sample(random);
			d1 = this.updateVerticalRadius(p_159036_, random, d1, (float) p_159048_, (float) i);
			float f2 = Mth.cos(p_159046_);
			float f3 = Mth.sin(p_159046_);
			p_159041_ += (double) (Mth.cos(p_159045_) * f2);
			p_159042_ += (double) f3;
			p_159043_ += (double) (Mth.sin(p_159045_) * f2);
			p_159046_ = p_159046_ * 0.7F;
			p_159046_ = p_159046_ + f1 * 0.05F;
			p_159045_ += f * 0.05F;
			f1 = f1 * 0.8F;
			f = f * 0.5F;
			f1 = f1 + (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
			f = f + (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;
			if (random.nextInt(4) != 0) {
				if (!canReach(p_159037_.getPos(), p_159041_, p_159043_, i, p_159048_, p_159044_)) {
					return;
				}

				this.carveEllipsoid(p_159035_, p_159036_, p_159037_, p_159038_, p_159039_, p_159040_, p_159041_, p_159042_, p_159043_, d0, d1, p_159050_, (p_159082_, p_159083_, p_159084_, p_159085_, p_159086_) -> {
					return this.shouldSkip(p_159082_, afloat, p_159083_, p_159084_, p_159085_, p_159086_);
				});
			}
		}
	}

	private float[] initWidthFactors(CarvingContext p_159061_, CanyonCarverConfiguration p_159062_, Random p_159063_) {
		int i = p_159061_.getGenDepth();
		float[] afloat = new float[i];
		float f = 1.0F;

		for(int j = 0; j < i; ++j) {
			if (j == 0 || p_159063_.nextInt(p_159062_.shape.widthSmoothness) == 0) {
				f = 1.0F + p_159063_.nextFloat() * p_159063_.nextFloat();
			}

			afloat[j] = f * f;
		}

		return afloat;
	}

	private double updateVerticalRadius(CanyonCarverConfiguration p_159026_, Random p_159027_, double p_159028_, float p_159029_, float p_159030_) {
		float f = 1.0F - Mth.abs(0.5F - p_159030_ / p_159029_) * 2.0F;
		float f1 = p_159026_.shape.verticalRadiusDefaultFactor + p_159026_.shape.verticalRadiusCenterFactor * f;
		return (double)f1 * p_159028_ * (double)Mth.randomBetween(p_159027_, 0.75F, 1.0F);
	}

	private boolean shouldSkip(CarvingContext p_159074_, float[] p_159075_, double p_159076_, double p_159077_, double p_159078_, int p_159079_) {
		int i = p_159079_ - p_159074_.getMinGenY();
		return (p_159076_ * p_159076_ + p_159078_ * p_159078_) * (double)p_159075_[i - 1] + p_159077_ * p_159077_ / 6.0D >= 1.0D;
	}

	@Override
	protected boolean carveBlock(CarvingContext p_159400_, CanyonCarverConfiguration p_159401_, ChunkAccess chunk, Function<BlockPos, Biome> p_159403_, BitSet p_159404_, Random p_159405_, MutableBlockPos mutable, MutableBlockPos p_159407_, Aquifer p_159408_, MutableBoolean p_159409_) {
		BlockState blockstate = chunk.getBlockState(mutable);
		BlockState blockstate1 = chunk.getBlockState(p_159407_.setWithOffset(mutable, Direction.UP));
		if (blockstate.is(Blocks.GRASS_BLOCK) || blockstate.is(Blocks.MYCELIUM) || blockstate.is(ModTags.COVERED_BROWNIE)) {
			p_159409_.setTrue();
		}

		if (!this.canReplaceBlock(blockstate, blockstate1) && !isDebugEnabled(p_159401_)) {
			return false;
		} else {
			BlockState blockstate2 = this.getCarveState(p_159400_, p_159401_, mutable, p_159408_);
			if (blockstate2 == null) {
				return false;
			} else {
				chunk.setBlockState(mutable, blockstate2, false);
				if (p_159409_.isTrue()) {
					p_159407_.setWithOffset(mutable, Direction.DOWN);
					if (chunk.getBlockState(p_159407_).is(Blocks.DIRT) || chunk.getBlockState(p_159407_).is(ModTags.BROWNIE)) {
						chunk.setBlockState(p_159407_, p_159403_.apply(mutable).getGenerationSettings().getSurfaceBuilderConfig().getTopMaterial(), false);
					}
				}

				return true;
			}
		}
	}

	@Nullable
	@Override
	public BlockState getCarveState(CarvingContext context, CanyonCarverConfiguration configuration, BlockPos pos, Aquifer aquifer) {
		if (pos.getY() <= configuration.lavaLevel.resolveY(context)) {
			return LIQUID_CANDY.createLegacyBlock();
		} else if (!configuration.aquifersEnabled) {
			return isDebugEnabled(configuration) ? getDebugState(configuration, AIR) : AIR;
		} else {
			BlockState blockstate = aquifer.computeState(CRYSTALLIZED_SUGAR_SOURCE, pos.getX(), pos.getY(), pos.getZ(), 0.0D);
			if (blockstate == CRYSTALLIZED_SUGAR) {
				return isDebugEnabled(configuration) ? configuration.debugSettings.getBarrierState() : null;
			} else {
				return isDebugEnabled(configuration) ? getDebugState(configuration, blockstate) : blockstate;
			}
		}
	}
}