package me.Eggses.villagerBiomeSwapper.Menus;

import me.Eggses.villagerBiomeSwapper.Config.CustomConfigurationFile;
import me.Eggses.villagerBiomeSwapper.Utility.MessageCreation;
import org.bukkit.entity.Player;

public class SwapMenu extends Menu {

    public SwapMenu(MessageCreation messageCreation, CustomConfigurationFile guiFile, String metaDataKey) {
        super(messageCreation, guiFile, metaDataKey);
    }

    @Override
    public void createInventory(Player player) {

    }

    @Override
    public void run(Player player, int slotClicked) {

    }
}
