package com.arka.fantasia.entity.ai.goal.goblin;

import com.arka.fantasia.entity.goblin.TestGoblinEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;

public class GoblinBruteSpinAttack extends Goal {

    protected final TestGoblinEntity attacker;
    private Path path;
    private float radius;
    private int damage;
    private long lastCanUseCheck;
    private static final long COOLDOWN = 100;


    public GoblinBruteSpinAttack(TestGoblinEntity attacker, int damage, float radius) {
        this.attacker = attacker;
        this.damage = damage;
        this.radius = radius;
    }

    public boolean canUse() {
        if(!this.attacker.isAttacking()) {

            long i = this.attacker.level.getGameTime();
            if(i - this.lastCanUseCheck < this.COOLDOWN) {
                return false;
            }
            else {

                this.lastCanUseCheck = i;
                LivingEntity livingEntity = this.attacker.getTarget();
                if(livingEntity == null || !livingEntity.isAlive()) {
                    return false;
                }
                else {

                    return this.getAttackReachSqr(livingEntity) >= this.attacker.distanceToSqr(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ());

                }
            }

        }
        else {
            return false;
        }

    }


/*    public List<Player> getEntities(Level world, double x, double y, double z, double radius) {
        return world.getEntitiesOfClass(Player.class, new AABB(x,0,z,x+1,257, z+1).deflate(radius));
    }*/

    public void start() {
        this.attacker.setState(2);
        this.attacker.setAttacking(true);
        this.attacker.setSpinAttack(true);
        final Vec3 center = new Vec3(attacker.position().x, attacker.position().y, attacker.position().z);
        this.attacker.getNavigation().moveTo(this.path, 1);
        if(attacker.getTarget().position().distanceTo(center) <= 2) {
            attacker.getTarget().hurt(DamageSource.GENERIC, damage);
        }
    }


    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public void tick() {
        LivingEntity livingEntity = this.attacker.getTarget();
        if(livingEntity != null) {
            double distanceToTargetSqr = this.attacker.distanceToSqr(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ());
        }

    }

    private double getAttackReachSqr(LivingEntity target) {
        return this.attacker.getBbWidth() * 2.0F * this.attacker.getBbWidth() * 2.0F + target.getBbWidth();
    }

}
