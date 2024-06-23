package com.nyfaria.nyfsspiders.network;

import commonnetwork.api.Network;

public class NetworkInit {
    public static void load() {
        Network.registerPacket(UpdateMovementTargetX.ID, UpdateMovementTargetX.class, UpdateMovementTargetX::encode, UpdateMovementTargetX::decode, UpdateMovementTargetX::handle);
        Network.registerPacket(UpdateMovementTargetY.ID, UpdateMovementTargetY.class, UpdateMovementTargetY::encode, UpdateMovementTargetY::decode, UpdateMovementTargetY::handle);
        Network.registerPacket(UpdateMovementTargetZ.ID, UpdateMovementTargetZ.class, UpdateMovementTargetZ::encode, UpdateMovementTargetZ::decode, UpdateMovementTargetZ::handle);
        Network.registerPacket(UpdatePathingSide.ID, UpdatePathingSide.class, UpdatePathingSide::encode, UpdatePathingSide::decode, UpdatePathingSide::handle);
        Network.registerPacket(UpdateRotationHead.ID, UpdateRotationHead.class, UpdateRotationHead::encode, UpdateRotationHead::decode, UpdateRotationHead::handle);
        Network.registerPacket(UpdateRotationBody.ID, UpdateRotationBody.class, UpdateRotationBody::encode, UpdateRotationBody::decode, UpdateRotationBody::handle);
        Network.registerPacket(UpdatePathingTarget.ID, UpdatePathingTarget.class, UpdatePathingTarget::encode, UpdatePathingTarget::decode, UpdatePathingTarget::handle);
    }
}
