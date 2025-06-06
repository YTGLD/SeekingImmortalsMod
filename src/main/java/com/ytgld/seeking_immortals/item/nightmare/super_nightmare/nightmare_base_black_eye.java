package com.ytgld.seeking_immortals.item.nightmare.super_nightmare;

import com.google.common.collect.Multimap;
import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.item.an_element.Destiny;
import com.ytgld.seeking_immortals.item.an_element.NightmareTooltip;
import com.ytgld.seeking_immortals.item.nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.extend.nightmare;
import com.ytgld.seeking_immortals.item.an_element.AllElement;
import com.ytgld.seeking_immortals.item.an_element.extend.Element;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.BundleTooltip;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class nightmare_base_black_eye extends nightmare implements SuperNightmare , AllElement {


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

    //白天会视力模糊
    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> linkedHashMultimap = com.google.common.collect.LinkedHashMultimap.create();
        CuriosApi
                .addSlotModifier(linkedHashMultimap, "nightmare", ResourceLocation.parse("nightmare_base_black_eye" + "add_slot"
                ), 3, AttributeModifier.Operation.ADD_VALUE);

        return linkedHashMultimap;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_black_eye.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_black_eye.tool.string.1").withStyle(ChatFormatting.RED));
        pTooltipComponents.add(Component.translatable("item.seeking_immortals.nightmare_base_black_eye_eye").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.seeking_immortals.nightmare_base_black_eye_red").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.seeking_immortals.nightmare_base_black_eye_heart").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.seeking_immortals.tricky_puppets").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));

        pTooltipComponents.add(Component.translatable("item.nightmareeye.tool.string.2").withStyle(ChatFormatting.DARK_RED));

    }

    @Override
    public ResourceLocation name() {
        return Destiny.destiny;
    }

    @Override
    public Map<Element, Integer> element() {
        Map<Element, Integer> map = new HashMap<>();
        map.put(this.destiny,10);
        return map;
    }
    @Override
    public @NotNull Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        return Optional.of(new NightmareTooltip(this));
    }
}
