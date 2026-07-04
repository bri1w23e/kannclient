package com.isacofff.clientbase.modules.impl.combat;

import com.isacofff.clientbase.Category;
import com.isacofff.clientbase.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemMinecart;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class AutoInstaCart extends Module {

    private EntityArrow lastArrow = null;

    public AutoInstaCart() {
        super("AutoInstaCart", Category.Hacks, "InstaCarts for you");
    }

    private Minecraft mc() {
        return Minecraft.getMinecraft();
    }

    @Override
    public void onUpdate() {
        if (!isEnabled()) return;
        if (mc().world == null || mc().player == null) return;

        // detect arrow spawn
        for (Object o : mc().world.loadedEntityList) {
            if (o instanceof EntityArrow) {
                EntityArrow arrow = (EntityArrow) o;

                // new arrow detected
                if (arrow != lastArrow && arrow.shootingEntity == mc().player) {
                    lastArrow = arrow;
                    handleArrow(arrow);
                }
            }
        }
    }

    private void handleArrow(EntityArrow arrow) {
        // predict forward trajectory
        Vec3d pos = arrow.getPositionVector();
        Vec3d motion = new Vec3d(arrow.motionX, arrow.motionY, arrow.motionZ);

        // project forward
        Vec3d target = pos.add(motion.scale(3.0));

        BlockPos railPos = new BlockPos(target);

        // place rail
        placeRail(railPos);

        // place TNT minecart
        placeTNTMinecart(railPos);
    }

    private void placeRail(BlockPos pos) {
        try {
            mc().playerController.processRightClickBlock(
                    mc().player,
                    mc().world,
                    pos.down(),
                    EnumFacing.UP,
                    new Vec3d(0.5, 1, 0.5),
                    EnumHand.MAIN_HAND
            );
        } catch (Throwable ignored) {}
    }

    private void placeTNTMinecart(BlockPos pos) {
        try {
            mc().playerController.processRightClickBlock(
                    mc().player,
                    mc().world,
                    pos,
                    EnumFacing.UP,
                    new Vec3d(0.5, 1, 0.5),
                    EnumHand.MAIN_HAND
            );
        } catch (Throwable ignored) {}
    }
}
