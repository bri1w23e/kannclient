package com.isacofff.clientbase.modules.features;

import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.Category;
import com.isacofff.clientbase.settings.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

public class AutoInstaCart extends Module {

    public Setting.BooleanSetting onlyWhenRiding = new Setting.BooleanSetting("OnlyWhenRiding", true);
    public Setting.NumberSetting drawTicks = new Setting.NumberSetting("DrawTicks", 3, 1, 20, 1);

    private boolean drawingBow = false;
    private int bowTicks = 0;

    public AutoInstaCart() {
        super("Auto Insta Cart", Category.Hacks);
        this.description = "Instantly draw and release a bow while riding a minecart.";
        settings.add(onlyWhenRiding);
        settings.add(drawTicks);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player == null || mc.world == null) return;
        if (onlyWhenRiding.getValue()) {
            if (!mc.player.isRiding() || !(mc.player.getRidingEntity() instanceof EntityMinecart)) {
                reset();
                return;
            }
        }
        ItemStack current = mc.player.inventory.getCurrentItem();
        if (current == null || current.func_190926_b() || !(current.getItem() instanceof ItemBow)) {
            reset();
            return;
        }

        if (!drawingBow) {
            startDrawing(mc);
            return;
        }

        bowTicks++;
        if (bowTicks >= drawTicks.getValue()) {
            releaseBow(mc);
        }
    }

    private void startDrawing(Minecraft mc) {
        if (mc.player.isHandActive()) return;
        mc.playerController.processRightClick(mc.player, mc.world, EnumHand.MAIN_HAND);
        drawingBow = true;
        bowTicks = 0;
    }

    private void releaseBow(Minecraft mc) {
        mc.playerController.onStoppedUsingItem(mc.player);
        drawingBow = false;
        bowTicks = 0;
    }

    private void reset() {
        drawingBow = false;
        bowTicks = 0;
    }
}
