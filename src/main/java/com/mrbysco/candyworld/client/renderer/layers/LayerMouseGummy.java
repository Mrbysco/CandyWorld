package com.mrbysco.candyworld.client.renderer.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mrbysco.candyworld.client.ClientHandler;
import com.mrbysco.candyworld.client.CustomRenderType;
import com.mrbysco.candyworld.client.model.GummyMouseModel;
import com.mrbysco.candyworld.client.model.GummyMouseOuterModel;
import com.mrbysco.candyworld.entity.GummyMouseEntity;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LayerMouseGummy extends RenderLayer<GummyMouseEntity, GummyMouseModel<GummyMouseEntity>> {
	private final GummyMouseOuterModel<GummyMouseEntity> gummyMouseOuterModel;

	public LayerMouseGummy(RenderLayerParent<GummyMouseEntity, GummyMouseModel<GummyMouseEntity>> rendererIn, EntityModelSet modelSet) {
		super(rendererIn);
		this.gummyMouseOuterModel = new GummyMouseOuterModel<>(modelSet.bakeLayer(ClientHandler.GUMMY_MOUSE_OUTER));
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLightIn, GummyMouseEntity gummyMouse, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		if (!gummyMouse.isInvisible()) {
			this.getParentModel().copyPropertiesTo(this.gummyMouseOuterModel);
			this.gummyMouseOuterModel.prepareMobModel(gummyMouse, limbSwing, limbSwingAmount, partialTicks);
			this.gummyMouseOuterModel.setupAnim(gummyMouse, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			VertexConsumer vertexConsumer = bufferSource.getBuffer(CustomRenderType.getEntityTranslucentZOffset(this.getTextureLocation(gummyMouse)));
			this.gummyMouseOuterModel.renderToBuffer(poseStack, vertexConsumer, packedLightIn, LivingEntityRenderer.getOverlayCoords(gummyMouse, 0.0F), 1.0F, 1.0F, 1.0F, 0.6F);
		}
	}
}
