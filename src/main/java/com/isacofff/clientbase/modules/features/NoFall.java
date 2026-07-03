package com.isacofff.clientbase.modules.features;

import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.Category;
import com.isacofff.clientbase.settings.Setting;
import net.minecraft.client.Minecraft;

public class NoFall extends Module {

    public Setting.NumberSetting threshold = new Setting.NumberSetting("Height", 3, 1, 20, 1);

    public NoFall() {
        super("NoFall", Category.Hacks);
        this.description = "Prevents fall damage by resetting fall distance.";
        settings.add(threshold);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player == null) return;
        if (mc.player.fallDistance > threshold.getValue()) {
            mc.player.fallDistance = 0.0F;
            mc.player.onGround = true;
        }
    }
}
