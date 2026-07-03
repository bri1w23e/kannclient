package com.isacofff.clientbase.modules.features;

import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.Category;
import com.isacofff.clientbase.settings.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

public class AutoEat extends Module {

    public Setting.NumberSetting threshold = new Setting.NumberSetting("Hunger", 12, 1, 20, 1);

    public AutoEat() {
        super("AutoEat", Category.Player);
        this.description = "Automatically uses food from hotbar when hungry.";
        settings.add(threshold);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player == null || mc.world == null) return;

        int hunger = mc.player.getFoodStats().getFoodLevel();
        if (hunger >= threshold.getValue().intValue()) return;

        InventoryPlayer inv = mc.player.inventory;
        for (int i = 0; i < 9; i++) {
            ItemStack stack = inv.mainInventory.get(i);
            if (!stack.func_190926_b() && stack.getItem() instanceof ItemFood) {
                if (inv.currentItem != i) inv.currentItem = i;
                mc.playerController.processRightClick(mc.player, mc.world, EnumHand.MAIN_HAND);
                break;
            }
        }
    }
}
