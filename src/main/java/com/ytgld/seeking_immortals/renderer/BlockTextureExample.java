package com.ytgld.seeking_immortals.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.Random;

public class BlockTextureExample {
    public static ResourceLocation getTopBlockTexture(BlockState blockState,Direction direction) {
        // 获取方块的注册名
        ResourceLocation blockRegistryName = BuiltInRegistries.BLOCK.getKey(blockState.getBlock());
        // 创建模型资源位置
        ModelResourceLocation modelResourceLocation = new ModelResourceLocation(blockRegistryName, "normal");

        // 获取烘焙模型
        BakedModel bakedModel = Minecraft.getInstance().getModelManager().getModel(modelResourceLocation);

        // 获取模型的四边形列表
        List<BakedQuad> quads = bakedModel.getQuads(blockState, direction, RandomSource.create());

        // 遍历四边形列表以获取材质（TextureAtlasSprite）
        for (BakedQuad quad : quads) {
            TextureAtlasSprite texture = quad.getSprite();
            return texture.atlasLocation();
        }
        return null;
    }

}
