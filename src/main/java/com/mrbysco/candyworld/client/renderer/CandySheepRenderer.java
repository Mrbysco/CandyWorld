package com.mrbysco.candyworld.client.renderer;

import com.mrbysco.candyworld.CandyWorld;
import com.mrbysco.candyworld.client.ClientHandler;
import com.mrbysco.candyworld.client.model.CandySheepModel;
import com.mrbysco.candyworld.client.renderer.layers.LayerCandySheepWool;
import com.mrbysco.candyworld.entity.CandySheepEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.ParametersAreNonnullByDefault;

@OnlyIn(Dist.CLIENT)
public class CandySheepRenderer extends MobRenderer<CandySheepEntity, CandySheepModel<CandySheepEntity>> {
	private static final ResourceLocation SHEARED_SHEEP_TEXTURES = new ResourceLocation(CandyWorld.MOD_ID, "textures/entity/candy_sheep/candy_sheep.png");

	public CandySheepRenderer(EntityRendererProvider.Context context) {
		super(context, new CandySheepModel(context.bakeLayer(ClientHandler.CANDY_SHEEP)), 0.7F);
		this.addLayer(new LayerCandySheepWool(this, context.getModelSet()));
	}

	@Override
	@ParametersAreNonnullByDefault
	public ResourceLocation getTextureLocation(CandySheepEntity candySheep) {
		return SHEARED_SHEEP_TEXTURES;
	}
}