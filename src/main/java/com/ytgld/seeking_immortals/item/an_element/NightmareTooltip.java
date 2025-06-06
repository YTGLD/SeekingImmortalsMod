package com.ytgld.seeking_immortals.item.an_element;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import org.jetbrains.annotations.NotNull;

public class NightmareTooltip implements ClientTooltipComponent, TooltipComponent {
    private static final ResourceLocation BACKGROUND_SPRITE =
            ResourceLocation.withDefaultNamespace("container/bundle/background");
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
        return this.gridSizeX() * 18 + 2;
    }

    private int backgroundHeight() {
        return this.gridSizeY() * 20 + 2;
    }

    public void renderImage(@NotNull Font font, int x, int y, GuiGraphics guiGraphics) {
        int i = this.gridSizeX();
        int j = this.gridSizeY();
        guiGraphics.blitSprite(BACKGROUND_SPRITE, x, y, this.backgroundWidth(), this.backgroundHeight());
        for(int l = 0; l < j; ++l) {
            for(int i1 = 0; i1 < i; ++i1) {
                int j1 = x + i1 * 18 + 1;
                int k1 = y + l * 20 + 1;
                this.renderSlot(j1, k1, guiGraphics);
            }
        }

    }

    private void renderSlot(int x, int y, GuiGraphics guiGraphics) {
        guiGraphics.blitSprite(element.name(),x,y,32,32);
    }
    private int gridSizeX() {
        if (this.element.element() !=null) {
            return Math.max(2, (int) Math.ceil(Math.sqrt((double) this.element.element().size() + 1.0)));
        }else return 0;
    }

    private int gridSizeY() {
        if (this.element.element() !=null) {
            return (int) Math.ceil(((double) this.element.element().size() + 1.0) / (double) this.gridSizeX());
        }else return 0;
    }
}
