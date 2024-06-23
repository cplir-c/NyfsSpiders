package com.nyfaria.nyfsspiders;


import com.nyfaria.nyfsspiders.common.SpiderData;
import com.nyfaria.nyfsspiders.network.NetworkInit;
import net.minecraft.world.entity.monster.Spider;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

@Mod(Constants.MODID)
public class NyfsSpiders {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, Constants.MODID);

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<SpiderData>> SPIDER_DATA = ATTACHMENT_TYPES.register("quiver", ()->AttachmentType.builder(
                    (e) ->new SpiderData((Spider)e))
            .serialize(SpiderData.CODEC)
            .build());

    public NyfsSpiders(ModContainer container, IEventBus bus) {
        container.registerConfig(ModConfig.Type.COMMON, Config.COMMON, "nyfsspiders.toml");
        CommonClass.init();
        ATTACHMENT_TYPES.register(bus);
        NetworkInit.load();
    }
}