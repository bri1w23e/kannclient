package com.isacofff.clientbase.modules.features;

import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.Category;
import com.isacofff.clientbase.settings.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;

public class TriggerBot extends Module {

    public Setting.BooleanSetting onlyPlayers = new Setting.BooleanSetting("OnlyPlayers", true);

    public TriggerBot() {
        super("TriggerBot", Category.Hacks);
        this.description = "Automatically attacks the target under your crosshair.";
        settings.add(onlyPlayers);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player == null || mc.world == null || mc.objectMouseOver == null) return;
        if (!mc.gameSettings.keyBindAttack.isKeyDown()) return;
        if (mc.objectMouseOver.typeOfHit != RayTraceResult.Type.ENTITY) return;
        Entity target = mc.objectMouseOver.entityHit;
        if (target == null || target == mc.player) return;
        if (onlyPlayers.getValue() && !(target instanceof EntityPlayer)) return;
        mc.playerController.attackEntity(mc.player, target);
    }
}
