package com.isacofff.clientbase.modules.features;

import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.Category;
import com.isacofff.clientbase.settings.Setting;

public class AdvancedHitboxes extends Module {

    public Setting.ModeSetting mode = new Setting.ModeSetting("ESP Mode", "Outline", "Outline", "Fill", "Glow");
    public Setting.NumberSetting range = new Setting.NumberSetting("Range", 64, 8, 256, 1);

    public AdvancedHitboxes() {
        super("AdvancedHitboxes", Category.Render);
        this.description = "Highlights players using configurable hitbox rendering modes.";
        settings.add(mode);
        settings.add(range);
    }

}
