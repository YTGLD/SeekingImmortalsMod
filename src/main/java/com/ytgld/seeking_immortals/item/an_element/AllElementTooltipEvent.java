package com.ytgld.seeking_immortals.item.an_element;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterClientTooltipComponentFactoriesEvent;

import java.util.function.Function;

public class AllElementTooltipEvent {
    @SubscribeEvent
    public void RegisterClientTooltipComponentFactoriesEvent(RegisterClientTooltipComponentFactoriesEvent event){
        event.register(NightmareTooltip.class, Function.identity());
    }

}
