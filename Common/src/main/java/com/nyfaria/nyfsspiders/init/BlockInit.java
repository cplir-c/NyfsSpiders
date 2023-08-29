package com.nyfaria.nyfsspiders.init;

import com.nyfaria.nyfsspiders.registration.RegistrationProvider;
import com.nyfaria.nyfsspiders.Constants;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class BlockInit {
    public static final RegistrationProvider<Block> BLOCKS = RegistrationProvider.get(Registries.BLOCK, Constants.MODID);
    public static final RegistrationProvider<BlockEntityType<?>> BLOCK_ENTITIES = RegistrationProvider.get(Registries.BLOCK_ENTITY_TYPE, Constants.MODID);


    public static void loadClass() {
    }
}
