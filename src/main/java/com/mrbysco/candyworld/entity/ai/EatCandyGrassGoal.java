package com.mrbysco.candyworld.entity.ai;

import com.mrbysco.candyworld.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;

import java.util.EnumSet;
import java.util.function.Predicate;

public class EatCandyGrassGoal extends Goal {
	private static final Predicate<BlockState> IS_TALL_GRASS = BlockStatePredicate.forBlock(ModBlocks.COTTON_CANDY_PLANT.get());
	private final Mob grassEaterEntity;
	private final Level entityWorld;
	private int eatingGrassTimer;

	public EatCandyGrassGoal(final Mob grassEaterEntityIn) {
		this.grassEaterEntity = grassEaterEntityIn;
		this.entityWorld = grassEaterEntityIn.level;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Goal.Flag.JUMP));
	}

	@Override
	public boolean canUse() {
		if (this.grassEaterEntity.getRandom().nextInt(this.grassEaterEntity.isBaby() ? 50 : 1000) != 0) {
			return false;
		} else {
			BlockPos blockpos = this.grassEaterEntity.blockPosition();
			if (IS_TALL_GRASS.test(this.entityWorld.getBlockState(blockpos))) {
				return true;
			} else {
				return this.entityWorld.getBlockState(blockpos.below()).is(ModBlocks.CANDY_GRASS_BLOCK.get());
			}
		}
	}

	@Override
	public void start() {
		this.eatingGrassTimer = 40;
		this.entityWorld.broadcastEntityEvent(this.grassEaterEntity, (byte) 10);
		this.grassEaterEntity.getNavigation().stop();
	}

	@Override
	public void stop() {
		this.eatingGrassTimer = 0;
	}

	@Override
	public boolean canContinueToUse() {
		return this.eatingGrassTimer > 0;
	}

	/**
	 * Number of ticks since the entity started to eat grasa
	 */
	public int getEatingGrassTimer() {
		return this.eatingGrassTimer;
	}

	@Override
	public void tick() {
		this.eatingGrassTimer = Math.max(0, this.eatingGrassTimer - 1);

		if (this.eatingGrassTimer == 4) {
			BlockPos blockpos = this.grassEaterEntity.blockPosition();

			if (IS_TALL_GRASS.test(this.entityWorld.getBlockState(blockpos))) {
				if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.entityWorld, this.grassEaterEntity)) {
					this.entityWorld.destroyBlock(blockpos, false);
				}

				this.grassEaterEntity.ate();
			} else {
				BlockPos blockpos1 = blockpos.below();

				if (this.entityWorld.getBlockState(blockpos1).is(ModBlocks.CANDY_GRASS_BLOCK.get())) {
					if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.entityWorld, this.grassEaterEntity)) {
						// sound played / particles
						this.entityWorld.levelEvent(2001, blockpos1, Block.getId(ModBlocks.COTTON_CANDY_LEAVES.get().defaultBlockState()));
						// block replacement
						this.entityWorld.setBlock(blockpos1, ModBlocks.MILK_BROWNIE_BLOCK.get().defaultBlockState(), 2);
					}

					this.grassEaterEntity.ate();
				}
			}


		}
	}

}