package com.nyfaria.nyfsspiders.network;

import com.nyfaria.nyfsspiders.Constants;
import com.nyfaria.nyfsspiders.client.CommonClientClass;
import com.nyfaria.nyfsspiders.platform.Services;
import commonnetwork.networking.data.PacketContext;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Spider;

public record UpdatePathingTarget(int spiderId, int index, BlockPos x) {

    public static ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(Constants.MODID, "update_pathing_target");

    public static UpdatePathingTarget decode(FriendlyByteBuf buf) {
        BlockPos pos = buf.readBlockPos();
        return new UpdatePathingTarget(buf.readInt(), buf.readInt(), pos == BlockPos.ZERO ? null : pos);
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(spiderId);
        buf.writeInt(index);
        buf.writeBlockPos(x);
    }

    public static void handle(PacketContext<UpdatePathingTarget> context) {
        Services.PLATFORM.getSpiderData((Spider) CommonClientClass.getLevel().getEntity(context.message().spiderId)).setPathingTarget(context.message().index, context.message().x());
    }
}
