package com.isacofff.clientbase.modules.features;

import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.Category;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;

public class PingHUD extends Module {

    public PingHUD() {
        super("PingHUD", Category.Client);
        this.description = "Shows the local server ping or the first player ping.";
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        try {
            Minecraft mc = Minecraft.getMinecraft();
            if (mc.player == null || mc.getConnection() == null) {
                this.description = "No server connection.";
                return;
            }
            NetworkPlayerInfo info = mc.getConnection().getPlayerInfo(mc.player.getUniqueID());
            if (info != null) {
                this.description = "Ping: " + info.getResponseTime() + " ms";
            } else if (!mc.getConnection().getPlayerInfoMap().isEmpty()) {
                this.description = "Ping: " + mc.getConnection().getPlayerInfoMap().get(0).getResponseTime() + " ms";
            } else {
                this.description = "Ping: unavailable.";
            }
        } catch (Throwable t) {
            this.description = "Ping: error.";
        }
    }
}
