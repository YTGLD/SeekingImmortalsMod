package com.ytgld.seeking_immortals.item.an_element;

import com.ytgld.seeking_immortals.SeekingImmortalsMod;
import com.ytgld.seeking_immortals.item.an_element.extend.Element;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class NightmareTooltip implements ClientTooltipComponent, TooltipComponent {
    private static final ResourceLocation BACKGROUND_SPRITE =
            ResourceLocation.fromNamespaceAndPath(SeekingImmortalsMod.MODID,"background");
    private final AllElement element;

    public NightmareTooltip(AllElement contents) {
        this.element = contents;
    }

    @Override
    public int getHeight() {
        return this.backgroundHeight() + 4;
    }

    @Override
    public int getWidth(Font font) {
        return this.backgroundWidth();
    }

    private int backgroundWidth() {
        return this.gridSizeX() * 32 + 2;
    }

    private int backgroundHeight() {
        return this.gridSizeY() * 32 + 2;
    }

    public void renderImage(@NotNull Font font, int x, int y, GuiGraphics guiGraphics) {
        int i = this.gridSizeX();
        int j = this.gridSizeY();
        guiGraphics.blitSprite(BACKGROUND_SPRITE, x, y, this.backgroundWidth(), this.backgroundHeight());
        for(int l = 0; l < j; ++l) {
            for(int i1 = 0; i1 < i; ++i1) {
                int j1 = x + i1 * 32 ;
                int k1 = y + l * 32 ;
                this.renderSlot(font,j1, k1, guiGraphics);
            }
        }

    }

    private void renderSlot(Font font,int x, int y, GuiGraphics guiGraphics) {
        guiGraphics.blitSprite(element.name(),x,y,32,32);

        Map<Element, Integer> map = element.element();
        if (map!=null) {
            for (int number : map.values()) {
                guiGraphics.drawString(font, String.valueOf(number), x+26, y+26, 0xFFFFE7BA, false);
                break;
            }
        }
    }
    private int gridSizeX() {
        Map<Element, Integer> map = element.element();
        if (map!=null) {
            return map.size();
        }else return 0;
    }

    private int gridSizeY() {
        return 1;
    }
}
