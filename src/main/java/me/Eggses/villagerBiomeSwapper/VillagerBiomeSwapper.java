package me.Eggses.villagerBiomeSwapper;

import me.Eggses.villagerBiomeSwapper.Commands.BaseCommand;
import me.Eggses.villagerBiomeSwapper.Config.CustomConfigurationFile;
import me.Eggses.villagerBiomeSwapper.Config.Messages;
import me.Eggses.villagerBiomeSwapper.DataManager.TraderManager;
import me.Eggses.villagerBiomeSwapper.Items.SwapperItem;
import me.Eggses.villagerBiomeSwapper.Listeners.RightClickEntity;
import me.Eggses.villagerBiomeSwapper.Menus.SwapMenu;
import me.Eggses.villagerBiomeSwapper.Utility.Commands;
import me.Eggses.villagerBiomeSwapper.Utility.MessageCreation;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class VillagerBiomeSwapper extends JavaPlugin {

    @Override
    public void onEnable() {

        // Configuration Files
        saveDefaultConfig();
        CustomConfigurationFile messagesFile = new CustomConfigurationFile(this, "messages.yml");
        CustomConfigurationFile biomeSwapperItemFile = new CustomConfigurationFile(this, "biomeSwapperItem.yml");
        CustomConfigurationFile guiFile = new CustomConfigurationFile(this, "gui.yml");

        // Messages
        MessageCreation messageCreation = new MessageCreation(messagesFile);
        Messages.setMessagesFile(messagesFile);

        // Item Managers
        SwapperItem swapperItem = new SwapperItem(this, messageCreation, biomeSwapperItemFile);

        // Data Managers
        TraderManager traderManager = new TraderManager();

        // Menus
        SwapMenu swapMenu = new SwapMenu(messageCreation, guiFile);

        // Commands
        Objects.requireNonNull(getCommand(Commands.BASE.getCommand())).setExecutor(
                new BaseCommand(this, biomeSwapperItemFile, guiFile, messagesFile, messageCreation, swapperItem));


        // Events
        getServer().getPluginManager().registerEvents((new RightClickEntity
                (this, swapperItem, swapMenu, traderManager)), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
