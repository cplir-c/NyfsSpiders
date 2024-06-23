package com.nyfaria.nyfsspiders.network;

import com.nyfaria.nyfsspiders.Constants;
import com.nyfaria.nyfsspiders.client.CommonClientClass;
import com.nyfaria.nyfsspiders.platform.Services;
import commonnetwork.networking.data.PacketContext;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Spider;

public record UpdateMovementTargetZ(int spiderId, float z) {

    public static ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(Constants.MODID,"update_movement_target_z");

    public static UpdateMovementTargetZ decode(FriendlyByteBuf buf){
        return new UpdateMovementTargetZ(buf.readInt(),buf.readFloat());
    }

    public void encode(FriendlyByteBuf buf){
        buf.writeInt(spiderId);
        buf.writeFloat(z);
    }
    public static void handle(PacketContext<UpdateMovementTargetZ> context) {
        Services.PLATFORM.getSpiderData((Spider)CommonClientClass.getLevel().getEntity(context.message().spiderId)).setMovementTargetZ(context.message().z());
    }
}
