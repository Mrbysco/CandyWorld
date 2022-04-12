package com.mrbysco.candyworld.interfaces;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public interface IItemToolEdible {

	default int calculateHealAmount(ItemStack stack) {
		return (int) (stack.getItem().getFoodProperties().getNutrition() * ((stack.getMaxDamage() - stack.getDamageValue()) / (float) stack.getMaxDamage()));
	}

	default ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack stack = playerIn.getItemInHand(handIn);
		if (stack.isEdible()) {
			if (playerIn.canEat(stack.getItem().getFoodProperties().canAlwaysEat()) && playerIn.isShiftKeyDown()) {
				playerIn.startUsingItem(handIn);
				return new ActionResult<>(ActionResultType.SUCCESS, stack);
			} else {
				return ActionResult.fail(stack);
			}
		} else {
			return ActionResult.pass(playerIn.getItemInHand(handIn));
		}
	}

	default ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
		return stack.isEdible() ? entityLiving.eat(worldIn, stack) : stack;
	}
}
