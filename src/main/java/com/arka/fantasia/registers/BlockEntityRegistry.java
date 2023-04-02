package com.arka.fantasia.registers;

import com.arka.fantasia.Fantasia;
import com.arka.fantasia.blocks.entity.TestBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityRegistry {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Fantasia.ModID);


    public static final RegistryObject<BlockEntityType<TestBlockEntity>> TEST_BLOCK = BLOCK_ENTITIES.register("test_block", () -> BlockEntityType.Builder.of(TestBlockEntity::new, BlockRegistry.TEST_BLOCK.get()).build(null));

    private BlockEntityRegistry() {

    }
}
