package me.Eggses.villagerBiomeSwapper.Config;

public enum Messages {

    PREFIX("prefix"),

    UNKNOWN_COMMAND("unknown-command"),
    PERMISSION_FAIL("no-permission"),
    NOT_PLAYER("non-player"),
    UNKNOWN_PLAYER("unknown-player"),

    GIVE_ITEM_TARGET("give.grant-item-target"),
    GIVE_ITEM_SELF("give.grant-item-self"),
    GIVE_ITEM_SYNTAX_ERROR("give.syntax-error"),

    GIVE_ALL_ITEM("give-all.grant-item"),
    GIVE_ALL_ITEM_SYNTAX_ERROR("give-all.syntax-error"),

    HELP("help.help-message"),
    HELP_SYNTAX_ERROR("help.syntax-error"),

    RELOAD_ALL_CONFIGS("reload.all-configs"),
    RELOAD_CONFIG("reload.config"),
    RELOAD_MESSAGES("reload.messages"),
    RELOAD_BIOME_SWAPPER_ITEM("reload.item"),
    RELOAD_GUI("reload.gui"),
    RELOAD_SYNTAX_ERROR("reload.syntax-error");

    private static CustomConfigurationFile messagesFile;

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return messagesFile.getCustomFile().getString(message);
    }

    public static void setMessagesFile(CustomConfigurationFile messagesFile) {
        Messages.messagesFile = messagesFile;
    }
}
