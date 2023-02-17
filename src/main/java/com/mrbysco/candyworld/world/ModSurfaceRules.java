package com.mrbysco.candyworld.world;

import com.mojang.serialization.Codec;
import com.mrbysco.candyworld.CandyWorld;
import com.mrbysco.candyworld.enums.EnumGummy;
import com.mrbysco.candyworld.registry.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

public class ModSurfaceRules extends SurfaceRules {
	public static final DeferredRegister<Codec<? extends SurfaceRules.RuleSource>> RULE_REGISTRY = DeferredRegister.create(Registry.RULE_REGISTRY, CandyWorld.MOD_ID);

	public static final RegistryObject<Codec<GummyBands>> GUMMY = RULE_REGISTRY.register("gummy", () -> GummyBands.CODEC);

	public enum GummyBands implements SurfaceRules.RuleSource {
		INSTANCE;

		static final Codec<GummyBands> CODEC = Codec.unit(INSTANCE);

		public Codec<? extends SurfaceRules.RuleSource> codec() {
			return CODEC;
		}

		public SurfaceRules.SurfaceRule apply(SurfaceRules.Context context) {
			NormalNoise normalNoise = context.system.getOrCreateNoise(Noises.SURFACE);
			class GummyRule implements SurfaceRule {
				@Nullable
				@Override
				public BlockState tryApply(int x, int y, int z) {
					double noise = normalNoise.getValue(x, 0.0F, z);
					EnumGummy gummy = EnumGummy.getGummyForGeneration(noise);
					return switch (gummy) {
						default -> ModBlocks.RED_GUMMY_BLOCK.get().defaultBlockState();
						case ORANGE -> ModBlocks.ORANGE_GUMMY_BLOCK.get().defaultBlockState();
						case YELLOW -> ModBlocks.YELLOW_GUMMY_BLOCK.get().defaultBlockState();
						case WHITE -> ModBlocks.WHITE_GUMMY_BLOCK.get().defaultBlockState();
						case GREEN -> ModBlocks.GREEN_GUMMY_BLOCK.get().defaultBlockState();
					};
				}
			}
			return new GummyRule();
		}
	}
}
