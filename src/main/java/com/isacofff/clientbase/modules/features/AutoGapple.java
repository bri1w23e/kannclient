package com.isacofff.clientbase.modules.features;

import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.Category;
import com.isacofff.clientbase.settings.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class AutoGapple extends Module {

    public Setting.NumberSetting health = new Setting.NumberSetting("Health", 10, 1, 36, 1);

    public AutoGapple() {
        super("AutoGapple", Category.Hacks);
        this.description = "Eats a golden apple when your health drops below the threshold.";
        settings.add(health);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player == null || mc.world == null) return;
        if (mc.player.getHealth() > health.getValue()) return;
        for (int i = 0; i < 9; i++) {
            ItemStack stack = mc.player.inventory.mainInventory.get(i);
            if (stack == null || stack.func_190926_b()) continue;
            if (stack.getItem() == Items.GOLDEN_APPLE) {
                mc.player.inventory.currentItem = i;
                mc.playerController.processRightClick(mc.player, mc.world, net.minecraft.util.EnumHand.MAIN_HAND);
                return;
            }
        }
    }
}
