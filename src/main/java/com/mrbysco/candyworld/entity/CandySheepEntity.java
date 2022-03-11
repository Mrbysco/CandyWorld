package com.mrbysco.candyworld.entity;

import com.mrbysco.candyworld.entity.ai.EatCandyGrassGoal;
import com.mrbysco.candyworld.registry.ModBlocks;
import com.mrbysco.candyworld.registry.ModEntities;
import com.mrbysco.candyworld.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;
import java.util.Random;

public class CandySheepEntity extends Animal {
	private static final EntityDataAccessor<Boolean> SHEARED = SynchedEntityData.defineId(CandySheepEntity.class, EntityDataSerializers.BOOLEAN);
	private int sheepTimer;
	private EatCandyGrassGoal eatGrassGoal;

	public CandySheepEntity(EntityType<? extends CandySheepEntity> type, Level worldIn) {
		super(type, worldIn);
	}

	public CandySheepEntity(Level worldIn) {
		this(ModEntities.COTTON_CANDY_SHEEP.get(), worldIn);
	}

	@Override
	public void defineSynchedData() {
		super.defineSynchedData();
		this.getEntityData().define(SHEARED, Boolean.FALSE);
	}

	@Override
	public void registerGoals() {
		this.eatGrassGoal = new EatCandyGrassGoal(this);
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.1D, Ingredient.of(Items.SUGAR), false));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
		this.goalSelector.addGoal(5, this.eatGrassGoal);
		this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
	}

	@Override
	public void customServerAiStep() {
		this.sheepTimer = this.eatGrassGoal.getEatingGrassTimer();
		super.customServerAiStep();
	}

	@Override
	public void aiStep() {
		if (this.level.isClientSide) {
			this.sheepTimer = Math.max(0, this.sheepTimer - 1);
		}

		super.aiStep();
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 8.0D).add(Attributes.MOVEMENT_SPEED, (double) 0.23F);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void handleEntityEvent(byte id) {
		if (id == 10) {
			this.sheepTimer = 40;
		} else {
			super.handleEntityEvent(id);
		}
	}

	@Override
	public InteractionResult mobInteract(Player playerIn, InteractionHand hand) {
		ItemStack itemstack = playerIn.getItemInHand(hand);

		if (itemstack.is(Tags.Items.RODS_WOODEN) && !this.getSheared() && !this.isBaby()) {
			this.setSheared(true);
			if (itemstack.getCount() == 1) {
				// changes the held stick to cotton candy
				playerIn.setItemInHand(hand, new ItemStack(ModItems.COTTON_CANDY.get()));
			} else {
				itemstack.shrink(1);
				if (!playerIn.addItem(new ItemStack(ModItems.COTTON_CANDY.get(), 1))) {
					// drop cotton candy
					playerIn.drop(new ItemStack(ModItems.COTTON_CANDY.get()), false);
				}
			}
			return InteractionResult.SUCCESS;
		} else {
			return super.mobInteract(playerIn, hand);
		}
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return stack.getItem() == Items.SUGAR;
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return 5;
	}

	public float getHeadRotationPointY(float p_70894_1_) {
		if (this.sheepTimer <= 0) {
			return 0.0F;
		} else if (this.sheepTimer >= 4 && this.sheepTimer <= 36) {
			return 1.0F;
		} else {
			return this.sheepTimer < 4 ? ((float) this.sheepTimer - p_70894_1_) / 4.0F : -((float) (this.sheepTimer - 40) - p_70894_1_) / 4.0F;
		}
	}

	public float getHeadRotationAngleX(float p_70890_1_) {
		if (this.sheepTimer > 4 && this.sheepTimer <= 36) {
			float f = ((float) (this.sheepTimer - 4) - p_70890_1_) / 32.0F;
			return ((float) Math.PI / 5F) + 0.21991149F * Mth.sin(f * 28.7F);
		} else {
			return this.sheepTimer > 0 ? ((float) Math.PI / 5F) : this.getXRot() * ((float) Math.PI / 180F);
		}
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("Sheared", this.getSheared());
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.setSheared(compound.getBoolean("Sheared"));
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.SHEEP_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.SHEEP_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.SHEEP_DEATH;
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		this.playSound(SoundEvents.SHEEP_STEP, 0.15F, 1.0F);
	}


	public boolean getSheared() {
		return this.getEntityData().get(SHEARED);
	}


	private void setSheared(boolean sheared) {
		this.getEntityData().set(SHEARED, sheared);
	}

	@Nullable
	@Override
	public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob mate) {
		return new CandySheepEntity(this.level);
	}

	@Override
	public void ate() {
		this.setSheared(false);

		if (this.isBaby()) {
			this.ageUp(60);
		}
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
		spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
		this.setSheared(false);
		return spawnDataIn;
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 0.95F * sizeIn.height;
	}

	public static boolean canSheepSpawn(EntityType<? extends CandySheepEntity> sheep, LevelAccessor worldIn, MobSpawnType reason, BlockPos pos, Random random) {
		return worldIn.getBlockState(pos.below()).is(ModBlocks.CANDY_GRASS_BLOCK.get()) && worldIn.getRawBrightness(pos, 0) > 8;
	}
}
