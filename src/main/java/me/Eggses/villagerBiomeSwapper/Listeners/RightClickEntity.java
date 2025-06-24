package me.Eggses.villagerBiomeSwapper.Listeners;

import me.Eggses.villagerBiomeSwapper.Config.CustomConfigurationFile;
import me.Eggses.villagerBiomeSwapper.Items.SwapperItem;
import me.Eggses.villagerBiomeSwapper.Menus.SwapMenu;
import me.Eggses.villagerBiomeSwapper.Utility.MessageCreation;
import me.Eggses.villagerBiomeSwapper.Utility.Permission;
import me.Eggses.villagerBiomeSwapper.Utility.PlaceHolder;
import me.Eggses.villagerBiomeSwapper.VillagerBiomeSwapper;
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
    private final MessageCreation messageCreation;
    private final CustomConfigurationFile guiFile;

    public RightClickEntity(VillagerBiomeSwapper plugin, SwapperItem swapperItem,
                            MessageCreation messageCreation, CustomConfigurationFile guiFile) {
        this.plugin = plugin;
        this.swapperItem = swapperItem;
        this.messageCreation = messageCreation;
        this.guiFile = guiFile;
    }

    @EventHandler
    public void rightClickEvent(PlayerInteractEntityEvent event) {

        Player player = event.getPlayer();

        if (!(event.getRightClicked() instanceof Villager villager)) {
            return;
        }

        if (!villager.hasAI()) {
            return;
        }

        // Ensure correct item used.
        ItemStack itemUsed = null;

        if (event.getHand() == EquipmentSlot.HAND) {
            itemUsed = player.getInventory().getItemInMainHand();
        }

        if (!swapperItem.isSwapperItem(itemUsed)) {
            return;
        }

        // Ensure can Convert
        if (!player.hasPermission(Permission.CONVERT.getPermission())) {
            return;
        }

        Permission.BiomePermission[] biomePermissions = Permission.BiomePermission.values();
        boolean atLeastOne = false;
        for (Permission.BiomePermission biomePermission : biomePermissions) {
            if (player.hasPermission(biomePermission.getPermission())) {
                atLeastOne = true;
            }
        }
        if (!atLeastOne) return;


        // Okay now player is allowed to do something now.
        event.setCancelled(true);

        Map<String, String> placeHolders = new HashMap<>();
        placeHolders.put(PlaceHolder.PLAYER.getPlaceHolder(), player.getName());

        SwapMenu swapMenu = new SwapMenu(player, villager, placeHolders, plugin, swapperItem, guiFile, messageCreation);
        swapMenu.openInventory();
    }
}
