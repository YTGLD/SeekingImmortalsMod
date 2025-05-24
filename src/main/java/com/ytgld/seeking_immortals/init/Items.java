package com.ytgld.seeking_immortals.init;

import com.ytgld.seeking_immortals.SeekingImmortalsMod;
import com.ytgld.seeking_immortals.item.nightmare.immortal;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.*;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.eye.*;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.fool.*;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.insight.*;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.redemption.*;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.reversal.*;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.start.*;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.stone.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Items {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(BuiltInRegistries.ITEM, SeekingImmortalsMod.MODID);
    public static final DeferredHolder<Item,?> nightmare_base_black_eye =REGISTRY.register("nightmare_base_black_eye", nightmare_base_black_eye::new);
    public static final DeferredHolder<Item,?> nightmare_base =REGISTRY.register("nightmare_base", nightmare_base::new);

    public static final DeferredHolder<Item,?> nightmare_base_black_eye_eye =REGISTRY.register("nightmare_base_black_eye_eye", nightmare_base_black_eye_eye::new);
    public static final DeferredHolder<Item,?> nightmare_base_black_eye_heart =REGISTRY.register("nightmare_base_black_eye_heart", nightmare_base_black_eye_heart::new);

    public static final DeferredHolder<Item,?> nightmare_base_black_eye_red =REGISTRY.register("nightmare_base_black_eye_red", nightmare_base_black_eye_red::new);
    public static final DeferredHolder<Item,?> nightmare_base_stone =REGISTRY.register("nightmare_base_stone", nightmare_base_stone::new);
    public static final DeferredHolder<Item,?> nightmare_base_stone_meet =REGISTRY.register("nightmare_base_stone_meet", nightmare_base_stone_meet::new);

    public static final DeferredHolder<Item,?> nightmare_base_stone_virus =REGISTRY.register("nightmare_base_stone_virus", nightmare_base_stone_virus::new);
    public static final DeferredHolder<Item,?> nightmare_base_stone_brain =REGISTRY.register("nightmare_base_stone_brain", nightmare_base_stone_brain::new);

    public static final DeferredHolder<Item,?> nightmare_virus =REGISTRY.register("nightmare_virus", nightmare_virus::new);
    public static final DeferredHolder<Item,?> nightmare_base_reversal =REGISTRY.register("nightmare_base_reversal", nightmare_base_reversal::new);

    public static final DeferredHolder<Item,?> nightmare_base_reversal_orb =REGISTRY.register("nightmare_base_reversal_orb", nightmare_base_reversal_orb::new);
    public static final DeferredHolder<Item,?> nightmare_base_reversal_card =REGISTRY.register("nightmare_base_reversal_card", nightmare_base_reversal_card::new);
    public static final DeferredHolder<Item,?> nightmare_base_reversal_mysterious =REGISTRY.register("nightmare_base_reversal_mysterious", nightmare_base_reversal_mysterious::new);

    public static final DeferredHolder<Item,?> nightmare_base_redemption =REGISTRY.register("nightmare_base_redemption", nightmare_base_redemption::new);
    public static final DeferredHolder<Item,?> nightmare_base_redemption_deception =REGISTRY.register("nightmare_base_redemption_deception", nightmare_base_redemption_deception::new);
    public static final DeferredHolder<Item,?> nightmare_base_redemption_degenerate =REGISTRY.register("nightmare_base_redemption_degenerate", nightmare_base_redemption_degenerate::new);
    public static final DeferredHolder<Item,?> nightmare_base_redemption_down_and_out =REGISTRY.register("nightmare_base_redemption_down_and_out", nightmare_base_redemption_down_and_out::new);
    public static final DeferredHolder<Item,?> nightmare_base_fool =REGISTRY.register("nightmare_base_fool", nightmare_base_fool::new);
    public static final DeferredHolder<Item,?> nightmare_base_fool_soul =REGISTRY.register("nightmare_base_fool_soul", nightmare_base_fool_soul::new);
    public static final DeferredHolder<Item,?> nightmare_base_fool_bone =REGISTRY.register("nightmare_base_fool_bone", nightmare_base_fool_bone::new);
    public static final DeferredHolder<Item,?> nightmare_base_fool_betray =REGISTRY.register("nightmare_base_fool_betray", nightmare_base_fool_betray::new);
    public static final DeferredHolder<Item,?> nightmare_base_insight =REGISTRY.register("nightmare_base_insight", nightmare_base_insight::new);
    public static final DeferredHolder<Item,?> nightmare_base_insight_drug =REGISTRY.register("nightmare_base_insight_drug", nightmare_base_insight_drug::new);
    public static final DeferredHolder<Item,?> nightmare_base_insight_insane =REGISTRY.register("nightmare_base_insight_insane", nightmare_base_insight_insane::new);
    public static final DeferredHolder<Item,?> nightmare_base_insight_collapse =REGISTRY.register("nightmare_base_insight_collapse", nightmare_base_insight_collapse::new);
    public static final DeferredHolder<Item,?> nightmare_base_start =REGISTRY.register("nightmare_base_start", nightmare_base_start::new);
    public static final DeferredHolder<Item,?> nightmare_base_start_pod =REGISTRY.register("nightmare_base_start_pod", nightmare_base_start_pod::new);
    public static final DeferredHolder<Item,?> nightmare_base_start_egg =REGISTRY.register("nightmare_base_start_egg", nightmare_base_start_egg::new);
    public static final DeferredHolder<Item,?> end_bone =REGISTRY.register("end_bone", end_bone::new);
    public static final DeferredHolder<Item,?> candle =REGISTRY.register("candle", candle::new);
    public static final DeferredHolder<Item,?> apple =REGISTRY.register("apple", apple::new);
    public static final DeferredHolder<Item,?> ring =REGISTRY.register("ring", ring::new);
    public static final DeferredHolder<Item,?> immortal =REGISTRY.register("immortal", immortal::new);
    public static final DeferredHolder<Item,?> hypocritical_self_esteem =REGISTRY.register("hypocritical_self_esteem", hypocritical_self_esteem::new);
    public static final DeferredHolder<Item,?> wolf =REGISTRY.register("wolf", wolf::new);

    public static final DeferredHolder<Item,?> nightmare_base_start_power =REGISTRY.register("nightmare_base_start_power", nightmare_base_start_power::new);
    public static final DeferredHolder<Item,?> tricky_puppets =REGISTRY.register("tricky_puppets", tricky_puppets::new);

}
