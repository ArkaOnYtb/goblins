package com.arka.fantasia;

import com.arka.fantasia.registers.BlockEntityRegistry;
import com.arka.fantasia.registers.BlockRegistry;
import com.arka.fantasia.registers.EntityRegistry;
import com.arka.fantasia.registers.ItemRegistry;
import com.arka.fantasia.setup.ModSetup;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

import java.util.stream.Collectors;

@Mod("fantasia")
public class Fantasia {

    public static final String ModID = "fantasia";
    private static final Logger LOGGER = LogManager.getLogger();
    public Fantasia() {
        GeckoLib.initialize();

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(CommonListeners.class);
        MinecraftForge.EVENT_BUS.register(ClientListeners.class);

        EntityRegistry.ENTITIES.register(bus);
        BlockRegistry.BLOCKS.register(bus);
        BlockEntityRegistry.BLOCK_ENTITIES.register(bus);
        ItemRegistry.ITEMS.register(bus);
        //FMLJavaModLoadingContext.get().getModEventBus().register(ModSetup.class);
    }

}
