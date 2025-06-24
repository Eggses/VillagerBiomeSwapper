package me.Eggses.villagerBiomeSwapper.Menus;

public interface MenuItem {

    String NAME_PATH = "name-path";
    String LORE_PATH = "lore-path";
    String MATERIAL_PATH = "materialPath";

    String getNamePath();
    String getMaterialPath();
    String getLorePath();
}
