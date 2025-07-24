package com.ytgld.seeking_immortals.test_entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.ytgld.seeking_immortals.Handler;
import com.ytgld.seeking_immortals.SeekingImmortalsMod;
import com.ytgld.seeking_immortals.renderer.MRender;
import com.ytgld.seeking_immortals.renderer.MoonPost;
import com.ytgld.seeking_immortals.renderer.light.Light;
import com.ytgld.seeking_immortals.test_entity.lotus_entity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class LotusEntityRender <T extends lotus_entity> extends net.minecraft.client.renderer.entity.EntityRenderer<T> {
    public LotusEntityRender(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(T t) {
        return null;
    }

    @Override
    public boolean shouldRender(T livingEntity, Frustum camera, double camX, double camY, double camZ) {
        return true;
    }

    @Override
    public void render(T p_entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        MoonPost.renderEffectForNextTick(SeekingImmortalsMod.POST);

        double x = Mth.lerp(partialTick, p_entity.xOld, p_entity.getX());
        double y = Mth.lerp(partialTick, p_entity.yOld, p_entity.getY());
        double z = Mth.lerp(partialTick, p_entity.zOld, p_entity.getZ());
        poseStack.pushPose();
        poseStack.translate(p_entity.getX()-x, p_entity.getY()-y,p_entity.getZ() -z);
        float f = p_entity.lightSize / 1.5f;
        float f1 = Handler.getDistanceToGround(p_entity);
        int alpha = 140 -  p_entity.tickCount ;
        if (alpha < 0) {
            alpha = 0;
        }
        if (p_entity.canSee) {
            if (p_entity.isFollower || p_entity.isBoom){
                float size = p_entity.boomAnimationTime / 20f;
                if (size > 1) {
                    size = 1;
                }
                orb(poseStack,bufferSource.getBuffer(MRender.entityShadowsEEKING(ResourceLocation.fromNamespaceAndPath(SeekingImmortalsMod.MODID,"textures/gui/light.png"))),240,size, Light.ARGB.color(alpha, p_entity.r, p_entity.g, p_entity.b));
            }
        }
        if (f1 > 0.0F&&f>0) {
            Light.renderShadow(poseStack, bufferSource, p_entity, p_entity.level(), f,p_entity.r,p_entity.g,p_entity.b);
        }
        float agee = 100 -  p_entity.tickCount*2.2f;
        if (agee < -10) {
            agee = -10;
        }
        {
            poseStack.pushPose();
            poseStack.translate(0,2.75,0);
            poseStack.scale(1.25f -agee/100f, 1.25f-agee/100f, 1.25f-agee/100f);

            if (p_entity.isBoom) {
                float b =50- p_entity.boomAnimationTime;
                if (b < 0) {
                    b = 0;
                }
                poseStack.pushPose();
                poseStack.translate(0,-10,0);
                renderDisk(poseStack,bufferSource.getBuffer(MRender.endBloodOutline),20,10,p_entity.r, p_entity.g, p_entity.b,100,b/30f);
                poseStack.popPose();

            }

            if (p_entity.canSee) {
                if (p_entity.isFollower || p_entity.isBoom) {

                    follower(poseStack, bufferSource.getBuffer(MRender.entityShadowsEEKING(ResourceLocation.fromNamespaceAndPath(SeekingImmortalsMod.MODID,
                            "textures/gui/light.png"))), Light.ARGB.color(alpha, p_entity.r, p_entity.g, p_entity.b),alpha,alpha,agee);
                }
                float orbSize;
                orbSize = f / 25f;
                poseStack.pushPose();
                poseStack.scale(0.75f, 0.75f, 0.75f);
                poseStack.translate(0, 0.25, 0);
                poseStack.mulPose(Axis.YN.rotationDegrees(45));
                if (p_entity.isFollower || p_entity.isBoom) {
                    follower(poseStack, bufferSource.getBuffer(MRender.entityShadowsEEKING(ResourceLocation.fromNamespaceAndPath(SeekingImmortalsMod.MODID,
                            "textures/gui/light.png"))), Light.ARGB.color(alpha, p_entity.r, p_entity.g, p_entity.b),alpha,alpha,agee);
                }
                poseStack.scale(1.5f, 1.5f, 1.5f);
                orb(poseStack, bufferSource.getBuffer(MRender.endBloodOutline), 240, orbSize, 1);
                orb(poseStack, bufferSource.getBuffer(MRender.ging()), 240, orbSize, 1);
                poseStack.popPose();
            }
            poseStack.popPose();
        }


        poseStack.popPose();

    }
    private static void renderDisk(PoseStack poseStack, VertexConsumer builder, float height, int segments, float red, float green, float blue, float alpha, float radius) {
        // 绘制圆柱底部
        builder.addVertex(poseStack.last().pose(), 0, 0, 0).setColor(red, green, blue, alpha).setUv2(240,240).setUv1(0,0).setUv(0,0).setOverlay(OverlayTexture.NO_OVERLAY);
        for (int i = 0; i <= segments; i++) {
            float angle = (float) i / segments * (float) Math.PI * 2;
            float x = (float) Math.cos(angle) * radius;
            float z = (float) Math.sin(angle) * radius;
            builder.addVertex(poseStack.last().pose(), x, 0, z).setColor(red, green, blue, alpha).setUv2(240,240).setUv1(0,0).setUv(0,0).setOverlay(OverlayTexture.NO_OVERLAY);
        }

        // 绘制圆柱顶部
        builder.addVertex(poseStack.last().pose(), 0, height, 0).setColor(red, green, blue, alpha).setUv2(240,240).setUv1(0,0).setUv(0,0).setOverlay(OverlayTexture.NO_OVERLAY);
        for (int i = 0; i <= segments; i++) {
            float angle = (float) i / segments * (float) Math.PI * 2;
            float x = (float) Math.cos(angle) * radius;
            float z = (float) Math.sin(angle) * radius;
            builder.addVertex(poseStack.last().pose(), x, height, z).setColor(red, green, blue, alpha).setUv2(240,240).setUv1(0,0).setUv(0,0).setOverlay(OverlayTexture.NO_OVERLAY);
        }

        // 绘制圆柱侧面
        for (int i = 0; i <= segments; i++) {
            float angle = (float) i / segments * (float) Math.PI * 2;
            float x = (float) Math.cos(angle) * radius;
            float z = (float) Math.sin(angle) * radius;
            builder.addVertex(poseStack.last().pose(), x, 0, z).setColor(red, green, blue, alpha).setUv2(240,240).setUv1(0,0).setUv(0,0).setOverlay(OverlayTexture.NO_OVERLAY);
            builder.addVertex(poseStack.last().pose(), x, height, z).setColor(red, green, blue, alpha).setUv2(240,240).setUv1(0,0).setUv(0,0).setOverlay(OverlayTexture.NO_OVERLAY);
        }
    }


    private void  follower (PoseStack poseStack, VertexConsumer vertexConsumer, int color, int a1, int a2,float agee){
        poseStack.pushPose();
        poseStack.mulPose(Axis.XP.rotationDegrees(180));
        {
            render_triangle(poseStack, vertexConsumer, (float) 1, color,a1,a2,agee);
            poseStack.pushPose();
            poseStack.mulPose(Axis.YP.rotationDegrees(180));
            render_triangle(poseStack, vertexConsumer, (float) 1, color,a1,a2,agee);
            poseStack.popPose();
        }
        poseStack.popPose();
    }

    public void render_triangle(PoseStack poseStack, VertexConsumer vertexConsumer, float size, int color,int a1,int a2,float age) {
        {
            poseStack.pushPose();
            poseStack.mulPose(Axis.YN.rotationDegrees(0.0F)); // 调整为0度
            poseStack.mulPose(Axis.XN.rotationDegrees(30F+age));
            poseStack.translate(0.0F,  2.0F - age / 200f, 0.0F);
            renderSphere1(poseStack, vertexConsumer, 240, size, color,a1,a2);
            poseStack.popPose();
        }
        {
            poseStack.pushPose();
            poseStack.mulPose(Axis.YN.rotationDegrees(90)); // 调整为90度
            poseStack.mulPose(Axis.XN.rotationDegrees(-30F-age));
            poseStack.translate(0.0F,  2.0F - age / 200f, 0.0F);
            renderSphere1(poseStack, vertexConsumer, 240, size, color,a1,a2);
            poseStack.popPose();
        }
        {
            poseStack.pushPose();
            poseStack.mulPose(Axis.YN.rotationDegrees(180)); // 调整为180度
            poseStack.mulPose(Axis.XN.rotationDegrees(-30F-age));
            poseStack.translate(0.0F, 2.0F - age / 200f, 0.0F);
            renderSphere1(poseStack, vertexConsumer, 240, size, color,a1,a2);
            poseStack.popPose();
        }
        {
            poseStack.pushPose();
            poseStack.mulPose(Axis.YN.rotationDegrees(270)); // 调整为270度
            poseStack.mulPose(Axis.XN.rotationDegrees(30F+age));
            poseStack.translate(0.0F,  2.0F - age / 200f, 0.0F);
            renderSphere1(poseStack, vertexConsumer, 240, size, color,a1,a2);
            poseStack.popPose();
        }
    }

    public void renderSphere1(@NotNull PoseStack matrices, @NotNull VertexConsumer vertexConsumer, int light, float s, int color,int a1,int a2) {
        int stacks = 20;
        int slices = 20;
        for (int i = 0; i < stacks; ++i) {
            float phi0 = (float) Math.PI * ((i + 0) / (float) stacks);
            float phi1 = (float) Math.PI * ((i + 1) / (float) stacks);
            for (int j = 0; j < slices; ++j) {
                float theta0 = (float) (2 * Math.PI) * ((j + 0) / (float) slices);
                float theta1 = (float) (2 * Math.PI) * ((j + 1) / (float) slices);

                float x0 = s * (float) Math.sin(phi0) * (float) Math.cos(theta0);
                float y0 = s * (float) Math.cos(phi0);
                float z0 = s * (float) Math.sin(phi0) * (float) Math.sin(theta0);

                float x1 = s * (float) Math.sin(phi0) * (float) Math.cos(theta1);
                float y1 = s * (float) Math.cos(phi0);
                float z1 = s * (float) Math.sin(phi0) * (float) Math.sin(theta1);

                float x2 = s * (float) Math.sin(phi1) * (float) Math.cos(theta1);
                float y2 = s * (float) Math.cos(phi1);
                float z2 = s * (float) Math.sin(phi1) * (float) Math.sin(theta1);

                float x3 = s * (float) Math.sin(phi1) * (float) Math.cos(theta0);
                float y3 = s * (float) Math.cos(phi1);
                float z3 = s * (float) Math.sin(phi1) * (float) Math.sin(theta0);

                matrices.pushPose();
                matrices.scale(1,0,2);

                // 设置顶点颜色时使用动态alpha值
                int color0 = (a1 << 24) | (color & 0x00FFFFFF);
                int color1 = (a1 << 24) | (color & 0x00FFFFFF);


                int color2 = (a2 << 24) | (color & 0x00FFFFFF);
                int color3 = (a2 << 24) | (color & 0x00FFFFFF);

                vertexConsumer.addVertex(matrices.last().pose(), x0, y0, z0).setColor(color0).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                vertexConsumer.addVertex(matrices.last().pose(), x1, y1, z1).setColor(color1).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                vertexConsumer.addVertex(matrices.last().pose(), x2, y2, z2).setColor(color2).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                vertexConsumer.addVertex(matrices.last().pose(), x3, y3, z3).setColor(color3).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                matrices.popPose();
            }
        }
    }
    public void orb(@NotNull PoseStack matrices, @NotNull VertexConsumer vertexConsumer, int light, float s, int color) {
        int stacks = 20;
        int slices = 20;
        for (int i = 0; i < stacks; ++i) {
            float phi0 = (float) Math.PI * ((i + 0) / (float) stacks);
            float phi1 = (float) Math.PI * ((i + 1) / (float) stacks);

            // 计算alpha值，从上半部分的20逐渐提高到下半部分的150
            int alpha0 = (int) (20 + (150 - 20) * (phi0 / Math.PI));
            int alpha1 = (int) (20 + (150 - 20) * (phi1 / Math.PI));

            for (int j = 0; j < slices; ++j) {
                float theta0 = (float) (2 * Math.PI) * ((j + 0) / (float) slices);
                float theta1 = (float) (2 * Math.PI) * ((j + 1) / (float) slices);

                float x0 = s * (float) Math.sin(phi0) * (float) Math.cos(theta0);
                float y0 = s * (float) Math.cos(phi0);
                float z0 = s * (float) Math.sin(phi0) * (float) Math.sin(theta0);

                float x1 = s * (float) Math.sin(phi0) * (float) Math.cos(theta1);
                float y1 = s * (float) Math.cos(phi0);
                float z1 = s * (float) Math.sin(phi0) * (float) Math.sin(theta1);

                float x2 = s * (float) Math.sin(phi1) * (float) Math.cos(theta1);
                float y2 = s * (float) Math.cos(phi1);
                float z2 = s * (float) Math.sin(phi1) * (float) Math.sin(theta1);

                float x3 = s * (float) Math.sin(phi1) * (float) Math.cos(theta0);
                float y3 = s * (float) Math.cos(phi1);
                float z3 = s * (float) Math.sin(phi1) * (float) Math.sin(theta0);

                matrices.pushPose();
                // 设置顶点颜色时使用动态alpha值
                int color0 = (alpha0 << 24) | (color & 0x00FFFFFF);
                int color1 = (alpha0 << 24) | (color & 0x00FFFFFF);


                int color2 = (alpha1 << 24) | (color & 0x00FFFFFF);
                int color3 = (alpha1 << 24) | (color & 0x00FFFFFF);

                vertexConsumer.addVertex(matrices.last().pose(), x0, y0, z0).setColor(color0).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                vertexConsumer.addVertex(matrices.last().pose(), x1, y1, z1).setColor(color1).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                vertexConsumer.addVertex(matrices.last().pose(), x2, y2, z2).setColor(color2).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                vertexConsumer.addVertex(matrices.last().pose(), x3, y3, z3).setColor(color3).setOverlay(OverlayTexture.NO_OVERLAY).setUv(0, 0).setUv2(light, light).setNormal(1, 0, 0);
                matrices.popPose();
            }
        }
    }

}


