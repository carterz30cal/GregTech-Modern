package com.gregtechceu.gtceu.core.mixins;

import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.gregtechceu.gtceu.api.item.IComponentItem;
import com.gregtechceu.gtceu.api.item.IGTTool;
import com.gregtechceu.gtceu.client.util.ToolChargeBarRenderer;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiGraphics.class)
public class GuiGraphicsMixin {

    @Inject(
        method = "renderItemDecorations(Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;IILjava/lang/String;)V",
        at = @At(
            value = "FIELD",
            target = "Lnet/minecraft/client/gui/GuiGraphics;minecraft:Lnet/minecraft/client/Minecraft;",
            shift = At.Shift.BEFORE,
            ordinal = 0))
    private void gtceu$renderCustomDurabilityBars(Font font, ItemStack stack, int x, int y, String text, CallbackInfo ci) {
        if (stack.getItem() instanceof IGTTool toolItem) {
            ToolChargeBarRenderer.renderBarsTool((GuiGraphics) (Object) this, toolItem, stack, x, y);
        } else if (stack.getItem() instanceof IComponentItem componentItem) {
            ToolChargeBarRenderer.renderBarsItem((GuiGraphics) (Object) this, componentItem, stack, x, y);
        }
    }
}
