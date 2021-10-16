package com.mrbysco.candyworld.world.feature.config;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SpikeFeatureConfig implements FeatureConfiguration {
	public static final Codec<SpikeFeatureConfig> CODEC = RecordCodecBuilder.create((configInstance) -> {
		return configInstance.group(BlockStateProvider.CODEC.fieldOf("state_provider").forGetter((config) -> {
			return config.stateProvider;
		}), BlockState.CODEC.listOf().fieldOf("whitelist").forGetter((config) -> {
			return config.whitelist.stream().map(Block::defaultBlockState).collect(Collectors.toList());
		}), BlockState.CODEC.listOf().fieldOf("blacklist").forGetter((states) -> {
			return ImmutableList.copyOf(states.blacklist);
		}), Codec.INT.fieldOf("chance").orElse(8).forGetter((config) -> {
			return config.chance;
		}), Codec.INT.fieldOf("minLength").orElse(2).forGetter((config) -> {
			return config.minLength;
		}), Codec.INT.fieldOf("maxLength").orElse(24).forGetter((config) -> {
			return config.maxLength;
		}), Codec.BOOL.fieldOf("can_replace").orElse(false).forGetter((config) -> {
			return config.canReplace;
		}), Codec.BOOL.fieldOf("project").orElse(true).forGetter((config) -> {
			return config.project;
		})).apply(configInstance, SpikeFeatureConfig::new);
	});
	public final BlockStateProvider stateProvider;
	public final Set<Block> whitelist;
	public final Set<BlockState> blacklist;
	public final int chance;
	public final int minLength;
	public final int maxLength;
	public final boolean canReplace;
	public final boolean project;

	private SpikeFeatureConfig(BlockStateProvider stateProvider, List<BlockState> whitelist, List<BlockState> blacklist, int chance, int minLength, int maxLength, boolean canReplace, boolean project) {
		this(stateProvider, whitelist.stream().map(BlockBehaviour.BlockStateBase::getBlock).collect(Collectors.toSet()), ImmutableSet.copyOf(blacklist), chance, minLength, maxLength, canReplace, project);
	}

	private SpikeFeatureConfig(BlockStateProvider stateProvider, Set<Block> whitelist, Set<BlockState> blacklist, int chance, int minLength, int maxLength, boolean canReplace, boolean project) {
		this.stateProvider = stateProvider;
		this.whitelist = whitelist;
		this.blacklist = blacklist;
		this.chance = chance;
		this.minLength = minLength;
		this.maxLength = maxLength;
		this.canReplace = canReplace;
		this.project = project;
	}

	public static class Builder {
		private final BlockStateProvider stateProvider;
		private Set<Block> whitelist = ImmutableSet.of();
		private Set<BlockState> blacklist = ImmutableSet.of();
		private int chance = 8;
		private int minLength = 3;
		private int maxLength = 24;
		private boolean canReplace;
		private boolean project = true;

		public Builder(BlockStateProvider stateProvider) {
			this.stateProvider = stateProvider;
		}

		public SpikeFeatureConfig.Builder whitelist(Set<Block> whitelist) {
			this.whitelist = whitelist;
			return this;
		}

		public SpikeFeatureConfig.Builder blacklist(Set<BlockState> blacklist) {
			this.blacklist = blacklist;
			return this;
		}

		public SpikeFeatureConfig.Builder chance(int chance) {
			this.chance = chance;
			return this;
		}

		public SpikeFeatureConfig.Builder minLength(int minLength) {
			this.minLength = minLength;
			return this;
		}

		public SpikeFeatureConfig.Builder maxLength(int maxLength) {
			this.maxLength = maxLength;
			return this;
		}

		public SpikeFeatureConfig.Builder canReplace() {
			this.canReplace = true;
			return this;
		}

		public SpikeFeatureConfig.Builder noProjection() {
			this.project = false;
			return this;
		}

		public SpikeFeatureConfig build() {
			return new SpikeFeatureConfig(this.stateProvider, this.whitelist, this.blacklist, this.chance, this.minLength, this.maxLength, this.canReplace, this.project);
		}
	}
}
