package com.isacofff.clientbase.modules.features;

import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.Category;
import net.minecraft.client.Minecraft;

public class CoordsHUD extends Module {

    public CoordsHUD() {
        super("CoordsHUD", Category.Client);
        this.description = "Displays player coordinates in description.";
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player != null) {
            int x = (int) mc.player.posX;
            int y = (int) mc.player.posY;
            int z = (int) mc.player.posZ;
            this.description = "X:" + x + " Y:" + y + " Z:" + z;
        }
    }
}
