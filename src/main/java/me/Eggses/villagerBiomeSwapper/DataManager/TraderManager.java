package me.Eggses.villagerBiomeSwapper.DataManager;

import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.HashMap;
import java.util.Map;

public class TraderManager {

    private final Map<Player, Villager> villagerMap;
    private final Map<Player, Villager.Type> currentVillagerType;

    public TraderManager() {
        villagerMap = new HashMap<>();
        currentVillagerType = new HashMap<>();
    }

    public Map<Player, Villager> getVillagerMap() {
        return villagerMap;
    }

    public Map<Player, Villager.Type> getCurrentVillagerType() {
        return currentVillagerType;
    }

}