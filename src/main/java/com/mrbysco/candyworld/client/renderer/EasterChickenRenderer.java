package com.mrbysco.candyworld.client.renderer;

import com.mrbysco.candyworld.CandyWorld;
import com.mrbysco.candyworld.entity.EasterChickenEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.ChickenModel;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.ParametersAreNonnullByDefault;


@OnlyIn(Dist.CLIENT)
public class EasterChickenRenderer extends MobRenderer<EasterChickenEntity, ChickenModel<EasterChickenEntity>> {
	private static final ResourceLocation CHICKEN_TEXTURES = new ResourceLocation(CandyWorld.MOD_ID, "textures/entity/easter_chicken/easter_chicken.png");

	public EasterChickenRenderer(EntityRendererManager rendererManager) {
		super(rendererManager, new ChickenModel(), 0.3F);
	}

	@Override
	@ParametersAreNonnullByDefault
	public ResourceLocation getTextureLocation(EasterChickenEntity entity) {
		return CHICKEN_TEXTURES;
	}

	@Override
	protected float getBob(EasterChickenEntity livingBase, float partialTicks) {
		float f = MathHelper.lerp(partialTicks, livingBase.oFlap, livingBase.wingRotation);
		float f1 = MathHelper.lerp(partialTicks, livingBase.oFlapSpeed, livingBase.destPos);
		return (MathHelper.sin(f) + 1.0F) * f1;
	}
}
