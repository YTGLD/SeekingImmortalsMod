package com.ytgld.seeking_immortals.event.now;

import com.ytgld.seeking_immortals.event.CurioHurtEvent;
import com.ytgld.seeking_immortals.item.nightmare.eye;
import com.ytgld.seeking_immortals.item.nightmare.immortal;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.nightmare_base_stone;
import net.neoforged.bus.api.SubscribeEvent;

public class EventHandler {
    @SubscribeEvent
    public void CurioLivingIncomingDamageEvent(CurioHurtEvent event){
        eye.CurioDamageEvent((event));
        nightmare_base_stone.CurioHurtEvent((event));
        immortal.CurioHurt(event);
    }
}
