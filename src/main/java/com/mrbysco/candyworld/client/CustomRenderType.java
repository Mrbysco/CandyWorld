package com.mrbysco.candyworld.client;

import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mrbysco.candyworld.CandyWorld;
import net.minecraft.client.renderer.RenderState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;

public class CustomRenderType extends RenderType {
	public CustomRenderType(String nameIn, VertexFormat formatIn, int drawModeIn, int bufferSizeIn, boolean useDelegateIn, boolean needsSortingIn, Runnable setupTaskIn, Runnable clearTaskIn) {
		super(nameIn, formatIn, drawModeIn, bufferSizeIn, useDelegateIn, needsSortingIn, setupTaskIn, clearTaskIn);
	}

	public static RenderType getEntityTranslucentZOffset(ResourceLocation LocationIn, boolean outlineIn) {
		RenderType.State rendertype$state =
				RenderType.State.builder().setTextureState(new RenderState.TextureState(LocationIn, false, false))
						.setTransparencyState(new RenderState.TransparencyState("translucent_transparency", () -> {
							RenderSystem.enableBlend();
							RenderSystem.blendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA,
									SourceFactor.ZERO, DestFactor.ONE_MINUS_SRC_ALPHA);
						}, () -> {
							RenderSystem.disableBlend();
							RenderSystem.defaultBlendFunc();
						}))
						.setDiffuseLightingState(DIFFUSE_LIGHTING)
						.setAlphaState(DEFAULT_ALPHA)
						.setCullState(NO_CULL)
						.setLightmapState(LIGHTMAP)
						.setOverlayState(OVERLAY)
						.setLayeringState(VIEW_OFFSET_Z_LAYERING)
						.createCompositeState(outlineIn);
		return create(CandyWorld.MOD_ID + ":entity_translucent_z_offset", DefaultVertexFormats.NEW_ENTITY, 7, 256, true, true, rendertype$state);
	}

	public static RenderType getEntityTranslucentZOffset(ResourceLocation locationIn) {
		return getEntityTranslucentZOffset(locationIn, true);
	}
}
