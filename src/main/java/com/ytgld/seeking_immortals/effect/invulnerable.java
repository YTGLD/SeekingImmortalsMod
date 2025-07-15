package com.ytgld.seeking_immortals.effect;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class invulnerable extends MobEffect {
    public invulnerable() {
        super(MobEffectCategory.HARMFUL, 0xffff00ff);
    }
}

