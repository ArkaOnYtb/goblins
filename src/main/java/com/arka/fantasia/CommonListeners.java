package com.arka.fantasia;

import com.arka.fantasia.entity.goblin.TestGoblinEntity;
import com.arka.fantasia.registers.EntityRegistry;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Fantasia.ModID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonListeners {

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {

        //Brute Gobeline
        event.put(EntityRegistry.GOBLIN_BRUTE_ENTITY.get(), Mob.createMobAttributes().build());

        //Test
        event.put(EntityRegistry.TEST_GOBLIN_ENTITY.get(), TestGoblinEntity.createAttributes().build());
    }

}
