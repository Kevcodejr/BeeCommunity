package com.beenugget.beecommunity.data;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

// File manager for YML configs
public class FileManager {
    private final String path;

    public FileManager(String path){
        this.path = path;
    }

    public FileConfiguration getFileConfiguration(String fileName){
        File file = new File(path + fileName + ".yml");
        try {
            if (file.exists()) {
                FileConfiguration config = new YamlConfiguration();
                config.load(file);
                return config;
            }
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setFile(String fileName, FileConfiguration config){
        try {
            File file = new File(path + fileName + ".yml");
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeFile(String fileName){
        File file = new File(path + fileName + ".yml");
        if (file.exists()) {
            boolean didFileDelete = file.delete();
        }
    }

    public boolean doesFileExist(String fileName){
        File file = new File(path + fileName + ".yml");
        return file.exists();
    }
}
