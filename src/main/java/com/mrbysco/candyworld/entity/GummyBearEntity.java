package com.mrbysco.candyworld.entity;

import com.mrbysco.candyworld.enums.EnumGummy;
import com.mrbysco.candyworld.registry.ModEntities;
import com.mrbysco.candyworld.registry.ModLootTables;
import com.mrbysco.candyworld.registry.ModTags;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PolarBearEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

public class GummyBearEntity extends PolarBearEntity {

	private static final DataParameter<Byte> COLOR = EntityDataManager.defineId(GummyBearEntity.class, DataSerializers.BYTE);

	public GummyBearEntity(EntityType<? extends GummyBearEntity> type, World worldIn) {
		super(type, worldIn);
	}

	public GummyBearEntity(World worldIn) {
		super(ModEntities.GUMMY_BEAR.get(), worldIn);
	}

	public GummyBearEntity(World worldIn, EnumGummy color) {
		this(worldIn);
		this.setColor(color);
	}

	public EnumGummy getColor() {
		return EnumGummy.byMetadata(this.entityData.get(COLOR));
	}

	public void setColor(EnumGummy enumgummy) {
		this.entityData.set(COLOR, (byte) enumgummy.getMetadata());
	}

	public static AttributeModifierMap.MutableAttribute registerAttributes() {
		return PolarBearEntity.createAttributes();
	}

	@Override
	@Nullable
	protected ResourceLocation getDefaultLootTable() {
		switch (getColor()) {
			default:
				return ModLootTables.ENTITY_BEAR_RED;
			case ORANGE:
				return ModLootTables.ENTITY_BEAR_ORANGE;
			case YELLOW:
				return ModLootTables.ENTITY_BEAR_YELLOW;
			case WHITE:
				return ModLootTables.ENTITY_BEAR_WHITE;
			case GREEN:
				return ModLootTables.ENTITY_BEAR_GREEN;
		}
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(COLOR, (byte) 0);
	}

	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		if (spawnDataIn instanceof GroupData) {
			if (((GroupData) spawnDataIn).madeParent) {
				if (!((GroupData) spawnDataIn).madeSecondParent && random.nextInt(3) == 0) {
					((GroupData) spawnDataIn).madeSecondParent = true;
				} else {
					this.setAge(-24000);
				}
			}
			if (((GroupData) spawnDataIn).color != null) {
				setColor(((GroupData) spawnDataIn).color);
			} else {
				setColor(EnumGummy.random(this.random));
			}
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
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putByte("Color", (byte) this.getColor().getMetadata());
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		this.setColor(EnumGummy.byMetadata(compound.getByte("Color")));
	}

	@Override
	public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity mate) {
		if (mate instanceof GummyBearEntity) {
			return new GummyBearEntity(this.level, ((GummyBearEntity) mate).getColor());
		}
		return new GummyBearEntity(this.level);
	}

	static class GroupData implements ILivingEntityData {
		public boolean madeParent;
		public boolean madeSecondParent;
		public EnumGummy color;

		private GroupData() {
		}
	}

	public static boolean canGummySpawn(EntityType<? extends AnimalEntity> entityType, IWorld world, SpawnReason reason, BlockPos pos, Random random) {
		return world.getBlockState(pos.below()).is(ModTags.GUMMY) && world.getRawBrightness(pos, 0) > 8;
	}
}
