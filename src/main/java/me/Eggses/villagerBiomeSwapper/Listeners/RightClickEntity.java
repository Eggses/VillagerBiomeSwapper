package me.Eggses.villagerBiomeSwapper.Listeners;

import me.Eggses.villagerBiomeSwapper.Utility.MessageCreation;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class RightClickEntity implements Listener {

    private final MessageCreation messageCreation;

    public RightClickEntity(MessageCreation messageCreation) {
        this.messageCreation = messageCreation;
    }

    @EventHandler
    public void rightClickEvent(PlayerInteractEntityEvent event) {





    }

}
