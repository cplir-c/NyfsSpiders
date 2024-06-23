package com.nyfaria.nyfsspiders.network;

import com.nyfaria.nyfsspiders.Constants;
import com.nyfaria.nyfsspiders.client.CommonClientClass;
import com.nyfaria.nyfsspiders.platform.Services;
import commonnetwork.networking.data.PacketContext;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Spider;

public record UpdateMovementTargetX(int spiderId, float x) {

    public static ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(Constants.MODID,"update_movement_target_x");

    public static UpdateMovementTargetX decode(FriendlyByteBuf buf){
        return new UpdateMovementTargetX(buf.readInt(),buf.readFloat());
    }

    public void encode(FriendlyByteBuf buf){
        buf.writeInt(spiderId);
        buf.writeFloat(x);
    }
    public static void handle(PacketContext<UpdateMovementTargetX> context) {
        Services.PLATFORM.getSpiderData((Spider)CommonClientClass.getLevel().getEntity(context.message().spiderId)).setMovementTargetX(context.message().x());
    }
}
