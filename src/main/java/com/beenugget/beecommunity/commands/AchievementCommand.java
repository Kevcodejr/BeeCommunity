package com.beenugget.beecommunity.commands;

import com.beenugget.beecommunity.objects.Achievement;
import com.beenugget.beecommunity.objects.AchievementChain;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import java.util.ArrayList;
import java.util.List;

public class AchievementCommand implements CommandExecutor {
    private final List<AchievementChain> achievementChains;
    public AchievementCommand(List<AchievementChain> achievementChains){
        this.achievementChains = achievementChains;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof ConsoleCommandSender){
            ConsoleCommandSender console = Bukkit.getConsoleSender();
            for(AchievementChain achievementChain : achievementChains){
                for(Achievement achievement : achievementChain.getAchievements()){
                    console.sendMessage(achievement.getTitle());
                    console.sendMessage(String.valueOf(achievement.getBreakAction().getAmount()));

                }
            }
        }



        return true;
    }
}
