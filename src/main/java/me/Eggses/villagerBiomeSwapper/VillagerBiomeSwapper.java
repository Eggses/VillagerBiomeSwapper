package me.Eggses.villagerBiomeSwapper;

import me.Eggses.villagerBiomeSwapper.Listeners.RightClickEntity;
import me.Eggses.villagerBiomeSwapper.Utility.MessageCreation;
import org.bukkit.plugin.java.JavaPlugin;

public final class VillagerBiomeSwapper extends JavaPlugin {

    @Override
    public void onEnable() {

        saveDefaultConfig();

        MessageCreation messageCreation = new MessageCreation();



        getServer().getPluginManager().registerEvents((new RightClickEntity(messageCreation)), this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
