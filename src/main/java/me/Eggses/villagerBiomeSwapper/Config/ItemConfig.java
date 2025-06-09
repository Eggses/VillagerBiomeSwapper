package me.Eggses.villagerBiomeSwapper.Config;

public enum ItemConfig {

    SWAPPER_ITEM("BiomeSwapperItem.");

    private static final String materialPath = "item-material";
    private static final String namePath = "item-name";
    private static final String lorePath = "item-lore";

    private final String itemHeading;

    ItemConfig(String itemHeading) {
        this.itemHeading = itemHeading;
    }

    public String getMaterialPath() {
        return itemHeading + materialPath;
    }

    public String getNamePath() {
        return itemHeading + namePath;
    }

    public String getLorePath() {
        return itemHeading + lorePath;
    }
}