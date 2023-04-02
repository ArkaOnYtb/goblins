package com.arka.fantasia.entity.goblin;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class GoblinBruteEntity extends Animal implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);

    public static final EntityDataAccessor<Integer> STATE = SynchedEntityData.defineId(GoblinBruteEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> SPIN_ANIM = SynchedEntityData.defineId(GoblinBruteEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Boolean> SPIN_ATTACK = SynchedEntityData.defineId(GoblinBruteEntity.class, EntityDataSerializers.BOOLEAN);

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {


        if(event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.goblinbrute.walk", true));
            return PlayState.CONTINUE;
        }

        if(this.swinging) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.goblinbrute.attack1", false));
            return PlayState.CONTINUE;
        }

        if(this.dead) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.goblinbrute.death", false));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.goblinbrute.idle", true));
        return PlayState.CONTINUE;
    }

    public GoblinBruteEntity(EntityType<? extends Animal> type, Level worldIn) {
        super(type, worldIn);
        this.noCulling = true;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<GoblinBruteEntity>(this, "controller", 0,this::predicate));

    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        return null;
    }

}
