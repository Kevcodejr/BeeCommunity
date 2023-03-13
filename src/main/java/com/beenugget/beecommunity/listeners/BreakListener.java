package com.beenugget.beecommunity.listeners;

import com.beenugget.beecommunity.objects.AchievementChain;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;
import java.util.List;

public class BreakListener implements Listener {
    private final List<AchievementChain> achievementChains;

    public BreakListener(List<AchievementChain> achievementChains){
        this.achievementChains = achievementChains;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        Player player = event.getPlayer();



    }

}
