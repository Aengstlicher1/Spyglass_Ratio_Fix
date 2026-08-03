package com.aengstlicher.spyratiofix.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class GuiMixin {

    @Unique
    private static final ResourceLocation SPYGLASS_SCOPE_LOCATION =
            ResourceLocation.withDefaultNamespace("textures/misc/spyglass_scope.png");

    @Inject(method = "renderSpyglassOverlay", at = @At("HEAD"), cancellable = true)
    private void onRenderSpyglassOverlay(GuiGraphics guiGraphics, float scopeScale, CallbackInfo ci) {
        // Cancel the vanilla square/vignette rendering method
        ci.cancel();

        Minecraft mc = Minecraft.getInstance();
        int screenWidth = mc.getWindow().getGuiScaledWidth();
        int screenHeight = mc.getWindow().getGuiScaledHeight();

        // Stretch the spyglass_scope.png texture across the full width and height
        RenderSystem.enableBlend();
        guiGraphics.blit(
                SPYGLASS_SCOPE_LOCATION,
                0, 0,                // Upper-left corner of the screen
                0.0F, 0.0F,         // Texture UV start (U, V)
                screenWidth, screenHeight, // Render width & height on screen
                screenWidth, screenHeight  // Texture width & height for UV scaling
        );
        RenderSystem.disableBlend();
    }
}