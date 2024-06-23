package com.nyfaria.nyfsspiders.platform;

import com.nyfaria.nyfsspiders.NyfsSpiders;
import com.nyfaria.nyfsspiders.common.SpiderData;
import com.nyfaria.nyfsspiders.platform.services.IPlatformHelper;
import net.minecraft.world.entity.monster.Spider;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;

public class NeoForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {

        return "NeoForge";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return !FMLLoader.isProduction();
    }

    @Override
    public SpiderData getSpiderData(Spider spider) {
        SpiderData data = spider.getData(NyfsSpiders.SPIDER_DATA);
        if (data.getSpider() == null)
            data.setSpider(spider);
        return data;
    }

    @Override
    public void setSpiderData(Spider spider, SpiderData data) {
        spider.setData(NyfsSpiders.SPIDER_DATA, data);
    }
}