package com.ytgld.seeking_immortals.item.nightmare.super_nightmare;

import com.google.common.collect.Multimap;
import com.ytgld.seeking_immortals.Config;
import com.ytgld.seeking_immortals.init.DataReg;
import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.item.nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class nightmare_base_insight extends nightmare implements SuperNightmare {
    @Override
    public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){
            if (CuriosApi.getCuriosInventory(player).isPresent()
                    && CuriosApi.getCuriosInventory(player).get().isEquipped(Items.immortal.get())){
                return true;
            }
            if (player.isCreative()){
                return true;
            }
        }
        return false;
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (stack.get(DataReg.tag)!=null) {
            if (stack.get(DataReg.tag).getBoolean("give_nightmare_base_insight_drug")){
                return;
            }
            if (!stack.get(DataReg.tag).getBoolean("give_nightmare_base_insight_drug")) {
                if (slotContext.entity() instanceof Player player) {
                    List<Integer> integers = new ArrayList<>();
                    int a = 0;
                    Collection<MobEffectInstance> collection = player.getActiveEffects();
                    if (!collection.isEmpty()) {
                        for (MobEffectInstance effectInstance : collection) {
                            if (effectInstance.getEffect().value().isBeneficial()) {
                                integers.add(1);
                            }
                        }
                    }
                    for (int ignored : integers) {
                        a++;
                    }
                    if (a >= Config.SERVER.give_nightmare_base_insight_drug.getAsInt()) {
                        player.addItem(new ItemStack(Items.nightmare_base_insight_drug.get()));
                        stack.get(DataReg.tag).putBoolean("give_nightmare_base_insight_drug",true);
                    }
                }
            }
        }else {
            stack.set(DataReg.tag,new CompoundTag());
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_insight.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_insight.tool.string.1").withStyle(ChatFormatting.DARK_RED));

        pTooltipComponents.add(Component.translatable("item.nightmare_base_black_eye.tool.string.1").withStyle(ChatFormatting.RED));
        pTooltipComponents.add(Component.translatable("item.seeking_immortals.nightmare_base_insight_collapse").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.seeking_immortals.nightmare_base_insight_insane").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.seeking_immortals.nightmare_base_insight_drug").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.nightmareeye.tool.string.2").withStyle(ChatFormatting.DARK_RED));

    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> linkedHashMultimap = com.google.common.collect.LinkedHashMultimap.create();
        CuriosApi
                .addSlotModifier(linkedHashMultimap, "nightmare", ResourceLocation.parse("nightmare_base_insight" + "add_slot"

                ), 3, AttributeModifier.Operation.ADD_VALUE);
        return linkedHashMultimap;
    }
}
