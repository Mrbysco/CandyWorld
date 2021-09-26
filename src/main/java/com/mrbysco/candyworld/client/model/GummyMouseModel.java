package com.mrbysco.candyworld.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mrbysco.candyworld.entity.GummyMouseEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.math.MathHelper;

public class GummyMouseModel<T extends GummyMouseEntity> extends EntityModel<T> {
    public final ModelRenderer body_1;
    public final ModelRenderer tail_1;
    public final ModelRenderer head_1;

    public GummyMouseModel() {
//        super(CustomRenderType::getEntityTranslucentZOffset);
        this.texWidth = 32;
        this.texHeight = 32;

        this.tail_1 = new ModelRenderer(this, 18, 8);
        this.tail_1.setPos(0.0F, 1.0F, 6.0F);
        this.tail_1.addBox(-0.5F, 0.0F, -1.5F, 1.0F, 1.0F, 6.0F, 0.0F);
        this.body_1 = new ModelRenderer(this, 16, 15);
        this.body_1.setPos(0, 21.5F, -2.5F);
        this.body_1.addBox(-1.5F, 0.0F, 0.0F, 3.0F, 2.0F, 5.0F, 0.0F);
        this.head_1 = new ModelRenderer(this, 22, 4);
        this.head_1.setPos(0.0F, 1.0F, 0.0F);
        this.head_1.addBox(-1.0F, 0.0F, -3.0F, 2.0F, 1.0F, 3.0F, 0.0F);
        this.body_1.addChild(this.tail_1);
        this.body_1.addChild(this.head_1);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        this.body_1.render(matrixStackIn, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY, red, green, blue, alpha);
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.body_1.setPos(0, 21.5F, -2.5F);

        this.tail_1.yRot = MathHelper.sin(ageInTicks * 0.6F + 0.3F) * (float) Math.PI * 0.03F;
        this.body_1.y += MathHelper.sin(ageInTicks * 0.05F) * 0.04F;
        this.body_1.x += MathHelper.sin(ageInTicks * 0.6F + 0.3F) * 0.004F;
    }
}
