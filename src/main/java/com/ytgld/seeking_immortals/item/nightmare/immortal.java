package com.ytgld.seeking_immortals.item.nightmare;

import com.ytgld.seeking_immortals.Config;
import com.ytgld.seeking_immortals.Handler;
import com.ytgld.seeking_immortals.event.CurioHurtEvent;
import com.ytgld.seeking_immortals.init.DataReg;
import com.ytgld.seeking_immortals.init.Effects;
import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.item.an_element.AllElement;
import com.ytgld.seeking_immortals.item.an_element.NightmareTooltip;
import com.ytgld.seeking_immortals.item.an_element.elements.Destiny;
import com.ytgld.seeking_immortals.item.an_element.elements.Transmigrate;
import com.ytgld.seeking_immortals.item.an_element.extend.Element;
import com.ytgld.seeking_immortals.item.nightmare.extend.INightmare;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CurioAttributeModifiers;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.common.CuriosRegistry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 不朽轮回之印章
 * <p>
 * <p>
 * 受到攻击有80%的概率规避并反弹50%的伤害给攻击者
 * <p>
 * 如果攻击者生命值大于70%，则反弹150%的伤害并破除无敌帧
 * <p>
 * <p>
 * 每隔5秒对周围生物施加一层枯朽Buff，最多达到5级
 * <p>
 * 被施加的生物每级减少20%抗性,护甲,治疗和5%的伤害,移速,攻速
 * <p>
 * <p>
 * 若你被杀死，则对攻击者造成20%当前生命值的穿透伤害并附加10级枯朽（10秒）
 * <p>
 * <p>
 * 深渊和噩梦物品无效化
 */
public class immortal extends Item implements ICurioItem , INightmare, AllElement {


    public static final String transmigrateTag ="TransmigrateTag";

    public immortal() {
        super(new Properties().stacksTo(1).component(CuriosRegistry.CURIO_ATTRIBUTE_MODIFIERS, CurioAttributeModifiers.EMPTY)
                .durability(1000000000).rarity(Rarity.UNCOMMON));
    }

    public static void CurioHurt(CurioHurtEvent curioHurtEvent){
        LivingIncomingDamageEvent event = curioHurtEvent.getEvent();
        @Nullable CompoundTag compoundTag = curioHurtEvent.getStack().get(DataReg.tag);
        if (event.getSource().getEntity() instanceof LivingEntity living){
            if (event.getEntity() instanceof Player player){
                int lvl = Mth.nextInt(RandomSource.create(),1,100);
                if (Handler.hascurio(player, Items.immortal.get())){
                    if (compoundTag!=null) {
                        if (lvl <= Config.SERVER.immortal.get()) {
                            player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_HIT_PLAYER, SoundSource.NEUTRAL, 1F, 1F);
                            if (living.getHealth() <= living.getMaxHealth() * 0.7f) {
                                living.hurt(living.damageSources().dryOut(), event.getAmount() * 0.5f);
                            } else {
                                living.invulnerableTime = 0;
                                living.hurt(living.damageSources().dryOut(), event.getAmount() * 1.5f);

                            }
                            event.setAmount(0);
                            compoundTag.putInt(transmigrateTag, compoundTag.getInt(transmigrateTag) + 1);
                        }
                    }else {
                        curioHurtEvent.getStack().set(DataReg.tag,new CompoundTag());
                    }
                }
            }
        }
    }
    public static void livDead(LivingDeathEvent event){
        if (event.getSource().getEntity() instanceof LivingEntity living){
            if (event.getEntity() instanceof Player player){
                if (Handler.hascurio(player, Items.immortal.get())){
                    living.hurt(living.damageSources().dryOut(),living.getHealth()*0.2f);
                    living.addEffect(new MobEffectInstance(Effects.dead,200,9));
                }
            }
        }
    }


    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player){


            Vec3 playerPos = player.position().add(0, 0.75, 0);
            int range = 8;
            List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));

            for (LivingEntity living : entities){
                if (!living.is(player)){
                    if (player.tickCount%100==1){
                        living.addEffect(new MobEffectInstance(Effects.dead,600,0));

                        if (living.getEffect(Effects.dead)!=null){
                            if (living.getEffect(Effects.dead).getAmplifier()<5) {
                                living.addEffect(new MobEffectInstance(Effects.dead, 600, living.getEffect(Effects.dead).getAmplifier() + 1));
                            }else {
                                living.addEffect(new MobEffectInstance(Effects.dead, 600, 5));
                            }
                        }
                    }
                }
            }
        }
    }


    @Override
    public void appendHoverText(ItemStack stack, TooltipContext level, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, level, tooltip, flags);
       if (Screen.hasShiftDown()) {
           tooltip.add(Component.literal(""));
           tooltip.add(Component.translatable("item.immortal.tool.string").withStyle(ChatFormatting.RED));
           tooltip.add(Component.translatable("item.immortal.tool.string.1").withStyle(ChatFormatting.RED));
           tooltip.add(Component.literal(""));
           tooltip.add(Component.translatable("item.immortal.tool.string.2").withStyle(ChatFormatting.RED));
           tooltip.add(Component.translatable("item.immortal.tool.string.3").withStyle(ChatFormatting.RED));
           tooltip.add(Component.literal(""));
           tooltip.add(Component.translatable("item.immortal.tool.string.4").withStyle(ChatFormatting.RED));
           tooltip.add(Component.literal(""));
           tooltip.add(Component.translatable("item.immortal.tool.string.5").withStyle(ChatFormatting.RED));
       }else {
           tooltip.add(Component.translatable("key.keyboard.left.shift").withStyle(ChatFormatting.DARK_RED));
           tooltip.add(Component.literal(""));
           tooltip.add(Component.translatable("item.immortal.tool.string.6").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
           tooltip.add(Component.translatable("item.immortal.tool.string.7").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
           tooltip.add(Component.translatable("item.immortal.tool.string.8").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.BOLD));
       }
    }
    @Override
    public Map<Element, ResourceLocation> name() {
        Map<Element, ResourceLocation> map = new HashMap<>();
        map.put(destiny, Destiny.destiny);
        map.put(transmigrate, Transmigrate.transmigrate);
        return map;
    }

    @Override
    public  Map<Element, String> tooltip() {
        Map<Element, String> map = new HashMap<>();
        map.put(this.destiny,"命运");
        map.put(this.transmigrate,"轮回");
        return map;
    }

    @Override
    public Map<Element, Integer> element(ItemStack stack) {
        Map<Element, Integer> map = new HashMap<>();
        @Nullable CompoundTag compoundTag = stack.get(DataReg.tag);
        int s = 0;
        if (compoundTag !=null){
            s = compoundTag.getInt(transmigrateTag);
        }
        map.put(this.destiny,30);
        map.put(this.transmigrate,30+s);
        return map;
    }
    @Override
    public @NotNull Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        return Optional.of(new NightmareTooltip(this,stack));
    }
}
