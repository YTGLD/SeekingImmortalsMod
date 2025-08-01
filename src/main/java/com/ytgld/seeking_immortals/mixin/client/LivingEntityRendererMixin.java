package com.ytgld.seeking_immortals.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.ytgld.seeking_immortals.client.StrengtheningLayer;
import com.ytgld.seeking_immortals.init.Effects;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin<T extends LivingEntity, M extends EntityModel<T>> extends EntityRenderer<T> implements RenderLayerParent<T, M> {
    protected LivingEntityRendererMixin(EntityRendererProvider.Context p_174008_) {
        super(p_174008_);
    }
    @Shadow public abstract boolean addLayer(RenderLayer<T, M> p_115327_);

    @Shadow protected M model;
    @Inject(at = @At("RETURN"), method = "<init>")
    public void init(EntityRendererProvider.Context context, M model, float Radius, CallbackInfo info) {
//        addLayer(new StrengtheningLayer<>((LivingEntityRenderer<?, ?>) (Object) this));
    }

}
