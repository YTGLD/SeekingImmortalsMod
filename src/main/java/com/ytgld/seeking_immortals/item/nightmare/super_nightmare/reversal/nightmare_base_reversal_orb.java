package com.ytgld.seeking_immortals.item.nightmare.super_nightmare.reversal;

import com.ytgld.seeking_immortals.Handler;
import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class nightmare_base_reversal_orb extends nightmare implements SuperNightmare {
    public static void LivingHealEvent(LivingHealEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.nightmare_base_reversal_orb.get())) {
                player.hurt(player.damageSources().dryOut(), event.getAmount());
                event.setAmount(0);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_reversal_orb.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_reversal_orb.tool.string.2").withStyle(ChatFormatting.DARK_RED));
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            if (!player.getCooldowns().isOnCooldown(this)) {
                player.setHealth(player.getMaxHealth());
                player.getCooldowns().addCooldown(this, 140);
            }
        }
    }
}
