package me.Eggses.villagerBiomeSwapper;

import me.Eggses.villagerBiomeSwapper.Commands.BaseCommand;
import me.Eggses.villagerBiomeSwapper.Commands.TabCompleter.VBSTabCompleter;
import me.Eggses.villagerBiomeSwapper.Config.CustomConfigurationFile;
import me.Eggses.villagerBiomeSwapper.Config.Messages;
import me.Eggses.villagerBiomeSwapper.Items.SwapperItem;
import me.Eggses.villagerBiomeSwapper.Listeners.InventoryEvent;
import me.Eggses.villagerBiomeSwapper.Listeners.RightClickEntity;
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
        MessageCreation messageCreation = new MessageCreation();
        Messages.setMessagesFile(messagesFile);

        // Item Manager
        SwapperItem swapperItem = new SwapperItem(this, messageCreation, biomeSwapperItemFile);

        // Events
        getServer().getPluginManager().registerEvents(new RightClickEntity(this, swapperItem, messageCreation, guiFile), this);
        getServer().getPluginManager().registerEvents(new InventoryEvent(), this);

        // Commands
        Objects.requireNonNull(getCommand("villagerbiomeswapper")).setExecutor(new BaseCommand(this, biomeSwapperItemFile, guiFile, messagesFile, messageCreation, swapperItem));
        Objects.requireNonNull(getCommand("villagerbiomeswapper")).setTabCompleter(new VBSTabCompleter());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
