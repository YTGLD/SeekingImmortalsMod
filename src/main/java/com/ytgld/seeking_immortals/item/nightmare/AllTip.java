package com.ytgld.seeking_immortals.item.nightmare;

import com.ytgld.seeking_immortals.item.an_element.extend.Element;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.util.Map;

public interface AllTip {
    Map<Integer, String>  tooltip();
    Map<Integer, String> element(ItemStack stack);

}
