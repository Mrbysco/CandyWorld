package com.mrbysco.candyworld.client.renderer.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mrbysco.candyworld.client.CustomRenderType;
import com.mrbysco.candyworld.client.model.GummyMouseModel;
import com.mrbysco.candyworld.client.model.GummyMouseOuterModel;
import com.mrbysco.candyworld.entity.GummyMouseEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LayerMouseGummy extends LayerRenderer<GummyMouseEntity, GummyMouseModel<GummyMouseEntity>> {
    private final GummyMouseOuterModel<GummyMouseEntity> gummyMouseOuterModel = new GummyMouseOuterModel<>();

    public LayerMouseGummy(IEntityRenderer<GummyMouseEntity, GummyMouseModel<GummyMouseEntity>> rendererIn) {
        super(rendererIn);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, GummyMouseEntity gummyMouse, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!gummyMouse.isInvisible()) {
            this.getParentModel().copyPropertiesTo(this.gummyMouseOuterModel);
            this.gummyMouseOuterModel.prepareMobModel(gummyMouse, limbSwing, limbSwingAmount, partialTicks);
            this.gummyMouseOuterModel.setupAnim(gummyMouse, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            IVertexBuilder ivertexbuilder = bufferIn.getBuffer(CustomRenderType.getEntityTranslucentZOffset(this.getTextureLocation(gummyMouse)));
            this.gummyMouseOuterModel.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, LivingRenderer.getOverlayCoords(gummyMouse, 0.0F), 1.0F, 1.0F, 1.0F, 0.6F);
        }
    }
}
