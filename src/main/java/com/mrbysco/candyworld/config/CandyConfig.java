package com.mrbysco.candyworld.config;

import com.mrbysco.candyworld.CandyWorld;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import org.apache.commons.lang3.tuple.Pair;

public class CandyConfig {
	public static class Common {
		public final BooleanValue disableTeleporter;

		public final IntValue weightCottonCandyPlains;
		public final IntValue weightChocolateForest;
		public final IntValue weightGummySwamp;

		public final IntValue weightCottonCandySheep;
		public final IntValue weightEasterChicken;
		public final IntValue weightGummyMouse;
		public final IntValue weightGummyBear;
		public final BooleanValue preventModdedMobSpawn;

		public final BooleanValue recursiveTreeTrunks;
		public final BooleanValue stackableTreeTrunks;

		Common(ForgeConfigSpec.Builder builder) {
			builder.comment("Dimension settings")
					.push("Dimension");

			disableTeleporter = builder
					.comment("Setting this to true will prevent players from teleporting to the dimension")
					.define("disableTeleporter", false);

			builder.pop();
			builder.comment("Biome settings")
					.push("Biome");

			weightCottonCandyPlains = builder
					.comment("Overworld cotton candy plains biome weight. 0 to prevent generation in overworld")
					.defineInRange("weightCottonCandyPlains", 1, 0, Integer.MAX_VALUE);

			weightChocolateForest = builder
					.comment("Overworld chocolate forest biome weight. 0 to prevent generation in overworld")
					.defineInRange("weightChocolateForest", 1, 0, Integer.MAX_VALUE);

			weightGummySwamp = builder
					.comment("Overworld gummy swamp biome weight. 0 to prevent generation in overworld")
					.defineInRange("weightGummySwamp", 1, 0, Integer.MAX_VALUE);

			builder.pop();
			builder.comment("Mob settings")
					.push("Mobs");

			weightCottonCandySheep = builder
					.comment("Cotton candy sheep weight. 0 to prevent spawning")
					.defineInRange("weightCottonCandySheep", 140, 1, Integer.MAX_VALUE);

			weightEasterChicken = builder
					.comment("Easter chicken weight. 0 to prevent spawning")
					.defineInRange("weightEasterChicken", 140, 1, Integer.MAX_VALUE);

			weightGummyMouse = builder
					.comment("Gummy mice weight. 0 to prevent spawning")
					.defineInRange("weightGummyMouse", 140, 1, Integer.MAX_VALUE);

			weightGummyBear = builder
					.comment("Gummy bear weight. 0 to prevent spawning")
					.defineInRange("weightGummyBear", 110, 1, Integer.MAX_VALUE);

			preventModdedMobSpawn = builder
					.comment("setting this to true should prevent any non-Candy World mobs from spawning in candy world biomes")
					.define("preventModdedMobSpawn", false);

			builder.pop();
			builder.comment("General settings")
					.push("General");

			recursiveTreeTrunks = builder
					.comment("Setting this to true will make tree trunks take longer to mine the higher they are")
					.define("recursiveTreeTrunks", false);


			stackableTreeTrunks = builder
					.comment("Setting this to false will make tree trunk blocks behave like normal blocks")
					.define("stackableTreeTrunks", true);


			builder.pop();
		}
	}

	public static final ForgeConfigSpec commonSpec;
	public static final Common COMMON;

	static {
		final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
		commonSpec = specPair.getRight();
		COMMON = specPair.getLeft();
	}

	@SubscribeEvent
	public static void onLoad(final ModConfigEvent.Loading configEvent) {
		CandyWorld.LOGGER.debug("Loaded Candy World's config file {}", configEvent.getConfig().getFileName());
	}

	@SubscribeEvent
	public static void onFileChange(final ModConfigEvent.Reloading configEvent) {
		CandyWorld.LOGGER.debug("Candy World's config just got changed on the file system!");
	}
}
