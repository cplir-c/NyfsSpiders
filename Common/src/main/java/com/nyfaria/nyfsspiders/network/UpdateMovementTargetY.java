package com.nyfaria.nyfsspiders.network;

import com.nyfaria.nyfsspiders.Constants;
import com.nyfaria.nyfsspiders.client.CommonClientClass;
import com.nyfaria.nyfsspiders.platform.Services;
import commonnetwork.networking.data.PacketContext;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Spider;

public record UpdateMovementTargetY(int spiderId, float y) {

    public static ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(Constants.MODID,"update_movement_target_y");

    public static UpdateMovementTargetY decode(FriendlyByteBuf buf){
        return new UpdateMovementTargetY(buf.readInt(),buf.readFloat());
    }

    public void encode(FriendlyByteBuf buf){
        buf.writeInt(spiderId);
        buf.writeFloat(y);
    }
    public static void handle(PacketContext<UpdateMovementTargetY> context) {
        Services.PLATFORM.getSpiderData((Spider)CommonClientClass.getLevel().getEntity(context.message().spiderId)).setMovementTargetY(context.message().y());
    }
}
