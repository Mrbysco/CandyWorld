package com.mrbysco.candyworld.client.renderer.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mrbysco.candyworld.CandyWorld;
import com.mrbysco.candyworld.client.ClientHandler;
import com.mrbysco.candyworld.client.model.CandySheepFlossModel;
import com.mrbysco.candyworld.client.model.CandySheepModel;
import com.mrbysco.candyworld.entity.CandySheepEntity;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LayerCandySheepWool extends RenderLayer<CandySheepEntity, CandySheepModel<CandySheepEntity>> {
	private static final ResourceLocation TEXTURE = new ResourceLocation(CandyWorld.MOD_ID, "textures/entity/candy_sheep/candy_sheep_fur.png");
	private final CandySheepFlossModel<CandySheepEntity> sheepModel;

	public LayerCandySheepWool(RenderLayerParent<CandySheepEntity, CandySheepModel<CandySheepEntity>> rendererIn, EntityModelSet modelSet) {
		super(rendererIn);
		this.sheepModel = new CandySheepFlossModel<>(modelSet.bakeLayer(ClientHandler.CANDY_SHEEP_FLOSS));
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLightIn, CandySheepEntity candySheep, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		if (!candySheep.getSheared() && !candySheep.isInvisible()) {
			coloredCutoutModelCopyLayerRender(this.getParentModel(), this.sheepModel, TEXTURE, poseStack, bufferSource, packedLightIn, candySheep, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, 1.0F, 1.0F, 1.0F);
		}
	}
}
