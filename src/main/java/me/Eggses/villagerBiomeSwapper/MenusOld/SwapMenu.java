package me.Eggses.villagerBiomeSwapper.MenusOld;

import me.Eggses.villagerBiomeSwapper.Config.CustomConfigurationFile;
import me.Eggses.villagerBiomeSwapper.DataManager.TraderManager;
import me.Eggses.villagerBiomeSwapper.Items.SwapperItem;
import me.Eggses.villagerBiomeSwapper.Utility.MessageCreation;
import me.Eggses.villagerBiomeSwapper.VillagerBiomeSwapper;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.Map;

public class SwapMenu extends Menu {

    private static final int INFO = 13;
    private static final int BIOME_PLAINS = 19;
    private static final int BIOME_SAVANNA = 20;
    private static final int BIOME_DESERT = 21;
    private static final int BIOME_TAIGA = 22;
    private static final int BIOME_SNOWY = 23;
    private static final int BIOME_JUNGLE = 24;
    private static final int BIOME_SWAMP = 25;
    private static final int CLOSE = 40;

    private static final String CONFIG_BASE = "biome-swapper-gui.";
    private static final String INFO_PATH = CONFIG_BASE + "info-item";
    private static final String PLAINS_PATH = CONFIG_BASE + "plains-item";
    private static final String SAVANNA_PATH = CONFIG_BASE + "savanna-item";
    private static final String DESERT_PATH = CONFIG_BASE + "desert-item";
    private static final String TAIGA_PATH = CONFIG_BASE + "taiga-item";
    private static final String SNOWY_PATH = CONFIG_BASE + "snowy-item";
    private static final String JUNGLE_PATH = CONFIG_BASE + "jungle-item";
    private static final String SWAMP_PATH = CONFIG_BASE + "swamp-item";
    private static final String CLOSE_PATH = CONFIG_BASE + "close-item";

    private final VillagerBiomeSwapper plugin;
    private final TraderManager traderManager;
    private final MessageCreation messageCreation;
    private final CustomConfigurationFile guiFile;
    private final MenuMetaData menuKey;
    private final SwapperItem swapperItem;

    public SwapMenu(VillagerBiomeSwapper plugin, TraderManager traderManager,
                    MessageCreation messageCreation, CustomConfigurationFile guiFile,
                    MenuMetaData menuKey, SwapperItem swapperItem) {
        super(messageCreation, guiFile);
        this.plugin = plugin;
        this.traderManager = traderManager;
        this.messageCreation = messageCreation;
        this.guiFile = guiFile;
        this.menuKey = menuKey;
        this.swapperItem = swapperItem;
    }

    @Override
    public void createInventory(Player player, Map<String, String> placeHolders) {

        Component title = messageCreation.createMessage(guiFile.getCustomFile().getString(CONFIG_BASE + "title"), placeHolders);

        Inventory inventory = Bukkit.createInventory(player, 9 * 5, title);

        ItemStack info = createItem(INFO_PATH + ".item-material", INFO_PATH + ".item-name", INFO_PATH + ".item-lore");
        ItemStack plains = createItem(PLAINS_PATH + ".item-material", PLAINS_PATH + ".item-name", PLAINS_PATH + ".item-lore");
        ItemStack savanna = createItem(SAVANNA_PATH + ".item-material", SAVANNA_PATH + ".item-name", SAVANNA_PATH + ".item-lore");
        ItemStack desert = createItem(DESERT_PATH + ".item-material", DESERT_PATH + ".item-name", DESERT_PATH + ".item-lore");
        ItemStack taiga = createItem(TAIGA_PATH + ".item-material", TAIGA_PATH + ".item-name", TAIGA_PATH + ".item-lore");
        ItemStack snowy = createItem(SNOWY_PATH + ".item-material", SNOWY_PATH + ".item-name", SNOWY_PATH + ".item-lore");
        ItemStack jungle = createItem(JUNGLE_PATH + ".item-material", JUNGLE_PATH + ".item-name", JUNGLE_PATH + ".item-lore");
        ItemStack swamp = createItem(SWAMP_PATH + ".item-material", SWAMP_PATH + ".item-name", SWAMP_PATH + ".item-lore");
        ItemStack close = createItem(CLOSE_PATH + ".item-material", CLOSE_PATH + ".item-name", CLOSE_PATH + ".item-lore");
        ItemStack panelItem = createPanelItem(CONFIG_BASE + "panel-item.item-material");

        inventory.setItem(INFO, info);
        inventory.setItem(BIOME_PLAINS, plains);
        inventory.setItem(BIOME_SAVANNA, savanna);
        inventory.setItem(BIOME_DESERT, desert);
        inventory.setItem(BIOME_TAIGA, taiga);
        inventory.setItem(BIOME_SNOWY, snowy);
        inventory.setItem(BIOME_JUNGLE, jungle);
        inventory.setItem(BIOME_SWAMP, swamp);
        inventory.setItem(CLOSE, close);


        ItemStack[] inventoryContents = inventory.getContents();
        for (int i = 0; i < inventoryContents.length; i++) {
            if (inventoryContents[i] == null) {
                inventory.setItem(i, panelItem);
            }
        }

        Villager villager = traderManager.getVillagerMap().get(player);

        for (VillagerBiomeTypeSlot typeSlot : VillagerBiomeTypeSlot.values()) {

            if (villager.getVillagerType() == typeSlot.getBiomeType()) {

                ItemStack biomeItem = inventory.getItem(typeSlot.getSlot());
                assert biomeItem != null;
                ItemMeta biomeItemMeta = biomeItem.getItemMeta();
                biomeItemMeta.setEnchantmentGlintOverride(true);
                biomeItem.setItemMeta(biomeItemMeta);

                traderManager.getCurrentVillagerType().put(player, typeSlot.getBiomeType());

                break;
            }
        }

        player.setMetadata(menuKey.getKey(), new FixedMetadataValue(plugin, true));
        player.openInventory(inventory);
    }

