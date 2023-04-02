package com.arka.fantasia.entity.ai.goal.goblin;

import com.arka.fantasia.entity.goblin.EntityGoblin;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.pathfinder.Path;

import java.util.EnumSet;

//Thanks to MCPECommander :)
public class CustomMeleeAttack extends Goal {
    protected final EntityGoblin attacker;
    private final double speedMultiplier;
    private final boolean followingTargetEvenIfNotSeen;
    private int ticksUntilNextAttack;
    private Path path;
    private long lastCanUseCheck;
    private static final long COOLDOWN_BETWEEN_CAN_USE_CHECKS = 20;


    public CustomMeleeAttack(EntityGoblin attacker, double speedMultiplier, boolean followWhenUnseen) {
        this.attacker = attacker;
        this.speedMultiplier = speedMultiplier;
        this.followingTargetEvenIfNotSeen = followWhenUnseen;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean canUse() {

        long i = this.attacker.level.getGameTime();

        if (i - this.lastCanUseCheck < COOLDOWN_BETWEEN_CAN_USE_CHECKS) {
            return false;
        }
        else {

            this.lastCanUseCheck = i;
            LivingEntity livingentity = this.attacker.getTarget();

            if (livingentity == null || !livingentity.isAlive()) {
                return false;
            } else {
                this.path = this.attacker.getNavigation().createPath(livingentity, 0);
                if (this.path != null) {
                    return true;
                } else {

                    return this.getAttackReachSqr(livingentity) >= this.attacker.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
                }

            }
        }
    }

    public boolean canContinueToUse() {

        LivingEntity livingentity = this.attacker.getTarget();
        if (livingentity == null || !livingentity.isAlive()) {
            return false;
        } else if (!this.followingTargetEvenIfNotSeen) {
            return !this.attacker.getNavigation().isDone();
        } else {
            return !(livingentity instanceof Player) || !livingentity.isSpectator() && !((Player) livingentity).isCreative();
        }
    }

    public void start() {
        this.attacker.getNavigation().moveTo(this.path, this.speedMultiplier);
        this.attacker.setAggressive(true);
        this.ticksUntilNextAttack = 0;
    }

    public void stop() {
        LivingEntity livingentity = this.attacker.getTarget();
        if (!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(livingentity)) {
            this.attacker.setTarget(null);
        }

        this.attacker.setAggressive(false);
        this.attacker.getNavigation().stop();
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public void tick() {
        LivingEntity livingentity = this.attacker.getTarget();
        if (livingentity != null) {
            this.attacker.getLookControl().setLookAt(livingentity, 30.0F, 30.0F);
            double distanceToTargetSqr = this.attacker.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());

            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
            this.checkAndPerformAttack(livingentity, distanceToTargetSqr);
        }
    }
    protected void checkAndPerformAttack(LivingEntity target, double distanceToTargetSqr) {
        if(!this.attacker.isAttacking()) {

            double attackReachSqr = this.getAttackReachSqr(target);
            if(distanceToTargetSqr < 8){
                this.attacker.swing(InteractionHand.MAIN_HAND);
                this.attacker.setAttacking(true);
                this.attacker.setLastAttackTimer();
                this.attacker.setAttackLength(10);
            }
            if (distanceToTargetSqr <= attackReachSqr && this.ticksUntilNextAttack <= 0) {
                this.ticksUntilNextAttack = 20;
                this.attacker.doHurtTarget(target);
            }

        }

    }
    private double getAttackReachSqr(LivingEntity target) {
        return this.attacker.getBbWidth() * 2.0F * this.attacker.getBbWidth() * 2.0F + target.getBbWidth();
    }
}