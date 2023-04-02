package com.arka.fantasia.client.model.entity;

import com.arka.fantasia.Fantasia;
import com.arka.fantasia.entity.goblin.GoblinBruteEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GoblinBruteModel extends AnimatedGeoModel<GoblinBruteEntity> {

    @Override
    public ResourceLocation getAnimationFileLocation(GoblinBruteEntity entity) {
        return new ResourceLocation(Fantasia.ModID, "animations/goblinbrute.animation.json");
    }

    @Override
    public ResourceLocation getModelLocation(GoblinBruteEntity entity) {
        return new ResourceLocation(Fantasia.ModID, "geo/goblinbrute.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GoblinBruteEntity entity) {
        return new ResourceLocation(Fantasia.ModID, "textures/models/entity/goblinbrute.png");
    }

}
