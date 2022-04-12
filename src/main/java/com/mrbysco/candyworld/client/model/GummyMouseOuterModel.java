package com.mrbysco.candyworld.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mrbysco.candyworld.client.CustomRenderType;
import com.mrbysco.candyworld.entity.GummyMouseEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;

public class GummyMouseOuterModel<T extends GummyMouseEntity> extends EntityModel<T> {
	public final ModelPart body;
	public final ModelPart head;
	public final ModelPart tail;
	public final ModelPart earLeft;
	public final ModelPart earRight;

	public GummyMouseOuterModel(ModelPart root) {
		super(CustomRenderType::getEntityTranslucentZOffset);

		this.body = root.getChild("body");
		this.head = body.getChild("head");
		this.earLeft = body.getChild("left_ear");
		this.earRight = body.getChild("right_ear");
		this.tail = body.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bodyDefinition = partdefinition.addOrReplaceChild("body", CubeListBuilder.create()
						.texOffs(0, 0).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 3.0F, 6.0F),
				PartPose.offset(0.0F, 21.0F, -3.0F));

		bodyDefinition.addOrReplaceChild("head", CubeListBuilder.create()
						.texOffs(0, 9).addBox(-1.5F, 0.0F, -3.0F, 3.0F, 2.0F, 3.0F),
				PartPose.offset(0.0F, 1.0F, 0.0F));

		bodyDefinition.addOrReplaceChild("left_ear", CubeListBuilder.create()
						.texOffs(0, 14).addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 2.0F),
				PartPose.offset(0.3F, -0.6F, -2.5F));

		bodyDefinition.addOrReplaceChild("right_ear", CubeListBuilder.create()
						.texOffs(0, 17).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 2.0F),
				PartPose.offset(-0.3F, -0.6F, -2.5F));

		bodyDefinition.addOrReplaceChild("tail", CubeListBuilder.create()
						.texOffs(0, 14).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 6.0F),
				PartPose.offset(0.0F, 1.0F, 6.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		this.body.render(poseStack, vertexConsumer, packedLightIn, OverlayTexture.NO_OVERLAY, red, green, blue, alpha);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.tail.yRot = Mth.sin(ageInTicks * 0.6F) * (float) Math.PI * 0.04F;
	}
}
