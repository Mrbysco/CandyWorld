package com.mrbysco.candyworld.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mrbysco.candyworld.client.CustomRenderType;
import com.mrbysco.candyworld.entity.GummyMouseEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.math.MathHelper;

public class GummyMouseOuterModel<T extends GummyMouseEntity> extends EntityModel<T> {
	public final ModelRenderer body;
	public final ModelRenderer head;
	public final ModelRenderer tail;
	public final ModelRenderer earLeft;
	public final ModelRenderer earRight;

	public GummyMouseOuterModel() {
		super(CustomRenderType::getEntityTranslucentZOffset);
		this.texWidth = 32;
		this.texHeight = 32;

		this.body = new ModelRenderer(this, 0, 0);
		this.body.setPos(0.0F, 21.0F, -3.0F);
		this.body.addBox(-2.0F, 0.0F, 0.0F, 4.0F, 3.0F, 6.0F, 0.0F);
		this.head = new ModelRenderer(this, 0, 9);
		this.head.setPos(0.0F, 1.0F, 0.0F);
		this.head.addBox(-1.5F, 0.0F, -3.0F, 3.0F, 2.0F, 3.0F, 0.0F);
		this.earLeft = new ModelRenderer(this, 0, 14);
		this.earLeft.setPos(0.3F, -0.6F, -2.5F);
		this.earLeft.addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 2.0F, 0.0F);
		this.earRight = new ModelRenderer(this, 0, 17);
		this.earRight.setPos(-0.3F, -0.6F, -2.5F);
		this.earRight.addBox(-1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 2.0F, 0.0F);
		this.tail = new ModelRenderer(this, 0, 14);
		this.tail.setPos(0.0F, 1.0F, 6.0F);
		this.tail.addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 6.0F, 0.0F);
		this.body.addChild(this.head);
		this.head.addChild(this.earLeft);
		this.head.addChild(this.earRight);
		this.body.addChild(this.tail);
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		this.body.render(matrixStackIn, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY, red, green, blue, alpha);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.tail.yRot = MathHelper.sin(ageInTicks * 0.6F) * (float) Math.PI * 0.04F;
	}
}
