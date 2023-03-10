package com.beenugget.beecommunity.objects.data;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// File manager for YML configs
public class FileManager {
    private final String path;

    public FileManager(String path){
        this.path = (path);
    }

    public FileConfiguration getFileConfiguration(String fileName){
        File file = new File(path + fileName);
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

    public List<FileConfiguration> getFileConfigurations(){
        mkdirs();
        List<FileConfiguration> configurations = new ArrayList<>();
        File folder = new File(path);
        File[] files = folder.listFiles();
        if(files == null){
            return configurations;
        }
        for(File file : files){
            FileConfiguration config = getFileConfiguration(file.getName());
            configurations.add(config);
        }
        return configurations;
    }

    public void mkdirs(){
        File file = new File(path);
        file.mkdirs();
    }

    public void setFile(String fileName, FileConfiguration config){
        try {
            File file = new File(path + fileName);
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeFile(String fileName){
        File file = new File(path + fileName);
        if (file.exists()) {
            boolean didFileDelete = file.delete();
        }
    }

    public boolean doesFileExist(String fileName){
        File file = new File(path + fileName);
        return file.exists();
    }
}
