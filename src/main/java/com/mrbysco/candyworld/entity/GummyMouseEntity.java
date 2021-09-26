package com.mrbysco.candyworld.entity;

import com.mrbysco.candyworld.enums.EnumGummy;
import com.mrbysco.candyworld.registry.ModBlocks;
import com.mrbysco.candyworld.registry.ModEntities;
import com.mrbysco.candyworld.registry.ModTags;
import net.minecraft.block.BlockState;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

public class GummyMouseEntity extends AnimalEntity {

    private static final DataParameter<Byte> COLOR = EntityDataManager.defineId(GummyMouseEntity.class, DataSerializers.BYTE);

    public GummyMouseEntity(EntityType<? extends GummyMouseEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public GummyMouseEntity(World worldIn) {
        super(ModEntities.GUMMY_MOUSE.get(), worldIn);
    }

    @Override
    @Nullable
    protected ResourceLocation getDefaultLootTable() {
        switch (getColor()) {
            default:
                return ModLootTables.ENTITY_MOUSE_RED;
            case ORANGE:
                return ModLootTables.ENTITY_MOUSE_ORANGE;
            case YELLOW:
                return ModLootTables.ENTITY_MOUSE_YELLOW;
            case WHITE:
                return ModLootTables.ENTITY_MOUSE_WHITE;
            case GREEN:
                return ModLootTables.ENTITY_MOUSE_GREEN;
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(COLOR, (byte) 0);
    }

    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        BlockState state = worldIn.getBlockState(this.blockPosition().below());
        if(state.is(ModBlocks.RED_GUMMY_BLOCK.get())) {
            this.setColor(EnumGummy.RED);
        } else if(state.is(ModBlocks.ORANGE_GUMMY_BLOCK.get())) {
            this.setColor(EnumGummy.ORANGE);
        } else if(state.is(ModBlocks.YELLOW_GUMMY_BLOCK.get())) {
            this.setColor(EnumGummy.YELLOW);
        } else if(state.is(ModBlocks.WHITE_GUMMY_BLOCK.get())) {
            this.setColor(EnumGummy.WHITE);
        } else if(state.is(ModBlocks.GREEN_GUMMY_BLOCK.get())) {
            this.setColor(EnumGummy.GREEN);
        } else {
            this.setColor(EnumGummy.random(this.random));
        }
        return spawnDataIn;
    }

    public EnumGummy getColor() {
        return EnumGummy.byMetadata(this.entityData.get(COLOR));
    }

    public void setColor(EnumGummy enumgummy) {
        this.entityData.set(COLOR, (byte) enumgummy.getMetadata());
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity mate) {
        return null;
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return false;
    }

    @Override
    public void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.2D));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, PlayerEntity.class, 1.2F, 0.85F, 1.33F));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, OcelotEntity.class, 8.0F, 0.85F, 1.33F));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.6D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
    }

    @Nullable
    @Override
    public SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    public SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.RABBIT_HURT;
    }

    @Override
    public SoundEvent getDeathSound() {
        return SoundEvents.RABBIT_DEATH;
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 2.0D).add(Attributes.MOVEMENT_SPEED, (double)0.3D);
    }

    @Override
    protected boolean isMovementNoisy() {
        return false;
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putByte("Color", (byte) this.getColor().getMetadata());
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        this.setColor(EnumGummy.byMetadata(compound.getByte("Color")));
    }

    public static boolean canGummySpawn(EntityType<? extends AnimalEntity> entityType, IWorld world, SpawnReason reason, BlockPos pos, Random random) {
        return world.getBlockState(pos.below()).is(ModTags.GUMMY) && world.getRawBrightness(pos, 0) > 8;
    }
}
