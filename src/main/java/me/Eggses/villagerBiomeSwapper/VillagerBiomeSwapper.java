package me.Eggses.villagerBiomeSwapper;

import me.Eggses.villagerBiomeSwapper.Commands.BaseCommand;
import me.Eggses.villagerBiomeSwapper.Config.CustomConfigurationFile;
import me.Eggses.villagerBiomeSwapper.Config.Messages;
import me.Eggses.villagerBiomeSwapper.Listeners.RightClickEntity;
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

        MessageCreation messageCreation = new MessageCreation(messagesFile);

        Messages.setMessagesFile(messagesFile);


        // Commands
        Objects.requireNonNull(getCommand(Commands.BASE.getCommand())).setExecutor(
                new BaseCommand(this, biomeSwapperItemFile, guiFile, messagesFile, messageCreation));


        // Events
        getServer().getPluginManager().registerEvents((new RightClickEntity(messageCreation)), this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
