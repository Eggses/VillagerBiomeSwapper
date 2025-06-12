package me.Eggses.villagerBiomeSwapper.Items;

import me.Eggses.villagerBiomeSwapper.Config.CustomConfigurationFile;
import me.Eggses.villagerBiomeSwapper.Config.ItemConfig;
import me.Eggses.villagerBiomeSwapper.Utility.MessageCreation;
import me.Eggses.villagerBiomeSwapper.VillagerBiomeSwapper;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    private final VillagerBiomeSwapper plugin;
    private final MessageCreation messageCreation;
    private final CustomConfigurationFile itemsFile;
    private final ItemKeys itemKey;

    public ItemManager(VillagerBiomeSwapper plugin,
                       MessageCreation messageCreation,
                       CustomConfigurationFile itemsFile,
                       ItemKeys itemKey) {

        this.plugin = plugin;
        this.messageCreation = messageCreation;
        this.itemsFile = itemsFile;
        this.itemKey = itemKey;
    }

    public ItemStack createItem(ItemConfig itemConstant) {

        // Get Paths
        String itemMaterialPath = itemConstant.getMaterialPath();
        String itemNamePath = itemConstant.getNamePath();
        String itemLorePath = itemConstant.getLorePath();

        // Get Values
        String itemMaterial = itemsFile.getCustomFile().getString(itemMaterialPath);
        String itemName = itemsFile.getCustomFile().getString(itemNamePath);
        List<String> itemLore = itemsFile.getCustomFile().getStringList(itemLorePath);

        // True Values
        Material material = getMaterial(itemMaterial);

        Component name = messageCreation.createMessage(itemName);

        List<Component> lore = new ArrayList<>();
        for (String line : itemLore) {
            lore.add(messageCreation.createMessage(line));
        }

        return generateItem(material, name, lore);
    }

    private ItemStack generateItem(Material material, Component name, List<Component> lore) {

        ItemStack item = new ItemStack(material);

        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(name);
        itemMeta.lore(lore);

        itemMeta.getPersistentDataContainer().set(itemKey.getKey(), PersistentDataType.BYTE, (byte) 1);

        item.setItemMeta(itemMeta);

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
