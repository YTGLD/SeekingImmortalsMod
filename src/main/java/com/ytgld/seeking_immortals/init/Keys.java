package com.ytgld.seeking_immortals.init;

import com.mojang.blaze3d.platform.InputConstants;
import cpw.mods.util.Lazy;
import net.minecraft.client.KeyMapping;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;

public class Keys{
    public static final KeyMapping KEY_MAPPING_LAZY_R = (new KeyMapping("key.seeking_immortals.r", InputConstants.KEY_R,"key.seeking_immortals"));


}
