package com.mrbysco.candyworld.entity;

import com.mrbysco.candyworld.enums.EnumGummy;
import com.mrbysco.candyworld.registry.ModEntities;
import com.mrbysco.candyworld.registry.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.PolarBear;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Random;

public class GummyBearEntity extends PolarBear {

	private static final EntityDataAccessor<Byte> COLOR = SynchedEntityData.defineId(GummyBearEntity.class, EntityDataSerializers.BYTE);

	public GummyBearEntity(EntityType<? extends GummyBearEntity> type, Level worldIn) {
		super(type, worldIn);
	}

	public GummyBearEntity(Level worldIn) {
		super(ModEntities.GUMMY_BEAR.get(), worldIn);
	}

	public GummyBearEntity(Level worldIn, EnumGummy color) {
		this(worldIn);
		this.setColor(color);
	}

	public EnumGummy getColor() {
		return EnumGummy.byMetadata(this.entityData.get(COLOR));
	}

	public void setColor(EnumGummy enumgummy) {
		this.entityData.set(COLOR, (byte) enumgummy.getMetadata());
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return PolarBear.createAttributes();
	}

	@Override
	@Nullable
	protected ResourceLocation getDefaultLootTable() {
		return switch (getColor()) {
			default -> ModLootTables.ENTITY_BEAR_RED;
			case ORANGE -> ModLootTables.ENTITY_BEAR_ORANGE;
			case YELLOW -> ModLootTables.ENTITY_BEAR_YELLOW;
			case WHITE -> ModLootTables.ENTITY_BEAR_WHITE;
			case GREEN -> ModLootTables.ENTITY_BEAR_GREEN;
		};
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(COLOR, (byte) 0);
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
		if (spawnDataIn instanceof GroupData) {
			if (((GroupData) spawnDataIn).madeParent) {
				if (!((GroupData) spawnDataIn).madeSecondParent && random.nextInt(3) == 0) {
					((GroupData) spawnDataIn).madeSecondParent = true;
				} else {
					this.setAge(-24000);
				}
			}
			setColor(Objects.requireNonNullElseGet(((GroupData) spawnDataIn).color, () -> EnumGummy.random(this.random)));
		} else {
			GroupData entitygummybear$groupdata = new GroupData();
			entitygummybear$groupdata.madeParent = true;
			entitygummybear$groupdata.color = EnumGummy.random(this.random);
			setColor(entitygummybear$groupdata.color);
			spawnDataIn = entitygummybear$groupdata;
		}

		return spawnDataIn;
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

	@Override
	public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob mate) {
		if (mate instanceof GummyBearEntity) {
			return new GummyBearEntity(this.level, ((GummyBearEntity) mate).getColor());
		}
		return new GummyBearEntity(this.level);
	}

	static class GroupData implements SpawnGroupData {
		public boolean madeParent;
		public boolean madeSecondParent;
		public EnumGummy color;

		private GroupData() {
		}
	}

	public static boolean canGummySpawn(EntityType<? extends Animal> entityType, LevelAccessor world, MobSpawnType reason, BlockPos pos, Random random) {
		return world.getBlockState(pos.below()).is(ModTags.GUMMY) && world.getRawBrightness(pos, 0) > 8;
	}
}
