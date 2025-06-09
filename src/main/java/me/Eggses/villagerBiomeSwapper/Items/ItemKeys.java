package me.Eggses.villagerBiomeSwapper.Items;

import me.Eggses.villagerBiomeSwapper.VillagerBiomeSwapper;
import org.bukkit.NamespacedKey;

public enum ItemKeys {

    SWAPPER("SwapItem");

    private final String key;
    private NamespacedKey namespacedKey;

    ItemKeys(String key) {
        this.key = key;
        this.namespacedKey = null;
    }

    public static void setKeys(VillagerBiomeSwapper plugin) {
        for (ItemKeys itemKey : ItemKeys.values()) {
            itemKey.namespacedKey = new NamespacedKey(plugin, itemKey.key);
        }
    }

    public NamespacedKey getKey() {
        return this.namespacedKey;
    }
}
