package com.arka.fantasia.entity.goblin;

import com.arka.fantasia.entity.ai.goal.goblin.GoblinBruteSpinAttack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class TestGoblinEntity extends EntityGoblin implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);


    public static final EntityDataAccessor<Integer> STATE = SynchedEntityData.defineId(TestGoblinEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> SPIN_ANIM = SynchedEntityData.defineId(GoblinBruteEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Boolean> SPIN_ATTACK = SynchedEntityData.defineId(GoblinBruteEntity.class, EntityDataSerializers.BOOLEAN);
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {


        if(event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.goblinbrute.walk", true));
            return PlayState.CONTINUE;
        }

        if(this.dead) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.goblinbrute.death", false));
            return PlayState.CONTINUE;
        }

        if(entityData.get(STATE) == 2) {
            try {
                wait(1000);
                this.setState(0);
            } catch (Exception e) {
                System.out.println(e);
            }
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.goblinbrute.attack2", true));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.goblinbrute.idle", true));
        return PlayState.CONTINUE;
    }

    private <E extends IAnimatable> PlayState predicate2(AnimationEvent<E> event) {
        if(this.swinging && entityData.get(STATE) != 2) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.goblinbrute.attack1", false));
            return PlayState.CONTINUE;
        }
        event.getController().clearAnimationCache();
        return PlayState.STOP;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0,this::predicate));
        data.addAnimationController(new AnimationController<>(this, "controller2", 0,this::predicate2));

    }

    public TestGoblinEntity(EntityType<? extends EntityGoblin> type, Level worldIn) {
        super(type, worldIn);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2, false));
        this.goalSelector.addGoal(2, new GoblinBruteSpinAttack(this, 8, 3));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 10.0f));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(4, new RandomStrollGoal(this, 1));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, Player.class, true));

    }

    public static AttributeSupplier.Builder createAttributes() {
        return LivingEntity.createLivingAttributes()
            .add(Attributes.MAX_HEALTH, 30.0D)
            .add(Attributes.FOLLOW_RANGE, 35.0D)
            .add(Attributes.MOVEMENT_SPEED, 0.25D)
            .add(Attributes.ATTACK_DAMAGE, 3.0D)
            .add(Attributes.ARMOR, 2.0D)
            .add(Attributes.ATTACK_KNOCKBACK, 1.0D)
            .add(Attributes.ATTACK_SPEED, 2.0D);
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public void baseTick() {
        super.baseTick();
        if(this.entityData.get(SPIN_ATTACK) == true) {
            this.entityData.set(SPIN_ANIM, this.entityData.get(SPIN_ANIM) + 1);
        }
        if(this.entityData.get(SPIN_ANIM) >= 20) {
            this.entityData.set(SPIN_ATTACK, false);
            this.entityData.set(SPIN_ANIM, 0);
            this.entityData.set(STATE, 0);
        }

        /*if(isAttackEnd()) {
            setAttacking(false);
        }*/
    }

    public MobType getMobType() {
        return MobType.UNDEFINED;
    }

    public int getState() {
        return this.entityData.get(STATE);
    }

    public void setState(int state) {
        this.entityData.set(STATE, state);
    }

    public boolean getSpinAttack() {
        return this.entityData.get(SPIN_ATTACK);
    }

    public void setSpinAttack(boolean value) {
        this.entityData.set(SPIN_ATTACK, value);
    }

    public int getSpinAnim() {
        return this.entityData.get(SPIN_ANIM);
    }

    public void setSpinAnim(int value) {
        this.entityData.set(SPIN_ANIM, value);
    }


    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setState(compound.getInt("State"));
        this.setSpinAnim(compound.getInt("SpinAttackAnim"));
        this.setSpinAttack(compound.getBoolean("SpinAttackAttack"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("State", this.getState());
        tag.putInt("SpinAttackAnim", this.getSpinAnim());
        tag.putBoolean("SpinAttackAttack", this.getSpinAttack());
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(STATE, 0);
        this.entityData.define(SPIN_ANIM, 0);
        this.entityData.define(SPIN_ATTACK, false);
    }

}
