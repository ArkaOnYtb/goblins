package com.arka.fantasia.setup;

import com.arka.fantasia.Fantasia;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = Fantasia.ModID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModSetup {

    /*public static final CreativeModeTab FANTASIA_GROUP = new CreativeModeTab("fantasia world") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack();
        }
    }*/

    @SubscribeEvent
    public static void init(final FMLCommonSetupEvent event) {
        
    }
}
