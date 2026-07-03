package com.isacofff.clientbase.modules.features;

import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.Category;
import net.lax1dude.eaglercraft.socket.ConnectionHandshake;

public class ViaViewer extends Module {

    public ViaViewer() {
        super("viaviewer", Category.Client);
        this.description = "Detects server version and shows newer-version items.";
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        try {
            int proto = ConnectionHandshake.protocolVersion;
            if (proto <= 0) {
                this.description = "No handshake detected yet.";
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Server protocol: ").append(proto).append(" - ");
            if (proto <= 340) {
                sb.append("Likely 1.12 server; no newer vanilla items.");
            } else {
                sb.append("May support newer-version items: ");
                sb.append("trident, crossbow, netherite");
                sb.append("; may also include custom items (mace, spear)");
            }
            this.description = sb.toString();
        } catch (Throwable t) {
            // keep existing description on error
        }
    }
}
