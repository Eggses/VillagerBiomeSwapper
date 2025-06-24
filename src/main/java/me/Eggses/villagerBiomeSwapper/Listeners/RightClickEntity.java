package me.Eggses.villagerBiomeSwapper.Listeners;

import me.Eggses.villagerBiomeSwapper.DataManager.TraderManager;
import me.Eggses.villagerBiomeSwapper.Items.SwapperItem;
import me.Eggses.villagerBiomeSwapper.MenusOld.Menu;
import me.Eggses.villagerBiomeSwapper.MenusOld.MenuManager;
import me.Eggses.villagerBiomeSwapper.MenusOld.MenuMetaData;
import me.Eggses.villagerBiomeSwapper.Utility.Permission;
import me.Eggses.villagerBiomeSwapper.Utility.PlaceHolder;
import me.Eggses.villagerBiomeSwapper.VillagerBiomeSwapper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class RightClickEntity implements Listener {

    private final VillagerBiomeSwapper plugin;
    private final SwapperItem swapperItem;
    private final MenuManager menuManager;
    private final TraderManager traderManager;

    public RightClickEntity(VillagerBiomeSwapper plugin, SwapperItem swapperItem,
                            MenuManager menuManager, TraderManager traderManager) {
        this.plugin = plugin;
        this.swapperItem = swapperItem;
        this.menuManager = menuManager;
        this.traderManager = traderManager;
    }

    @EventHandler
    public void rightClickEvent(PlayerInteractEntityEvent event) {

        Entity entity = event.getRightClicked();
        Player player = event.getPlayer();

        if (!(entity instanceof Villager villager)) {
            return;
        }

        // Ensure correct item used.
        ItemStack itemUsed = (event.getHand() == EquipmentSlot.HAND) ? player.getInventory().getItemInMainHand() : player.getInventory().getItemInOffHand();
        if (!swapperItem.isSwapperItem(itemUsed)) return;


        // Ensure can Convert
        if (!player.hasPermission(Permission.CONVERT.getPermission())) {
            return;
        }

       /* Permission[] biomeTypes = Permission.getBiomeTypes();
        boolean hasAtLeastOne = false;
        for (Permission permission : biomeTypes) {
            if (player.hasPermission(permission.getPermission())) {
                hasAtLeastOne = true;
                break;
            }
        }
        if (!hasAtLeastOne) return;

        */


        // Okay now player is allowed to do something now.
        event.setCancelled(true);

        Map<String, String> placeHolders = new HashMap<>();
        placeHolders.put(PlaceHolder.PLAYER.getPlaceHolder(), player.getName());

        traderManager.getVillagerMap().put(player, villager);

        Menu swapMenu = menuManager.getMenu(MenuMetaData.SWAP_MENU.getKey());
        swapMenu.createInventory(player, placeHolders);
    }
}
