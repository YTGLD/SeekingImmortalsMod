package com.ytgld.seeking_immortals;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class Config {
    public static Config SERVER;
    public static ModConfigSpec fc;

    static {
        final Pair<Config, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(Config::new);
        SERVER = specPair.getLeft();
        fc = specPair.getRight();
    }

    public final ModConfigSpec.BooleanValue nightmare_base_black_eye ;
    public final ModConfigSpec.DoubleValue nightmare_base_stone ;
    public final ModConfigSpec.DoubleValue nightmare_base_fool ;
    public final ModConfigSpec.IntValue nightmare_base_insight ;
    public final ModConfigSpec.IntValue nightmare_base_redemption ;
    public final ModConfigSpec.IntValue nightmare_base_reversal ;
    public final ModConfigSpec.IntValue nightmare_base_start ;
    public final ModConfigSpec.DoubleValue disintegrating_stone ;
    public final ModConfigSpec.IntValue give_nightmare_base_insight_drug ;
    public final ModConfigSpec.IntValue immortal ;






    public Config(ModConfigSpec.Builder BUILDER){
        BUILDER.push("噩梦");

        nightmareBaseMaxItem = BUILDER
                .translation("seeking_immortals.config.nightmareBaseMaxItem")
                .defineInRange("nightmare_", 3, 0, 7);

        Nightecora = BUILDER
                .translation("seeking_immortals.config.Nightecora")
                .defineInRange("Nightecora_", 10, 0, 100);
        nightmare_base_redemption_deception = BUILDER
                .translation("seeking_immortals.config.nightmare_base_redemption_deception")
                .defineInRange("nightmare_base_redemption_deception", 100, 0, 100);
        nightmare_base_redemption_deception_time = BUILDER
                .translation("seeking_immortals.config.nightmare_base_redemption_deception_time")
                .defineInRange("nightmare_base_redemption_deception_time", 7, 0, 100);

        nightmare_base_fool_bone = BUILDER
                .translation("seeking_immortals.config.nightmare_base_fool_bone")
                .defineInRange("nightmare_base_fool_bone_", 1.2f, 0, 9999);

        nightmare_base_insight_drug = BUILDER
                .translation("seeking_immortals.config.nightmare_base_insight_drug")
                .defineInRange("nightmare_base_insight_drug_", 50, 0, 99999);

        nightmare_base_insight_drug_2 = BUILDER
                .translation("seeking_immortals.config.nightmare_base_insight_drug_2")
                .defineInRange("nightmare_base_insight_drug_2", 8, 0, 99999);

        nightmare_base_insight_insane = BUILDER
                .translation("seeking_immortals.config.nightmare_base_insight_insane")
                .defineInRange("nightmare_base_insight_insane_", 25, 0, 99999);


        {
            BUILDER.push("不朽轮回之印章");

            immortal = BUILDER
                    .translation("seeking_immortals.config.immortal")
                    .defineInRange("immortal_", 70, 0, 100);

            BUILDER.pop();

            BUILDER.push("裂天石");
            disintegrating_stone = BUILDER
                    .translation("seeking_immortals.config.disintegrating_stone")
                    .defineInRange("disintegrating_stone", 1.3f, 0, 10000);
            BUILDER.pop();

            BUILDER.push("邪念之窥眸");
            nightmare_base_black_eye = BUILDER
                    .translation("seeking_immortals.config.nightmare_base_black_eye")
                    .define("nightmare_base_black_eye", true);
            BUILDER.pop();

            BUILDER.push("死兆方尖碑");
            nightmare_base_stone = BUILDER
                    .translation("seeking_immortals.config.nightmare_base_stone")
                    .defineInRange("nightmare_base_stone", 5f,1,999);
            BUILDER.pop();

            BUILDER.push("愚者之危");
            nightmare_base_fool = BUILDER
                    .translation("seeking_immortals.config.nightmare_base_fool")
                    .defineInRange("nightmare_base_fool", 0.5f,0,1);
            BUILDER.pop();

            BUILDER.push("噩梦洞悉者");
            nightmare_base_insight = BUILDER
                    .translation("seeking_immortals.config.nightmare_base_insight")
                    .defineInRange("nightmare_base_insight", 2,0,1000);
            BUILDER.pop();

            BUILDER.push("“救赎”");
            nightmare_base_redemption = BUILDER
                    .translation("seeking_immortals.config.nightmare_base_redemption")
                    .defineInRange("nightmare_base_redemption", 15,0,100);
            BUILDER.pop();

            BUILDER.push("颠倒之物");
            nightmare_base_reversal = BUILDER
                    .translation("seeking_immortals.config.nightmare_base_reversal")
                    .defineInRange("nightmare_base_reversal", 25,0,100);
            BUILDER.pop();

            BUILDER.push("噩梦之起始");
            nightmare_base_start = BUILDER
                    .translation("seeking_immortals.config.nightmare_base_start")
                    .defineInRange("nightmare_base_start", 75,0,100);
            BUILDER.pop();
            BUILDER.push("获取");
            give_nightmare_base_insight_drug = BUILDER
                    .translation("seeking_immortals.config.give_nightmare_base_insight_drug")
                    .defineInRange("give_nightmare_base_insight_drug", 9,1,100);
            BUILDER.pop();

            BUILDER.push("惶恐肉瘤");
            nightmare_base_black_eye_eye = BUILDER
                    .translation("seeking_immortals.config.nightmare_base_black_eye_eye")
                    .defineInRange("nightmare_base_black_eye_eye", 1.25f,0.01f,100);
            BUILDER.pop();

            BUILDER.push("幽怨之魂");
            nightmare_base_fool_soul = BUILDER
                    .translation("seeking_immortals.config.nightmare_base_fool_soul")
                    .defineInRange("nightmare_base_fool_soul", 1,0.01f,100);
            nightmare_base_fool_soul2 = BUILDER
                    .translation("seeking_immortals.config.nightmare_base_fool_soul2")
                    .defineInRange("nightmare_base_fool_soul2", 1,0.01f,100);
            BUILDER.pop();
            BUILDER.push("灵念药戒");
            ring = BUILDER
                    .translation("seeking_immortals.config.ring")
                    .defineInRange("ring", 2,0,100f);
            BUILDER.pop();
            BUILDER.push("虚伪的自尊");
            hypocritical_self_esteem = BUILDER
                    .translation("seeking_immortals.config.hypocritical_self_esteem")
                    .defineInRange("hypocritical_self_esteem", 10,1,100f);
            BUILDER.pop();
            BUILDER.push("邪异古烛");
            candle = BUILDER
                    .translation("seeking_immortals.config.candle")
                    .defineInRange("candle", 1.25,1,100f);
            candle2 = BUILDER
                    .translation("seeking_immortals.config.candle2")
                    .defineInRange("candle2", 1.5,1,100f);
            candle3 = BUILDER
                    .translation("seeking_immortals.config.candle3")
                    .defineInRange("candle3", 100,0,100000f);
            BUILDER.pop();
            BUILDER.push("孤狼");
            wolf = BUILDER
                    .translation("seeking_immortals.config.wolf")
                    .defineInRange("wolf", 10,1,100f);

            BUILDER.pop();
        }


        BUILDER.pop();
    }
    public final ModConfigSpec.DoubleValue wolf ;
    public final ModConfigSpec.DoubleValue candle ;
    public final ModConfigSpec.DoubleValue candle2 ;
    public final ModConfigSpec.DoubleValue candle3 ;



    public final ModConfigSpec.DoubleValue hypocritical_self_esteem ;
    public final ModConfigSpec.DoubleValue ring ;
    public final ModConfigSpec.DoubleValue nightmare_base_fool_soul ;
    public final ModConfigSpec.DoubleValue nightmare_base_fool_soul2 ;

    public final ModConfigSpec.DoubleValue nightmare_base_black_eye_eye ;

    public final   ModConfigSpec.IntValue Nightecora ;


    public final   ModConfigSpec.IntValue nightmare_base_redemption_deception ;

    public final   ModConfigSpec.DoubleValue nightmare_base_fool_bone ;
    public final   ModConfigSpec.IntValue nightmare_base_insight_drug ;
    public final   ModConfigSpec.IntValue nightmare_base_insight_drug_2 ;

    public final   ModConfigSpec.IntValue nightmare_base_insight_insane ;
    public final   ModConfigSpec.IntValue nightmare_base_redemption_deception_time ;


    public final   ModConfigSpec.IntValue nightmareBaseMaxItem ;
    @SubscribeEvent
    public static void onLoad(final ModConfigEvent.Loading configEvent) {
    }

    @SubscribeEvent
    public static void onFileChange(final ModConfigEvent.Reloading configEvent) {
    }

}
