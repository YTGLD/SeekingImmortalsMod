package com.ytgld.seeking_immortals.client.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.ytgld.seeking_immortals.renderer.MRender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

import java.util.List;

import static net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY;

public class SevenSword {
    public SevenSword(@NotNull PoseStack poseStack,
                      @NotNull MultiBufferSource bufferSource
    ) {
        renderCube(poseStack,bufferSource,240,MRender.DRAGON_RAYS,0.7f);
    }
    public void renderCube(@NotNull PoseStack matrices, @NotNull MultiBufferSource vertexConsumers, int light, RenderType renderType,float a  ) {
        PoseStack.Pose pose = matrices.last();
        Matrix4f matrix4f = pose.pose();
        float x = 0.5F;
        float y = 0.5F;
        float z = 0.5F;
        float size = 0.5F;

        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(renderType);


        {
            vertexConsumer.addVertex(matrix4f, x - size, y - size, z - size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x + size, y - size, z - size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x + size, y + size, z - size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x - size, y + size, z - size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
        }

        {
            vertexConsumer.addVertex(matrix4f, x - size, y + size, z + size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x + size, y + size, z + size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x + size, y - size, z + size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x - size, y - size, z + size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
        }

        {
            vertexConsumer.addVertex(matrix4f, x - size, y - size, z - size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x - size, y - size, z + size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x - size, y + size, z + size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x - size, y + size, z - size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
        }
        {
            vertexConsumer.addVertex(matrix4f, x + size, y - size, z - size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x + size, y - size, z + size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x + size, y + size, z + size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x + size, y + size, z - size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
        }
        {
            vertexConsumer.addVertex(matrix4f, x - size, y + size, z - size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x + size, y + size, z - size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x + size, y + size, z + size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x - size, y + size, z + size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
        }
        {
            vertexConsumer.addVertex(matrix4f, x - size, y - size, z + size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x + size, y - size, z + size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x + size, y - size, z - size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
            vertexConsumer.addVertex(matrix4f, x - size, y - size, z - size).setUv(0, 0).setUv2(light, light).setOverlay(NO_OVERLAY).setNormal(0, 0, 0).setColor(0, 0, 1, a);
        }
    }

}
