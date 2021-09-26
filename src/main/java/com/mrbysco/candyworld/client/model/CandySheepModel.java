package com.mrbysco.candyworld.client.model;

import com.mrbysco.candyworld.entity.CandySheepEntity;
import net.minecraft.client.renderer.entity.model.QuadrupedModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class CandySheepModel<T extends CandySheepEntity> extends QuadrupedModel<T> {
    private float headRotationAngleX;

    public CandySheepModel() {
        super(12, 0.0F, false, 8.0F, 4.0F, 2.0F, 2.0F, 24);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-3.0F, -4.0F, -6.0F, 6, 6, 8, 0.0F);
        this.head.setPos(0.0F, 6.0F, -8.0F);
        this.body = new ModelRenderer(this, 28, 8);
        this.body.addBox(-4.0F, -10.0F, -7.0F, 8, 16, 6, 0.0F);
        this.body.setPos(0.0F, 5.0F, 2.0F);
    }

    @Override
    public void prepareMobModel(T candySheep, float limbSwing, float limbSwingAmount, float partialTickTime) {
        super.prepareMobModel(candySheep, limbSwing, limbSwingAmount, partialTickTime);
        this.head.y = 6.0F + candySheep.getHeadRotationPointY(partialTickTime) * 9.0F;
        this.headRotationAngleX = candySheep.getHeadRotationAngleX(partialTickTime);
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.head.xRot = this.headRotationAngleX;
    }
}