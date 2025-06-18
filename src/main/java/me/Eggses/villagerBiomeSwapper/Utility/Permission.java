package me.Eggses.villagerBiomeSwapper.Utility;

public enum Permission {

    BASE("base"),
    GIVE_ITEM("give"),
    RELOAD("reload"),
    HELP("help");

    private static final String base = "villagerbiomeswapper.";
    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return base + permission;
    }
}
