package com.beenugget.beecommunity.objects;

import java.util.ArrayList;
import java.util.List;

public class AchievementChain {
    private final List<Achievement> achievements = new ArrayList<>();
    private final boolean isGlobal;
    private String chainName = null;
    private boolean isModified = false;

    public AchievementChain(String chainName, boolean global) {
        isGlobal = global;
        this.chainName = chainName;
    }

    public String getChainName() {
        return chainName;
    }

    public List<Achievement> getAchievements() {
        return achievements;
    }

    public void addAchievement(Achievement achievement){
        achievements.add(achievement);
    }

    public boolean isGlobal() {
        return isGlobal;
    }

    public boolean isModified() {
        return isModified;
    }

    public void setModified(boolean modified) {
        isModified = modified;
    }
}
