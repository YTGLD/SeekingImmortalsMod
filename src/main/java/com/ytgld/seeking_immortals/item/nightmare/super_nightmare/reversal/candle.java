package com.ytgld.seeking_immortals.item.nightmare.super_nightmare.reversal;

import com.ytgld.seeking_immortals.Handler;
import com.ytgld.seeking_immortals.init.Effects;
import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.item.nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class candle  extends nightmare implements SuperNightmare {

    public static void hurt(LivingIncomingDamageEvent event){
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.candle.get())) {
                if (!player.getCooldowns().isOnCooldown(Items.candle.get())) {
                    if (player.getHealth() >= player.getMaxHealth()) {

                        event.setAmount(event.getAmount() * 11);
                        int s = (int) (event.getAmount());

                        if (event.getAmount() > player.getHealth()) {
                            event.setAmount(0);
                            player.setHealth(1);
                        }
                        if (s > 5 * 20) {
                            s = 5 * 20;
                        }
                        player.addEffect(new MobEffectInstance(Effects.invulnerable,s,0,false,false,false));
                        player.invulnerableTime = player.invulnerableTime + s;
                        player.getCooldowns().addCooldown(Items.candle.get(), 200);
                    }
                }
            }
        }
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.candle.get())){
                if (player.invulnerableTime>0){
                    event.setAmount(event.getAmount()*1.25f);
                }
            }
        }
    }
    public static void heal(LivingHealEvent event){
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.candle.get())){
                if (player.invulnerableTime>0){
                    event.setAmount(event.getAmount()*1.5f);
                }
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.candle.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.candle.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.candle.tool.string.2").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.candle.tool.string.3").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.candle.tool.string.4").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.candle.tool.string.5").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.candle.tool.string.6").withStyle(ChatFormatting.LIGHT_PURPLE));
        }
}
