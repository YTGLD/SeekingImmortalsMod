package com.ytgld.seeking_immortals;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.logging.LogUtils;
import com.ytgld.seeking_immortals.client.particle.ParticleRenderer;
import com.ytgld.seeking_immortals.client.particle.blood;
import com.ytgld.seeking_immortals.client.particle.cube;
import com.ytgld.seeking_immortals.event.now.EventHandler;
import com.ytgld.seeking_immortals.event.old.AdvancementEvt;
import com.ytgld.seeking_immortals.event.old.NewEvent;
import com.ytgld.seeking_immortals.init.*;
import com.ytgld.seeking_immortals.item.an_element.NightmareTooltip;
import com.ytgld.seeking_immortals.item.nightmare.AllTip;
import com.ytgld.seeking_immortals.item.nightmare.ToolTip;
import com.ytgld.seeking_immortals.renderer.MRender;
import com.ytgld.seeking_immortals.test_entity.client.OrbEntityRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.Locale;
import java.util.function.Function;

@Mod(SeekingImmortalsMod.MODID)
public class SeekingImmortalsMod
{
    public static final String MODID = "seeking_immortals";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final ResourceLocation POST = ResourceLocation.fromNamespaceAndPath(SeekingImmortalsMod.MODID,
            "shaders/post/entity_outline.json");


    public SeekingImmortalsMod(IEventBus eventBus, ModContainer modContainer) {

        modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);

        NeoForge.EVENT_BUS.register(new NewEvent());
        NeoForge.EVENT_BUS.register(new AdvancementEvt());
        NeoForge.EVENT_BUS.register(new EventHandler());

        Effects.REGISTRY.register(eventBus);
        AttReg.REGISTRY.register(eventBus);
        LootReg.REGISTRY.register(eventBus);
        DataReg.REGISTRY.register(eventBus);
        Items.REGISTRY.register(eventBus);
        EntityTs.REGISTRY.register(eventBus);
        Particles.PARTICLE_TYPES.register(eventBus);
        Tab.TABS.register(eventBus);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.fc);
        modContainer.registerConfig(ModConfig.Type.CLIENT, ClientConfig.CLIENT);
    }
    public static RenderLevelStageEvent.Stage stage_particles ;


    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void RegisterClientTooltipComponentFactoriesEvent(RegisterClientTooltipComponentFactoriesEvent event){
            event.register(NightmareTooltip.class, Function.identity());
            event.register(ToolTip.class, Function.identity());
        }
        @SubscribeEvent
        public static void EntityRenderersEvent(EntityRenderersEvent.RegisterRenderers event){
            event.registerEntityRenderer(EntityTs.orb_entity.get(), OrbEntityRenderer::new);
        }
        @SubscribeEvent
        public static void RegisterStageEvent(RenderLevelStageEvent.RegisterStageEvent event) {
            RenderType renderType = MRender.beacon.apply(ResourceLocation.fromNamespaceAndPath(SeekingImmortalsMod.MODID, "textures/p_blood.png"), true);
            stage_particles = event.register(ResourceLocation.fromNamespaceAndPath(SeekingImmortalsMod.MODID, "seeking_particles"),
                    renderType);
        }
        @SubscribeEvent
        public static void registerFactories(RegisterParticleProvidersEvent event) {
            event.registerSpriteSet(Particles.blood.get(), blood.Provider::new);
            event.registerSpriteSet(Particles.cube.get(), cube.Provider::new);

        }
        @SubscribeEvent
        public static void EntityRenderersEvent(RegisterShadersEvent event) {
            try {

                event.registerShader(new ShaderInstance(event.getResourceProvider(),
                        ResourceLocation.fromNamespaceAndPath(MODID,"rendertype_mls"),
                        DefaultVertexFormat.POSITION_TEX_COLOR),MRender::setShaderInstance_mls);

                event.registerShader(new ShaderInstance(event.getResourceProvider(),
                        ResourceLocation.fromNamespaceAndPath(MODID, "rendertype_ging"),
                        DefaultVertexFormat.POSITION_TEX_COLOR),MRender::setShaderInstance_ging);

                event.registerShader(new ShaderInstance(event.getResourceProvider(),
                        ResourceLocation.fromNamespaceAndPath(MODID,"p_blood"),
                        DefaultVertexFormat.POSITION_TEX_COLOR),MRender::setShaderInstance_p_blood);

            }catch (IOException exception){
                exception.printStackTrace();
            }
        }
    }
    public static ResourceLocation prefix(String name) {
        return ResourceLocation.fromNamespaceAndPath(MODID, name.toLowerCase(Locale.ROOT));
    }
}
