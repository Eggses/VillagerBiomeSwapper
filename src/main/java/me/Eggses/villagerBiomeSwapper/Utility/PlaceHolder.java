package me.Eggses.villagerBiomeSwapper.Utility;

public enum PlaceHolder {

    PREFIX("%prefix%"),
    PLAYER("%player%"),
    TARGET_PLAYER("%target%");

    private final String placeHolder;

    PlaceHolder(String placeHolder) {
        this.placeHolder = placeHolder;
    }

    public String getPlaceHolder() {
        return placeHolder;
    }
}