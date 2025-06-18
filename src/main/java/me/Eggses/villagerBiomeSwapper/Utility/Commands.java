package me.Eggses.villagerBiomeSwapper.Utility;

public enum Commands {

    BASE("villagerbiomeswapper"),

    GIVE("give"),
    GIVE_ALL("giveall"),

    HELP("help"),

    RELOAD("reload"),
    RELOAD_ITEM("biomeSwapperitem"),
    RELOAD_CONFIG("config"),
    RELOAD_GUI("gui"),
    RELOAD_MESSAGES("messages");

    private final String command;

    Commands(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

}
