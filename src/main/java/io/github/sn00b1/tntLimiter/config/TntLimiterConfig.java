package io.github.sn00b1.tntLimiter.config;

import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import io.github.sn00b1.tntLimiter.TntLimiter;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.ResourceLocation;

public class TntLimiterConfig {
    private static Object GsonBuilder;
    public static ConfigClassHandler<TntLimiterConfig> HANDLER = ConfigClassHandler.createBuilder(TntLimiterConfig.class)
            .id(ResourceLocation.fromNamespaceAndPath(TntLimiter.MOD_ID, "tntlimiter"))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(FabricLoader.getInstance().getConfigDir().resolve("tntlimiter.json"))
                    .setJson5(false)
                    .build())
            .build();

    @SerialEntry
    public static int DetectionRadius = 50;

    @SerialEntry
    public static int TntLimit = 20;
}
