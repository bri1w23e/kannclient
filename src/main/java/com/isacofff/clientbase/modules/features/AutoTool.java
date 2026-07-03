package com.isacofff.clientbase.modules.features;

import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.Category;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.block.material.Material;

public class AutoTool extends Module {

    public AutoTool() {
        super("AutoTool", Category.Player);
        this.description = "Auto-switches to the best tool in hotbar for the targeted block.";
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player == null || mc.world == null) return;

        if (mc.objectMouseOver == null || mc.objectMouseOver.typeOfHit != net.minecraft.util.math.RayTraceResult.Type.BLOCK)
            return;

        Material mat = mc.world.getBlockState(mc.objectMouseOver.getBlockPos()).getMaterial();

        int bestIndex = mc.player.inventory.currentItem;
        for (int i = 0; i < 9; i++) {
            ItemStack stack = mc.player.inventory.mainInventory.get(i);
            if (stack == null) continue;
            if (stack.func_190926_b()) continue;

            if (mat == Material.ROCK || mat == Material.IRON || mat == Material.ANVIL) {
                if (stack.getItem() instanceof ItemPickaxe) { bestIndex = i; break; }
            } else if (mat == Material.WOOD || mat == Material.PLANTS) {
                if (stack.getItem() instanceof ItemAxe) { bestIndex = i; break; }
            } else if (mat == Material.GROUND || mat == Material.GRASS || mat == Material.SAND) {
                if (stack.getItem() instanceof ItemSpade) { bestIndex = i; break; }
            }
        }

        mc.player.inventory.currentItem = bestIndex;
    }
}
