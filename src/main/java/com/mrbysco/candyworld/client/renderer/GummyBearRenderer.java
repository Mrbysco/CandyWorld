package com.mrbysco.candyworld.client.renderer;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mrbysco.candyworld.CandyWorld;
import com.mrbysco.candyworld.client.ClientHandler;
import com.mrbysco.candyworld.client.model.GummyBearModel;
import com.mrbysco.candyworld.entity.GummyBearEntity;
import com.mrbysco.candyworld.enums.EnumGummy;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class GummyBearRenderer extends MobRenderer<GummyBearEntity, GummyBearModel<GummyBearEntity>> {
	private static final Map<EnumGummy, ResourceLocation> GUMMY_BEAR_TEXTURES = Util.make(Maps.newEnumMap(EnumGummy.class), (location) -> {
		location.put(EnumGummy.RED, new ResourceLocation(CandyWorld.MOD_ID, "textures/entity/gummy_bear/red_gummy_bear.png"));
		location.put(EnumGummy.ORANGE, new ResourceLocation(CandyWorld.MOD_ID, "textures/entity/gummy_bear/orange_gummy_bear.png"));
		location.put(EnumGummy.YELLOW, new ResourceLocation(CandyWorld.MOD_ID, "textures/entity/gummy_bear/yellow_gummy_bear.png"));
		location.put(EnumGummy.WHITE, new ResourceLocation(CandyWorld.MOD_ID, "textures/entity/gummy_bear/white_gummy_bear.png"));
		location.put(EnumGummy.GREEN, new ResourceLocation(CandyWorld.MOD_ID, "textures/entity/gummy_bear/green_gummy_bear.png"));
	});

	public GummyBearRenderer(EntityRendererProvider.Context context) {
		super(context, new GummyBearModel(context.bakeLayer(ClientHandler.GUMMY_BEAR)), 0.7F);
	}

	@Override
	public void render(GummyBearEntity entityIn, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int packedLightIn) {
		super.render(entityIn, entityYaw, partialTicks, poseStack, bufferSource, packedLightIn);
	}

	@Override
	public ResourceLocation getTextureLocation(GummyBearEntity entity) {
		return GUMMY_BEAR_TEXTURES.getOrDefault(entity.getColor(), GUMMY_BEAR_TEXTURES.get(EnumGummy.RED));
	}

	@Override
	protected void scale(GummyBearEntity gummyBear, PoseStack poseStack, float partialTickTime) {
		poseStack.scale(1.2F, 1.2F, 1.2F);
	}
}
