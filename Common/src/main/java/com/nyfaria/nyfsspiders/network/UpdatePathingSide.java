package com.nyfaria.nyfsspiders.network;

import com.nyfaria.nyfsspiders.Constants;
import com.nyfaria.nyfsspiders.client.CommonClientClass;
import com.nyfaria.nyfsspiders.platform.Services;
import commonnetwork.networking.data.PacketContext;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Spider;
import org.joml.Vector3f;

public record UpdatePathingSide(int spiderId, int index, Direction x) {

    public static ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(Constants.MODID,"update_pathing_side");

    public static UpdatePathingSide decode(FriendlyByteBuf buf){
        return new UpdatePathingSide(buf.readInt(),buf.readInt(),Direction.byName(buf.readUtf()));
    }

    public void encode(FriendlyByteBuf buf){
        buf.writeInt(spiderId);
        buf.writeInt(index);
        buf.writeUtf(x.getName());
    }
    public static void handle(PacketContext<UpdatePathingSide> context) {
        Services.PLATFORM.getSpiderData((Spider)CommonClientClass.getLevel().getEntity(context.message().spiderId)).setPathingSide(context.message().index,context.message().x());
    }
}
