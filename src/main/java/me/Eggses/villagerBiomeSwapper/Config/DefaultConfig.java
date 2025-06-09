package me.Eggses.villagerBiomeSwapper.Config;

public enum DefaultConfig {

    CONFIG_VILLAGER_ENABLED("villager-biome-change"),
    CONFIG_TRADER_ENABLED("wandering-trader-to-villager-conversion");

    private final String path;

    DefaultConfig(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }
}