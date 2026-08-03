package com.aengstlicher.spyratiofix;

import org.slf4j.Logger;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(SpyglassRatioFix.MODID)
public class SpyglassRatioFix {

    public static final String MODID = "spyglassratiofix";
    public static final Logger LOGGER = LogUtils.getLogger();

    public SpyglassRatioFix(IEventBus modEventBus) {
        LOGGER.info("Spyglass Ratio Fix initialized.");
    }
}