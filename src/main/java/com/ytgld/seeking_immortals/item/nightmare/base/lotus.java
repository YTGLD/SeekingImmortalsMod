package com.ytgld.seeking_immortals.item.nightmare.base;

import com.ytgld.seeking_immortals.Handler;
import com.ytgld.seeking_immortals.init.EntityTs;
import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.item.nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.extend.nightmare;
import com.ytgld.seeking_immortals.test_entity.lotus_entity;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

import java.util.List;

public class lotus extends nightmare implements SuperNightmare {
    public static void die(LivingDeathEvent event){
        if (event.getSource().getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.lotus.get())){
                if (!player.getCooldowns().isOnCooldown(Items.lotus.get())) {
                    lotus_entity lotus_entity = new lotus_entity(EntityTs.lotus_entity.get(), player.level());
                    lotus_entity.setPos(event.getEntity().position());
                    lotus_entity.setOwner(player);
                    player.level().addFreshEntity(lotus_entity);
                    player.getCooldowns().addCooldown(Items.lotus.get(),200);
                }
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.lotus.tool.string.1").withStyle(ChatFormatting.DARK_RED));
    }
}
