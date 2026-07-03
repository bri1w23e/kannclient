package com.isacofff.clientbase.modules.features;

import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.Category;
import net.minecraft.client.Minecraft;

public class AutoSprint extends Module {

    public AutoSprint() {
        super("AutoSprint", Category.Movement);
        this.description = "Automatically sprints when moving forward.";
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player == null) return;

        if (mc.player.movementInput.moveForward > 0.0F && !mc.player.isSprinting()) {
            mc.player.setSprinting(true);
        }

        if (mc.player.movementInput.moveForward <= 0.0F && mc.player.isSprinting()) {
            mc.player.setSprinting(false);
        }
    }
}
