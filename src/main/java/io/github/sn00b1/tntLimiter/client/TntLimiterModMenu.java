package io.github.sn00b1.tntLimiter.client;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import io.github.sn00b1.tntLimiter.config.TntLimiterConfig;
import net.minecraft.network.chat.Component;

public class TntLimiterModMenu implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> YetAnotherConfigLib.createBuilder()
                .title(Component.translatable("tntlimiter.config.title"))
                .category(ConfigCategory.createBuilder()
                        .name(Component.translatable("tntlimiter.config.category"))
                        .option(Option.<Integer>createBuilder()
                                .name(Component.translatable("tntlimiter.config.detectRadius"))
                                .binding(50, () -> TntLimiterConfig.DetectionRadius, newVal -> TntLimiterConfig.DetectionRadius = newVal)
                                .controller(opt -> IntegerSliderControllerBuilder.create(opt).range(2,100).step(1))
                                .build())
                        .option(Option.<Integer>createBuilder()
                                .name(Component.translatable("tntlimiter.config.tntLimit"))
                                .binding(20, () -> TntLimiterConfig.TntLimit, newVal -> TntLimiterConfig.TntLimit = newVal)
                                .controller(opt -> IntegerSliderControllerBuilder.create(opt).range(0,100).step(1))
                                .build())
                        .build()).save(TntLimiterConfig.HANDLER::save)
                .build().generateScreen(parent);
    }
}
