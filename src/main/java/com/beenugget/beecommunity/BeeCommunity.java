package com.beenugget.beecommunity;

import com.beenugget.beecommunity.objects.data.FileManager;
import com.beenugget.beecommunity.objects.AchievementChainConfig;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class BeeCommunity extends JavaPlugin {
    private static BeeCommunity instance;
    private String pluginPath;


    @Override
    public void onEnable() {
        instance = this;
        Logger log = getLogger();
        pluginPath = getDataFolder().getAbsolutePath();
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        String path = pluginPath + "/achievements/";
        FileManager fileManager = new FileManager(path);
        FileConfiguration config = new YamlConfiguration();
        config.set("achievements.1.text", "Get lumber");
        config.set("achievements.highway.text", "Get stone");
        fileManager.setFile("Test.yml", config);


        AchievementChainConfig achievementChainConfig = new AchievementChainConfig(pluginPath, log);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public BeeCommunity getInstance() {
        return instance;
    }

    public String getPath() {
        return pluginPath;
    }
}
