package com.isacofff.clientbase.modules.features;

import com.isacofff.clientbase.Settings.DoesNotExistException;
import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.Category;
import com.isacofff.clientbase.settings.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class AutoArmor extends Module {

    public Setting.BooleanSetting onlyFullSet = new Setting.BooleanSetting("FullSetOnly", false);

    public AutoArmor() {
        super("AutoArmor", Category.Hacks);
        this.description = "Automatically equips better armor from inventory.";
        settings.add(onlyFullSet);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player == null || mc.world == null) return;

        for (int slot = 0; slot < mc.player.inventory.mainInventory.size(); slot++) {
            ItemStack stack = mc.player.inventory.mainInventory.get(slot);
            if (stack == null || stack.func_190926_b() || !(stack.getItem() instanceof ItemArmor)) continue;
            ItemArmor armor = (ItemArmor) stack.getItem();
            int armorSlot = 3 - armor.damageReduceAmount + 0; // fallback id order
            EntityEquipmentSlot equipSlot = armor.getEquipmentSlot();
            ItemStack current = mc.player.inventory.getStackInSlot(36 + equipSlot.getIndex());
            if (current.func_190926_b() || armor.damageReduceAmount > getArmorPoints(current)) {
                int desiredHotbar = slot;
                if (slot >= 9) {
                    mc.player.inventory.currentItem = 0;
                    mc.player.inventory.currentItem = desiredHotbar % 9;
                } else {
                    mc.player.inventory.currentItem = desiredHotbar;
                }
                mc.playerController.processRightClick(mc.player, mc.world, net.minecraft.util.EnumHand.MAIN_HAND);
                return;
            }
        }
    }

    private int getArmorPoints(ItemStack stack) {
        if (stack == null || stack.func_190926_b() || !(stack.getItem() instanceof ItemArmor)) return 0;
        return ((ItemArmor) stack.getItem()).damageReduceAmount;
    }
}
