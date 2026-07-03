package com.isacofff.clientbase.modules.features;

import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.Category;
import com.isacofff.clientbase.settings.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;

public class AutoBlockHit extends Module {

    public Setting.BooleanSetting autoBreak = new Setting.BooleanSetting("AutoBreak", false);

    public AutoBlockHit() {
        super("AutoBlockHit", Category.Hacks);
        this.description = "Automatically continues breaking the targeted block.";
        settings.add(autoBreak);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!autoBreak.getValue()) return;
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player == null || mc.world == null || mc.objectMouseOver == null) return;
        if (mc.objectMouseOver.typeOfHit != RayTraceResult.Type.BLOCK) return;
        BlockPos pos = mc.objectMouseOver.getBlockPos();
        EnumFacing side = mc.objectMouseOver.sideHit;
        mc.playerController.onPlayerDamageBlock(pos, side);
    }
}
