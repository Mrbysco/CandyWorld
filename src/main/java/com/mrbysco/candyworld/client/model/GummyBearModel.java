package com.mrbysco.candyworld.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mrbysco.candyworld.client.CustomRenderType;
import com.mrbysco.candyworld.entity.GummyBearEntity;
import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

public class GummyBearModel<T extends GummyBearEntity> extends QuadrupedModel<T> {
	private final ModelPart headOuter;
	private final ModelPart bodyOuter;
	private final ModelPart rightHindLegOuter;
	private final ModelPart leftHindLegOuter;
	private final ModelPart rightFrontLegOuter;
	private final ModelPart leftFrontLegOuter;

	public GummyBearModel(ModelPart root) {
		super(root, true, 16.0F, 4.0F, 2.25F, 2.0F, 24);
		this.renderType = CustomRenderType::getEntityTranslucentZOffset;
		this.headOuter = root.getChild("head_outer");
		this.bodyOuter = root.getChild("body_outer");
		this.rightHindLegOuter = root.getChild("right_hind_leg_outer");
		this.leftHindLegOuter = root.getChild("left_hind_leg_outer");
		this.rightFrontLegOuter = root.getChild("right_front_leg_outer");
		this.leftFrontLegOuter = root.getChild("left_front_leg_outer");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("head", CubeListBuilder.create()
						.texOffs(0, 0).addBox(-3.5F, -3.0F, -3.0F, 7.0F, 7.0F, 7.0F),
				PartPose.offset(0.0F, 10.0F, -16.0F));

		partdefinition.addOrReplaceChild("head_outer", CubeListBuilder.create()
						.texOffs(13, 49).addBox(-4.5F, -4.0F, -4.0F, 9.0F, 9.0F, 9.0F)
						.texOffs(0, 44).addBox("mouth", -2.5F, 1.0F, -6.0F, 5.0F, 3.0F, 3.0F)
						.texOffs(26, 0).addBox("right_ear", -5.0F, -4.5F, -1.0F, 2.0F, 2.0F, 1.0F)
						.texOffs(26, 0).mirror().addBox("left_ear", 3.0F, -4.5F, -1.0F, 2.0F, 2.0F, 1.0F),
				PartPose.offset(0.0F, 10.0F, -16.0F));

		partdefinition.addOrReplaceChild("body", CubeListBuilder.create()
						.texOffs(0, 19).addBox(-9.0F, -13.0F, -7.0F, 14.0F, 14.0F, 11.0F)
						.texOffs(39, 0).addBox(-8.0F, -25.0F, -7.0F, 12.0F, 12.0F, 10.0F),
				PartPose.offsetAndRotation(2.0F, 9.0F, 12.0F, ((float) Math.PI / 2F), 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("body_outer", CubeListBuilder.create()
						.texOffs(58, 70).addBox(-9.0F, -26.0F, -8.0F, 14.0F, 13.0F, 12.0F)
						.texOffs(0, 67).addBox(-10.0F, -14.0F, -8.0F, 16.0F, 15.0F, 13.0F),
				PartPose.offsetAndRotation(2.0F, 9.0F, 12.0F, ((float) Math.PI / 2F), 0.0F, 0.0F));

		CubeListBuilder cubelistbuilder = CubeListBuilder.create()
				.texOffs(50, 22).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 8.0F);

		partdefinition.addOrReplaceChild("right_hind_leg", cubelistbuilder,
				PartPose.offset(-3.5F, 14.0F, 6.0F));
		partdefinition.addOrReplaceChild("left_hind_leg", cubelistbuilder,
				PartPose.offset(3.5F, 14.0F, 6.0F));
		CubeListBuilder cubelistbuilder1 = CubeListBuilder.create()
				.texOffs(50, 40).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 6.0F);
		partdefinition.addOrReplaceChild("right_front_leg", cubelistbuilder1,
				PartPose.offset(-2.5F, 14.0F, -7.0F));
		partdefinition.addOrReplaceChild("left_front_leg", cubelistbuilder1,
				PartPose.offset(2.5F, 14.0F, -7.0F));

		CubeListBuilder cubelistbuilder2 = CubeListBuilder.create()
				.texOffs(100, 32).addBox(-2.5F, -1.0F, -2.5F, 5.0F, 11.0F, 9.0F);
		partdefinition.addOrReplaceChild("right_hind_leg_outer", cubelistbuilder2,
				PartPose.offset(-3.5F, 14.0F, 6.0F));
		partdefinition.addOrReplaceChild("left_hind_leg_outer", cubelistbuilder2,
				PartPose.offset(3.5F, 14.0F, 6.0F));
		CubeListBuilder cubelistbuilder3 = CubeListBuilder.create()
				.texOffs(104, 52).addBox(-2.5F, -1.0F, -2.5F, 5.0F, 11.0F, 7.0F);
		partdefinition.addOrReplaceChild("right_front_leg_outer", cubelistbuilder3,
				PartPose.offset(-2.5F, 14.0F, -8.0F));
		partdefinition.addOrReplaceChild("left_front_leg_outer", cubelistbuilder3,
				PartPose.offset(2.5F, 14.0F, -8.0F));

		return LayerDefinition.create(meshdefinition, 128, 96);
	}

