package com.mrbysco.candyworld.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mrbysco.candyworld.client.CustomRenderType;
import com.mrbysco.candyworld.entity.GummyBearEntity;
import net.minecraft.client.renderer.entity.model.QuadrupedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class GummyBearModel<T extends GummyBearEntity> extends QuadrupedModel<T> {
	private final ModelRenderer leg4Outer;
	private final ModelRenderer bodyOuter;
	private final ModelRenderer leg3Outer;
	private final ModelRenderer leg1Outer;
	private final ModelRenderer leg2Outer;
	private final ModelRenderer headOuter;

	public GummyBearModel() {
		super(12, 0.0F, true, 16.0F, 4.0F, 2.25F, 2.0F, 24);
		this.renderType = CustomRenderType::getEntityTranslucentZOffset;

		this.texWidth = 128;
		this.texHeight = 96;

		this.head = new ModelRenderer(this);
		this.head.setPos(0.0F, 10.0F, -16.0F);
		this.head.texOffs(0, 0).addBox(-3.5F, -3.0F, -3.0F, 7.0F, 7.0F, 7.0F, 0.0F, false);

		this.headOuter = new ModelRenderer(this);
		this.headOuter.setPos(0.0F, 10.0F, -16.0F);
		this.headOuter.texOffs(13, 49).addBox(-4.5F, -4.0F, -4.0F, 9.0F, 9.0F, 9.0F, 0.0F, false);
		this.headOuter.texOffs(0, 44).addBox(-2.5F, 1.0F, -6.0F, 5.0F, 3.0F, 3.0F, 0.0F, false);
		this.headOuter.texOffs(26, 0).addBox(3.0F, -4.5F, -1.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		this.headOuter.texOffs(26, 0).addBox(-5.0F, -4.5F, -1.0F, 2.0F, 2.0F, 1.0F, 0.0F, true);

		this.body = new ModelRenderer(this);
		this.body.setPos(2.0F, 9.0F, 12.0F);
		this.body.texOffs(0, 19).addBox(-9.0F, -13.0F, -7.0F, 14.0F, 14.0F, 11.0F, 0.0F, false);
		this.body.texOffs(39, 0).addBox(-8.0F, -25.0F, -7.0F, 12.0F, 12.0F, 10.0F, 0.0F, false);

		this.bodyOuter = new ModelRenderer(this);
		this.bodyOuter.setPos(2.0F, 9.0F, 12.0F);
		this.bodyOuter.texOffs(58, 70).addBox(-9.0F, -26.0F, -8.0F, 14.0F, 13.0F, 12.0F, 0.0F, false);
		this.bodyOuter.texOffs(0, 67).addBox(-10.0F, -14.0F, -8.0F, 16.0F, 15.0F, 13.0F, 0.0F, false);

		this.leg0 = new ModelRenderer(this, 50, 22);
		this.leg0.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 8.0F, 0.0F);
		this.leg0.setPos(-3.5F, 14.0F, 6.0F);
		this.leg1 = new ModelRenderer(this, 50, 22);
		this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 8.0F, 0.0F);
		this.leg1.setPos(3.5F, 14.0F, 6.0F);
		this.leg2 = new ModelRenderer(this, 50, 40);
		this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 6.0F, 0.0F);
		this.leg2.setPos(-2.5F, 14.0F, -7.0F);
		this.leg3 = new ModelRenderer(this, 50, 40);
		this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 6.0F, 0.0F);
		this.leg3.setPos(2.5F, 14.0F, -7.0F);

		this.leg1Outer = new ModelRenderer(this);
		this.leg1Outer.setPos(-3.5F, 14.0F, 6.0F);
		this.leg1Outer.texOffs(100, 32).addBox(-2.5F, -1.0F, -2.5F, 5.0F, 11.0F, 9.0F, 0.0F, false);
		this.leg2Outer = new ModelRenderer(this);
		this.leg2Outer.setPos(3.5F, 14.0F, 6.0F);
		this.leg2Outer.texOffs(100, 32).addBox(-2.5F, -1.0F, -2.5F, 5.0F, 11.0F, 9.0F, 0.0F, false);
		this.leg3Outer = new ModelRenderer(this);
		this.leg3Outer.setPos(-2.5F, 14.0F, -8.0F);
		this.leg3Outer.texOffs(104, 52).addBox(-2.5F, -1.0F, -2.5F, 5.0F, 11.0F, 7.0F, 0.0F, false);
		this.leg4Outer = new ModelRenderer(this);
		this.leg4Outer.setPos(2.5F, 14.0F, -8.0F);
		this.leg4Outer.texOffs(104, 52).addBox(-2.5F, -1.0F, -2.5F, 5.0F, 11.0F, 7.0F, 0.0F, false);

		--this.leg0.x;
		--this.leg1Outer.x;
		++this.leg1.x;
		++this.leg2Outer.x;
		--this.leg2.x;
		--this.leg3Outer.x;
		++this.leg3.x;
		++this.leg4Outer.x;
		--this.leg2.z;
		--this.leg3Outer.z;
		--this.leg3.z;
		--this.leg4Outer.z;
	}

	@Override
	protected Iterable<ModelRenderer> bodyParts() {
		return ImmutableList.of(this.body, this.leg0, this.leg1, this.leg2, this.leg3);
	}

	protected Iterable<ModelRenderer> getOuterParts() {
		return ImmutableList.of(this.bodyOuter, this.leg1Outer, this.leg2Outer, this.leg3Outer, this.leg4Outer, this.headOuter);
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		if (this.young) {
			matrixStackIn.pushPose();
			if (this.scaleHead) {
				float f = 1.5F / this.babyHeadScale;
				matrixStackIn.scale(f, f, f);
			}

			matrixStackIn.translate(0.0D, (double)(this.yHeadOffset / 16.0F), (double)(this.zHeadOffset / 16.0F));
			this.headParts().forEach((modelRenderer) -> modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
			matrixStackIn.popPose();
			matrixStackIn.pushPose();
			float f1 = 1.0F / this.babyBodyScale;
			matrixStackIn.scale(f1, f1, f1);
			matrixStackIn.translate(0.0D, (double)(this.bodyYOffset / 16.0F), 0.0D);
			this.bodyParts().forEach((modelRenderer) -> modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
			this.getOuterParts().forEach((modelRenderer) -> modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, 0.8F));
			matrixStackIn.popPose();
		} else {
			this.headParts().forEach((modelRenderer) -> modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
			this.bodyParts().forEach((modelRenderer) -> modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
			this.getOuterParts().forEach((modelRenderer) -> modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, 0.8F));
		}
	}


	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		this.head.xRot = headPitch * 0.017453292F;
        this.head.yRot = netHeadYaw * 0.017453292F;
        this.body.xRot = ((float) Math.PI / 2F);
        this.leg1.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leg0.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.leg3.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.leg2.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

        this.headOuter.xRot = this.head.xRot;
        this.headOuter.yRot = this.head.yRot;
        this.bodyOuter.xRot = this.body.xRot;
        this.leg1Outer.xRot = this.leg1.xRot;
        this.leg2Outer.xRot = this.leg0.xRot;
        this.leg3Outer.xRot = this.leg3.xRot;
        this.leg4Outer.xRot = this.leg2.xRot;

		float f = ageInTicks - (float)entityIn.tickCount;
		float f1 = entityIn.getStandingAnimationScale(f);
		f1 = f1 * f1;
		float f2 = 1.0F - f1;

		this.body.xRot = ((float)Math.PI / 2F) - f1 * (float)Math.PI * 0.35F;
        this.bodyOuter.xRot = this.body.xRot;

		this.body.y = 9.0F * f2 + 11.0F * f1;
        this.bodyOuter.y = this.body.y;

		this.leg2.z = -8.0F * f2 - 4.0F * f1;
		this.leg2.xRot -= f1 * (float)Math.PI * 0.45F;
		this.leg3.y = this.leg2.y;
		this.leg3.z = this.leg2.z;
		this.leg3.xRot -= f1 * (float)Math.PI * 0.45F;

        this.leg3Outer.y = this.leg3.y;
        this.leg3Outer.z = this.leg3.z;
        this.leg3Outer.xRot = this.leg3.xRot;
        this.leg4Outer.y = this.leg2.y;
        this.leg4Outer.z = this.leg2.z;
        this.leg4Outer.xRot = this.leg2.xRot;

		if (this.young) {
			this.head.y = 10.0F * f2 - 9.0F * f1;
			this.head.z = -16.0F * f2 - 7.0F * f1;
		} else {
			this.head.y = 10.0F * f2 - 14.0F * f1;
			this.head.z = -16.0F * f2 - 3.0F * f1;
		}
        this.headOuter.y = this.head.y;
        this.headOuter.z = this.head.z;

        this.head.xRot += f1 * (float) Math.PI * 0.15F;
        this.headOuter.xRot = this.head.xRot;

		this.head.y += MathHelper.sin(ageInTicks * 0.04F) * 0.3F;
		this.body.y += MathHelper.sin(ageInTicks * 0.04F + (float) Math.PI / 2) * 0.3F;
	}
}