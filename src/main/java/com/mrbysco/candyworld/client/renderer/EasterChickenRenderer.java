package com.mrbysco.candyworld.client.renderer;

import com.mrbysco.candyworld.CandyWorld;
import com.mrbysco.candyworld.client.ClientHandler;
import com.mrbysco.candyworld.entity.EasterChickenEntity;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.ParametersAreNonnullByDefault;


@OnlyIn(Dist.CLIENT)
public class EasterChickenRenderer extends MobRenderer<EasterChickenEntity, ChickenModel<EasterChickenEntity>> {
	private static final ResourceLocation CHICKEN_TEXTURES = new ResourceLocation(CandyWorld.MOD_ID, "textures/entity/easter_chicken/easter_chicken.png");

	public EasterChickenRenderer(EntityRendererProvider.Context context) {
		super(context, new ChickenModel(context.bakeLayer(ClientHandler.EASTER_CHICKEN)), 0.3F);
	}

	@Override
	@ParametersAreNonnullByDefault
	public ResourceLocation getTextureLocation(EasterChickenEntity chickenEntity) {
		return CHICKEN_TEXTURES;
	}

	@Override
	protected float getBob(EasterChickenEntity chickenEntity, float partialTicks) {
		float f = Mth.lerp(partialTicks, chickenEntity.oFlap, chickenEntity.wingRotation);
		float f1 = Mth.lerp(partialTicks, chickenEntity.oFlapSpeed, chickenEntity.destPos);
		return (Mth.sin(f) + 1.0F) * f1;
	}
}
