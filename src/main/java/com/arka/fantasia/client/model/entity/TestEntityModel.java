package com.arka.fantasia.client.model.entity;

import com.arka.fantasia.Fantasia;
import com.arka.fantasia.entity.goblin.TestGoblinEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TestEntityModel extends AnimatedGeoModel<TestGoblinEntity> {

    @Override
    public ResourceLocation getAnimationFileLocation(TestGoblinEntity entity) {
        return new ResourceLocation(Fantasia.ModID, "animations/goblinbrute.animation.json");
    }

    @Override
    public ResourceLocation getModelLocation(TestGoblinEntity entity) {
        return new ResourceLocation(Fantasia.ModID, "geo/goblinbrute.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(TestGoblinEntity entity) {
        return new ResourceLocation(Fantasia.ModID, "textures/models/entity/goblinbrute.png");
    }

}
