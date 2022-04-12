package com.mrbysco.candyworld.block.gummy;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BaseGummyBlock extends Block {
	public BaseGummyBlock(Properties properties) {
		super(properties);
	}

	@OnlyIn(Dist.CLIENT)
	public boolean skipRendering(BlockState state, BlockState adjacentBlockState, Direction side) {
		return adjacentBlockState.is(this) ? true : super.skipRendering(state, adjacentBlockState, side);
	}

	@Override
	public void fallOn(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
		if (entityIn.isShiftKeyDown()) {
			super.fallOn(worldIn, pos, entityIn, fallDistance * 0.5F);
		} else {
			entityIn.causeFallDamage(fallDistance, 0.0F);
		}
	}

	@Override
	public void updateEntityAfterFallOn(IBlockReader worldIn, Entity entityIn) {
		if (entityIn.isSuppressingBounce()) {
			super.updateEntityAfterFallOn(worldIn, entityIn);
		} else {
			this.bounceEntity(entityIn);
		}
	}

	@Override
	public void stepOn(World worldIn, BlockPos pos, Entity entityIn) {
		double d0 = Math.abs(entityIn.getDeltaMovement().y);
		if (d0 < 0.1D && !entityIn.isSteppingCarefully()) {
			double d1 = 0.8D;
			entityIn.setDeltaMovement(entityIn.getDeltaMovement().multiply(d1, 1.0D, d1));
		}

		super.stepOn(worldIn, pos, entityIn);
	}

	private void bounceEntity(Entity entity) {
		Vector3d vector3d = entity.getDeltaMovement();
		if (vector3d.y < 0.0D) {
			double d0 = 0.8D;
			entity.setDeltaMovement(vector3d.x, -vector3d.y * d0, vector3d.z);

			if (!(entity instanceof LivingEntity)) {
				entity.setDeltaMovement(vector3d.x, vector3d.y * d0, vector3d.z);
			}
		}
	}
}
