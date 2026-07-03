package com.isacofff.clientbase.modules.features;

import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.Category;
import com.isacofff.clientbase.settings.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class ReachAssist extends Module {

    public Setting.NumberSetting range = new Setting.NumberSetting("Range", 6.5, 4.5, 10.0, 0.1);

    public ReachAssist() {
        super("ReachAssist", Category.Hacks);
        this.description = "Attacks entities slightly beyond normal reach.";
        settings.add(range);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player == null || mc.world == null) return;
        if (!mc.gameSettings.keyBindAttack.isKeyDown()) return;
        Entity closest = null;
        double best = Double.MAX_VALUE;
        for (Entity e : mc.world.loadedEntityList) {
            if (e == mc.player || !(e instanceof EntityPlayer)) continue;
            double d = mc.player.getDistance(e);
            if (d <= range.getValue() && d < best) {
                closest = e;
                best = d;
            }
        }
        if (closest != null) {
            mc.playerController.attackEntity(mc.player, closest);
        }
    }
}
