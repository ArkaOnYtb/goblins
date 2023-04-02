package com.arka.fantasia.registers;

import com.arka.fantasia.Fantasia;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Fantasia.ModID);


    //BLOCKS ITEMS
    public static final RegistryObject<BlockItem> TEST_BLOCK_ITEM = ITEMS.register("test_block", () -> new BlockItem(BlockRegistry.TEST_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE)));

    private ItemRegistry() {

    }
}
