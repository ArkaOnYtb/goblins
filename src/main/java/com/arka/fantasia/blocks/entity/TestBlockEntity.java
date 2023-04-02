package com.arka.fantasia.blocks.entity;

import com.arka.fantasia.registers.BlockEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TestBlockEntity extends BlockEntity {
        public TestBlockEntity(BlockPos pos, BlockState state) {
            super(BlockEntityRegistry.TEST_BLOCK.get(), pos, state);
        }

}
