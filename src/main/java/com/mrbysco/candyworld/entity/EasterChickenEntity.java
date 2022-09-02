package com.mrbysco.candyworld.entity;

import com.mrbysco.candyworld.entity.ai.EasterChickenPanicGoal;
import com.mrbysco.candyworld.enums.EnumChocolate;
import com.mrbysco.candyworld.registry.ModBlocks;
import com.mrbysco.candyworld.registry.ModEntities;
import com.mrbysco.candyworld.registry.ModItems;
import com.mrbysco.candyworld.registry.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.Random;

public class EasterChickenEntity extends Animal {
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

	public EasterChickenEntity(EntityType<? extends EasterChickenEntity> type, Level worldIn) {
		super(type, worldIn);
		this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
		this.explodeWhenDone = false;
		this.nextEggType = -1;
	}

	public EasterChickenEntity(Level worldIn) {
		this(ModEntities.EASTER_CHICKEN.get(), worldIn);
	}

	@Override
	protected void spawnSprintParticle() {
		this.level.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5D, this.getZ(), 0.0D, 0.0D, 0.0D);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new EasterChickenPanicGoal(this, 1.3D));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, Ingredient.of(ModItems.WAFER_STICK.get(),
				ModItems.MILK_CHOCOLATE_BAR.get(), ModItems.WHITE_CHOCOLATE_BAR.get(), ModItems.DARK_CHOCOLATE_BAR.get()), false));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
	}

	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return this.isBaby() ? sizeIn.height * 0.85F : sizeIn.height * 0.92F;
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 4.0D).add(Attributes.MOVEMENT_SPEED, 0.25D);
	}

	@Override
	public InteractionResult mobInteract(Player playerIn, InteractionHand hand) {
		ItemStack itemStack = playerIn.getItemInHand(hand);

		if (!this.isBaby() && this.nextEggType == -1 && !this.explodeWhenDone) {
			if (itemStack.getItem() == Items.FIRE_CHARGE) {
				firePanic();
				playerIn.swing(hand);
				itemStack.shrink(1);
				return InteractionResult.SUCCESS;
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
				return InteractionResult.SUCCESS;
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
				return InteractionResult.SUCCESS;
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
				return InteractionResult.SUCCESS;
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
				Vec3 motion = getDeltaMovement();
				setDeltaMovement(motion.x + this.random.nextFloat() * 0.4F, motion.y + (this.random.nextFloat() - this.random.nextFloat()) * 0.3F, motion.z + (this.random.nextFloat() - this.random.nextFloat()) * 0.3F);
			}
		}
		for (int i = 0; i <= this.random.nextInt(3) + 3; i++) {
			Item chocolateEgg = ForgeRegistries.ITEMS.tags().getTag(ModTags.CHOCOLATE_EGGS).getRandomElement(random).orElse(ModItems.MILK_CHOCOLATE_EGG.get());
			ItemEntity ent = this.spawnAtLocation(new ItemStack(chocolateEgg), 1);
			if (ent != null) {
				Vec3 motion = getDeltaMovement();
				setDeltaMovement(motion.x + this.random.nextFloat() * 0.4F, motion.y + (this.random.nextFloat() - this.random.nextFloat()) * 0.3F, motion.z + (this.random.nextFloat() - this.random.nextFloat()) * 0.3F);
			}
		}
		this.removeAfterChangingDimensions();
	}

	private void dropEgg(int meta) {
		Item eggItem = switch (meta) {
			case 1 -> ModItems.WHITE_CHOCOLATE_EGG.get();
			case 2 -> ModItems.DARK_CHOCOLATE_EGG.get();
			default -> ModItems.MILK_CHOCOLATE_EGG.get();
		};
		ItemStack stack = new ItemStack(eggItem);
		Vec3 motion = getDeltaMovement();
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
		this.destPos = Mth.clamp(this.destPos, 0.0F, 1.0F);

		if (!this.onGround && this.wingRotDelta < 1.0F) {
			this.wingRotDelta = 1.0F;
		}

		this.wingRotDelta = (float) ((double) this.wingRotDelta * 0.9D);

		Vec3 motion = this.getDeltaMovement();
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
	public boolean causeFallDamage(float p_147187_, float p_147188_, DamageSource p_147189_) {
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
	public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob mate) {
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
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);

		if (compound.contains("EggLayTime")) {
			this.timeUntilNextEgg = compound.getInt("EggLayTime");
		}
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("EggLayTime", this.timeUntilNextEgg);
	}

	public static boolean canChickenSpawn(EntityType<? extends EasterChickenEntity> chicken, LevelAccessor worldIn, MobSpawnType reason, BlockPos pos, Random random) {
		return worldIn.getBlockState(pos.below()).is(ModBlocks.CANDY_GRASS_BLOCK.get()) && worldIn.getRawBrightness(pos, 0) > 8;
	}
}
