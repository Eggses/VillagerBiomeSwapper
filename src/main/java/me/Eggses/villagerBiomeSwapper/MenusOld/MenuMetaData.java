package me.Eggses.villagerBiomeSwapper.MenusOld;

public enum MenuMetaData {

    SWAP_MENU("SwapMenu");

    private final String key;

    MenuMetaData(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }
}
