package com.ytgld.seeking_immortals.item.nightmare.super_nightmare;

import com.google.common.collect.Multimap;
import com.ytgld.seeking_immortals.init.DataReg;
import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.item.an_element.elements.Destiny;
import com.ytgld.seeking_immortals.item.an_element.NightmareTooltip;
import com.ytgld.seeking_immortals.item.an_element.elements.Nightmare;
import com.ytgld.seeking_immortals.item.nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.extend.nightmare;
import com.ytgld.seeking_immortals.item.an_element.AllElement;
import com.ytgld.seeking_immortals.item.an_element.extend.Element;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class nightmare_base_black_eye extends nightmare implements SuperNightmare , AllElement {


    public static String destinyTag = "Destiny";

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
        if (slotContext.entity() instanceof Player player) {
            if (look(player.level(), player) instanceof LivingEntity) {
                @Nullable CompoundTag compoundTag = stack.get(DataReg.tag);
                if (compoundTag !=null) {
                    if (!player.level().isClientSide && player.tickCount % 100 == 0) {
                        compoundTag.putInt(destinyTag,compoundTag.getInt(destinyTag)+1);
                    }
                }
            }
        }
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
    public Entity look(Level level, LivingEntity living) {
        Entity pointedEntity = null;
        double range = 20.0D;
        Vec3 srcVec = living.getEyePosition();
        Vec3 lookVec = living.getViewVector(1.0F);
        Vec3 destVec = srcVec.add(lookVec.x() * range, lookVec.y() * range, lookVec.z() * range);
        float var9 = 1.0F;
        List<Entity> possibleList = level.getEntities(living, living.getBoundingBox().expandTowards(lookVec.x() * range, lookVec.y() * range, lookVec.z() * range).inflate(var9, var9, var9));
        double hitDist = 0;

        for (Entity possibleEntity : possibleList) {

            if (possibleEntity.isPickable()) {
                float borderSize = possibleEntity.getPickRadius();
                AABB collisionBB = possibleEntity.getBoundingBox().inflate(borderSize, borderSize, borderSize);
                Optional<Vec3> interceptPos = collisionBB.clip(srcVec, destVec);

                if (collisionBB.contains(srcVec)) {
                    if (0.0D < hitDist || hitDist == 0.0D) {
                        pointedEntity = possibleEntity;
                        hitDist = 0.0D;
                    }
                } else if (interceptPos.isPresent()) {
                    double possibleDist = srcVec.distanceTo(interceptPos.get());

                    if (possibleDist < hitDist || hitDist == 0.0D) {
                        pointedEntity = possibleEntity;
                        hitDist = possibleDist;
                    }
                }
            }
        }
        return pointedEntity;
    }
    @Override
    public Map<Element, ResourceLocation> name() {
        Map<Element, ResourceLocation> map = new HashMap<>();
        map.put(destiny,Destiny.destiny);
        map.put(nightmare, Nightmare.nightmare);
        return map;
    }

    @Override
    public  Map<Element, String> tooltip() {
        Map<Element, String> map = new HashMap<>();
        map.put(this.destiny,"命运");
        map.put(this.nightmare,"梦魇");
        return map;
    }

    @Override
    public Map<Element, Integer> element(ItemStack stack) {
        Map<Element, Integer> map = new HashMap<>();
        @Nullable CompoundTag compoundTag = stack.get(DataReg.tag);
        int s = 0;
        if (compoundTag !=null){
            s = compoundTag.getInt(destinyTag);
        }
        map.put(this.destiny,10+s);
        map.put(this.nightmare,14);
        return map;
    }
    @Override
    public @NotNull Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        return Optional.of(new NightmareTooltip(this,stack));
    }
}
