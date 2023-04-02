package com.arka.fantasia.registers;

import com.arka.fantasia.Fantasia;
import com.arka.fantasia.blocks.TestBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockRegistry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Fantasia.ModID);


    public static final RegistryObject<TestBlock> TEST_BLOCK = BLOCKS.register("test_block", () -> new TestBlock(BlockBehaviour.Properties.copy(Blocks.ANVIL)));


    private BlockRegistry() {

    }
}
