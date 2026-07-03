package com.isacofff.clientbase.modules.features;

import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.Category;
import net.minecraft.client.Minecraft;

public class FPSCounter extends Module {

    public FPSCounter() {
        super("FPSCounter", Category.Client);
        this.description = "Displays current FPS in the module description.";
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        try {
            this.description = "FPS: " + Minecraft.getDebugFPS();
        } catch (Throwable t) {
            // ignore
        }
    }
}
