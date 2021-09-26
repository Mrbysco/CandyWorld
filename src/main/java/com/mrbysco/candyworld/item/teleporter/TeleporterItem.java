package com.mrbysco.candyworld.item.teleporter;

import com.mrbysco.candyworld.config.CandyConfig;
import com.mrbysco.candyworld.registry.ModDimension;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
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
    public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity entity) {
        if (stack.isEdible()) {
            world.playSound((PlayerEntity)null, entity.getX(), entity.getY(), entity.getZ(), entity.getEatingSound(stack), SoundCategory.NEUTRAL, 1.0F, 1.0F + (world.random.nextFloat() - world.random.nextFloat()) * 0.4F);
            entity.addEatEffect(stack, world, entity);

            if (entity instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) entity;
                player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
                if (player instanceof ServerPlayerEntity && (CandyConfig.COMMON.disableTeleporter.get() || world.dimension() == ModDimension.candy_world)) {
                    CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayerEntity) player, stack);
                }
            }

            if (!(entity instanceof PlayerEntity) || (world.dimension() == ModDimension.candy_world && !((PlayerEntity) entity).abilities.instabuild) || CandyConfig.COMMON.disableTeleporter.get()) {
                stack.shrink(1);
            }
        }
        if (!world.isClientSide && entity instanceof PlayerEntity && !CandyConfig.COMMON.disableTeleporter.get()) {
            ServerPlayerEntity player = (ServerPlayerEntity) entity;

            if (world.dimension() == ModDimension.candy_world) {
                if (!ForgeHooks.onTravelToDimension(player, World.OVERWORLD))
                    return stack;

                teleportToDimension(world, player, World.OVERWORLD);
            } else {
                if (!ForgeHooks.onTravelToDimension(player, ModDimension.candy_world))
                    return stack;

                teleportToDimension(world, player, ModDimension.candy_world);
            }
        }
        return stack;
    }

    public void teleportToDimension(World worldIn, PlayerEntity player, RegistryKey<World> dimension) {
        if (player.isAlive() && !worldIn.isClientSide()) {
            if (!player.isPassenger() && !player.isVehicle() && player.canChangeDimensions()) {
                ServerPlayerEntity playerMP = (ServerPlayerEntity) player;
                MinecraftServer server = player.getServer();
                ServerWorld destinationWorld = server != null ? server.getLevel(dimension) : null;
                if(destinationWorld == null) {
                    com.mrbysco.candyworld.CandyWorld.LOGGER.error("Destination invalid {} isn't known", dimension.location());
                    return;
                }

                CustomTeleporter teleporter = new CustomTeleporter();
                playerMP.changeDimension(destinationWorld, teleporter);
            }
        }
    }

    @Nonnull
    @Override
    @ParametersAreNonnullByDefault
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
    }
}
