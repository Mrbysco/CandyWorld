package com.mrbysco.candyworld.entity;

import com.mrbysco.candyworld.entity.ai.EasterChickenPanicGoal;
import com.mrbysco.candyworld.enums.EnumChocolate;
import com.mrbysco.candyworld.registry.ModBlocks;
import com.mrbysco.candyworld.registry.ModEntities;
import com.mrbysco.candyworld.registry.ModItems;
import com.mrbysco.candyworld.registry.ModTags;
import net.minecraft.block.BlockState;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

public class EasterChickenEntity extends AnimalEntity {
	public float wingRotation;
	public float destPos;
	public float oFlapSpeed;
	public float oFlap;
	/**
	 * This chicken will explode when eggComboAmount reaches 0
	 */
	public boolean explodeWhenDone;
	private float wingRotDelta = 1.0F;
	/**
	 * The time until the next egg is spawned
	 */
	public int timeUntilNextEgg = this.random.nextInt(6000) + 6000;
	/**
	 * Type for the next egg
	 */
	private int nextEggType;
	/**
	 * Amount of eggs still to be laid in quick succession
	 */
	private int eggComboAmount;

	public EasterChickenEntity(EntityType<? extends EasterChickenEntity> type, World worldIn) {
		super(type, worldIn);
		this.setPathfindingMalus(PathNodeType.WATER, 0.0F);
		this.explodeWhenDone = false;
		this.nextEggType = -1;
	}

	public EasterChickenEntity(World worldIn) {
		this(ModEntities.EASTER_CHICKEN.get(), worldIn);
	}

