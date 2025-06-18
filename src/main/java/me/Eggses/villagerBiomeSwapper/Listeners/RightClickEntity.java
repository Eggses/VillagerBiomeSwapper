package me.Eggses.villagerBiomeSwapper.Listeners;

import me.Eggses.villagerBiomeSwapper.Config.DefaultConfig;
import me.Eggses.villagerBiomeSwapper.DataManager.TraderManager;
import me.Eggses.villagerBiomeSwapper.Items.SwapperItem;
import me.Eggses.villagerBiomeSwapper.Menus.SwapMenu;
import me.Eggses.villagerBiomeSwapper.Utility.Permission;
import me.Eggses.villagerBiomeSwapper.VillagerBiomeSwapper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class RightClickEntity implements Listener {

    private final VillagerBiomeSwapper plugin;
    private final SwapperItem swapperItem;
    private final SwapMenu swapMenu;
    private final TraderManager traderManager;

    public RightClickEntity(VillagerBiomeSwapper plugin, SwapperItem swapperItem, SwapMenu swapMenu,
                            TraderManager traderManager) {
        this.plugin = plugin;
        this.swapperItem = swapperItem;
        this.swapMenu = swapMenu;
        this.traderManager = traderManager;
    }

    @EventHandler
    public void rightClickEvent(PlayerInteractEntityEvent event) {

        Entity entity = event.getRightClicked();
        Player player = event.getPlayer();

        // Remove all other entities.
        if (entity.getType() != EntityType.VILLAGER || entity.getType() != EntityType.WANDERING_TRADER) {
            return;
        }

        // Ensure correct item used.
        ItemStack itemUsed = (event.getHand() == EquipmentSlot.HAND)
                ? player.getInventory().getItemInMainHand() : player.getInventory().getItemInOffHand();

        if (itemUsed == null) return;

        if (!swapperItem.isSwapperItem(itemUsed)) {
            return;
        }

        // Ensure if villager, villager allowed.
        if (entity.getType() == EntityType.VILLAGER && !(plugin.getConfig().getBoolean(DefaultConfig.CONFIG_VILLAGER_ENABLED.getPath()))) {
            return;
        }

        //Ensure if trader, trader allowed.
        if (entity.getType() == EntityType.WANDERING_TRADER && !(plugin.getConfig().getBoolean(DefaultConfig.CONFIG_TRADER_ENABLED.getPath()))) {
            return;
        }

        // Ensure if villager, player can convert.
        if (entity.getType() == EntityType.VILLAGER && !(player.hasPermission(Permission.VILLAGER.getPermission()))) {
            return;
        }

        // Ensure if trader, player can convert.
        if (entity.getType() == EntityType.WANDERING_TRADER && !(player.hasPermission(Permission.TRADER.getPermission()))) {
            return;
        }

        Permission[] biomeTypes = Permission.getBiomeTypes();
        boolean hasAtLeastOne = false;
        for (Permission permission : biomeTypes) {
            if (player.hasPermission(permission.getPermission())) {
                hasAtLeastOne = true;
                break;
            }
        }
        if (!hasAtLeastOne) return;

        // Okay now player is allowed to do something now.
        event.setCancelled(true);
        traderManager.addEntry(player, entity);
        swapMenu.createInventory(player);
    }
}
