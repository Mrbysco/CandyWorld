package com.mrbysco.candyworld.block.gummy;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
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
	public void fallOn(Level level, BlockState state, BlockPos pos, Entity entityIn, float fallDistance) {
		if (entityIn.isShiftKeyDown()) {
			super.fallOn(level, state, pos, entityIn, fallDistance);
		} else {
			entityIn.causeFallDamage(fallDistance, 0.0F, entityIn.damageSources().fall());
		}
	}

	@Override
	public void updateEntityAfterFallOn(BlockGetter worldIn, Entity entityIn) {
		if (entityIn.isSuppressingBounce()) {
			super.updateEntityAfterFallOn(worldIn, entityIn);
		} else {
			this.bounceEntity(entityIn);
		}
	}

	@Override
	public void stepOn(Level worldIn, BlockPos pos, BlockState state, Entity entityIn) {
		double d0 = Math.abs(entityIn.getDeltaMovement().y);
		if (d0 < 0.1D && !entityIn.isSteppingCarefully()) {
			double d1 = 0.8D;
			entityIn.setDeltaMovement(entityIn.getDeltaMovement().multiply(d1, 1.0D, d1));
		}

		super.stepOn(worldIn, pos, state, entityIn);
	}

	private void bounceEntity(Entity entity) {
		Vec3 vector3d = entity.getDeltaMovement();
		if (vector3d.y < 0.0D) {
			double d0 = 0.8D;
			entity.setDeltaMovement(vector3d.x, -vector3d.y * d0, vector3d.z);

			if (!(entity instanceof LivingEntity)) {
				entity.setDeltaMovement(vector3d.x, vector3d.y * d0, vector3d.z);
			}
		}
	}
}
