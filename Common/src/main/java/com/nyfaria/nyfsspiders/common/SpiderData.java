package com.nyfaria.nyfsspiders.common;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.nyfaria.nyfsspiders.network.NetworkInit;
import com.nyfaria.nyfsspiders.network.UpdateMovementTargetY;
import com.nyfaria.nyfsspiders.network.UpdateMovementTargetZ;
import com.nyfaria.nyfsspiders.network.UpdatePathingSide;
import com.nyfaria.nyfsspiders.network.UpdatePathingTarget;
import com.nyfaria.nyfsspiders.network.UpdateRotationBody;
import com.nyfaria.nyfsspiders.network.UpdateRotationHead;
import com.nyfaria.nyfsspiders.platform.Services;
import commonnetwork.api.Network;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.level.Level;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SpiderData {
    public static Codec<SpiderData> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Codec.FLOAT.fieldOf("movementTargetX").forGetter(SpiderData::getMovementTargetX),
                    Codec.FLOAT.fieldOf("movementTargetY").forGetter(SpiderData::getMovementTargetY),
                    Codec.FLOAT.fieldOf("movementTargetZ").forGetter(SpiderData::getMovementTargetZ),
                    ExtraCodecs.VECTOR3F.fieldOf("rotationBody").forGetter(SpiderData::getRotationBody),
                    ExtraCodecs.VECTOR3F.fieldOf("rotationHead").forGetter(SpiderData::getRotationHead),
                    Codec.list(Direction.CODEC).fieldOf("pathingSides").forGetter(SpiderData::getPathingSides),
                    Codec.list(BlockPos.CODEC).xmap(
                            list -> {
                                List<Optional<BlockPos>> newList = new ArrayList<>();
                                for (BlockPos pos : list) {
                                    if (pos == BlockPos.ZERO) {
                                        newList.add(Optional.empty());
                                    } else {
                                        newList.add(Optional.ofNullable(pos));
                                    }
                                }
                                return newList;
                            }
                            ,
                            list -> list.stream().map(pos -> pos.orElse(BlockPos.ZERO)).toList()
                    ).fieldOf("pathingTargets").forGetter(SpiderData::getPathingTargets)
            ).apply(instance, SpiderData::new)
    );


    private float movementTargetX = 0;
    private float movementTargetY = 0;
    private float movementTargetZ = 0;
    private Vector3f rotationBody = new Vector3f(0, 0, 0);
    private Vector3f rotationHead = new Vector3f(0, 0, 0);
    private List<Direction> pathingSides = List.of(
            Direction.DOWN,
            Direction.DOWN,
            Direction.DOWN,
            Direction.DOWN,
            Direction.DOWN,
            Direction.DOWN,
            Direction.DOWN,
            Direction.DOWN
    );
    private List<Optional<BlockPos>> pathingTargets = List.of(
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty()
    );

    private Spider spider;
    public SpiderData(Spider spider){
        this.spider = spider;
    }
    public SpiderData(){}

    public SpiderData(float movementTargetX, float movementTargetY, float movementTargetZ, Vector3f rotationBody, Vector3f rotationHead,
                      List<Direction> pathingSides, List<Optional<BlockPos>> pathingTargets) {
        this.movementTargetX = movementTargetX;
        this.movementTargetY = movementTargetY;
        this.movementTargetZ = movementTargetZ;
        this.rotationBody = rotationBody;
        this.rotationHead = rotationHead;
        this.pathingSides = pathingSides;
        this.pathingTargets = pathingTargets;
    }

    public float getMovementTargetX() {
        return movementTargetX;
    }

    public void setMovementTargetX(float movementTargetX) {
        this.movementTargetX = movementTargetX;
        if (spider.getServer() != null) {
            Services.PLATFORM.setSpiderData(spider, this);
            Network.getNetworkHandler().sendToAllClients(new UpdateRotationBody(spider.getId(), rotationBody), spider.getServer());
        }
    }

    public float getMovementTargetY() {
        return movementTargetY;
    }

    public void setMovementTargetY(float movementTargetY) {
        this.movementTargetY = movementTargetY;
        if (spider.getServer() != null) {
            Services.PLATFORM.setSpiderData(spider, this);
            Network.getNetworkHandler().sendToAllClients(new UpdateMovementTargetY(spider.getId(),movementTargetY), spider.getServer());
        }
    }

    public float getMovementTargetZ() {
        return movementTargetZ;
    }

    public void setMovementTargetZ(float movementTargetZ) {
        this.movementTargetZ = movementTargetZ;
        if(spider.getServer()!=null){
            Services.PLATFORM.setSpiderData(spider,this);
            Network.getNetworkHandler().sendToAllClients(new UpdateMovementTargetZ(spider.getId(),movementTargetZ), spider.getServer());

        }
    }

    public Vector3f getRotationBody() {
        return rotationBody;
    }

    public void setRotationBody(Vector3f rotationBody) {
        this.rotationBody = rotationBody;
        if(spider.getServer()!=null){
            Services.PLATFORM.setSpiderData(spider,this);
            Network.getNetworkHandler().sendToAllClients(new UpdateRotationBody(spider.getId(), rotationBody), spider.getServer());
        }
    }

    public Vector3f getRotationHead() {
        return rotationHead;
    }

    public void setRotationHead(Vector3f rotationHead) {
        this.rotationHead = rotationHead;
        if (spider.getServer() != null) {
            Services.PLATFORM.setSpiderData(spider, this);
            Network.getNetworkHandler().sendToAllClients(new UpdateRotationHead(spider.getId(), rotationHead), spider.getServer());
        }
    }

    public List<Direction> getPathingSides() {
        return pathingSides;
    }

    public void setPathingSides(List<Direction> pathingSides) {
        this.pathingSides = pathingSides;

    }

    public List<Optional<BlockPos>> getPathingTargets() {
        return pathingTargets;
    }

    public void setPathingTargets(List<Optional<BlockPos>> pathingTargets) {
        this.pathingTargets = pathingTargets;
    }
    public void setPathingTarget(int index, BlockPos pos) {
        this.pathingTargets.set(index, Optional.ofNullable(pos));
        if (spider.getServer() != null) {
            Services.PLATFORM.setSpiderData(spider, this);
            Network.getNetworkHandler().sendToAllClients(new UpdatePathingTarget(spider.getId(),index ,pos), spider.getServer());
        }
    }
    public void setPathingSide(int index, Direction side) {
        this.pathingSides.set(index, side);
        if (spider.getServer() != null) {
            Services.PLATFORM.setSpiderData(spider, this);
            Network.getNetworkHandler().sendToAllClients(new UpdatePathingSide(spider.getId(),index ,side), spider.getServer());
        }
    }

    public Spider getSpider() {
        return spider;
    }

    public void setSpider(Spider spider) {
        this.spider = spider;
    }
}
