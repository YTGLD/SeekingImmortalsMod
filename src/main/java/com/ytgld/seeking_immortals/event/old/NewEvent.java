package com.ytgld.seeking_immortals.event.old;

import com.ytgld.seeking_immortals.Handler;
import com.ytgld.seeking_immortals.SeekingImmortalsMod;
import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.init.AttReg;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.*;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.INightmare;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.eye.nightmare_base_black_eye_eye;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.eye.nightmare_base_black_eye_heart;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.eye.nightmare_base_black_eye_red;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.fool.apple;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.fool.nightmare_base_fool_bone;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.insight.nightmare_base_insight_insane;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.redemption.nightmare_base_redemption_deception;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.nightmare_base_reversal;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.reversal.candle;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.reversal.nightmare_base_reversal_orb;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.start.nightmare_base_start_pod;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.stone.end_bone;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.stone.nightmare_base_stone_brain;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.stone.nightmare_base_stone_virus;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderTooltipEvent;
import net.neoforged.neoforge.common.util.TriState;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.CriticalHitEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.event.CurioCanEquipEvent;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.Map;

public class NewEvent {


    @SubscribeEvent
    public void LivingHealEvent(LivingHealEvent event) {
        nightmare_base_reversal_orb.LivingHealEvent(event);
        nightmare_base_black_eye_heart.heal(event);
        candle.heal(event);
        if (event.getEntity() instanceof LivingEntity living){
            if (living.getAttribute(AttReg.heal)!=null){
                float attack = (float) living.getAttribute(AttReg.heal).getValue();
                event.setAmount(event.getAmount()*(attack));
            }
        }
    }

    @SubscribeEvent
    public void LivingHealEvent(LivingDeathEvent event) {
        nightmare_base_reversal.LivingDeathEvent(event);

        nightmare_base_black_eye_red.kill(event);
        nightmare_base_insight_insane.LivingDeathEvents(event);

    }

    @SubscribeEvent
    public void LivingHurtEvent(LivingIncomingDamageEvent event){

        nightmare_base_stone_virus.h(event);
        nightmare_base_black_eye_eye.attLook(event);
        nightmare_base_black_eye_heart.hurt(event);
        nightmare_base_stone.LivingHurtEvent(event);
        nightmare_base_stone_brain.hurts(event);
        nightmare_base_redemption_deception.LivingIncomingDamageEvent(event);
        nightmare_base_fool_bone.attLook(event);
        nightmare_base_insight_insane.damage(event);
        nightmare_base_start.damage(event);
        nightmare_base_start_pod.damage(event);
        end_bone.hurts(event);
        candle.hurt(event);
        apple.damage(event);

        if (event.getAmount()>Integer.MAX_VALUE){
            event.setAmount(Integer.MAX_VALUE);
        }
        CuriosApi.getCuriosInventory(event.getEntity()).ifPresent(handler -> {
            Map<String, ICurioStacksHandler> curios = handler.getCurios();
            for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                ICurioStacksHandler stacksHandler = entry.getValue();
                IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                for (int i = 0; i < stacksHandler.getSlots(); i++) {
                    ItemStack stack = stackHandler.getStackInSlot(i);
                    if (BuiltInRegistries.ITEM.getKey(stack.getItem()).getNamespace().equals(SeekingImmortalsMod.MODID)){

                        float s = event.getAmount();
                        if (s>Integer.MAX_VALUE){
                            event.setAmount(Integer.MAX_VALUE);
                        }
                    }
                }
            }
        });
        if (event.getSource().getEntity() instanceof LivingEntity living){
            if (living.getAttribute(AttReg.alL_attack)!=null){
                float attack = (float) living.getAttribute(AttReg.alL_attack).getValue();
                event.setAmount(event.getAmount()*(attack));
            }
        }

    }
    @SubscribeEvent
    public  void CurioCanEquipEvent(CurioCanEquipEvent event) {
        Item item = event.getStack().getItem();
        if (BuiltInRegistries.ITEM.getKey(item).getNamespace().equals(SeekingImmortalsMod.MODID)) {
            if (Handler.hascurio(event.getEntity(), item)) {
                event.setEquipResult(TriState.FALSE);
            }
        }
    }

    @SubscribeEvent
    public  void PlayerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        if (!player.getTags().contains(SeekingImmortalsMod.MODID+"nightmare")) {
            player.addItem(Items.nightmare_base.get().getDefaultInstance());
            player.addTag(SeekingImmortalsMod.MODID+"nightmare");
        }
    }
    @SubscribeEvent
    public void Night(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        Player player = event.getEntity();
        if (stack.getItem() instanceof SuperNightmare){
            if (!Handler.hascurio(player,Items.nightmare_base.get())) {
                event.getToolTip().add(1, Component.literal(""));
                event.getToolTip().add(1, Component.translatable("seeking_immortals.super_nightmare.name").withStyle(ChatFormatting.DARK_RED));
            }else {
                event.getToolTip().add(1, Component.literal(""));
                event.getToolTip().add(1, Component.translatable("seeking_immortals.super_nightmare.name").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFCD853F))));
            }
        }
    }
    @SubscribeEvent
    public void soulbattery(CriticalHitEvent event) {
       if (event.getEntity() instanceof Player living){
            if (living.getAttribute(AttReg.cit)!=null){
                float attack = (float) living.getAttribute(AttReg.cit).getValue();
                event.setDamageMultiplier(event.getDamageMultiplier()*(attack));
            }
        }

    }
    @SubscribeEvent
    public void soulbattery(PlayerEvent.BreakSpeed event) {
        if (event.getEntity() instanceof Player living){
            if (living.getAttribute(AttReg.dig)!=null){

                float dig = (float) living.getAttribute(AttReg.dig).getValue();

                event.setNewSpeed(event.getNewSpeed()*(dig));
            }
        }
    }
    @SubscribeEvent
    public void hurt(LivingIncomingDamageEvent event) {
        if (event.getEntity() instanceof Player living){
            if (living.getAttribute(AttReg.hurt)!=null){
                float hurt = (float) living.getAttribute(AttReg.hurt).getValue();
                event.setAmount(event.getAmount()*(hurt));
            }
        }

    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void Color(RenderTooltipEvent.Color tooltipEvent){
        ItemStack stack = tooltipEvent.getItemStack();

        if (stack.getItem() instanceof INightmare) {
            tooltipEvent.setBorderStart(0xFF800000);
            tooltipEvent.setBorderEnd(0xFF800080);

            tooltipEvent.setBackgroundStart(0x00000000);
            tooltipEvent.setBackgroundEnd(0x00000000);
        }
    }
}
