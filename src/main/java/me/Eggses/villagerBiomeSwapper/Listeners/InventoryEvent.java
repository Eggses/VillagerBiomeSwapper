package me.Eggses.villagerBiomeSwapper.Listeners;

import me.Eggses.villagerBiomeSwapper.Menus.Menu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class InventoryEvent implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {

        Inventory clickedInventory = event.getClickedInventory();

        if (clickedInventory == null) {
            return;
        }

        if (!(clickedInventory.getHolder() instanceof Menu menu)) {
            return;
        }

        event.setCancelled(true);

        ClickType clickType = event.getClick();
        if (clickType != ClickType.LEFT && clickType != ClickType.RIGHT) {
            return;
        }

        int slotClicked = event.getSlot();
        menu.run(slotClicked);
    }
}
