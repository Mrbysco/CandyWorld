package com.mrbysco.candyworld.entity;

import com.mrbysco.candyworld.enums.EnumGummy;
import com.mrbysco.candyworld.registry.ModBlocks;
import com.mrbysco.candyworld.registry.ModEntities;
import com.mrbysco.candyworld.registry.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.Random;

public class GummyMouseEntity extends Animal {

	private static final EntityDataAccessor<Byte> COLOR = SynchedEntityData.defineId(GummyMouseEntity.class, EntityDataSerializers.BYTE);

	public GummyMouseEntity(EntityType<? extends GummyMouseEntity> type, Level worldIn) {
		super(type, worldIn);
	}

	public GummyMouseEntity(Level worldIn) {
		super(ModEntities.GUMMY_MOUSE.get(), worldIn);
	}

	@Override
	@Nullable
	protected ResourceLocation getDefaultLootTable() {
		return switch (getColor()) {
			default -> ModLootTables.ENTITY_MOUSE_RED;
			case ORANGE -> ModLootTables.ENTITY_MOUSE_ORANGE;
			case YELLOW -> ModLootTables.ENTITY_MOUSE_YELLOW;
			case WHITE -> ModLootTables.ENTITY_MOUSE_WHITE;
			case GREEN -> ModLootTables.ENTITY_MOUSE_GREEN;
		};
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(COLOR, (byte) 0);
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
		spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
		BlockState state = worldIn.getBlockState(this.blockPosition().below());
		if (state.is(ModBlocks.RED_GUMMY_BLOCK.get())) {
			this.setColor(EnumGummy.RED);
		} else if (state.is(ModBlocks.ORANGE_GUMMY_BLOCK.get())) {
			this.setColor(EnumGummy.ORANGE);
		} else if (state.is(ModBlocks.YELLOW_GUMMY_BLOCK.get())) {
			this.setColor(EnumGummy.YELLOW);
		} else if (state.is(ModBlocks.WHITE_GUMMY_BLOCK.get())) {
			this.setColor(EnumGummy.WHITE);
		} else if (state.is(ModBlocks.GREEN_GUMMY_BLOCK.get())) {
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
	public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob mate) {
		return null;
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return false;
	}

	@Override
	public void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 1.2D));
		this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Player.class, 1.2F, 0.85F, 1.33F));
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Ocelot.class, 8.0F, 0.85F, 1.33F));
		this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.6D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
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

	public static AttributeSupplier.Builder registerAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 2.0D).add(Attributes.MOVEMENT_SPEED, 0.3D);
	}

	@Override
	protected MovementEmission getMovementEmission() {
		return MovementEmission.NONE;
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putByte("Color", (byte) this.getColor().getMetadata());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.setColor(EnumGummy.byMetadata(compound.getByte("Color")));
	}

	public static boolean canGummySpawn(EntityType<? extends Animal> entityType, LevelAccessor world, MobSpawnType reason, BlockPos pos, Random random) {
		return world.getBlockState(pos.below()).is(ModTags.GUMMY) && world.getRawBrightness(pos, 0) > 8;
	}
}
