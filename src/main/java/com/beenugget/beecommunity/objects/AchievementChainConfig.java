package com.beenugget.beecommunity.objects;

import com.beenugget.beecommunity.BeeCommunity;
import com.beenugget.beecommunity.objects.data.FileManager;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class AchievementChainConfig {
    private FileManager fileManager = null;
    private final Logger log;
    private final BeeCommunity beeCommunity;

    public AchievementChainConfig(String path, Logger log, BeeCommunity beeCommunity) {
        this.log = log;
        this.beeCommunity = beeCommunity;
        String path1 = path + "/achievements/";
        fileManager = new FileManager(path1);
        loadAll();
    }

    public void loadAll(){
        List<FileConfiguration> configs = fileManager.getFileConfigurations();
        List<String> chainNames = new ArrayList<>();

        for(FileConfiguration config : configs){
            for(String str : chainNames){
                if(str.equalsIgnoreCase(config.getString("chain-name"))){
                    log.severe("Found a duplicated chain-name: (" + str + "), each achievement file needs to have an unique chain-name to be properly loaded. Please fix this and reload the plugin!");
                    return;
                }
            }
            chainNames.add(config.getString("chain-name"));
            AchievementChain achievementChain = new AchievementChain(config.getString("chain-name"), config.getBoolean("global"));

            for(String key : Objects.requireNonNull(config.getConfigurationSection("achievements")).getKeys(false)){
                ConfigurationSection currentSection = config.getConfigurationSection("achievements." + key);
                assert currentSection != null;

                Achievement achievement = new Achievement(currentSection.getString("title"), currentSection.getString("desc"), key, currentSection.getStringList("action"));
                achievementChain.addAchievement(achievement);
            }

            beeCommunity.addAchievementChain(achievementChain);
        }
    }

    public void saveAll(List<AchievementChain> achievementChains){
        for(AchievementChain achievementChain : achievementChains){
            if(!achievementChain.isModified()){
                continue;
            }
            FileConfiguration config = new YamlConfiguration();
            config.set("global", achievementChain.isGlobal());
            config.set("chain-name", achievementChain.getChainName());
            for(Achievement achievement : achievementChain.getAchievements()){
                config.set("achievements." + achievement.getKey() + ".title", achievement.getTitle());
                config.set("achievements." + achievement.getKey() + ".desc", achievement.getDesc());
                config.set("achievements." + achievement.getKey() + ".action", achievement.getActions());
            }
            fileManager.setFile(config.getString("chain-name") + ".yml", config);
        }
    }

}
