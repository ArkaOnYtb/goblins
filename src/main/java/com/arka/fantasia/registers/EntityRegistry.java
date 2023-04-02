package com.arka.fantasia.registers;

import com.arka.fantasia.Fantasia;
import com.arka.fantasia.entity.goblin.GoblinBruteEntity;
import com.arka.fantasia.entity.goblin.TestGoblinEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityRegistry {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Fantasia.ModID);

    public static final RegistryObject<EntityType<GoblinBruteEntity>> GOBLIN_BRUTE_ENTITY = buildEntity(GoblinBruteEntity::new, GoblinBruteEntity.class, 0.5f, 2f, "goblin_brute");
    public static final RegistryObject<EntityType<TestGoblinEntity>> TEST_GOBLIN_ENTITY = buildEntity(TestGoblinEntity::new, TestGoblinEntity.class, 0.5f, 2f, "test_goblin");

    public static <T extends Entity> RegistryObject<EntityType<T>> buildEntity(EntityType.EntityFactory<T> entity, Class<T> entityClass, float width, float height, String name) {
        return ENTITIES.register(name, () -> EntityType.Builder.of(entity, MobCategory.CREATURE).sized(width, height).build(name));
    }

}
