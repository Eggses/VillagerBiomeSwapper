package me.Eggses.villagerBiomeSwapper.Commands;

import me.Eggses.villagerBiomeSwapper.Commands.SubCommands.GiveItemCommand;
import me.Eggses.villagerBiomeSwapper.Commands.SubCommands.HelpCommand;
import me.Eggses.villagerBiomeSwapper.Commands.SubCommands.ReloadCommand;
import me.Eggses.villagerBiomeSwapper.Commands.SubCommands.SubCommand;
import me.Eggses.villagerBiomeSwapper.Config.CustomConfigurationFile;
import me.Eggses.villagerBiomeSwapper.Config.Messages;
import me.Eggses.villagerBiomeSwapper.Utility.Commands;
import me.Eggses.villagerBiomeSwapper.Utility.MessageCreation;
import me.Eggses.villagerBiomeSwapper.Utility.Permission;
import me.Eggses.villagerBiomeSwapper.Utility.PlaceHolder;
import me.Eggses.villagerBiomeSwapper.VillagerBiomeSwapper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class BaseCommand implements CommandExecutor {

    private final VillagerBiomeSwapper plugin;
    private final CustomConfigurationFile biomeSwapperItemFile;
    private final CustomConfigurationFile guiFile;
    private final CustomConfigurationFile messagesFile;
    private final MessageCreation messageCreation;

    Map<String, SubCommand> subCommandMap = new HashMap<>();

    public BaseCommand(VillagerBiomeSwapper plugin, CustomConfigurationFile biomeSwapperItemFile,
                       CustomConfigurationFile guiFile, CustomConfigurationFile messagesFile,
                       MessageCreation messageCreation) {

        this.plugin = plugin;
        this.biomeSwapperItemFile = biomeSwapperItemFile;
        this.guiFile = guiFile;
        this.messagesFile = messagesFile;
        this.messageCreation = messageCreation;

        registerSubCommands();
    }

    private void registerSubCommands() {

        subCommandMap.put(Commands.GIVE.getCommand(), new GiveItemCommand(messageCreation));

        subCommandMap.put(Commands.HELP.getCommand(), new HelpCommand(messageCreation));

        subCommandMap.put(Commands.RELOAD.getCommand(),
                new ReloadCommand(plugin, biomeSwapperItemFile, guiFile, messagesFile, messageCreation));
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
                                @NotNull String s, @NotNull String @NotNull [] args) {

        String name = sender.getName();
        Map<String, String> placeHolders = new HashMap<>();
        placeHolders.put(PlaceHolder.PLAYER.getPlaceHolder(), name);

        // No Permission
        if (!sender.hasPermission(Permission.BASE.getPermission())) {
            sender.sendMessage(messageCreation.createMessage(Messages.PERMISSION_FAIL.getMessage(), placeHolders));
            return true;
        }

        // No Arguments
        if (args.length == 0) {
            sender.sendMessage(messageCreation.createMessage(Messages.UNKNOWN_COMMAND.getMessage(), placeHolders));
            return true;
        }

        // Run SubCommand
        SubCommand subCommand = subCommandMap.get(args[0].toLowerCase());

        if (subCommand != null) {
            subCommand.execute(sender, args, placeHolders);
        }
        else {
            sender.sendMessage(messageCreation.createMessage(Messages.UNKNOWN_COMMAND.getMessage(), placeHolders));
            return true;
        }

        return true;
    }
}
