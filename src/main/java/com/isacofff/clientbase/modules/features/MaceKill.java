package com.isacofff.clientbase.modules.impl.combat;

import com.isacofff.clientbase.Category;
import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.settings.Setting.NumberSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class MaceKill extends Module {

    private final NumberSetting fallSpoof =
            new NumberSetting("Fall Distance", 5.0, 0.0, 50.0, 1.0);

    public MaceKill() {
        super("MaceKill", Category.Hacks, "Spoofs fall distance on mace hit");
        addSetting(fallSpoof);
    }

    private Minecraft mc() {
        return Minecraft.getMinecraft();
    }

    @Override
    public void onUpdate() {
    }

    @Override
    public void onAttack(EntityLivingBase target) {
        if (!isEnabled()) return;
        if (target == null) return;

        ItemStack held = mc().player.getHeldItemMainhand();
        if (held == null) return;

        if (!isMace(held)) return;

        mc().player.fallDistance = (float) fallSpoof.getValue();
    }

    private boolean isMace(ItemStack stack) {
        if (stack == null) return false;

        int density = getEnchantLevel(stack, "density");
        int breach = getEnchantLevel(stack, "breach");
        int windburst = getEnchantLevel(stack, "windburst");

        return density > 0 || breach > 0 || windburst > 0;
    }

    private int getEnchantLevel(ItemStack stack, String name) {
        for (Enchantment ench : Enchantment.REGISTRY) {
            if (ench == null) continue;
            String id = ench.getName();
            if (id == null) continue;

            if (id.toLowerCase().contains(name.toLowerCase())) {
                return EnchantmentHelper.getEnchantmentLevel(ench, stack);
            }
        }
        return 0;
    }
}
