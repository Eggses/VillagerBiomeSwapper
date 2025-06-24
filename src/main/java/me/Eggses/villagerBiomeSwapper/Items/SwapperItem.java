package me.Eggses.villagerBiomeSwapper.Items;

import me.Eggses.villagerBiomeSwapper.Config.CustomConfigurationFile;
import me.Eggses.villagerBiomeSwapper.Utility.MessageCreation;
import me.Eggses.villagerBiomeSwapper.VillagerBiomeSwapper;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SwapperItem {

    private final MessageCreation messageCreation;
    private final CustomConfigurationFile biomeSwapperItemFile;

    private final NamespacedKey itemKey;

    public SwapperItem(VillagerBiomeSwapper plugin, MessageCreation messageCreation,
                       CustomConfigurationFile biomeSwapperItemFile) {

        this.messageCreation = messageCreation;
        this.biomeSwapperItemFile = biomeSwapperItemFile;

        itemKey = new NamespacedKey(plugin, "SwapperItem");
    }

    public void giveSwapperItemToPlayer(Player player, Map<String, String> placeHolders) {

        ItemStack item = createSwapperItem(placeHolders);

        PlayerInventory inventory = player.getInventory();
        ItemStack[] inventoryContents = inventory.getContents();

        int firstValidSlot = -1;

        for (int i = 0; i < 36; i++) {
            if (inventoryContents[i] == null) {
                firstValidSlot = i;
                break;
            }
        }

        if (firstValidSlot != -1) {
            inventory.setItem(firstValidSlot, item);
        } else {
            player.getWorld().dropItem(player.getLocation(), item);
        }
    }

    public boolean isSwapperItem(ItemStack item) {

        if (item == null) return false;
        if (item.getItemMeta() == null) return false;

        ItemMeta itemMeta = item.getItemMeta();

        return itemMeta.getPersistentDataContainer().has(itemKey, PersistentDataType.BYTE);
    }

    private ItemStack createSwapperItem(Map<String, String> placeHolders) {

        String materialPath = "biome-swapper-item.item-material";
        String itemNamePath = "biome-swapper-item.item-name";
        String itemLorePath = "biome-swapper-item.item-lore";
        String itemGlowPath = "biome-swapper-item.item-glow";

        String materialValue = biomeSwapperItemFile.getCustomFile().getString(materialPath);
        String itemNameValue = biomeSwapperItemFile.getCustomFile().getString(itemNamePath);
        List<String> itemLoreValue = biomeSwapperItemFile.getCustomFile().getStringList(itemLorePath);
        boolean glow = biomeSwapperItemFile.getCustomFile().getBoolean(itemGlowPath);

        Material material = getMaterial(materialValue);
        Component name = messageCreation.createMessage(itemNameValue, placeHolders);

        List<Component> lore = new ArrayList<>();

        for (String line : itemLoreValue) {
            lore.add(messageCreation.createMessage(line, placeHolders));
        }
        
        ItemStack item = new ItemStack(material);

        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.getPersistentDataContainer().set(itemKey, PersistentDataType.BYTE, (byte) 1);
        itemMeta.displayName(name);
        itemMeta.lore(lore);
        itemMeta.setMaxStackSize(1);
        if (glow) itemMeta.setEnchantmentGlintOverride(true);

        return item;
    }

    private Material getMaterial(String itemMaterial) {

        Material material = Material.getMaterial(itemMaterial);

        if (material == null) {
            material = Material.BARRIER;
        }

        return material;
    }
}