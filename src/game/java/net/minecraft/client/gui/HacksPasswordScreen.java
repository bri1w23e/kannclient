package net.minecraft.client.gui;

import com.isacofff.clientbase.Client;
import net.lax1dude.eaglercraft.KeyboardConstants;
import java.io.IOException;

public class HacksPasswordScreen extends GuiScreen {

    private String input = "";
    private String message = "Enter password to unlock Hacks:";

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, "Hacks Locked", this.width / 2, this.height / 2 - 40, 0xFFFFFF);
        this.drawCenteredString(this.fontRendererObj, message, this.width / 2, this.height / 2 - 20, 0xCCCCCC);
        this.drawCenteredString(this.fontRendererObj, input.isEmpty() ? "_" : input, this.width / 2, this.height / 2, 0xFFFFFF);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode == KeyboardConstants.KEY_ESCAPE) {
            mc.displayGuiScreen(null);
            return;
        }

        if (keyCode == KeyboardConstants.KEY_RETURN || keyCode == KeyboardConstants.KEY_NUMPADENTER) {
            if ("KANND".equals(input)) {
                Client.INSTANCE.manager.hacksUnlocked = true;
                mc.displayGuiScreen(null);
            } else {
                message = "Wrong password. Try again:";
                input = "";
            }
            return;
        }

        if (typedChar == '\b') {
            if (!input.isEmpty()) input = input.substring(0, input.length() - 1);
            return;
        }

        if (Character.isDefined(typedChar) && typedChar != '\r' && typedChar != '\n') {
            input += typedChar;
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return true;
    }
}
