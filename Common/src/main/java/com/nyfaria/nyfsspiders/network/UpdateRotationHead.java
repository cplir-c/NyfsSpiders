package com.nyfaria.nyfsspiders.network;

import com.nyfaria.nyfsspiders.Constants;
import com.nyfaria.nyfsspiders.client.CommonClientClass;
import com.nyfaria.nyfsspiders.common.entity.mob.IClimberEntity;
import com.nyfaria.nyfsspiders.platform.Services;
import commonnetwork.networking.data.PacketContext;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.phys.Vec3;
import org.apache.commons.lang3.tuple.Pair;
import org.joml.Vector3f;

public record UpdateRotationHead(int spiderId, Vector3f x) {

    public static ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(Constants.MODID,"update_rotation_head");

    public static UpdateRotationHead decode(FriendlyByteBuf buf){
        return new UpdateRotationHead(buf.readInt(),buf.readVector3f());
    }

    public void encode(FriendlyByteBuf buf){
        buf.writeInt(spiderId);
        buf.writeVector3f(x);
    }
    public static void handle(PacketContext<UpdateRotationHead> context) {
        Spider spider = (Spider)CommonClientClass.getLevel().getEntity(context.message().spiderId);
        Services.PLATFORM.getSpiderData(spider).setRotationHead(context.message().x());

        Vector3f rotation = context.message().x();
        Vec3 look = new Vec3(rotation.x(), rotation.y(), rotation.z());

        Pair<Float, Float> rotations = ((IClimberEntity)spider).getOrientation().getLocalRotation(look);

        ((IClimberEntity)spider).setLerpYHeadRot(rotations.getLeft());
        ((IClimberEntity)spider).setLerpHeadSteps(3);
    }
}
