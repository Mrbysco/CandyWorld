package com.mrbysco.candyworld.client.renderer;

import com.mrbysco.candyworld.client.model.CandySheepModel;
import com.mrbysco.candyworld.client.renderer.layers.LayerCandySheepWool;
import com.mrbysco.candyworld.entity.CandySheepEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.ParametersAreNonnullByDefault;

@OnlyIn(Dist.CLIENT)
public class CandySheepRenderer extends MobRenderer<CandySheepEntity, CandySheepModel<CandySheepEntity>> {
    private static final ResourceLocation SHEARED_SHEEP_TEXTURES = new ResourceLocation(com.mrbysco.candyworld.CandyWorld.MOD_ID, "textures/entity/candy_sheep/candy_sheep.png");

    public CandySheepRenderer(EntityRendererManager rendermanagerIn) {
        super(rendermanagerIn, new CandySheepModel(), 0.7F);
        this.addLayer(new LayerCandySheepWool(this));
    }

    @Override
    @ParametersAreNonnullByDefault
    public ResourceLocation getTextureLocation(CandySheepEntity entity) {
        return SHEARED_SHEEP_TEXTURES;
    }
}