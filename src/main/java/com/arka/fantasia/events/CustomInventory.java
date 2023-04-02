package com.arka.fantasia.events;

import com.arka.fantasia.Fantasia;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Fantasia.ModID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class CustomInventory {

    @SubscribeEvent
    public static void onInventoryOpen(ScreenEvent.InitScreenEvent.Post event) {
        if(event.getScreen() instanceof InventoryScreen) {

            event.addListener(new Button(event.getScreen().width / 2 + 100, event.getScreen().height / 2, 80, 20, new TranslatableComponent("Test"), (p_96786_) -> {
            }));

        }
    }
}
