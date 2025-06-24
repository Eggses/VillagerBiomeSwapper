package me.Eggses.villagerBiomeSwapper.Menus;

import me.Eggses.villagerBiomeSwapper.Config.CustomConfigurationFile;
import me.Eggses.villagerBiomeSwapper.Utility.MessageCreation;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class RightClick implements Listener {

    private final CustomConfigurationFile guiFile;
    private final MessageCreation messageCreation;

    public RightClick(CustomConfigurationFile guiFile, MessageCreation messageCreation) {
        this.guiFile = guiFile;
        this.messageCreation = messageCreation;
    }

    @EventHandler
    public void onRightClickEntity(PlayerInteractEntityEvent event) {

        if (!(event.getRightClicked() instanceof Villager villager)) {
            return;
        }

        Player player = event.getPlayer();

        SwapMenu swapMenu = new SwapMenu(player, villager, guiFile, messageCreation);
        swapMenu.openGUI();

        // should work
    }
}
