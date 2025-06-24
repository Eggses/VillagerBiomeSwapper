package me.Eggses.villagerBiomeSwapper.Utility;

import org.bukkit.entity.Villager;

public enum Permission {

    BASE("base"),
    GIVE_ITEM("give"),
    RELOAD("reload"),
    HELP("help"),

    CONVERT("convert.entity.villager");

    private static final String base = "villagerbiomeswapper.";
    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return base + permission;
    }

    public enum BiomePermission {
        DESERT("convert.biome.desert", Villager.Type.DESERT),
        JUNGLE("convert.biome.jungle", Villager.Type.JUNGLE),
        PLAINS("convert.biome.plains", Villager.Type.PLAINS),
        SAVANNA("convert.biome.savanna", Villager.Type.SAVANNA),
        SNOW("convert.biome.snow", Villager.Type.SNOW),
        SWAMP("convert.biome.swamp", Villager.Type.SWAMP),
        TAIGA("convert.biome.taiga", Villager.Type.TAIGA);

        private final String permission;
        private final Villager.Type villagerType;

        BiomePermission(String permission, Villager.Type villagerType) {
            this.permission = permission;
            this.villagerType = villagerType;
        }

        public String getPermission() {
            return base + permission;
        }

        public Villager.Type getVillagerType() {
            return villagerType;
        }

        public static BiomePermission getPermission(Villager.Type villagerType) {

            for (BiomePermission biomePermission : BiomePermission.values()) {
                if (biomePermission.villagerType == villagerType) {
                    return biomePermission;
                }
            }
            return null;
        }
    }
}