	protected Iterable<ModelPart> getOuterParts() {
		return ImmutableList.of(this.bodyOuter, this.rightHindLegOuter, this.leftHindLegOuter, this.rightFrontLegOuter, this.leftFrontLegOuter, this.headOuter);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		if (this.young) {
			poseStack.pushPose();
			if (this.scaleHead) {
				float f = 1.5F / this.babyHeadScale;
				poseStack.scale(f, f, f);
			}

			poseStack.translate(0.0D, (double) (this.babyYHeadOffset / 16.0F), (double) (this.babyZHeadOffset / 16.0F));
			this.headParts().forEach((modelRenderer) -> modelRenderer.render(poseStack, vertexConsumer, packedLightIn, packedOverlayIn, red, green, blue, alpha));
			poseStack.popPose();
			poseStack.pushPose();
			float f1 = 1.0F / this.babyBodyScale;
			poseStack.scale(f1, f1, f1);
			poseStack.translate(0.0D, (double) (this.bodyYOffset / 16.0F), 0.0D);
			this.bodyParts().forEach((modelRenderer) -> modelRenderer.render(poseStack, vertexConsumer, packedLightIn, packedOverlayIn, red, green, blue, alpha));
			this.getOuterParts().forEach((modelRenderer) -> modelRenderer.render(poseStack, vertexConsumer, packedLightIn, packedOverlayIn, red, green, blue, 0.8F));
			poseStack.popPose();
		} else {
			this.headParts().forEach((modelRenderer) -> modelRenderer.render(poseStack, vertexConsumer, packedLightIn, packedOverlayIn, red, green, blue, alpha));
			this.bodyParts().forEach((modelRenderer) -> modelRenderer.render(poseStack, vertexConsumer, packedLightIn, packedOverlayIn, red, green, blue, alpha));
			this.getOuterParts().forEach((modelRenderer) -> modelRenderer.render(poseStack, vertexConsumer, packedLightIn, packedOverlayIn, red, green, blue, 0.8F));
		}
	}


	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		this.head.xRot = headPitch * 0.017453292F;
		this.head.yRot = netHeadYaw * 0.017453292F;
		this.body.xRot = ((float) Math.PI / 2F);
		this.leftHindLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.rightHindLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.leftFrontLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.rightFrontLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

		this.headOuter.xRot = this.head.xRot;
		this.headOuter.yRot = this.head.yRot;
		this.bodyOuter.xRot = this.body.xRot;
		this.leftHindLegOuter.xRot = this.leftHindLeg.xRot;
		this.rightHindLegOuter.xRot = this.rightHindLeg.xRot;
		this.leftFrontLegOuter.xRot = this.leftFrontLeg.xRot;
		this.rightFrontLegOuter.xRot = this.rightFrontLeg.xRot;

		float f = ageInTicks - (float) entityIn.tickCount;
		float f1 = entityIn.getStandingAnimationScale(f);
		f1 = f1 * f1;
		float f2 = 1.0F - f1;

		this.body.xRot = ((float) Math.PI / 2F) - f1 * (float) Math.PI * 0.35F;
		this.bodyOuter.xRot = this.body.xRot;

		this.body.y = 9.0F * f2 + 11.0F * f1;
		this.bodyOuter.y = this.body.y;

		this.rightFrontLeg.z = -8.0F * f2 - 4.0F * f1;
		this.rightFrontLeg.xRot -= f1 * (float) Math.PI * 0.45F;
		this.leftFrontLeg.y = this.rightFrontLeg.y;
		this.leftFrontLeg.z = this.rightFrontLeg.z;
		this.leftFrontLeg.xRot -= f1 * (float) Math.PI * 0.45F;

		this.leftFrontLegOuter.y = this.leftFrontLeg.y;
		this.leftFrontLegOuter.z = this.leftFrontLeg.z;
		this.leftFrontLegOuter.xRot = this.leftFrontLeg.xRot;
		this.rightFrontLegOuter.y = this.rightFrontLeg.y;
		this.rightFrontLegOuter.z = this.rightFrontLeg.z;
		this.rightFrontLegOuter.xRot = this.rightFrontLeg.xRot;

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

		this.head.y += Mth.sin(ageInTicks * 0.04F) * 0.3F;
		this.body.y += Mth.sin(ageInTicks * 0.04F + (float) Math.PI / 2) * 0.3F;
	}
}