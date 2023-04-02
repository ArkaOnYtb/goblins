package com.arka.fantasia.client.renderer.entity;

import com.arka.fantasia.client.model.entity.TestEntityModel;
import com.arka.fantasia.entity.goblin.TestGoblinEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class TestEntityGeoRenderer extends GeoEntityRenderer<TestGoblinEntity> {

    public TestEntityGeoRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new TestEntityModel());
    }

    @Override
    public RenderType getRenderType(TestGoblinEntity animatable, float partialTicks, PoseStack stack, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }

    @Override
    protected float getDeathMaxRotation(TestGoblinEntity entity) {
        return 0f;
    }

}
