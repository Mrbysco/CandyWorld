package com.mrbysco.candyworld.entity.ai;

import com.mrbysco.candyworld.entity.EasterChickenEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.ai.goal.PanicGoal;

/**
 * New panic AI, used for explosion
 */
public class EasterChickenPanicGoal extends PanicGoal {

	private final EasterChickenEntity creature;

	public EasterChickenPanicGoal(final EasterChickenEntity creature, double speedIn) {
		super(creature, speedIn);
		this.creature = creature;
	}

	@Override
	public boolean canUse() {
		if (this.creature.explodeWhenDone) {
			return this.findRandomPosition();
		}
		return super.canUse();
	}

	@Override
	public boolean canContinueToUse() {
		if (!this.creature.getNavigation().isDone()) {
			return true;
		}
		if (creature.explodeWhenDone) {
			this.creature.hurt(DamageSource.GENERIC, 0.0F);
		}
		return false;
	}
}