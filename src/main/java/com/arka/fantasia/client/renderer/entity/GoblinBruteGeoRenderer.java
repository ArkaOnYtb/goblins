package com.arka.fantasia.client.renderer.entity;

import com.arka.fantasia.client.model.entity.GoblinBruteModel;
import com.arka.fantasia.entity.goblin.GoblinBruteEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GoblinBruteGeoRenderer extends GeoEntityRenderer<GoblinBruteEntity> {

    public GoblinBruteGeoRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new GoblinBruteModel());
    }

    @Override
    public RenderType getRenderType(GoblinBruteEntity animatable, float partialTicks, PoseStack stack, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }
}
