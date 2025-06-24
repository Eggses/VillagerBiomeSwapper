package me.Eggses.villagerBiomeSwapper.MenusOld;

import me.Eggses.villagerBiomeSwapper.Config.CustomConfigurationFile;
import me.Eggses.villagerBiomeSwapper.DataManager.TraderManager;
import me.Eggses.villagerBiomeSwapper.Items.SwapperItem;
import me.Eggses.villagerBiomeSwapper.Utility.MessageCreation;
import me.Eggses.villagerBiomeSwapper.VillagerBiomeSwapper;

import java.util.HashMap;
import java.util.Map;

public final class MenuManager {

    private final VillagerBiomeSwapper plugin;
    private final MessageCreation messageCreation;
    private final CustomConfigurationFile guiFile;
    private final TraderManager traderManager;
    private final SwapperItem swapperItem;

    private final Map<String, Menu> menuMap;

    public MenuManager(VillagerBiomeSwapper plugin, MessageCreation messageCreation,
                       CustomConfigurationFile guiFile, TraderManager traderManager,
                       SwapperItem swapperItem) {
        this.plugin = plugin;
        this.messageCreation = messageCreation;
        this.guiFile = guiFile;
        this.traderManager = traderManager;
        this.swapperItem = swapperItem;

        menuMap = new HashMap<>();

        registerMenus();
    }

    public void registerMenus() {
        menuMap.put(MenuMetaData.SWAP_MENU.getKey(), new SwapMenu(plugin, traderManager, messageCreation, guiFile, MenuMetaData.SWAP_MENU, swapperItem));
    }

    public Menu getMenu(String menuKey) {
        return menuMap.get(menuKey);
    }






}
