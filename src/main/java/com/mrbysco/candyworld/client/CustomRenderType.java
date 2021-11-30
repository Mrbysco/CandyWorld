package com.mrbysco.candyworld.client;

import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat.Mode;
import com.mrbysco.candyworld.CandyWorld;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class CustomRenderType extends RenderType {
	public CustomRenderType(String nameIn, VertexFormat formatIn, Mode drawModeIn, int bufferSizeIn, boolean useDelegateIn, boolean needsSortingIn, Runnable setupTaskIn, Runnable clearTaskIn) {
		super(nameIn, formatIn, drawModeIn, bufferSizeIn, useDelegateIn, needsSortingIn, setupTaskIn, clearTaskIn);
	}

	public static RenderType getEntityTranslucentZOffset(ResourceLocation LocationIn, boolean outlineIn) {
		RenderType.CompositeState rendertype$compositestate =
				RenderType.CompositeState.builder()
						.setTextureState(new RenderStateShard.TextureStateShard(LocationIn, false, false))
						.setTransparencyState(new RenderStateShard.TransparencyStateShard("translucent_transparency", () -> {
							RenderSystem.enableBlend();
							RenderSystem.blendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA,
									SourceFactor.ZERO, DestFactor.ONE_MINUS_SRC_ALPHA);
						}, () -> {
							RenderSystem.disableBlend();
							RenderSystem.defaultBlendFunc();
						}))
						.setShaderState(RENDERTYPE_ENTITY_CUTOUT_NO_CULL_SHADER)
						.setTextureState(new RenderStateShard.TextureStateShard(LocationIn, false, false))
						.setTransparencyState(NO_TRANSPARENCY)
						.setCullState(NO_CULL)
						.setLightmapState(LIGHTMAP)
						.setOverlayState(OVERLAY)
						.createCompositeState(outlineIn);

		return create(CandyWorld.MOD_ID + ":entity_translucent_z_offset", DefaultVertexFormat.NEW_ENTITY, Mode.QUADS, 256, true, true, rendertype$compositestate);
	}

	public static RenderType getEntityTranslucentZOffset(ResourceLocation locationIn) {
		return getEntityTranslucentZOffset(locationIn, true);
	}
}
