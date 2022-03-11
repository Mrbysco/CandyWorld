package com.mrbysco.candyworld.interfaces;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface IItemToolEdible {

	default int calculateHealAmount(ItemStack stack) {
		return (int) (stack.getItem().getFoodProperties().getNutrition() * ((stack.getMaxDamage() - stack.getDamageValue()) / (float) stack.getMaxDamage()));
	}

	default InteractionResultHolder<ItemStack> onItemRightClick(Level worldIn, Player playerIn, InteractionHand handIn) {
		ItemStack stack = playerIn.getItemInHand(handIn);
		if (stack.isEdible()) {
			if (playerIn.canEat(stack.getItem().getFoodProperties().canAlwaysEat()) && playerIn.isShiftKeyDown()) {
				playerIn.startUsingItem(handIn);
				return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
			} else {
				return InteractionResultHolder.fail(stack);
			}
		} else {
			return InteractionResultHolder.pass(playerIn.getItemInHand(handIn));
		}
	}

	default ItemStack onItemUseFinish(ItemStack stack, Level worldIn, LivingEntity entityLiving) {
		return stack.isEdible() ? entityLiving.eat(worldIn, stack) : stack;
	}
}
