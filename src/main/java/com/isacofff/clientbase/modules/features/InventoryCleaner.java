package com.isacofff.clientbase.modules.features;

import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.Category;
import com.isacofff.clientbase.settings.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

public class InventoryCleaner extends Module {

    public Setting.BooleanSetting keepTools = new Setting.BooleanSetting("KeepTools", true);

    public InventoryCleaner() {
        super("InventoryCleaner", Category.Hacks);
        this.description = "Automatically drops junk items from your inventory.";
        settings.add(keepTools);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player == null || mc.world == null) return;
        for (int i = 9; i < 36; i++) {
            ItemStack stack = mc.player.inventory.mainInventory.get(i);
            if (stack == null || stack.func_190926_b()) continue;
            if (keepTools.getValue() && (stack.getItem() instanceof net.minecraft.item.ItemTool
                    || stack.getItem() instanceof net.minecraft.item.ItemSword
                    || stack.getItem() instanceof net.minecraft.item.ItemArmor)) continue;
            if (stack.getItem() == net.minecraft.init.Items.DIRT || stack.getItem() == net.minecraft.init.Items.COBBLESTONE
                    || stack.getItem() == net.minecraft.init.Items.SAND || stack.getItem() == net.minecraft.init.Items.GRAVEL) {
                mc.player.dropItem(stack, true, false);
                return;
            }
        }
    }
}
