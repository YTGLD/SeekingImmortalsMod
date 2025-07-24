package com.ytgld.seeking_immortals.test_entity;

import com.ytgld.seeking_immortals.init.Particles;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class lotus_entity extends ThrowableItemProjectile {
    public boolean canSee = true;
    public int live = 30;
    public int r = 255;
    public int g = 61;
    public int b = 255;
    public int alpha = 150;
    public float size = 1;

    public lotus_entity(EntityType<? extends lotus_entity> entityType, Level level) {
        super(entityType, level);
    }
    public float lightSize= 0;
    public int followerAnimationTime = 0;
    public int follower = 0;
    public boolean isFollower = false;


    public boolean isBoom = false;
    public int boomAnimationTime = 0;


    @Override
    public void tick() {
        if (this.getOwner()!=null) {

            setNoGravity(true);

            if (isFollower) {
                Vec3 playerPos = this.position().add(0, 0.75, 0);
                float range = 8;
                List<LivingEntity> itemEntities =
                        this.level().getEntitiesOfClass(LivingEntity.class,
                                new AABB(playerPos.x - range,
                                        playerPos.y - range,
                                        playerPos.z - range,
                                        playerPos.x + range,
                                        playerPos.y + range,
                                        playerPos.z + range));
                for (LivingEntity item : itemEntities) {
                    if (!item.is(this.getOwner())) {
                        Vec3 direction = playerPos.subtract(item.position().add(0,-1.5,0));
                        direction = direction.normalize().scale(0.25F);
                        item.setDeltaMovement(item.getDeltaMovement().add(direction));
                    }
                }
            }


            super.tick();
            follower++;
            if (follower >= 10) {
                isFollower = true;
            }

            if (follower == 10) {
                this.level().addParticle(ParticleTypes.SONIC_BOOM, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
                this.level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.TRIAL_SPAWNER_OMINOUS_ACTIVATE, SoundSource.NEUTRAL, 2, 2);
            }
            if (isFollower) {
                lightSize += 1 * 2f / 20f;
            }
            if (follower == 200) {
                isBoom = true;
                isFollower = false;
            }
            if (isBoom) {
                boomAnimationTime++;
                if (lightSize > 0) {
                    lightSize -= 1 * 20f / 20f;
                }
                if (alpha > 0) {
                    alpha -= 3;
                }
                size += 0.01f;
            }
            if (boomAnimationTime == 20) {
                canSee = false;
                Vec3 playerPos = this.position();
                float range = 5;
                this.level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.WARDEN_SONIC_BOOM, SoundSource.NEUTRAL, 2, 2);
                List<LivingEntity> entities =
                        this.level().getEntitiesOfClass(LivingEntity.class,
                                new AABB(playerPos.x - range,
                                        playerPos.y - range,
                                        playerPos.z - range,
                                        playerPos.x + range,
                                        playerPos.y + range,
                                        playerPos.z + range));
                for (LivingEntity living : entities) {
                    if (this.getOwner() != null && !living.is(this.getOwner())) {
                        if (this.getOwner() instanceof Player player) {
                            if (this.tickCount > 15) {
                                living.invulnerableTime = 0;
                                living.hurt(living.damageSources().magic(), (float) (40 + player.getAttributeValue(Attributes.ATTACK_DAMAGE)*2));
                                living.setRemainingFireTicks(200);
                                this.level().addParticle(ParticleTypes.SONIC_BOOM, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
                                canSee = false;
                            }
                        }
                    }
                }
            }
            if (isFollower) {
                if (this.tickCount % 10 == 1) {
                    this.level().addParticle(Particles.cube.get(), this.getX(), this.getY(), this.getZ(), 0, Mth.nextFloat(RandomSource.create(), 0, 0.1f), 0);
                    Vec3 playerPos = this.position();
                    float range = 5;
                    List<LivingEntity> entities =
                            this.level().getEntitiesOfClass(LivingEntity.class,
                                    new AABB(playerPos.x - range,
                                            playerPos.y - range,
                                            playerPos.z - range,
                                            playerPos.x + range,
                                            playerPos.y + range,
                                            playerPos.z + range));

                    for (LivingEntity living : entities){
                        if (this.getOwner() != null && !living.is(this.getOwner())) {
                            if (this.getOwner() instanceof Player player) {
                                living.invulnerableTime = 0;
                                living.hurt(living.damageSources().magic(), (float) (player.getAttributeValue(Attributes.ATTACK_DAMAGE)*0.25f));
                                living.level().addParticle(Particles.blood.get(), living.getX(), living.getY(), living.getZ(), 0, Mth.nextFloat(RandomSource.create(), 0, 0.1f), 0);
                                return;
                            }
                        }
                    }
                }
            }
            if (!canSee) {
                live--;
            }
            if (live <= 0) {
                this.discard();
            }
        }
    }

    @Override
    public void playerTouch(Player player) {
    }

    protected @NotNull Item getDefaultItem() {
        return Items.IRON_SWORD;
    }

}