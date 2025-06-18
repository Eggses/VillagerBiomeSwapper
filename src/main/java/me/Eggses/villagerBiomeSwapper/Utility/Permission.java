package me.Eggses.villagerBiomeSwapper.Utility;

public enum Permission {

    BASE("base"),
    GIVE_ITEM("give"),
    RELOAD("reload"),
    HELP("help"),

    VILLAGER("convert.entity.villager"),
    TRADER("convert.entity.wanderingtrader"),
    DESERT("convert.biome.desert"),
    JUNGLE("convert.biome.jungle"),
    PLAINS("convert.biome.plains"),
    SAVANNA("convert.biome.savanna"),
    SNOW("convert.biome.snow"),
    SWAMP("convert.biome.swamp"),
    TAIGA("convert.biome.taiga");

    private static final String base = "villagerbiomeswapper.";
    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return base + permission;
    }

    public static Permission[] getBiomeTypes() {
        return new Permission[] {DESERT, JUNGLE, PLAINS, SAVANNA, SNOW, SWAMP, TAIGA};
    }
}
