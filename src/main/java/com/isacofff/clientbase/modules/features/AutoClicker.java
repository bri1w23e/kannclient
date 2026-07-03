package com.isacofff.clientbase.modules.features;

import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.Category;
import com.isacofff.clientbase.settings.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.lax1dude.eaglercraft.Mouse;

public class AutoClicker extends Module {

    public Setting.NumberSetting cps = new Setting.NumberSetting("CPS", 8.0, 1.0, 20.0, 0.5);

    private long lastClick = 0L;

    public AutoClicker() {
        super("AutoClicker", Category.Hacks);
        this.description = "Automatically clicks at a configurable CPS when holding attack.";
        settings.add(cps);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player == null || mc.world == null) return;

        // Only trigger while left mouse button is held
        if (!Mouse.isButtonDown(0)) return;

        double cpsVal = cps.getValue().doubleValue();
        if (cpsVal <= 0) cpsVal = 8.0;

        long delay = (long) (1000.0 / cpsVal);
        long now = System.currentTimeMillis();
        if (now - lastClick >= delay) {
            try {
                RayTraceResult r = mc.objectMouseOver;
                if (r != null && r.typeOfHit == RayTraceResult.Type.ENTITY && r.entityHit != null) {
                    mc.playerController.attackEntity(mc.player, r.entityHit);
                } else {
                    mc.player.swingArm(EnumHand.MAIN_HAND);
                }
            } catch (Throwable t) {
                // ignore
            }
            lastClick = now;
        }
    }
}
