package me.Eggses.villagerBiomeSwapper.MenusOld;

import me.Eggses.villagerBiomeSwapper.Config.CustomConfigurationFile;
import me.Eggses.villagerBiomeSwapper.Utility.MessageCreation;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Menu {

    private final MessageCreation messageCreation;
    private final CustomConfigurationFile guiFile;

    protected Menu(MessageCreation messageCreation, CustomConfigurationFile guiFile) {
        this.messageCreation = messageCreation;
        this.guiFile = guiFile;
    }

    public abstract void createInventory(Player player, Map<String, String> placeHolders);
    public abstract void run(Player player, int slotClicked);

    protected ItemStack createItem(String itemMaterialPath, String itemNamePath, String itemLorePath) {

        // Get Values
        String itemMaterial = guiFile.getCustomFile().getString(itemMaterialPath);
        String itemName = guiFile.getCustomFile().getString(itemNamePath);
        List<String> itemLore = guiFile.getCustomFile().getStringList(itemLorePath);

        // True Values
        Material material = getMaterial(itemMaterial);

        Component name = messageCreation.createMessage(itemName, null);

        List<Component> lore = new ArrayList<>();
        for (String line : itemLore) {
            lore.add(messageCreation.createMessage(line, null));
        }

        ItemStack item = new ItemStack(material);

        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(name);
        itemMeta.lore(lore);
        item.setItemMeta(itemMeta);

        return item;
    }

    protected ItemStack createPanelItem(String itemMaterialPath) {

       String itemMaterial = guiFile.getCustomFile().getString(itemMaterialPath);

       Material material = getMaterial(itemMaterial);

       ItemStack item = new ItemStack(material);
       ItemMeta itemMeta = item.getItemMeta();

       itemMeta.setHideTooltip(true);
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