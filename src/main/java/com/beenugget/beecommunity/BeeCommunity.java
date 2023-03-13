package com.beenugget.beecommunity;

import com.beenugget.beecommunity.commands.AchievementCommand;
import com.beenugget.beecommunity.listeners.BreakListener;
import com.beenugget.beecommunity.objects.AchievementChain;
import com.beenugget.beecommunity.objects.data.FileManager;
import com.beenugget.beecommunity.objects.AchievementChainConfig;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public final class BeeCommunity extends JavaPlugin {
//    private static BeeCommunity instance;
    private final List<AchievementChain> achievementChains = new ArrayList<>();
    private String pluginPath;
    public AchievementChainConfig achievementChainConfig;


    @Override
    public void onEnable() {
//        instance = this;
        Logger log = getLogger();
        pluginPath = getDataFolder().getAbsolutePath();
        getConfig().options().copyDefaults();
        saveDefaultConfig();


        achievementChainConfig = new AchievementChainConfig(pluginPath, log, this);



        //////// Listeners and Commands ////////
        Objects.requireNonNull(getCommand("achievement")).setExecutor(new AchievementCommand(achievementChains));

        getServer().getPluginManager().registerEvents(new BreakListener(achievementChains), this);


//        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new Runnable() {
//            @Override
//            public void run() {
////                System.out.println("Test");
//                achievementChainConfig.saveAll(achievementChains);
//                log.info("Running task...");
//            }
//        }, 20 * 5, 20 * 15);
    }

    public void saveConfigs(){
        achievementChainConfig.saveAll(achievementChains);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

//    public static BeeCommunity getInstance() {
//        return instance;
//    }

    public String getPath() {
        return pluginPath;
    }

    public List<AchievementChain> getAchievementChains() {
        return achievementChains;
    }

    public void addAchievementChain(AchievementChain achievementChain){
        achievementChains.add(achievementChain);
    }
}
