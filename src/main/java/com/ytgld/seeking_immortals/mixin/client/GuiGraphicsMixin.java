package com.ytgld.seeking_immortals.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.ytgld.seeking_immortals.MGuiGraphics;
import com.ytgld.seeking_immortals.SeekingImmortalsMod;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.extend.INightmare;
import com.ytgld.seeking_immortals.item.nightmare.super_nightmare.nightmare_base;
import com.ytgld.seeking_immortals.renderer.MRender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipPositioner;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.client.ClientHooks;
import net.neoforged.neoforge.client.event.RenderTooltipEvent;
import org.joml.Matrix4f;
import org.joml.Vector2ic;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(GuiGraphics.class)
public abstract class GuiGraphicsMixin {

    @Shadow private ItemStack tooltipStack;

    @Shadow public abstract int guiWidth();

    @Shadow public abstract int guiHeight();
    @Shadow @Final private PoseStack pose;

    @Inject(at = {@At("RETURN")}, method = {"renderItem(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;III)V"})
    public void ca$renderItemDecorationsRenderItem(LivingEntity living, Level level, ItemStack stack, int x, int y, int is, CallbackInfo ci) {
        GuiGraphics guiGraphics = (GuiGraphics) (Object) this;
        if (living != null) {
            if (stack.getItem() instanceof nightmare_base){
                int tickCount = living.tickCount;



                float[][] positions = {
                        {x - 8/10f, y - 8/10f},
                        {x + 24/10f, y - 8/10f},
                        {x - 8/10f, y + 24/10f},
                        {x + 24/10f, y + 24/10f},
                        {x + 56/10f, y - 8/10f},
                        {x + 56/10f, y + 24/10f},
                        {x - 8/10f, y + 56/10f},
                        {x + 24/10f, y + 56/10f},
                        {x + 5/10f, y + 5/10f}
                };
                double[] alphaFactors = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0};
                for (int i = 0; i < 9; i++) {
                    float s = (float) Math.sin((double) tickCount / 50 * alphaFactors[i]);
                    if (s < 0) {
                        s = -s;
                    }
                    float red = 1 - (i/10f);
                    MGuiGraphics.blit(guiGraphics, ResourceLocation.fromNamespaceAndPath(SeekingImmortalsMod.MODID, "textures/gui/necora_red.png"), positions[i][0]-6, positions[i][1]-6, 0, 0, 24, 24, 24, 24, red, 0, 1, s);
                }
            }

        }
    }




    @Unique
    public void moon1_21$drawManaged(Runnable pRunnable) {
        this.flush();
        this.managed = true;
        pRunnable.run();
        this.managed = false;
        this.flush();
    }
    @Shadow @Final private MultiBufferSource.BufferSource bufferSource;
    @Shadow @Deprecated protected abstract void flushIfUnmanaged();

    @Shadow public abstract void flush();

    @Shadow private boolean managed;

    @Shadow @Final private Minecraft minecraft;

    @Shadow public abstract PoseStack pose();
    @Inject(at = @At(value = "INVOKE",target = "Lnet/minecraft/client/gui/GuiGraphics;drawManaged(Ljava/lang/Runnable;)V"),method = "renderTooltipInternal(Lnet/minecraft/client/gui/Font;Ljava/util/List;IILnet/minecraft/client/gui/screens/inventory/tooltip/ClientTooltipPositioner;)V")
    public void moonstone$ClientTooltipPositioner(Font p_282675_, List<ClientTooltipComponent> p_282615_, int x, int y, ClientTooltipPositioner p_282442_, CallbackInfo ci) {
        moon1_21$drawManaged(()->{
            RenderTooltipEvent.Pre preEvent = ClientHooks.onRenderTooltipPre(this.tooltipStack, (GuiGraphics)(Object)this, x, y, guiWidth(), guiHeight(), p_282615_, p_282675_, p_282442_);

            int i = 0;
            int j = p_282615_.size() == 1 ? -2 : 0;

            for(ClientTooltipComponent clienttooltipcomponent : p_282615_) {
                int k = clienttooltipcomponent.getWidth(preEvent.getFont());
                if (k > i) {
                    i = k;
                }

                j += clienttooltipcomponent.getHeight();
            }

            int i2 = i;
            int j2 = j;


            Vector2ic vector2ic = p_282442_.positionTooltip(this.guiWidth(), this.guiHeight(), preEvent.getX(), preEvent.getY(), i2, j2);

            int l = vector2ic.x();
            int i1 = vector2ic.y();
            if (tooltipStack.getItem()instanceof INightmare){
                this.pose.pushPose();
                moonstone$renderTooltipBackground_nig((GuiGraphics) (Object) this, l, i1, i2, j2, 400, 0xff000000, 0xff000000, 0xff000000, 0xff000000);
                si1_21_4$renderTooltipBackground((GuiGraphics) (Object) this, l, i1, i2, j2,400);
                this.pose.popPose();
            }
        });

    }
    @Unique
    public void si1_21_4$renderTooltipBackground(GuiGraphics guiGraphics, int x, int y, int width, int height, int z) {
        // 左上角
        int topLeftX = x - 3 - 9+2;
        int topLeftY = y - 3 - 9;
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(0.0F, -2, (float)z);
        guiGraphics.blit(
                ResourceLocation.fromNamespaceAndPath(SeekingImmortalsMod.MODID,
                        "textures/gui/tooltip/tool_0_0.png"), topLeftX, topLeftY,  0, 0, 48, 48, 48, 48);
        guiGraphics.pose().popPose();

        // 中间位置
        int middleX = x + (width - 48) / 2;
        int middleY = y - 3 - 9;
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(0.0F, -7, (float)z);
        guiGraphics.blit(
                ResourceLocation.fromNamespaceAndPath(SeekingImmortalsMod.MODID,
                        "textures/gui/tooltip/tool_middle_0.png"), middleX, middleY, 0, 0, 48, 48, 48, 48);
        guiGraphics.pose().popPose();


        // 右上角
        int topRightX = x + width + 3 - 48+6;
        int topRightY = y - 3 - 9;
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(0.0F, -2, (float)z);
        guiGraphics.blit(
                ResourceLocation.fromNamespaceAndPath(SeekingImmortalsMod.MODID,
                        "textures/gui/tooltip/tool_0_1.png"), topRightX, topRightY, 0, 0, 48, 48, 48, 48);
        guiGraphics.pose().popPose();

        // 左下角
        int bottomLeftX = x - 3 - 9 + 2;
        int bottomLeftY = y + height + 3 - 48 + 4;
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(0.0F, 4, (float)z);
        guiGraphics.blit(
                ResourceLocation.fromNamespaceAndPath(SeekingImmortalsMod.MODID,
                        "textures/gui/tooltip/tool_1_0.png"),bottomLeftX, bottomLeftY,0, 0, 48, 48, 48, 48);
        guiGraphics.pose().popPose();

        // 右下角
        int bottomRightX = x + width + 3 - 48 + 6;
        int bottomRightY = y + height + 3 - 48 + 4;
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(0.0F, 4, (float)z);
        guiGraphics.blit(
                ResourceLocation.fromNamespaceAndPath(SeekingImmortalsMod.MODID,
                        "textures/gui/tooltip/tool_1_1.png"),bottomRightX, bottomRightY, 0, 0, 48, 48, 48, 48);
        guiGraphics.pose().popPose();
    }

    @Unique
    private void moonstone$renderTooltipBackground_nig(GuiGraphics p_282666_, int p_281901_, int p_281846_, int p_281559_, int p_283336_, int p_283422_, int backgroundTop, int backgroundBottom, int borderTop, int borderBottom)
    {
        int i = p_281901_ - 3;
        int j = p_281846_ - 3;
        int k = p_281559_ + 3 + 3;
        int l = p_283336_ + 3 + 3;
        moonstone$renderHorizontalLine_nig(p_282666_, i, j - 1, k, p_283422_, backgroundTop);
        moonstone$renderHorizontalLine_nig(p_282666_, i, j + l, k, p_283422_, backgroundBottom);
        moonstone$renderRectangle_nig(p_282666_, i, j, k, l, p_283422_, backgroundTop, backgroundBottom);
        moonstone$renderVerticalLineGradient_nig(p_282666_, i - 1, j, l, p_283422_, backgroundTop, backgroundBottom);
        moonstone$renderVerticalLineGradient_nig(p_282666_, i + k, j, l, p_283422_, backgroundTop, backgroundBottom);
    }
    @Unique
    private void moonstone$renderFrameGradient_nig(GuiGraphics p_282000_, int p_282055_, int p_281580_, int p_283284_, int p_282599_, int p_283432_, int p_282907_, int p_283153_) {
        moonstone$renderVerticalLineGradient_nig(p_282000_, p_282055_, p_281580_, p_282599_ - 2, p_283432_, p_282907_, p_283153_);
        moonstone$renderVerticalLineGradient_nig(p_282000_, p_282055_ + p_283284_ - 1, p_281580_, p_282599_ - 2, p_283432_, p_282907_, p_283153_);
        moonstone$renderHorizontalLine_nig(p_282000_, p_282055_, p_281580_ - 1, p_283284_, p_283432_, p_282907_);
        moonstone$renderHorizontalLine_nig(p_282000_, p_282055_, p_281580_ - 1 + p_282599_ - 1, p_283284_, p_283432_, p_283153_);
    }
    @Unique
    private void moonstone$renderHorizontalLine_nig(GuiGraphics p_282981_, int p_282028_, int p_282141_, int p_281771_, int p_282734_, int p_281979_) {
        moonstone$fill_nig(p_282028_, p_282141_, p_282028_ + p_281771_, p_282141_ + 1, p_282734_, p_281979_);
    }
    @Unique
    private void moonstone$renderVerticalLine_nig(GuiGraphics p_281270_, int p_281928_, int p_281561_, int p_283155_, int p_282552_, int p_282221_) {
        moonstone$fill_nig(p_281928_, p_281561_, p_281928_ + 1, p_281561_ + p_283155_, p_282552_, p_282221_);
    }

    @Unique
    private void moonstone$renderVerticalLineGradient_nig(GuiGraphics p_282478_, int p_282583_, int p_283262_, int p_283161_, int p_283322_, int p_282624_, int p_282756_) {
        moonstone$fillGradient_nig(p_282583_, p_283262_, p_282583_ + 1, p_283262_ + p_283161_, p_283322_, p_282624_, p_282756_);
    }@Unique
    private void moonstone$renderRectangle_nig(GuiGraphics p_281392_, int p_282294_, int p_283353_, int p_282640_, int p_281964_, int p_283211_, int p_282349_, int colorTo) {
        moonstone$fillGradient_nig(p_282294_, p_283353_, p_282294_ + p_282640_, p_283353_ + p_281964_, p_283211_, p_282349_, colorTo);
    }

    @Unique
    public void moonstone$fill_nig(int p_281437_, int p_283660_, int p_282606_, int p_283413_, int p_283428_, int p_283253_) {
        this.moonstone$fill_nig(MRender.ging(), p_281437_, p_283660_, p_282606_, p_283413_, p_283428_, p_283253_);
    }
    @Unique
    public void moonstone$fill_nig(RenderType p_286711_, int p_286234_, int p_286444_, int p_286244_, int p_286411_, int p_286671_, int p_286599_) {
        Matrix4f matrix4f = this.pose.last().pose();
        if (p_286234_ < p_286244_) {
            int i = p_286234_;
            p_286234_ = p_286244_;
            p_286244_ = i;
        }

        if (p_286444_ < p_286411_) {
            int j = p_286444_;
            p_286444_ = p_286411_;
            p_286411_ = j;
        }

        float f3 = (float) FastColor.ARGB32.alpha(p_286599_) / 255.0F;
        float f = (float)FastColor.ARGB32.red(p_286599_) / 255.0F;
        float f1 = (float)FastColor.ARGB32.green(p_286599_) / 255.0F;
        float f2 = (float)FastColor.ARGB32.blue(p_286599_) / 255.0F;
        VertexConsumer vertexconsumer = this.bufferSource.getBuffer(p_286711_);
        vertexconsumer.addVertex(matrix4f, (float)p_286234_, (float)p_286444_, (float)p_286671_).setColor(f, f1, f2, f3);
        vertexconsumer.addVertex(matrix4f, (float)p_286234_, (float)p_286411_, (float)p_286671_).setColor(f, f1, f2, f3);
        vertexconsumer.addVertex(matrix4f, (float)p_286244_, (float)p_286411_, (float)p_286671_).setColor(f, f1, f2, f3);
        vertexconsumer.addVertex(matrix4f, (float)p_286244_, (float)p_286444_, (float)p_286671_).setColor(f, f1, f2, f3);
        this.flushIfUnmanaged();
    }
    @Unique
    public void moonstone$fillGradient_nig(int p_282702_, int p_282331_, int p_281415_, int p_283118_, int p_282419_, int p_281954_, int p_282607_) {
        this.moonstone$fillGradient_nig(MRender.ging(), p_282702_, p_282331_, p_281415_, p_283118_, p_281954_, p_282607_, p_282419_);
    }

    @Unique
    public void moonstone$fillGradient_nig(RenderType p_286522_, int p_286535_, int p_286839_, int p_286242_, int p_286856_, int p_286809_, int p_286833_, int p_286706_) {
        VertexConsumer vertexconsumer = this.bufferSource.getBuffer(p_286522_);
        this.moonstone$fillGradient_nig(vertexconsumer, p_286535_, p_286839_, p_286242_, p_286856_, p_286706_, p_286809_, p_286833_);
        this.flushIfUnmanaged();
    }

    @Unique
    private void moonstone$fillGradient_nig(VertexConsumer p_286862_, int p_283414_, int p_281397_, int p_283587_, int p_281521_, int p_283505_, int p_283131_, int p_282949_) {
        float f = (float)FastColor.ARGB32.alpha(p_283131_) / 255.0F;
        float f1 = (float)FastColor.ARGB32.red(p_283131_) / 255.0F;
        float f2 = (float)FastColor.ARGB32.green(p_283131_) / 255.0F;
        float f3 = (float)FastColor.ARGB32.blue(p_283131_) / 255.0F;

        float f4 = (float)FastColor.ARGB32.alpha(p_282949_) / 255.0F;
        float f5 = (float)FastColor.ARGB32.red(p_282949_) / 255.0F;
        float f6 = (float)FastColor.ARGB32.green(p_282949_) / 255.0F;
        float f7 = (float)FastColor.ARGB32.blue(p_282949_) / 255.0F;

        Matrix4f matrix4f = this.pose.last().pose();
        VertexConsumer vertexconsumer = this.bufferSource.getBuffer(MRender.ging());

       vertexconsumer.addVertex(matrix4f, (float)p_283414_, (float)p_281397_, (float)p_283505_).setColor(f1, f2, f3, f);
       vertexconsumer.addVertex(matrix4f, (float)p_283414_, (float)p_281521_, (float)p_283505_).setColor(f5, f6, f7, f4);
       vertexconsumer.addVertex(matrix4f, (float)p_283587_, (float)p_281521_, (float)p_283505_).setColor(f5, f6, f7, f4);
       vertexconsumer.addVertex(matrix4f, (float)p_283587_, (float)p_281397_, (float)p_283505_).setColor(f1, f2, f3, f);
    }

}