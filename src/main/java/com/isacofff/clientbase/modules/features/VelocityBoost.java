package com.isacofff.clientbase.modules.features;

import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.Category;
import com.isacofff.clientbase.settings.Setting;
import net.minecraft.client.Minecraft;

public class VelocityBoost extends Module {

    public Setting.NumberSetting multiplier = new Setting.NumberSetting("Boost", 1.2, 1.0, 3.0, 0.1);

    public VelocityBoost() {
        super("VelocityBoost", Category.Hacks);
        this.description = "Increases knockback movement velocity when hit.";
        settings.add(multiplier);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player == null) return;
        if (mc.player.hurtTime > 0) {
            mc.player.motionX *= multiplier.getValue();
            mc.player.motionZ *= multiplier.getValue();
        }
    }
}
