package com.mrbysco.candyworld.item.teleporter;

import com.mrbysco.candyworld.config.CandyConfig;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeHooks;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class TeleporterItem extends Item {

	public TeleporterItem(Properties properties) {
		super(properties);
	}

	@Nonnull
	@Override
	public Rarity getRarity(ItemStack stack) {
		return Rarity.RARE;
	}

	@Override
	public boolean isFoil(ItemStack stack) {
		return true;
	}

	@Nonnull
	@Override
	@ParametersAreNonnullByDefault
	public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
		if (CandyConfig.COMMON.disableTeleporter.get()) {
			return stack;
		}
		if (stack.isEdible()) {
			world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), entity.getEatingSound(stack), SoundSource.NEUTRAL, 1.0F, 1.0F + (world.random.nextFloat() - world.random.nextFloat()) * 0.4F);
			entity.addEatEffect(stack, world, entity);

			if (entity instanceof Player player) {
				player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
				if (player instanceof ServerPlayer && (world.dimension() == getDimension())) {
					CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayer) player, stack);
				}
			}

			if (!(entity instanceof Player) || (world.dimension() == getDimension() && !((Player) entity).getAbilities().instabuild)) {
				stack.shrink(1);
			}

			if (!world.isClientSide && entity instanceof Player) {
				ServerPlayer player = (ServerPlayer) entity;

				if (world.dimension() == getDimension()) {
					if (!ForgeHooks.onTravelToDimension(player, Level.OVERWORLD))
						return stack;

					teleportToDimension(world, player, Level.OVERWORLD);
				} else {
					if (!ForgeHooks.onTravelToDimension(player, getDimension()))
						return stack;

					teleportToDimension(world, player, getDimension());
				}
			}
		}
		return stack;
	}

	public void teleportToDimension(Level worldIn, Player player, ResourceKey<Level> dimension) {
		if (player.isAlive() && !worldIn.isClientSide() && !CandyConfig.COMMON.disableTeleporter.get()) {
			if (!player.isPassenger() && !player.isVehicle() && player.canChangeDimensions()) {
				ServerPlayer serverPlayer = (ServerPlayer) player;
				MinecraftServer server = player.getServer();
				ServerLevel destinationWorld = server != null ? server.getLevel(dimension) : null;
				if (destinationWorld == null) {
					com.mrbysco.candyworld.CandyWorld.LOGGER.error("Destination invalid {} isn't known", dimension.location());
					return;
				}

				CustomTeleporter teleporter = new CustomTeleporter();
				serverPlayer.changeDimension(destinationWorld, teleporter);
			}
		}
	}

	public ResourceKey<Level> getDimension() {
		return ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation("candyworld", "candy_world"));
	}

	@Nonnull
	@Override
	@ParametersAreNonnullByDefault
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		player.startUsingItem(hand);
		return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemstack);
	}
}
