package com.isacofff.clientbase.modules;

import com.isacofff.clientbase.modules.features.ClickGui;
import com.isacofff.clientbase.modules.features.ExampleModule;
import com.isacofff.clientbase.modules.features.FullBright;
import com.isacofff.clientbase.modules.features.AutoSprint;
import com.isacofff.clientbase.modules.features.AutoEat;
import com.isacofff.clientbase.modules.features.AdvancedHitboxes;
import com.isacofff.clientbase.modules.features.AutoClicker;
import com.isacofff.clientbase.modules.features.AutoTool;
import com.isacofff.clientbase.modules.features.AutoArmor;
import com.isacofff.clientbase.modules.features.AutoBlockHit;
import com.isacofff.clientbase.modules.features.AutoGapple;
import com.isacofff.clientbase.modules.features.FastPlace;
import com.isacofff.clientbase.modules.features.InventoryCleaner;
import com.isacofff.clientbase.modules.features.PingHUD;
import com.isacofff.clientbase.modules.features.ReachAssist;
import com.isacofff.clientbase.modules.features.Scaffold;
import com.isacofff.clientbase.modules.features.StepAssist;
import com.isacofff.clientbase.modules.features.TriggerBot;
import com.isacofff.clientbase.modules.features.VelocityBoost;
import com.isacofff.clientbase.modules.features.ViaViewer;
import com.isacofff.clientbase.modules.features.AutoInstaCart;
import com.isacofff.clientbase.modules.features.VelocityBoost;
import com.isacofff.clientbase.modules.features.ViaViewer;
import com.isacofff.clientbase.Category;

import java.util.ArrayList;

public class Manager {

    public final ArrayList<Module> modules = new ArrayList<>();
    // whether Hacks category is unlocked in the GUI
    public boolean hacksUnlocked = false;
    //Add the modules here for them to appear in the ClickGui
    public void init() {
    modules.add(new ClickGui());
    modules.add(new FullBright());
    modules.add(new ExampleModule());
    modules.add(new AutoSprint());
    modules.add(new AutoEat());
    modules.add(new AdvancedHitboxes());
    modules.add(new AutoClicker());
    modules.add(new AutoTool());
    modules.add(new AutoArmor());
    modules.add(new AutoBlockHit());
    modules.add(new AutoGapple());
    modules.add(new FastPlace());
    modules.add(new InventoryCleaner());
    modules.add(new ReachAssist());
    modules.add(new Scaffold());
    modules.add(new StepAssist());
    modules.add(new TriggerBot());
    modules.add(new VelocityBoost());
    modules.add(new AutoInstaCart());
    modules.add(new FPSCounter());
    modules.add(new CoordsHUD());
    modules.add(new PingHUD());
    modules.add(new ViaViewer());
    }

    public void onTick() {
        for (Module m : modules) {
            if (m.isEnabled()) {
                m.onUpdate();
            }
        }
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public <T extends Module> T getModule(Class<T> classs) {

        for (Module m : modules) {
            if (classs.isInstance(m)) return classs.cast(m);
        }
        return null;
    }

    public Module getModuleByName(String name) {
        for (Module m : modules) {
            if (m.getName().equalsIgnoreCase(name)) return m;
        }
        return null;
    }

    public ArrayList<Module> getModulesByCategory(Category c) {
        ArrayList<Module> list = new ArrayList<>();
        for (Module m : modules) {
            if (m.getCategory() == c) list.add(m);
        }
        return list;
    }
}
