package com.nyfaria.nyfsspiders.platform.services;

import com.nyfaria.nyfsspiders.common.SpiderData;
import com.nyfaria.nyfsspiders.mixin.ClimberEntityMixin;
import net.minecraft.world.entity.monster.Spider;

public interface IPlatformHelper {

    /**
     * Gets the name of the current platform
     *
     * @return The name of the current platform.
     */
    String getPlatformName();

    /**
     * Checks if a mod with the given id is loaded.
     *
     * @param modId The mod to check if it is loaded.
     * @return True if the mod is loaded, false otherwise.
     */
    boolean isModLoaded(String modId);

    /**
     * Check if the game is currently in a development environment.
     *
     * @return True if in a development environment, false otherwise.
     */
    boolean isDevelopmentEnvironment();

    /**
     * Gets the name of the environment type as a string.
     *
     * @return The name of the environment type.
     */
    default String getEnvironmentName() {

        return isDevelopmentEnvironment() ? "development" : "production";
    }
    SpiderData getSpiderData(Spider spider);
    void setSpiderData(Spider spider, SpiderData data);
}