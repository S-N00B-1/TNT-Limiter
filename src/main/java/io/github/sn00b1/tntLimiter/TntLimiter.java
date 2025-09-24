package io.github.sn00b1.tntLimiter;

import io.github.sn00b1.tntLimiter.config.TntLimiterConfig;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TntLimiter implements ModInitializer {
    public static final String MOD_ID = "tntlimiter";
    public static final Logger LOGGER = LogManager.getLogger("TNT Limiter");

    @Override
    public void onInitialize() {
        TntLimiterConfig.HANDLER.load();
        LOGGER.info("Loaded TNT Limiter Config");
    }
}
