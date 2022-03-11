//package com.mrbysco.candyworld.world.feature;
//
//import com.mojang.serialization.Codec;
//import net.minecraft.core.BlockPos;
//import net.minecraft.tags.FluidTags;
//import net.minecraft.world.level.WorldGenLevel;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.world.level.chunk.ChunkGenerator;
//import net.minecraft.world.level.levelgen.Heightmap;
//import net.minecraft.world.level.levelgen.feature.Feature;
//import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
//import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
//
//import java.util.Random;
//
//public class CandyCaneFeature extends Feature<RandomPatchConfiguration> {
//	public CandyCaneFeature(Codec<RandomPatchConfiguration> configCodec) {
//		super(configCodec);
//	}
//
//	public boolean place(FeaturePlaceContext<RandomPatchConfiguration> placeContext) {
////		RandomPatchConfiguration clusterFeatureConfig = placeContext.config();
////		WorldGenLevel reader = placeContext.level();
////		Random random = placeContext.random();
////		BlockPos pos = placeContext.origin();
////		ChunkGenerator generator = placeContext.chunkGenerator();
////		BlockState blockstate = clusterFeatureConfig.stateProvider.getState(random, pos);
////		BlockPos blockpos;
////		if (clusterFeatureConfig.project) {
////			blockpos = reader.getHeightmapPos(Heightmap.Types.WORLD_SURFACE_WG, pos);
////		} else {
////			blockpos = pos;
////		}
////
////		int i = 0;
////
////		BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();
////		for(int j = 0; j < clusterFeatureConfig.tries(); ++j) {
////			int yOffset = 0;
////			if(blockpos.getY() > generator.getSeaLevel() - 2) {
////				BlockPos newPos = new BlockPos(blockpos);
////				for(int offset = 0; offset < blockpos.getY(); offset++) {
////					newPos = newPos.below();
////					if(blockstate.canSurvive(reader, newPos) && newPos.getY() < generator.getSeaLevel() - 2) {
////						yOffset = offset;
////						continue;
////					}
////				}
////				if(yOffset == 0) {
////					return false;
////				}
////			}
////
////			blockpos$mutable.setWithOffset(blockpos, random.nextInt(clusterFeatureConfig.xspread + 1) - random.nextInt(clusterFeatureConfig.xspread + 1), -yOffset, random.nextInt(clusterFeatureConfig.zspread + 1) - random.nextInt(clusterFeatureConfig.zspread + 1));
////			BlockPos blockpos1 = blockpos$mutable.below();
////			BlockState blockstate1 = reader.getBlockState(blockpos1);
////			if ((reader.isEmptyBlock(blockpos$mutable) || clusterFeatureConfig.canReplace && reader.getBlockState(blockpos$mutable).getMaterial().isReplaceable()) && blockstate.canSurvive(reader, blockpos$mutable) && (clusterFeatureConfig.whitelist.isEmpty() || clusterFeatureConfig.whitelist.contains(blockstate1.getBlock())) && !clusterFeatureConfig.blacklist.contains(blockstate1) && (!clusterFeatureConfig.needWater || reader.getFluidState(blockpos1.west()).is(FluidTags.WATER) || reader.getFluidState(blockpos1.east()).is(FluidTags.WATER) || reader.getFluidState(blockpos1.north()).is(FluidTags.WATER) || reader.getFluidState(blockpos1.south()).is(FluidTags.WATER))) {
////				clusterFeatureConfig.blockPlacer.place(reader, blockpos$mutable, blockstate, random);
////				++i;
////			}
////		}
////
////		return i > 0;
//		return false;
//	}
//}