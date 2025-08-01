package com.ytgld.seeking_immortals.item.nightmare.extend;

import com.ytgld.seeking_immortals.SeekingImmortalsMod;
import com.ytgld.seeking_immortals.init.DataReg;
import com.ytgld.seeking_immortals.item.nightmare.tip.Terror;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.inventory.tooltip.BundleTooltip;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CurioAttributeModifiers;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.common.CuriosRegistry;

import java.util.Map;
import java.util.Optional;

public class nightmare extends Item implements ICurioItem, INightmare , Terror {
    public final String terrorName ="SITheTerrorName";
    public nightmare() {
        super(new Properties().stacksTo(1).component(CuriosRegistry.CURIO_ATTRIBUTE_MODIFIERS, CurioAttributeModifiers.EMPTY)
                .durability(1000000000).rarity(Rarity.UNCOMMON));
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        Component component = super.getName(stack);
        @Nullable Map<Integer, Component> map = this.describe(stack);
        if (map != null) {
            Integer integer = map.keySet().stream().toList().get(this.nowLevel(stack));
            if (integer!=0) {
                Component newName = map.get(integer);
                newName.copy().append(component);
                return newName;
            }else {
                return component;
            }
        }


        return component;
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        if (stack.get(DataReg.tag) == null) {
            stack.set(DataReg.tag, new CompoundTag());
        }
    }


    @NotNull
    @Override
    public ICurio.DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit, ItemStack stack) {
        return ICurio.DropRule.ALWAYS_KEEP;
    }

    @NotNull
    @Override
    public ICurio.DropRule getDropRule(SlotContext slotContext, DamageSource source, boolean recentlyHit, ItemStack stack) {
        return ICurio.DropRule.ALWAYS_KEEP;
    }

    @Override
    public ResourceLocation image(@Nullable LivingEntity entity) {

        return ResourceLocation.fromNamespaceAndPath(SeekingImmortalsMod.MODID, "textures/gui/tooltip/fire.png");

    }

    @Override
    @Nullable
    public Map<Integer ,Component> describe(ItemStack stack) {
        return null;
    }

    @Override
    public int maxLevel(ItemStack stack) {
        return 1;
    }
    @Override
    public int nowLevel(ItemStack stack) {
        return 1;
    }
}

