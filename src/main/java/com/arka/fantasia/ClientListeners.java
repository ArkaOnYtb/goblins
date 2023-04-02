package com.arka.fantasia;

import com.arka.fantasia.client.renderer.block.TestBlockEntityRenderer;
import com.arka.fantasia.client.renderer.entity.GoblinBruteGeoRenderer;
import com.arka.fantasia.client.renderer.entity.TestEntityGeoRenderer;
import com.arka.fantasia.registers.BlockEntityRegistry;
import com.arka.fantasia.registers.EntityRegistry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Fantasia.ModID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientListeners {

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {

        event.registerEntityRenderer(EntityRegistry.GOBLIN_BRUTE_ENTITY.get(), GoblinBruteGeoRenderer::new);
        event.registerEntityRenderer(EntityRegistry.TEST_GOBLIN_ENTITY.get(), TestEntityGeoRenderer::new);

        event.registerBlockEntityRenderer(BlockEntityRegistry.TEST_BLOCK.get(), TestBlockEntityRenderer::new);

    }

}
