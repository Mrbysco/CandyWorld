package com.mrbysco.candyworld.client.model;

import com.mrbysco.candyworld.entity.CandySheepEntity;
import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class CandySheepFlossModel<T extends CandySheepEntity> extends QuadrupedModel<T> {
	private float headRotationAngleX;

	public CandySheepFlossModel(ModelPart part) {
		super(part, false, 8.0F, 4.0F, 2.0F, 2.0F, 24);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = QuadrupedModel.createBodyMesh(12, CubeDeformation.NONE);
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("head", CubeListBuilder.create()
						.texOffs(0, 0)
						.addBox(-3.0F, -4.0F, -3.85F, 6.0F, 6.0F, 8.0F, new CubeDeformation(1.2F)),
				PartPose.offset(0.0F, 6.0F, -8.0F));
		partdefinition.addOrReplaceChild("body", CubeListBuilder.create()
						.texOffs(28, 8)
						.addBox(-4.0F, -9.0F, -9.0F, 8, 16, 6, new CubeDeformation(2.4F)),
				PartPose.offsetAndRotation(0.0F, 5.0F, 2.0F, ((float) Math.PI / 2F), 0.0F, 0.0F));

		CubeListBuilder cubelistbuilder = CubeListBuilder.create()
				.texOffs(0, 16)
				.addBox(-2.0F, 2.0F, -2.0F, 4, 6, 4, new CubeDeformation(0.8F));
		partdefinition.addOrReplaceChild("right_hind_leg", cubelistbuilder, PartPose.offset(-3.0F, 12.0F, 7.0F));
		partdefinition.addOrReplaceChild("left_hind_leg", cubelistbuilder, PartPose.offset(3.0F, 12.0F, 7.0F));
		partdefinition.addOrReplaceChild("right_front_leg", cubelistbuilder, PartPose.offset(-3.0F, 12.0F, -5.0F));
		partdefinition.addOrReplaceChild("left_front_leg", cubelistbuilder, PartPose.offset(3.0F, 12.0F, -5.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void prepareMobModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
		super.prepareMobModel(entityIn, limbSwing, limbSwingAmount, partialTick);
		this.head.y = 6.0F + entityIn.getHeadRotationPointY(partialTick) * 9.0F;
		this.headRotationAngleX = entityIn.getHeadRotationAngleX(partialTick);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		this.head.xRot = this.headRotationAngleX;
	}
}