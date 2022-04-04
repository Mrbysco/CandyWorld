//package com.mrbysco.candyworld.world.carvers;
//
//import com.google.common.collect.ImmutableSet;
//import com.mojang.serialization.Codec;
//import com.mrbysco.candyworld.block.fluid.ModFluids;
//import com.mrbysco.candyworld.registry.ModBlocks;
//import com.mrbysco.candyworld.registry.ModTags;
//import net.minecraft.core.BlockPos;
//import net.minecraft.core.Direction;
//import net.minecraft.core.SectionPos;
//import net.minecraft.util.Mth;
//import net.minecraft.world.level.ChunkPos;
//import net.minecraft.world.level.biome.Biome;
//import net.minecraft.world.level.block.Blocks;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.world.level.chunk.CarvingMask;
//import net.minecraft.world.level.chunk.ChunkAccess;
//import net.minecraft.world.level.levelgen.Aquifer;
//import net.minecraft.world.level.levelgen.carver.CarvingContext;
//import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
//import net.minecraft.world.level.levelgen.carver.WorldCarver;
//import net.minecraft.world.level.material.FluidState;
//import net.minecraft.world.level.material.Fluids;
//import org.apache.commons.lang3.mutable.MutableBoolean;
//
//import javax.annotation.Nullable;
//import java.util.Random;
//import java.util.function.Function;
//
//public class CandyCavesWorldCarver extends WorldCarver<CaveCarverConfiguration> {
//	public static FluidState LIQUID_CANDY = ModFluids.LIQUID_CANDY_SOURCE.get().defaultFluidState();
//	public static BlockState CRYSTALLIZED_SUGAR = ModBlocks.CRYSTALLIZED_SUGAR.get().defaultBlockState();
////	protected static final BaseStoneSource CRYSTALLIZED_SUGAR_SOURCE = new SingleBaseStoneSource(ModBlocks.CRYSTALLIZED_SUGAR.get().defaultBlockState());
//
//	public CandyCavesWorldCarver(Codec<CaveCarverConfiguration> configCodec) {
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
//	public boolean isStartChunk(CaveCarverConfiguration configuration, Random random) {
//		return random.nextFloat() <= configuration.probability;
//	}
//
//	public boolean carve(CarvingContext context, CaveCarverConfiguration carverConfiguration, ChunkAccess chunkAccess, Function<BlockPos, Biome> p_159257_, Random random, Aquifer aquifer, ChunkPos chunkPos, CarvingMask carvingMask) {
//		int i = SectionPos.sectionToBlockCoord(this.getRange() * 2 - 1);
//		int j = random.nextInt(random.nextInt(random.nextInt(this.getCaveBound()) + 1) + 1);
//
//		for (int k = 0; k < j; ++k) {
//			double d0 = chunkPos.getBlockX(random.nextInt(16));
//			double d1 = carverConfiguration.y.sample(random, context);
//			double d2 = chunkPos.getBlockZ(random.nextInt(16));
//			double d3 = carverConfiguration.horizontalRadiusMultiplier.sample(random);
//			double d4 = carverConfiguration.verticalRadiusMultiplier.sample(random);
//			double d5 = carverConfiguration.floorLevel.sample(random);
//			WorldCarver.CarveSkipChecker worldcarver$carveskipchecker = (p_159202_, p_159203_, p_159204_, p_159205_, p_159206_) -> {
//				return shouldSkip(p_159203_, p_159204_, p_159205_, d5);
//			};
//			int l = 1;
//			if (random.nextInt(4) == 0) {
//				double d6 = carverConfiguration.yScale.sample(random);
//				float f1 = 1.0F + random.nextFloat() * 6.0F;
//				this.createRoom(context, carverConfiguration, chunkAccess, p_159257_, aquifer, d0, d1, d2, f1, d6, carvingMask, worldcarver$carveskipchecker);
//				l += random.nextInt(4);
//			}
//
//			for (int k1 = 0; k1 < l; ++k1) {
//				float f = random.nextFloat() * ((float) Math.PI * 2F);
//				float f3 = (random.nextFloat() - 0.5F) / 4.0F;
//				float f2 = this.getThickness(random);
//				int i1 = i - random.nextInt(i / 4);
//				int j1 = 0;
//				this.createTunnel(context, carverConfiguration, chunkAccess, p_159257_, random.nextLong(), aquifer, d0, d1, d2, d3, d4, f2, f, f3, j1, i1, this.getYScale(), carvingMask, worldcarver$carveskipchecker);
//			}
//		}
//
//		return true;
//	}
//
//	protected int getCaveBound() {
//		return 15;
//	}
//
//	protected float getThickness(Random p_64834_) {
//		float f = p_64834_.nextFloat() * 2.0F + p_64834_.nextFloat();
//		if (p_64834_.nextInt(10) == 0) {
//			f *= p_64834_.nextFloat() * p_64834_.nextFloat() * 3.0F + 1.0F;
//		}
//
//		return f;
//	}
//
//	protected double getYScale() {
//		return 1.0D;
//	}
//
//	protected void createRoom(CarvingContext carvingContext, CaveCarverConfiguration carverConfiguration, ChunkAccess chunkAccess, Function<BlockPos, Biome> p_190694_, Aquifer aquifer, double p_190696_, double p_190697_, double p_190698_, float p_190699_, double p_190700_, CarvingMask carvingMask, WorldCarver.CarveSkipChecker carveSkipChecker) {
//		double d0 = 1.5D + (double) (Mth.sin(((float) Math.PI / 2F)) * p_190699_);
//		double d1 = d0 * p_190700_;
//		this.carveEllipsoid(carvingContext, carverConfiguration, chunkAccess, p_190694_, aquifer, p_190696_ + 1.0D, p_190697_, p_190698_, d0, d1, carvingMask, carveSkipChecker);
//	}
//
//	protected void createTunnel(CarvingContext context, CaveCarverConfiguration carverConfiguration, ChunkAccess chunkAccess, Function<BlockPos, Biome> p_190674_, long p_190675_, Aquifer aquifer, double p_190677_, double p_190678_, double p_190679_, double p_190680_, double p_190681_, float p_190682_, float p_190683_, float p_190684_, int p_190685_, int p_190686_, double p_190687_, CarvingMask carvingMask, WorldCarver.CarveSkipChecker carveSkipChecker) {
//		Random random = new Random(p_190675_);
//		int i = random.nextInt(p_190686_ / 2) + p_190686_ / 4;
//		boolean flag = random.nextInt(6) == 0;
//		float f = 0.0F;
//		float f1 = 0.0F;
//
//		for (int j = p_190685_; j < p_190686_; ++j) {
//			double d0 = 1.5D + (double) (Mth.sin((float) Math.PI * (float) j / (float) p_190686_) * p_190682_);
//			double d1 = d0 * p_190687_;
//			float f2 = Mth.cos(p_190684_);
//			p_190677_ += (double) (Mth.cos(p_190683_) * f2);
//			p_190678_ += (double) Mth.sin(p_190684_);
//			p_190679_ += (double) (Mth.sin(p_190683_) * f2);
//			p_190684_ *= flag ? 0.92F : 0.7F;
//			p_190684_ += f1 * 0.1F;
//			p_190683_ += f * 0.1F;
//			f1 *= 0.9F;
//			f *= 0.75F;
//			f1 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
//			f += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;
//			if (j == i && p_190682_ > 1.0F) {
//				this.createTunnel(context, carverConfiguration, chunkAccess, p_190674_, random.nextLong(), aquifer, p_190677_, p_190678_, p_190679_, p_190680_, p_190681_, random.nextFloat() * 0.5F + 0.5F, p_190683_ - ((float) Math.PI / 2F), p_190684_ / 3.0F, j, p_190686_, 1.0D, carvingMask, carveSkipChecker);
//				this.createTunnel(context, carverConfiguration, chunkAccess, p_190674_, random.nextLong(), aquifer, p_190677_, p_190678_, p_190679_, p_190680_, p_190681_, random.nextFloat() * 0.5F + 0.5F, p_190683_ + ((float) Math.PI / 2F), p_190684_ / 3.0F, j, p_190686_, 1.0D, carvingMask, carveSkipChecker);
//				return;
//			}
//
//			if (random.nextInt(4) != 0) {
//				if (!canReach(chunkAccess.getPos(), p_190677_, p_190679_, j, p_190686_, p_190682_)) {
//					return;
//				}
//
//				this.carveEllipsoid(context, carverConfiguration, chunkAccess, p_190674_, aquifer, p_190677_, p_190678_, p_190679_, d0 * p_190680_, d1 * p_190681_, carvingMask, carveSkipChecker);
//			}
//		}
//	}
//
//	private static boolean shouldSkip(double p_159196_, double p_159197_, double p_159198_, double p_159199_) {
//		if (p_159197_ <= p_159199_) {
//			return true;
//		} else {
//			return p_159196_ * p_159196_ + p_159197_ * p_159197_ + p_159198_ * p_159198_ >= 1.0D;
//		}
//	}
//
//	protected boolean carveBlock(CarvingContext carvingContext, CaveCarverConfiguration carverConfiguration, ChunkAccess chunkAccess, Function<BlockPos, Biome> p_159403_, CarvingMask carvingMask, BlockPos.MutableBlockPos p_190749_, BlockPos.MutableBlockPos p_190750_, Aquifer p_190751_, MutableBoolean mutableBoolean) {
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
//	public BlockState getCarveState(CarvingContext context, CaveCarverConfiguration configuration, BlockPos pos, Aquifer aquifer) {
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