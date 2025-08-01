package com.ytgld.seeking_immortals.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.ytgld.seeking_immortals.MGuiGraphics;
import com.ytgld.seeking_immortals.item.nightmare.tip.Terror;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class TerrorFire {
    public final  ResourceLocation image;
    public final  GuiGraphics guiGraphics;
    public final  float x;
    public final  float y;

    public final  float r;
    public final  float g;
    public final  float b;
    public final  float a;

    public TerrorFire(ResourceLocation image, GuiGraphics guiGraphics, float x, float y, float r, float g, float b, float a){
        this.image = image;
        this.guiGraphics = guiGraphics;
        this.x = x;
        this.y = y;
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }
    public float time;
    private void tick(){
        time++;
    }
    public void seekingImmortals$dofire(LivingEntity entity,int offset,int seed) {
        tick();
        PoseStack stack = guiGraphics.pose();
        if (entity != null) {
            int time = entity.tickCount;
            float sin = (float) Math.sin(time*seed);
            stack.pushPose();
            stack.translate(0,0,0);
            MGuiGraphics.blit(guiGraphics,image, x, y, 0, 0, offset, offset, offset, offset,r,g,b,a);
            stack.popPose();
        }
    }
}
