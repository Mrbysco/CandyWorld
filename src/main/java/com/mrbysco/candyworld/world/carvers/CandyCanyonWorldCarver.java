//package com.mrbysco.candyworld.world.carvers;
//
//import com.google.common.collect.ImmutableSet;
//import com.mojang.serialization.Codec;
//import com.mrbysco.candyworld.block.fluid.ModFluids;
//import com.mrbysco.candyworld.registry.ModBlocks;
//import com.mrbysco.candyworld.registry.ModTags;
//import net.minecraft.core.BlockPos;
//import net.minecraft.core.Direction;
//import net.minecraft.util.Mth;
//import net.minecraft.world.level.ChunkPos;
//import net.minecraft.world.level.biome.Biome;
//import net.minecraft.world.level.block.Blocks;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.world.level.chunk.CarvingMask;
//import net.minecraft.world.level.chunk.ChunkAccess;
//import net.minecraft.world.level.levelgen.Aquifer;
//import net.minecraft.world.level.levelgen.carver.CanyonCarverConfiguration;
//import net.minecraft.world.level.levelgen.carver.CarvingContext;
//import net.minecraft.world.level.levelgen.carver.WorldCarver;
//import net.minecraft.world.level.material.FluidState;
//import net.minecraft.world.level.material.Fluids;
//import org.apache.commons.lang3.mutable.MutableBoolean;
//
//import javax.annotation.Nullable;
//import java.util.Random;
//import java.util.function.Function;
//
//public class CandyCanyonWorldCarver extends WorldCarver<CanyonCarverConfiguration> {
//	public static FluidState LIQUID_CANDY = ModFluids.LIQUID_CANDY_SOURCE.get().defaultFluidState();
//	public static BlockState CRYSTALLIZED_SUGAR = ModBlocks.CRYSTALLIZED_SUGAR.get().defaultBlockState();
////	protected static final BaseStoneSource CRYSTALLIZED_SUGAR_SOURCE = new SingleBaseStoneSource(ModBlocks.CRYSTALLIZED_SUGAR.get().defaultBlockState());
//
//	public CandyCanyonWorldCarver(Codec<CanyonCarverConfiguration> configCodec) {
//		super(configCodec);
//		this.replaceableBlocks = ImmutableSet.of(Blocks.STONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL,
//				Blocks.GRASS_BLOCK, Blocks.TERRACOTTA, Blocks.WHITE_TERRACOTTA, Blocks.ORANGE_TERRACOTTA, Blocks.MAGENTA_TERRACOTTA, Blocks.LIGHT_BLUE_TERRACOTTA,
//				Blocks.YELLOW_TERRACOTTA, Blocks.LIME_TERRACOTTA, Blocks.PINK_TERRACOTTA, Blocks.GRAY_TERRACOTTA, Blocks.LIGHT_GRAY_TERRACOTTA, Blocks.CYAN_TERRACOTTA,
//				Blocks.PURPLE_TERRACOTTA, Blocks.BLUE_TERRACOTTA, Blocks.BROWN_TERRACOTTA, Blocks.GREEN_TERRACOTTA, Blocks.RED_TERRACOTTA, Blocks.BLACK_TERRACOTTA,
//				Blocks.SANDSTONE, Blocks.RED_SANDSTONE, Blocks.MYCELIUM, Blocks.SNOW, Blocks.PACKED_ICE, ModBlocks.CRYSTALLIZED_SUGAR.get(), ModBlocks.SUGAR_SAND.get(),
//				ModBlocks.RED_GUMMY_BLOCK.get(), ModBlocks.ORANGE_GUMMY_BLOCK.get(), ModBlocks.YELLOW_GUMMY_BLOCK.get(), ModBlocks.WHITE_GUMMY_BLOCK.get(),
//				ModBlocks.GREEN_GUMMY_BLOCK.get(), ModBlocks.RED_HARDENED_GUMMY_BLOCK.get(), ModBlocks.ORANGE_HARDENED_GUMMY_BLOCK.get(), ModBlocks.YELLOW_HARDENED_GUMMY_BLOCK.get(),
//				ModBlocks.WHITE_HARDENED_GUMMY_BLOCK.get(), ModBlocks.GREEN_HARDENED_GUMMY_BLOCK.get(), ModBlocks.CANDY_GRASS_BLOCK.get(), ModBlocks.WHITE_BROWNIE_BLOCK.get(),
//				ModBlocks.CHOCOLATE_COVERED_WHITE_BROWNIE.get(), ModBlocks.MILK_BROWNIE_BLOCK.get(), ModBlocks.DARK_CANDY_GRASS.get(), ModBlocks.DARK_BROWNIE_BLOCK.get());
//		this.liquids = ImmutableSet.of(Fluids.WATER, ModFluids.LIQUID_CANDY_SOURCE.get(), ModFluids.LIQUID_CHOCOLATE_SOURCE.get());
//	}
//
//	@Override
//	protected boolean canReplaceBlock(BlockState state) {
//		return this.replaceableBlocks.contains(state.getBlock());
//	}
//
//	@Override
//	public boolean isStartChunk(CanyonCarverConfiguration carverConfiguration, Random random) {
//		return random.nextFloat() <= carverConfiguration.probability;
//	}
//
//	@Override
//	public boolean carve(CarvingContext carvingContext, CanyonCarverConfiguration carverConfiguration, ChunkAccess chunkAccess, Function<BlockPos, Biome> p_159055_, Random random, Aquifer aquifer, ChunkPos chunkPos, CarvingMask carvingMask) {
//		int i = (this.getRange() * 2 - 1) * 16;
//		double d0 = chunkPos.getBlockX(random.nextInt(16));
//		int j = carverConfiguration.y.sample(random, carvingContext);
//		double d1 = chunkPos.getBlockZ(random.nextInt(16));
//		float f = random.nextFloat() * ((float) Math.PI * 2F);
//		float f1 = carverConfiguration.verticalRotation.sample(random);
//		double d2 = carverConfiguration.yScale.sample(random);
//		float f2 = carverConfiguration.shape.thickness.sample(random);
//		int k = (int) ((float) i * carverConfiguration.shape.distanceFactor.sample(random));
//		int l = 0;
//		this.doCarve(carvingContext, carverConfiguration, chunkAccess, p_159055_, random.nextLong(), aquifer, d0, j, d1, f2, f, f1, l, k, d2, carvingMask);
//		return true;
//	}
//
//	private void doCarve(CarvingContext carvingContext, CanyonCarverConfiguration carverConfiguration, ChunkAccess chunkAccess, Function<BlockPos, Biome> p_159038_, long p_159039_, Aquifer aquifer, double p_159041_, double p_159042_, double p_159043_, float p_159044_, float p_159045_, float p_159046_, int p_159047_, int p_159048_, double p_159049_, CarvingMask carvingMask) {
//		Random random = new Random(p_159039_);
//		float[] afloat = this.initWidthFactors(carvingContext, carverConfiguration, random);
//		float f = 0.0F;
//		float f1 = 0.0F;
//
//		for (int i = p_159047_; i < p_159048_; ++i) {
//			double d0 = 1.5D + (double) (Mth.sin((float) i * (float) Math.PI / (float) p_159048_) * p_159044_);
//			double d1 = d0 * p_159049_;
//			d0 = d0 * (double) carverConfiguration.shape.horizontalRadiusFactor.sample(random);
//			d1 = this.updateVerticalRadius(carverConfiguration, random, d1, (float) p_159048_, (float) i);
//			float f2 = Mth.cos(p_159046_);
//			float f3 = Mth.sin(p_159046_);
//			p_159041_ += Mth.cos(p_159045_) * f2;
//			p_159042_ += f3;
//			p_159043_ += Mth.sin(p_159045_) * f2;
//			p_159046_ = p_159046_ * 0.7F;
//			p_159046_ = p_159046_ + f1 * 0.05F;
//			p_159045_ += f * 0.05F;
//			f1 = f1 * 0.8F;
//			f = f * 0.5F;
//			f1 = f1 + (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
//			f = f + (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;
//			if (random.nextInt(4) != 0) {
//				if (!canReach(chunkAccess.getPos(), p_159041_, p_159043_, i, p_159048_, p_159044_)) {
//					return;
//				}
//
//				this.carveEllipsoid(carvingContext, carverConfiguration, chunkAccess, p_159038_, aquifer, p_159041_, p_159042_, p_159043_, d0, d1, carvingMask, (p_159082_, p_159083_, p_159084_, p_159085_, p_159086_) -> {
//					return this.shouldSkip(p_159082_, afloat, p_159083_, p_159084_, p_159085_, p_159086_);
//				});
//			}
//		}
//	}
//
//	private float[] initWidthFactors(CarvingContext carvingContext, CanyonCarverConfiguration carverConfiguration, Random random) {
//		int i = carvingContext.getGenDepth();
//		float[] afloat = new float[i];
//		float f = 1.0F;
//
//		for (int j = 0; j < i; ++j) {
//			if (j == 0 || random.nextInt(carverConfiguration.shape.widthSmoothness) == 0) {
//				f = 1.0F + random.nextFloat() * random.nextFloat();
//			}
//
//			afloat[j] = f * f;
//		}
//
//		return afloat;
//	}
//
//	private double updateVerticalRadius(CanyonCarverConfiguration carverConfiguration, Random random, double p_159028_, float p_159029_, float p_159030_) {
//		float f = 1.0F - Mth.abs(0.5F - p_159030_ / p_159029_) * 2.0F;
//		float f1 = carverConfiguration.shape.verticalRadiusDefaultFactor + carverConfiguration.shape.verticalRadiusCenterFactor * f;
//		return (double) f1 * p_159028_ * (double) Mth.randomBetween(random, 0.75F, 1.0F);
//	}
//
//	private boolean shouldSkip(CarvingContext p_159074_, float[] p_159075_, double p_159076_, double p_159077_, double p_159078_, int p_159079_) {
//		int i = p_159079_ - p_159074_.getMinGenY();
//		return (p_159076_ * p_159076_ + p_159078_ * p_159078_) * (double) p_159075_[i - 1] + p_159077_ * p_159077_ / 6.0D >= 1.0D;
//	}
//
//	@Override
//	protected boolean carveBlock(CarvingContext carvingContext, CanyonCarverConfiguration carverConfiguration, ChunkAccess chunkAccess, Function<BlockPos, Biome> p_159403_, CarvingMask carvingMask, BlockPos.MutableBlockPos p_190749_, BlockPos.MutableBlockPos p_190750_, Aquifer p_190751_, MutableBoolean mutableBoolean) {
//		BlockState blockstate = chunkAccess.getBlockState(p_190749_);
//		if (blockstate.is(Blocks.GRASS_BLOCK) || blockstate.is(Blocks.MYCELIUM) || blockstate.is(ModTags.COVERED_BROWNIE)) {
//			mutableBoolean.setTrue();
//		}
//
//		if (!this.canReplaceBlock(blockstate) && !isDebugEnabled(carverConfiguration)) {
//			return false;
//		} else {
//			BlockState blockstate1 = this.getCarveState(carvingContext, carverConfiguration, p_190749_, p_190751_);
//			if (blockstate1 == null) {
//				return false;
//			} else {
//				chunkAccess.setBlockState(p_190749_, blockstate1, false);
//				if (p_190751_.shouldScheduleFluidUpdate() && !blockstate1.getFluidState().isEmpty()) {
//					chunkAccess.markPosForPostprocessing(p_190749_);
//				}
//
//				if (mutableBoolean.isTrue()) {
//					p_190750_.setWithOffset(p_190749_, Direction.DOWN);
//					if (chunkAccess.getBlockState(p_190750_).is(Blocks.DIRT) || chunkAccess.getBlockState(p_190750_).is(ModTags.BROWNIE)) {
//						carvingContext.topMaterial(p_159403_, chunkAccess, p_190750_, !blockstate1.getFluidState().isEmpty()).ifPresent((state) -> {
//							chunkAccess.setBlockState(p_190750_, state, false);
//							if (!state.getFluidState().isEmpty()) {
//								chunkAccess.markPosForPostprocessing(p_190750_);
//							}
//						});
//					}
//				}
//
//				return true;
//			}
//		}
//	}
//
//	@Nullable
//	@Override
//	public BlockState getCarveState(CarvingContext context, CanyonCarverConfiguration configuration, BlockPos pos, Aquifer aquifer) {
//		if (pos.getY() <= configuration.lavaLevel.resolveY(context)) {
//			return LIQUID_CANDY.createLegacyBlock();
//		} else {
//			BlockState blockstate = aquifer.computeSubstance(pos.getX(), pos.getY(), pos.getZ(), 0.0D, 0.0D);
//			if (blockstate == null) {
//				return isDebugEnabled(configuration) ? configuration.debugSettings.getBarrierState() : null;
//			} else {
//				return isDebugEnabled(configuration) ? getDebugState(configuration, blockstate) : blockstate;
//			}
//		}
//	}
//}