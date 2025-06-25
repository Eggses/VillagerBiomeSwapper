package me.Eggses.villagerBiomeSwapper.Listeners;

import me.Eggses.villagerBiomeSwapper.Items.SwapperItem;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class SheepDyeEvent implements Listener {

    private final SwapperItem swapperItem;

    public SheepDyeEvent(SwapperItem swapperItem) {
        this.swapperItem = swapperItem;
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {

        if (!(event.getRightClicked() instanceof Sheep)) {
            return;
        }

        Player player = event.getPlayer();
        ItemStack itemUsed = null;


        if (event.getHand() == EquipmentSlot.HAND) {
            itemUsed = player.getInventory().getItemInMainHand();
        } else if (event.getHand() == EquipmentSlot.OFF_HAND) {
            itemUsed = player.getInventory().getItemInOffHand();
        }

        if (itemUsed != null && swapperItem.isSwapperItem(itemUsed)) {
            event.setCancelled(true);
        }
    }
}