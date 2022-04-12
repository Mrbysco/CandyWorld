package com.mrbysco.candyworld.client.renderer;

import com.google.common.collect.Maps;
import com.mrbysco.candyworld.CandyWorld;
import com.mrbysco.candyworld.client.ClientHandler;
import com.mrbysco.candyworld.client.model.GummyMouseModel;
import com.mrbysco.candyworld.client.renderer.layers.LayerMouseGummy;
import com.mrbysco.candyworld.entity.GummyMouseEntity;
import com.mrbysco.candyworld.enums.EnumGummy;
import net.minecraft.Util;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class GummyMouseRenderer extends MobRenderer<GummyMouseEntity, GummyMouseModel<GummyMouseEntity>> {
	private static final Map<EnumGummy, ResourceLocation> GUMMY_MOUSE_TEXTURES = Util.make(Maps.newEnumMap(EnumGummy.class), (location) -> {
		location.put(EnumGummy.RED, new ResourceLocation(CandyWorld.MOD_ID, "textures/entity/gummy_mouse/red_gummy_mouse.png"));
		location.put(EnumGummy.ORANGE, new ResourceLocation(CandyWorld.MOD_ID, "textures/entity/gummy_mouse/orange_gummy_mouse.png"));
		location.put(EnumGummy.YELLOW, new ResourceLocation(CandyWorld.MOD_ID, "textures/entity/gummy_mouse/yellow_gummy_mouse.png"));
		location.put(EnumGummy.WHITE, new ResourceLocation(CandyWorld.MOD_ID, "textures/entity/gummy_mouse/white_gummy_mouse.png"));
		location.put(EnumGummy.GREEN, new ResourceLocation(CandyWorld.MOD_ID, "textures/entity/gummy_mouse/green_gummy_mouse.png"));
	});

	public GummyMouseRenderer(EntityRendererProvider.Context context) {
		super(context, new GummyMouseModel(context.bakeLayer(ClientHandler.GUMMY_MOUSE)), 0.25F);
		this.addLayer(new LayerMouseGummy(this, context.getModelSet()));
	}

	@Override
	public ResourceLocation getTextureLocation(GummyMouseEntity entity) {
		return GUMMY_MOUSE_TEXTURES.getOrDefault(entity.getColor(), GUMMY_MOUSE_TEXTURES.get(EnumGummy.RED));
	}
}
