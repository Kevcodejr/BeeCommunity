package com.beenugget.beecommunity;

import org.bukkit.plugin.java.JavaPlugin;

public final class BeeCommunity extends JavaPlugin {

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
