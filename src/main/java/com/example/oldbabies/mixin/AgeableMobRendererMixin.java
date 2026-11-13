package com.example.oldbabies.mixin;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * DRAFT, UNVERIFIED: written from memory of the 1.21.x structure, not from 26.3 sources.
 * Idea: after construction, make the baby model the same object as the adult model.
 * The 0.5x scale is applied separately (see LivingEntityRendererMixin).
 * Field names/constructor signature may differ in 26.3; fix against genSources output.
 */
@Mixin(AgeableMobRenderer.class)
public abstract class AgeableMobRendererMixin<M extends EntityModel<?>> {
    @Shadow @Final @Mutable private M babyModel;
    @Shadow @Final private M adultModel;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void oldbabies$useAdultModelForBabies(EntityRendererProvider.Context ctx, M adult, M baby,
                                                  float shadowRadius, CallbackInfo ci) {
        this.babyModel = this.adultModel;
    }
}
