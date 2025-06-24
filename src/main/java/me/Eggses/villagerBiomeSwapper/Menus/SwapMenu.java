package me.Eggses.villagerBiomeSwapper.Menus;

import me.Eggses.villagerBiomeSwapper.Config.CustomConfigurationFile;
import me.Eggses.villagerBiomeSwapper.Items.SwapperItem;
import me.Eggses.villagerBiomeSwapper.Utility.MessageCreation;
import me.Eggses.villagerBiomeSwapper.Utility.Permission;
import me.Eggses.villagerBiomeSwapper.VillagerBiomeSwapper;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;
import java.util.List;

public class SwapMenu extends Menu {

    private final Player player;
    private final Villager villager;

    private final VillagerBiomeSwapper villagerBiomeSwapper;
    private final SwapperItem swapperItem;
    private final Map<String, String> placeHolders;
    private final CustomConfigurationFile guiFile;
    private final MessageCreation messageCreation;

    public SwapMenu(Player player, Villager villager, Map<String, String> placeHolders,
                    VillagerBiomeSwapper villagerBiomeSwapper, SwapperItem swapperItem,
                    CustomConfigurationFile guiFile, MessageCreation messageCreation) {

        super(
                messageCreation.createMessage(
                        guiFile.getCustomFile().getString("biome-swapper-gui.title"), placeHolders),
                Row.FIVE.getSlotCount(),
                player,
                placeHolders,
                guiFile,
                messageCreation
        );

        this.player = player;
        this.villager = villager;
        this.villagerBiomeSwapper = villagerBiomeSwapper;
        this.swapperItem = swapperItem;
        this.placeHolders = placeHolders;
        this.guiFile = guiFile;
        this.messageCreation = messageCreation;

        setInventoryItems();
    }

    @Override
    protected void setInventoryItems() {

        // Items
        ItemStack infoItem = createItem(SwapMenuPath.INFO_ITEM);
        ItemStack plainsItem = createItem(SwapMenuPath.PLAINS_ITEM);
        ItemStack savannaItem = createItem(SwapMenuPath.SAVANNA_ITEM);
        ItemStack desertItem = createItem(SwapMenuPath.DESERT_ITEM);
        ItemStack taigaItem = createItem(SwapMenuPath.TAIGA_ITEM);
        ItemStack snowyItem = createItem(SwapMenuPath.SNOWY_ITEM);
        ItemStack jungleItem = createItem(SwapMenuPath.JUNGLE_ITEM);
        ItemStack swampItem = createItem(SwapMenuPath.SWAMP_ITEM);
        ItemStack closeItem = createItem(SwapMenuPath.CLOSE_ITEM);
        ItemStack panelItem = createPanelItem(SwapMenuPath.PANEL_ITEM);

        // Update Items
        Map<ItemStack, Villager.Type> biomeItemsTypeMap = new HashMap<>();
        biomeItemsTypeMap.put(plainsItem, Villager.Type.PLAINS);
        biomeItemsTypeMap.put(savannaItem, Villager.Type.SAVANNA);
        biomeItemsTypeMap.put(desertItem, Villager.Type.DESERT);
        biomeItemsTypeMap.put(taigaItem, Villager.Type.TAIGA);
        biomeItemsTypeMap.put(snowyItem, Villager.Type.SNOW);
        biomeItemsTypeMap.put(jungleItem, Villager.Type.JUNGLE);
        biomeItemsTypeMap.put(swampItem, Villager.Type.SWAMP);

        updateItems(biomeItemsTypeMap);


        // Setting Items
        placeItem(infoItem, SwapMenuPath.INFO_ITEM.position);
        placeItem(plainsItem, SwapMenuPath.PLAINS_ITEM.position, () -> swapBiomeType(Villager.Type.PLAINS));
        placeItem(savannaItem, SwapMenuPath.SAVANNA_ITEM.position, () -> swapBiomeType(Villager.Type.SAVANNA));
        placeItem(desertItem, SwapMenuPath.DESERT_ITEM.position, () -> swapBiomeType(Villager.Type.DESERT));
        placeItem(taigaItem, SwapMenuPath.TAIGA_ITEM.position, () -> swapBiomeType(Villager.Type.TAIGA));
        placeItem(snowyItem, SwapMenuPath.SNOWY_ITEM.position, () -> swapBiomeType(Villager.Type.SNOW));
        placeItem(jungleItem, SwapMenuPath.JUNGLE_ITEM.position, () -> swapBiomeType(Villager.Type.JUNGLE));
        placeItem(swampItem, SwapMenuPath.SWAMP_ITEM.position, () -> swapBiomeType(Villager.Type.SWAMP));
        placeItem(closeItem, SwapMenuPath.CLOSE_ITEM.position, () -> getInventory().close());

        ItemStack[] inventoryContents = super.getInventory().getContents();
        for (int i = 0; i < inventoryContents.length; i++) {
            if (inventoryContents[i] == null) {
                placeItem(panelItem, i);
            }
        }
    }

