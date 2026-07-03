package com.isacofff.clientbase.modules.features;

import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.Category;
import com.isacofff.clientbase.settings.Setting;
import net.minecraft.client.Minecraft;

import java.lang.reflect.Field;

public class FastPlace extends Module {

    public Setting.NumberSetting delay = new Setting.NumberSetting("Delay", 0, 0, 4, 1);

    public FastPlace() {
        super("FastPlace", Category.Hacks);
        this.description = "Reduces right-click delay for faster placement.";
        settings.add(delay);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        try {
            Field field = Minecraft.class.getDeclaredField("rightClickDelayTimer");
            field.setAccessible(true);
            field.setInt(Minecraft.getMinecraft(), (int) delay.getValue());
        } catch (Throwable ignored) {
        }
    }
}
