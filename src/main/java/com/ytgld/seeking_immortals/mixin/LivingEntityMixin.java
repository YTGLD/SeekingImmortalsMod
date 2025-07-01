package com.ytgld.seeking_immortals.mixin;

import com.ytgld.seeking_immortals.Handler;
import com.ytgld.seeking_immortals.init.Items;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow public abstract double getAttributeValue(Holder<Attribute> attribute);

    @Shadow public abstract double getAttributeBaseValue(Holder<Attribute> attribute);

    @Inject(at = @At("RETURN"), method = "getMaxHealth", cancellable = true)
    private void SeekingImmortalscreateAttributes(CallbackInfoReturnable<Float> cir){
        LivingEntity living = (LivingEntity) (Object) this;
        if (Handler.hascurio(living, Items.apple.get())){
            cir.setReturnValue(30f);
        }
        if (Handler.hascurio(living, Items.falling_immortals.get())){
            cir.setReturnValue(30f);
        }
    }
    @Inject(at = @At("RETURN"), method = "getArmorValue", cancellable = true)
    private void getArmorValue(CallbackInfoReturnable<Integer> cir){
        LivingEntity living = (LivingEntity) (Object) this;
        if (Handler.hascurio(living, Items.falling_immortals.get())){
            cir.setReturnValue(10);
        }

    }
    @Inject(at = @At("RETURN"), method = "getAttributeValue", cancellable = true)
    private void getAttributeValue(Holder<Attribute> attribute, CallbackInfoReturnable<Double> cir) {
        LivingEntity living = (LivingEntity) (Object) this;
        if (Handler.hascurio(living, Items.falling_immortals.get())){
            cir.setReturnValue(this.getAttributeBaseValue(attribute));
        }

    }
}
