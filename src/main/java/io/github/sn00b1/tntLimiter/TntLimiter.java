package io.github.sn00b1.tntLimiter;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import io.github.sn00b1.tntLimiter.config.TntLimiterConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.Commands;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TntLimiter implements ModInitializer {
    public static final String MOD_ID = "tntlimiter";
    public static final Logger LOGGER = LogManager.getLogger("TNT Limiter");

    @Override
    public void onInitialize() {
        TntLimiterConfig.HANDLER.load();
        LOGGER.info("Loaded TNT Limiter Config");

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) ->
                dispatcher.register(Commands.literal("tntlimiter").requires(source -> source.hasPermission(2))
                .then(Commands.literal("DetectionRadius")
                        .then(Commands.argument("radius", IntegerArgumentType.integer(1))
                                .executes(context -> {
                                    TntLimiterConfig.DetectionRadius = IntegerArgumentType.getInteger(context, "radius");
                                    TntLimiterConfig.HANDLER.save();
                                    return Command.SINGLE_SUCCESS;
                                })))
                .then(Commands.literal("TntLimit")
                        .then(Commands.argument("limit", IntegerArgumentType.integer(0))
                                .executes(context -> {
                                    TntLimiterConfig.TntLimit = IntegerArgumentType.getInteger(context, "limit");
                                    TntLimiterConfig.HANDLER.save();
                                    return Command.SINGLE_SUCCESS;
                                })))));
    }
}
