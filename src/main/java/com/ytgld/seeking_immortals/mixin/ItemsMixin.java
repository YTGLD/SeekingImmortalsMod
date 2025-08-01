package com.ytgld.seeking_immortals.mixin;

import com.moonstone.moonstonemod.init.items.Items;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import com.ytgld.seeking_immortals.item.nightmare.disintegrating_stone;
import com.ytgld.seeking_immortals.item.nightmare.element.yoke;
import com.ytgld.seeking_immortals.item.nightmare.eye;
import com.ytgld.seeking_immortals.item.nightmare.*;
import com.ytgld.seeking_immortals.item.nightmare.base.*;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.*;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.eye.*;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.fool.*;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.insight.*;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.redemption.*;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.reversal.*;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.start.*;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.stone.*;

import java.util.function.Supplier;

@Mixin(Items.class)
public abstract class ItemsMixin {
    @Final
    @Shadow public static DeferredRegister<Item> REGISTRY;

    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/neoforged/neoforge/registries/DeferredRegister;register(Ljava/lang/String;Ljava/util/function/Supplier;)Lnet/neoforged/neoforge/registries/DeferredHolder;"))
    private static DeferredHolder<Item, ?> redirectEctoplasmApple(DeferredRegister<Item> instance, String name, Supplier<? extends Item> sup) {
        return seekingImmortals$replaceItem(instance, name, sup);
    }
    @Unique
    private static DeferredHolder<Item, ?> seekingImmortals$replaceItem(DeferredRegister<Item> instance, String name, Supplier<? extends Item> sup) {
        return switch (name) {
            case "nightmare_base" -> instance.register(name, nightmare_base::new);
            case "nightmare_base_black_eye_eye" -> instance.register(name, nightmare_base_black_eye_eye::new);
            case "nightmare_base_black_eye_heart" -> instance.register(name, nightmare_base_black_eye_heart::new);
            case "nightmare_base_black_eye_red" -> instance.register(name, nightmare_base_black_eye_red::new);
            case "nightmare_base_stone" -> instance.register(name, nightmare_base_stone::new);
            case "nightmare_base_stone_meet" -> instance.register(name, nightmare_base_stone_meet::new);
            case "nightmare_base_stone_virus" -> instance.register(name, nightmare_base_stone_virus::new);
            case "nightmare_base_stone_brain" -> instance.register(name, nightmare_base_stone_brain::new);
            case "nightmare_virus" -> instance.register(name, nightmare_virus::new);
            case "nightmare_base_reversal" -> instance.register(name, nightmare_base_reversal::new);
            case "nightmare_base_reversal_orb" -> instance.register(name, nightmare_base_reversal_orb::new);
            case "nightmare_base_reversal_card" -> instance.register(name, nightmare_base_reversal_card::new);
            case "nightmare_base_reversal_mysterious" -> instance.register(name, nightmare_base_reversal_mysterious::new);
            case "nightmare_base_redemption" -> instance.register(name, nightmare_base_redemption::new);
            case "nightmare_base_redemption_deception" -> instance.register(name, nightmare_base_redemption_deception::new);
            case "nightmare_base_redemption_degenerate" -> instance.register(name, nightmare_base_redemption_degenerate::new);
            case "nightmare_base_redemption_down_and_out" -> instance.register(name, nightmare_base_redemption_down_and_out::new);
            case "nightmare_base_fool" -> instance.register(name, nightmare_base_fool::new);
            case "nightmare_base_fool_soul" -> instance.register(name, nightmare_base_fool_soul::new);
            case "nightmare_base_fool_bone" -> instance.register(name, nightmare_base_fool_bone::new);
            case "nightmare_base_fool_betray" -> instance.register(name, nightmare_base_fool_betray::new);
            case "nightmare_base_insight" -> instance.register(name, nightmare_base_insight::new);
            case "nightmare_base_insight_drug" -> instance.register(name, nightmare_base_insight_drug::new);
            case "nightmare_base_insight_insane" -> instance.register(name, nightmare_base_insight_insane::new);
            case "nightmare_base_insight_collapse" -> instance.register(name, nightmare_base_insight_collapse::new);
            case "nightmare_base_start" -> instance.register(name, nightmare_base_start::new);
            case "nightmare_base_start_pod" -> instance.register(name, nightmare_base_start_pod::new);
            case "nightmare_base_start_egg" -> instance.register(name, nightmare_base_start_egg::new);
            case "end_bone" -> instance.register(name, end_bone::new);
            case "candle" -> instance.register(name, candle::new);
            case "apple" -> instance.register(name, apple::new);
            case "ring" -> instance.register(name, ring::new);
            case "immortal" -> instance.register(name, immortal::new);
            case "hypocritical_self_esteem" -> instance.register(name, hypocritical_self_esteem::new);
            case "wolf" -> instance.register(name, wolf::new);
            case "eye" -> instance.register(name, eye::new);
            case "falling_immortals" -> instance.register(name, falling_immortals::new);
            case "disintegrating_stone" -> instance.register(name, disintegrating_stone::new);
            case "muddy_jewels" -> instance.register(name, muddy_jewels::new);
            case "defend_against_runestone" -> instance.register(name, defend_against_runestone::new);
            case "revive_runestone" -> instance.register(name, revive_runestone::new);
            case "strengthen_runestone" -> instance.register(name, strengthen_runestone::new);
            case "hidden_blade" -> instance.register(name, hidden_blade::new);
            case "yoke" -> instance.register(name, yoke::new);
            case "blood_god" -> instance.register(name, blood_god::new);
            case "lotus" -> instance.register(name, lotus::new);
            case "bone_or_god" -> instance.register(name, bone_or_god::new);
            case "nightmare_base_start_power" -> instance.register(name, nightmare_base_start_power::new);
            case "tricky_puppets" -> instance.register(name, tricky_puppets::new);


            default -> instance.register(name, sup);
        };
    }
}