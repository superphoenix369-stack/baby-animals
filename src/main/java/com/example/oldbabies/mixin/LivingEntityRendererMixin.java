package com.example.oldbabies.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** DRAFT, UNVERIFIED: the scale(...) signature is a guess from 1.21.x. */
@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin {
    @Inject(method = "scale", at = @At("TAIL"))
    private void oldbabies$halfSizeBabies(LivingEntityRenderState state, PoseStack poseStack, CallbackInfo ci) {
        if ((Object) this instanceof AgeableMobRenderer<?, ?, ?> && state.isBaby) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
        }
    }
}
