package com.mrbysco.candyworld.client.renderer.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mrbysco.candyworld.CandyWorld;
import com.mrbysco.candyworld.client.model.CandySheepFlossModel;
import com.mrbysco.candyworld.client.model.CandySheepModel;
import com.mrbysco.candyworld.entity.CandySheepEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LayerCandySheepWool extends LayerRenderer<CandySheepEntity, CandySheepModel<CandySheepEntity>> {
	private static final ResourceLocation TEXTURE = new ResourceLocation(CandyWorld.MOD_ID, "textures/entity/candy_sheep/candy_sheep_fur.png");
	private final CandySheepFlossModel<CandySheepEntity> sheepModel = new CandySheepFlossModel<>();

	public LayerCandySheepWool(IEntityRenderer<CandySheepEntity, CandySheepModel<CandySheepEntity>> rendererIn) {
		super(rendererIn);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, CandySheepEntity candySheep, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		if (!candySheep.getSheared() && !candySheep.isInvisible()) {
			coloredCutoutModelCopyLayerRender(this.getParentModel(), this.sheepModel, TEXTURE, matrixStackIn, bufferIn, packedLightIn, candySheep, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, 1.0F, 1.0F, 1.0F);
		}
	}
}
