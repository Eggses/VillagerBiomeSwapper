package me.Eggses.villagerBiomeSwapper.Menus;

import me.Eggses.villagerBiomeSwapper.Config.CustomConfigurationFile;
import me.Eggses.villagerBiomeSwapper.Utility.MessageCreation;
import org.bukkit.entity.*;

public class SwapMenu extends Menu {

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

    public SwapMenu(MessageCreation messageCreation, CustomConfigurationFile guiFile) {
        super(messageCreation, guiFile);
        this.messageCreation = messageCreation;
        this.guiFile = guiFile;
    }

    @Override
    public void createInventory(Player player) {

    }

    @Override
    public void run(Player player, int slotClicked) {

    }
}