package com.arka.fantasia.client.renderer.block;

import com.arka.fantasia.blocks.entity.TestBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.client.model.data.EmptyModelData;

public class TestBlockEntityRenderer implements BlockEntityRenderer<TestBlockEntity> {

    private final BlockEntityRendererProvider.Context context;
    public TestBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }

    @Override
    public void render(TestBlockEntity blockEntity, float partialTicks, PoseStack stack, MultiBufferSource buffer, int combineOverlay, int packedLight) {

        final BlockRenderDispatcher dispatcher = this.context.getBlockRenderDispatcher();
        final ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        dispatcher.renderSingleBlock(Blocks.ANVIL.defaultBlockState(), stack, buffer, combineOverlay, packedLight, EmptyModelData.INSTANCE);

        final LocalPlayer player = Minecraft.getInstance().player;
        ItemStack heldItem = player.getMainHandItem();

        stack.pushPose();
        stack.translate(0.5f, 1.0f, 0.5f);
        stack.scale(0.75f, 0.75f, 0.75f);
        stack.mulPose(Vector3f.XN.rotationDegrees(90f));
        itemRenderer.renderStatic(player, heldItem, ItemTransforms.TransformType.FIXED, false, stack, buffer,
                Minecraft.getInstance().level, combineOverlay, packedLight, 0);
        stack.popPose();
    }

}
