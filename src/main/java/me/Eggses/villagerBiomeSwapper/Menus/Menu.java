package me.Eggses.villagerBiomeSwapper.Menus;

import me.Eggses.villagerBiomeSwapper.Config.CustomConfigurationFile;
import me.Eggses.villagerBiomeSwapper.Utility.MessageCreation;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public abstract class Menu implements InventoryHolder {

    private final Inventory inventory;
    private final Map<Integer, Consumer<Player>> actions = new HashMap<>();
    private final CustomConfigurationFile guiFile;
    private final MessageCreation messageCreation;

    protected Menu(Component title, int rowCount,
                   CustomConfigurationFile guiFile, MessageCreation messageCreation) {

        // This class is the owner as it's an inventory holder.
        inventory = Bukkit.createInventory(this, rowCount * 9, title);

        this.guiFile = guiFile;
        this.messageCreation = messageCreation;
    }

    public void run(Player player, int slot) {
        Consumer<Player> action = actions.get(slot);

        if (action != null) {
            action.accept(player);
        }
    }

    protected void placeItem(ItemStack item, int slot) {
        inventory.setItem(slot, item);
    }

    protected void placeItem(ItemStack item, int slot, Consumer<Player> action) {
        inventory.setItem(slot, item);
        actions.put(slot, action);
    }

    protected ItemStack createItem(MenuItem menuItem) {

        String itemMaterialPath = menuItem.getMaterialPath();
        String itemNamePath = menuItem.getNamePath();
        String itemLorePath = menuItem.getLorePath();

        FileConfiguration guiFileConfiguration = guiFile.getCustomFile();
        String itemMaterial = guiFileConfiguration.getString(itemMaterialPath);
        String itemName = guiFileConfiguration.getString(itemNamePath);
        List<String> itemLore = guiFileConfiguration.getStringList(itemLorePath);

        Material material = getMaterial(itemMaterial);

        Component name = messageCreation.createMessage(itemName);

        List<Component> lore = new ArrayList<>();
        for (String line : itemLore) {
            lore.add(messageCreation.createMessage(line));
        }

        ItemStack item = new ItemStack(material, 1);

        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(name);
        itemMeta.lore(lore);
        item.setItemMeta(itemMeta);

        return item;
    }

    protected ItemStack createPanelItem(MenuItem menuItem) {

        String itemMaterialPath = menuItem.getMaterialPath();
        String itemMaterial = guiFile.getCustomFile().getString(itemMaterialPath);

        Material material = getMaterial(itemMaterial);

        ItemStack item = new ItemStack(material, 1);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setHideTooltip(true);
        item.setItemMeta(itemMeta);

        return item;
    }

    private Material getMaterial(String materialName) {
        Material material = Material.getMaterial(materialName);
        if (material == null) material = Material.BARRIER;

        return material;
    }

    @Override // Method is by default deferred, choosing to implement here.
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }

    protected void openInventory(Player player) {
        player.openInventory(inventory);
    }

    protected abstract void setInventoryItems();

    protected enum Row {
        ONE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6);

        private final int row;

        Row(int row) {
            this.row = row;
        }

        public int getSlotCount() {
            return row * 9;
        }
    }
}