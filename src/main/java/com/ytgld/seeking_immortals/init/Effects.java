package com.ytgld.seeking_immortals.init;

import com.ytgld.seeking_immortals.effect.*;
import com.ytgld.seeking_immortals.SeekingImmortalsMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Effects {
    public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, SeekingImmortalsMod.MODID);
    public static final DeferredHolder<MobEffect,?> dead  =REGISTRY.register("dead",()->new dead());
    public static final DeferredHolder<MobEffect,?> erode  =REGISTRY.register("erode",()->new erode());

}
