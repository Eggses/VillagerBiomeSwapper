package me.Eggses.villagerBiomeSwapper.Menus;

import me.Eggses.villagerBiomeSwapper.Config.CustomConfigurationFile;
import me.Eggses.villagerBiomeSwapper.Utility.MessageCreation;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public abstract class Menu {

    private final MessageCreation messageCreation;
    private final CustomConfigurationFile guiFile;

    protected Menu(MessageCreation messageCreation, CustomConfigurationFile guiFile) {
        this.messageCreation = messageCreation;
        this.guiFile = guiFile;
    }

    public abstract void createInventory(Player player);
    public abstract void run(Player player, int slotClicked);

    protected ItemStack createItem(ItemConfig itemConstant) {

        // Get Paths
        String itemMaterialPath = itemConstant.getMaterialPath();
        String itemNamePath = itemConstant.getNamePath();
        String itemLorePath = itemConstant.getLorePath();

        // Get Values
        String itemMaterial = guiFile.getCustomFile().getString(itemMaterialPath);
        String itemName = guiFile.getCustomFile().getString(itemNamePath);
        List<String> itemLore = guiFile.getCustomFile().getStringList(itemLorePath);

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
        item.setItemMeta(itemMeta);

        return item;
    }

    protected ItemStack createPanelItem(ItemConfig itemConstant) {

       String itemMaterialPath = itemConstant.getMaterialPath();
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