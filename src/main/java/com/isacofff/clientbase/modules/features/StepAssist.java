package com.isacofff.clientbase.modules.features;

import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.Category;
import net.minecraft.client.Minecraft;

public class StepAssist extends Module {

    private float previousStep = 0.5f;

    public StepAssist() {
        super("StepAssist", Category.Movement);
        this.description = "Automatically steps up one-block obstacles.";
    }

    @Override
    public void onEnable() {
        super.onEnable();
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player != null) {
            previousStep = mc.player.stepHeight;
            mc.player.stepHeight = 1.0f;
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player != null) {
            mc.player.stepHeight = previousStep;
        }
    }
}
