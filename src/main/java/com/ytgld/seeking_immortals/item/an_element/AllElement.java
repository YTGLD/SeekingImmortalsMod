package com.ytgld.seeking_immortals.item.an_element;

import com.ytgld.seeking_immortals.item.an_element.extend.Element;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;
import java.util.Map;

public interface AllElement {
    Destiny destiny = new Destiny();
    ResourceLocation name();

    @Nullable
    Map<Element, Integer> element();


}
