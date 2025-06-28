package com.ytgld.seeking_immortals.item.nightmare.super_nightmare;

import com.google.common.collect.Multimap;
import com.ytgld.seeking_immortals.Config;
import com.ytgld.seeking_immortals.Handler;
import com.ytgld.seeking_immortals.event.CurioHurtEvent;
import com.ytgld.seeking_immortals.init.DataReg;
import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.item.an_element.AllElement;
import com.ytgld.seeking_immortals.item.an_element.NightmareTooltip;
import com.ytgld.seeking_immortals.item.an_element.elements.Death;
import com.ytgld.seeking_immortals.item.an_element.elements.Destiny;
import com.ytgld.seeking_immortals.item.an_element.elements.Nightmare;
import com.ytgld.seeking_immortals.item.an_element.extend.Element;
import com.ytgld.seeking_immortals.item.nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.extend.nightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class nightmare_base_stone extends nightmare implements SuperNightmare , AllElement {

    public static final String deathTag  = "DeathTag";
    public static void LivingHurtEvent(LivingIncomingDamageEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (Handler.hascurio(player, Items.nightmare_base_stone.get())) {
                if (Handler.hascurio(player,Items.candle.get())){
                    return;
                }
                if (player.getHealth() >= player.getMaxHealth()) {
                    float s  = (float) Config.SERVER.nightmare_base_stone.getAsDouble();
                    s+=1;
                    event.setAmount(event.getAmount() * s);

                    if (!player.getCooldowns().isOnCooldown(Items.nightmare_base_stone.get())) {
                        if (event.getAmount() > player.getHealth()) {
                            event.setAmount(0);
                            player.setHealth(1);
                            player.getCooldowns().addCooldown(Items.nightmare_base_stone.get(),200);
                        }
                    }

                }
            }
        }
    }
    public static void CurioHurtEvent(CurioHurtEvent event){
        Player player = event.getPlayer();
        ItemStack stack = event.getStack();
        CompoundTag compoundTag = stack.get(DataReg.tag);
        if (Handler.hascurio(player,Items.nightmare_base_stone.get())){
            if (stack.is(Items.nightmare_base_stone.get())) {
                if (event.getEvent().getSource().getEntity() instanceof LivingEntity) {
                    if (compoundTag != null) {
                        compoundTag.putInt(deathTag, compoundTag.getInt(deathTag) + 1);
                    }
                }
            }
        }
    }

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
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> linkedHashMultimap = com.google.common.collect.LinkedHashMultimap.create();
        CuriosApi
                .addSlotModifier(linkedHashMultimap, "nightmare", ResourceLocation.parse("nightmare_base_stone" + "add_slot"
                ), 3, AttributeModifier.Operation.ADD_VALUE);
        return linkedHashMultimap;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> pTooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, pTooltipComponents, tooltipFlag);
        pTooltipComponents.add(Component.translatable("item.nightmare_base_stone.tool.string").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.candle.tool.string.1").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_stone.tool.string.2").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));
        pTooltipComponents.add(Component.translatable("item.nightmare_base_stone.tool.string.1").withStyle(ChatFormatting.RED));

        pTooltipComponents.add(Component.translatable("item.seeking_immortals.nightmare_base_stone_virus").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.seeking_immortals.nightmare_base_stone_meet").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.seeking_immortals.nightmare_base_stone_brain").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.translatable("item.seeking_immortals.end_bone").withStyle(ChatFormatting.DARK_RED));
        pTooltipComponents.add(Component.literal(""));

        pTooltipComponents.add(Component.translatable("item.nightmareeye.tool.string.2").withStyle(ChatFormatting.DARK_RED));
    }

    @Override
    public Map<Element, ResourceLocation> name() {
        Map<Element, ResourceLocation> map = new HashMap<>();
        map.put(death, Death.death);
        map.put(nightmare, Nightmare.nightmare);
        return map;
    }

    @Override
    public  Map<Element, String> tooltip() {
        Map<Element, String> map = new HashMap<>();
        map.put(this.death,"死亡");
        map.put(this.nightmare,"梦魇");
        return map;
    }

    @Override
    public Map<Element, Integer> element(ItemStack stack) {
        Map<Element, Integer> map = new HashMap<>();
        @Nullable CompoundTag compoundTag = stack.get(DataReg.tag);
        int s = 0;
        if (compoundTag !=null){
            s = compoundTag.getInt(deathTag);
        }
        map.put(this.death,15+s);
        map.put(this.nightmare,7);
        return map;
    }
    @Override
    public @NotNull Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        return Optional.of(new NightmareTooltip(this,stack));
    }
}

