package com.isacofff.clientbase.modules.features;

import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.Category;
import com.isacofff.clientbase.settings.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class Scaffold extends Module {

    public Setting.BooleanSetting onlyGround = new Setting.BooleanSetting("GroundOnly", true);

    public Scaffold() {
        super("Scaffold", Category.Hacks);
        this.description = "Place blocks automatically beneath you while moving.";
        settings.add(onlyGround);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player == null || mc.world == null || mc.player.inventory.getCurrentItem().func_190926_b()) return;
        BlockPos under = new BlockPos(mc.player.posX, mc.player.posY - 1.0D, mc.player.posZ);
        if (!mc.world.isAirBlock(under)) return;
        if (onlyGround.getValue() && !mc.player.onGround) return;
        mc.playerController.processRightClickBlock(mc.player, mc.world, under, EnumFacing.UP, mc.player.getPositionVector(), net.minecraft.util.EnumHand.MAIN_HAND);
    }
}
