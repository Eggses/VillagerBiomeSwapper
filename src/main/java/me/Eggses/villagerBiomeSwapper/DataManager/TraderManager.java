package me.Eggses.villagerBiomeSwapper.DataManager;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class TraderManager {

    private final Map<Player, Entity> entityMap;

    public TraderManager() {
        entityMap = new HashMap<>();
    }

    public void addEntry(Player player, Entity entity) {
        entityMap.put(player, entity);
    }

    public void removeEntry(Player player) {
        entityMap.remove(player);
    }

    public Entity getEntity(Player player) {
        return entityMap.get(player);
    }
}