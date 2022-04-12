package com.mrbysco.candyworld.item.tools;

import com.mrbysco.candyworld.interfaces.IItemToolEdible;
import com.mrbysco.candyworld.registry.ModGroups;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class EdibleShovelItem extends ShovelItem implements IItemToolEdible {

	public EdibleShovelItem(Tier tier, float attackDamageIn, float attackSpeedIn, Properties builder) {
		super(tier, attackDamageIn, attackSpeedIn, builder.tab(ModGroups.TOOLS));
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Player player = context.getPlayer();
		ItemStack stack = context.getItemInHand();
		if (player.isShiftKeyDown() && player.canEat(stack.getItem().getFoodProperties().canAlwaysEat())) {
			return InteractionResult.PASS;
		}
		return super.useOn(context);
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
