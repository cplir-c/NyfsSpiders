package com.nyfaria.nyfsspiders;


import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(Constants.MODID)
public class NyfsSpiders {

    public NyfsSpiders(IEventBus eventBus) {
        Constants.LOG.info("Hello NeoForge world!");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON, "nyfsspiders.toml");
        CommonClass.init();
    }
}