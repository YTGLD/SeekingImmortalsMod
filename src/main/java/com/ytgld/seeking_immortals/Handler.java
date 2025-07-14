package com.ytgld.seeking_immortals;

import com.ytgld.seeking_immortals.init.Items;
import com.ytgld.seeking_immortals.item.an_element.AllElement;
import com.ytgld.seeking_immortals.item.an_element.extend.Element;
import com.ytgld.seeking_immortals.item.nightmare.extend.SuperNightmare;
import com.ytgld.seeking_immortals.item.nightmare.extend.nightmare;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.Map;

public class Handler {

    public static int getElement(ItemStack stack, AllElement allElement, Element element){
        if (!stack.isEmpty()){
            if (stack.getItem() instanceof AllElement){
                Map<Element, Integer> map = allElement.element(stack);
                if (map !=null) {
                    Integer value = map.get(element);
                    if (value!=null) {
                        return value;
                    }
                }
            }
        }
        return  0;
    }

    public static boolean hascurio(LivingEntity entity, Item curio) {

        if (CuriosApi.getCuriosInventory(entity).isPresent()) {
            if (CuriosApi.getCuriosInventory(entity).get().isEquipped(Items.immortal.get())) {
                if (curio instanceof nightmare) {
                    return false;
                }
            }
        }
        if (CuriosApi.getCuriosInventory(entity).isPresent()
                && !CuriosApi.getCuriosInventory(entity).get().isEquipped(Items.nightmare_base.get())) {
            if (curio instanceof SuperNightmare) {
                return false;
            }
        }

        if (CuriosApi.getCuriosInventory(entity).isPresent()
                && !CuriosApi.getCuriosInventory(entity).get().isEquipped(Items.nightmare_base.get())) {
            if (curio instanceof SuperNightmare) {
                return false;
            }
        }
        return CuriosApi.getCuriosInventory(entity).isPresent()
                && CuriosApi.getCuriosInventory(entity).get().isEquipped(curio);
    }

}
