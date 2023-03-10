package com.beenugget.beecommunity.objects;

import com.beenugget.beecommunity.objects.data.FileManager;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import java.util.logging.Logger;

public class AchievementChainConfig {
    private FileManager fileManager = null;
    private String path = null;
    private final Logger log;

    public AchievementChainConfig(String path, Logger log) {
        this.log = log;
        this.path = path + "/achievements/";
        fileManager = new FileManager(this.path);
        loadAll();
    }

    public void loadAll(){
        List<FileConfiguration> configs = fileManager.getFileConfigurations();
        log.severe("List of config names: ");
        for(FileConfiguration config : configs){
//            System.out.println(config.getName());
//            log.warning(config.getString("text"));
            for(String key : config.getConfigurationSection("achievements").getKeys(false)){
                String t = "achievements." + key;
                ConfigurationSection test = config.getConfigurationSection(t);
                String output = test.getString("text");
                log.warning(key + output);
            }


        }
    }

}