    @Override
    public void run(Player player, int slotClicked) {

        Villager.Type currentBiomeType = traderManager.getCurrentVillagerType().get(player);
        Villager villager = traderManager.getVillagerMap().get(player);

        switch (slotClicked) {
            case BIOME_PLAINS -> {
                if (slotClicked != VillagerBiomeTypeSlot.getSlot(currentBiomeType)) {
                    convert(player, villager, Villager.Type.PLAINS);
                }
            }
            case BIOME_SAVANNA -> {
                if (slotClicked != VillagerBiomeTypeSlot.getSlot(currentBiomeType)) {
                    convert(player, villager, Villager.Type.SAVANNA);
                }
            }
            case BIOME_DESERT -> {
                if (slotClicked != VillagerBiomeTypeSlot.getSlot(currentBiomeType)) {
                    convert(player, villager, Villager.Type.DESERT);
                }
            }
            case BIOME_TAIGA -> {
                if (slotClicked != VillagerBiomeTypeSlot.getSlot(currentBiomeType)) {
                    convert(player, villager, Villager.Type.TAIGA);
                }
            }
            case BIOME_SNOWY -> {
                if (slotClicked != VillagerBiomeTypeSlot.getSlot(currentBiomeType)) {
                    convert(player, villager, Villager.Type.SNOW);
                }
            }
            case BIOME_JUNGLE -> {
                if (slotClicked != VillagerBiomeTypeSlot.getSlot(currentBiomeType)) {
                    convert(player, villager, Villager.Type.JUNGLE);
                }
            }
            case BIOME_SWAMP -> {
                if (slotClicked != VillagerBiomeTypeSlot.getSlot(currentBiomeType)) {
                    convert(player, villager, Villager.Type.SWAMP);
                }
            }
            case CLOSE -> {
                player.closeInventory();
            }
            default -> {
                // Nothing (runs if other slots clicked)
            }
        }
    }

    private void convert(Player player, Villager villager, Villager.Type newBiome) {

        // Remove Item
        ItemStack mainHand = player.getInventory().getItemInMainHand();
        ItemStack offHand = player.getInventory().getItemInOffHand();

        if (swapperItem.isSwapperItem(mainHand)) {
            player.getInventory().setItemInMainHand(null);
        }
        else if (swapperItem.isSwapperItem(offHand)) {
            player.getInventory().setItemInOffHand(null);
        }

        // Swap Villager
        villager.setVillagerType(newBiome);

        // Play Sound
        if (plugin.getConfig().getBoolean("biome-swap-play-sound")) {

            String soundToPlay = "minecraft:entity.evoker.prepare_wololo";
            String configSoundToPlay = plugin.getConfig().getString("biome-swap-sound");
            if (configSoundToPlay != null) {
                soundToPlay = configSoundToPlay;
            }
            Sound sound = Sound.sound(Key.key(soundToPlay), Sound.Source.PLAYER, 1.0f, 1.0f);

            player.playSound(sound, Sound.Emitter.self());
        }

        // Play Particles

        Location center = villager.getLocation().add(0, 1.0, 0);

        // Particle settings
        Particle particleType = Particle.SPORE_BLOSSOM_AIR;
        int particleCount = 10; // Number of particles to spawn
        double radius = 0.5; // Radius of the particle effect around the villager

        // Spawn particles in a circular pattern around the villager
        for (int i = 0; i < 360; i += 10) { // Iterate 360 degrees in steps of 10
            double angle = Math.toRadians(i);
            double x = radius * Math.cos(angle);
            double z = radius * Math.sin(angle);

            // Create new location for each particle
            Location particleLoc = center.clone().add(x, 0, z);

            // Spawn particle for the player (only the specified player sees it)
            player.spawnParticle(particleType, particleLoc, particleCount, 0.1, 0.1, 0.1, 0);
        }
    }


    private enum VillagerBiomeTypeSlot {
        PLAINS(Villager.Type.PLAINS, BIOME_PLAINS),
        SAVANNA(Villager.Type.SAVANNA, BIOME_SAVANNA),
        DESERT(Villager.Type.DESERT, BIOME_DESERT),
        TAIGA(Villager.Type.TAIGA, BIOME_TAIGA),
        SNOW(Villager.Type.SNOW, BIOME_SNOWY),
        JUNGLE(Villager.Type.JUNGLE, BIOME_JUNGLE),
        SWAMP(Villager.Type.SWAMP, BIOME_SWAMP);

        private final Villager.Type biomeType;
        private final int slot;

        VillagerBiomeTypeSlot(Villager.Type biomeType, int slot) {
            this.biomeType = biomeType;
            this.slot = slot;
        }

        public Villager.Type getBiomeType() {
            return biomeType;
        }

        public int getSlot() {
            return slot;
        }

        public static int getSlot(Villager.Type villagerType) {

            for (VillagerBiomeTypeSlot typeSlot : VillagerBiomeTypeSlot.values()) {
                if (villagerType.equals(typeSlot.getBiomeType())) {
                    return typeSlot.getSlot();

                }
            }
            return -1;
        }
    }
}