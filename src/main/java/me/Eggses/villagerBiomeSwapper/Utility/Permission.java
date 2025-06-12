package me.Eggses.villagerBiomeSwapper.Utility;

public enum Permission {

    GIVE_ITEM("give.item"),
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
