package com.arka.fantasia.entity.goblin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import org.checkerframework.checker.nullness.qual.NonNull;

public class EntityGoblin extends Monster {

    public long lastAttackTimer;
    public int attackLength;
    public static final EntityDataAccessor<Integer> STATE = SynchedEntityData.defineId(TestGoblinEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(GoblinBruteEntity.class, EntityDataSerializers.BOOLEAN);


    protected EntityGoblin(EntityType<? extends EntityGoblin> type, Level worldIn) {
        super(type, worldIn);
    }

    public void performRangedAttack(LivingEntity target, float pullProgress) {
    }

    @Override
    public boolean shouldDespawnInPeaceful() {
        return true;
    }

    //ENTITY DATA
    public int getState() {
        return this.entityData.get(STATE);
    }

    public void setState(int state) {
        this.entityData.set(STATE, state);
    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    public void setAttacking(boolean value) {
        this.entityData.set(ATTACKING, value);
    }

    public void setLastAttackTimer() {
        this.lastAttackTimer = this.level.getGameTime();
    }

    public void setAttackLength(int length) {
        this.attackLength = length;
    }

    public boolean isAttackEnd() {
        return(this.level.getGameTime() - this.lastAttackTimer > attackLength);
    }


    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setState(compound.getInt("State"));
        this.setAttacking(compound.getBoolean("Attacking"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("State", this.getState());
        tag.putBoolean("Attacking", this.isAttacking());
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(STATE, 0);
        this.entityData.define(ATTACKING, false);
    }

    @NonNull
    @Override
    public MobType getMobType() {
        return MobType.UNDEFINED;
    }
}
