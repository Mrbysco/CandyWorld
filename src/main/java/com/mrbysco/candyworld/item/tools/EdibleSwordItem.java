package com.mrbysco.candyworld.item.tools;

import com.mrbysco.candyworld.interfaces.IItemToolEdible;
import com.mrbysco.candyworld.registry.ModGroups;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class EdibleSwordItem extends SwordItem implements IItemToolEdible {

	public EdibleSwordItem(Tier tier, int attackDamageIn, float attackSpeedIn, Item.Properties properties) {
		super(tier, attackDamageIn, attackSpeedIn, properties);
	}
	///////////////////////////////////////////////////////////////////////////
	// Food implementation
	///////////////////////////////////////////////////////////////////////////

	@Override
	public int getUseDuration(ItemStack stack) {
		return 32;
	}

	@Nonnull
	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.EAT;
	}

	@Nonnull
	@Override
	@ParametersAreNonnullByDefault
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
		return IItemToolEdible.super.onItemRightClick(worldIn, playerIn, handIn);
	}

	@Nonnull
	@Override
	@ParametersAreNonnullByDefault
	public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entityLiving) {
		return IItemToolEdible.super.onItemUseFinish(stack, worldIn, entityLiving);
	}
}
