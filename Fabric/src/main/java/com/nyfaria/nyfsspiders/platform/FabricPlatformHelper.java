package com.nyfaria.nyfsspiders.platform;

import com.nyfaria.nyfsspiders.NyfsSpiders;
import com.nyfaria.nyfsspiders.common.SpiderData;
import com.nyfaria.nyfsspiders.platform.services.IPlatformHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.entity.monster.Spider;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public SpiderData getSpiderData(Spider spider) {
        if(spider == null){
            return new SpiderData();
        }
        SpiderData data = spider.getAttachedOrCreate(NyfsSpiders.SPIDER_DATA, ()->new SpiderData(spider));
        if (data.getSpider() == null)
            data.setSpider(spider);
        return data;
    }

    @Override
    public void setSpiderData(Spider spider, SpiderData data) {
        spider.setAttached(NyfsSpiders.SPIDER_DATA, data);
    }
}