    private void updateItems(Map<ItemStack, Villager.Type> biomeItemsTypeMap) {

        List<String> invalidSwapLore = guiFile.getCustomFile().getStringList("biome-swapper-gui.invalid-swap-text");
        List<String> permissionFailLore = guiFile.getCustomFile().getStringList("biome-swapper-gui.permission-fail-swap-text");

        List<Component> invalidSwap = new ArrayList<>();
        List<Component> permissionFail = new ArrayList<>();

        for (String line : invalidSwapLore) {
            invalidSwap.add(messageCreation.createMessage(line, placeHolders));
        }
        for (String line : permissionFailLore) {
            permissionFail.add(messageCreation.createMessage(line, placeHolders));
        }

        for (Map.Entry<ItemStack, Villager.Type> entry : biomeItemsTypeMap.entrySet()) {

            ItemStack item = entry.getKey();
            Villager.Type biomeType = entry.getValue();

            // Update Lore if No Permission
            Permission.BiomePermission biomePermission = Permission.BiomePermission.getPermission(biomeType);
            if (biomePermission != null) {
                if (!player.hasPermission(biomePermission.getPermission())) {
                    ItemMeta itemMeta = item.getItemMeta();
                    itemMeta.lore(permissionFail);
                    item.setItemMeta(itemMeta);
                }
            }

            // Update Item if current Type
            if (villager.getVillagerType() == biomeType) {
                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.lore(invalidSwap);
                itemMeta.setEnchantmentGlintOverride(true);
                item.setItemMeta(itemMeta);
                break;
            }
        }
    }

    private void swapBiomeType(Villager.Type targetType) {

        Villager.Type currentType = villager.getVillagerType();

        if (currentType == targetType) return;

        Permission.BiomePermission biomePermission = Permission.BiomePermission.getPermission(targetType);
        if (biomePermission == null) return;
        if (!player.hasPermission(biomePermission.getPermission())) return;

        // Valid Swap

        // Take Item
        ItemStack mainHand = player.getInventory().getItemInMainHand();

        if (swapperItem.isSwapperItem(mainHand)) {
            player.getInventory().setItemInMainHand(null);
        }

        // Play Sound
        if (villagerBiomeSwapper.getConfig().getBoolean("biome-swap-play-sound")) {

            String soundToPlay = "minecraft:entity.evoker.prepare_wololo";
            String configSoundToPlay = villagerBiomeSwapper.getConfig().getString("biome-swap-sound");
            if (configSoundToPlay != null) {
                soundToPlay = configSoundToPlay;
            }
            Sound sound = Sound.sound(Key.key(soundToPlay), Sound.Source.PLAYER, 1.0f, 1.0f);

            player.playSound(sound, Sound.Emitter.self());
        }

        villager.setVillagerType(targetType);
        getInventory().close();
    }

    enum SwapMenuPath implements MenuItem {

        INFO_ITEM("info-item", 13),
        PLAINS_ITEM("plains-item", 19),
        SAVANNA_ITEM("savanna-item", 20),
        DESERT_ITEM("desert-item", 21),
        TAIGA_ITEM("taiga-item", 22),
        SNOWY_ITEM("snowy-item", 23),
        JUNGLE_ITEM("jungle-item", 24),
        SWAMP_ITEM("swamp-item", 25),
        CLOSE_ITEM("close-item", 40),
        PANEL_ITEM("panel-item", -1);

        private static final String BASE_PATH = "biome-swapper-gui.";
        private static final String MATERIAL_PATH = ".item-material";
        private static final String NAME_PATH = ".item-name";
        private static final String LORE_PATH = ".item-lore";

        private final String itemPath;
        private final int position;

        SwapMenuPath(String itemPath, int position) {
            this.itemPath = itemPath;
            this.position = position;
        }

        @Override
        public String getMaterialPath() {
            return BASE_PATH + itemPath + MATERIAL_PATH;
        }

        @Override
        public String getNamePath() {
            return BASE_PATH + itemPath + NAME_PATH;
        }

        @Override
        public String getLorePath() {
            return BASE_PATH + itemPath + LORE_PATH;
        }
    }
}