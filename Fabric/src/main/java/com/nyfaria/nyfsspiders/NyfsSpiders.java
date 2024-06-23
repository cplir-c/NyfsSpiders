package com.nyfaria.nyfsspiders;

import com.nyfaria.nyfsspiders.common.SpiderData;
import com.nyfaria.nyfsspiders.network.NetworkInit;
import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeConfigRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.fabricmc.fabric.impl.attachment.AttachmentRegistryImpl;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Spider;
import net.neoforged.fml.config.ModConfig;

public class NyfsSpiders implements ModInitializer {


    public static AttachmentType<SpiderData> SPIDER_DATA =
            AttachmentRegistryImpl.<SpiderData>builder()
                    .initializer(SpiderData::new)
                    .persistent(SpiderData.CODEC)
                    .buildAndRegister(ResourceLocation.fromNamespaceAndPath(Constants.MODID, "spider_data"));
    @Override
    public void onInitialize() {

        // This method is invoked by the Fabric mod loader when it is ready
        // to load your mod. You can access Fabric and Common code in this
        // project.

        // Use Fabric to bootstrap the Common mod.
        Constants.LOG.info("Hello Fabric world!");
        CommonClass.init();
        NeoForgeConfigRegistry.INSTANCE.register(Constants.MODID, ModConfig.Type.COMMON, Config.COMMON);
        NetworkInit.load();

    }
}
