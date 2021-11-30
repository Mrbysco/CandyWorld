package com.mrbysco.candyworld.block;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraftforge.common.util.ForgeSoundType;

public class ModSoundTypes {

    public static final SoundType COTTON_CANDY = new ForgeSoundType(1.0F, 0F, () -> SoundEvents.WOOL_BREAK, () -> SoundEvents.WOOL_STEP, () -> SoundEvents.WOOL_BREAK, () -> SoundEvents.WOOL_HIT, () -> SoundEvents.WOOL_FALL);
    public static final SoundType CANDY_GRASS = new ForgeSoundType(1.0F, 0F, () -> SoundEvents.GRAVEL_BREAK, () -> SoundEvents.WOOL_STEP, () -> SoundEvents.GRAVEL_PLACE, () -> SoundEvents.GRAVEL_HIT, () -> SoundEvents.WOOL_FALL);
    public static final SoundType CANDY_DIRT = new ForgeSoundType(1.0F, 0F, () -> SoundEvents.GRAVEL_BREAK, () -> SoundEvents.GRAVEL_STEP, () -> SoundEvents.GRAVEL_PLACE, () -> SoundEvents.GRAVEL_HIT, () -> SoundEvents.GRAVEL_FALL);
    public static final SoundType GUMMY = new ForgeSoundType(1.0F, 1.0F, () -> SoundEvents.SLIME_BLOCK_STEP, () -> SoundEvents.SLIME_SQUISH, () -> SoundEvents.SLIME_BLOCK_PLACE, () -> SoundEvents.SLIME_BLOCK_HIT, () -> SoundEvents.SLIME_BLOCK_FALL);
}
