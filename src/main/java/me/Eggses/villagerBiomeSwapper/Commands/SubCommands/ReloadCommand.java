package me.Eggses.villagerBiomeSwapper.Commands.SubCommands;

import me.Eggses.villagerBiomeSwapper.Config.CustomConfigurationFile;
import me.Eggses.villagerBiomeSwapper.Config.Messages;
import me.Eggses.villagerBiomeSwapper.Utility.Commands;
import me.Eggses.villagerBiomeSwapper.Utility.MessageCreation;
import me.Eggses.villagerBiomeSwapper.Utility.Permission;
import me.Eggses.villagerBiomeSwapper.VillagerBiomeSwapper;
import org.bukkit.command.CommandSender;

import java.util.Map;

public class ReloadCommand implements SubCommand {
    
    private final VillagerBiomeSwapper plugin;
    private final CustomConfigurationFile biomeSwapperItemFile;
    private final CustomConfigurationFile guiFile;
    private final CustomConfigurationFile messagesFile;
    private final MessageCreation messageCreation;
    
    public ReloadCommand(VillagerBiomeSwapper plugin, CustomConfigurationFile biomeSwapperItemFile, 
                         CustomConfigurationFile guiFile, CustomConfigurationFile messagesFile,
                         MessageCreation messageCreation) {
        
        this.plugin = plugin;
        this.biomeSwapperItemFile = biomeSwapperItemFile;
        this.guiFile = guiFile;
        this.messagesFile = messagesFile;
        this.messageCreation = messageCreation;
    }

    @Override
    public void execute(CommandSender sender, String[] args, Map<String, String> placeHolders) {

        if (!sender.hasPermission(Permission.RELOAD.getPermission())) {
            sender.sendMessage(messageCreation.createMessage(Messages.PERMISSION_FAIL.getMessage(), placeHolders));
            return;
        }
        
        if (args.length > 2) {
            sender.sendMessage(messageCreation.createMessage(Messages.RELOAD_SYNTAX_ERROR.getMessage(), placeHolders));
            return;
        }
        
        if (args.length == 1) {
            sender.sendMessage(messageCreation.createMessage(Messages.RELOAD_ALL_CONFIGS.getMessage(), placeHolders));
            plugin.reloadConfig();
            biomeSwapperItemFile.reloadCustomFile();
            guiFile.reloadCustomFile();
            messagesFile.reloadCustomFile();
        }

        String file = args[1].toLowerCase();
        
        if (file.equals(Commands.RELOAD_CONFIG.getCommand())) {
            sender.sendMessage(messageCreation.createMessage(Messages.RELOAD_CONFIG.getMessage(), placeHolders));
            plugin.reloadConfig();
        }
        else if (file.equals(Commands.RELOAD_MESSAGES.getCommand())) {
            sender.sendMessage(messageCreation.createMessage(Messages.RELOAD_MESSAGES.getMessage(), placeHolders));
            messagesFile.reloadCustomFile();
        }
        else if (file.equals(Commands.RELOAD_ITEM.getCommand())) {
            sender.sendMessage(messageCreation.createMessage(Messages.RELOAD_BIOME_SWAPPER_ITEM.getMessage(), placeHolders));
            biomeSwapperItemFile.reloadCustomFile();
        }
        else if (file.equals(Commands.RELOAD_GUI.getCommand())) {
            sender.sendMessage(messageCreation.createMessage(Messages.RELOAD_GUI.getMessage(), placeHolders));
            guiFile.reloadCustomFile();
        }
        else {
            sender.sendMessage(messageCreation.createMessage(Messages.RELOAD_SYNTAX_ERROR.getMessage(), placeHolders));
        }
    }
}