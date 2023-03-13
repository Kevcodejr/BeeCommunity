package com.beenugget.beecommunity.objects;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Achievement {
    private final String title;
    private final String desc;
    private final String key;
    private ItemStack blockBreak = null;
    private ItemStack blockPlace = null;
    private final List<Integer> progress = new ArrayList<>();

    public Achievement(String title, String desc, String key, List<String> actions) {
        this.title = title;
        this.desc = desc;
        this.key = key;
        for(String action : actions){
            String[] actionGroup = action.split(":");
            if(actionGroup[0].equalsIgnoreCase("break")){
                Material material = Material.getMaterial(actionGroup[1].toUpperCase());
                int amount = Integer.parseInt(actionGroup[2]);
                assert material != null;
                blockBreak = new ItemStack(material, amount);
                progress.add(Integer.parseInt(actionGroup[3]));
            }
            else if(actionGroup[0].equalsIgnoreCase("place")){
                Material material = Material.getMaterial(actionGroup[1].toUpperCase());
                int amount = Integer.parseInt(actionGroup[2]);
                assert material != null;
                blockPlace = new ItemStack(material, amount);
                progress.add(Integer.parseInt(actionGroup[3]));
            }
        }
    }

    public List<String> getActions(){
        List<String> actions = new ArrayList<>();
        if(blockBreak != null){
            String action = "break:" + blockBreak.getType().toString().toLowerCase() + ":" + blockBreak.getAmount() + ":" + progress.get(0);
            actions.add(action);
        }
        if(blockPlace != null){
            String action = "place:" + blockPlace.getType().toString().toLowerCase() + ":" + blockPlace.getAmount() + ":" + progress.get(actions.size());
            actions.add(action);
        }

        return actions;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getKey() {
        return key;
    }


    public List<Integer> getProgress() {
        return progress;
    }

    public ItemStack getBreakAction(){
        return blockBreak;
    }

    public ItemStack getPlaceAction(){
        return blockPlace;
    }
}