	@Override
	protected void spawnSprintParticle() {
		this.level.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5D, this.getZ(), 0.0D, 0.0D, 0.0D);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new EasterChickenPanicGoal(this, 1.3D));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, false, Ingredient.of(ModItems.WAFER_STICK.get(),
				ModItems.MILK_CHOCOLATE_BAR.get(), ModItems.WHITE_CHOCOLATE_BAR.get(), ModItems.DARK_CHOCOLATE_BAR.get())));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
	}

	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return this.isBaby() ? sizeIn.height * 0.85F : sizeIn.height * 0.92F;
	}

	public static AttributeModifierMap.MutableAttribute registerAttributes() {
		return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 4.0D).add(Attributes.MOVEMENT_SPEED, 0.25D);
	}

	@Override
	public ActionResultType mobInteract(PlayerEntity playerIn, Hand hand) {
		ItemStack itemStack = playerIn.getItemInHand(hand);

		if (!this.isBaby() && this.nextEggType == -1 && !this.explodeWhenDone) {
			if (itemStack.getItem() == Items.FIRE_CHARGE) {
				firePanic();
				playerIn.swing(hand);
				itemStack.shrink(1);
				return ActionResultType.SUCCESS;
			}
			if (itemStack.getItem() == ModItems.MILK_CHOCOLATE_BAR.get()) {
				if (!this.level.isClientSide) {
					this.timeUntilNextEgg = 30 + this.random.nextInt(30);
					itemStack.shrink(1);
					this.nextEggType = 0;
				}
				this.level.addParticle(ParticleTypes.HAPPY_VILLAGER,
						this.getX() + (double) (this.random.nextFloat() * this.getBbWidth() * 2.0F) - (double) this.getBbWidth(),
						this.getY() + 0.5D + (double) (this.random.nextFloat() * this.getBbHeight()),
						this.getZ() + (double) (this.random.nextFloat() * this.getBbWidth() * 2.0F) - (double) this.getBbWidth(), 0.0D, 0.0D, 0.0D);
				return ActionResultType.SUCCESS;
			} else if (itemStack.getItem() == ModItems.WHITE_CHOCOLATE_BAR.get()) {
				if (!this.level.isClientSide) {
					this.timeUntilNextEgg = 30 + this.random.nextInt(30);
					itemStack.shrink(1);
					this.nextEggType = 1;
				}
				this.level.addParticle(ParticleTypes.HAPPY_VILLAGER,
						this.getX() + (double) (this.random.nextFloat() * this.getBbWidth() * 2.0F) - (double) this.getBbWidth(),
						this.getY() + 0.5D + (double) (this.random.nextFloat() * this.getBbHeight()),
						this.getZ() + (double) (this.random.nextFloat() * this.getBbWidth() * 2.0F) - (double) this.getBbWidth(), 0.0D, 0.0D, 0.0D);
				return ActionResultType.SUCCESS;
			} else if (itemStack.getItem() == ModItems.DARK_CHOCOLATE_BAR.get()) {
				if (!this.level.isClientSide) {
					this.timeUntilNextEgg = 30 + this.random.nextInt(30);
					itemStack.shrink(1);
					this.nextEggType = 2;
				}
				this.level.addParticle(ParticleTypes.HAPPY_VILLAGER,
						this.getX() + (double) (this.random.nextFloat() * this.getBbWidth() * 2.0F) - (double) this.getBbWidth(),
						this.getY() + 0.5D + (double) (this.random.nextFloat() * this.getBbHeight()),
						this.getZ() + (double) (this.random.nextFloat() * this.getBbWidth() * 2.0F) - (double) this.getBbWidth(), 0.0D, 0.0D, 0.0D);
				return ActionResultType.SUCCESS;
			}
		}

		return super.mobInteract(playerIn, hand);
	}

	@Override
	public void spawnAnim() {
		super.spawnAnim();
		if (this.level.isClientSide) {
			this.level.addParticle(ParticleTypes.EXPLOSION, this.getX(), this.getY() + 0.5D, this.getZ(), 0.0D, 0.0D, 0.0D);
		} else {
			this.level.broadcastEntityEvent(this, (byte) 20);
		}
	}

	private void firePanic() {
		if (!this.level.isClientSide) {
			this.timeUntilNextEgg = 20 + this.random.nextInt(50);
			this.hurt(DamageSource.GENERIC, 0.0F);
			this.explodeWhenDone = true;
			this.setSprinting(true);
			this.eggComboAmount = 25 + this.random.nextInt(20);
			this.playSound(SoundEvents.TNT_PRIMED, 1.0F, 1.0F);
		}
	}

	private void explode() {
		this.spawnAnim();
		this.playSound(SoundEvents.GENERIC_EXPLODE, 1.0F, 1.0F);
		for (int i = 0; i <= this.random.nextInt(5) + 3; i++) {
			ItemEntity ent = this.spawnAtLocation(new ItemStack(Items.FEATHER, 1), 0);
			if (ent != null) {
				Vector3d motion = getDeltaMovement();
				setDeltaMovement(motion.x + this.random.nextFloat() * 0.4F, motion.y + (this.random.nextFloat() - this.random.nextFloat()) * 0.3F, motion.z + (this.random.nextFloat() - this.random.nextFloat()) * 0.3F);
			}
		}
		for (int i = 0; i <= this.random.nextInt(3) + 3; i++) {
			ItemEntity ent = this.spawnAtLocation(new ItemStack(ModTags.CHOCOLATE_EGGS.getRandomElement(random)), 1);
			if (ent != null) {
				Vector3d motion = getDeltaMovement();
				setDeltaMovement(motion.x + this.random.nextFloat() * 0.4F, motion.y + (this.random.nextFloat() - this.random.nextFloat()) * 0.3F, motion.z + (this.random.nextFloat() - this.random.nextFloat()) * 0.3F);
			}
		}
		this.removeAfterChangingDimensions();
	}

	private void dropEgg(int meta) {
		Item eggItem;
		switch (meta) {
			case 1:
				eggItem = ModItems.WHITE_CHOCOLATE_EGG.get();
				break;
			case 2:
				eggItem = ModItems.DARK_CHOCOLATE_EGG.get();
				break;
			default:
				eggItem = ModItems.MILK_CHOCOLATE_EGG.get();
				break;
		}
		ItemStack stack = new ItemStack(eggItem);
		Vector3d motion = getDeltaMovement();
		ItemEntity entityitem = new ItemEntity(this.level, this.getX() - motion.x * 5, this.getY(), this.getZ() - motion.z * 5, stack);
		entityitem.setDefaultPickUpDelay();

		this.level.addFreshEntity(entityitem);
	}

	@Override
	public void aiStep() {
		super.aiStep();
		this.oFlap = this.wingRotation;
		this.oFlapSpeed = this.destPos;
		this.destPos = (float) ((double) this.destPos + (double) (this.onGround ? -1 : 4) * 0.3D);
		this.destPos = MathHelper.clamp(this.destPos, 0.0F, 1.0F);

		if (!this.onGround && this.wingRotDelta < 1.0F) {
			this.wingRotDelta = 1.0F;
		}

		this.wingRotDelta = (float) ((double) this.wingRotDelta * 0.9D);

		Vector3d motion = this.getDeltaMovement();
		if (!this.onGround && motion.y < 0.0D) {
			this.setDeltaMovement(motion.multiply(1.0D, 0.6D, 1.0D));
		}

		this.wingRotation += this.wingRotDelta * 2.0F;

		// drop an egg
		if (!this.level.isClientSide && !this.isBaby() && --this.timeUntilNextEgg <= 0) {

			// whether the chicken has been fed chocolate, combo's should not happen
			boolean flag = false;

			this.playSound(SoundEvents.CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);

			// choose egg type
			int meta = this.random.nextInt(EnumChocolate.META_LOOKUP.length);
			if (this.nextEggType != -1) {
				meta = this.nextEggType;
				flag = true;
				this.nextEggType = -1;
			}
			this.dropEgg(meta);

			// chance for combo, can only happen when currently not in combo and last egg was not chocolate induced
			if (this.eggComboAmount <= 0 && this.random.nextInt(100) == 0 && !flag) {
				this.eggComboAmount = this.random.nextInt(30) + 30;
				this.hurt(DamageSource.GENERIC, 0.0F);
			}

			// set time until next egg
			if (this.eggComboAmount-- > 0) {
				this.timeUntilNextEgg = 1;
			} else {
				this.timeUntilNextEgg = this.random.nextInt(6000) + 10000;
			}

			// check whether the chicken should explode
			if (this.eggComboAmount == 0 && this.explodeWhenDone) {
				this.explode();
			}
		}
	}

	@Override
	public boolean causeFallDamage(float distance, float damageMultiplier) {
		return false;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.CHICKEN_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.CHICKEN_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.CHICKEN_DEATH;
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		this.playSound(SoundEvents.CHICKEN_STEP, 0.15F, 1.0F);
	}

	@Nullable
	@Override
	public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity mate) {
		return new EasterChickenEntity(this.level);
	}

	/**
	 * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
	 * the animal type)
	 */
	@Override
	public boolean isFood(ItemStack stack) {
		return stack.getItem() == ModItems.WAFER_STICK.get();
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);

		if (compound.contains("EggLayTime")) {
			this.timeUntilNextEgg = compound.getInt("EggLayTime");
		}
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("EggLayTime", this.timeUntilNextEgg);
	}

	public static boolean canChickenSpawn(EntityType<? extends EasterChickenEntity> chicken, IWorld worldIn, SpawnReason reason, BlockPos pos, Random random) {
		return worldIn.getBlockState(pos.below()).is(ModBlocks.CANDY_GRASS_BLOCK.get()) && worldIn.getRawBrightness(pos, 0) > 8;
	}
}
