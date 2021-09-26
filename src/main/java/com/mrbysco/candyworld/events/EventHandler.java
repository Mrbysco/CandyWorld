package com.mrbysco.candyworld.events;

import com.mrbysco.candyworld.config.CandyConfig;
import com.mrbysco.candyworld.registry.ModBlocks;
import com.mrbysco.candyworld.registry.ModDimension;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = com.mrbysco.candyworld.CandyWorld.MOD_ID)
public class EventHandler {

    @SubscribeEvent
    public static void onCheckSpawn(LivingSpawnEvent.CheckSpawn event) {
        LivingEntity livingEntity = event.getEntityLiving();
        // prevent water mobs such as squid from spawning in chocolate
        BlockPos pos = new BlockPos(event.getX(), event.getY(), event.getZ());
        if(event.getSpawnReason() == SpawnReason.NATURAL || event.getSpawnReason() == SpawnReason.CHUNK_GENERATION || event.getSpawnReason() == SpawnReason.STRUCTURE) {
            if (livingEntity instanceof WaterMobEntity && event.getWorld().getBlockState(pos).getBlock() == ModBlocks.LIQUID_CHOCOLATE_BLOCK.get()) {
                event.setResult(Result.DENY);
            }
            if(CandyConfig.COMMON.preventModdedMobSpawn.get() && livingEntity.level.dimension().location().equals(ModDimension.candy_world.location())) {
                if(livingEntity.getType().getRegistryName().getNamespace() != com.mrbysco.candyworld.CandyWorld.MOD_ID) {
                    event.setResult(Result.DENY);
                }
            }
        }
    }
}
