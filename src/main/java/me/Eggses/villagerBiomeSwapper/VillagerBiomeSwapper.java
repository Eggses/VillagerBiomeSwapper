package me.Eggses.villagerBiomeSwapper;

import me.Eggses.villagerBiomeSwapper.Config.CustomConfigurationFile;
import me.Eggses.villagerBiomeSwapper.Items.ItemKeys;
import me.Eggses.villagerBiomeSwapper.Items.ItemManager;
import me.Eggses.villagerBiomeSwapper.Listeners.RightClickEntity;
import me.Eggses.villagerBiomeSwapper.Utility.MessageCreation;
import org.bukkit.plugin.java.JavaPlugin;

public final class VillagerBiomeSwapper extends JavaPlugin {

    @Override
    public void onEnable() {

        // Configuration Files
        saveDefaultConfig();

        CustomConfigurationFile itemsFile = new CustomConfigurationFile(this, "items.yml");
        CustomConfigurationFile guiFile = new CustomConfigurationFile(this, "gui.yml");

        MessageCreation messageCreation = new MessageCreation();

        ItemManager swapperItem = new ItemManager(this, messageCreation, itemsFile, ItemKeys.SWAPPER);



        // Commands


        // Events
        getServer().getPluginManager().registerEvents((new RightClickEntity(messageCreation)), this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
