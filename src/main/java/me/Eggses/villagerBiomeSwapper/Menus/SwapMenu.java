package me.Eggses.villagerBiomeSwapper.Menus;

import me.Eggses.villagerBiomeSwapper.Config.CustomConfigurationFile;
import me.Eggses.villagerBiomeSwapper.Config.ItemConfig;
import me.Eggses.villagerBiomeSwapper.Utility.MessageCreation;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SwapMenu {

    private static final int INFO = 13;
    private static final int BIOME_PLAINS = 19;
    private static final int BIOME_DESERT = 20;
    private static final int BIOME_SAVANNA = 21;
    private static final int BIOME_TAIGA = 22;
    private static final int BIOME_SNOWY = 23;
    private static final int BIOME_SWAMP = 24;
    private static final int BIOME_JUNGLE = 25;
    private static final int CLOSE = 40;

    private final MessageCreation messageCreation;
    private final CustomConfigurationFile guiFile;
    private final String menuDataKey = MenuKeys.SWAP_MENU.getKey();

    public SwapMenu(MessageCreation messageCreation, CustomConfigurationFile guiFile, String metaDataKey) {
        this.messageCreation = messageCreation;
        this.guiFile = guiFile;
    }

    public void createInventory(Player player, Villager villager, WanderingTrader wanderingTrader) {

    }

    private void run(Player player, int slotClicked) {

    }






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
